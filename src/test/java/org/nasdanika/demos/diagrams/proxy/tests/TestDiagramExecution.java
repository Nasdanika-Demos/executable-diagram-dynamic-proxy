package org.nasdanika.demos.diagrams.proxy.tests;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.PropertySourceProcessorFactory;

public class TestDiagramExecution {

	@Test
	public void testDynamicProxy() throws Exception {
		Function<URI, InputStream> uriHandler = null;		
		Function<String, String> propertySource = Map.of("my-property", "Hello")::get;		
		Document document = Document.load(
				new File("diagram.drawio"),
				uriHandler,
				propertySource);
		
		NopEndpointProcessorConfigFactory<Invocable> processorConfigFactory = new NopEndpointProcessorConfigFactory<Invocable>() {
			
			@Override
			protected boolean isPassThrough(Connection incomingConnection) {
				return false;
			}
			
		};
		
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(processorConfigFactory);		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();		
		Collection<Element> elements = new ArrayList<>();
		Consumer<org.nasdanika.drawio.Element> consumer = org.nasdanika.drawio.Util.withLinkTargets(elements::add, ConnectionBase.SOURCE);
		document.accept(consumer, null);
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(elements, false, progressMonitor);
		
		PropertySourceProcessorFactory<Invocable> processorFactory = new PropertySourceProcessorFactory<Invocable>("processor", document.getURI());
		Map<Element, ProcessorInfo<Invocable>> processors = processorFactory.createProcessors(configs.values(), false, progressMonitor);
		processors.values().stream().map(ProcessorInfo::getProcessor).filter(Objects::nonNull).forEach(System.out::println);
		System.out.println(processors);
		// TODO - dynamic proxy
		
		
	}	
	
}
