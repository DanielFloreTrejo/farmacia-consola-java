package com.dflorestrejo.pharmacysystem;

import com.dflorestrejo.pharmacysystem.config.DatabaseConnection;
import com.dflorestrejo.pharmacysystem.controller.AuthController;
import com.dflorestrejo.pharmacysystem.controller.MenuController;
import com.dflorestrejo.pharmacysystem.controller.ProductController;
import com.dflorestrejo.pharmacysystem.repository.ProductRepository;
import com.dflorestrejo.pharmacysystem.repository.ProductRepositoryImpl;
import com.dflorestrejo.pharmacysystem.repository.UserRepository;
import com.dflorestrejo.pharmacysystem.repository.UserRepositoryImpl;
import com.dflorestrejo.pharmacysystem.security.BcryptPasswordEncoder;
import com.dflorestrejo.pharmacysystem.security.PasswordEncoder;
import com.dflorestrejo.pharmacysystem.service.ProductService;
import com.dflorestrejo.pharmacysystem.service.ProductServiceImpl;
import com.dflorestrejo.pharmacysystem.service.UserService;
import com.dflorestrejo.pharmacysystem.service.UserServiceImpl;

import java.util.Scanner;

public class PharmacysystemApplication {
    public static void main(String[] args) {

        // 1. Scanner — uno solo para toda la app
        Scanner scanner = new Scanner(System.in);


        // 2. Capas de abajo hacia arriba
        PasswordEncoder passwordEncoder = new BcryptPasswordEncoder();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository, passwordEncoder);
        ProductRepository productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductServiceImpl();

        // 3. Controllers
        AuthController authController = new AuthController(userService, scanner);
        ProductController productController = new ProductController(productService, scanner);
        MenuController menuController = new MenuController(authController, productController, scanner);

        // 4. Arrancar
        try {
            menuController.iniciar();
        } finally {
            // Siempre cierra el pool y el scanner al salir
            DatabaseConnection.getInstance().close();
            scanner.close();
        }
    }
}
