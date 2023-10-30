package com.tienda.servicios;

import com.tienda.entidades.Producto;
import com.tienda.persistencia.ProductoDao;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductoServicio {

    private ProductoDao proDao;
    private Scanner sc;

    public ProductoServicio() {
        proDao = new ProductoDao();
        sc = new Scanner(System.in);
    }

    public void ingresarProducto() throws Exception {
        String nombre;
        double precio;
        int codigoFabricante;
        System.out.print("Ingresar producto a la BD.\n" +
                "Por favor ingrese los siguientes datos del producto.\n" +
                "Nombre: ");
        nombre = sc.next();
        System.out.print("Precio: ");
        precio = sc.nextDouble();
        System.out.print("Código del fabricante: ");
        codigoFabricante = sc.nextInt();

        try {
            proDao.guardarProducto(new Producto(nombre, precio, codigoFabricante));
        } catch (Exception e) {
            throw e;
        }
    }

    public void verProducto() throws SQLException, ClassNotFoundException {
        Producto producto = null;
        String respuesta = "";
        producto = devolverProducto("Por favor digite el código del producto que desea ver.");

        while (producto == null && !respuesta.equals("*")) {
            System.out.print("No existe un producto con el código ingresado.\n" +
                    "Digite cualquier tecla para ingresar un nuevo codigo o '*' para salir: ");
            respuesta = sc.next();

            if (!respuesta.equals("*")) {
                producto = devolverProducto("\nPor favor digite el código del producto que desea ver.");
            }

        }

        if (producto != null) {
            System.out.println(producto.toString());
        } else {
            System.out.println("No hay un producto en la base de datos con el código ingresado.");
        }
    }

    public void editarProducto() throws Exception {
        Producto producto = null;
        String nombre;
        double precio;
        int codigoFabricante;

        try {
            producto = devolverProducto("Por favor digite el código del producto que desea editar.");

            if (producto == null) {
                System.out.println("No se encontró un producto con el código ingresado");
            }

            System.out.println("Datos del producto que sea actualizar:" +
                    "" + producto.toString());

            System.out.print("\nPor favor ingrese los siguientes datos.\n" +
                    "Nombre: ");
            nombre = sc.next();
            System.out.print("Precio: ");
            precio = sc.nextDouble();
            System.out.print("Código del fabricante: ");
            codigoFabricante = sc.nextInt();

            proDao.actualizarProducto(new Producto(producto.getCodigo(), nombre, precio, codigoFabricante));

        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }

    private Producto devolverProducto(String cadena) throws SQLException, ClassNotFoundException {
        int codigo;
        System.out.print(cadena + "\nCódigo: ");
        codigo = sc.nextInt();
        return proDao.buscarProducto(codigo);
    }
}
