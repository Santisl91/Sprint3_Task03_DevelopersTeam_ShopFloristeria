package persistence;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import entities.Catalogue;
import entities.Decoration;
import entities.Flower;
import entities.Tree;

import java.io.File;
import java.io.IOException;

public class CatalogueDb {
    private Catalogue catalogoDb;
    private static CatalogueDb instance;

    public CatalogueDb() {

    }
    public CatalogueDb(Catalogue cat) {
        catalogoDb = cat;
    }

    public static CatalogueDb getInstance() {
        if (instance == null) {
            instance = new CatalogueDb();
        }
        return instance;
    }

    public Catalogue leerBd(String filePath) {
        JsonNode jsonNode;
        FileConnection f = (FileConnection) FactoryDb.getConexionBD("TXT");
        f.setNombre(filePath);

        File file = new File(f.getNombre());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        jsonNode = (JsonNode) f.leer();
        if (jsonNode != null) {
            JsonNode cat = jsonNode.get(Catalogue.class.toString());

            if (cat != null) {
                for (JsonNode prod : cat) {
                    String tipo = prod.get("tipo").asText();
                    if (tipo.equals(Flower.class.toString())) {
                        Flower fl = new Flower(prod.get("id").asInt(), prod.get("name").asText(), prod.get("color").asText(), prod.get("price").asDouble());
                        catalogoDb.addItem(fl);

                    } else if (tipo.equals(Tree.class.toString())) {
                        Tree tr = new Tree(prod.get("id").asInt(), prod.get("name").asText(), prod.get("height").asText(), prod.get("price").asDouble());
                        catalogoDb.addItem(tr);

                    } else if (tipo.equals(Decoration.class.toString())) {
                        Decoration dc = new Decoration(prod.get("id").asInt(), prod.get("name").asText(), prod.get("material").asText(), prod.get("price").asDouble());
                        catalogoDb.addItem(dc);

                    }
                }
            } else {
                System.out.println("Error: Catalog key not found in JSON file.");
            }
        } else {
            System.out.println("Error: The JSON file is empty or could not be read correctly.");
        }

        return null;
    }

    public void guardarBd(String filePath) {
        if (this.catalogoDb != null) {
            FileConnection f = (FileConnection) FactoryDb.getConexionBD("TXT");
            f.setNombre(filePath);
            JSONObject jsonCatalogo = new JSONObject();
            jsonCatalogo.put(catalogoDb.getClass().toString(), catalogoDb.getItems());
            f.write(jsonCatalogo);

        } else {
            System.out.println("Error: The CatalogDb object is null and cannot be saved.");
        }
    }
}
