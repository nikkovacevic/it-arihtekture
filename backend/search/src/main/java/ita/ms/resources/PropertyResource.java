package ita.ms.resources;

import ita.ms.model.Property;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/api/properties")
@OpenAPIDefinition(
        info = @Info(
                title = "Property API",
                version = "1.0.0"
                ),
        servers = @Server(
                url = "http://localhost:8082",
                description = "Local server"
        )
)
@Tag(name = "SearchProperties")
public class PropertyResource {

    @Inject
    EntityManager entityManager;

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Property> searchProperty(@QueryParam("query") String query) {
        try {
            List<Property> resultList = entityManager
                    .createQuery("SELECT p FROM Property p WHERE lower(p.location) LIKE :query", Property.class)
                    .setParameter("query", "%" + query.toLowerCase() + "%")
                    .getResultList();
            if (resultList.isEmpty()) {
                throw new NotFoundException("No properties found for query: " + query);
            }
            return resultList;
        } catch (Exception e) {
            throw e;
        }

    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Property> getAll() {
        try {
            return entityManager
                    .createQuery("SELECT p FROM Property p", Property.class)
                    .getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

}