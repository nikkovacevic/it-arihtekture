package ita.ms.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import ita.ms.model.Property;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class PropertyResourceTest {

    @Test
    @Transactional
    void testSearchProperty() {

        given()
                .when().get("/api/properties/search?query=1")
                .then()
                    .statusCode(200)
                    .contentType(MediaType.APPLICATION_JSON)
                .body("[0].location", equalTo("Praha 1 - Nové Město"));

        given()
                .when().get("/api/properties/search?query=banana")
                .then()
                .statusCode(404);

        }

    @Test
    void testGetAll() {
        given()
                .when().get("/api/properties/getAll")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                .body("$.size()", equalTo(500));
    }
}