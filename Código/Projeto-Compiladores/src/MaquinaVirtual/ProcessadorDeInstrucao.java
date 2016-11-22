/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author lucas
 */
public class ProcessadorDeInstrucao {
    
    private Vector<Instrucao> m_instrucoes = null;
    private Pilha m_pilha = null;
    private boolean fim = false;
    private int res = 0, varSegura = -1; //Recebe o resultado do procedimento ret();
    private String saida = null;
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
    
    public String getSaida()
    {
        return saida;
    }
    
    public void setSaida()
    {
        saida = null;
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
            executaInstrucao(m_instrucoes.get(m_instrucao));
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
        // M[s-1] := M[s-1] + M[s]; s := s-1
        res = m_pilha.getValor(2) + m_pilha.getValor(1);
        m_pilha.setValor(2, res);
        m_pilha.pop();
    }
    
    private void sub()
    {
        //M[s-1]:=M[s-1] - M[s]; s:=s - 1
        res = m_pilha.getValor(2) - m_pilha.getValor(1);
        m_pilha.setValor(2, res);
        m_pilha.pop();
    }
    
    private void mult()
    {
        //M[s-1]:=M[s-1] * M[s]; s:=s - 1
        res = m_pilha.getValor(2) * m_pilha.getValor(1);
        m_pilha.setValor(2, res);
        m_pilha.pop();
    }
    
    private void divi()
    {
        //M[s-1]:=M[s-1] div M[s]; s:=s - 1
        res = m_pilha.getValor(2) / m_pilha.getValor(1);
        m_pilha.setValor(2, res);
        m_pilha.pop();
    }
    
    private void inv()
    {
        //M[s]:= -M[s]
        res = -m_pilha.getValor(1);
        m_pilha.setValor(1, res);
    }
    
    private void and()
    {
        //se M [s-1] = 1 e M[s] = 1 então M[s-1]:=1 senão M[s-1]:=0; s:=s - 1
        if((m_pilha.getValor(2) == 1) && (m_pilha.getValor(1) == 1))
        {
            m_pilha.setValor(2, 1);
        }
        else
        {
            m_pilha.setValor(2, 0);
        }
        m_pilha.pop();
    }
    
    private void or()
    {
        //se M[s-1] = 1 ou M[s] = 1 então M[s-1]:=1 senão M[s-1]:=0; s:=s - 1
        if((m_pilha.getValor(2) == 1) || (m_pilha.getValor(1) == 1))
        {
            m_pilha.setValor(2, 1);
        }
        else
        {
            m_pilha.setValor(2, 0);
        }
        m_pilha.pop();
    }
    
    private void neg()
    {
        //M[s]:= 1-M[s]
        res = 1 - m_pilha.getValor(1);
        m_pilha.setValor(m_pilha.tamPilha()-1, res);
    }
    
    private void cme()
    {
        //se M[s-1] < M[s] então M[s-1]:=1 senão M[s-1]:=0; s:=s - 1
        if((m_pilha.getValor(2)) < (m_pilha.getValor(1)))
        {
            m_pilha.setValor(2, 1);
        }
        else
        {
            m_pilha.setValor(2, 0);
        }
        m_pilha.pop();
    }
    
    private void cma()
    {
        //se M[s-1] > M[s] então M[s-1]:=1 senão M[s-1]:=0; s:=s - 1
        if((m_pilha.getValor(2)) > (m_pilha.getValor(1)))
        {
            m_pilha.setValor(2, 1);
        }
        else
        {
            m_pilha.setValor(2, 0);
        }
        m_pilha.pop();
    }
    
    private void ceq()
    {
        //se M[s-1] = M[s] então M[s-1]:=1 senão M[s-1]:=0; s:=s - 1
        if((m_pilha.getValor(2)) == (m_pilha.getValor(1)))
        {
            m_pilha.setValor(2, 1);
        }
        else
        {
            m_pilha.setValor(2, 0);
        }
        m_pilha.pop();
    }
    
    private void cdif()
    {
        //se M[s-1] != M[s] então M[s-1]:=1 senão M[s-1]:=0; s:=s - 1
        if((m_pilha.getValor(2)) != (m_pilha.getValor(1)))
        {
            m_pilha.setValor(2, 1);
        }
        else
        {
            m_pilha.setValor(2, 0);
        }
        m_pilha.pop();
    }
    
    private void cmeq()
    {
        //se M[s-1] <= M[s] então M[s-1]:=1 senão M[s-1]:=0; s:=s - 1
        if((m_pilha.getValor(2)) <= (m_pilha.getValor(1)))
        {
            m_pilha.setValor(2, 1);
        }
        else
        {
            m_pilha.setValor(2, 0);
        }
        m_pilha.pop();
    }   
    
    private void cmaq()
    {
        //se M[s-1] >= M[s] então M[s-1]:=1 senão M[s-1]:=0; s:=s - 1
        if((m_pilha.getValor(2)) >= (m_pilha.getValor(1)))
        {
            m_pilha.setValor(2, 1);
        }
        else
        {
            m_pilha.setValor(2, 0);
        }
        m_pilha.pop();
    }
    
    private void start()
    {
        //S:=-1
        m_pilha.push(m_pilha.tamPilha(), -1);
    }
    
    private void hlt()
    {
        fim = true;
    }
    
    private void nul()
    {
        //nada a se fazer.
    }
    
    private void rd()
    {
        //S:=s+1; M[s]:=proxima entra
        String value = JOptionPane.showInputDialog(null, "Valor:", "Entrada de valor", JOptionPane.QUESTION_MESSAGE);
        res = Integer.parseInt(value);
        m_pilha.push(m_pilha.tamPilha(), res);
    }
    
    private void prn()
    {
        //M[s];s:=s-1;
        saida = String.valueOf(m_pilha.getValor(1));
        m_pilha.pop();
    }
    
    private void ret()
    {
        varSegura = m_pilha.getValor(1);
        m_pilha.pop();
    }
    
    private void executaExpressaoComposta(InstrucaoComposta instrucao)
    {
        nomeInstrucao i = instrucao.getInstrucao();
        if(i == nomeInstrucao.ADD)  ldc(instrucao.getParam1());
        if(i == nomeInstrucao.SUB)  ldv(instrucao.getParam1());
        if(i == nomeInstrucao.MULT) str(instrucao.getParam1());
        if(i == nomeInstrucao.DIVI) jmp(instrucao.getParam1());
        if(i == nomeInstrucao.INV)  jmpf(instrucao.getParam1());
        if(i == nomeInstrucao.AND)  call(instrucao.getParam1());
    }
    
    private void ldc(int param)
    {
        //S:=s + 1 ; M [s]: = k
        m_pilha.push(m_pilha.tamPilha(), param);
    }
    
    private void ldv(int param)
    {
        //S:=s + 1 ; M[s]:=M[n]
        m_pilha.push(m_pilha.tamPilha(), param);
    }
    
    private void str(int param)
    {
        //M[n]:=M[s]; s;=s-1;
        m_pilha.setValor(param, m_pilha.getValor(1));
    }
    
    private void jmp(int param)
    {
        //desviar sempre i:=att1
        m_instrucao = param;
    }
    
    private void jmpf(int param)
    {
        // desviar caso falso  se M[s]=0, entao i:=att1, senao i:=i+1
        res = m_pilha.getValor(1);
        if(res == 0)
        {
            m_instrucao = param;
        }
        else
            m_pilha.pop();
    }
    
    private void call(int param)
    {
        m_pilha.push(m_pilha.tamPilha(), m_instrucao +1);
        m_instrucao = param;
    }
    
    private void executaExpressaoDuplamenteComposta(InstrucaoDuplamenteComposta instrucao)
    {
        nomeInstrucao i = instrucao.getInstrucao();
        if(i == nomeInstrucao.ALLOC) alloc(instrucao.getParam1(), instrucao.getParam2());
        if(i == nomeInstrucao.DALLOC) dalloc(instrucao.getParam1(), instrucao.getParam2());
    }
    
    private void alloc(int p1, int p2)
    {
        //{s:=s + 1; M[s]:=M[m+k]}
        for(int k=0; k<(p2);k++)
        {
            m_pilha.push(m_pilha.tamPilha(), 0);
            m_pilha.setValor(1, p1+k);
        }
    }
    
    private void dalloc(int p1, int p2)
    {
        //{M[m+k]:=M[s]; s:=s - 1}
         for(int k =(p2-1);k>=0;k--)
         {
             m_pilha.setValor(p1+k, m_pilha.getValor(p1+k));
             m_pilha.pop();
         }
    }
}
