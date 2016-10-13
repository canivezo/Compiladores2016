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
    int linha = 0;
    int type;
    
    
    public Erro ()
    {
    }
    
    public void erroLexico (int linhaErro, int cod)
    {
        System.out.println("Erro Lexico identificado na linha: " +linhaErro+ "  Descrição: "+codErro(cod));
        System.exit(0);
    }
    
    public void erroSintatico (int linhaErro, int cod)
    {
        System.out.println("Erro Sintatico identificado na linha: " +linhaErro+ "  Descrição: "+codErro(cod));
        System.exit(0);
    }
    
    public void erroSemantico (int linhaErro, int cod)
    {
        System.out.println("Erro Semantico identificado na linha: " +linhaErro+ "  Descrição: "+codErro(cod));
        System.exit(0);
    }
    
   public String codErro (int cod)
   {
       switch(cod)
       {
           case 1:
               return "Falta ponto final";
           case 2:
               return "Falta ';'";
           case 3:
               return "Falta identificador";
           case 4:
               return "Falta identificador 'programa'";
           case 5:
               return "Falta ':'";
           case 6:
               return "Falta ',' ou ':'";
           case 7:
               return "Falta atribuição de tipo";
           case 8:
               return "Falta 'inicio'";
           case 9:
               return "Falta '('";
           case 10:
               return "Falta ')'";
           case 11:
               return "Erro na expressao'";
           case 12:
               return "Falta 'entao'";
           case 13:
               return "Falta 'verdeiro' ou 'falso'";
       }
       return "Erro nao encontrado";
   }
    
}
