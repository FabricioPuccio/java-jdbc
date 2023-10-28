package com.tienda.persistencia;

import com.tienda.entidades.Producto;

import java.sql.SQLException;

public final class ProductoDao extends DAO {

    public void guardarProducto(Producto producto) throws Exception {

        if (producto == null) {
            throw new Exception("Es necesario indicar un producto para guardarlo en la BD.");
        }
        String sql = "INSERT INTO producto " +
                "VALUES('" + producto.getNombre() + "'," +
                "" + producto.getPrecio() + "," +
                "" + producto.getCodigoFabricante() + ");";
        try {
            super.insertarModificarEliminar(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }

    }

    public Producto buscarProducto(int codigo) throws SQLException, ClassNotFoundException {

        String sql = "select * from producto where codigo = " + codigo + ";";
        try {
            super.consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
            }
            super.desconectarBase();
            return producto;
        } catch (SQLException | ClassNotFoundException e) {
            super.desconectarBase();
            throw e;
        }
    }
}
