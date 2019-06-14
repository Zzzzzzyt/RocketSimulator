package com.zzzyt.rs.rocket;

import com.zzzyt.rs.data.Rocket;
import com.zzzyt.rs.data.Stage;
import com.zzzyt.rs.phy.Physics;

public class F9B5 {
	public static Rocket get() {
		Rocket r = new Rocket("Falcon 9 Block 5 Demo", 0, 0, Physics.R + 10, 0, 0, 0);

		r.stages.add(new Stage() {
			public double getDrag(Rocket r) {return 3.3;}
			public double getTr(Rocket r) {return 7586 * 1000;}
			public double getTheta(Rocket r) {
				if (r.t <= 100)
					return Math.PI / 2;
				return Math.PI / 2 * (180 - r.t) / 80;
			}
			public double getMass(Rocket r) {return 457545 - r.t * 3025;}
			public double getM0() {return 457545;}
			public double getTime() {return 150;}
		});

		r.stages.add(new Stage() {
			public double getDrag(Rocket r) {return 3.3;}
			public double getTr(Rocket r) {return 981 * 1000;}
			public double getTheta(Rocket r) {
				if (r.t >= 180)
					return 0;
				return Math.PI / 2 * (180 - r.t) / 80;
			}
			public double getMass(Rocket r) {return 91509 - (r.t - 150) * 250;}
			public double getM0() {return 91509;}
			public double getTime() {return 360;}
		});

		r.stages.add(new Stage() {
			public double getDrag(Rocket r) {return 3.3;}
			public double getTr(Rocket r) {return 0;}
			public double getTheta(Rocket r) {return 0;}
			public double getMass(Rocket r) {return 18000;}
			public double getM0() {return 18000;}
			public double getTime() {return 1E20;}
		});
		r.init();
		
		return r;
	}
}
