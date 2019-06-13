package com.zzzyt.rs.data;

public abstract class Stage {
	public abstract double getM0();
	
	public abstract double getTime();
	
	public abstract double getDrag(Rocket r);

	public abstract double getTr(Rocket r);

	public abstract double getTheta(Rocket r);

	public abstract double getMass(Rocket r);
	
	public Stage() {
		
	}

}
