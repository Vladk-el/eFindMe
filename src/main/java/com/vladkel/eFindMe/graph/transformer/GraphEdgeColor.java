package com.vladkel.eFindMe.graph.transformer;

import java.awt.Color;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

import com.vladkel.eFindMe.graph.parsingxml.GraphXML;

public class GraphEdgeColor implements Transformer<String, Paint>{

		public GraphEdgeColor(){
		}
		
		public Paint transform(String node) {
			
			int indiceNode = Integer.parseInt(node);
			
			switch(GraphXML.getInstance().getMatches().get(indiceNode).getTrust().toString())
			{
				case "Trusted":
					return Color.GREEN;
					
				case "Unknown":
					return Color.GRAY;
					
				case "Bad":
					return Color.RED;
					
				default:
					return Color.GREEN;
			}
		}
}