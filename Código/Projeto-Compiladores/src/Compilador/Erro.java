/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

/**
 * 
 * @author Rubens
 */
public class Erro 
{
    String msgErro;
    int linhaErro = 0;
    int type;
    
    
    public Erro ()
    {
    }
    
    public void erroLexico ()
    {
        System.out.println("Erro Lexico identificado na linha: " +linhaErro);
    }
    
    public void erroSintatico ()
    {
        System.out.println("Erro Sintatico identificado na linha: " +linhaErro);
    }
    
    public void erroSemantico ()
    {
        System.out.println("Erro Semantico identificado na linha: " +linhaErro);       
    }
    
   
    
}
