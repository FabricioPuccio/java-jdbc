package com.tienda.servicios;

import com.tienda.entidades.Producto;
import com.tienda.persistencia.ProductoDao;

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
}
