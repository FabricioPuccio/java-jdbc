package com.tienda.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public abstract class DAO {

    protected Connection conexion = null;
    protected Statement sentencia = null;
    protected ResultSet resultado = null;

    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void conectarBase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jbdc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    protected void desconectarBase() throws SQLException {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al intentar desconectar base", ex);
        }

    }

    protected void insertarModificarEliminar(String query) throws ClassNotFoundException, SQLException {

        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(query);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    protected void consultarBase(String query) throws SQLException, ClassNotFoundException {

        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(query);
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }
}
