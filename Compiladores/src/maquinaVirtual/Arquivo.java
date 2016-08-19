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
}




