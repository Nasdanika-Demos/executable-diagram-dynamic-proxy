package org.nasdanika.demos.diagrams.proxy;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.nasdanika.capability.CapabilityFactory.Loader;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.IncomingHandler;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;


public class SystemProcessor {
	
	private String amount;
	
	@ProcessorElement
	public void setElement(Node element) {
		this.amount = element.getProperty("amount");
	}
	
	public SystemProcessor(
			Loader loader,
			ProgressMonitor loaderProgressMonitor,
			Object data,
			ProcessorConfig config,
			BiConsumer<Element, BiConsumer<ProcessorInfo<Invocable>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			ProgressMonitor wiringProgressMonitor) {
		
		System.out.println("I got constructed " + this);
	}
	
	@IncomingHandler
	public Supplier<String> getAmountSupplier() {
		return () -> amount;
	}

}
