package com.dflorestrejo.pharmacysystem.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private final HikariDataSource dataSource;  // ← ya no es una Connection directa

    private DatabaseConnection() {
        PropertiesLoader props = new PropertiesLoader("db.properties");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(props.get("db.url"));
        config.setUsername(props.get("db.user"));
        config.setPassword(props.get("db.password"));

        // Tamaño del pool — para consola con estos valores alcanza
        config.setMaximumPoolSize(5);       // máximo 5 conexiones abiertas
        config.setMinimumIdle(2);           // mínimo 2 siempre listas
        config.setConnectionTimeout(30000); // espera máx 30seg para obtener una
        config.setIdleTimeout(600000);      // cierra conexiones ociosas a los 10min
        config.setPoolName("FarmaciaPool");

        this.dataSource = new HikariDataSource(config);
        System.out.println("Pool de conexiones iniciado");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Ahora devuelve una conexión del pool en lugar de siempre la misma
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Para cerrar todo el pool al terminar la app
    public void close() {
        dataSource.close();
    }

}
