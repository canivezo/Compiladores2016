/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.util.Vector;

/**
 *
 * @author lucas
 */
public class TesteDeAnalisadorLexical {
    
    public static void main(String [] args)
    {
        Vector<Token> i;
        AnalisadorLexical teste = null;
        try
        {
            teste = new AnalisadorLexical("C:\\Users\\Rubens\\Desktop\\Sintatico\\teste1.txt");
            i = teste.pegaTokens();
            for(int j = 0; j < i.size(); j++)
            {
                System.out.println("===============================\n"+i.get(j)+"\n===============================\n");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(""+e.getMessage());
        }
    }
}
