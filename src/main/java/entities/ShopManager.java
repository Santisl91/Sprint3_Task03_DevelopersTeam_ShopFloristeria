package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import app.Shop;
import interfaces.Ipersistence;

public class ShopManager {
    private static ShopManager instance;
    private List<Shop> shops;

    private ShopManager() {
        this.shops = new ArrayList<>();
    }

    public static ShopManager getInstance() {
        if (instance == null) {
            instance = new ShopManager();
        }
        return instance;
    }

    public void addShop(Shop shop) {
        shops.add(shop);
    }

    public Shop getShop(String flowerShopName) {
        for (Shop shop : shops) {
            if (shop.getName().equals(flowerShopName)) {
                return shop;
            }
        }
        return null; // Si no se encuentra la tienda
    }

    public List<Shop> getShops() {
        return shops;
    }


}
