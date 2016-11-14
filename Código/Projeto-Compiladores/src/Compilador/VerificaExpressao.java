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
public class VerificaExpressao {
    
    private interface vetorExp<Generic> {
        public Generic getExp();
        public void setExp(Generic t);
    }
    
    private class ExpSimb implements vetorExp<Simbolo>{
            
        private Simbolo simbolo;
        
        public ExpSimb(Simbolo s)
        {
            setExp(s);
        }
        
        @Override
        public Simbolo getExp()
        {
            return simbolo;
        }
        
        @Override
        public void setExp(Simbolo t)
        {
            simbolo = t;
        }
    }

    private class ExpOp implements vetorExp<Token>{

        private Token token;

        public ExpOp(Token t)
        {
            setExp(t);
        }
        
        public Token getExp()
        {
            return token;
        }

        public void setExp(Token t)
        {
            token = t;
        }
    }
    
    private Vector<vetorExp> pilha;
    
    public VerificaExpressao()
    {
        pilha = null;
    }
    
    private void adicionaOperador(Token t)
    {
        pilha.add(new ExpOp(t));
    }
    
    private void adicionaSimbolo(Simbolo s)
    {
        pilha.add(new ExpSimb(s));
    }
    
    private void geraExp()
    {
        
    }
    
    public void comecaExpressao()
    {
        pilha = new Vector<vetorExp>();
    }
    
    public void adicionaFatorNaExpressao(Simbolo s)
    {
        
    }
    
    public void adicionaOperadorNaExpressao(Token t)
    {
        
    }
    
    public void terminaExpressao()
    {
        pilha = null;
    }
}