package persistence;

import app.Shop;
import entities.ShopManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ShopDB {

    private Shop shop;
    private ShopManager shopManager;

    private static ShopDB instance;

    public ShopDB() {
        this.shop = null;  // Inicializa con un objeto Shop por defecto
        this.shopManager = ShopManager.getInstance();  // Inicializa con una instancia de ShopManager por defecto
    }

    public ShopDB(Shop sh, ShopManager shManager) {
        this.shop = sh;
        this.shopManager = shManager;
    }


    public static ShopDB getInstance() {
        if (instance == null) {
            instance = new ShopDB();
        }
        return instance;
    }

    public void leerShop() throws IOException {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Shop.txt");
        ShopManager shopManager = ShopManager.getInstance();

        try (BufferedReader reader = new BufferedReader(new FileReader(f.getNombre()))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line.trim());
            }

            // Convertir el contenido del archivo a un objeto JSONObject
            JSONObject jsonShopObject = new JSONObject(jsonStringBuilder.toString());

            // Iterar a través de las claves (nombres de tiendas) en el objeto JSON
            for (String shopKey : jsonShopObject.keySet()) {
                // Obtener el objeto JSON asociado a la tienda actual
                JSONObject jsonShop = jsonShopObject.getJSONObject(shopKey);

                // Obtener los valores del objeto JSON
                String nombre = jsonShop.getString("Nombre");
                String catalogo = jsonShop.getString("Catalogo");
                String stock = jsonShop.getString("Stock");
                String ticket = jsonShop.getString("Ticket");

                // Crear una instancia de Shop con la información obtenida
                Shop newShop = new Shop(nombre, catalogo, stock, ticket);

                // Agregar la tienda al ShopManager
                shopManager.addShop(newShop);
            }
        } catch (JSONException e) {
            System.out.println("Error al procesar el contenido JSON del archivo Shop.txt: " + e.getMessage());
        }
    }

    public void guardarShop() throws IOException {
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
        newShopJSON.put("Nombre", shop.getName());
        newShopJSON.put("Catalogo", shop.getCatalogueDbName());
        newShopJSON.put("Stock", shop.getStockDbName());
        newShopJSON.put("Ticket", shop.getTicketDbName());

        jsonShops.put(shop.getName(), newShopJSON);
        } else {
            throw new IllegalArgumentException("El objeto 'shop' es nulo o tiene un nombre nulo.");
        }
        try (FileWriter file = new FileWriter("Shop.txt")) {
            file.write(jsonShops.toString(4)); // Uso toString(4) para dar formato al JSON
        }
    }
}