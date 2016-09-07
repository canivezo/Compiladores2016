/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.lexico;

import projeto.lexico.LeitorDeArquivo;

/**
 *
 * @author Murilo
 */
public class TesteLerCaracter 
{
    
    public static void main(String[] args) 
    {
        LeitorDeArquivo leendo = null;
        try 
        {
            leendo = new LeitorDeArquivo("C:\\Users\\Murilo\\Desktop\\file.txt");
        } catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}