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
    private AnalisadorSemantico semantico;

    public AnalisadorSintatico(String caminhoArquivo) throws Exception
    {
        lexico = new AnalisadorLexical(caminhoArquivo);
        vetorDeTokens = lexico.pegaTokens();
        finalDoVetor = vetorDeTokens.size();
        token = vetorDeTokens.get(posicaoAtualNoVetor);  //Recebe primeiro token
        semantico = new AnalisadorSemantico();
        System.out.println("Simbolo: "+token.getSimbolo()+" Lexema: "+token.getLexema()+"  linha: "+token.getLinha());
        
        if(token.simboloToCode() == 1)  //sprograma
        {
            proximoToken();
            //Gera START
            if(token.simboloToCode() == 17)  //sidentificador
            {
                semantico.insereTabela(new Simbolo(new Rotina("programa"), token));
                proximoToken();
                if(token.simboloToCode() == 20) //spontovirgula
                {
                    analisaBloco();
                    if(token.simboloToCode() == 19)  //sponto
                    {
                        System.out.println("Sucesso!");
                    }
                    else erro.erroSintatico(token.getLinha(),1);
                }
                else erro.erroSintatico(token.getLinha(),2);
            }
            else erro.erroSintatico(token.getLinha(),3);
        }
        else erro.erroSintatico(token.getLinha(),4);
    }
    
    private void proximoToken() throws Exception
    {
        if(posicaoAtualNoVetor <= finalDoVetor)
        {
            posicaoAtualNoVetor++;
            token = vetorDeTokens.get(posicaoAtualNoVetor);
            System.out.println("Simbolo: "+token.getSimbolo()+" Lexema: "+token.getLexema()+ "  linha: "+token.getLinha());
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
    
    public void analisaEtVariaveis() throws Exception
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
                        erro.erroSintatico(token.getLinha(),2);
                    }
                }
            }
            else
            {
                erro.erroSintatico(token.getLinha(),3);
            }
        }
    }
    
    public void analisaVariaveis() throws Exception
    {
        do
        {
            if(token.simboloToCode() == 17)  //sidentificador
            {
                semantico.insereTabela(new Simbolo(token));
                proximoToken();
                if((token.simboloToCode() == 21) || (token.simboloToCode() == 37)) //svirgula ou sdoispontos
                {
                    if(token.simboloToCode() == 21) //svirgula
                    {
                        proximoToken();
                        if(token.simboloToCode() == 37)  //sdoispontos
                        {
                            erro.erroSintatico(token.getLinha(),5);
                        }
                    }
                }
                else 
                {
                    erro.erroSintatico(token.getLinha(),6);
                }
            }
            else 
            {
                erro.erroSintatico(token.getLinha(),3);
            }
        }
        while(token.simboloToCode() != 37);  //sdoispontos
        proximoToken();
        analisaTipo();
    }
    
    public void analisaTipo() throws Exception
    {
        if((token.simboloToCode() != 15) && (token.simboloToCode() != 16))  //sinteiro e sbooleano
        {
            erro.erroSintatico(token.getLinha(),7);
        }
        else
        {
            semantico.colocaTipoTabela(new Var(token.getLexema()));
            proximoToken();
        }
    }
    
    public void analisaComandos() throws Exception
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
                }
                else
                {
                        erro.erroSintatico(token.getLinha(),2);
                }
            }
            proximoToken();
        }
        else
        {
            erro.erroSintatico(token.getLinha(),8);  
        }
    }
    
    public void analisaComandoSimples() throws Exception
    {
        switch (token.simboloToCode())
        {
            case 17:    // sidentificador
                analisaAtribChProcedimento();
                break;
            case 6:    // sse
                analisaSe();
                break;
            case 9:    //senquanto
                analisaEnquanto();
                break;
            case 13:    //sleia
                analisaLeia();
                break;
            case 12:    //sescreva
                analisaEscreva();
                break;
            default:
                analisaComandos();
                break;
        }
    }
    
    /**
     * Guardar o simbolo para fazer o str
     * Verificar se o identificador existe
     * @throws Exception 
     */
    public void analisaAtribChProcedimento() throws Exception   
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
    
    public void analisaAtribuicao() throws Exception
    {
        proximoToken();
        analisaExpressao();
    }
    
    public void analisaLeia() throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == 22)  // sabreparenteses
        {
            proximoToken();
            if(token.simboloToCode() == 17)  //sidentificador
            {
                //pesquisa_declvar_tabela(token.lexema) SEMANTICO
                proximoToken();
                if(token.simboloToCode() == 23)  //sfechaparenteses
                {
                    proximoToken();
                }
                else
                {
                    erro.erroSintatico(token.getLinha(),10);
                }
            }
            else
            {
                erro.erroSintatico(token.getLinha(),3);
            }
        }
        else
        {
            erro.erroSintatico(token.getLinha(),9);
        }
            
    }
    
    public void analisaEscreva() throws Exception
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
                    erro.erroSintatico(token.getLinha(),10);
                }
            }
            else
            {
                erro.erroSintatico(token.getLinha(),3);
            }
        }
        else
        {
            erro.erroSintatico(token.getLinha(),9);
        }
    }
    
    public void analisaEnquanto() throws Exception
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
            erro.erroSintatico(token.getLinha(),11);
        }
    }
    
    public void analisaSe() throws Exception
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
            erro.erroSintatico(token.getLinha(),12);
        }
    }
    
    public void analisaSubRotinas() throws Exception
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
                erro.erroSintatico(token.getLinha(),2);
            }
        }
        if(flag == 1)
        {
            //SEMANTICO
        }
    }
    
    public void analisaDeclaracaoProcedimento() throws Exception
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
                erro.erroSintatico(token.getLinha(),2);
            }
        }
        else
        {
            erro.erroSintatico(token.getLinha(),3);
        }
    }
    
    public void analisaDeclaracaoFuncao() throws Exception
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
                else erro.erroSintatico(token.getLinha(),7);
            }
            else erro.erroSintatico(token.getLinha(),5);
        }
        else erro.erroSintatico(token.getLinha(),3);
    }
    
    public void analisaExpressao() throws Exception
    {
        analisaExpressaoSimples();
        // smaior ou smaiorig ou sig ou smenor ou smenorig ou sdif
        if((token.simboloToCode() == 24) || (token.simboloToCode() == 25) || (token.simboloToCode() == 26) || (token.simboloToCode() == 27) || (token.simboloToCode() == 28) || (token.simboloToCode() == 29))
        {
            proximoToken();
            analisaExpressaoSimples();
        }
    }
    
    public void analisaExpressaoSimples() throws Exception
    {
        if((token.simboloToCode() == 30) || (token.simboloToCode() == 31))  //smais ou smenos
        {
            proximoToken();
        }
        analisaTermo();
        while((token.simboloToCode() == 30) || (token.simboloToCode() == 31) || (token.simboloToCode() == 35))  //smais ou smenos ou sou
        {
            proximoToken();
            analisaTermo();
        }
        
    }
    
    public void analisaTermo() throws Exception
    {
        analisaFator();
        while((token.simboloToCode() == 32) || (token.simboloToCode() == 33) || (token.simboloToCode() == 34))  //smult ou sdiv ou se
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
                            erro.erroSintatico(token.getLinha(),10);
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
                            erro.erroSintatico(token.getLinha(),13);
                        }
                        erro.erroSintatico(token.getLinha(),9);
                    }
                }
            }
        }
    }
    
    public void analisaChamadaProcedimento()
    {
        //SEMANTICO
    }
    
    public void analisaChamadaFuncao() throws Exception
    {
        //SEMANTICO
        proximoToken();
    }
        
}