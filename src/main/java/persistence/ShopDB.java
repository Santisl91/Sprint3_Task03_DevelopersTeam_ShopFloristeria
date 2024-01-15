package persistence;

import entities.ShopManager;
import org.json.JSONException;
import org.json.JSONObject;
import app.Shop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShopDb {

    private Shop shop;
    private ShopManager shopManager;

    public ShopDb(Shop shop, ShopManager shopManager) {
        this.shop = shop;
        this.shopManager = shopManager;
    }

    public ShopDb() {

    }
    public void leerShop() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Shop.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject jsonShop = new JSONObject(line);

                if (jsonShop.has("Name") && jsonShop.has("Catalogue") && jsonShop.has("Stock") && jsonShop.has("Ticket")) {
                    String nombre = jsonShop.getString("Name");
                    String catalogo = jsonShop.getString("Catalogue");
                    String stock = jsonShop.getString("Stock");
                    String ticket = jsonShop.getString("Ticket");

                    Shop shop = new Shop(nombre, catalogo, stock, ticket);

                    shopManager.addShop(shop);
                } else {
                    System.out.println("Error: Shop.txt file is not in the expected format.");
                }
            }
        } catch (IOException | JSONException e) {
            throw new IOException("Error loading Shop database: " + e.getMessage());
        }
    }
    public void guardarShop() throws IOException {
        if (shop != null) {
            FileConnection f = (FileConnection) FactoryDb.getConexionBD("TXT");
            f.setNombre("Shop.txt");

            if (shop != null) {
                JSONObject jsonShop = new JSONObject();
                jsonShop.put("Name", shop.getName());
                jsonShop.put("Catalogue", shop.getCatalogueDbName());
                jsonShop.put("Stock", shop.getStockDbName());
                jsonShop.put("Ticket", shop.getTicketDbName());

                f.write(jsonShop);
            } else {
                System.out.println("Error: The Shop object is null and cannot be saved.");
            }
        }
    }
}