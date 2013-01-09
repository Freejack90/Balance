package com.balance;

import com.badlogic.gdx.ApplicationListener;


import com.balance.levels.IntroScreen;

public class Game implements ApplicationListener {
	
	private AbstractScreen currentLevel;
	public int lol = 99;
	
	

	public void setLevel(AbstractScreen level) {
		currentLevel = level;
		currentLevel.create(this);
	}

	
	public void create() {
		System.out.print("Game");
		setLevel(new IntroScreen());
		//System.out.println(this);
	}

	public void dispose() {
		currentLevel.dispose();
	}

	public void pause() {
		currentLevel.pause();
	}

	public void render() {
		currentLevel.render();
	}

	public void resize(int arg0, int arg1) {
		currentLevel.resize(arg0, arg1);
	}

	public void resume() {
		currentLevel.resume();
	}

}
