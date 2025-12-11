module org.nasdanika.demos.diagrams.proxy {
		
	requires transitive org.nasdanika.drawio;
	
	exports org.nasdanika.demos.diagrams.proxy;
	exports org.nasdanika.demos.diagrams.dispatch;
	
	opens org.nasdanika.demos.diagrams.proxy;
	opens org.nasdanika.demos.diagrams.dispatch;
	
}
