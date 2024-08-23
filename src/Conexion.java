import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Conexion {
    private static MongoClient mongoClient = null;

    public static MongoDatabase connectToDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
        }
        return mongoClient.getDatabase("SISTEMA_NOTAS");
    }
}

