/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Murilo
 */
public class LeitorDeArquivo 
{
    private char caracter;
    InputStreamReader leituracaracteres;
    
        public LeitorDeArquivo(String path) throws IOException 
        {
            FileInputStream abertura = new FileInputStream(path); //abertura seria o objeto responsáel pela abertura do arquivo
            this.leituracaracteres = new InputStreamReader(abertura); //leitura de caracteres
        }
        
        public char leituraCaracter()
        {
            try
            {
                this.caracter = (char)leituracaracteres.read(); //método read que retorna um inteiro que representa o caracter 
                return caracter;
            }
            catch (Exception ex)
                    {
                       System.out.println(ex.getMessage()); 
                    }
                   return 0; 
        }
        
}

