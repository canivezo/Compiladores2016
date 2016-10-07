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
    Token token = null;
    Erro erro = new Erro();
    Vector<Token> vetorDeTokens;
    int posicaoAtualNoVetor = 0;
    int finalDoVetor = 0;
    AnalisadorLexical lexico;

    public AnalisadorSintatico(String caminhoArquivo) throws Exception
    {
        lexico = new AnalisadorLexical(caminhoArquivo);
        vetorDeTokens = lexico.pegaTokens();
        finalDoVetor = vetorDeTokens.size();
        token = lexico.pegaToken(posicaoAtualNoVetor);  //Recebe primeiro token
        
        if(token.simboloToCode() == 1)  //sprograma
            {
            proximoToken();
            if(token.simboloToCode() == 17)  //sidentificador
                {
                //insereTabela no semantico
                proximoToken();
                if(token.simboloToCode() == 20) //spontovirgula
                    {
                    analisaBloco();
                    if(token.simboloToCode() == 19)  //sponto
                        {
                        System.out.println("Sucesso!");
                        }
                        else erro.erroSintatico();
                    }
                    else erro.erroSintatico();
                }
                else erro.erroSintatico();
            }
            else erro.erroSintatico();
    }
    
    private void proximoToken() throws Exception
    {
        if(posicaoAtualNoVetor <= finalDoVetor)
        {
        posicaoAtualNoVetor++;
        token = lexico.pegaToken(posicaoAtualNoVetor);
        }
        else
        throw new Exception("Erro, Final do vetor de tokens atingido.");
    }
    
    public void analisaBloco () throws Exception
    {
        proximoToken();
        analisaEtVariaveis();
        analisaSubRotinas();
        analisaComandos();
    }
    
    public void analisaEtVariaveis () throws Exception
    {
        if(token.simboloToCode() == 14)
        {
            proximoToken();
            if(token.simboloToCode() == 17)
            {
                while(token.simboloToCode() == 17)
                {
                    analisaVariaveis();
                    if(token.simboloToCode() == 20)
                    {
                        proximoToken();
                    }
                    else
                    {
                        erro.erroSemantico();
                    }
                }
            }
            else
            {
                erro.erroSemantico();
            }
        }
    }
    
    public void analisaVariaveis () throws Exception
    {
        
    }
    
    public void analisaTipo () throws Exception
    {
        if((token.simboloToCode() != 15) && (token.simboloToCode() != 16))
        {
            erro.erroSintatico();
        }
            else
            {
            // Coloca_tipo_tabela(token.lexema)
            }
    }
    
    public void analisaComandos () throws Exception
    {
        if(token.simboloToCode() == 2)
        {
            proximoToken();
            analisaComandoSimples();
            while(token.simboloToCode()!= 3)
            {
                if(token.simboloToCode() == 20)
                {
                    proximoToken();
                    if(token.simboloToCode() != 3)
                    {
                        analisaComandoSimples();
                    }
                    else
                    {
                        erro.erroSintatico();
                    }
                }
                else
                {
                    proximoToken();
                }
            }
            
        }
        else
        {
          erro.erroSintatico();  
        }
    }
    
    public void analisaComandoSimples () throws Exception
    {
        if(token.simboloToCode() == 17)  // sidentificador
        {
            analisaAtribChProcedimento();
        }
        else
        {
            if(token.simboloToCode() == 6)
            {
                analisaSe();
            }
            else
            {
                if(token.simboloToCode() == 9)
                {
                    analisaEnquanto();
                }
                else
                {
                    if(token.simboloToCode() == 13)
                    {
                        analisaLeia();
                    }
                    else
                    {
                        if(token.simboloToCode() == 12)
                        {
                            analisaEscreva();
                        }
                        else
                        {
                            analisaComandos();
                        }
                    }
                }
            }
        }
    }
    
    public void analisaAtribChProcedimento () throws Exception   
    {
        proximoToken();
        if(token.simboloToCode() == 11)  // satribuicao
        {
            analisaAtribuicao();
        }
        else
        {
            chamadaProcedimento();
        }
    }
    
    public void analisaAtribuicao ()
    {
        
    }
    
    public void analisaLeia () throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == 17)  // sidentificador
        {
            //pesquisa_declvar_tabela(token.lexema) SEMANTICO
        }
            
    }
    
    public void analisaEscreva ()
    {
        
    }
    
    public void analisaEnquanto ()
    {
        
    }
    
    public void analisaSe () throws Exception
    {
        proximoToken();
        analisaExpressao();
        if(token.simboloToCode() == 7)  //sentao
        {
            proximoToken();
            analisaComandoSimples();
            if(token.simboloToCode() == 8)  //ssenao
            {
                proximoToken();
                analisaComandoSimples();
            }
        }
        else
        {
            erro.erroSintatico();
        }
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
    
    public void chamadaProcedimento ()
    {
        
    }
    
    public void chamadaFuncao ()
    {
        
    }
        
}
