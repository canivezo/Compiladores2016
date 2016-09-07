/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class TesteDeLeitorDeArquivo {
    
    public static void main(String [] args)
    {
        int caracterLido = 0;
        LeitorDeArquivo leitor = null;
        try 
        {
            leitor = new LeitorDeArquivo("C:\\Users\\lucas\\Desktop\\lucas.txt");
        } 
        catch (IOException ex) {}
        do
        {
            caracterLido = leitor.leituraCaracter();
            if(caracterLido != -1)
                System.out.println(""+(char)caracterLido);
        }
        while(caracterLido != -1);
    }
}
