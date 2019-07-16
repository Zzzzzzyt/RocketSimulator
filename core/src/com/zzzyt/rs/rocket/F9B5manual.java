package com.zzzyt.rs.rocket;

import com.zzzyt.rs.type.Rocket;
import com.zzzyt.rs.type.Stage;
import com.zzzyt.rs.phy.Phy;

public class F9B5manual extends Rocket{
	public F9B5manual() {
		super("Falcon 9 Block 5 Manual", 0, 0, Phy.R + 1, 0, 0, 0,false);

		this.stages.add(new Stage(this) {
			public double getDrag() {return 3.3;}
			public double getThrust() {return 7586 * 1000*throttle;}
			public double getTime() {return 150;}
			public double getDryMass() {return 3795;}
			public double getFuelMass() {return 453750;}
			public double getFlow() {return 3025*throttle;}
		});

		this.stages.add(new Stage(this) {
			public double getDrag() {return 3.3;}
			public double getThrust() {return 981 * 1000*throttle;}
			public double getTime() {return 360;}
			public double getDryMass() {return 1509;}
			public double getFuelMass() {return 90000;}
			public double getFlow() {return 250*throttle;}
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
