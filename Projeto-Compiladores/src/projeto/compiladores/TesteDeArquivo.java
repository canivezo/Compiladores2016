/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.compiladores;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Murilo
 */
public class TesteDeArquivo {
    
    public static void main(String[] args) 
    {	
        Arquivo lendo = null;
        Vector <Instrucao> i = null;
        try 
        {
            lendo = new Arquivo("C:\\Users\\lucas\\Documents\\teste.txt"); 
        } catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
        try {
            i = lendo.parsearPalavras();
        } catch (Exception ex) {
            Logger.getLogger(TesteDeArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(i.get(0));
    }
}
