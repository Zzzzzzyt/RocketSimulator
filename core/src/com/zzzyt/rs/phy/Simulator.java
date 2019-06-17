package com.zzzyt.rs.phy;

import com.zzzyt.rs.RocketSimulator;

public class Simulator {
	public double fps;
	public RocketSimulator rs;
	public boolean stopped;
	public double speed;
	private double last;
	
	public Simulator(RocketSimulator rs,double fps,double speed){
		this.fps=fps;
		this.rs=rs;
		this.speed=speed;
		this.stopped=false;
	}
	
	public void sim() {
		last=rs.r.t;
		while(rs.r.t<last+1/fps*speed) {
			Physics.sim(rs.r);
			if (Physics.tri(rs.r.x, rs.r.y) < Physics.R) {
				System.out.printf("Crash! t=%.2f\n", rs.r.t);
				stopped=true;
				break;
			}
		}		
	}
}
