package com.dflorestrejo.pharmacysystem.controller;

import com.dflorestrejo.pharmacysystem.entity.Category;
import com.dflorestrejo.pharmacysystem.entity.Product;
import com.dflorestrejo.pharmacysystem.service.CategoryService;
import com.dflorestrejo.pharmacysystem.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final Scanner scanner;

    public ProductController(ProductService productService, CategoryService categoryService, Scanner scanner) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.scanner = scanner;
    }

    public void menu() {
        boolean corriendo = true;

        while (corriendo) {
            System.out.println("1. Agregar nuevo producto");
            System.out.println("2. Modificar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Ver producto");
            System.out.println("0. Volver atrás");
            System.out.print("Opción: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> add();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> read();
                case "0" -> corriendo = false;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void add() {
        List<Category> categories = categoryService.findAll();

        System.out.println("\n--- AGREGAR PRODUCTO ---");

        System.out.println("Ingrese el nombre del producto: ");
        String name = scanner.nextLine().trim();

        System.out.println("Ingrese la descripción del producto: ");
        String description = scanner.nextLine().trim();

        System.out.println("\nCategorías disponibles:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("Elegí una categoría (1-" + categories.size() + "): ");
        int option = Integer.parseInt(scanner.nextLine().trim());
        Category category = categories.get(option - 1); // ← cargamos el objeto completo

        System.out.println("Ingrese el laboratorio: ");
        String laboratory = scanner.nextLine().trim();

        System.out.println("Ingrese la presentación del producto: ");
        String presentation = scanner.nextLine().trim();

        System.out.println("Ingrese el stock del producto: ");
        int currentStock = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Ingrese el stock mínimo para avisar: ");
        int minimumStock = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Ingrese el precio de compra del producto: $");
        BigDecimal purchasePrice = new BigDecimal(scanner.nextLine().trim());

        System.out.println("Ingrese el precio de venta del producto: $");
        BigDecimal salePrice = new BigDecimal(scanner.nextLine().trim());

        System.out.println("Ingrese la fecha de vencimiento del producto (yyyy-mm-dd): ");
        LocalDate expirationDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("Ingrese el código de barra del producto: ");
        String barCode = scanner.nextLine().trim();

        System.out.println("Requiere receta medica el producto (s/n): ");
        boolean requiresPrescription = scanner.nextLine().trim().equalsIgnoreCase("s");

        try {
            Product product = new Product(name, description, category, laboratory, presentation, currentStock,
                    minimumStock, purchasePrice, salePrice, expirationDate, barCode, requiresPrescription, true);

            Product saved = productService.add(product);
            System.out.println("Producto agregado con id: " + saved.getId());

        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private void update() {

        read(); // ← Mostramos un listado de productos

        System.out.println("Ingrese el id del producto a modificar: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Product product = productService.findById(id).orElseThrow(()-> new RuntimeException("Ingrese un id existente"));

        List<Product> listProduct = productService.findById(id).stream().toList();

        System.out.printf("%-5s %-30s %-15s %-10s %-10s%n",
                "ID", "NOMBRE", "LABORATORIO", "P.VENTA", "STOCK");
        System.out.println("-".repeat(75));

        System.out.printf("%-5d %-30s %-15s %-10s %-10d%n",product.getId(),product.getName(), product.getLaboratory(),
                product.getSalePrice(), product.getCurrentStock());

        System.out.println("Ingrese lo que desea modificar");
        System.out.println(" 1. Nombre");
        System.out.println(" 2. Descripción");
        System.out.println(" 3. Categoria");
        System.out.println(" 4. Laboratorio");
        System.out.println(" 5. Presentación");
        System.out.println(" 6. Stock");
        System.out.println(" 7. Stock mínimo");
        System.out.println(" 8. Precio de compra");
        System.out.println(" 9. Precio de venta");
        System.out.println(" 10. Fecha de vencimiento");
        System.out.println(" 11. Código de barra");
        System.out.println(" 12. Requiere receta medica");
        System.out.println(" 13. ????");
        System.out.println(" 0. Salir");


    }

    private void delete() {

    }

    private void read() {
        List<Product> products = productService.findAll();

        if (products.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\n--- LISTADO DE PRODUCTOS ---");

        System.out.printf("%-5s %-30s %-15s %-10s %-10s%n",
                "ID", "NOMBRE", "LABORATORIO", "P.VENTA", "STOCK");
        System.out.println("-".repeat(75));

        for (Product p : products) {
            System.out.printf("%-5d %-30s %-15s %-10s %-10d%n",
                    p.getId(),
                    p.getName(),
                    p.getLaboratory(),
                    p.getSalePrice(),
                    p.getCurrentStock()
            );
        }
    }
}
