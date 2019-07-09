package com.zzzyt.rs.phy;

import com.zzzyt.rs.RocketSimulator;

public class Simulator {
	public double fps;
	public RocketSimulator rs;
	public boolean stopped;
	public double speed;
	private double last;
	
	public Simulator(double fps,double speed){
		this.fps=fps;
		this.speed=speed;
		this.stopped=false;
		this.rs=RocketSimulator.rs;
	}
	
	public void sim() {
		last=rs.r.t;
		while(rs.r.t<last+1/fps*speed) {
			if(!Phy.sim(rs.r)) {
				stopped=true;
				break;
			}
		}		
	}
}
