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
public class InstrucaoSimples extends Instrucao{

    public InstrucaoSimples(nomeInstrucao i, int l) throws Exception
    {
        super(i, l);
        if(nomeInstrucao.getInstructionType(i) != 0)
        {
            throw new Exception("instrucao de outro tipo");
        }
    }
}
