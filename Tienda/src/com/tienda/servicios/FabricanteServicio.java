package com.tienda.servicios;

import com.tienda.entidades.Fabricante;
import com.tienda.persistencia.FabricanteDao;

import java.sql.SQLException;
import java.util.Scanner;

public class FabricanteServicio {

    private FabricanteDao fabriDao;
    private Scanner sc;

    public FabricanteServicio() {
        this.fabriDao = new FabricanteDao();
        this.sc = new Scanner(System.in);
    }

    public void ingresarFabricante() throws Exception {
        String nombre;
        System.out.print("Para ingresar un Fabricante a la BD es necesario que digite el siguiente dato:\n" +
                "Nombre fabricante: ");
        nombre = sc.next();

        if (nombre.isEmpty()) {
            throw new Exception("Es necesario indicar un nombre");
        }

        try {
            fabriDao.guardarFabricante(new Fabricante(nombre));
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }

    }

    public void updateFabricante() throws Exception {
        int codigo;
        String nombre;
        Fabricante fabricante = null;

        System.out.print("\nPor favor ingrese el código del fabricante que desea actualizar.\n" +
                "Código: ");
        codigo = sc.nextInt();

        try {
            fabricante = fabriDao.findById(codigo);

            if (fabricante == null) {
                throw new Exception("No se encontró un fabricante con el código ingresado");
            }
            System.out.println("Los datos del fabricante que desea actualizar son los siguientes:\n");
            fabricante.toString();

            System.out.print("\nIngrese el nuevo nombre del fabricante: ");
            nombre = sc.next();

            if (nombre.isEmpty() || nombre == null) {
                throw new Exception("Es necesario indicar un nombre");
            }
            fabriDao.modificarFabricante(new Fabricante(codigo, nombre));
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }

    public void deleteFabricante() throws Exception {
        int codigo;
        Fabricante fabricante;
        System.out.print("¡Eliminar de la BD un fabricante!\n"
                + "Ingrese el código del fabricante que sea eliminar: ");
        codigo = sc.nextInt();
        try {
            fabricante = fabriDao.findById(codigo);
            if (fabricante == null) {
                throw new Exception("No es encontró un fabricante con el código ingresado");
            }
            fabriDao.eliminarFabricante(fabricante);
        } catch (Exception ex) {
            throw ex;
        }
    }

}
