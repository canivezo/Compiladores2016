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
public class TesteDeToken {
    
    public static void main(String [] args)
    {
        Token toke = null;
        try
        {
            toke = new Token(null, "oi", 1);
            System.out.println(""+toke);
        }
        catch(Exception e)
        {
            System.out.println(""+e.getMessage());
        }
    }
}
