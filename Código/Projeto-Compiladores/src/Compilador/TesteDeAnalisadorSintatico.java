/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.util.Vector;

/**
 *
 * @author Rubens
 */
public class TesteDeAnalisadorSintatico 
{
    
    public static void main(String [] args)
    {
        AnalisadorSintatico teste = null;
        try
        {
            teste = new AnalisadorSintatico("C:\\Users\\Rubens\\Desktop\\Sintatico\\teste1.txt");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(""+e.getMessage());
        }
    }
    
}
