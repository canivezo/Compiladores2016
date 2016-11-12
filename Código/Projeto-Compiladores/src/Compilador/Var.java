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
public class Var implements Type{

    private int posPilha;
    private final String type;
    
    public Var(String t) throws Exception
    {
        if(t == null)
            throw new Exception("Tipo invalido");
        
        type = t;
    }
    
    public Var(String t, int pos) throws Exception
    {
        if(t == null)
            throw new Exception("Tipo invalido");
        
       type = t;
       setInfo(pos);
    }
    
    @Override
    public String getType() 
    {
        return type;
    }

    @Override
    public int getInfo() 
    {
        return posPilha;
    }

    @Override
    public void setInfo(int info) 
    {
        posPilha = info;
    }
    
}
