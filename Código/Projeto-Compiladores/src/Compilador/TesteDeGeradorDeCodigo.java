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
            GeradorDeCodigo.init(new File("C:\\Users\\lucas\\Documents\\MV"));
            GeradorDeCodigo.getInstance().geraComando(Comandos.ADD);
            GeradorDeCodigo.getInstance().close();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(TesteDeGeradorDeCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
