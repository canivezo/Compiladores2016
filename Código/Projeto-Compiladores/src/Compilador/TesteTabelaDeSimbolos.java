/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class TesteTabelaDeSimbolos {
    AnalisadorLexical lexico;
    Vector<Token> tokens;
    
    public static void main(String [] args)
    {
        TesteTabelaDeSimbolos tabela = null;
        try 
        {
            //
            // Para funcionar, poem a string com o caminho de um arquivo
            //
            tabela = new TesteTabelaDeSimbolos("");
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(TesteTabelaDeSimbolos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TesteTabelaDeSimbolos(String arquivo) throws Exception
    {
        lexico = new AnalisadorLexical(arquivo);
        Vector<Token> aux = lexico.pegaTokens();
        //
        // Aqui ele pega soh os sidentificadores
        //
        for(int i = 0; i < aux.capacity(); i++)
        {
            if(aux.get(i).getSimbolo().equals("sidentificador"))
                tokens.add(aux.get(i));
        }
    }    
}
