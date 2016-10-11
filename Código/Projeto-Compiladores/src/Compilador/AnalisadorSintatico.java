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
    private Token token = null;
    private Erro erro = new Erro();
    private Vector<Token> vetorDeTokens;
    private int posicaoAtualNoVetor = 0;
    private int finalDoVetor = 0;
    private AnalisadorLexical lexico;
    
    private void proximoToken() throws Exception
    {
        if(posicaoAtualNoVetor < finalDoVetor)
        {
            token = vetorDeTokens.get(posicaoAtualNoVetor);
            System.out.println("Simbolo: "+token.getSimbolo()+" Lexema: "+token.getLexema());
            posicaoAtualNoVetor++;
        }
        else
        {
            throw new Exception("Erro, Final do vetor de tokens atingido.");
        }
    }
    
    public AnalisadorSintatico(String caminhoArquivo) throws Exception
    {
        lexico = new AnalisadorLexical(caminhoArquivo);
        vetorDeTokens = lexico.pegaTokens();
        finalDoVetor = vetorDeTokens.size();
        proximoToken();
        System.out.println("Simbolo: "+token.getSimbolo()+" Lexema: "+token.getLexema());
        
        if(token.simboloToCode() == Token.simboloToCode("sprograma"))  //sprograma
        {
            proximoToken();
            if(token.simboloToCode() == Token.simboloToCode("sidentificador"))  //sidentificador
            {
                //insereTabela no semantico
                proximoToken();
                if(token.simboloToCode() == Token.simboloToCode("spontovirgula")) //spontovirgula
                {
                    analisaBloco();
                    if(token.simboloToCode() == Token.simboloToCode("sponto"))  //sponto
                    {
                        System.out.println("Sucesso!");
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
        else 
        {
            erro.erroSintatico(token.getLinha());
        }
    }
    
    public void analisaBloco() throws Exception
    {
        proximoToken();
        analisaEtVariaveis();
        analisaSubRotinas();
        analisaComandos();
    }
    
    public void analisaEtVariaveis () throws Exception
    {
        if(token.simboloToCode() == Token.simboloToCode("svar")) //svar
        {
            proximoToken();
            if(token.simboloToCode() == Token.simboloToCode("sidentificador")) //sidentificador
            {
                while(token.simboloToCode() == Token.simboloToCode("sidentificador"))  //sidentificador
                {
                    analisaVariaveis();
                    if(token.simboloToCode() == Token.simboloToCode("spontovirgula"))  //spontovirgula
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
            if(token.simboloToCode() == Token.simboloToCode("sidentificador"))  //sidentificador
            {
                proximoToken();
                if((token.simboloToCode() == Token.simboloToCode("svirgula")) || (token.simboloToCode() == Token.simboloToCode("sdoispontos")))
                {
                    if(token.simboloToCode() == Token.simboloToCode("svirgula")) //svirgula
                    {
                        proximoToken();
                        if(token.simboloToCode() == Token.simboloToCode("sdoispontos"))  //sdoispontos
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
        while(token.simboloToCode() != Token.simboloToCode("sdoispontos"));  //sdoispontos
        proximoToken();
        analisaTipo();
    }
    
    public void analisaTipo() throws Exception
    {
        if((token.simboloToCode() != Token.simboloToCode("sinteiro")) && (token.simboloToCode() != Token.simboloToCode("sbooleano")))  //sinteiro e sbooleano
        {
            erro.erroSintatico(token.getLinha());
        }
        else
        {
            //Coloca_tipo_tabela(token.lexema)
            proximoToken();
        }
    }
    
    public void analisaComandos() throws Exception
    {
        if(token.simboloToCode() == Token.simboloToCode("sinicio"))  //sinicio
        {
            proximoToken();
            analisaComandoSimples();
            while(token.simboloToCode()!= Token.simboloToCode("sfim"))  //sfim  
            {
                if(token.simboloToCode() == Token.simboloToCode("spontovirgula"))  //spontovirgula
                {
                    proximoToken();
                    if(token.simboloToCode() != Token.simboloToCode("sfim"))  //sfim
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
        switch(token.simboloToCode())
        {
            case 17://sidentificador
                analisaAtribChProcedimento();
                break;
            case 6://sse
                analisaSe();
                break;
            case 9://senquanto
                analisaEnquanto();
                break;
            case 13://sleia
                analisaLeia();
                break;
            case 12://sescreva
                analisaEscreva();
                break;
            default:
                analisaComandos();           
        }
    }
    
    public void analisaAtribChProcedimento () throws Exception   
    {
        proximoToken();
        if(token.simboloToCode() == Token.simboloToCode("satribuicao"))  // satribuicao
        {
            analisaAtribuicao();
        }
        else
        {
            analisaChamadaProcedimento();
        }
    }
    
    public void analisaAtribuicao () throws Exception
    {
        proximoToken();
        analisaExpressao();
    }
    
    public void analisaLeia() throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == Token.simboloToCode("sabreparenteses"))  //sabreparenteses
        {
            proximoToken();
            if(token.simboloToCode() == Token.simboloToCode("sidentificador")) //sidentificador
            {
                //SEMANTICO
                proximoToken();
                if(token.simboloToCode() == Token.simboloToCode("sfechaparenteses"))  //sfechaparenteses
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
    
    public void analisaEscreva () throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == Token.simboloToCode("sabreparenteses"))  //sabreparenteses
        {
            proximoToken();
            if(token.simboloToCode() == Token.simboloToCode("sidentificador")) //sidentificador
            {
                //SEMANTICO
                proximoToken();
                if(token.simboloToCode() == Token.simboloToCode("sfechaparenteses"))  //sfechaparenteses
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
        if(token.simboloToCode() == Token.simboloToCode("sfaca")) //sfaca
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
    
    public void analisaSe() throws Exception
    {
        proximoToken();
        analisaExpressao();
        if(token.simboloToCode() == Token.simboloToCode("sentao"))  //sentao
        {
            proximoToken();
            analisaComandoSimples();
            if(token.simboloToCode() == Token.simboloToCode("ssenao"))  //ssenao
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
        if((token.simboloToCode() == Token.simboloToCode("sprocedimento")) || (token.simboloToCode() == Token.simboloToCode("sfuncao")))  //sprocedimento ou sfuncao
        {
            //SEMANTICO
        }
        while((token.simboloToCode() == Token.simboloToCode("sprocedimento")) || (token.simboloToCode() == Token.simboloToCode("sfuncao")))  //sprocedimento ou sfuncao
        {
            if(token.simboloToCode() == Token.simboloToCode("sprocedimento"))  //sprocedimento
            {
                analisaDeclaracaoProcedimento();
            }
            else
            {
                analisaDeclaracaoFuncao();
            }
            if(token.simboloToCode() == Token.simboloToCode("spontovirgula"))  //spontovirgula
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
        if(token.simboloToCode() == Token.simboloToCode("sidentificador"))  //sidentificador
        {
            //SEMANTICO
            proximoToken();
            if(token.simboloToCode() == Token.simboloToCode("spontovirgula"))  //spontovirgula
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
        if(token.simboloToCode() == Token.simboloToCode("sidentificador"))  //sidentificador
        {
            //SEMANTICO
            proximoToken();
            if(token.simboloToCode() == Token.simboloToCode("sdoispontos"))  //sdoispontos
            {
                proximoToken();
                if((token.simboloToCode() == Token.simboloToCode("sinteiro")) || (token.simboloToCode() == Token.simboloToCode("sbooleano")))  //sinteiro ou sbooleano
                {
                    //SEMANTICO
                    proximoToken();
                    if(token.simboloToCode() == Token.simboloToCode("spontovirgula"));  //spontovirgula
                    {
                        analisaBloco();
                    } //DUVIDA Se existe um else aqui
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
    
    public void analisaFator() throws Exception
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
