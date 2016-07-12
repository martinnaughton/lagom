package sample.jugquiz.impl;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;

import akka.Done;

/*
 * 
 */
public interface JugQuizCommand extends Jsonable{
	
	 @SuppressWarnings("serial")
	  @Immutable
	  @JsonDeserialize
	  public final class QuizQuestions implements JugQuizCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
	    public final String message;

	    @JsonCreator
	    public QuizQuestions(String message) {
	      this.message = Preconditions.checkNotNull(message, "message");
	    }

	    @Override
	    public boolean equals(@Nullable Object another) {
	      if (this == another)
	        return true;
	      return another instanceof QuizQuestions && equalTo((QuizQuestions) another);
	    }

	    private boolean equalTo(QuizQuestions another) {
	      return message.equals(another.message);
	    }

	    @Override
	    public int hashCode() {
	      int h = 31;
	      h = h * 17 + message.hashCode();
	      return h;
	    }

	    @Override
	    public String toString() {
	      return MoreObjects.toStringHelper("UseGreetingMessage").add("message", message).toString();
	    }
	  }
	 
	 @SuppressWarnings("serial")
	  @Immutable
	  @JsonDeserialize
	  public final class CorrectAnswer implements JugQuizCommand, CompressedJsonable, PersistentEntity.ReplyType<String> {

	    @JsonCreator
	    public CorrectAnswer() {
	    }

	    @Override
	    public boolean equals(@Nullable Object another) {
	      if (this == another)
	        return true;
	      return another instanceof CorrectAnswer;
	    }


	    @Override
	    public int hashCode() {
	      int h = 31;
	      return h;
	    }

	    @Override
	    public String toString() {
	      return MoreObjects.toStringHelper("CorrectAnswer").add("message", "You did it").toString();
	    }
	  }

}
