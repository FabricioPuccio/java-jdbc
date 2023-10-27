package com.tienda.persistencia;

import com.tienda.entidades.Fabricante;

import java.sql.SQLException;

public final class FabricanteDao extends DAO {

    public void guardarFabricante(Fabricante fabricante) throws Exception {

        if (fabricante == null) {
            throw new Exception("Debe indicar un Fabricante");
        }

        String sql = "INSERT INTO fabricante VALUES('" + fabricante.getNombre() + "');";
        try {
            super.insertarModificarEliminar(sql);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        }

    }
}
