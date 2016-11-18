/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;

/**
 *
 * @author Rubens
 */
public class TestePilha 
{
    
    Pilha pilha = new Pilha();
   
    
    public void teste()
    {
        DadosPilha res;
        //Popula a pilha para o teste
        for(int a=0; a<5; a++)
        {
            pilha.push(a, a);
            System.out.println("Empilhando: end: "+pilha.tamPilha()+" valor: "+a);
        }
        //Tamanho da pilha
        System.out.println("Tamanho da pilha: "+pilha.tamPilha());
        
        //Pega valor do segundo elemento na pilha
        System.out.println("Teste: "+pilha.getValor(2));
        
        //Altera o valor do segundo elemento da pilha
        pilha.setValor(2, 100);
        
        //Desempilha a pilha com o valor do segundo elemento da pilha alterado
        System.out.println("Desempilhando.");
        for(int a=0; a<5; a++)
        {
            res = pilha.pop();
            System.out.println("Desempilhando: end: "+res.getAdress()+" valor: "+res.valor);
            if(a == 2)
            {
                System.out.println("Tamanho da pilha: "+pilha.tamPilha());
            }
        }
        //Tamanho da pilha
        System.out.println("Tamanho da pilha: "+pilha.tamPilha());
    }
    
}

class Testinho
{
    public Testinho()
    {
        
    }
    public static void main(String[] args) 
    {
        TestePilha teste = new TestePilha();
        teste.teste();
    }
}
