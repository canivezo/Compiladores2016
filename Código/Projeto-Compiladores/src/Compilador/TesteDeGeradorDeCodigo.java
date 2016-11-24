package Compilador;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class TesteDeGeradorDeCodigo {
    
    public static void main(String [] args)
    {
        try 
        {
            GeradorDeCodigo gerador;
            GeradorDeCodigo.init(new File("C:\\Users\\Rubens\\Documents\\MV"));
            GeradorDeCodigo.getInstance().geraComando(Comandos.Start);
            GeradorDeCodigo.getInstance().geraComando(Comandos.Allocate,"0","2");
            GeradorDeCodigo.getInstance().geraLabel(1);
            GeradorDeCodigo.getInstance().geraComando(Comandos.LDV,2);
            GeradorDeCodigo.getInstance().geraComando(Comandos.HALT);
            GeradorDeCodigo.getInstance().close();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(TesteDeGeradorDeCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
