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
        token = vetorDeTokens.get(posicaoAtualNoVetor);  //Recebe primeiro token
        System.out.println("Simbolo: "+token.getSimbolo()+" Lexema: "+token.getLexema());
        
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
                        else erro.erroSintatico(token.getLinha());
                    }
                    else erro.erroSintatico(token.getLinha());
                }
                else erro.erroSintatico(token.getLinha());
            }
            else erro.erroSintatico(token.getLinha());
    }
    
    private void proximoToken() throws Exception
    {
        if(posicaoAtualNoVetor <= finalDoVetor)
        {
        posicaoAtualNoVetor++;
        token = vetorDeTokens.get(posicaoAtualNoVetor);
        System.out.println("Simbolo: "+token.getSimbolo()+" Lexema: "+token.getLexema());
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
        if(token.simboloToCode() == 14) //svar
        {
            proximoToken();
            if(token.simboloToCode() == 17) //sidentificador
            {
                while(token.simboloToCode() == 17)  //sidentificador
                {
                    analisaVariaveis();
                    if(token.simboloToCode() == 20)  //spontovirgula
                    {
                        proximoToken();
                    }
                    else
                    {
                        erro.erroSintatico(token.getLinha());
                    }
                }
            }
            else
            {
                erro.erroSintatico(token.getLinha());
            }
        }
    }
    
    public void analisaVariaveis () throws Exception
    {
        do
        {
        if(token.simboloToCode() == 17)  //sidentificador
        {
            proximoToken();
            if((token.simboloToCode() == 21) || (token.simboloToCode() == 37)) //svirgula ou sdoispontos
            {
                if(token.simboloToCode() == 21) //svirgula
                {
                    proximoToken();
                    if(token.simboloToCode() == 37)  //sdoispontos
                    {
                        erro.erroSintatico(token.getLinha());
                    }
                }
            }
            else 
                {
                erro.erroSintatico(token.getLinha());
                }
        }
        else 
            {
            erro.erroSintatico(token.getLinha());
            }
        }
        while(token.simboloToCode() != 37);  //sdoispontos
    proximoToken();
    analisaTipo();
    }
    
    public void analisaTipo () throws Exception
    {
        if((token.simboloToCode() != 15) && (token.simboloToCode() != 16))  //sinteiro e sbooleano
        {
            erro.erroSintatico(token.getLinha());
        }
            else
            {
            // Coloca_tipo_tabela(token.lexema)
            proximoToken();
            }
    }
    
    public void analisaComandos () throws Exception
    {
        if(token.simboloToCode() == 2)  //sinicio
        {
            proximoToken();
            analisaComandoSimples();
            while(token.simboloToCode()!= 3)  //sfim  
            {
                if(token.simboloToCode() == 20)  //spontovirgula
                {
                    proximoToken();
                    if(token.simboloToCode() != 3)  //sfim
                    {
                        analisaComandoSimples();
                    }
                    else
                    {
                        erro.erroSintatico(token.getLinha());
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
          erro.erroSintatico(token.getLinha());  
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
            if(token.simboloToCode() == 6) //sse
            {
                analisaSe();
            }
            else
            {
                if(token.simboloToCode() == 9)  //senquanto
                {
                    analisaEnquanto();
                }
                else
                {
                    if(token.simboloToCode() == 13)  //sleia
                    {
                        analisaLeia();
                    }
                    else
                    {
                        if(token.simboloToCode() == 12)  //sescreva
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
            erro.erroSintatico(token.getLinha());
        }
    }
    
    public void analisaSubRotinas () throws Exception
    {
        int flag = 0;
        if((token.simboloToCode() == 4) || (token.simboloToCode() == 5))  //sprocedimento ou sfuncao
        {
            //SEMANTICO
        }
        while((token.simboloToCode() == 4) || (token.simboloToCode() == 5))  //sprocedimento ou sfuncao
        {
            if(token.simboloToCode() ==4)  //sprocedimento
            {
                analisaDeclaracaoProcedimento();
            }
            else
                {
                    analisaDeclaracaoFuncao();
                }
            if(token.simboloToCode() == 20)  //spontovirgula
                {
                proximoToken();
                }
                else
                    {
                    erro.erroSintatico(token.getLinha());
                    }
        }
        if(flag == 1)
        {
            //SEMANTICO
        }
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
