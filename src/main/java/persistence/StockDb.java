package persistence;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Stock;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StockDb {
    private Stock stockDb;
    private static StockDb instance;

    public StockDb(Stock stock) {
        stockDb = stock;
    }

    public StockDb() {

    }
    public static StockDb getInstance() {
        if (instance == null) {
            instance = new StockDb();
        }
        return instance;
    }
    public Stock leerBd(String filePath) throws IOException {
        FileConnection f = (FileConnection) FactoryDb.getConexionBD("TXT");
        f.setNombre(filePath);

        File file = new File(f.getNombre());
        if (!file.exists()) {
            throw new IOException("Stock.txt file does not exist.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);

        if (rootNode != null && rootNode.has("stock")) {
            JsonNode stockNode = rootNode.get("stock");

            Map<Integer, Integer> stockItems = new HashMap<>();

            for (JsonNode stockItemNode : stockNode) {
                int productId = stockItemNode.get("id").asInt();
                int quantity = stockItemNode.get("quantity").asInt();

                stockItems.put(productId, quantity);
            }

            Stock.getInstance().setStockItems(stockItems);
        } else {
            throw new IOException("Error reading JSON file - Node 'stock' not found.");
        }

        return null;
    }
    public void guardarBd(String filePath) throws IOException {
        FileConnection f = (FileConnection) FactoryDb.getConexionBD("TXT");
        f.setNombre(filePath);
        Stock stock = Stock.getInstance();
        JSONArray stockArray = new JSONArray();
        for (Map.Entry<Integer, Integer> entry : stock.getItems().entrySet()) {
            JSONObject stockItemObject = new JSONObject();
            stockItemObject.put("id", entry.getKey());
            stockItemObject.put("quantity", entry.getValue());
            stockArray.put(stockItemObject);
        }

        JSONObject jsonStock = new JSONObject();
        jsonStock.put("stock", stockArray);

        f.write(jsonStock);
    }
}
