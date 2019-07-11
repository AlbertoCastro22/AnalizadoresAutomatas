/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Analizadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author luisalbertocastroquevedo
 */
public class CodigoFuente {
    private ArrayList<String> lineas;
    
    /**
     * Abre un archivo de texto y almacena en RAM su contenido.
     * 
     * @param directorio
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public CodigoFuente(String directorio) throws FileNotFoundException,IOException
    {
        lineas = new ArrayList();
        File archivo = new File(directorio);
        FileReader lector = new FileReader(archivo);
        BufferedReader buffer = new BufferedReader(lector);
        String linea;
        while((linea=buffer.readLine())!=null)
            lineas.add(linea);
        buffer.close();
        lector.close();
    }
    public ArrayList<String> getLineas()
    {
        return lineas;
    }
}
