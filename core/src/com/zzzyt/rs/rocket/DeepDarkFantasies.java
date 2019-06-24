package com.zzzyt.rs.rocket;

import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.data.Stage;
import com.zzzyt.rs.phy.Physics;

public class DeepDarkFantasies extends Rocket{	
	public DeepDarkFantasies() {
		super("The Deep Dark Fantasies Rocket", 0, 0, Physics.R + 10, 0, 0, 0,false);

		this.stages.add(new Stage(this) {
			public double getDrag() {return 0;}
			public double getThrust() {return 10000000;}
			public double getDryMass() {return 7999;}
			public double getFuelMass() {return 1;}
			public double getFlow() {return 0.0001;}
		});
		this.init();
	}
}
