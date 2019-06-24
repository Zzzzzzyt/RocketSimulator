package com.zzzyt.rs.rocket;

import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.data.Stage;
import com.zzzyt.rs.phy.Physics;

public class F9B5auto extends Rocket{
	public void guide() {
		if (t <= 100) {
			dir=Math.PI / 2;
		}
		else{
			dir=Math.PI / 2 * (180 - t) / 80;
		}
	}
	
	public F9B5auto() {
		super("Falcon 9 Block 5 Auto #1", 0, 0, Physics.R + 10, 0, 0, 0,true);

		this.stages.add(new Stage(this) {
			public double getDrag() {return 3.3;}
			public double getThrust() {return 7586 * 1000;}
			public double getTime() {return 150;}
			public double getDryMass() {return 3795;}
			public double getFuelMass() {return 453750;}
			public double getFlow() {return 3025;}
		});

		this.stages.add(new Stage(this) {
			public double getDrag() {return 3.3;}
			public double getThrust() {return 981 * 1000;}
			public double getTime() {return 360;}
			public double getDryMass() {return 1509;}
			public double getFuelMass() {return 90000;}
			public double getFlow() {return 250;}
		});

		this.stages.add(new Stage(this) {
			public double getDrag() {return 3.3;}
			public double getDryMass() {return 8000;}
			public double getFuelMass() {return 0;}
			public double getThrust() {return 0;}
			public double getFlow() {return 0;}
		});
		this.init();
	}
}