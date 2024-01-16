package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Shop;
import interfaces.Ipersistence;
import persistence.CatalogueDb;
import persistence.ShopDb;
import persistence.ShopDb;
import persistence.StockDb;
import persistence.TicketDb;

public class ShopManager implements Ipersistence {
    private static ShopManager instance;
    private Map<String, Shop> shopMap; // Utilizando el nombre de la tienda como clave

    private ShopManager() {
        this.shopMap = new HashMap<>();
    }

    public static ShopManager getInstance() {
        if (instance == null) {
            instance = new ShopManager();
        }
        return instance;
    }

    public void addShop(Shop shop) {
        shopMap.put(shop.getName(), shop);
    }
    public Shop getShop(String flowerShopName) {
        return shopMap.get(flowerShopName);
    }
    public Map<String, Shop> getItems() {
        return new HashMap<>(shopMap);
    }
    public Object crearNuevaFloristeria(String newFlowerShopName) throws IOException {
        if (shopMap.containsKey(newFlowerShopName)) {
            System.out.println("Error: La floristería ya existe.");
        } else {
            // Crear una nueva tienda
            Shop newShop = new Shop(newFlowerShopName);

            // Guardar la nueva tienda y sus datos
            addShop(newShop);
            guardarShop(newShop);

            // Crear archivos TXT asociados a la nueva floristería
            CatalogueDb newCatalogoBD = CatalogueDb.getInstance();
            newCatalogoBD.guardarBd(newShop.getCatalogueDbName());

            StockDb newStockDB = new StockDb();
            newStockDB.guardarBd(newShop.getStockDbName());

            TicketDb newTicketDB = TicketDb.getInstance();
            newTicketDB.guardarBd(newShop.getTicketDbName());

            System.out.println("Nueva floristería creada: " + newShop.getName());
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de tiendas:\n");

        for (Shop shop : shopMap.values()) {
            sb.append("Nombre: ").append(shop.getName())
                    .append(", Catálogo: ").append(shop.getCatalogueDbName())
                    .append(", Stock: ").append(shop.getStockDbName())
                    .append(", Ticket: ").append(shop.getTicketDbName())
                    .append("\n");
        }

        return sb.toString();
    }

    @Override
    public void guardarShop(Shop newShop) throws IOException {
        ShopDb sh = new ShopDb();
        sh.guardarShop(newShop);
    }

    @Override
    public void leerShop() throws IOException {
        ShopDb sh = new ShopDb();
        sh.leerShop();
    }


    @Override
    public void guardarCatalogo(String catalogoFileName) throws IOException {
    }

    @Override
    public void guardarStock(String stockFileName) throws IOException {
    }

    @Override
    public void guardarTicket(String ticketFileName) throws IOException {
    }

    @Override
    public void leerCatalogo(String catalogoFileName) throws IOException {
    }

    @Override
    public void leerStock(String stockFileName) throws IOException {
    }

    @Override
    public void leerTicket(String ticketFileName) throws IOException {

    }
}
