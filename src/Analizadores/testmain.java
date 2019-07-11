/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 *@author luisalbertocastroquevedo
 */
public class testmain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            System.out.println("ANALIZADOR LEXICO: ");
            probarAnalizadorLexico("/Users/luisalbertocastroquevedo/NetBeansProjects/Analizadores/src/Analizadores/codigofuente.fjl", false);

            System.out.println("------------------------------------------------");
            System.out.println("ANALIZADOR SINTÁCTICO:");
            probarAnalizadorSintactico("/Users/luisalbertocastroquevedo/NetBeansProjects/Analizadores/src/Analizadores/codigofuente.fjl", true);
            System.out.println("------------------------------------------------");
            System.out.println("ANALIZADOR SEMANTICO");
            probarAnalizadorSemantico("/Users/luisalbertocastroquevedo/NetBeansProjects/Analizadores/src/Analizadores/codigofuente.fjl");

            
probarGeneradorDeCodigoIntermedio("/Users/luisalbertocastroquevedo/NetBeansProjects/Analizadores/src/Analizadores/codigofuente.fjl");
      
        } catch (Exception e) {
            System.out.println("Error de compilacion");
        }
    }

    public static void probarAnalizadorLexico(String directorio, boolean mostrarSoloErrores) {
        try {
            CodigoFuente cf = new CodigoFuente(directorio);
            AnalizadorLexico al = new AnalizadorLexico(cf.getLineas());
            String bufferError = "";
            Tokens t;
            while ((t = al.siguienteToken()) != Tokens.EOT) {
                if (!mostrarSoloErrores) {
                    if (t == Tokens.ERROR && al.getTokenActual().getToken().length() == 1) {
                        bufferError += al.getTokenActual().getToken();
                    } else {
                        if (bufferError.length() > 0) {
                            System.out.println("\"" + bufferError + "\" es ERROR");
                            bufferError = "";
                        }
                        System.out.println("\"" + al.getTokenActual().getToken() + "\" es " + t);
                    }
                }
            }
        } catch (FileNotFoundException exc) {
            System.out.println("No se encontró el archivo con el código fuente.");
        } catch (IOException exc) {
            System.out.println("Hubo un error durante la lectura del archivo con el código fuente.");
        }
    }

    public static void probarAnalizadorSintactico(String directorio, boolean mostrarArbol) {
        try {
            CodigoFuente cf = new CodigoFuente(directorio);
            AnalizadorLexico al = new AnalizadorLexico(cf.getLineas());
            AnalizadorSintactico as = new AnalizadorSintactico(al);
            ArbolSintactico arsi = as.analizarSintaxis();
            if (mostrarArbol) {
                System.out.println("\033[34m" + arsi.toString() + "\033[30m");
            }
        } catch (FileNotFoundException exc) {
            System.err.println("No se encontró el archivo con el código fuente.");
        } catch (IOException exc) {
            System.err.println("Hubo un error durante la lectura del archivo con el código fuente.");
        }
    }

    public static void probarAnalizadorSemantico(String directorio) {
        try {
            CodigoFuente cf = new CodigoFuente(directorio);
            AnalizadorLexico al = new AnalizadorLexico(cf.getLineas());
            AnalizadorSintactico as = new AnalizadorSintactico(al);
            AnalizadorSemantico anse = new AnalizadorSemantico(as);
            anse.analizarSemantica();
        } catch (FileNotFoundException exc) {
            System.err.println("No se encontró el archivo con el código fuente.");
        } catch (IOException exc) {
            System.err.println("Hubo un error durante la lectura del archivo con el código fuente.");
        }
        catch (Exception exc) {
            System.err.println("Hubo un error durante la lectura del archivo con el código fuente.");
        }
    }

    public static void probarGeneradorDeCodigoIntermedio(String directorio) {
        try {
            CodigoFuente cf = new CodigoFuente(directorio);
            AnalizadorLexico al = new AnalizadorLexico(cf.getLineas());
            AnalizadorSintactico as = new AnalizadorSintactico(al);
            AnalizadorSemantico anse = new AnalizadorSemantico(as);
            GeneraCodeInter gci = new GeneraCodeInter(anse);
            gci.generarCodigoIntermedio((directorio.substring(0, directorio.length() - 3) + "cpp"));
        } catch (FileNotFoundException exc) {
            System.err.println("No se encontró el archivo con el código fuente.");
        } catch (IOException exc) {
            System.err.println("Hubo un error durante la lectura del archivo con el código fuente.");
        }
    }
}