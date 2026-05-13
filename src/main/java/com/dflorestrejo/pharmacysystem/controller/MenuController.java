package com.dflorestrejo.pharmacysystem.controller;

import com.dflorestrejo.pharmacysystem.entity.User;
import com.dflorestrejo.pharmacysystem.enums.EmployeeRole;

import java.util.Scanner;

public class MenuController {

    private final AuthController authController;
    private final Scanner scanner;

    public MenuController(AuthController authController, Scanner scanner) {
        this.authController = authController;
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
                            System.out.println("1. Buscar producto");
                            System.out.println("2. Vender Producto");
                            System.out.println("0. Salir");
                            System.out.print("Opción: ");
                        }
                        System.out.println("Login exitoso — menú principal próximamente");
                    }
                }
                case "2" -> authController.register();
                case "0" -> corriendo = false;
                default  -> System.out.println("Opción inválida, intentá de nuevo.");
            }
        }

        System.out.println("Cerrando sistema...");
    }
}
