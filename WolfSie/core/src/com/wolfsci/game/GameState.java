package com.wolfsci.game;

public interface GameState {
	
	/** Called when the gamestate should be rendered, This might not be applicable to every state
	 * 
	 */
	public void render();
	
	/**
	 * Called to do things like a physics or collision update, called before a frame
	 */
	
	public void update();
	
	/**
	 * .dispose() is called when the gameState is going to be trashed. Use this to clean up Textures, network connections, etc.
	 */
	public void dispose();
}
