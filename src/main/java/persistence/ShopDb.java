package persistence;

import app.Shop;
import entities.ShopManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShopDb {

    private Shop shop;
    private ShopManager shopManager;

    private static ShopDb instance;

    public ShopDb() {
        this.shop = null;
        this.shopManager = ShopManager.getInstance();
    }

    public ShopDb(Shop sh, ShopManager shManager) {
        this.shop = sh;
        this.shopManager = shManager;
    }


    public static ShopDb getInstance() {
        if (instance == null) {
            instance = new ShopDb();
        }
        return instance;
    }

    public void leerShop() throws IOException {
        FileConnection f = (FileConnection) FactoryDb.getConexionBD("TXT");
        f.setNombre("Shop.txt");
        ShopManager shopManager = ShopManager.getInstance();

        try (BufferedReader reader = new BufferedReader(new FileReader(f.getNombre()))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line.trim());
            }

            JSONObject jsonShopObject = new JSONObject(jsonStringBuilder.toString());

            for (String shopKey : jsonShopObject.keySet()) {
                JSONObject jsonShop = jsonShopObject.getJSONObject(shopKey);

                String nombre = jsonShop.getString("Name");
                String catalogo = jsonShop.getString("Catalogue");
                String stock = jsonShop.getString("Stock");
                String ticket = jsonShop.getString("Ticket");

                Shop newshop = new Shop(nombre, catalogo, stock, ticket);

                shopManager.addShop(newshop);
            }
        } catch (JSONException e) {
            System.out.println("Error processing JSON content of Shop.txt file: " + e.getMessage());
        }
    }

    public void guardarShop(Shop shop) throws IOException {
        JSONObject jsonShops = new JSONObject();
        try {
            String content = new String(Files.readAllBytes(Paths.get("Shop.txt")));
            if (!content.isEmpty()) {
                jsonShops = new JSONObject(content);
            }
        } catch (FileNotFoundException e) {
        }
        if (shop != null && shop.getName() != null) {

        JSONObject newShopJSON = new JSONObject();
        newShopJSON.put("Name", shop.getName());
        newShopJSON.put("Catalogue", shop.getCatalogueDbName());
        newShopJSON.put("Stock", shop.getStockDbName());
        newShopJSON.put("Ticket", shop.getTicketDbName());

        jsonShops.put(shop.getName(), newShopJSON);
        } else {
            throw new IllegalArgumentException("The 'shop' object is null or has a null name.");
        }
        try (FileWriter file = new FileWriter("Shop.txt")) {
            file.write(jsonShops.toString(4));
        }
    }
}