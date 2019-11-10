package com.zzzyt.rs.type;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.zzzyt.rs.phy.Phy;
import com.zzzyt.rs.util.StringUtil;

public class Rocket {
	public String name;
	public List<Stage> stages;
	
	public double t;
	public double x;
	public double y;
	public double vx;
	public double vy;
	public double dir,throttle,gimbal;
	public int stage;
	
	public boolean doCheckStage;
	public double nextTime;
	
	private double tmpmass;
	
	public void init() {
		tmpmass=0;
		for(int i=1;i<stages.size();i++) {
			tmpmass+=stages.get(i).getDryMass()+stages.get(i).getFuelMass();
		}
		nextTime=stages.get(0).getTime();
	}
	
	public double getMass() {
		return tmpmass+stages.get(stage).getDryMass()+stages.get(stage).f;
	}
	
	public double getThrust() {
		if(stages.get(stage).f<=0)return 0;
		return stages.get(stage).getThrust()*throttle;
	}
	
	public double getTheta() {
		return dir+gimbal;
	}
	
	public double getDrag() {
		return stages.get(stage).getDrag();
	}
	
	public void stage() {
		Gdx.app.log("Rocket",StringUtil.format("Stage: %d -> %d",stage,stage+1));
		stage++;
		tmpmass=0;
		for(int i=stage+1;i<stages.size();i++) {
			tmpmass+=stages.get(i).getDryMass()+stages.get(i).getFuelMass();
		}
		if(stages.get(stage).getTime()!=-1)nextTime=t+stages.get(stage).getTime();
		else nextTime=-1;
	}
	
	public void checkStage() {
		if(!doCheckStage)return;
		if(stages.get(stage).getTime()==-1)return;
		if(t<nextTime)return;
		stage();
	}
	
	public void guide() {
		
	}
	
	public Rocket() {
		
	}
	
	public Rocket(String name) {
		this.name = name;
		this.t = 0;
		this.x = 0;
		this.y = Phy.R;
		this.vx = 0;
		this.vy = 0;
		this.stage = 0;
		this.dir=Math.PI/2;
		this.throttle=0;
		this.gimbal=0;
		this.doCheckStage=true;
		this.stages=new ArrayList<Stage>();
	}
	
	public Rocket(String name, double t, double x, double y, double vx, double vy, int stage,boolean doCheckStage) {
		this.name = name;
		this.t = t;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.stage = stage;
		this.dir=Math.PI/2;
		this.throttle=0;
		this.gimbal=0;
		this.doCheckStage = doCheckStage;
		this.stages=new ArrayList<Stage>();
	}
}
