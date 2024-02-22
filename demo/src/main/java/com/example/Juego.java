package com.example;

public class Juego {
    
    final private int plataforma;
    final private String precio;

    public Juego(int plataforma, String precio) {
        this.plataforma = plataforma;
        this.precio = precio;
    }

    public String getPlataforma() {
        if (this.plataforma == 0) {
            return "Steam";
        }
        if (this.plataforma == 1) {
            return "Eneba";
        }
        if (this.plataforma == 2) {
            return "Instant Gaming";
        }
        return null;
    }

    public double getPrecio() {
        
        return Double.parseDouble(precio.substring(0, 4).replace(",", "."));
        
        

    }

    public String getPrecio(int x) {
        return precio;
    }

    public String toString() {
        return getPlataforma() + " " + getPrecio() + " euros";
    }
}
