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
public class TesteDeArquivo {
    
    public static void main(String[] args) 
    {	
        Arquivo lendo = null;
        try 
        {
            lendo = new Arquivo("C:\\Users\\Murilo\\Documents\\Teste.txt"); 
        } catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
        System.out.println(""+lendo.parsearPalavras());
    }
}
