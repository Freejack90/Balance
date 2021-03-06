package org.balancer.main.levels;

import java.util.ArrayList;
import java.util.Iterator;

import org.balancer.main.AndroidGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


public class LevelBuilder extends AbstractScreen{
	private LevelManager manager;
	private World world;
	//private Box2DDebugRenderer render;
	private Matrix4 mtx = new Matrix4();
	private int clickTimes = 0;
	private Sprite currentSprite;
	public void create(AndroidGame game) {
		super.create(game);
		trace("LevelBuilder");
		manager = new LevelManager("data/levels.xml");
		world = new World(new Vector2(0,-15), true);
		//render = new Box2DDebugRenderer();
		mtx.setToOrtho2D(0, 0, screenWidth/ModelHelper.meters, screenHeight/ModelHelper.meters);
		//Creating static objects
		Iterator<ObjectType> statics = manager.getObjects(ObjectType.STATICS).iterator();
		while (statics.hasNext()) {
			ObjectType stat = statics.next();
			ModelHelper.create(world, ObjectType.STATICS, stat);
		}
	}
	
	public void render() {
		super.render();
		setColor(1,1,1);
		//render.render(world, mtx);
		batch.begin();		
			drawBar();
			drawBodies();	
			if(currentSprite != null) 
				currentSprite.draw(batch);		
		batch.end();
	}
	
	public void update() {
		world.step(Gdx.graphics.getDeltaTime(), 6, 6);
		onTouchAndMove();
	}
	
	private void onTouchAndMove() {
		if(clickTimes < manager.getCount(ObjectType.DYNAMICS)){
			if(Gdx.input.justTouched()){					
				currentSprite = ModelHelper.getSprite(
									manager.getObjects(ObjectType.DYNAMICS)
									.get(clickTimes)
									.getType()
				);
				changePosition();				
			}
			if(Gdx.input.isTouched() && currentSprite != null){				
				changePosition();				
			}else if(currentSprite != null){
				ObjectType newBody = manager.getObjects(ObjectType.DYNAMICS)
									 .get(clickTimes);
				newBody.setPosition(mouseX, mouseY);
				ModelHelper.create(world, ObjectType.DYNAMICS, newBody);
				currentSprite = null;
				clickTimes++;
			}
		}else if(currentSprite != null) currentSprite = null;		
	}

	private void changePosition(){		
		currentSprite.setPosition(
				mouseX-currentSprite.getWidth()/2, 
				mouseY-currentSprite.getHeight()/2
		);
	}
	
	private void drawBar(){
		if(clickTimes > manager.getCount(ObjectType.DYNAMICS) - 1) return;
		
		ArrayList<ObjectType> levelmap = manager.getObjects(ObjectType.DYNAMICS);
		int leftMargin = 32;
		int topMargin = 50;
		int offset = 42;
	
		for (int index = clickTimes; index < manager.getCount(ObjectType.DYNAMICS); index++){				
			String typePart = levelmap.get(index).getType();
			String[] parts = typePart.split("_");
			String firstPart = parts[0];
			Sprite tempSprite = ModelHelper.getSprite(firstPart);
			tempSprite.setPosition(leftMargin, Gdx.graphics.getHeight() - topMargin);
			leftMargin += offset;
			tempSprite.draw(batch);
		}			
	}
	
	public void drawBodies() {
		Iterator<Body> bodies = world.getBodies();		
		while(bodies.hasNext()){			
			Body curBody = bodies.next();
			Sprite bodySprite = (Sprite)curBody.getUserData();
			bodySprite.setPosition(curBody.getPosition().x*ModelHelper.meters-bodySprite.getWidth()/2, curBody.getPosition().y*ModelHelper.meters-bodySprite.getHeight()/2);
			bodySprite.setRotation((float)Math.toDegrees(curBody.getAngle()));
			bodySprite.draw(batch);			
		}
	}
	
	public void dispose() {}

	public void pause() {}

	public void resize(int arg0, int arg1) {}

	public void resume() {}
}
