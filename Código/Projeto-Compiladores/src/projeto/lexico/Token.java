/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package projeto.lexico;

/**
 *
 * Murlinho O GATÃO
 */

public class Token 
{
    private String simbolo;
    private String lexema;
    private int linha;
    
    public Token(String simbolo, String lexema, int linha)
    {
        this.simbolo = simbolo;
        this.lexema = lexema;
        this.linha = linha;
    }
    
    public Token(Token original)
    {
        this.simbolo = original.simbolo;
        this.lexema = original.lexema;
        this.linha = original.linha;
    }
    
    public void setSimbolo(String simbolo)
    {
        this.simbolo = simbolo;
    }
       
    public String getSimbolo()
    {
        return simbolo;
    }
    
    public void setLexema(String lexema)
    {
        this.lexema = lexema;
    }
    
    public String getLexema()
    {
        return lexema;
    }
    
    public void setLinha(int linha)
    {
        this.linha = linha;
    }
    
    public int getLinha()
    {
        return linha;
    }
}

