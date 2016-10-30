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
    
    public Simbolo(int expressionType, int type, Token token)
    {
        this.expressionType = expressionType;
        this.type = type;
        this.token = token;
    }
    
    public void setexpressionType(int expressionType)
    {
        this.expressionType = expressionType;
    }
   
    public int getexpressionType()
    {
        return expressionType;
    }
    
    public void setToken(Token token)
    {
        this.token = token;
    }
    
    public Token getToken()
    {
        return token;
    }
    
    public void setType(int type)
    {
        this.type = type;
    }
    
    public int getType()
    {
        return type;
    }
}

