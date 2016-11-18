/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;
import java.util.*;
/**
 *
 * @author lucas
 */
public class ProcessadorDeInstrucao {
    
    private Vector<Instrucao> m_instrucoes = null;
    private Pilha m_pilha = null;
    private boolean fim = false;
    int m_instrucao = 0;
    /**
     * 
     * @param instrucoes inicializa o vetor de instruções na classe.
     * @throws Exception o vetor não pode ser nulo.
     */
    public ProcessadorDeInstrucao(String arquivo) throws Exception
    {
        m_instrucoes = new Arquivo(arquivo).parsearPalavras();
        m_pilha = new Pilha();
        m_instrucao = 0;
    }
    
    /**
     * @return : vetor de instruções lidas no arquivo de configuração. 
     */
    public Vector<Instrucao> getInstrucoes()
    {
        return m_instrucoes;
    }
    
    /**
     * @return : pilha da MV 
     */
    public Pilha getPilha()
    {
        return m_pilha;
    }
    
    public boolean isFim()
    {
        if(fim) 
            return true;
        
        return false;
    }
    
    public void runInstruction()
    {
        if(!(m_instrucao < 0 || m_instrucao < m_instrucoes.size()))
        {
            m_instrucao++;
        }
        else
        {
            //Número da instrução inválido. Este erro nunca vai ocorrer, pois a tradução dos labels não aceita isso. 
        }
    }
    
    private void executaInstrucao(Instrucao instrucao)
    {
        int type = nomeInstrucao.getInstructionType(instrucao.getInstrucao());
        switch(type)
        {
            case 0: 
                executaExpressaoSimples((InstrucaoSimples) instrucao);
                break;
            case 1:
                executaExpressaoComposta((InstrucaoComposta) instrucao);
                break;
            case 2:
                executaExpressaoDuplamenteComposta((InstrucaoDuplamenteComposta) instrucao);
                break;
            default:
                //nao tem como chegar no default, pois nao existe nenum outro tipo de instrucao
        }
    }
    
    private void executaExpressaoSimples(InstrucaoSimples instrucao)
    {
        nomeInstrucao i = instrucao.getInstrucao();
        if(i == nomeInstrucao.ADD)      add();
        if(i == nomeInstrucao.SUB)      sub();
        if(i == nomeInstrucao.MULT)     mult();
        if(i == nomeInstrucao.DIVI)     divi();
        if(i == nomeInstrucao.INV)      inv();
        if(i == nomeInstrucao.AND)      and();
        if(i == nomeInstrucao.OR)       or();
        if(i == nomeInstrucao.NEG)      neg();
        if(i == nomeInstrucao.CME)      cme();
        if(i == nomeInstrucao.CMA)      cma();
        if(i == nomeInstrucao.CEQ)      ceq();  
        if(i == nomeInstrucao.CDIF)     cdif(); 
        if(i == nomeInstrucao.CMEQ)     cmeq(); 
        if(i == nomeInstrucao.CMAQ)     cmaq(); 
        if(i == nomeInstrucao.START)    start();
        if(i == nomeInstrucao.HLT)      hlt();
        if(i == nomeInstrucao.NULL)     nul();
        if(i == nomeInstrucao.RD)       rd();
        if(i == nomeInstrucao.PRN)      prn();
        if(i == nomeInstrucao.RETURN)   ret();
    }
    
    private void add()
    {
        
    }
    
    private void sub()
    {
        
    }
    
    private void mult()
    {
        
    }
    
    private void divi()
    {
        
    }
    
    private void inv()
    {
        
    }
    
    private void and()
    {
        
    }
    
    private void or()
    {
        
    }
    
    private void neg()
    {
        
    }
    
    private void cme()
    {
        
    }
    
    private void cma()
    {
        
    }
    
    private void ceq()
    {
        
    }
    
    private void cdif()
    {
        
    }
    
    private void cmeq()
    {

    }   
    
    private void cmaq()
    {
        
    }
    
    private void start()
    {
        
    }
    
    private void hlt()
    {
        fim = true;
    }
    
    private void nul()
    {
        
    }
    
    private void rd()
    {
        
    }
    
    private void prn()
    {
        
    }
    
    private void ret()
    {
        
    }
    
    private void executaExpressaoComposta(InstrucaoComposta instrucao)
    {
        
    }
    
    private void executaExpressaoDuplamenteComposta(InstrucaoDuplamenteComposta instrucao)
    {
        
    }
}
