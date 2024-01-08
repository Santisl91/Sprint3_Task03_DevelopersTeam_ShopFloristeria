package entities;

import app.Shop;

import java.io.IOException;
import java.util.Scanner;


public class ProductItemFactory {
    public static Object createCatalogItem(int itemType, Catalogue catalog, Shop shop) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (itemType) {
                case 1:
                    System.out.println("Ingrese el nombre del árbol:");
                    String treeName = scanner.nextLine();
                    System.out.println("Ingrese la altura del árbol:");
                    String treeHeight = scanner.nextLine();
                    System.out.println("Ingrese el precio del árbol:");
                    double treePrice = scanner.nextDouble();
                    Tree tree = new Tree(treeName, treeHeight, treePrice);
                    catalog.addItem(tree);
                    catalog.guardarBd();
                    Stock stockTree = shop.encontrarStock(tree);
                    if (stockTree == null) {
                        stockTree = Stock.getInstance();
                        System.out.println("Stock creado para el árbol. ¿Desea agregar cantidad? (S/N)");
                        char choice = scanner.next().charAt(0);
                        if (choice == 'S' || choice == 's') {
                            System.out.println("Ingrese la cantidad:");
                            int quantity = scanner.nextInt();
                            stockTree.addStockItem(tree, quantity);
                            stockTree.guardarBd();
                            System.out.println("Stock creado.");
                        }
                    } else {
                        System.out.println("Stock creado.");
                    }

                    return tree;

                case 2:
                    System.out.println("Ingrese el nombre de la flor:");
                    String flowerName = scanner.nextLine();
                    System.out.println("Ingrese el color de la flor:");
                    String flowerColor = scanner.nextLine();
                    System.out.println("Ingrese el precio de la flor:");
                    double flowerPrice = scanner.nextDouble();
                    Flower flower = new Flower(flowerName, flowerColor, flowerPrice);
                    catalog.addItem(flower);
                    Stock stockFlower = shop.encontrarStock(flower);
                    if (stockFlower != null) {
                        stockFlower.addStockItem(flower, 0); // 0 es la cantidad inicial
                    } else {
                        System.out.println("Error: Stock no encontrado para el árbol.");
                    }

                    return flower;

                case 3:
                    System.out.println("Ingrese el nombre de la decoración:");
                    String decorationName = scanner.nextLine();
                    System.out.println("Ingrese el material de la decoración:");
                    String decorationMaterial = scanner.nextLine();
                    System.out.println("Ingrese el precio de la decoración:");
                    double decoPrice = scanner.nextDouble();
                    Decoration decoration = new Decoration(decorationName, decorationMaterial, decoPrice);
                    catalog.addItem(decoration);
                    Stock stockDeco = shop.encontrarStock(decoration);
                    if (stockDeco != null) {
                        stockDeco.addStockItem(decoration, 0); // 0 es la cantidad inicial
                    } else {
                        System.out.println("Error: Stock no encontrado para el árbol.");
                    }

                    return decoration;

                case 4:
                    catalog.displayCatalog();
                    System.out.println("Ingrese el ID del árbol a retirar:");
                    int treeToRemove = scanner.nextInt();
                    catalog.removeProductById(treeToRemove);
                    return null;

                case 5:
                    catalog.displayCatalog();
                    System.out.println("Ingrese el ID de la flor a retirar:");
                    int flowerToRemove = scanner.nextInt();
                    catalog.removeProductById(flowerToRemove);
                    return null;

                case 6:
                    catalog.displayCatalog();
                    System.out.println("Ingrese el ID de la decoración a retirar:");
                    int decorationToRemove = scanner.nextInt();
                    catalog.removeProductById(decorationToRemove);
                    return null;

                case 7:
                    // Ver todos los tickets
                    for (Ticket ticket : shop.getTickets()) {
                        System.out.println(ticket);
                    }
                    return null;

                case 8:
                   shop.getStockProducts();
                    break;

                case 9:
                    // Crear floristería
                    System.out.println("Ya se creó la floristería.");
                    return null;

                case 10:
                    int newTicketId = shop.getNextTicketId();
                    Ticket newTicket = new Ticket(newTicketId, 0, 0.0);
                    shop.addTicket(newTicket);

                    System.out.println("Ingrese el ID del producto a agregar al ticket:");
                    catalog.displayCatalog();
                    int productIdToAdd = scanner.nextInt();

                    Product productToAdd = shop.getProductById(productIdToAdd);
                    Stock stock = shop.encontrarStock(productToAdd);

                    if (productToAdd != null && stock != null) {
                        newTicket.addProductById(productIdToAdd, 0, shop);

                        System.out.println("Ingrese la cantidad del producto:");
                        int quantityToAdd = scanner.nextInt();

                        stock.reduceStock(productToAdd, quantityToAdd);

                        newTicket.updateTotalPrice();

                        newTicket.guardarBd();

                        System.out.println("Producto agregado al ticket y stock actualizado.");
                    } else {
                        System.out.println("Error: Producto no encontrado en el stock.");
                    }

                    return newTicket;



                case 0:
                    System.out.println("Saliendo del programa.");
                    catalog.displayCatalog();
                    return null;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}

