package sample.jugquiz.impl;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.Jsonable;


public interface JugQuizEvent extends Jsonable {
	
	/**
	   * An event that represents a change in greeting message.
	   */
	  @SuppressWarnings("serial")
	  @Immutable
	  @JsonDeserialize
	  public final class JugQuizChanged implements JugQuizEvent {
	    public final String message;

	    @JsonCreator
	    public JugQuizChanged(String message) {
	      this.message = Preconditions.checkNotNull(message, "message");
	    }

	    @Override
	    public boolean equals(@Nullable Object another) {
	      if (this == another)
	        return true;
	      return another instanceof JugQuizChanged && equalTo((JugQuizChanged) another);
	    }

	    private boolean equalTo(JugQuizChanged another) {
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
	      return MoreObjects.toStringHelper("JugQuizChanged").add("message", message).toString();
	    }
	  }

}
