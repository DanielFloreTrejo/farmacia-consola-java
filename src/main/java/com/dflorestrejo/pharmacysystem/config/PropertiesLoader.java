package com.dflorestrejo.pharmacysystem.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private final Properties properties = new Properties();

    public PropertiesLoader(String fileName) {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo: " + fileName);
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Error al leer " + fileName + ": " + e.getMessage());
        }
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Clave no encontrada en properties: " + key);
        }
        return value;
    }
}
