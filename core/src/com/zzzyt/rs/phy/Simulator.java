package com.zzzyt.rs.phy;

import com.zzzyt.rs.RocketSimulator;

public class Simulator {
	public double fps;
	public RocketSimulator rs;
	public boolean stopped;
	public double speed;
	private double last;
	private int cnt;
	
	public Simulator(RocketSimulator rs,double fps,double speed){
		this.fps=fps;
		this.rs=rs;
		this.speed=speed;
		this.stopped=false;
		cnt=0;
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
		
		if(cnt==0) {
			rs.r.debug();
			cnt=(int)fps;
		}
		else {
			cnt--;
		}
		
	}
}
