package persistence;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import entities.Catalogue;
import entities.Decoration;
import entities.Flower;
import entities.Tree;

import java.io.File;
import java.io.IOException;

public class CatalogoBD {
    private Catalogue catalogoDb;
    private static CatalogoBD instance;

    public CatalogoBD() {

    }
    public CatalogoBD(Catalogue cat) {
        catalogoDb = cat;
    }

    public static CatalogoBD getInstance() {
        if (instance == null) {
            instance = new CatalogoBD();
        }
        return instance;
    }

    public Catalogue leerBd(String filePath) {
        JsonNode jsonNode;
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
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

        JsonNode cat = jsonNode.get(Catalogue.class.toString());
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
        return null;
    }

    public void guardarBd(String filePath) {
        if (this.catalogoDb != null) {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre(filePath);
        JSONObject jsonCatalogo = new JSONObject();
        jsonCatalogo.put(catalogoDb.getClass().toString(), catalogoDb.getItems());
        f.write(jsonCatalogo);

        } else {
            System.out.println("Error: El objeto CatalogoDb es nulo y no puede ser guardado.");
        }
    }
}
