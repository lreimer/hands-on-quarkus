package cloud.nativ.quarkus.boundary;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Timed(absolute = true, unit = MetricUnits.MILLISECONDS)
    public CompletionStage<String> hello() {
        return CompletableFuture.supplyAsync(() -> "Hello Quarkus!");
    }
}