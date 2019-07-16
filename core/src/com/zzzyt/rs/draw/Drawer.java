package com.zzzyt.rs.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.zzzyt.rs.RocketSimulator;
import com.zzzyt.rs.phy.Phy;
import com.zzzyt.rs.type.Rocket;

import java.util.Locale;

public class Drawer {
    RocketSimulator rs;
    Rocket r;

    public ShapeRenderer shape;
    public SpriteBatch batch;
    public BitmapFont font;
    public Matrix4 defaultMat;
    
    public ButtonDrawer buttons;
    
    public Sprite rocket;
    public Sprite rocketi;
    public Sprite exhaust;

    private float len=100,zoom=1;

    public void init(){
        font=new BitmapFont(Gdx.files.internal("font/debug.fnt"),Gdx.files.internal("font/debug.png"),false);
        font.setColor(Color.WHITE);

        shape=new ShapeRenderer();
        defaultMat=shape.getProjectionMatrix().cpy();
        batch = new SpriteBatch();

        rocket = new Sprite(new Texture("rocket.png"));
        rocket.setRegion(0, 0, 23, 45);
        rocket.setSize(23, 45);
        rocket.setOrigin(rocket.getWidth()/2,0);

        rocketi = new Sprite(new Texture("rocketi.png"));
        rocketi.setRegion(0, 0, 32, 32);
        rocketi.setOriginCenter();

        exhaust = new Sprite(new Texture("exhaust.png"));
        exhaust.setRegion(0,0,11,32);
        exhaust.setOrigin(exhaust.getWidth()/2, exhaust.getHeight());
        
        buttons=new ButtonDrawer();
    }

    public void dispose(){
        batch.dispose();
        shape.dispose();
        rocket.getTexture().dispose();
        rocketi.getTexture().dispose();
        exhaust.getTexture().dispose();
    }

    public void drawTerrian(){
        shape.setProjectionMatrix(rs.cam.combined);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(new Color(0,0,0.3f,1));
        shape.circle(0, 0, (float)(Phy.R+Phy.up));
        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(new Color(0.1f,0.2f,0.6f,1));
        shape.circle(0, 0, (float)(Phy.R+Phy.up/2));
        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(new Color(0,0.5f,0,1));
        shape.circle(0, 0, (float)Phy.R);
        shape.end();
    }

    public void drawRocket(){
        batch.setProjectionMatrix(rs.cam.combined);
        batch.begin();
        if(rs.cam.zoom<=10) {
            rocket.setPosition((float)r.x-rocket.getWidth()/2, (float)r.y);
            rocket.setRotation((float)(r.dir/(2*Math.PI)*360)-90);
            rocket.draw(batch);
        }
        else {
            rocketi.setPosition((float)r.x-rocketi.getWidth()/2, (float)r.y-rocketi.getHeight()/2);
            rocketi.setRotation((float)(r.dir/(2*Math.PI)*360)-90);
            rocketi.setScale(rs.cam.zoom/2);
            rocketi.draw(batch);
        }

        if(r.getThrust()>0) {
            exhaust.setPosition((float)r.x-exhaust.getWidth()/2, (float)r.y-exhaust.getHeight());
            exhaust.setRotation((float)(r.getTheta()/(2*Math.PI)*360)-90);
            exhaust.setScale((float)(Math.cbrt(r.getThrust())/200));
            exhaust.draw(batch);
        }
        batch.end();
    }

    public void drawOverlay(){
        zoom=rs.cam.zoom;
        if(zoom<0.2) {
            len=10;
        }
        else if(zoom<3) {
            len=100;
        }
        else if(zoom<30) {
            len=1E3f;
        }
        else if(zoom<300) {
            len=1E4f;
        }
        else if(zoom<3E3f) {
            len=1E5f;
        }
        else if(zoom<3E4f){
            len=1E6f;
        }
        else if(zoom<3E5f) {
            len=1E7f;
        }
        else {
            len=1E8f;
        }

        shape.setProjectionMatrix(defaultMat);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(new Color(1,1,1,1));
        shape.rect(10, RocketSimulator.rs.h-90, len/zoom, 2);
        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(new Color(1,1,1,1));
        shape.rect(10, RocketSimulator.rs.h-90, 2, 5);
        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(new Color(1,1,1,1));
        shape.rect(10+len/zoom-2, RocketSimulator.rs.h-90, 2, 5);
        shape.end();

        batch.setProjectionMatrix(defaultMat);
        batch.begin();
        font.setColor(1,1,1,1);
        font.draw(batch, String.format(Locale.getDefault(),"Version:%s Rocket Name: %s","1.1.2",r.name),10,rs.h-10);
        font.draw(batch, String.format(Locale.getDefault(),"t=%.1f x=%.2f y=%.2f vx=%.2f vy=%.2f m=%.0f", r.t,r.x,r.y,r.vx,r.vy,r.getMass()), 10,rs.h-30);
        font.draw(batch,String.format(Locale.getDefault(),"sp=%.2f zm=%.2f tr=%.2f f=%.2f h=%.1f drag=%.2f th=%.2f",rs.sim.speed,rs.cam.zoom,r.throttle,r.stages.get(r.stage).f,Phy.tri(r.x,r.y)-Phy.R,Phy.tri(r.x,r.y)>=Phy.R+Phy.up?0:r.getDrag()*(Phy.R+Phy.up-Phy.tri(r.x,r.y))/Phy.up,r.getTheta()/Math.PI),10,rs.h-50);
        if(len>=1000) {
            font.draw(batch,String.format(Locale.getDefault(),"%.0fkm", len/1000),20,RocketSimulator.rs.h-70);
        }
        else {
            font.draw(batch,String.format(Locale.getDefault(),"%.0fm", len),20,RocketSimulator.rs.h-70);
        }
        batch.end();
    }

    public void drawGUI(){
    	batch.setProjectionMatrix(defaultMat);
        buttons.draw(batch);
    }

    public Drawer(){
        this.rs=RocketSimulator.rs;
        this.r=rs.r;
    }
}
