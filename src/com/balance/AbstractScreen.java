package com.balance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class AbstractScreen {
	protected Game curGame;
	public GL10 gl;
	protected SpriteBatch s;
	protected int mouseY;
	protected int mouseX;
	protected int devHeight;
	protected int devWidth;
	public void trace(Object mixed) {
		System.out.println(mixed);
	}
	public void setColor(float red, float green, float blue){

		gl.glClearColor(red, green, blue, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
	}
	public void setColor(){

		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
	}
	
	public void create(Game gameObject) {
		this.curGame = gameObject;
		gl = Gdx.graphics.getGL10 ();
		s = new SpriteBatch();
		devHeight = Gdx.graphics.getHeight();
		devWidth = Gdx.graphics.getWidth();
	}
	
	abstract public void dispose();

	abstract public void pause();

	public void render(){

			mouseY = Gdx.graphics.getHeight()-Gdx.input.getY();
			mouseX = Gdx.input.getX();

	}

	abstract public void resize(int arg0, int arg1);

	abstract public void resume();

	
}
