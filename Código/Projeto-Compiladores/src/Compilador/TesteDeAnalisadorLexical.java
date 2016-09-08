/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

/**
 *
 * @author lucas
 */
public class TesteDeAnalisadorLexical {
    
    public static void main(String [] args)
    {
        AnalisadorLexical teste;
        try
        {
            teste = new AnalisadorLexical("C:\\Users\\lucas\\Desktop\\Lexico\\teste4.txt");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(""+e.getMessage());
        }
    }
}
