/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;

import java.util.ArrayList;
import java.util.Vector;


/**
 * @author Rubens
 */
public class Pilha 
{
    
    public int posicao = 0;
    private Vector<DadosPilha> conteudo = new Vector<DadosPilha>();
    private DadosPilha aux = new DadosPilha();
    
    /**
     *
     */
    public Pilha()
    {
       this.posicao = 0;
    }
    
    public boolean pilhaVazia()
    {
        if(this.posicao < 1) // Pilha vazia
        {
            return true;
        }
        else
            return false;
    }
    
    public int tamPilha()
    {
        if(!pilhaVazia())
        {
            return conteudo.size();
        }
        else
            return 0;
    }
    
    public void push(int end, int valor)
    {
        aux = new DadosPilha();
        aux.setAdress(end);
        aux.setValor(valor);
        conteudo.add(aux);
        this.posicao++;
    }
    
    public void pushPos(int end)
    {
        posicao++;
        aux = new DadosPilha();
        aux.setAdress(conteudo.size());
        aux.setValor(conteudo.get(end).valor);
        conteudo.add(aux);
    }
    
    public DadosPilha pop()
    {
        posicao--;
        return conteudo.remove(conteudo.size() -1);
    }
    
    public int getEnd(int pos)
    {
        return conteudo.get(conteudo.size()-pos).getAdress();
    }
    
    public int getValor(int pos)
    {
        return conteudo.get((conteudo.size()-pos)).getValor();
    }
    
        public int getValorFixo(int pos)
    {
        return conteudo.get(pos).getValor();
    }
    
    
    public void setValor(int pos, int val)
    {
        aux = new DadosPilha();
        int a = conteudo.get(conteudo.size()-pos).getAdress();
        aux.setAdress(a);
        aux.setValor(val);
        conteudo.set(conteudo.size()-pos, aux);
    }
    
        public void setValorFixo(int pos, int val)
    {
        aux = new DadosPilha();
        aux.setAdress(pos);
        aux.setValor(val);
        conteudo.set(pos, aux);
    }
    
}
