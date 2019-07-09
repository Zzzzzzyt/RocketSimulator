package com.zzzyt.rs.rocket;

import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.data.Stage;
import com.zzzyt.rs.phy.Phy;

public class F9B5auto2 extends Rocket{
	public void guide() {
		if (t <= 100) {
			dir=Math.PI / 2;
		}
		else if(t<=180){
			dir=Math.PI / 2 * (180 - t) / 80;
		}
		else if(t>3000&&vy>=0){
			dir=Math.PI;
		}
		if(t<=500) {
			throttle=1;
		}
		else if(t>3000&&vy>=0) {
			throttle=1;
		}
		else {
			throttle=0;
		}
		if(stages.get(stage).f<=0&&stage<2)stage();
	}
	
	public F9B5auto2() {
		super("Falcon 9 Block 5 Auto #2", 0, 0, Phy.R + 10, 0, 0, 0,false);

		this.stages.add(new Stage(this) {
			public double getDrag() {return 3.3;}
			public double getThrust() {return 7586 * 1000;}
			public double getDryMass() {return 3795;}
			public double getFuelMass() {return 453750;}
			public double getFlow() {return 3025;}
		});

		this.stages.add(new Stage(this) {
			public double getDrag() {return 3.3;}
			public double getThrust() {return 981 * 1000;}
			public double getDryMass() {return 1509;}
			public double getFuelMass() {return 90000;}
			public double getFlow() {return 250;}
		});

		this.stages.add(new Stage(this) {
			public double getDrag() {return 3.3;}
			public double getDryMass() {return 4500;}
			public double getFuelMass() {return 0;}
			public double getThrust() {return 0;}
			public double getFlow() {return 0;}
		});
		this.init();
	}
}
