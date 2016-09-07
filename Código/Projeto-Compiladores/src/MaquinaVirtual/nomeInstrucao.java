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
public enum nomeInstrucao {
    ADD, SUB, MULT, DIVI, INV, AND, OR, NEG, CME, CMA, CEQ, CDIF, CMEQ, CMAQ, START, HLT, NULL, RD, PRN, RETURN,
    LDC, LDV, STR, JMP, JMPF, CALL,
    ALLOC, DALLOC;
    
    public static int getInstructionType(nomeInstrucao p)
    {
        if(p.compareTo(LDC) < 0)
        {
           return 0;
        }
        if(p.compareTo(ALLOC) < 0)
        {
            return 1;
        }
        return 2;
    }
    
    public static nomeInstrucao getNomeInstrucao(String i) throws Exception
    {
        switch (i.toUpperCase()) 
        {
            case "ADD":   return ADD;
            case "SUB":   return SUB;
            case "MULT":  return MULT;
            case "DIVI":  return DIVI;
            case "INV":   return INV;
            case "AND":   return AND;
            case "OR":    return OR;
            case "NEG":   return NEG;
            case "CME":   return CME;
            case "CMA":   return CMA;
            case "CEQ":   return CEQ;
            case "CDIF":  return CDIF;
            case "CMEQ":  return CMEQ;
            case "CMAQ":  return CMAQ;
            case "START": return START;
            case "HLT":   return HLT;
            case "RD":    return RD;
            case "PRN":   return PRN;

            case "LDC":   return LDC;
            case "LDV":   return LDV;
            case "STR":   return STR;
            case "JMP":   return JMP;
            case "JMPF":  return JMPF;
            case "CALL":  return CALL;

            case "ALLOC": return ALLOC;
            case "DALLOC":return DALLOC;

            case "RETURN":return RETURN;
            
            default: throw new Exception("Invalido");
        }
    }
}
