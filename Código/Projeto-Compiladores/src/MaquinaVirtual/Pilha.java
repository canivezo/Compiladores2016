/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;

import java.util.ArrayList;


/**
 * @author Rubens
 */
public class Pilha 
{
    
    public int posicao = 0;
    private ArrayList<DadosPilha> conteudo = new ArrayList<DadosPilha>();
    
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
            return this.posicao;
        }
        else
            return 0;
    }
    
    public void push(int end, int valor)
    {
            DadosPilha aux = new DadosPilha();
            aux.setAdress(end);
            aux.setValor(valor);
            this.conteudo.add(aux);
            this.posicao++; 
    }
    
    public DadosPilha pop()
    {
        this.posicao--;
        return this.conteudo.remove(this.conteudo.size() -1);
    }
    
    public int getEnd(int pos)
    {
        return conteudo.get(conteudo.size()-pos).getAdress();
    }
    
    public int getValor(int pos)
    {
        return conteudo.get(conteudo.size()-pos).getValor();

    }
    
    
    public void setValor(int pos, int val)
    {
        int a = conteudo.get(conteudo.size()-pos).adress;
        DadosPilha aux = new DadosPilha();
        aux.setAdress(a);
        aux.setValor(val);
        conteudo.set(conteudo.size()-pos, aux);
    }
    
}
