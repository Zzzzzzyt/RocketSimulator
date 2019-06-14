package com.zzzyt.rs.phy;

import com.zzzyt.rs.RocketSimulator;

public class Simulator {
	public double fps;
	public RocketSimulator rs;
	public boolean stopped;
	private double last;
	
	public Simulator(RocketSimulator rs,double fps){
		this.fps=fps;
		this.rs=rs;
		this.stopped=false;
	}
	
	public void sim() {
		last=rs.r.t;
		while(rs.r.t<last+1/fps*50) {
			Physics.sim(rs.r);
			if (Physics.tri(rs.r.x, rs.r.y) < Physics.R) {
				System.out.printf("Crash! t=%.2f\n", rs.r.t);
				stopped=true;
				break;
			}
		}
		rs.r.debug();
	}
}
