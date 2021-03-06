package com.zzzyt.rs.phy;

import com.zzzyt.rs.RocketSimulator;

public class Simulator {
	public double fps;
	public RocketSimulator rs;
	public boolean stopped;
	public double speed;
	public long cnt;
	
	public Simulator(double fps,double speed){
		this.fps=fps;
		this.speed=speed;
		this.stopped=false;
		this.rs=RocketSimulator.rs;
		this.cnt=0;
	}
	
	public void sim() {
		double last;
		last=rs.r.t;
		cnt=0;
		while(rs.r.t<last+1/fps*speed) {
			if(!Phy.sim(rs.r)) {
				stopped=true;
				break;
			}
			cnt++;
		}
		rs.r.t=last+cnt*Phy.dt;
	}
}
