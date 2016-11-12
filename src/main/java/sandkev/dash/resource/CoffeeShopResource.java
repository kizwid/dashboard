package sandkev.dash.resource;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import sandkev.dash.pojo.CoffeeShop;
import sandkev.dash.pojo.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.mongodb.morphia.geo.PointBuilder.pointBuilder;

@Path("/coffeeshop")
@Produces(APPLICATION_JSON)
public class CoffeeShopResource {
    private Datastore datastore;

    public CoffeeShopResource(MongoClient mongoClient) {
        datastore = new Morphia().createDatastore(mongoClient, "Cafelito");
    }

    @Path("ping")
    @GET
    public String ping(){
        return "Hi";
    }

    @Path("nearest/{latitude}/{longitude}")
    @GET
    public Object getNearest(@PathParam("latitude") double latitude,
                             @PathParam("longitude") double longitude) {
        return datastore.find(CoffeeShop.class)
                        .field("location")
                        .near(pointBuilder().latitude(latitude)
                                            .longitude(longitude).build(), 2000)
                        .get();
    }


    @Path("order")
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveOrder(Order order) {
        datastore.save(order);

        return Response.created(URI.create(order.getId()))
                       .entity(order)
                       .build();
    }
}
