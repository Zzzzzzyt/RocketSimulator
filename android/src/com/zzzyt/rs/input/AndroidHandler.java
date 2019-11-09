package com.zzzyt.rs.input;

import com.badlogic.gdx.Gdx;
import com.zzzyt.rs.Handler;
import com.zzzyt.rs.RocketSimulator;
import com.zzzyt.rs.draw.ButtonDrawer;

public class AndroidHandler implements Handler{
	private static final double[] speeds= {0,0.1,0.2,0.5,1,1.5,2,5,10,50,100,500,1000,10000,100000,1000000};
	private int spd;
	private RocketSimulator rs;
	private ButtonDrawer b;

	private float x,y,h,w,scale;
	private boolean Pdirl,Pdirr,Pgiml,Pgimr,Pthu,Pthd,Pthmax,Pthmin,Pspu,Pspd;

	private boolean inBox(float x,float y,float x1,float y1,float w,float h) {
		return x1-w/2<=x&&y1-h/2<=y&&x<=x1+w/2&&y<=y1+h/2;
	}

	@Override
	public void handle() {
		if(b==null)b=RocketSimulator.rs.drawer.buttons;
		h=RocketSimulator.rs.h;
		w=RocketSimulator.rs.w;

		Pdirl=Pdirr=Pgiml=Pgimr=Pthu=Pthd=Pthmax=Pthmin=Pspu=Pspd=false;

		for(int p = 0; p<10;p++){
			if(!Gdx.input.isTouched(p))continue;
			x=Gdx.input.getX(p);
			y=Gdx.graphics.getHeight()-1-Gdx.input.getY(p);

			scale=0.5f;
			if(Math.min(h,w*9/16)>700)scale=1f;

			if(inBox(x,y,w-200*scale, 80*scale,100,100)) {
				//dirl
				rs.r.dir+=0.02;
				Pdirl=true;
			}
			else if(inBox(x,y,w-80*scale, 80*scale,100,100)) {
				//dirr
				rs.r.dir-=0.02;
				Pdirr=true;
			}
			else if(inBox(x,y,w-200*scale, 200*scale,100,100)) {
				//giml
				rs.r.gimbal+=0.01;
				if(rs.r.gimbal>0.2)rs.r.gimbal=0.2;
				Pgiml=true;
			}
			else if(inBox(x,y,w-80*scale, 200*scale,100,100)) {
				//gimr
				rs.r.gimbal -= 0.01;
				if (rs.r.gimbal < -0.2) rs.r.gimbal = -0.2;
				Pgimr = true;
			}
			else if(inBox(x,y,80*scale, 200*scale,100,100)) {
				//thu
				rs.r.throttle+=0.005;
				if(rs.r.throttle>1)rs.r.throttle=1;
				Pthu=true;
			}
			else if(inBox(x,y,80*scale,80*scale,100,100)) {
				//thd
				rs.r.throttle-=0.005;
				if(rs.r.throttle<0)rs.r.throttle=0;
				Pthd=true;
			}
			else if(inBox(x,y,200*scale,200*scale,100,100)) {
				//thmax
				rs.r.throttle=1;
				Pthmax=true;
			}
			else if(inBox(x,y,200*scale,80*scale,100,100)) {
				//thmin
				rs.r.throttle=0;
				Pthmin=true;
			}
			else if(inBox(x,y,w-80*scale,h-80*scale,100,100)) {
				//spu
				if(!b.Pspu) {
					if (speeds[spd] != rs.sim.speed) {
						for (int i = speeds.length - 1; i >= 0; i--) {
							if (speeds[i] <= rs.sim.speed) {
								spd = i;
							}
						}
					}
					if (spd < speeds.length - 1) spd++;
					rs.sim.speed = speeds[spd];
				}
				Pspu=true;
			}
			else if(inBox(x,y,w-80*scale,h-200*scale,100,100)) {
				//spd
				if(!b.Pspd){
					if(speeds[spd]!=rs.sim.speed) {
						for(int i=speeds.length-1;i>=0;i--) {
							if(speeds[i]<=rs.sim.speed) {
								spd=i;
							}
						}
					}
					if(spd>0)spd--;
					rs.sim.speed=speeds[spd];
				}
				Pspd=true;
			}
		}

		b.Pdirl=Pdirl;
		b.Pdirr=Pdirr;
		b.Pgiml=Pgiml;
		b.Pgimr=Pgimr;
		b.Pthu=Pthu;
		b.Pthd=Pthd;
		b.Pthmax=Pthmax;
		b.Pthmin=Pthmin;
		b.Pspu=Pspu;
		b.Pspd=Pspd;
	}

	public AndroidHandler(){
		this.rs=RocketSimulator.rs;
		this.spd=4;
	}
}
