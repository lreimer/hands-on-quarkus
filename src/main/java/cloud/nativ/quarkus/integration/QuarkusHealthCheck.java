package cloud.nativ.quarkus.integration;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Health
public class QuarkusHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("Quarkus.io").withData("version", "0.12").up().build();
    }
}
