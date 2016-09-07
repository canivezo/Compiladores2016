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
}
