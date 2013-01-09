package com.balance.levels;

import java.util.ArrayList;
import java.util.Iterator;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.balance.AbstractScreen;
import com.balance.Game;

public class LevelBuilder extends AbstractScreen{
	public int LevelNumber = 5;
	private ArrayList<ObjectTypes> levelmap = new ArrayList<ObjectTypes>();
	protected int clickTimes = 0;
	protected int startId = 0;
	int count_dyn;
	int count_stat;
	int j = 0;
	private World world;
	private float meters = 32;
	private Box2DDebugRenderer render;
	private Matrix4 mtx = new Matrix4();
	protected Texture map = new Texture("images/map.png");
	int setWidth = 32;
	public MyButton resetButton;
	public MyButton firstPrev;
	private Sprite currentSprite; 
	
	public Sprite getSprite(String currentShape){ // Метод возвращающий нужный спрайт, по форме, передаваемой в аргмуент данного метода;
		//if (currentShape != "") return new Sprite(map,0,0,0,0);
		switch(currentShape){
			case "cube_s": return new Sprite(map, 0,0,48,48);
			case "cube_m": return new Sprite(map, 48,0,64,64);
			case "cube_l": return new Sprite(map, 112,0,96,96);
			case "rect_s": return new Sprite(map, 208,0,128,24);
			case "rect_m": return new Sprite(map, 208,24,192,32);
			case "rect_l": return new Sprite(map, 208,56,256,48);
			case "triangle_s": return new Sprite(map, 48,128,64,32);
			case "triangle_m": return new Sprite(map, 336,104,96,48);
			case "triangle_l": return new Sprite(map, 208,104,128,64);
			case "circle_s": return new Sprite(map, 0, 48,48,48);
			case "circle_m": return new Sprite(map, 48, 64,64,64);
			case "circle_l": return new Sprite(map, 112, 96,96,96);
			case "cube": return new Sprite(map, 64,224,32,32);
			case "rect": return new Sprite(map, 32,224,32,32);
			case "triangle": return new Sprite(map,96,224,32,32);
			case "circle": return new Sprite(map,0,224,32,32);
			default: return new Sprite();
		}
	}
	
	private void createBody(float x, float y, boolean isStatic, int angle, int width, int height, String type) {
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(width/meters/2, height/meters/2);
		BodyDef bd = new BodyDef();
		float setAngle = MathUtils.degreesToRadians*(-angle);
		bd.angle = setAngle;
		//bd.linearDamping = 1f;
		bd.position.set(x/meters, y/meters);
		if(isStatic){
			bd.type = BodyType.KinematicBody;
		}else{
			bd.type = BodyType.DynamicBody;
		}
		//Sprite cubetextureregion = new Sprite(map, 0,0,64,64);
		//Sprite cubetextureregion2 = new Sprite(map, 64,32,64,32);
		Body body = world.createBody(bd);
		FixtureDef lolo = new FixtureDef();
		lolo.shape = poly;
		lolo.friction = 0.5f;
		lolo.density = 10;
		body.createFixture(lolo);
		body.setUserData(getSprite(type));

		//body.setLinearVelocity(new Vector2(0,1));


	
	}
		
	private void createCircle(float x, float y, boolean isStatic, int width, String type){
		
		CircleShape poly = new CircleShape();
		poly.setRadius((float)width/meters/2);
		BodyDef bd = new BodyDef();
		bd.position.set(x/meters, y/meters);
		if(isStatic){
			bd.type = BodyType.StaticBody;
		}else{
			bd.type = BodyType.DynamicBody;
		}
		Body body = world.createBody(bd);
		/*switch(width){
			case 48:body.setUserData(getSprite("circle_s"));break;
			case 64:body.setUserData(getSprite("circle_m"));break;
			case 96:body.setUserData(getSprite("circle_l"));break;
			default:body.setUserData(getSprite("circle_m"));break;
		}*/
		FixtureDef lolo = new FixtureDef();
		lolo.shape = poly;
		lolo.friction = 0.5f;
		lolo.density = 10;
		body.createFixture(lolo);
		body.setUserData(getSprite(type));

	}

	private void createTriangle(float x, float y, boolean isStatic, int angle, float coef, String type){
		
		PolygonShape poly = new PolygonShape();
		Vector2[] verticles = new Vector2[3];
	    verticles[0] = new Vector2(2*coef  , -1*coef);
	    verticles[1] = new Vector2(0 , 1*coef);
	    verticles[2] = new Vector2(-2*coef , -1*coef);
		poly.set(verticles); 
		BodyDef bd = new BodyDef();
		float setAngle = MathUtils.degreesToRadians*(-angle);
		bd.angle = setAngle;
		//bd.linearDamping = 1f;
		bd.position.set(x/meters, y/meters);
		if(isStatic){
			bd.type = BodyType.StaticBody;
		}else{
			bd.type = BodyType.DynamicBody;
		}
		Body body = world.createBody(bd);
		
		FixtureDef lolo = new FixtureDef();
		lolo.shape = poly;
		lolo.friction = 0.5f;
		lolo.density = 10;
		body.createFixture(lolo);
		body.setUserData(getSprite(type));

	}
	
	private void paintingTools(){
		if(clickTimes > count_dyn - 1) return;
		//firstPrev = new MyButton(32,Gdx.graphics.getHeight() - 50, " ", getSprite(levelmap.get(clickTimes).type.split("_")[0]));
		//firstPrev.hitTest();
		setWidth = 32;
	
			for (int b = clickTimes; b < count_dyn; b++){
				
				String typePart = levelmap.get(b).type;
				String[] parts = typePart.split("_");
				String firstPart = parts[0];
				Sprite tempSprite = getSprite(firstPart);
				tempSprite.setPosition(setWidth, Gdx.graphics.getHeight() - 50);
				setWidth += 42;
				tempSprite.draw(s);
			}
			
	}
	/**
	 * Преобразует кординату Y(%) в px
	 * @param z
	 * @return
	 */
	private int getY(String z){
		
		return Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/100*Integer.parseInt(z);
		
	}
	
	/**
	 * Преобразует кординату X(%) в px
	 * @param z
	 * @return
	 */
	private int getX(String z){
		
		return Gdx.graphics.getWidth()/100*Integer.parseInt(z);
		
	}
	
	public void create(Game param1) {
		
		super.create(param1);
		System.out.print("LevelBuilder");
		resetButton = new MyButton(100,100, "Reset", new Sprite(new TextureRegion(new Texture("images/play_button.png"), 0, 0, 109, 32)));
		
		XmlReader reader = new XmlReader();
		Element root = reader.parse(Gdx.files.internal("data/levels.xml").readString());
		Element current_level = root.getChild(LevelNumber);
		Element dynamical = current_level.getChild(1);
		Element statical = current_level.getChild(0);
		count_dyn = dynamical.getChildCount();
		count_stat = statical.getChildCount();
		//System.out.println(count_stat);
		//System.out.println(count_dyn);
		world = new World(new Vector2(0, -10), true);
		render = new Box2DDebugRenderer();
		mtx.setToOrtho2D(0, 0, devWidth/meters, devHeight/meters);		
		
		for (int i = 0; i < count_dyn; i++){
			ObjectTypes tempObject = new ObjectTypes();
			Element object = dynamical.getChild(i);
			//tempObject.size = object.getAttribute("size") != null ?  object.getAttribute("size") : "medium";
			tempObject.type = object.getAttribute("type") != null ?  object.getAttribute("type") : "cube";
			tempObject.angle = object.getAttribute("angle") != null ? Integer.parseInt(object.getAttribute("angle")): 0;
			//tempObject.id = Integer.parseInt(object.getAttribute("id"));

			
			tempObject.type = object.getAttribute("type");
			tempObject.angle = Integer.parseInt(object.getAttribute("angle"));
			System.out.println(tempObject.id);
			levelmap.add(tempObject);
			//trace(levelmap.get(levelmap.size() -1).type);
		}
		//trace(levelmap);
		for (int j = 0; j < levelmap.size(); j++) {
			
		}
		for (int a = 0; a < count_stat; a++){
			
			Element object2 = statical.getChild(a);
			
			int posx = getX(object2.getAttribute("x"));
			int posy = getY(object2.getAttribute("y"));
			int angle = Integer.parseInt(object2.getAttribute("angle"));
			switch(object2.getAttribute("type")){
				case "cube_s": createBody(posx, posy, true, angle, 48,48, object2.getAttribute("type")); break;
				case "cube_m": createBody(posx, posy, true, angle, 64,64, object2.getAttribute("type")); break;
				case "cube_l": createBody(posx, posy, true, angle, 96,96, object2.getAttribute("type")); break;
				case "triangle_s": createTriangle(posx, posy, true, angle, 0.5f, object2.getAttribute("type")); break;
				case "triangle_m": createTriangle(posx, posy, true, angle, 0.75f, object2.getAttribute("type")); break;
				case "triangle_l": createTriangle(posx, posy, true, angle, 1, object2.getAttribute("type")); break;
				case "circle_s": createCircle(posx, posy, true, 48, object2.getAttribute("type")); break;
				case "circle_m": createCircle(posx, posy, true, 64, object2.getAttribute("type")); break;
				case "circle_l": createCircle(posx, posy, true, 96, object2.getAttribute("type")); break;
				case "rect_s": createBody(posx, posy, true, angle, 128, 24, object2.getAttribute("type")); break;
				case "rect_m": createBody(posx, posy, true, angle, 192, 32, object2.getAttribute("type")); break;
				case "rect_l": createBody(posx, posy, false, angle, 256, 48, object2.getAttribute("type")); break;
			}
			
		} 
		

	   
	}
	
	public void render() {
		super.render();
		setColor(1,1,1);
		world.step(Gdx.graphics.getDeltaTime(), 6, 6);
		render.render(world, mtx);
		Iterator<Body> bi = world.getBodies();
		
		s.begin();
		
		paintingTools();
		//resetButton.draw(s);		
		
		while (bi.hasNext()){
		    Body b = bi.next();
		    Sprite e = (Sprite)b.getUserData();
		    // Get the bodies user data - in this example, our user 
		    // data is an instance of the Entity class
		   //Sprite e = (Sprite)b.getUserData();
		   //System.out.println(e);
		   
		   if(e != null){
		    	
		        // Update the entities/sprites position and angle
		        e.setPosition(b.getPosition().x*meters-(float)e.getWidth()/2, b.getPosition().y*meters-(float)e.getHeight()/2);
		        // We need to convert our angle from radians to degrees
		        e.setRotation(MathUtils.radiansToDegrees * b.getAngle());
		        e.draw(s);
		   }    

		    
		}
		if(currentSprite != null) currentSprite.draw(s);
		s.end();
		/*if(j == 0){
			createBody(getX("50"), getY("50"), true, levelmap.get(0).angle, 48,48, levelmap.get(0).type);
			j--;
			System.out.println(j);
			if (Gdx.input.isTouched()) {
				
				
				
			}
		}*/
		if (Gdx.input.justTouched()) {
			
			if(clickTimes < count_dyn ){
				
				currentSprite = getSprite(levelmap.get(clickTimes).type);

				//System.out.println(levelmap.get(clickTimes));
				clickTimes++;
			}
			
		}
		if (Gdx.input.isTouched() && clickTimes < count_dyn && currentSprite != null){
			System.out.println(currentSprite);
			
			currentSprite.setPosition(mouseX - currentSprite.getWidth()/2, mouseY - currentSprite.getHeight()/2);
			
		}else if(currentSprite != null && clickTimes <= count_dyn){

			switch(levelmap.get(clickTimes - 1).type){
			
			case "cube_s": createBody(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 48,48, levelmap.get(clickTimes - 1).type); break;
			case "cube_m": createBody(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 64,64, levelmap.get(clickTimes - 1).type); break;
			case "cube_l": createBody(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 96,96, levelmap.get(clickTimes - 1).type); break;
			case "triangle_s": createTriangle(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 0.5f, levelmap.get(clickTimes - 1).type); break;
			case "triangle_m": createTriangle(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 0.75f, levelmap.get(clickTimes - 1).type); break;
			case "triangle_l": createTriangle(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 1, levelmap.get(clickTimes - 1).type); break;
			case "circle_s": createCircle(mouseX, mouseY, false, 48, levelmap.get(clickTimes - 1).type); break;
			case "circle_m": createCircle(mouseX, mouseY, false, 64, levelmap.get(clickTimes - 1).type); break;
			case "circle_l": createCircle(mouseX, mouseY, false, 96, levelmap.get(clickTimes - 1).type); break;
			case "rect_s": createBody(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 128, 24, levelmap.get(clickTimes - 1).type); break;
			case "rect_m": createBody(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 192, 32, levelmap.get(clickTimes - 1).type); break;
			case "rect_l": createBody(mouseX, mouseY, false, levelmap.get(clickTimes - 1).angle, 256, 48, levelmap.get(clickTimes - 1).type); break;
		
			}
			currentSprite = null;
				
		}
		
		
	}
	
	public void dispose() {
		
	}

	public void pause() {
		
	}

	public void resize(int arg0, int arg1) {
		
	}

	public void resume() {
		
	}

}
