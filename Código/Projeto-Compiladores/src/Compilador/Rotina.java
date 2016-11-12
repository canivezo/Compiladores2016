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
public class Rotina implements Type{
    
    private int label;
    private final String tipo;
    
    public Rotina(String t) throws Exception
    {
        if(t == null)
            throw new Exception("tipo invalido");
        
        tipo = t;
    }
    
    public Rotina(String t, int info) throws Exception
    {
        if(t == null)
            throw new Exception("tipo invalido");
        
        tipo = t;
        setInfo(info);
    }
    
    @Override
    public String getType() 
    {
        return tipo;
    }

    @Override
    public int getInfo() 
    {
        return 0;
    }

    @Override
    public void setInfo(int info) 
    {
        label = info;
    }
    
}
