/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.compiladores;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rubens
 */
public class Pilha 
{
    
    public int posicao;
    private List<DadosPilha> conteudo = new LinkedList<DadosPilha>();
    
    public Pilha()
    {
       
    }
    
    public boolean pilhaVazia()
    {
        if(this.posicao < 0) // Pilha vazia
        {
            return true;
        }
        else
            return false;
    }
    
    public void push(int valor, int end)
    {

            DadosPilha aux = new DadosPilha();
            aux.setAdress(end);
            aux.setValor(valor);
            this.conteudo.add(aux);
        
    }
    
    public DadosPilha pop()
    {
        return this.conteudo.remove(this.conteudo.size() -1);
    }
    
}
