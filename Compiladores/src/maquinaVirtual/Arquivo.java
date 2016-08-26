// classe desenvolvida para ler o arquivo

package maquinaVirtual;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Arquivo 
{
	public Vector<String> lerArquivo(String path) 
	{
		Vector<String> arquivo = new Vector<String>();
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
					} catch (IOException e) 
					{
						System.err.printf("Erro na leitura do arquivo: %s.\n",
						          e.getMessage());
					}
			  }
		}
		System.out.println(arquivo); 
		//quando o separador de atributo estiver pronto, é só chamar o método 
		//nesse return passando como parametro o arquivo, como no println 
		return null; 
	}
	public static int NumeroDeAtributos(String comando) 
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
				
				default: return 1;
		}

	}

	public Vector<String> parsearPalavras(Vector<String> arquivo)
	{
		Vector<String> instrucao = new Vector<String>();		
		for(int i=0; i<arquivo.size(); i++)
		{
			String [] result = arquivo.get(i).split(" ");
			if(NumeroDeAtributos(result[0])==0)  
			{
				instrucao.add(result[0]);
				instrucao.add("\\t");
			}
		}
		System.out.println(instrucao);
		return null;
	}
}




