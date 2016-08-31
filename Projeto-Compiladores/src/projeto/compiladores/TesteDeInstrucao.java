/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.compiladores;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class TesteDeInstrucao {
    
    public static void main(String [] args)
    {
        int count = 0;
        InstrucaoSimples i = null;
        InstrucaoComposta iU = null;
        InstrucaoDuplamenteComposta iD = null;
        for(nomeInstrucao l : nomeInstrucao.values())
        {
            count = (int) Math.round((Math.random() - 0.5) * 10);
            try{
                i = new InstrucaoSimples(l, 0); 
                System.out.println(i.getInstrucao().toString());
                System.out.println(""+i.getLinha());
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println(""+count);
            }
        }
        
        for(nomeInstrucao l : nomeInstrucao.values())
        {
            count = (int) Math.round((Math.random() - 0.5) * 10);
            try{
                iU = new InstrucaoComposta(l, 0, count*2); 
                System.out.println(iU.getInstrucao().toString()+"\t"+iU.getParam1());
                System.out.println(""+iU.getLinha());
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println(""+count);
            }
        }
        
        for(nomeInstrucao l : nomeInstrucao.values())
        {
            count = (int) Math.round((Math.random() - 0.5) * 10);
            try{
                iD = new InstrucaoDuplamenteComposta(l, 0, 0, count*2); 
                System.out.println(iD.getInstrucao().toString()+"\t"+iD.getParam1()+", "+iD.getParam2());
                System.out.println(""+iD.getLinha());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println(""+count);
            }      
        }
    }
}
