package app;

import entities.Catalogue;
import entities.Product;
import entities.ProductItemFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Catalogue catalogo = new Catalogue();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Ingrese el tipo de artículo a agregar (1: Árbol, 2: Flor, 3: Decoración, 0: Salir):");
            int eleccion = scanner.nextInt();
            scanner.nextLine();

            Product nuevoItem = ProductItemFactory.createCatalogItem(eleccion, catalogo);

            if (nuevoItem == null) {
                System.out.println("Programa cerrado.");
                break;
            }

        } while (true);
    }
}
