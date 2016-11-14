package Compilador;

import java.io.File;
import java.util.Formatter;

import Compilador.Comandos;

public class GeradorDeCodigo {
	
	private File arquivo;
	private Formatter out;
	
	public GeradorDeCodigo(File source) throws Exception 
        {
		arquivo = new File(source.getPath() + ".vmobj");
		if(arquivo.exists())
			arquivo.delete();
		arquivo.createNewFile();
		
		out = new Formatter(arquivo);
	}
	
	public void geraLabel(int n) 
        {
		out.format(Comandos.Label + n + "\tNULL%n");
                System.out.println(Comandos.Label + n + "\tNULL\n");
        }
	
	public void geraComando(String operador) 
        {
            System.out.println("\t" + operador + "\n");
            out.format("\t" + operador + "%n");
                
        }

	public void geraComando(String operador, String primeiroArg) 
        {
	System.out.println("\t" + operador + "\t" + primeiroArg + "\n");
            out.format("\t" + operador + "\t" + primeiroArg + "%n");
	}
	
        public void geraComando(String operador, int primeiroArg) 
        {
            System.out.println("\t" + operador + "\t" +  primeiroArg + "\n");
		out.format("\t" + operador + "\t" +  primeiroArg + "%n");
	}
        
	public void geraComando(String operador, String primeiroArg, String segundoArg) 
        {
            System.out.println("\t" + operador + "\t" + primeiroArg + "\t" + segundoArg +  "\n");
		out.format("\t" + operador + "\t" + primeiroArg + "\t" + segundoArg + "%n");
	}
        
        public void geraComando(String operador, int primeiroArg, int segundoArg) 
        {
            System.out.println("\t" + operador + "\t" + primeiroArg + "\t" + segundoArg +  "\n");
		out.format("\t" + operador + "\t" + primeiroArg + "\t" + segundoArg + "%n");
	}
	
	public void close() 
        {
		out.close();
	}

}