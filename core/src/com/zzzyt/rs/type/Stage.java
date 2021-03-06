package com.zzzyt.rs.type;

public abstract class Stage {
	public Rocket r;
	public double f;
	
	public abstract double getDryMass();
	
	public abstract double getFuelMass();
	
	public double getTime() {return -1;};
	
	public abstract double getDrag();

	public abstract double getThrust();

	public abstract double getFlow();
	
	public double getDir() {
		return Math.atan2(r.vx, r.vy);
	}
	
	public Stage(Rocket r) {
		this.r=r;
		this.f=getFuelMass();
	}

}
