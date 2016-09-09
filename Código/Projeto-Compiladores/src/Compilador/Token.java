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
public class Token {

    private final String simbolo;
    private final String lexema;
    private final int linha;
    
    public Token(String simbolo, String lexema, int linha) throws Exception
    {
        if(lexema == null) throw new Exception("Lexema inválido");
        if(simbolo == null) throw new Exception("Símbolo inválido");
        this.simbolo = simbolo;
        this.lexema = lexema;
        this.linha = linha;
    }
       
    public String getSimbolo()
    {
        return simbolo;
    }
     
    public String getLexema()
    {
        return lexema;
    }
    
    public int getLinha()
    {
        return linha;
    }
    
    public String toString()
    {
        return ""+simbolo+"\n"+lexema+"\n"+linha;
    }
    
    public int lexemaToCode(String lexema)
    {
        try
            {
            switch (lexema)
                {
                case "programa":
                    return 1;
                case "inicio":
                    return 2;
                case "fim":
                    return 3;
                case "procedimento":
                    return 4;
                case "funcao":
                    return 5;
                case "se":
                    return 6;
                case "entao":
                    return 7;
                case "senao":
                    return 8;
                case "enquanto":
                    return 9;
                case "faca":
                    return 10;
                case ":=":
                    return 11;
                case "escreva":
                    return 12;
                case "leia":
                    return 13;
                case "var":
                    return 14;
                case "inteiro":
                    return 15;
                case "booleano":
                    return 16;
                case "identificador":
                    return 17;
                case "numero":
                    return 18;
                case ".":
                    return 19;
                case ";":
                    return 20;
                case ",":
                    return 21;
                case "(":
                    return 22;
                case ")":
                    return 23;
                case ">":
                    return 24;
                case ">=":
                    return 25;
                case "=":
                    return 26;
                case "<":
                    return 27;
                case "<=":
                    return 28;
                case "!=":
                    return 29;
                case "+":
                    return 30;
                case "-":
                    return 31;
                case "*":
                    return 32;
                case "Div":
                    return 33;
                case "e":
                    return 34;
                case "ou":
                    return 35;
                case "nao":
                    return 36;
                case ":":
                    return 37;
                }
            }
        
            catch(Exception ex)
                {
                System.out.println(ex.getMessage()); 
                }
        return 0;
    }
     
}
