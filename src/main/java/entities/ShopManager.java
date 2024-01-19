package entities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import interfaces.Ipersistence;
import dataBases.CatalogueDb;
import dataBases.ShopDb;
import dataBases.StockDb;
import dataBases.TicketDb;

public class ShopManager implements Ipersistence {
    private static ShopManager instance;
    private Map<String, Shop> shopMap;

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
            System.out.println("Error: The florist already exists.");
        } else {
            Shop newShop = new Shop(newFlowerShopName);

            addShop(newShop);
            guardarShop(newShop);

            CatalogueDb newCatalogoBD = CatalogueDb.getInstance();
            newCatalogoBD.guardarBd(newShop.getCatalogueDbName());

            StockDb newStockDB = new StockDb();
            newStockDB.guardarBd(newShop.getStockDbName());

            TicketDb newTicketDB = TicketDb.getInstance();
            newTicketDB.guardarBd(newShop.getTicketDbName());

            System.out.println("New florist created: " + newShop.getName());
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Store List:\n");

        for (Shop shop : shopMap.values()) {
            sb.append("Name: ").append(shop.getName())
                    .append(", Catalogue: ").append(shop.getCatalogueDbName())
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
