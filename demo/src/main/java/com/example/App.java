package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * Es una aplicacion en la que puedes introducir un nombre de un videojuego y muestra 
 * los precios de ese juego en distintas paginas junto con la pagina donde está mas barato
 * 
 */



public class App {

    public static ArrayList<Juego> precios = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("¿Que juego quieres buscar? ");
        String juego = input.nextLine();
        String[] urls = {"https://store.steampowered.com/search/?term=" + juego, "https://www.eneba.com/es/marketplace?text=" + juego + "&sortBy=RELEVANCE_DESC", "https://www.instant-gaming.com/es/busquedas/?q=" + juego};
        String url;

        for (int i = 0; i < urls.length; i++) {
            url = urls[i];

            switch (i) {
                case 0:
                    juegoSteam(url, juego, i);
                    break;
                case 1:
                    juegoEneba(url, juego, i);
                    break;
                case 2:
                    juegoIG(url, juego, i);
                    break;
                default:
                    break;
            }
        }

        mostrarPrecios();
        masBarato();

        input.close();
    }

    public static void masBarato() {
        double aux = precios.get(0).getPrecio();
        int juego = 0;
            for (int i = precios.size() - 1; i > 0; i--) {
                if (precios.get(i).getPrecio() < aux ) {
                    aux = precios.get(i).getPrecio();
                    juego = i;
                }
            }
            System.out.println("El mas barato es: " + precios.get(juego).toString());
            
    }

    public static void juegoIG(String url, String juego, int plataforma) {

        if (getStatusConnectionCode(url) == 200) {

                Document document = getHtmlDocument(url);

                Elements entradas = document.select("div.information");
                
                for (Element elemento : entradas) {
                    if (elemento.getElementsByClass("title").text().toLowerCase().contains(juego.toLowerCase())) {

                        String precio = elemento.getElementsByClass("price").text();
                        if (! (precio.length() < 4)) {
                        precios.add(new Juego(plataforma, precio));
                        }

                    }
                }

            } else {
                return;
            }

    }

    public static void juegoEneba(String url, String juego, int plataforma) {

        if (getStatusConnectionCode(url) == 200) {

                Document document = getHtmlDocument(url);

                Elements entradas = document.select("div.pFaGHa");
                
                for (Element elemento : entradas) {
                    if (elemento.getElementsByClass("YLosEL").text().toLowerCase().contains(juego.toLowerCase())) {

                        String precio = elemento.getElementsByClass("L5ErLT").text();
                        precios.add(new Juego(plataforma, precio));

                    }
                }

            } else {
                return;
            }

    }

    public static void juegoSteam(String url, String juego, int plataforma) {

        if (getStatusConnectionCode(url) == 200) {

                Document document = getHtmlDocument(url);

                Elements entradas = document.select("div.responsive_search_name_combined");
                
                for (Element elemento : entradas) {
                    if (elemento.getElementsByClass("title").text().toLowerCase().equals(juego.toLowerCase())) {

                        String precio = elemento.getElementsByClass("discount_final_price").text();
                        precios.add(new Juego(plataforma, precio));

                    }
                }

            } else {
                return;
            }

    }

    public static void mostrarPrecios() {

        for (int i = 0; i < precios.size(); i++) {
            System.out.println(precios.get(i).toString());
        }
    }

    public static Document getHtmlDocument(String url) {

        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();

        } catch (IOException e) {
            System.out.println("Excepcion al obtener el HTML de la pagina" + e.getMessage());
        }
        return doc;
    }


    public static int getStatusConnectionCode(String url) {

        Response response = null;

        try {

            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();

        } catch (IOException e) {
            System.out.println("Excepcion al obtener el Status Code: " + e.getMessage());
        }

        return response.statusCode();
    }
}

