/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.compiladores;

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
	private static int NumeroDeAtributos(String comando) 
	{
            switch (comando.toUpperCase()) 
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

	public Vector<String> parsearPalavras()
	{
            Vector<String> instrucao = new Vector<String>();		
            for(int i = 0; i < arquivo.size(); i++)
            {
                String [] result = arquivo.get(i).split("\t");
                if(NumeroDeAtributos(result[0]) == 0)  
                {
                    instrucao.add(result[0]);
                }
                
                if(NumeroDeAtributos(result[0]) == 1)  
                {
                    instrucao.add(result[0]);
                    instrucao.add(result[1]);
                }
                
                if(NumeroDeAtributos(result[0]) == 2)  
                {
                    instrucao.add(result[0]);
                    String [] aux = result[1].split(",");
                    instrucao.add(aux[0]);
                    instrucao.add(aux[1]);
                }
            }
            return instrucao;
	}
}

