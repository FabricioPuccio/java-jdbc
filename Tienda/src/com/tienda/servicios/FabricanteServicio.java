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
        System.out.println("Para ingresar un Fabricante a la BD es necesario que digite el siguiente dato:\n" +
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
}
