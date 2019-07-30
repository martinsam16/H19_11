package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    protected static Connection conexion = null;

    public Connection conectar() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(
                    "jdbc:sqlserver://servermartin.database.windows.net;database=CompuTech;",
                    "malditoidealismo",
                    "Nadieseacuerda12"
            );
//            conexion = DriverManager.getConnection(
//                    "jdbc:sqlserver://localhost;database=CompuTech;",
//                    "administrador",
//                    "Administrador123"
//            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public void desconectar() throws Exception {
        try {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
