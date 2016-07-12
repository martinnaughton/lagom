package sample.jugquiz.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import sample.jugquiz.api.JugQuizService;


	
public class JugQuizModule extends AbstractModule implements ServiceGuiceSupport {
	@Override
	protected void configure() {
		bindServices(serviceBinding(JugQuizService.class, JugQuizServiceImpl.class));
	}
}


