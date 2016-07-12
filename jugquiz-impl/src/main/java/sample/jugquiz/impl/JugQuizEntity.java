package sample.jugquiz.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import akka.Done;
import sample.jugquiz.impl.JugQuizCommand.CorrectAnswer;
import sample.jugquiz.impl.JugQuizCommand.QuizQuestions;
import sample.jugquiz.impl.JugQuizEvent.JugQuizChanged;

public class JugQuizEntity extends PersistentEntity<JugQuizCommand, JugQuizEvent, JugQuizState>{

	@Override
	public Behavior initialBehavior(Optional<JugQuizState> snapshotState) {
		
		BehaviorBuilder behavior = newBehaviorBuilder(
		        snapshotState.orElse(new JugQuizState("Hello", LocalDateTime.now().toString())));
		
		/*
	     * Command handler for the UseGreetingMessage command.
	     */
	    behavior.setCommandHandler(QuizQuestions.class, (command, context) ->
	    // In response to this command, we want to first persist it as a
	    // GreetingMessageChanged event
	    context.thenPersist(new JugQuizChanged(command.message),
	        // Then once the event is successfully persisted, we respond with done.
	        (event) -> {
	        	context.reply(Done.getInstance());
	        }));

	    /*
	     * Event handler for the GreetingMessageChanged event.
	     */
	    behavior.setEventHandler(JugQuizChanged.class,
	        // We simply update the current state to use the greeting message from
	        // the event.
	        event -> new JugQuizState(event.message, LocalDateTime.now().toString()));


	    /*
	     * Command handler for the Hello command.
	     */
	    behavior.setReadOnlyCommandHandler(CorrectAnswer.class,
	        // Get the greeting from the current state, and prepend it to the name
	        // that we're sending
	        // a greeting to, and reply with that message.
	        (command, context) -> {
	        	context.reply(state().message + "why!\n");
	        	
	        });
	        /*
	     * We've defined all our behaviour, so build and return it.
	     */
	    return behavior.build();
	}
}
