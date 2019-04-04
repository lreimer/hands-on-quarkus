package cloud.nativ.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LibraryResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/library")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}