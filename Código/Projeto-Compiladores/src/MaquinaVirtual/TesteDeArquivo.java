/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;

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
            lendo = new Arquivo("C:\\Users\\lucas\\Documents\\teste2.txt"); 
            i = lendo.parsearPalavras();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
        System.out.println(i);
    }
}
