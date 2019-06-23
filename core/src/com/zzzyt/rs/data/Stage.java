package com.zzzyt.rs.data;

public abstract class Stage {
	public Rocket r;
	public double f;
	
	public abstract double getDryMass();
	
	public abstract double getFuelMass();
	
	public double getTime() {return -1;};
	
	public abstract double getDrag();

	public abstract double getThrust();

	public abstract double getTheta();

	public abstract double getFlow();
	
	public Stage(Rocket r) {
		this.r=r;
		this.f=getFuelMass();
	}

}
