package com.balance.levels;

import com.badlogic.gdx.Gdx;
import com.balance.AbstractScreen;
import com.balance.Game;

public class Level2 extends AbstractScreen {

	
	public void create(Game gameObject){
		super.create(gameObject);
		System.out.print("Level2");	
	}
	
	public void dispose() {

		
	}


	public void pause() {

		
	}


	public void render() {
		
		setColor(0,0,1);
		if(Gdx.input.justTouched()){
			System.out.print(curGame.lol);

		}
		
	}


	public void resize(int arg0, int arg1) {
		System.out.print(arg0);	
		System.out.print(arg1);	
		
	}


	public void resume() {

		
	}
	
	

}
