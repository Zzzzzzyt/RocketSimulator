package com.zzzyt.rs.draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zzzyt.rs.RocketSimulator;

public class ButtonDrawer {
	public Sprite dirl,dirr,giml,gimr,thu,thd,thmax,thmin,spu,spd,focus;
	public Sprite pdirl,pdirr,pgiml,pgimr,pthu,pthd,pthmax,pthmin,pspu,pspd,pfocus;
	public boolean Pdirl,Pdirr,Pgiml,Pgimr,Pthu,Pthd,Pthmax,Pthmin,Pspu,Pspd,Pfocus;
	public Texture texture;
	private float h,w;
	
	public void init() {
		texture=new Texture("button/button.png");
		
		dirl=new Sprite(texture,100,100,100,100);
		dirr=new Sprite(texture,0,100,100,100);
		giml=new Sprite(texture,100,100,100,100);
		gimr=new Sprite(texture,0,100,100,100);
		thu=new Sprite(texture,200,100,100,100);
		thd=new Sprite(texture,300,100,100,100);		
		spu=new Sprite(texture,200,100,100,100);
		spd=new Sprite(texture,300,100,100,100);		
		thmax=new Sprite(texture,0,300,100,100);
		thmin=new Sprite(texture,100,300,100,100);
		focus=new Sprite(texture,0,0,150,100);

		pdirl=new Sprite(texture,100,200,100,100);
		pdirr=new Sprite(texture,0,200,100,100);
		pgiml=new Sprite(texture,100,200,100,100);
		pgimr=new Sprite(texture,0,200,100,100);
		pthu=new Sprite(texture,200,200,100,100);
		pthd=new Sprite(texture,300,200,100,100);
		pspu=new Sprite(texture,200,200,100,100);
		pspd=new Sprite(texture,300,200,100,100);
		pthmax=new Sprite(texture,200,300,100,100);
		pthmin=new Sprite(texture,300,300,100,100);
		pfocus=new Sprite(texture,150,0,150,100);

		dirr.setOriginCenter();
		dirl.setOriginCenter();
		gimr.setOriginCenter();
		giml.setOriginCenter();
		thu.setOriginCenter();
		thd.setOriginCenter();
		thmax.setOriginCenter();
		thmin.setOriginCenter();
		spu.setOriginCenter();
		spd.setOriginCenter();
		focus.setOriginCenter();

		pdirr.setOriginCenter();
		pdirl.setOriginCenter();
		pgimr.setOriginCenter();
		pgiml.setOriginCenter();
		pthu.setOriginCenter();
		pthd.setOriginCenter();
		pthmax.setOriginCenter();
		pthmin.setOriginCenter();
		pspu.setOriginCenter();
		pspd.setOriginCenter();
		pfocus.setOriginCenter();

		
		dirr.setAlpha(0.5f);
		dirl.setAlpha(0.5f);
		gimr.setAlpha(0.5f);
		giml.setAlpha(0.5f);
		thu.setAlpha(0.5f);
		thd.setAlpha(0.5f);
		thmax.setAlpha(0.5f);
		thmin.setAlpha(0.5f);
		spu.setAlpha(0.5f);
		spd.setAlpha(0.5f);
		focus.setAlpha(0.5f);

		pdirr.setAlpha(0.5f);
		pdirl.setAlpha(0.5f);
		pgimr.setAlpha(0.5f);
		pgiml.setAlpha(0.5f);
		pthu.setAlpha(0.5f);
		pthd.setAlpha(0.5f);
		pthmax.setAlpha(0.5f);
		pthmin.setAlpha(0.5f);
		pspu.setAlpha(0.5f);
		pspd.setAlpha(0.5f);
		pfocus.setAlpha(0.5f);
	}
	
	public void setPos() {
		h=RocketSimulator.rs.h;
		w=RocketSimulator.rs.w;
		float scale=0.5f;
		if(Math.min(h,w*9/16)>700)scale=1f;
		
		dirr.setScale(scale);
		dirl.setScale(scale);
		gimr.setScale(scale);
		giml.setScale(scale);
		thu.setScale(scale);
		thd.setScale(scale);
		thmax.setScale(scale);
		thmin.setScale(scale);
		spu.setScale(scale);
		spd.setScale(scale);
		focus.setScale(scale);

		pdirr.setScale(scale);
		pdirl.setScale(scale);
		pgimr.setScale(scale);
		pgiml.setScale(scale);
		pthu.setScale(scale);
		pthd.setScale(scale);
		pthmax.setScale(scale);
		pthmin.setScale(scale);
		pspu.setScale(scale);
		pspd.setScale(scale);
		pfocus.setScale(scale);
		
		dirl.setOriginBasedPosition(w-200*scale, 80*scale);
		dirr.setOriginBasedPosition(w-80*scale, 80*scale);
		giml.setOriginBasedPosition(w-200*scale, 200*scale);
		gimr.setOriginBasedPosition(w-80*scale, 200*scale);
		thu.setOriginBasedPosition(80*scale, 200*scale);
		thd.setOriginBasedPosition(80*scale, 80*scale);
		thmax.setOriginBasedPosition(200*scale, 200*scale);
		thmin.setOriginBasedPosition(200*scale, 80*scale);
		spu.setOriginBasedPosition(w-80*scale, h-80*scale);
		spd.setOriginBasedPosition(w-80*scale, h-200*scale);
		focus.setOriginBasedPosition(w-240*scale, h-80*scale);

		pdirl.setOriginBasedPosition(w-200*scale, 80*scale);
		pdirr.setOriginBasedPosition(w-80*scale, 80*scale);
		pgiml.setOriginBasedPosition(w-200*scale, 200*scale);
		pgimr.setOriginBasedPosition(w-80*scale, 200*scale);
		pthu.setOriginBasedPosition(80*scale, 200*scale);
		pthd.setOriginBasedPosition(80*scale, 80*scale);
		pthmax.setOriginBasedPosition(200*scale, 200*scale);
		pthmin.setOriginBasedPosition(200*scale, 80*scale);
		pspu.setOriginBasedPosition(w-80*scale, h-80*scale);
		pspd.setOriginBasedPosition(w-80*scale, h-200*scale);
		pfocus.setOriginBasedPosition(w-240*scale, h-80*scale);
	}
	
	public void draw(SpriteBatch batch) {
		batch.begin();
		if(Pdirl)pdirl.draw(batch);
		else dirl.draw(batch);
		if(Pdirr)pdirr.draw(batch);
		else dirr.draw(batch);
		if(Pgiml)pgiml.draw(batch);
		else giml.draw(batch);
		if(Pgimr)pgimr.draw(batch);
		else gimr.draw(batch);
		if(Pthu)pthu.draw(batch);
		else thu.draw(batch);
		if(Pthd)pthd.draw(batch);
		else thd.draw(batch);
		if(Pthmax)pthmax.draw(batch);
		else thmax.draw(batch);
		if(Pthmin)pthmin.draw(batch);
		else thmin.draw(batch);
		if(Pspu)pspu.draw(batch);
		else spu.draw(batch);
		if(Pspd)pspd.draw(batch);
		else spd.draw(batch);
		if(Pfocus)pfocus.draw(batch);
		else focus.draw(batch);
		batch.end();
	}
	
	public ButtonDrawer() {
		this.init();
		this.setPos();
		this.Pfocus=true;
	}
}
