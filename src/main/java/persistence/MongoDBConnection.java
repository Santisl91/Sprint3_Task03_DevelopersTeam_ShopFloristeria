/*package persistence;

import interfaces.IConexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection implements IConexion {

    private MongoClient mongoClient;

    public MongoDBConnection(String connectionString) {
        this.mongoClient = MongoClients.create(connectionString);
    }

    @Override
    public void conectar() {
        System.out.println("Connected to MongoDB.");
    }

    @Override
    public void desconectar() {
        if (mongoClient != null) {
            mongoClient.close();
        }
        System.out.println("Disconnected from MongoDB.");
    }
}*/
