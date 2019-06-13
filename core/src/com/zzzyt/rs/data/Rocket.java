package com.zzzyt.rs.data;

import java.util.ArrayList;
import java.util.List;

import com.zzzyt.rs.phy.Physics;

public class Rocket {
	public String name;
	public List<Stage> stages;
	
	public double t;
	public double x,y,vx,vy;
	public int stage;
	
	public double nextTime;
	
	double tmpmass;
	
	public void init() {
		tmpmass=0;
		for(int i=1;i<stages.size();i++) {
			tmpmass+=stages.get(i).getM0();
		}
		nextTime=stages.get(0).getTime();
	}
	
	public double getMass() {
		return tmpmass+stages.get(stage).getMass(this);
	}
	
	public double getTr() {
		return stages.get(stage).getTr(this);
	}
	
	public double getTheta() {
		return stages.get(stage).getTheta(this);
	}
	
	public double getDrag() {
		return stages.get(stage).getDrag(this);
	}
	
	public void stage() {
		stage++;
		tmpmass=0;
		for(int i=stage+1;i<stages.size();i++) {
			tmpmass+=stages.get(i).getM0();
		}
		nextTime+=stages.get(stage).getTime();
	}
	
	public Rocket() {
		
	}
	
	public Rocket(String name) {
		this.name = name;
		this.t = 0;
		this.x = 0;
		this.y = Physics.R;
		this.vx = 0;
		this.vy = 0;
		this.stage = 0;
		this.stages=new ArrayList<Stage>();
	}
	
	public Rocket(String name, double t, double x, double y, double vx, double vy, int stage) {
		this.name = name;
		this.t = t;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.stage = stage;
		this.stages=new ArrayList<Stage>();
	}

	public void guide() {
		
	}

	
	public void debug() {
		System.out.printf("t=%.1f x=%.2f y=%.2f vx=%.2f vy=%.2f m=%.0f h=%.1f th=%.2f\n",t,x,y,vx,vy,getMass(),Physics.tri(x,y)-Physics.R,getTheta()/Math.PI);
	}
	
	@Override
	public String toString() {
		return "t=" + t + ", x=" + x + ", y=" + y + ", vx=" + vx + ", vy=" + vy + ", stage=" + stage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + stage;
		result = prime * result + ((stages == null) ? 0 : stages.hashCode());
		long temp;
		temp = Double.doubleToLongBits(t);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tmpmass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(vx);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(vy);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rocket other = (Rocket) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stage != other.stage)
			return false;
		if (stages == null) {
			if (other.stages != null)
				return false;
		} else if (!stages.equals(other.stages))
			return false;
		if (Double.doubleToLongBits(t) != Double.doubleToLongBits(other.t))
			return false;
		if (Double.doubleToLongBits(tmpmass) != Double.doubleToLongBits(other.tmpmass))
			return false;
		if (Double.doubleToLongBits(vx) != Double.doubleToLongBits(other.vx))
			return false;
		if (Double.doubleToLongBits(vy) != Double.doubleToLongBits(other.vy))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
}
