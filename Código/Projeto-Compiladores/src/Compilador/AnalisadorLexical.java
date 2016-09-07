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
    Vector<Token> vetorDeTokens;
    private int linha = 1;
    private int aux = 0;
    //leitor de arquivo
    public AnalisadorLexical(String arquivo)
    {
        vetorDeTokens = new Vector<Token>();
        for(int i = 0; i < arquivo.length(); i++)
        {
            char caracterLido = leiaCaracter(arquivo);
            if(caracterLido == '{')
            {
                while(caracterLido != '}' )
                {
                    if(caracterLido == '\n' || caracterLido == '\r') linha++;
                }
                continue;
            }
            if(Character.isWhitespace(arquivo.charAt(i)))
            {
                if(caracterLido == '\n' || caracterLido == '\r') linha++;
                continue;
            }
            pegaToken(arquivo, caracterLido);
        }
    }
    
    public boolean isfimDoArquivo(String arquivo)
    {
        if(arquivo.length() == aux) return true;
        return false;
    }
    
    public Token pegaToken(String arquivo, char caracterLido) throws Exception
    {
        if(Character.isDigit(caracterLido))
        {
            return trataDigito(arquivo, caracterLido);
        }
        if(Character.isAlphabetic(caracterLido))
        {
            return trataIdentificadorEPalavraReservada(arquivo, caracterLido);
        }
        if(caracterLido ==  ':')
        {
            return trataAtribuicao(arquivo, caracterLido);
        }
        if(caracterLido == '+' || caracterLido == '-' || caracterLido == '*')
        {
            return trataOperadorAritmetico(arquivo, caracterLido);
        }
        if(caracterLido == '+' || caracterLido == '-' || caracterLido == '*')
        {
            return trataOperadorRelacional(arquivo, caracterLido);
        }
        return trataPontuacao(arquivo, caracterLido);
    }
    
    public Token pegaToken()
    {
        Token token = null;
        //leia caracter
        
        return token;
    }
    
    private char leiaCaracter(String arquivo)
    {
        aux++;
        return arquivo.charAt((aux - 1));
    }
    
    private Token trataDigito(String arquivo, char caracterLido)
    {
        Token token = null;
        return token;
    }
    
    private Token trataIdentificadorEPalavraReservada(String arquivo, char caracterLido) throws Exception
    {
        String id;
        do
        {
             id = ""+caracterLido;
             caracterLido = leiaCaracter(arquivo);
        }
        while(Character.isDigit(caracterLido));
        switch(id)
        {
            case "programa":
                return new Token("sprograma", id, linha);
            case "se":
                return new Token("sse", id, linha);
            case "entao":
                return new Token("sentao", id, linha);
            case "senao":
                return new Token("ssenao", id, linha);
            case "enquanto":
                return new Token("senquanto", id, linha);
            case "faca":
                return new Token("sfaca", id, linha);
            case "incio":
                return new Token("sinício", id, linha);
            case "fim":
                return new Token("sfim", id, linha);
            case "escreva":
                return new Token("sescreva", id, linha);
            case "leia":
                return new Token("sleia", id, linha);
            case "var":
                return new Token("svar", id, linha);
            case "inteiro":
                return new Token("sinteiro", id, linha);
            case "booleano":
                return new Token("sbooleano", id, linha);
            case "verdadeiro":
                return new Token("sverdadeiro", id, linha);
            case "falso":
                return new Token("sfalso", id, linha);
        }
        return null;
    }
    
    private Token trataAtribuicao(String arquivo, char caracterLido)
    {
        return null;
    }
    
    private Token trataOperadorAritmetico(String arquivo, char caracterLido)
    {
        return null;
    }
    
    private Token trataOperadorRelacional(String arquivo, char caracterLido)
    {
        return null;
    }
    
    private Token trataPontuacao(String arquivo, char caracterLido)
    {
        return null;
    }
}
