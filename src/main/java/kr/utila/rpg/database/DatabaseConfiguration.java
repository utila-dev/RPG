package kr.utila.rpg.database;

public class DatabaseConfiguration {

    private final String address = null;
    private final String port = null;
    private final String database = null;
    private final String username = null;
    private final String password = null;

    public String getURL() {
        final String URL = "mongodb://" + username + ":" + password + "@" + address + ":" + port + "/";
        return URL;
    }

    public String getDatabase() {
        return database;
    }
}
