package kr.utila.rpg.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import kr.utila.rpg.Main;
import kr.utila.rpg.utils.FileUtil;

import java.io.File;

public class DBConnector {

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;

    public static void connect() {
        Main plugin = Main.getInstance();
        final File FILE = new File(plugin.getDataFolder(), "database.json");
        if (!FILE.exists()) {
            plugin.saveResource("database.json", false);
        }
        DatabaseConfiguration configuration = (DatabaseConfiguration) FileUtil.read(FILE, DatabaseConfiguration.class);

        ConnectionString connectionString = new ConnectionString(configuration.getURL());
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        mongoClient = MongoClients.create(settings);
        mongoDatabase = mongoClient.getDatabase(configuration.getDatabase());

    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
