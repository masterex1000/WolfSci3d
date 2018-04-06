package com.wolfsci.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.wolfsci.engine.*;

/**
 * The State that is used for the entire game, For example, drawing the world, player, etc.
 * @author Collin
 *
 */
public class WolfSciState implements GameState {
	public Environment environment;
	public PerspectiveCamera cam;
	public FirstPersonController camController;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;
	
	private BlockMap map;
	
	
	public WolfSciState() {
		map = new BlockMap(new int[] {
				1,1,2,2,2,2,2,2,1,1,
				1,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,0,1,0,2,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,1,1,1,1,1,1,1,1,1
				}, 10);
		
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		cam.position.set(0f, 0f, 0f);
		
		cam.lookAt(0,0,0);
		cam.near = 1f;
		cam.far = 300f;
		cam.fieldOfView = 90;
		cam.update();
		
		camController = new FirstPersonController(cam);
        camController.setTurnSpeed(150);
        camController.setVelocity(15);
        Gdx.input.setInputProcessor(camController);
	}
	
	public void render() {
		map.drawMap(cam, environment);
	}
	
	public void update() {
		camController.update();
	}
	
	public void dispose() {
		
	}
}
