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
public class DadosPilha {
    
    public int adress;
    public int valor;
    
    public DadosPilha()
    {
       this.valor = 0;
       this.adress = 0;
    }
    
    public DadosPilha(int a, int v)
    {
       this.valor = v;
       this.adress =a;
    }      
    
    public int getValor() 
    {
        return valor;
    }

    public void setValor(int valor) 
    {
        this.valor = valor;
    }

    public int getAdress() 
    {
        return adress;
    }

    public void setAdress(int adress) 
    {
        this.adress = adress;
    }
    
}
