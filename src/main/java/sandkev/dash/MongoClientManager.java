package sandkev.dash;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import io.dropwizard.lifecycle.Managed;

public class MongoClientManager implements Managed {
    private MongoClient mongoClient;

    public MongoClientManager(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {
/*
        MongodStarter starter = MongodStarter.getDefaultInstance();

        MongodExecutable _mongodExe = starter.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(27017, Network.localhostIsIPv6()))
                .build());
        MongodProcess mongod = _mongodExe.start();
        mongoClient = new MongoClient();  //"localhost", 12345
*/

    }

    @Override
    public void stop() throws Exception {
        mongoClient.close();
    }
}
