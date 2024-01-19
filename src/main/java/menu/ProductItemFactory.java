package menu;

import entities.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductItemFactory {

    public static Object createCatalogItem(int itemType, Catalogue catalog, Shop shop) throws IOException {
        Scanner scanner = new Scanner(System.in);

        switch (itemType) {
            case 1:
                return createTree(catalog, shop, scanner);

            case 2:
                return createFlower(catalog, shop, scanner);

            case 3:
                return createDecoration(catalog, shop, scanner);

            case 4:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the tree to remove:");
                int treeToRemove = scanner.nextInt();
                catalog.removeProductById(treeToRemove);
                catalog.guardarCatalogo(shop.getCatalogueDbName());
                return null;

            case 5:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the flower to collect:");
                int flowerToRemove = scanner.nextInt();
                catalog.removeProductById(flowerToRemove);
                catalog.guardarCatalogo(shop.getCatalogueDbName());
                return null;

            case 6:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the decoration to be removed:");
                int decorationToRemove = scanner.nextInt();
                catalog.removeProductById(decorationToRemove);
                catalog.guardarCatalogo(shop.getCatalogueDbName());
                return null;

            case 7:
                Stock stock = Stock.getInstance();
                Stock.showStockWithQuantities(shop);
                System.out.println("Enter the ID of the product you want to add stock:");
                int idToAdd = scanner.nextInt();
                System.out.println("Enter the new stock:");
                int newStock = scanner.nextInt();
                stock.setStockQuantityById(idToAdd,newStock);
                stock.guardarStock(shop.getStockDbName());
                return null;
            case 8:
                showAllTickets(shop);
                return null;

            case 9:
                Stock.getInstance();
                Stock.showStockWithQuantities(shop);
                return null;

            case 10:
                System.out.println("Create new florist.");
                System.out.print("Enter the name of the florist: ");
                String newFlowerShopName = scanner.nextLine();
                ShopManager shopManager = ShopManager.getInstance();
                shopManager.crearNuevaFloristeria(newFlowerShopName);
                return null;


            case 11:
                Stock.showStockWithQuantities(shop);
                createTicket(shop, scanner, Catalogue.getInstance());
                return null;


            case 12:
                showTotalSales(shop);
                return null;

            case 0:
                System.out.println("Exiting the program.");
                catalog.displayCatalog();
                return null;

            default:
                System.out.println("Invalid option.");
                return null;
        }
    }

    private static Tree createTree(Catalogue catalog,   Shop shop, Scanner scanner) throws IOException {
        System.out.println("Enter the name of the tree:");
        String treeName = scanner.nextLine();
        System.out.println("Enter the height of the tree:");
        String treeHeight = scanner.nextLine();
        System.out.println("Enter the price of the tree:");
        double treePrice = scanner.nextDouble();
        Tree tree = new Tree(treeName, treeHeight, treePrice);
        catalog.addItem(tree);
        catalog.guardarCatalogo(shop.getCatalogueDbName());

        Stock stockTree = Stock.getInstance();
        System.out.println("Do you want to add stock? (Y/N)");
        char choice1 = scanner.next().charAt(0);
        if (choice1 == 'Y' || choice1 == 'y') {
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();
            StockItem stockItem = new StockItem(tree.getId(), quantity);
            stockTree.addStockItem(stockItem);
            System.out.println("Stock created.");
        } else if (choice1 == 'N' || choice1 == 'n') {
            Stock.createProductWithZeroQuantity(tree.getId());
        }
        stockTree.guardarStock(shop.getStockDbName());
        return tree;
    }

    private static Flower createFlower(Catalogue catalog,  Shop shop, Scanner scanner) throws IOException {
        System.out.println("Enter the name of the flower:");
        String flowerName = scanner.nextLine();
        System.out.println("Enter the color of the flower:");
        String flowerColor = scanner.nextLine();
        System.out.println("Enter the price of the flower:");
        double flowerPrice = scanner.nextDouble();
        Flower flower = new Flower(flowerName, flowerColor, flowerPrice);
        catalog.addItem(flower);
        catalog.guardarCatalogo(shop.getCatalogueDbName());

        Stock stockFlower = Stock.getInstance();
        System.out.println("Do you want to add stock? (Y/N)");
        char choice2 = scanner.next().charAt(0);
        if (choice2 == 'Y' || choice2 == 'y') {
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();
            StockItem stockItem = new StockItem(flower.getId(), quantity);
            stockFlower.addStockItem(stockItem);
            System.out.println("Stock created.");
        } else if (choice2 == 'N' || choice2 == 'n') {
            Stock.createProductWithZeroQuantity(flower.getId());
        }
        stockFlower.guardarStock(shop.getStockDbName());
        return flower;
    }

    private static Decoration createDecoration(Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
        System.out.println("Enter the name of the decoration:");
        String decorationName = scanner.nextLine();
        System.out.println("Enter the decoration material:");
        String decorationMaterial = scanner.nextLine();
        System.out.println("Enter the price of the decoration:");
        double decoPrice = scanner.nextDouble();
        Decoration decoration = new Decoration(decorationName, decorationMaterial, decoPrice);
        catalog.addItem(decoration);
        catalog.guardarCatalogo(shop.getCatalogueDbName());

        Stock stockDeco = Stock.getInstance();
        System.out.println("Do you want to add stock? (Y/N)");
        char choice3 = scanner.next().charAt(0);
        if (choice3 == 'Y' || choice3 == 'y') {
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();
            StockItem stockItem = new StockItem(decoration.getId(), quantity);
            stockDeco.addStockItem(stockItem);
            System.out.println("Stock created.");
        } else if (choice3 == 'N' || choice3 == 'n') {
            Stock.createProductWithZeroQuantity(decoration.getId());
        }
        stockDeco.guardarStock(shop.getStockDbName());
        return decoration;
    }

    private static void createTicket(Shop shop, Scanner scanner, Catalogue catalogo) throws IOException {
        TicketManager ticketManager = TicketManager.getInstance();
        List<Ticket> tickets = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        do {
            System.out.println("Enter the product ID (0 to finish adding products):");
            int productId = scanner.nextInt();

            if (productId == 0) {
                break;
            }

            scanner.nextLine();
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();

            Product product = catalogo.getProductById(productId);

            if (product != null) {
                int availableStock = Stock.getInstance().getItems().getOrDefault(productId, 0);

                if (availableStock >= quantity) {
                    String productName = product.getName();
                    Ticket ticket = new Ticket(productId, productName, quantity, currentDate);
                    tickets.add(ticket);
                    System.out.println("Product added to the ticket.");

                    Stock.getInstance().reduceStock(product, quantity);
                } else {
                    System.out.println("Error: Insufficient stock for Product ID '" + productId + "'. Available stock: " + availableStock);
                }
            } else {
                System.out.println("Product ID '" + productId + "' not found in the catalogue.");
            }
        } while (true);

        if (!tickets.isEmpty()) {
            tickets.forEach(ticketManager::addTicket);
            System.out.println("Ticket created successfully:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
                ticketManager.guardarTicket(shop.getTicketDbName());
            }
        } else {
            System.out.println("No products added to the ticket.");
        }
    }


    private static void showAllTickets(Shop shop) {
        TicketManager ticketManager = TicketManager.getInstance();

        try {
            ticketManager.leerTicket(shop.getTicketDbName());
        } catch (IOException e) {
            System.out.println("Error reading tickets: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        List<Ticket> tickets = ticketManager.getTickets();

        if (tickets.isEmpty()) {
            System.out.println("There are no tickets available.");
        } else {
            System.out.println("\n--- All Tickets ---");
            for (Ticket ticket : tickets) {
                System.out.println("Ticket ID: " + ticket.getTicketId());
                System.out.println("Product ID: " + ticket.getProductId());
                System.out.println("Product name: " + ticket.getProductName());
                System.out.println("Ticket Date: " + ticket.getTicketDate());
                System.out.println("Quantity: " + ticket.getQuantity());
                System.out.println("Total Price: " + ticket.getTotalPrice());
                System.out.println("------------------------");
            }
        }
    }

    private static void showTotalSales(Shop shop) {
        System.out.println("Calculating total sales: ");

        TicketManager ticketManager = TicketManager.getInstance();

        try {
            ticketManager.leerTicket(shop.getTicketDbName());
        } catch (IOException e) {
            System.out.println("Error reading tickets: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        List<Ticket> tickets = ticketManager.getTickets();

        if (tickets.isEmpty()) {
            System.out.println("There are no tickets available.");
        } else {
            double totalSales = 0.0;

            for (Ticket ticket : tickets) {
                totalSales += ticket.getTotalPrice();
            }
            System.out.println("Total sales: " + totalSales);
        }
    }
}




