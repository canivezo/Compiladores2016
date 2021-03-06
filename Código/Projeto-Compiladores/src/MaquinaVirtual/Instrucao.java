/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;

/**
 *
 * @author lucas
 */

public class Instrucao { 
    
    protected nomeInstrucao instrucao;
    protected int linha;
    public Instrucao(nomeInstrucao i, int l) throws Exception
    {
        instrucao = i;
        if(l < 0)
        {
            throw new Exception("linha negativa");
        }
        linha = l;
    }
    
    public nomeInstrucao getInstrucao()
    {
        return instrucao;
    }
    
    public int getLinha()
    {
        return linha;
    }
    
    @Override
    public String toString()
    {
        return "\nINTRUCAO\t:\t"+instrucao.name()+"\nLINE\t\t:\t"+linha+"\n";
    }
}
