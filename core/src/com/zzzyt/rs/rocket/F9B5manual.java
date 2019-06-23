package com.zzzyt.rs.rocket;

import com.zzzyt.rs.RocketSimulator;
import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.data.Stage;
import com.zzzyt.rs.phy.Physics;

public class F9B5manual {
	public static Rocket get(RocketSimulator rs) {
		Rocket ret = new Rocket("Falcon 9 Block 5 Demo", 0, 0, Physics.R + 10, 0, 0, 0,true);

		ret.stages.add(new Stage(ret) {
			public RocketSimulator game=rs;
			public double getDrag() {return 3.3;}
			public double getThrust() {return 7586 * 1000*game.control.tr;}
			public double getTheta() {
				return game.control.theta;
			}
			public double getTime() {return 150;}
			public double getDryMass() {return 3795;}
			public double getFuelMass() {return 453750;}
			public double getFlow() {return 3025*game.control.tr;}
		});

		ret.stages.add(new Stage(ret) {
			public RocketSimulator game=rs;
			public double getDrag() {return 3.3;}
			public double getThrust() {return 981 * 1000*game.control.tr;}
			public double getTheta() {
				return game.control.theta;
			}
			public double getTime() {return 360;}
			public double getDryMass() {return 1509;}
			public double getFuelMass() {return 90000;}
			public double getFlow() {return 250*game.control.tr;}
		});

		ret.stages.add(new Stage(ret) {
			public double getDrag() {return 3.3;}
			public double getTheta() {return 0;}
			public double getDryMass() {return 8000;}
			public double getFuelMass() {return 0;}
			public double getThrust() {return 0;}
			public double getFlow() {return 0;}
		});
		ret.init();
		
		return ret;
	}
}
