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
    private boolean isFunc = false;
    private boolean wasFuncAtrib = false;
    /**
     * Recebe o caminho do arquivo e começa a compilação.
     * programa teste;
     * bloco()
     * fim
     * .
     * Vai para o analisa bloco após ler programa e o nome do programa
     * @param caminhoArquivo
     * @throws Exception 
     */
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
            GeradorDeCodigo.getInstance().geraComando(Comandos.Start);
            GeradorDeCodigo.getInstance().geraComando(Comandos.Allocate, 0, 1);
            if(token.simboloToCode() == 17)  //sidentificador
            {
                semantico.insereTabela(new Simbolo(new Rotina("programa"), token));
                proximoToken();
                if(token.simboloToCode() == 20) //spontovirgula
                {
                    analisaBloco();
                    if(token.simboloToCode() == 19)  //sponto
                    {
                        GeradorDeCodigo.getInstance().geraComando(Comandos.DALLOC, 0, 1);
                        GeradorDeCodigo.getInstance().geraComando(Comandos.HALT);
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
    
    /**
     * Função auxiliar que pega o próximo token
     * @throws Exception 
     */
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
    
    /**
     * É chamado pelo começo do programa, e as sub-rotinas
     * @throws Exception 
     */
    public void analisaBloco () throws Exception
    {
        proximoToken();
        analisaEtVariaveis();
        analisaSubRotinas();
        analisaComandos();
    }
    
    /**
     * Função que lê as variáveis.
     * @throws Exception 
     */
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
    
    /**
     * Este método lê cada um dos identificadores das variáveis.
     * Aqui são adicionasdas todas as variáveis no semântico.
     * @throws Exception 
     */
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
    
    /**
     * Depois de ler uma liha de declaração de variáveis, o analisa tipo é chamado. 
     * Por isso ele chama a tabela de símbolos e adiciona os tipos de todas as variáveis 
     * que foram adicionadas anteriormente.
     * @throws Exception 
     */
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
    
    /**
     * Só começa e termina um escopo
     * @throws Exception 
     */
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
                    // Se encontrar mais de uma atribuição no mesmo bloco vai parar aqui
                    if(wasFuncAtrib) throw new Exception("Atribuição de fução repetida");
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
            if(isFunc)
            {
                if(!wasFuncAtrib)
                {
                    throw new Exception("Último comando não era a atribuição da função");
                }
                wasFuncAtrib = false;
            }
            proximoToken();
        }
        else
        {
            erro.erroSintatico(token.getLinha(),8);  
        }
    }
    
    /**
     * Chama cada comando conforme o símbolo lido.
     * @throws Exception 
     */
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
     * Guardar o token para fazer o str.
     * Verificar se o identificador existe.
     * Gera um str no endereço de memória dele.
     * @throws Exception 
     */
    public void analisaAtribChProcedimento() throws Exception   
    {
        //Simbolo onde ocorre o STR, ou o label da chamada de proc
        Token t = token;
        proximoToken();
        if(token.simboloToCode() == 11)  // satribuicao
        {
            if(semantico.pesquisaDeclVarTabela(t))
            {
                analisaAtribuicao(t);
            }
            else
                throw new Exception("Var não existe");
        }
        else
        {            
            analisaChamadaProcedimento(t);
        }
    }
    
    /**
     * Apenas começa e termina a expressão.
     * O resultado da expressão vai estar no topo da pilha.
     * Aqui fica o STR 0 da Func
     * @param t
     * @throws Exception 
     */
    public void analisaAtribuicao(Token t) throws Exception
    {
        //Verificar se a atribuição é válida
        proximoToken();
        semantico.comecaExpressao();
        analisaExpressao();
        semantico.terminaExpressao();
        if(semantico.getSimboloType(t).getType().equals("inteiro") || semantico.getSimboloType(t).getType().equals("booleano")) //Var
        {
            if(semantico.booleanExp() && semantico.getSimboloType(t).getType().equals("booleano")) //expressão booleana e var booleana
            {
                GeradorDeCodigo.getInstance().geraComando(Comandos.STR, semantico.getSimboloType(t).getInfo());
            }
            else
            {
                if(!semantico.booleanExp() && semantico.getSimboloType(t).getType().equals("inteiro")) //expressão inteira e var inteira
                {
                    GeradorDeCodigo.getInstance().geraComando(Comandos.STR, semantico.getSimboloType(t).getInfo());
                }
                else
                {
                    throw new Exception("Tipo da Expressão inválida");
                }
            }
        }
        else // booleano
        {
            //Aqui é gerado o str0
            if(semantico.booleanExp() && semantico.getSimboloType(t).getType().equals("funcBool")) //expressão booleana e função booleana
            {
                wasFuncAtrib = true;
                GeradorDeCodigo.getInstance().geraComando(Comandos.STR, 0);
            }
            else
            {
                if(!semantico.booleanExp() && semantico.getSimboloType(t).getType().equals("funcInt")) //expressão inteira e função inteira
                {
                    wasFuncAtrib = true;
                    GeradorDeCodigo.getInstance().geraComando(Comandos.STR, 0);
                }
                else
                {
                    throw new Exception("Tipo da Expressão inválida");
                }
            }
        }
    }
    
    /**
     * Gera o rd e dá um str no endereço da variável.
     * @throws Exception 
     */
    public void analisaLeia() throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == 22)  // sabreparenteses
        {
            proximoToken();
            if(token.simboloToCode() == 17)  //sidentificador
            {
                if(!semantico.pesquisaDeclVarTabela(token))
                {
                    throw new Exception("sidentificador nao encontrado");
                }
                GeradorDeCodigo.getInstance().geraComando(Comandos.RD);
                GeradorDeCodigo.getInstance().geraComando(Comandos.STR, Comandos.Label+""+semantico.getSimboloType(token).getInfo());
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
    
    /**
     * Gera um LDV ou um CALL e LDV 0 (Esses comandos quem gera é o aanalisaChamadaDeFunc) e gera o comando de escrita da MV.
     * @throws Exception 
     */
    public void analisaEscreva() throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == 22)  //sabreparenteses
        {
            proximoToken();
            if(token.simboloToCode() == 17) //sidentificador
            {
                if(!semantico.pesquisaDeclVarFunc(token))
                {
                    throw new Exception("Identificador não existe");
                }
                else
                {
                    if(semantico.getSimboloType(token).getClass() == Rotina.class)
                    {
                        GeradorDeCodigo.getInstance().geraComando(Comandos.CALL, Comandos.Label+""+semantico.getSimboloType(token).getInfo());
                        GeradorDeCodigo.getInstance().geraComando(Comandos.LDV, 0);
                    }
                    else
                    {
                        GeradorDeCodigo.getInstance().geraComando(Comandos.LDV, semantico.getSimboloType(token).getInfo());
                    }
                }
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
    
    /**
     * Gera os labels para o salto se verdadeiro ou o salto se falso.
     * @throws Exception 
     */
    public void analisaEnquanto() throws Exception
    {
        int label1 = semantico.getLabel(), label2 = semantico.getLabel();
        
        //Label principal do while
        GeradorDeCodigo.getInstance().geraLabel(label1);
        
        proximoToken();
        semantico.comecaExpressao();
        analisaExpressao();
        semantico.terminaExpressao();
        
        if(semantico.booleanExp())
        {
            if(token.simboloToCode() == 10) //sfaca
            {
                //Salto para o label do fim se a condição for falsa
                GeradorDeCodigo.getInstance().geraComando(Comandos.JMPF, label2);
                proximoToken();
                analisaComandoSimples();
                //Salto para o label principal, para validar o while de novo
                GeradorDeCodigo.getInstance().geraComando(Comandos.JUMP, label2);
                //Label do fim
                GeradorDeCodigo.getInstance().geraLabel(label2);
            }
            else
            {
                erro.erroSintatico(token.getLinha(),11);
            }
        }
        else
        {
            throw new Exception("Expressao invalida, nao e booleana");
        }
    }
    
    /**
     * Gera os labels do salto.
     * @throws Exception 
     */
    public void analisaSe() throws Exception
    {
        proximoToken();
        semantico.comecaExpressao();
        analisaExpressao();
        semantico.terminaExpressao();
        if(semantico.booleanExp())
        {
            //Está errado
            int labelSenao = semantico.getLabel();
            int labelSe = semantico.getLabel();
            GeradorDeCodigo.getInstance().geraComando(Comandos.JMPF, Comandos.Label+""+labelSenao);
            if(token.simboloToCode() == 7)  //sentao
            {
                proximoToken();
                analisaComandoSimples();
                GeradorDeCodigo.getInstance().geraComando(Comandos.JUMP, Comandos.Label+""+labelSe);
                GeradorDeCodigo.getInstance().geraLabel(labelSenao);
                if(token.simboloToCode() == 8)  //ssenao
                {
                    proximoToken();
                    analisaComandoSimples();
                }
                GeradorDeCodigo.getInstance().geraLabel(labelSe);
            }
            else
            {
                erro.erroSintatico(token.getLinha(),12);
            }    
        }
        else
        {
            throw new Exception("Expressã não é booleana");
        }
    }
    
    /**
     * Gera os labels de salto para a rotina onde está sendo declarada essa rotina.
     * @throws Exception 
     */
    public void analisaSubRotinas() throws Exception
    {
        int flag = 0;
        int label = 0;
        if((token.simboloToCode() == 4) || (token.simboloToCode() == 5))  //sprocedimento ou sfuncao
        {
            flag = 1;
            //Pega um label para pular a rotina
            label = semantico.getLabel();
            GeradorDeCodigo.getInstance().geraComando(Comandos.JUMP, label);
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
            GeradorDeCodigo.getInstance().geraLabel(label);
        }
    }
    
    /**
     * 
     * @throws Exception 
     */
    public void analisaDeclaracaoProcedimento() throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == 17)  //sidentificador
        {
            if(!semantico.pesquisaDeclProc(token))
            {
                semantico.insereTabela(new Simbolo(new Rotina("proc"), token));
                
                //Label do procedimento
                GeradorDeCodigo.getInstance().geraLabel(semantico.getSimboloType(token).getInfo());
                proximoToken();
                if(token.simboloToCode() == 20)  //spontovirgula
                {
                    analisaBloco();
                }
                else
                {
                    erro.erroSintatico(token.getLinha(),2);
                }
                GeradorDeCodigo.getInstance().geraComando(Comandos.RETURN);
            }
            else
            {
                throw new Exception("Sidentificador invalido, var no declarada");
            }
        }
        else
        {
            erro.erroSintatico(token.getLinha(),3);
        }
        
        semantico.finalizaEscopo();
        GeradorDeCodigo.getInstance().geraComando(Comandos.RETURN);
    }
    
    /**
     * 
     * @throws Exception 
     */
    public void analisaDeclaracaoFuncao() throws Exception
    {
        proximoToken();
        if(token.simboloToCode() == 17)  //sidentificador
        {
            if(!semantico.pesquisaDeclVarFunc(token))
            {
                Token func = token;
                semantico.insereTabela(new Simbolo(func));
                proximoToken();
                if(token.simboloToCode() == 37)  //sdoispontos
                {
                    proximoToken();
                    if((token.simboloToCode() == 15) || (token.simboloToCode() == 16))  //sinteiro ou sbooleano
                    {
                        if(token.simboloToCode() == 15)
                            semantico.colocaTipoFuncTabela("funcInt");
                        else
                            semantico.colocaTipoFuncTabela("funcBool");
                       
                        //Label da funcao
                        GeradorDeCodigo.getInstance().geraLabel(semantico.getSimboloType(func).getInfo());
                        proximoToken();
                        if(token.simboloToCode() == 20)  //spontovirgula
                        {
                            isFunc = true;//Mexer para garantir que o ultimo comando é atribuição da função
                            analisaBloco();
                            isFunc = false;
                        }
                        else
                        {
                            erro.erroSintatico(token.getLinha(),2);
                        }
                    }
                    else erro.erroSintatico(token.getLinha(),7);
                }
                else erro.erroSintatico(token.getLinha(),5);
            }
            else
            {
                throw new Exception("Sidentificador invalido, funcao já existe");
            }
        }
        else
        {
            erro.erroSintatico(token.getLinha(),3);
        }
        
        semantico.finalizaEscopo();
        GeradorDeCodigo.getInstance().geraComando(Comandos.RETURN);
    }
    
    /**
     * Apenas passa a responsabilidade para ae funções de analise de expressão.
     * @throws Exception 
     */
    public void analisaExpressao() throws Exception
    {
        analisaExpressaoSimples();
        // smaior ou smaiorig ou sig ou smenor ou smenorig ou sdif
        if((token.simboloToCode() == 24) || (token.simboloToCode() == 25) || (token.simboloToCode() == 26) || (token.simboloToCode() == 27) || (token.simboloToCode() == 28) || (token.simboloToCode() == 29))
        {
            semantico.adicionaOperadorNaExpressao(token);
            proximoToken();
            analisaExpressaoSimples();
        }
    }
    
    /**
     * Deve adicionar os operadores na tabela de símbolos.
     * @throws Exception 
     */
    public void analisaExpressaoSimples() throws Exception
    {
        if((token.simboloToCode() == 30) || (token.simboloToCode() == 31))  //smais ou smenos
        {
            if(token.simboloToCode() == 31) // não adiciona o mais porque ele não faz nada
                semantico.adicionaInvNaExpressao(token); //Dentro do adiciona inv, o Token muda e o código fica como zero
            proximoToken();
        }
        analisaTermo();
        while((token.simboloToCode() == 30) || (token.simboloToCode() == 31) || (token.simboloToCode() == 35))  //smais ou smenos ou sou
        {
            semantico.adicionaOperadorNaExpressao(token);
            proximoToken();
            analisaTermo();
        }
        
    }
    
    /**
     * Deve adicionar os operadores na tabela de simbolos.
     * @throws Exception 
     */
    public void analisaTermo() throws Exception
    {
        analisaFator();
        while((token.simboloToCode() == 32) || (token.simboloToCode() == 33) || (token.simboloToCode() == 34))  //smult ou sdiv ou se
        {
            semantico.adicionaOperadorNaExpressao(token);
            proximoToken();
            analisaFator();
        }
    }
    
    /**
     * Vai adicionando os identificadore ou números na tabela de síbolos.
     * Também adiciona abre e fecha parentesis.
     * @throws Exception 
     */
    public void analisaFator () throws Exception
    {
        if(token.simboloToCode() == 17)  //sidentificador
        {
            //Só vai retornar true se achar uma var ou func
            if(semantico.pesquisaDeclVarFunc(token))
            {
                //Aqui o sintático garante que não serão adicionados procedimentos na expressão
                if(semantico.getSimboloType(token).getClass() == Rotina.class)
                    analisaChamadaFuncao();
                else
                {
                    semantico.adicionaFatorNaExpressao(token);
                    proximoToken();
                }
            }
            else
            {
                throw new Exception("Sidentificador invalido");
            }
        }
        else
        {
            if(token.simboloToCode() == 18)  //snumero
            {
                semantico.adicionaFatorNaExpressao(token);
                proximoToken();
            }
            else
            {
                if(token.simboloToCode() == 36)  //snao
                {
                    semantico.adicionaOperadorNaExpressao(token);
                    proximoToken();
                    analisaFator();
                }
                else
                {
                    if(token.simboloToCode() == 22)  //sabreparenteses
                    {
                        semantico.adicionaOperadorNaExpressao(token);
                        proximoToken();
                        analisaExpressao();
                        if(token.simboloToCode() == 23)  //sfechaparenteses
                        {
                            semantico.adicionaOperadorNaExpressao(token);
                            proximoToken();
                        }
                        else
                        {
                            erro.erroSintatico(token.getLinha(), 10);
                        }
                    }
                    else
                    {
                        if(token.simboloToCode() == 38 || token.simboloToCode() == 39) //sverdadeiro e sfalso
                        {
                            semantico.adicionaFatorNaExpressao(token);
                            proximoToken();
                        }
                        else
                        {
                            erro.erroSintatico(token.getLinha(), 13);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Recebe o sidentificador para pesquisar na tabela de simbolos
     * @param t 
     */
    public void analisaChamadaProcedimento(Token t) throws Exception
    {
        if(semantico.pesquisaDeclProc(t))
        {
            GeradorDeCodigo.getInstance().geraComando(Comandos.CALL, Comandos.Label+""+semantico.getSimboloType(t).getInfo());
        }
        else
        {
            throw new Exception("Sidentificador invalido, procedimento nao encontrado");
        }
    }
    
    /**
     * Chamado pelo analisa expressão, adiciona uma função no validaExpressao dentro do semantico.
     * O geraExp que vai gerar o call e o LDV 0
     * @throws Exception 
     */
    public void analisaChamadaFuncao() throws Exception
    {
        semantico.adicionaFatorNaExpressao(token);
        proximoToken();
    }
        
}