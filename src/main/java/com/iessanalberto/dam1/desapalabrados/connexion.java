package com.iessanalberto.dam1.desapalabrados;

import java.sql.Connection;
import java.sql.DriverManager;

public class connexion {
    public class ConnectionBasicSupabase {
        private static final String URL = "jdbc:postgresql://db.erngodjaxfwybscotiqz.supabase.co:5432/postgres";
        private static final String USERNAME = "postgres";
        private static final String PASSWORD = "dnnel122002."; // Contraseña que se creó en la base de datos.

        public static Connection getConnection() throws Exception {
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
    }
}
