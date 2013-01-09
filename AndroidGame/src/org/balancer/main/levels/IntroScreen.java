package org.balancer.main.levels;

import org.balancer.main.AndroidGame;
import org.balancer.main.ui.Button;

import com.badlogic.gdx.Gdx;

public class IntroScreen extends AbstractScreen {
	private Button playbtn;
	
	public void create(AndroidGame game) {
		super.create(game);
		trace("Introscreen");
		playbtn = new Button((int) Math.floor(screenWidth*0.5), (int) Math.floor(screenHeight*0.5), "Play");
	}
	
	public void render() {
		setColor();
		batch.begin();
		playbtn.draw(batch);
		batch.end();
		
		if (Gdx.input.justTouched()) {
			if (playbtn.hitTest()) {
				this.curGame.setLevel(new LevelBuilder());
			}
		}
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
