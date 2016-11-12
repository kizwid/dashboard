package sandkev.dash;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import sandkev.dash.resource.CoffeeShopResource;
import sandkev.dash.resource.HelloResource;
import sandkev.dash.resource.PhotoTaskResource;

import java.io.IOException;

/**
 * Created by kevin on 09/11/2016.
 */
public class DashboardApp extends Application<DashboardAppConfig> {

    private MongodProcess mongod;
    private MongoClient mongo;

    public static void main(String[] args) throws Exception {
        new DashboardApp().run(args);
    }

    @Override
    public void run(DashboardAppConfig config, Environment environment) throws Exception {

        environment.jersey().setUrlPattern("/service/*");
        //MongoClient mongoClient = new MongoClient();
        MongoClient mongoClient = startEmmbeddedMongo();
        environment.lifecycle().manage(new MongoClientManager(mongoClient));
        environment.jersey().register(new CoffeeShopResource(mongoClient));
        environment.jersey().register(new PhotoTaskResource(mongoClient));

        final HelloResource resource = new HelloResource(
                config.getTemplate(),
                config.getDefaultName()
        );
        environment.jersey().register(resource);
    }


    @Override
    public String getName() {
        return "dashboard";
    }

    @Override
    public void initialize(Bootstrap<DashboardAppConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/www/", "/dashboard"));
    }


    private MongoClient startEmmbeddedMongo() throws IOException {
        //http://stackoverflow.com/questions/6437226/embedded-mongodb-when-running-integration-tests

        MongodStarter starter = MongodStarter.getDefaultInstance();

        MongodExecutable _mongodExe = starter.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(27017, Network.localhostIsIPv6()))
                .build());
        mongod = _mongodExe.start();
        mongo = new MongoClient();  //"localhost", 12345
        return mongo;

    }

}
