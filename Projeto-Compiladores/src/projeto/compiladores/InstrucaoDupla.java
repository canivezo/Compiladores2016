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
public class InstrucaoDupla extends Instrucao{
    private int param1;
    private int param2;
    public InstrucaoDupla(nomeInstrucao i, int l, int p1, int p2) throws Exception
    {
        super(i, l);
        if(nomeInstrucao.getInstructionType(i) != 2)
        {
            throw new Exception("instrucao de outro tipo");
        }
        if(param1 < 0)
        {
            throw new Exception("p1 nao pode ser menor que zero");
        }
        if(param2 < 0)
        {
            throw new Exception("p2 nao pode ser menor que zero");
        }
        
        param1 = p1;
        param2 = p2;
    }
    
    public int getParam1()
    {
        return param1;
    }
    
    public int getParam2()
    {
        return param2;
    }
}
