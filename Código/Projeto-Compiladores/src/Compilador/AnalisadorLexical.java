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
    private int leituraDoArquivo = 0;
    private char caracterLido;
    private LeitorDeArquivo arquivo;
    //leitor de arquivo
    public AnalisadorLexical(String nomeDoArquivo) throws Exception
    {
        vetorDeTokens = new Vector<Token>();
	arquivo = new LeitorDeArquivo(nomeDoArquivo);
        leiaCaracter();
        while(leituraDoArquivo != -1) // ainda precisa verficar se chegou ao fim do arquivo em outros pontos do código
        {
            if(caracterLido == '{')
            {
                while(caracterLido != '}' )
                {
                    if(caracterLido == '\n' || caracterLido == '\r') 
                    {
                        linha++;
                    }
                    leiaCaracter();
                }
                continue;
            }
            if(Character.isWhitespace(caracterLido))
            {
                if(caracterLido == '\n' || caracterLido == '\r') 
                {
                    linha++;
                }
                leiaCaracter();
                continue;
            }
            pegaToken();
        }
    }
    
    public Token pegaToken() throws Exception
    {
        if(Character.isDigit(caracterLido))
        {
            return trataDigito();
        }
        if(Character.isAlphabetic(caracterLido))
        {
            return trataIdentificadorEPalavraReservada();
        }
        if(caracterLido ==  ':')
        {
            return trataAtribuicao();
        }
        if(caracterLido == '+' || caracterLido == '-' || caracterLido == '*')
        {
            return trataOperadorAritmetico();
        }
        if(caracterLido == '+' || caracterLido == '-' || caracterLido == '*')
        {
            return trataOperadorRelacional();
        }
        return trataPontuacao();
    }
    
    public Token pegaToken(int i)
    {
        Token token = null;
        //leia caracter
        
        return token;
    }
    
    private void leiaCaracter()
    {
        leituraDoArquivo = arquivo.leituraCaracter();
        if(leituraDoArquivo != -1)
        {
            caracterLido = (char) leituraDoArquivo;
        }
    }
    
    private Token trataDigito()
    {
        Token token = null;
        return token;
    }
    
    private Token trataIdentificadorEPalavraReservada() throws Exception
    {
        String id;
        do
        {
             id = ""+caracterLido;
             leiaCaracter();
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
            case "procedimento":
                return new Token("sprocedimento", id, linha);
            case "funcao":
                return new Token("sfuncao", id, linha);
            case "div":
                return new Token("sdiv", id, linha);
            case "e":
                return new Token("e", id, linha);
            case "ou":
                return new Token("ou", id, linha);
            case "nao":
                return new Token("nao", id, linha);
        }
        return null;
    }
    
    private Token trataAtribuicao() throws Exception
    {
        String atrib = ""+caracterLido;
        leiaCaracter();
        if(caracterLido == '=')
        {
            atrib = atrib+'='; 
            leiaCaracter();
            return new Token("satribuição", atrib, linha);
        }
        return new Token("sdoispontos", atrib, linha);
    }
    
    private Token trataOperadorAritmetico() throws Exception
    {
        leiaCaracter();
        if(caracterLido == '+')
        {
                return new Token("smais", atrib, linha);
        }
        else if(caracterLido == '-')
        {
                return new Token("smenos", atrib, linha);
        }
        else 
        {
                return new Token("smult", atrib, linha);
        }
    }
    
    private Token trataOperadorRelacional()
    {
        return null;
    }
    
     private Token trataPontuacao() throws Exception
    {
        leiaCaracter();
        if(caracterLido == ';')
        {
            return new Token("sponto_virgula", atrib, linha);
        }
        else if(caracterLido == '(')
        {           
            return new Token("sabre_parenteses", atrib, linha);
        }
        else if(caracterLido == ')')
        {   
            return new Token("sfecha_parenteses", atrib, linha);
        }
        else if(caracterLido == ',')
        {            
            return new Token("svirgula", atrib, linha);
        }
        else //if(caracter[i] == '.')
        {            
            return new Token("sponto", atrib, linha); 
        }
    }
}
