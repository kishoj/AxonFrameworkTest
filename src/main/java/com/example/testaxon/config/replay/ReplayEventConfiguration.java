package com.example.testaxon.config.replay;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.config.ProcessingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Configuration
public class ReplayEventConfiguration {

	private static final String BASE_PACKAGE = "com.example.testaxon";

	@Autowired
	private EventHandlingConfiguration eventHandlingConfiguration;

	@PostConstruct
	public void replayEvents() throws ClassNotFoundException {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Replayable.class));
		for (BeanDefinition bd : scanner.findCandidateComponents(BASE_PACKAGE)) {
			Class<?> aClass = Class.forName(bd.getBeanClassName());
			ProcessingGroup processingGroup = aClass.getAnnotation(ProcessingGroup.class);
			String name = Optional.ofNullable(processingGroup).map(ProcessingGroup::value)
					.orElse(aClass.getPackage().getName());
			registerTrackingProcessor(name);
		}
	}

	private void registerTrackingProcessor(String name) {
		eventHandlingConfiguration.registerTrackingProcessor(name);
	}
}
