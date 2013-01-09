package com.balance;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Application {
	
	public static void main(String args[]){
		System.out.print("Application");
		new LwjglApplication(new Game(), "title", 420, 800, false);		
		//Feax comment
	}

}
