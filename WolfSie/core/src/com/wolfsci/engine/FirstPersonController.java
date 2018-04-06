package com.wolfsci.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntIntMap;

public class FirstPersonController extends InputAdapter {
	private final Camera camera;
	private final IntIntMap keys = new IntIntMap();
	private int STRAFE_LEFT = Keys.A;
	private int STRAFE_RIGHT = Keys.D;
	private int FORWARD = Keys.W;
	private int BACKWARD = Keys.S;
	private int UP = Keys.Q;
	private int DOWN = Keys.E;
	private int VIEW_LEFT = Keys.DPAD_LEFT;
	private int VIEW_RIGHT = Keys.DPAD_RIGHT;
	private float velocity = 5;
	private float turnSpeed = 5;
	private final Vector3 tmp = new Vector3();

	public FirstPersonController (Camera camera) {
		this.camera = camera;
	}

	@Override
	public boolean keyDown (int keycode) {
		keys.put(keycode, keycode);
		return true;
	}

	@Override
	public boolean keyUp (int keycode) {
		keys.remove(keycode, 0);
		return true;
	}

	/** Sets the velocity in units per second for moving forward, backward and strafing left/right.
	 * @param velocity the velocity in units per second */
	public void setVelocity (float velocity) {
		this.velocity = velocity;
	}
	
	/** Sets the speed in units per second for turning
	 * @param turn speed
	 */
	public void setTurnSpeed(float speed) {
		this.turnSpeed = speed;
	}

	//@Override
//	public boolean touchDragged (int screenX, int screenY, int pointer) {
//		float deltaX = -Gdx.input.getDeltaX() * degreesPerPixel;
//		float deltaY = -Gdx.input.getDeltaY() * degreesPerPixel;
//		camera.direction.rotate(camera.up, deltaX);
//		tmp.set(camera.direction).crs(camera.up).nor();
//		camera.direction.rotate(tmp, deltaY);
//// camera.up.rotate(tmp, deltaY);
//		return true;
//	}

	public void update() {
		update(Gdx.graphics.getDeltaTime());
	}

	public void update (float deltaTime) {
		if (keys.containsKey(FORWARD)) {
			tmp.set(camera.direction).nor().scl(deltaTime * velocity);
			camera.position.add(tmp);
		}
		if (keys.containsKey(BACKWARD)) {
			tmp.set(camera.direction).nor().scl(-deltaTime * velocity);
			camera.position.add(tmp);
		}
		if (keys.containsKey(STRAFE_LEFT)) {
			tmp.set(camera.direction).crs(camera.up).nor().scl(-deltaTime * velocity);
			camera.position.add(tmp);
		}
		if (keys.containsKey(STRAFE_RIGHT)) {
			tmp.set(camera.direction).crs(camera.up).nor().scl(deltaTime * velocity);
			camera.position.add(tmp);
		}
		if (keys.containsKey(UP)) {
			tmp.set(camera.up).nor().scl(deltaTime * velocity);
			camera.position.add(tmp);
		}
		if (keys.containsKey(DOWN)) {
			tmp.set(camera.up).nor().scl(-deltaTime * velocity);
			camera.position.add(tmp);
		}
		if(keys.containsKey(VIEW_LEFT)) {
			camera.direction.rotate(camera.up, deltaTime * turnSpeed);
		}
		if(keys.containsKey(VIEW_RIGHT)) {
			camera.direction.rotate(camera.up, -deltaTime * turnSpeed);
		}
		camera.update(true);
	}
}