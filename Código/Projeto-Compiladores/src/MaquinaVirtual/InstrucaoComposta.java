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
public class InstrucaoComposta extends Instrucao{
    private int param1;
    
    public InstrucaoComposta(nomeInstrucao i, int l, int p1) throws Exception
    {
        super(i, l);
        if(nomeInstrucao.getInstructionType(i) != 1)
        {
            throw new Exception("instrucao de outro tipo");
        }
        
        if(p1 < 0)
        {
            throw new Exception("p1 nao pode ser menor que zero");
        }
        param1 = p1;
    }
    
    int getParam1()
    {
        return param1;
    }
}