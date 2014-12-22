package com.vladkel.eFindMe.graph.transformer;

import java.awt.Rectangle;
import java.awt.Shape;

import com.vladkel.eFindMe.search.engine.model.Url;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;

public class GraphNodeShape extends AbstractVertexShapeTransformer<Url> {
    public GraphNodeShape(){
	}
	
	@Override
	public Shape transform(Url arg0) {
		
		return new Rectangle(10,10);
	}
}