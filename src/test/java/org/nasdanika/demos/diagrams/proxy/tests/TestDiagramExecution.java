package org.nasdanika.demos.diagrams.proxy.tests;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.processor.DocumentInvocableFactory;

public class TestDiagramExecution {

	@Test
	public void testDynamicProxy() throws Exception {
		Function<URI, InputStream> uriHandler = null;				
		Function<String, String> propertySource = Map.of("my-property", "Hello")::get;		
		Document document = Document.load(
				new File("diagram.drawio"),
				uriHandler,
				propertySource);

		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();				
		
		DocumentInvocableFactory documentInvocableFactory = new DocumentInvocableFactory(document, "processor");
		java.util.function.Function<Object,Object> proxy = documentInvocableFactory.createProxy(
				"bind",
				null,
				false,
				progressMonitor,
				java.util.function.Function.class);
		
		System.out.println(proxy.apply(33));
	}	
	
}
