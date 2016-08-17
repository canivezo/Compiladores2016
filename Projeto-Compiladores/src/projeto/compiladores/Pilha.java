/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.compiladores;

/**
 * @author Rubens
 */
public class Pilha 
{
    
    public Object[] pilha;
    public int posicao;
    
    public Pilha()
    {
        this.posicao = -1;
        this.pilha = new Object[1000]; 
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
    
    public void push(Object valor)
    {
        if(this.posicao < this.pilha.length -1)
        {
            this.pilha[++posicao] = valor;
        }
    }
    
    public Object pop()
    {
        if(pilhaVazia())
        {
            return null;
        }
        return this.pilha[this.posicao--];
    }
    
    
}
