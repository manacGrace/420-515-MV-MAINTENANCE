package com.example.backend.utils;

import java.io.InputStream;
import java.util.Properties;

public class Connexion {
    public static Properties getProps(String fileName) throws Exception {
        //trouver sur internet car base de donner voulait pas marcher
        Properties props = new Properties();
        try (InputStream input = Connexion.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new Exception("file pas trouver : " + fileName);
            }
            props.load(input);
        }
        return props;
    }
}
