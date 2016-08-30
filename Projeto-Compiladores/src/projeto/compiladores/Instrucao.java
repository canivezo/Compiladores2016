/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.compiladores;

/**
 *
 * @author lucas
 */

public class Instrucao { 
    
    protected nomeInstrucao instrucao;
    protected int linha;
    public Instrucao(nomeInstrucao i, int l)
    {
        instrucao = i;
        linha = l;
    }
    
    public nomeInstrucao getInstrucao()
    {
        return instrucao;
    }
}
