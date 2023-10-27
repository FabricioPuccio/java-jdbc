package com.tienda.persistencia;

import com.tienda.entidades.Fabricante;

import java.sql.SQLException;

public final class FabricanteDao extends DAO {

    public void guardarFabricante(Fabricante fabricante) throws Exception {

        if (fabricante == null) {
            throw new Exception("Debe indicar un Fabricante");
        }

        String sql = "INSERT INTO fabricante (nombre) VALUES('" + fabricante.getNombre() + "');";
        try {
            super.insertarModificarEliminar(sql);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        }

    }

    public Fabricante findById(int codigo) throws SQLException, ClassNotFoundException {

        String sql = "select * from fabricante where codigo = " + codigo + " ;";

        try {
            super.consultarBase(sql);

            Fabricante fabricante = null;

            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt("codigo"));
                fabricante.setNombre(resultado.getString("nombre"));
            }
            
            super.desconectarBase();
            return fabricante;
        } catch (SQLException | ClassNotFoundException e) {
            super.desconectarBase();
            throw e;
        }

    }
}
