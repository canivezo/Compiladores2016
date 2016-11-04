/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import Compilador.LeitorDeArquivo;

/**
 *
 * @author Murilo
 */
public class TesteLerCaracter 
{
    
    public static void main(String[] args) 
    {
        
        LeitorDeArquivo leendo = null;
        try 
        {
           leendo = new LeitorDeArquivo("C:\\Users\\Rubens\\Documents\\Arqui.txt");
           int clido = leendo.leituraCaracter();
           while(clido != -1)
           {
               System.out.print((char)clido);
               clido = leendo.leituraCaracter();
           }
            System.out.println("");
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
}