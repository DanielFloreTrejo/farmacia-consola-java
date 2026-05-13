package com.dflorestrejo.pharmacysystem.controller;

import com.dflorestrejo.pharmacysystem.entity.User;
import com.dflorestrejo.pharmacysystem.enums.EmployeeRole;
import com.dflorestrejo.pharmacysystem.service.UserService;

import java.util.Arrays;
import java.util.Scanner;

public class AuthController {

    private final UserService userService;
    private final Scanner scanner;

    public AuthController(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;

    }

    public User login() {


        System.out.println("\n=== INICIAR SESIÓN ===");

        System.out.print("Usuario: ");
        String username = scanner.nextLine().trim();

       System.out.print("Contraseña: ");
       String password = scanner.nextLine().trim();


        try {
            User user = userService.login(username, password);
            System.out.println("Bienvenido, " + user.getUsername() +
                    " [" + user.getEmployeeRole() + "]");
            return user;
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // null indica que el login falló
        }
    }

    public void register() {

        System.out.println("\n=== REGISTRAR USUARIO ===");

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine().trim();

        System.out.println("Roles disponibles: ");
        EmployeeRole[] roles = EmployeeRole.values();
        for (int i = 0; i < roles.length; i++) {
            System.out.println("  " + (i + 1) + ". " + roles[i]);
        }
        System.out.print("Elegí un rol (1-" + roles.length + "): ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            EmployeeRole rol = roles[opcion - 1];

            User user = userService.register(username, password, rol);
            System.out.println("Usuario registrado con id: " + user.getId());

        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
