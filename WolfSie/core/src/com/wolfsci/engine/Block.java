package com.wolfsci.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Block {
	Texture tex;
	Material mat;
	Model model;
	
	public Block(String texturePath) {
		tex = new Texture(texturePath);
		mat = new Material(TextureAttribute.createDiffuse(tex));
		
		ModelBuilder modelBuilder = new ModelBuilder();
		
		model = modelBuilder.createBox(5f, 5f, 5f, mat,
				Usage.Position | Usage.Normal | Usage.TextureCoordinates);
	}
	
	public Model getModel() {
		return model;
	}
}