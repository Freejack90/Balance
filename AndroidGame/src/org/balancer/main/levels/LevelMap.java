package org.balancer.main.levels;

import java.util.ArrayList;
import java.util.Iterator;

public class LevelMap {
	private ArrayList<ObjectType> statics = new ArrayList<ObjectType>();
	private ArrayList<ObjectType> dynamics = new ArrayList<ObjectType>();
	
	LevelMap() {
		
	}
	
	public void add(ObjectType object, int type) {
		if (type == ObjectType.DYNAMICS) 
			dynamics.add(object);
		else 
			statics.add(object);
	}
	
	public Iterator<ObjectType> get(int type) {
		if (type == ObjectType.DYNAMICS) 
			return dynamics.iterator();
		else
			return statics.iterator();
			
	}
}
