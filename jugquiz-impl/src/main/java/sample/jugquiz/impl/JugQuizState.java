package sample.jugquiz.impl;


import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;

@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public class JugQuizState implements CompressedJsonable {
	
	String message;
	private String timestamp;

	public JugQuizState(String message, String timestamp) {
	    this.message = Preconditions.checkNotNull(message, "message");
	    this.timestamp = Preconditions.checkNotNull(timestamp, "timestamp");
	  }

	@Override
	  public boolean equals(@Nullable Object another) {
	    if (this == another)
	      return true;
	    return another instanceof JugQuizState && equalTo((JugQuizState) another);
	  }

	  private boolean equalTo(JugQuizState another) {
	    return message.equals(another.message) && timestamp.equals(another.timestamp);
	  }

	  @Override
	  public int hashCode() {
	    int h = 31;
	    h = h * 17 + message.hashCode();
	    h = h * 17 + timestamp.hashCode();
	    return h;
	  }

	  @Override
	  public String toString() {
	    return MoreObjects.toStringHelper("JugQuizState").add("message", message).add("timestamp", timestamp).toString();
	  }
}
