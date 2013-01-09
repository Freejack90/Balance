package com.balance.levels;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.balance.AbstractScreen;
import com.balance.Game;

public class IntroScreen extends AbstractScreen {
	
	private TextureRegion mainBtn;
	BitmapFont font;
	public void create(Game gameObject) {
		super.create(gameObject);
		System.out.print("Intro");
		mainBtn = new TextureRegion(new Texture("images/play_button.png"), 0, 0, 109, 32);
		/*font = new BitmapFont(
		Gdx.files.internal("data/Jura-Medium.fnt"), 
		Gdx.files.internal("data/Jura-Medium.png"), false);
		font.setColor(Color.WHITE);*/

	}
	
	public void render() {
		s.begin();
		s.draw(mainBtn, 160, 384);
		s.end();
		//font.draw(s, "ololoev", 200, 200);
		if (Gdx.input.justTouched()) {
			if ((Gdx.input.getX() > 160 && Gdx.input.getX() < 269)&&(Gdx.input.getY() > 384 && Gdx.input.getY() < 416)){
				//one1.setRegionY(64);
				curGame.setLevel(new LevelBuilder());
			}
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
