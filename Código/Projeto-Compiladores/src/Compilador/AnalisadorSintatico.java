/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;
import java.util.Vector;
/**
 *
 * @author Rubens
 */
public class AnalisadorSintatico
{
    Erro erro = null;
    Vector<Token> vetorDeTokens;
    int posicaoAtualNoVetor = 0;
    int finalDoVetor = 0;

    public AnalisadorSintatico() throws Exception
    {
        AnalisadorLexical lexico = new AnalisadorLexical("");
        vetorDeTokens = lexico.pegaTokens();
        finalDoVetor = vetorDeTokens.size();
    }
    
    public void analisaBloco ()
    {
        
    }
    
    public void analisaEtVariaveis ()
    {
        
    }
    
    public void analisaVariaveis ()
    {
        
    }
    
    public void analisaTipo (Token token)
    {
        if((token.simboloToCode() != 15) && (token.simboloToCode() != 16))
        {
            //erro
        }
            else
            {
            // Coloca_tipo_tabela(token.lexema
            }
    }
    
    public void analisaComandos (Token token)
    {
        if(token.simboloToCode() == 2)
        {
            
        }
    }
    
    public void analisaComandoSimples ()
    {
        
    }
    
    public void analisaAtribChProcedimento ()    
    {
        
    }
    
    public void analisaLeia ()
    {
        
    }
    
    public void analisaEscreva ()
    {
        
    }
    
    public void analisaEnquanto ()
    {
        
    }
    
    public void analisaSe ()
    {
        
    }
    
    public void analisaSubRotinas ()
    {
        
    }
    
    public void analisaDeclaracaoProcedimento ()
    {
        
    }
    
    public void analisaDeclaracaoFuncao ()
    {
        
    }
    
    public void analisaExpressao ()
    {
        
    }
    
    public void analisaExpressaoSimples ()
    {
        
    }
    
    public void analisaTermo ()
    {
        
    }
    
    public void analisaFator ()
    {
        
    }
    
    public void analisaSintatico ()
    {
        
    }
        
}
