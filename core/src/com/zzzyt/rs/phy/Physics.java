package com.zzzyt.rs.phy;

import com.zzzyt.rs.data.Rocket;

public class Physics {
	public final static double G = 6.67428E-11d;
	public final static double M = 5.977028E24d;
	public final static double GM = 3.989236E14d;
	public final static double R = 6378000;
	public final static double up = 80000;
	
	public final static double dt=0.01;
	
	static double gravity,drag,ax,ay,tr,theta,m,flow,x,y,vx,vy;
	
	public static boolean eq(double x, double y) {
		return Math.abs(x - y) < 1E-6;
	}

	public static double getg(double x, double y) {
		return GM / (x * x + y * y);
	}

	public static double tri(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
	
	public static void sim(Rocket r) {
		x=r.x;
		y=r.y;
		vx=r.vx;
		vy=r.vy;
		gravity = -getg(x, y);
		m=r.getMass();
		drag=r.getDrag();
		theta=r.getTheta();
		tr=r.getTr();
		
		ay = gravity / tri(x, y) * y;
		ax = gravity / tri(x, y) * x;
		ax += r.getTr() / r.getMass() * Math.cos(theta);
		ay += r.getTr() / m * Math.sin(theta);
		drag = -drag * tri(vx, vy) / m;
		ax += vx * drag;
		ay += vy * drag;
		y += (2 * vy + ay * dt) * dt / 2;
		x += (2 * vx + ax * dt) * dt / 2;
		vy += ay * dt;
		vx += ax * dt;
		
		r.t += dt;
		r.x=x;
		r.y=y;
		r.vx=vx;
		r.vy=vy;

		if(r.t>=r.nextTime) {
			r.stage();
		}
		
		r.guide();
	}

}
