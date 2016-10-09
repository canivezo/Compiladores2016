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
            analisaChamadaProcedimento();
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
    
    public void analisaEscreva () throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == 22)  //sabreparenteses
        {
            proximoToken();
            if(token.simboloToCode() == 17) //sidentificador
            {
                //SEMANTICO
                proximoToken();
                if(token.simboloToCode() == 23)  //sfechaparenteses
                {
                    proximoToken();
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
        else
        {
            erro.erroSintatico(token.getLinha());
        }
    }
    
    public void analisaEnquanto () throws Exception
    {
        //SEMANTICO
        proximoToken();
        analisaExpressao();
        if(token.simboloToCode() == 10) //sfaca
        {
            //SEMANTICO
            proximoToken();
            analisaComandoSimples();
            //SEMANTICO
        }
        else
            {
            erro.erroSintatico(token.getLinha());
            }
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
            if(token.simboloToCode() == 4)  //sprocedimento
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
    
    public void analisaDeclaracaoProcedimento () throws Exception
    {
        proximoToken();
        //SEMANTICO
        if(token.simboloToCode() == 17)  //sidentificador
           {
            //SEMANTICO
            proximoToken();
            if(token.simboloToCode() == 20)  //spontovirgula
                {
                analisaBloco();
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
    
    public void analisaDeclaracaoFuncao () throws Exception
    {
        proximoToken();
        //SEMANTICO
        if(token.simboloToCode() == 17)  //sidentificador
        {
            //SEMANTICO
            proximoToken();
            if(token.simboloToCode() == 37)  //sdoispontos
            {
                proximoToken();
                if((token.simboloToCode() == 15) || (token.simboloToCode() == 16))  //sinteiro ou sbooleano
                {
                   //SEMANTICO
                   proximoToken();
                   if(token.simboloToCode() == 20)  //spontovirgula
                   {
                       analisaBloco();
                   } //DUVIDA Se existe um else aqui
                }
                else erro.erroSintatico(token.getLinha());
            }
            else erro.erroSintatico(token.getLinha());
        }
        else erro.erroSintatico(token.getLinha());
    }
    
    public void analisaExpressao () throws Exception
    {
        analisaExpressaoSimples();
        // smaior ou smaiorig ou sig ou smenor ou smenorig ou sdif
        if((token.simboloToCode() == 24) || (token.simboloToCode() == 25) || (token.simboloToCode() == 26) || (token.simboloToCode() == 27) || (token.simboloToCode() == 28) || (token.simboloToCode() == 29))
        {
            proximoToken();
            analisaExpressaoSimples();
        }
    }
    
    public void analisaExpressaoSimples () throws Exception
    {
        if((token.simboloToCode() == 30) || (token.simboloToCode() == 31))  //smais ou smenos
        {
            proximoToken();
            analisaTermo();
            while((token.simboloToCode() == 30) || (token.simboloToCode() == 31) || (token.simboloToCode() == 35))  //smais ou smenos ou sou
            {
                proximoToken();
                analisaTermo();
            }
        }
    }
    
    public void analisaTermo () throws Exception
    {
        analisaFator();
        while((token.simboloToCode() == 32) || (token.simboloToCode() == 33) || (token.simboloToCode() == 6))  //smult ou sdiv ou sse
        {
            proximoToken();
            analisaFator();
        }
    }
    
    public void analisaFator () throws Exception
    {
        if(token.simboloToCode() == 17)  //sidentificador
        {
            //SEMANTICO
            analisaChamadaFuncao();
        }
        else
        {
            if(token.simboloToCode() == 18)  //snumero
            {
                proximoToken();
            }
            else
            {
                if(token.simboloToCode() == 36)  //snao
                {
                    proximoToken();
                    analisaFator();
                }
                else
                {
                    if(token.simboloToCode() == 22)  //sabreparenteses
                    {
                        proximoToken();
                        analisaExpressao();
                        if(token.simboloToCode() == 23)  //sfechaparenteses
                        {
                            proximoToken();
                        }
                        else
                        {
                            erro.erroSintatico(token.getLinha());
                        }
                    }
                    else
                    {
                        if((token.getLexema().equals("verdadeiro")) || (token.getLexema().equals("falso")))
                        {
                            proximoToken();
                        }
                        else
                        {
                            erro.erroSintatico(token.getLinha());
                        }
                    }
                }
            }
        }
    }
    
    public void analisaChamadaProcedimento ()
    {
        
    }
    
    public void analisaChamadaFuncao () throws Exception
    {
        proximoToken();
    }
        
}
