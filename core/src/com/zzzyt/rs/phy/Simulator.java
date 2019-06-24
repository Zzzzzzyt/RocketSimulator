package com.zzzyt.rs.phy;

import com.badlogic.gdx.Gdx;
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
			if (Physics.tri(rs.r.x, rs.r.y)-Physics.R<1) {
				Gdx.app.log("Rocket", String.format("Crash! t=%.2f", rs.r.t));
				stopped=true;
				break;
			}
			Physics.sim(rs.r);
		}		
	}
}
