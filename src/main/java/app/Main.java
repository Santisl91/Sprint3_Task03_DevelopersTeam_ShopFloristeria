package app;

import entities.Catalogue;
import entities.Product;
import entities.ProductItemFactory;
import entities.Ticket;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Catalogue catalogo = new Catalogue();
        Ticket ticket = new Ticket(1, 0);

        try {
            Scanner scanner = new Scanner(System.in);
            catalogo.leerBd();
            ticket.leerBd();

            int eleccion;
            do {
                System.out.println("Ingrese el tipo de artículo a agregar (1: Árbol, 2: Flor, 3: Decoración, 0: Salir):");
                eleccion = scanner.nextInt();
                scanner.nextLine();

                if (eleccion == 0) {
                    System.out.println("Saliendo del programa");
                    break;
                }

                Product nuevoItem = ProductItemFactory.createCatalogItem(eleccion, catalogo);
                if (nuevoItem != null) {
                    catalogo.addItem(nuevoItem);
                    System.out.println("Se agregó el artículo al catálogo.");
                } else {
                    System.out.println("No se pudo agregar el artículo al catálogo.");
                }
            } while (true);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            ticket.guardarBd();
            catalogo.guardarBd();
        }
    }
}
