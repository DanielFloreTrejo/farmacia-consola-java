package com.dflorestrejo.pharmacysystem.controller;

import com.dflorestrejo.pharmacysystem.entity.User;
import com.dflorestrejo.pharmacysystem.enums.EmployeeRole;

import java.util.Scanner;

public class MenuController {

    private final AuthController authController;
    private final ProductController productController;
    private final Scanner scanner;

    public MenuController(AuthController authController, ProductController productController, Scanner scanner) {
        this.authController = authController;
        this.productController = productController;
        this.scanner = scanner;
    }

    public void iniciar() {

        boolean corriendo = true;

        while (corriendo) {
            System.out.println("\n============================");
            System.out.println("     FARMACIA SISTEMA");
            System.out.println("============================");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {
                    User user = authController.login();
                    if (user != null) {
                        // cuando tengas el menú principal post-login va acá
                        if (user.getEmployeeRole() != EmployeeRole.ADMIN) {
                            generaldMenu();
                        } else {
                            adminMenu();
                        }
                    }
                }
                case "2" -> authController.register();
                case "0" -> corriendo = false;
                default -> System.out.println("Opción inválida, intentá de nuevo.");
            }
        }

        System.out.println("Cerrando sistema...");
    }

    public void adminMenu() {
        System.out.println("1. Gestionar producto");
        System.out.println("2. Gestionar stock");
        System.out.println("3. Compras a proveedores");
        System.out.println("4. Gestion de usuario");
        System.out.println("5. Ver ventas");
        System.out.println("6. Reportes");
        System.out.println("0. Cerrar sesión");
        System.out.print("Opción: ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "1":
                // todo sin implementar
                break;
            case "2":
                // todo sin implementar
                break;
            case "3":
                // todo sin implementar
                break;
            case "4":
                // todo sin implementar
                break;
            case "5":
                // todo sin implementar
                break;
            case "6":
                // todo sin implementar
                break;
        }
    }

    public void generaldMenu() {
        System.out.println("======= CAJA =======");
        System.out.println("1. Nueva venta");
        System.out.println("2. Buscar producto");
        System.out.println("3. Cobrar venta");
        System.out.println("4. Generar factura");
        System.out.println("5. Historial de ventas");
        System.out.println("0. Cerrar sesión");
        System.out.print("Opción: ");
    }
}
