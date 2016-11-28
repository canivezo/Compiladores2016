package Compilador;

import java.io.File;
import java.util.Formatter;

import Compilador.Comandos;

public class GeradorDeCodigo {
	
	private File arquivo;
	private Formatter out;
        private static GeradorDeCodigo singleToN = null; 
        
        /**
         * Retorna a instância do objeto. Assim ele é acessível a todas as classes.
         * @return instância do geradorDeCódigo. Pode ser null, dependendo se o objeto tiver sido instanciado ou nao.
         * @throws Exception 
         */
        public static GeradorDeCodigo getInstance() throws Exception
        {
            return singleToN;
        }
        
        /**
         * Inicializa o objeto.
         * @param source
         * @throws Exception 
         */
        public static void init(String source) throws Exception
        {
            singleToN = new GeradorDeCodigo(source);
        }
        
	private GeradorDeCodigo(String source) throws Exception 
        {
            arquivo = new File(source);
            if(arquivo.exists())
                arquivo.delete();
            
            arquivo.createNewFile();
            out = new Formatter(arquivo);
	}
	
	public void geraLabel(int n) 
        {
            out.format(Comandos.Label + n + "\tNULL\n");
            out.flush();
        }
	
	public void geraComando(String operador) 
        {
            out.format("\t" + operador + "\n");
            out.flush();
        }

	public void geraComando(String operador, String primeiroArg) 
        {
            out.format("\t" + operador + "\t" + primeiroArg + "\n");
            out.flush();
	}
	
        public void geraComando(String operador, int primeiroArg) 
        {
            out.format("\t" + operador + "\t" +  primeiroArg + "\n");
            out.flush();
	}
        
	public void geraComando(String operador, String primeiroArg, String segundoArg) 
        {
            out.format("\t" + operador + "\t" + primeiroArg + "," + segundoArg + "\n");
            out.flush();
	}
        
        public void geraComando(String operador, int primeiroArg, int segundoArg) 
        {
            out.format("\t" + operador + "\t" + primeiroArg + "," + segundoArg + "\n");
            out.flush();
	}
	
	public void close() 
        {
            out.close();
	}

        private Exception exception() 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}