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
        public LeitorDeArquivo(String path) throws IOException 
        {
            FileInputStream abertura = new FileInputStream("C:\\Users\\Murilo\\Desktop\\file.txt"); //abertura seria o objeto responsáel pela abertura do arquivo
            InputStreamReader leituracaracteres = new InputStreamReader(abertura); //leitura de caracteres
            int caracter = leituracaracteres.read(); //método read que retorna um inteiro que representa o caracter 

            while( caracter!=-1)
            {
                System.out.print( (char) caracter);
                caracter = leituracaracteres.read();
            }
        }
}

