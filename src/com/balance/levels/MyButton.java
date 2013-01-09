package com.balance.levels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyButton {
	
	private BitmapFont font;
	private int posX;
	private int posY;
	private String text;
	private Sprite sprite;
	
	public MyButton(int posX, int posY, String text, Sprite sprite){
		this.posX = posX;
		this.posY = posY;
		this.text = text;
		this.sprite = sprite;
		this.sprite.setPosition(posX, posY);
		font = new BitmapFont(
		Gdx.files.internal("data/arial.fnt"), 
		Gdx.files.internal("data/arial.png"), false);
		font.setColor(Color.BLACK);
		//font.setScale(0.75f, 0.75f);
	}
	
	public void draw (SpriteBatch batch){
		
		sprite.draw(batch);
		font.draw(batch, text, posX + 20, posY + 30);
		
	}
	
	public boolean hitTest (){
		
		if ((Gdx.input.getX() > posX && Gdx.input.getX() < posX + sprite.getWidth())&&(Gdx.input.getY() > Gdx.graphics.getHeight() - posY - sprite.getHeight() && Gdx.input.getY() < Gdx.graphics.getHeight() - posY )){
			
			return true;
			
		}
		return false;
		
	}


}
