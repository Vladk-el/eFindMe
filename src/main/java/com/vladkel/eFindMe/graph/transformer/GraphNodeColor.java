package com.vladkel.eFindMe.graph.transformer;

import java.awt.Color;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

import com.vladkel.eFindMe.graph.parsingxml.GraphXML;
import com.vladkel.eFindMe.search.engine.model.Matches;
import com.vladkel.eFindMe.search.engine.model.Url;

import edu.uci.ics.jung.visualization.picking.PickedInfo;

public class GraphNodeColor implements Transformer<Url, Paint>{

    protected PickedInfo<Url> picked;
	protected String myNode;
	
	public GraphNodeColor(PickedInfo<Url> pi){
		this.picked = pi;
	}
	
	@Override
	public Paint transform(Url node) {
		
		Matches matches = GraphXML.getInstance().getMatch(Integer.parseInt(node.getId()));
				
		if(matches != null)
		{
			switch(matches.getTrust().toString())
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
		
		return Color.GREEN;
	}
}