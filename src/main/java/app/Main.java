package app;

import entities.*;
import menu.ProductItemFactory;
import persistence.CatalogueDb;
import persistence.StockDb;
import persistence.TicketDb;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    private static ShopManager shopManager = ShopManager.getInstance();
    static Catalogue catalogo = Catalogue.getInstance();
    static Stock stock = Stock.getInstance();
    static TicketManager ticket = TicketManager.getInstance();


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Decoration.Shop shop = seleccionarFloristeria();

        if (shop.getName() != null) {

            int eleccion;

            do {
                mostrarMenu();
                eleccion = scanner.nextInt();
                scanner.nextLine();

                if (eleccion != 0) {
                    Object nuevoItem = ProductItemFactory.createCatalogItem(eleccion, catalogo, shop);
                } else {
                    System.out.println("Closed program.");
                }

            } while (eleccion != 0);

            catalogo.guardarCatalogo(shop.getCatalogueDbName());
            stock.guardarStock(shop.getStockDbName());
            ticket.guardarTicket(shop.getTicketDbName());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add tree.");
        System.out.println("2. Add flower.");
        System.out.println("3. Add decoration.");
        System.out.println("4. Remove tree.");
        System.out.println("5. Remove flower.");
        System.out.println("6. Remove decoration.");
        System.out.println("7. Show all tickets");
        System.out.println("8. Show stock with product quantities.");
        System.out.println("9. Create Florist.");
        System.out.println("10. Create Ticket.");
        System.out.println("11. Total sales.");
        System.out.println("0. Exit menu.");

    }

    private static Decoration.Shop seleccionarFloristeria() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the florist app.");

        ShopManager shopManager = ShopManager.getInstance();
        shopManager.leerShop();
        System.out.println(shopManager.toString());

        Decoration.Shop selectedShop = null;

        do {
            System.out.print("Enter the name of the florist: ");
            String flowerShopName = scanner.nextLine();

            selectedShop = shopManager.getShop(flowerShopName);

            if (selectedShop == null) {
                System.out.println("Florist not found '" + flowerShopName + "'.");
                System.out.print("Do you want to create a new flower shop? (y/n): ");
                String crearNueva = scanner.nextLine();

                if (crearNueva.equalsIgnoreCase("y")) {
                    selectedShop = new Decoration.Shop(flowerShopName);

                    String catalogoFileName = flowerShopName + ".catalog.txt";
                    String stockFileName = flowerShopName + ".stock.txt";
                    String ticketFileName = flowerShopName + ".ticket.txt";

                    selectedShop.setCatalogueDbName(catalogoFileName);
                    selectedShop.setStockDbName(stockFileName);
                    selectedShop.setTicketDbName(ticketFileName);

                    shopManager.guardarShop(selectedShop);

                    CatalogueDb catalogueDb = CatalogueDb.getInstance();
                    catalogueDb.guardarBd(selectedShop.getCatalogueDbName());

                    StockDb stockDB = StockDb.getInstance();
                    stockDB.guardarBd(selectedShop.getStockDbName());

                    TicketDb ticketDB = TicketDb.getInstance();
                    ticketDB.guardarBd(selectedShop.getTicketDbName());

                    System.out.println("New florist created: " + selectedShop.getName());
                }
            } else {
                System.out.println("Selected florista: " + selectedShop.getName());

                try {
                    catalogo.leerCatalogo(selectedShop.getCatalogueDbName());
                    stock.leerStock(selectedShop.getStockDbName());
                    ticket.leerTicket(selectedShop.getTicketDbName());

                } catch (IOException e) {
                    System.out.println("Error reading florist files: " + e.getMessage());
                }

                break;
            }


            System.out.println("");
        } while (selectedShop == null);

        return selectedShop;
    }

}