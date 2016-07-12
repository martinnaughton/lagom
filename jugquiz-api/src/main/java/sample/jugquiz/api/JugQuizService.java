package sample.jugquiz.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

public interface JugQuizService extends Service{

	/* sends the answer object with a response done and the id of the answer*/
	ServiceCall<Answer,Done> answerEntry(String id);
	
	/* By having nothing in the request section it is implicitly a get command*/
	ServiceCall<NotUsed,String> sayHelloBack(String id);

	@Override 
	default Descriptor descriptor() {
		return named("jugquizservice").withCalls(
			pathCall("/api/jugquiz/:id",  this::sayHelloBack),
			pathCall("/api/jugquiz/:id", this::answerEntry)
		).withAutoAcl(true);
	}
}
