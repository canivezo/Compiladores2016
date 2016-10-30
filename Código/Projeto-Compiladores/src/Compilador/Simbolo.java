/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

/**
 *
 * @author Murilo
 */
public class Simbolo 
{
    private int type;
    private Token token;
    private int expressionType;
    
    public Simbolo(int expType, int typ, Token tok) throws Exception
    {
        setExpressionType(expType);
        setType(typ);
        setToken(tok);
    }
    
    /**
     * 
     * @param e assume 0 não pode estar em uma expressão, 
     *          1 para expressões booleanas 
     *          e 2 para expressóes do tipo inteiro. 
     */
    public void setExpressionType(int e) throws Exception
    {
        if(e < 0 || e > 2)
            throw new Exception("Tipo da expressao do simbolo invalido");
        this.expressionType = e;
    }
   
    public int getExpressionType()
    {
        return expressionType;
    }
    
    public void setToken(Token t) throws Exception
    {
        if(t == null)
            throw new Exception("Token do simbolo invalido");
        this.token = t;
    }
    
    public Token getToken()
    {
        return token;
    }
    
    /**
     * 
     * @param t pode assumir os valores a seguir conforme os tipos:
     * 1 - para booleano
     * 2 - para inteiro
     * 3 - para funcao
     * 4 - para procedimento
     * 5 - para programa
     */
    public void setType(int t) throws Exception
    {
        if(t < 0 || t > 5)
            throw new Exception("Tipo do simbolo invalido");
        this.type = t;
    }
    
    public int getType()
    {
        return type;
    }
}

