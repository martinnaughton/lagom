package sample.jugquiz.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import akka.Done;
import akka.NotUsed;
import sample.jugquiz.api.Answer;
import sample.jugquiz.api.JugQuizService;
import sample.jugquiz.impl.JugQuizCommand.CorrectAnswer;
import sample.jugquiz.impl.JugQuizCommand.QuizQuestions;


public class JugQuizServiceImpl implements JugQuizService{
	
	private final Logger logger = LoggerFactory.getLogger(JugQuizServiceImpl.class);
	  private PersistentEntityRegistry persistentEntityRegistry;

	@Inject
	  public JugQuizServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
	    this.persistentEntityRegistry = persistentEntityRegistry;
	    persistentEntityRegistry.register(JugQuizEntity.class);
	  }

	public ServiceCall<Answer,Done> answerEntry(String id)
	{
		logger.error("I made it here GREAT");
		return (request) -> {
		PersistentEntityRef<JugQuizCommand> ref = persistentEntityRegistry.refFor(JugQuizEntity.class, id);
		return ref.ask(new QuizQuestions("I'm here"));
		};
	}

	@Override
	public ServiceCall<NotUsed, String> sayHelloBack(String id) {
		logger.error("Just saying hello back\n");
		return (request) -> {
			PersistentEntityRef<JugQuizCommand> ref = persistentEntityRegistry.refFor(JugQuizEntity.class,id);
			return ref.ask(new CorrectAnswer());
		};
	}
}
