package com.zzzyt.rs.rocket;

import com.zzzyt.rs.type.Rocket;
import com.zzzyt.rs.type.Stage;
import com.zzzyt.rs.phy.Phy;

public class DeepDarkFantasy extends Rocket{
	
	public static double norm(double x) {
		while(x>-Math.PI/2)x-=Math.PI*2;
		while(x<3*Math.PI/2)x+=Math.PI*2;
		return x;
	}
	public void guide() {
		if(stage>0){
			if(Phy.tri(x, y)>Phy.R+2){
				dir=Math.atan2(vy, vx)+Math.PI;
			}
			else {
				dir=Math.PI/2;
			}
			double sp=0;
			if(Phy.tri(x, y)>Phy.R+3000) {
				sp=-300;
			}
			else if(Phy.tri(x, y)>Phy.R+800) {
				sp=-100;
			}
			else if(Phy.tri(x, y)>Phy.R+10) {
				sp=-15;
			}
			else if(Phy.tri(x, y)>Phy.R+2){
				sp=-1;
			}
			else {
				sp=-1000;
			}
			if(vy<sp) {
				throttle=Math.min(1,throttle+0.1);
			}
			else {
				throttle=Math.max(0, throttle-0.1);
			}
		}
	}
	public DeepDarkFantasy() {
		super("The Deep Dark Fantasies Rocket", 0, 0, Phy.R + 10, 0, 0, 0,false);
		
		this.stages.add(new Stage(this) {
			public double getDrag() {return 1;}
			public double getThrust() {return 2000000d;}
			public double getDryMass() {return 0;}
			public double getFuelMass() {return 1;}
			public double getFlow() {return 0;}
		});
		this.stages.add(new Stage(this) {
			public double getDrag() {return 1;}
			public double getThrust() {return 2000000d;}
			public double getDryMass() {return 1;}
			public double getFuelMass() {return 79998;}
			public double getFlow() {return 0;}
		});
		this.init();
	}
}
