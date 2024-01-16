package entities;

import app.Shop;
import persistence.TicketDb;

import java.io.IOException;
import java.util.*;

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
                return null;

            case 5:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the flower to collect:");
                int flowerToRemove = scanner.nextInt();
                catalog.removeProductById(flowerToRemove);
                return null;

            case 6:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the decoration to be removed:");
                int decorationToRemove = scanner.nextInt();
                catalog.removeProductById(decorationToRemove);
                return null;

            case 7:
                showAllTickets(shop);
                return null;

            case 8:
                Stock.getInstance().showStockWithQuantities();
                return null;

            case 9:
                System.out.println("Create new florist.");
                System.out.print("Enter the name of the florist: ");
                String newFlowerShopName = scanner.nextLine();
                ShopManager shopManager = ShopManager.getInstance();
                shopManager.crearNuevaFloristeria(newFlowerShopName);
                return null;


            case 10:
                showStockWithQuantities(shop);
                construirTicket(shop, scanner, Catalogue.getInstance());
                return null;


            case 11:
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

    private static Tree createTree(Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
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
            stockTree.guardarStock(shop.getStockDbName());
            System.out.println("Stock created.");
        }
        return tree;
    }

    private static Flower createFlower(Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
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
            stockFlower.guardarStock(shop.getStockDbName());
            System.out.println("Stock created.");
        }
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
            stockDeco.guardarStock(shop.getStockDbName());
            System.out.println("Stock created.");
        }
        return decoration;
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
    private static void createTicket(Shop shop, Scanner scanner, Catalogue catalogo) throws IOException {
        List<Ticket> tickets = new ArrayList<>();

        ProductItemFactory.showStockWithQuantities(shop);

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
                String productName = product.getName();
                Ticket ticket = new Ticket(productId, productName, quantity);
                tickets.add(ticket);
                System.out.println("Product added to the ticket.");
            } else {
                System.out.println("Product ID '" + productId + "' not found in the catalogue.");
            }
        } while (true);

        if (!tickets.isEmpty()) {
            TicketManager ticketManager = TicketManager.getInstance();
            tickets.forEach(ticketManager::addTicket);

            System.out.println("Ticket created successfully:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
            ticketManager.guardarTicket(shop.getTicketDbName());
        } else {
            System.out.println("No products added to the ticket.");
        }
    }

    public static void construirTicket(Shop shop, Scanner scanner, Catalogue catalogo) throws IOException {
        // Crear lista para almacenar la información de productos
        List<ProductInfo> productInfoList = new ArrayList<>();
        TicketManager tM = TicketManager.getInstance();
        // Solicitar al usuario la información del producto
        while (true) {
            System.out.print("Ingrese el ID del producto: ");
            int productId = scanner.nextInt();

            // Buscar el producto por el ID en el catálogo
            Product product = catalogo.getProductById(productId);

            if (product != null) {
                String productName = product.getName();
                System.out.print("Ingrese la cantidad para '" + productName + "': ");
                int quantity = scanner.nextInt();

                // Crear instancia de ProductInfo y agregarla a la lista
                ProductInfo productInfo = new ProductInfo(product, quantity);
                productInfoList.add(productInfo);

                // Preguntar al usuario si desea seguir agregando productos
                System.out.print("¿Desea agregar otro producto? (s/n): ");
                char respuesta = scanner.next().charAt(0);
                if (respuesta == 'n' || respuesta == 'N') {
                    break;  // Salir del bucle si la respuesta es 'n' o 'N'
                }
            } else {
                System.out.println("ID de producto no válido. Inténtelo de nuevo.");
            }
        }
        TicketPrueba ticket = new TicketPrueba(productInfoList);
        tM.addTicketPrueba(ticket);
        tM.guardarTicket(shop.getTicketDbName());
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

