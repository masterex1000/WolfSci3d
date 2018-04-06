package com.wolfsci.engine;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class BlockMap {
	private ArrayList<ModelInstance> blocks;
	private ModelBatch modelBatch;

	private Model wallTile;
	private Model floorTile;
	private Model serverTexture;

	private Material mat;
	private Material floorMat;

	private Texture wall;
	private Texture floor;

	int[] map;
	int mapWidth;

	public BlockMap(int[] map, int width) {
		if (map.length % width != 0 && map.length > 0) { // tests if the map size is valid
			throw new Error("Map Size Invalid");
		}
		this.map = map; // sets the map
		mapWidth = width;
		
		 //Build the map
		
		modelBatch = new ModelBatch();

		floorTile = new Block("PixelWoodFloor.png").getModel(); //load model
		wallTile = new Block("cobblestone_wall.png").getModel(); //load model
		serverTexture = new Block("serverrack.png").getModel();
		
		System.out.println(floorTile);
		
		buildMap();
	}

	public void buildMap() { // builds the map
		blocks = new ArrayList<ModelInstance>();

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				ModelInstance inst = null;
				if(map[y * mapWidth + x] == 0) {
					inst = new ModelInstance(floorTile);
					inst.transform.setToTranslation(x * 5, -5, y * 5);
					inst.transform.rotate(Vector3.Z, 90);
				} else if (map[y * mapWidth + x] == 1) {
					inst = new ModelInstance(wallTile);
					inst.transform.setToTranslation(x * 5, 0, y * 5);
					inst.transform.rotate(Vector3.Z, 90);
				} else if (map[y * mapWidth + x] == 2) {
					inst = new ModelInstance(serverTexture);
					inst.transform.setToTranslation(x * 5, 0, y * 5);
					inst.transform.rotate(Vector3.Z, 90);
				}
				
				blocks.add(inst);
			}
		}
	}

	public void drawMap(Camera cam, Environment env) {
		modelBatch.begin(cam);

		for (int i = 0; i < blocks.size(); i++)
			modelBatch.render(blocks.get(i), env);

		modelBatch.end();
	}
}