package com.vladkel.eFindMe.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.vladkel.eFindMe.graph.parsingxml.GraphXML;
import com.vladkel.eFindMe.graph.transformer.GraphEdgeColor;
import com.vladkel.eFindMe.graph.transformer.GraphNodeColor;
import com.vladkel.eFindMe.search.engine.model.Match;
import com.vladkel.eFindMe.search.engine.model.Url;

import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Graph extends JPanel {

	private Forest<Url,String> graph;
	private VisualizationViewer<Url,String> vv;
    private TreeLayout<Url, String> treeLayout;
		
	public Graph() 
	{
		super(new BorderLayout());
	}
		
	public void initGraph()
	{		
		this.removeAll();
		
		fillGraph();
		
		treeLayout = new TreeLayout<Url,String>(graph);

		vv = new VisualizationViewer(treeLayout);
		vv.setBackground(new Color(255,255,255));
		
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
        vv.setVertexToolTipTransformer(new ToStringLabeller());
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		
		gm.setMode(Mode.TRANSFORMING);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.setGraphLayout(treeLayout);
		vv.setGraphMouse(gm);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.W);
        
		vv.getRenderContext().setEdgeDrawPaintTransformer(new GraphEdgeColor());
		vv.getRenderContext().setArrowDrawPaintTransformer(new GraphEdgeColor());
		vv.getRenderContext().setArrowFillPaintTransformer(new GraphEdgeColor());
		
		vv.getRenderContext().setVertexFillPaintTransformer(new GraphNodeColor(vv.getPickedVertexState()));
		vv.repaint();
        	
        //final ScalingControl scaler = new CrossoverScalingControl();
        //scaler.scale(vv, 1.1f, vv.getCenter());
		
        eventsGraph();   
		add(vv);
	}

	public void eventsGraph()
	{
		vv.addGraphMouseListener(new GraphMouseListener() {
            @Override
            public void graphClicked(Object v, MouseEvent me) {
                if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
                    System.out.println("Double clicked "+ v);
                }
                me.consume();
            }

            @Override
            public void graphPressed(Object v, MouseEvent me) {
            }

            @Override
            public void graphReleased(Object v, MouseEvent me) {
            }
        });
	}
	
	public void fillGraph()
	{
		graph = new DelegateForest<Url, String>();
		
		for(Entry<Integer, Url> value : GraphXML.getInstance().getUrls().entrySet()) {
		    Url url = value.getValue();
		    
		    graph.addVertex(url);
		}
		
		Integer indice = 0 ;
		
		for(Match matche : GraphXML.getInstance().getMatches())
		{
			graph.addEdge(indice.toString(), GraphXML.getInstance().getUrl(Integer.parseInt(matche.getIdSource())), GraphXML.getInstance().getUrl(Integer.parseInt(matche.getIdLink())));
			indice++;
		}
	}

	public static void main(String argv[])
	{
		GraphXML.getInstance().getDataXml(0);
		final Graph graph = new Graph();
		graph.initGraph();

		JFrame f = new JFrame();
		f.getContentPane().add(graph);
		f.setSize(1000,1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}