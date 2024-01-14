package persistence;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Product;
import entities.Stock;
import entities.StockItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StockDB {
    private Stock stockDb;
    private static StockDB instance;

    public StockDB(Stock stock) {
        stockDb = stock;
    }

    public StockDB() {

    }
    public static StockDB getInstance() {
        if (instance == null) {
            instance = new StockDB();
        }
        return instance;
    }

    public Stock leerBd(String filePath) throws IOException {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre(filePath);

        File file = new File(f.getNombre());
        if (!file.exists()) {
            throw new IOException("El archivo Stock.txt no existe");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);

        if (rootNode != null && rootNode.has("stock")) {
            JsonNode stockNode = rootNode.get("stock");

            Map<Integer, Integer> stockItems = new HashMap<>();

            for (JsonNode stockItemNode : stockNode) {
                int productId = stockItemNode.get("id").asInt();
                int quantity = stockItemNode.get("cantidad").asInt();

                stockItems.put(productId, quantity);
            }

            Stock.getInstance().setStockItems(stockItems);
        } else {
            throw new IOException("Error al leer el archivo JSON - No se encontró el nodo 'stock'");
        }

        return null;
    }


    public void guardarBd(String filePath) throws IOException {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre(filePath);
        Stock stock = Stock.getInstance();
        JSONArray stockArray = new JSONArray();
        for (Map.Entry<Integer, Integer> entry : stock.getItems().entrySet()) {
            JSONObject stockItemObject = new JSONObject();
            stockItemObject.put("id", entry.getKey());
            stockItemObject.put("cantidad", entry.getValue());
            stockArray.put(stockItemObject);
        }

        JSONObject jsonStock = new JSONObject();
        jsonStock.put("stock", stockArray);

        f.write(jsonStock);
    }

}
