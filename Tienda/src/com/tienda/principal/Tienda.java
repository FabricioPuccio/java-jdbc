package com.tienda.principal;

import com.tienda.servicios.FabricanteServicio;

public class Tienda {
    public static void main(String[] args) {

        FabricanteServicio fServ = new FabricanteServicio();
        try {
            fServ.ingresarFabricante();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
