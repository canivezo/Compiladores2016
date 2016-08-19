//classe desenvolvida somente com o metodo main
//para testar se o arquivo está sendo lido ou não

package maquinaVirtual;

public class LerArquivo 
{
	public static void main(String[] args) 
	{		
		Arquivo lendo = new Arquivo();
		try 
		{
			lendo.lerArquivo("C:/Users/murilosilva/Desktop/teste1.txt"); 
		} catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
	}
}

