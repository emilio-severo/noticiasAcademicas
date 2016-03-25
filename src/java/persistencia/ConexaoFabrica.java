/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author emilio
 */
public final class ConexaoFabrica {

    private static final String bd = "noticias";
    private static final String url = "jdbc:postgresql://localhost/";
    private static final String usr = "postgres";
    private static final String pwd = "p0stgr&s";
    private static Connection con;

    private ConexaoFabrica() {

    }

    public static final Connection getConexao() {
        try {
            if (con == null) {
                DriverManager.registerDriver(new org.postgresql.Driver());
                con = DriverManager.getConnection(url + bd, usr, pwd);
            } else if (con.isClosed()) {
                con = DriverManager.getConnection(url + bd, usr, pwd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

}
