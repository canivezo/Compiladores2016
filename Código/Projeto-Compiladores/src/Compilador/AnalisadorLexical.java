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
public class AnalisadorLexical {
    Vector vetorDeTokens = null;
    private int linha = 1;
    
    public AnalisadorLexical(String arquivo)
    {
        for(int i = 0; i < arquivo.length(); i++)
        {
            char caracterLido = leiaCaracter(arquivo,i);
            if(caracterLido == '{')
            {
                while(arquivo.charAt(i) != '}') i++;
                continue;
            }
            if(Character.isWhitespace(arquivo.charAt(i)))
            {
                if(arquivo.charAt(i) == '\n' || arquivo.charAt(i) == '\r') linha++;
                continue;
            }
            pegaToken(arquivo);
        }
    }
    
    public Token pegaToken(String arquivo)
    {
        Token token = null;
        //leia caracter
        
        return token;
    }
    
    public Token pegaToken()
    {
        Token token = null;
        //leia caracter
        
        return token;
    }
    
    private char leiaCaracter(String arquivo, int count)
    {
        return arquivo.charAt(count);
    }
    
    private Token trataDigito()
    {
        Token token = null;
        return token;
    }
    
    private Token trataIdentificadorEPalavraReservada()
    {
        return null;
    }
    
    private Token trataAtribuicao()
    {
        return null;
    }
    
    private Token trataOperadorAritmetico()
    {
        return null;
    }
    
    private Token trataOperadorRelacional()
    {
        return null;
    }
    
    private Token trataPontuacao()
    {
        return null;
    }
}
