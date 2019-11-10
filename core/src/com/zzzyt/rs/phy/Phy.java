package com.zzzyt.rs.phy;

import com.badlogic.gdx.Gdx;
import com.zzzyt.rs.RocketSimulator;
import com.zzzyt.rs.type.Rocket;
import com.zzzyt.rs.type.Stage;
import com.zzzyt.rs.util.StringUtil;

public class Phy {
	public final static double G = 6.67428E-11d;
	public final static double M = 5.977028E24d;
	public final static double GM = 3.989236E14d;
	public final static double R = 6378000;
	public final static double up = 100000;
	
	public static double dt=0.05;
	
	private static double gravity,drag,ax,ay,tr,theta,m,x,y,vx,vy;
	private static Stage stg;
	
	public static boolean eq(double x, double y) {
		return Math.abs(x - y) < 1E-6;
	}

	public static double getg(double x, double y) {
		return GM / (x * x + y * y);
	}

	public static double tri(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
	
	public static boolean sim(Rocket r) {
		if(RocketSimulator.rs.sim.speed>=100000) {
			dt=0.2;
		}
		else {
			dt=0.05;
		}
		
		
		stg=r.stages.get(r.stage);
		x=r.x;
		y=r.y;
		vx=r.vx;
		vy=r.vy;
		gravity = -getg(x, y);
		m=r.getMass();
		if(tri(x,y)>=up+R) {
			drag=0;
		}
		else {
			drag=r.getDrag();
			drag=drag*(R+up-tri(x,y))/up;
		}
		drag = -drag * tri(vx, vy) / m;
		
		theta=r.getTheta();
		tr=r.getThrust();
		
		ay = gravity / tri(x, y) * y;
		ax = gravity / tri(x, y) * x;
		ax += tr / m  * Math.cos(theta);
		ay += tr / m * Math.sin(theta);
		ax += vx * drag;
		ay += vy * drag;
		
		y += (2 * vy + ay * dt) * dt / 2;
		x += (2 * vx + ax * dt) * dt / 2;
		vy += ay * dt;
		vx += ax * dt;
		
		r.t += dt;
		r.x=x;
		r.y=y;
		r.vx=vx;
		r.vy=vy;
		if(stg.f>0)stg.f-=stg.getFlow()*r.throttle*dt;

		if (Phy.tri(r.x, r.y)-Phy.R<1) {
			if(Phy.tri(r.vx, r.vy)>10) {
				Gdx.app.log("Rocket", StringUtil.format("Crash! t=%.2f", r.t));
				return false;
			}
			else {
				r.x*=(Phy.R+1)/Phy.tri(r.x, r.y);
				r.y*=(Phy.R+1)/Phy.tri(r.x, r.y);
				r.vx=0;
				r.vy=0;
			}
		}
		
		r.checkStage();
		r.guide();
		return true;
	}
}
