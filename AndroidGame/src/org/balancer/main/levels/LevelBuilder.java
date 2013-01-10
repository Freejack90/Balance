package org.balancer.main.levels;

import java.util.Iterator;

import org.balancer.main.AndroidGame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class LevelBuilder extends AbstractScreen{
	private LevelManager manager;
	private World world;
	
	public void create(AndroidGame game) {
		super.create(game);
		trace("LevelBuilder");
		manager = new LevelManager("data/levels.xml");
		world = new World(new Vector2(0,15), true);
		
		//Creating static objects
		Iterator<ObjectType> statics = manager.getObjects(ObjectType.STATICS);
		while (statics.hasNext()) {
			ObjectType stat = statics.next();
			ModelHelper.create(world, stat.getShape(), stat.getType(), ObjectType.STATICS, stat.getSize(), stat.getAngle(), stat.getPosition());
		}
	}
	
	public void render() {
		setColor(1,0,0);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
