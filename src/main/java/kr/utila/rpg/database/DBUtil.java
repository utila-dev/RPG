package kr.utila.rpg.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DBUtil {
    private static final Gson gson = (new GsonBuilder()).create();

    public static void update(MongoCollection<Document> collection, Document filter, Document update) {
        collection.updateOne(filter, new Document("$set", update));
    }

    public static Document toDocument(Object object) {
        return Document.parse(gson.toJson(object));
    }

    public static Object toObject(Document document, Class<?> clazz) {
        return gson.fromJson(document.toJson(), clazz);
    }
}
