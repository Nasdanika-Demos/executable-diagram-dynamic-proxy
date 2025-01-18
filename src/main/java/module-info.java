module org.nasdanika.demos.diagrams.proxy {
		
	requires transitive org.nasdanika.drawio;
	
	exports org.nasdanika.demos.diagrams.proxy;
	exports org.nasdanika.demos.diagrams.dispatch;
	
	opens org.nasdanika.demos.diagrams.proxy to org.nasdanika.common;
	opens org.nasdanika.demos.diagrams.dispatch to org.nasdanika.gaph;
	
}
