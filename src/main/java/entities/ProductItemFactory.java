package entities;

import app.Shop;
import entities.Stock;
import java.io.IOException;
import java.util.Scanner;


public class ProductItemFactory {
    public static Product createCatalogItem(int itemType, Catalogue catalog, Shop shop) throws IOException {
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
                    catalog.guardarBd();
                    Stock stockFlower = shop.encontrarStock(flower);
                    if (stockFlower == null) {
                        stockFlower = Stock.getInstance();
                        System.out.println("Stock creado para la flor. ¿Desea agregar cantidad? (S/N)");
                        char choice = scanner.next().charAt(0);
                        if (choice == 'S' || choice == 's') {
                            System.out.println("Ingrese la cantidad:");
                            int quantity = scanner.nextInt();
                            stockFlower.addStockItem(flower, quantity);
                            stockFlower.guardarBd();
                            System.out.println("Stock creado.");
                        }
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
                    catalog.guardarBd();
                    Stock stockDeco = shop.encontrarStock(decoration);
                    if (stockDeco == null) {
                        stockDeco = Stock.getInstance();
                        System.out.println("Stock creado para la decoración. ¿Desea agregar cantidad? (S/N)");
                        char choice = scanner.next().charAt(0);
                        if (choice == 'S' || choice == 's') {
                            System.out.println("Ingrese la cantidad:");
                            int quantity = scanner.nextInt();
                            stockDeco.addStockItem(decoration, quantity);
                            stockDeco.guardarBd();
                            System.out.println("Stock creado.");
                        }
                    } else {
                        System.out.println("Error: Stock no encontrado para el árbol.");
                    }

                    return decoration;

                case 4:
                    System.out.println("Ingrese el nombre del árbol a retirar:");
                    String treeToRemove = scanner.nextLine();
                    catalog.removeProductByName(treeToRemove);
                    return null;

                case 5:
                    System.out.println("Ingrese el nombre de la flor a retirar:");
                    String flowerToRemove = scanner.nextLine();
                    catalog.removeProductByName(flowerToRemove);
                    return null;

                case 6:
                    System.out.println("Ingrese el nombre de la decoración a retirar:");
                    String decorationToRemove = scanner.nextLine();
                    catalog.removeProductByName(decorationToRemove);
                    return null;

                case 7:
                    // Ver todos los tickets
                    for (Ticket ticket : shop.getTickets()) {
                        System.out.println(ticket);
                    }
                    return null;

                case 8://TODO: Ver stock con cantidades de productos de clase Shop

                case 9:
                    // Crear floristería
                    System.out.println("Ya se creó la floristería.");
                    return null;

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

