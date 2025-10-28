package com.example.backend.utils;

import com.example.backend.entities.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class CreationUser {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static boolean creerUser(String email, String mdp) throws Exception {
        ArrayList<Users> users = new ArrayList<>();
        Users users1 = new Users();
        users1.setEmail(email);
        String hache = encoder.encode(mdp);
        users1.setPassword(hache);
        users.add(users1);

        if (verifExiste(email)) {
            userSetBd(users1.getEmail(), users1.getPassword());
            return true;
        } else {
            System.out.println("utiliser deja ");
            return false;
        }
    }

    public static void userSetBd(String email, String mdp) throws Exception {
        Connection con = null;
        Properties props = new Properties();
        PreparedStatement prtm = null;
        props = Connexion.getProps("application.properties");
        con = DriverManager.getConnection(props.getProperty("spring.datasource.url"),
                props.getProperty("spring.datasource.username"),
                props.getProperty("spring.datasource.password"));
        try {
            String sql = "INSERT INTO users(email,password) VALUES (?,?)";
            prtm = con.prepareStatement(sql);
            prtm.setString(1, email);
            prtm.setString(2, mdp);
            prtm.executeUpdate();
        } finally {
            con.close();
            prtm.close();
        }
    }

    public static String login(String email, String mdp) throws Exception {
        Connection con = null;
        Properties props = new Properties();
        PreparedStatement prtm = null;
        ArrayList<Users> users = new ArrayList<>();
        props = Connexion.getProps("application.properties");
        con = DriverManager.getConnection(props.getProperty("spring.datasource.url"),
                props.getProperty("spring.datasource.username"),
                props.getProperty("spring.datasource.password"));
        String sql = "SELECT * FROM users WHERE email = ?";
        prtm = con.prepareStatement(sql);
        prtm.setString(1, email);
        ResultSet rs = prtm.executeQuery();

        while (rs.next()) {
            Users users1 = new Users();
            users1.setId(rs.getInt(1));
            users1.setEmail(rs.getString(2));
            users1.setPassword(rs.getString(3));
            users.add(users1);
        }

        con.close();
        prtm.close();

        if (users.isEmpty()) return null;

        Users usersVerif = users.get(0);
        if (encoder.matches(mdp, usersVerif.getPassword())) {
            return JwtUtil.creerToken(email);
        } else {
            return null;
        }
    }


    public static boolean verifExiste(String email) throws Exception {
        Connection con = null;
        Properties props = new Properties();
        PreparedStatement prtm = null;
        ArrayList<Users> users = new ArrayList<>();
        props = Connexion.getProps("application.properties");
        con = DriverManager.getConnection(props.getProperty("spring.datasource.url"),
                props.getProperty("spring.datasource.username"),
                props.getProperty("spring.datasource.password"));
        String sql = "SELECT * FROM users WHERE email = ?";
        prtm = con.prepareStatement(sql);
        prtm.setString(1, email);
        ResultSet rs = prtm.executeQuery();

        while (rs.next()) {
            Users users1 = new Users();
            users1.setId(rs.getInt(1));
            users1.setEmail(rs.getString(2));
            users1.setPassword(rs.getString(3));
            users.add(users1);
        }
        con.close();
        prtm.close();

        return users.isEmpty();
    }


}
