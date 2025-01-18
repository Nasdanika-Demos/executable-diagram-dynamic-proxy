package org.nasdanika.demos.diagrams.dispatch;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Handler;

public class DispatchTarget {
	
	@Handler
	public void onConnection(Connection connection) {
		System.out.println("Got a connection: " + connection);
	}
	
	@Handler
	public void onNode(Node node) {
		System.out.println("Got a node: " + node);
	}
	
	@Handler("label == 'Person'")
	public void onPersonNode(Node node) {
		System.out.println("Got a person node: " + node);
	}

}
