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
    private int nivel;
    
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
     * @param n pode ser qualquer valor acima de -2
     */
    public void setNivel(int n) throws Exception
    {
        if(n < -1)
            throw new Exception("Nivel invalido");
        
        nivel = n;
    }
   
    public int getNivel()
    {
        return nivel;
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

