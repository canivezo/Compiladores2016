/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;

/**
 *
 * @author Murilo
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Arquivo 
{
        private Vector<String> arquivo;
	public Arquivo(String path) 
	{
            arquivo = new Vector<String>();
            BufferedReader reader = null;
            File caminho = new File(path);
            if (caminho.exists()) 
            {
                try 
                {
                    FileReader fileReader = new FileReader(path);
                    reader = new BufferedReader(fileReader);
                    String linhaAtual;

                    while ((linhaAtual = reader.readLine()) != null) 
                    {
                        arquivo.add(linhaAtual);

                    }
                } 
                catch (IOException e) 
                {
                     e.printStackTrace();
                } 
               finally 
               {
                    try 
                    {
                        if (reader != null)
                                reader.close();
                    } 
                    catch (IOException e) 
                    {
                        System.err.printf("Erro na leitura do arquivo: %s.\n",
                        e.getMessage());
                    }
               }
            } 
	}
	private static int numeroDeAtributos(String comando) 
	{
            switch (comando) 
            {
                case "ADD":   return 0;
                case "SUB":   return 0;
                case "MULT":  return 0;
                case "DIVI":  return 0;
                case "INV":   return 0;
                case "AND":   return 0;
                case "OR":    return 0;
                case "NEG":   return 0;
                case "CME":   return 0;
                case "CMA":   return 0;
                case "CEQ":   return 0;
                case "CDIF":  return 0;
                case "CMEQ":  return 0;
                case "CMAQ":  return 0;
                case "START": return 0;
                case "HLT":   return 0;
                case "RD":    return 0;
                case "PRN":   return 0;

                case "LDC":   return 1;
                case "LDV":   return 1;
                case "STR":   return 1;
                case "JMP":   return 1;
                case "JMPF":  return 1;
                case "CALL":  return 1;

                case "ALLOC": return 2;
                case "DALLOC":return 2;

                case "RETURN":return 0;

                default: return 0;
            }
	}

	public Vector<Instrucao> parsearPalavras() throws Exception
	{
            Vector<Instrucao> instrucoes = new Vector<Instrucao>();
            Vector<Label> labels = new Vector<Label>();
            int parametro1 =0, parametro2 = 0;
            
            /*
             * parseando o arquivo para os labels 
             */
            for(int i = 0; i < arquivo.size();i++)
            {
                String [] l = arquivo.get(i).split("\t");
                String toRes = "";
                if(!l[0].equals(""))
                {
                    labels.add(new Label(i, l[0]));
                }
                for(int j = 1; j < l.length; j++)
                {    
                    toRes += l[j];
                    toRes += "\t";
                    arquivo.set(i, toRes);
                }
            }
            
            /*
             * pegando os comandos
             */
            for(int i = 0; i < arquivo.size(); i++)
            {
                String [] result = arquivo.get(i).split("\t");
                if(numeroDeAtributos(result[0]) == 0)  
                {
                    InstrucaoSimples e = new InstrucaoSimples(nomeInstrucao.getNomeInstrucao(result[0]), i);
                    instrucoes.add(e);
                }
                
                if(numeroDeAtributos(result[0]) == 1)  
                {
                    if(result[0].equals("JMP") || result[0].equals("JMPF") || result[0].equals("CALL"))
                    {
                       int j;
                       for(j = 0; j < labels.capacity();j++)
                       {
                           if(labels.get(j).getLabel().equals(result[1]))
                               break;
                       }
                       if(j == labels.capacity())
                           throw new Exception("label invalido");
                       
                       parametro1 = labels.get(j).getLinha();
                    }
                    else
                    {
                        parametro1 = Integer.parseInt(result[1]); 
                    }
                    InstrucaoComposta e = new InstrucaoComposta(nomeInstrucao.getNomeInstrucao(result[0]), i, parametro1);
                    instrucoes.add(e);
                }
                
                if(numeroDeAtributos(result[0]) == 2)  
                {
                    String [] aux = result[1].split(",");
                    parametro1 = Integer.parseInt(aux[0]);
                    parametro2 = Integer.parseInt(aux[1]);
                    InstrucaoDuplamenteComposta e = new InstrucaoDuplamenteComposta(nomeInstrucao.getNomeInstrucao(result[0]), i, parametro1, parametro2);
                    instrucoes.add(e);
                }
            }
            return instrucoes;
	}
}

