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
    private Type tipo = null;
    private Token token;
    //private int expressionType;
    
    public Simbolo(Type t, Token tok) throws Exception
    {
        if(t == null)
            throw new Exception("Tipo invalido");
        
        if(tok == null)
            throw new Exception("Token invalido");
        
        setType(t);
        setToken(tok);
    }
    
    public Simbolo(Token tok) throws Exception
    {
        if(tok == null)
            throw new Exception("Token invalido");
        
        setToken(tok);
    }
    
    /**
     * 
     * @param e assume 0 não pode estar em uma expressão, 
     *          1 para expressões booleanas 
     *          e 2 para expressóes do tipo inteiro.
     *          nao utilizado por enquanto
     */
    /*public void setExpressionType(int e) throws Exception
    {
        if(e < 0 || e > 2)
            throw new Exception("Tipo da expressao do simbolo invalido");
        this.expressionType = e;
    }
   
    public int getExpressionType()
    {
        return expressionType;
    }*/
    
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
    public void setType(Type t) throws Exception
    {
        if(t == null)
            throw new Exception("Tipo do simbolo invalido");
        
        tipo = t;
    }
    
    public Type getType()
    {
        return tipo;
    }
    
    /**
    * Este método retorna se esse símnolo é o mesmo que outro símbolo.
    * Não compara o campo type.
    */
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass() == this.getClass())
        {
            Simbolo s = (Simbolo) o;
            if(s.getToken().getSimbolo().equals(this.token.getSimbolo()) && s.getToken().getSimbolo().equals(this.token.getSimbolo()))
            {
                return true;
            }
        }
        return false;
    }
}

