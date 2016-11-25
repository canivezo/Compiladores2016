/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

/**
 * 
 * @author 12649190
 */
public class AnalisadorSemantico {

    private TabelaDeSimbolos tabelaDeSimbolos;
    private VerificaExpressao exp;
    private int label = 0;
    private int posPilha = 1;
    private int nivel = 0;
    
    public AnalisadorSemantico() 
    {
        tabelaDeSimbolos = new TabelaDeSimbolos();
        exp = new VerificaExpressao();
    }
    
    /**
     * Retorna o nivel atual
     * @return 
     */
    public int getNivel()
    {
        return nivel;
    }
    
    /**
     * Retorna o valor do label disponível e incrementa o label dentro do semântico.
     * As chamadas de se e enquanto precisam do label.
     * @return 
     */
    public int getLabel()
    {
        int i = label;
        label++;
        return i;
    }
    
    /**
     * No caso de procedimentos e do programa, o simbolo é inserido com o tipo.
     * @param s
     * @throws Exception 
     */
    public void insereTabela(Simbolo s) throws Exception
    {
        //Deve setar o nivel neste ponto.
        s.setNivel(nivel);
        if(s.getType() != null)
        {
            switch(s.getType().getType())
            {
                //se definir um proc ou func ou programa, deve incrementar o nivel.
                case "proc":
                    //ao definir um proc o programa atribui um label a ele
                    s.getType().setInfo(label);
                    label++;
                case "programa":
                   nivel++;
                   break;
                default:
                    throw new Exception("tipo invalido");
            }
        }
        tabelaDeSimbolos.adicionaSimbolo(s);
    }
    
    public boolean pesquisaDuplicVar(Token t) throws Exception
    {
        Simbolo s = new Simbolo(t);
        s.setNivel(nivel);
        return tabelaDeSimbolos.verificaEscopo(s);
    }
    
    /**
     * Coloca tipo de variáveis na tabela de simbolos
     * Deve ser chamado no analisa tipo, e apenas para adicionar tipos em variáveis
     * @param t
     * @throws Exception 
     */
    public void colocaTipoTabela(Type t) throws Exception
    {
        int i = tabelaDeSimbolos.getSize() - 1;
        int posInit = posPilha;
        int alloc = 0;
        while(i > 0 && tabelaDeSimbolos.getSimbolo(i).getType() == null)
        {
            Type tipo = new Var(t.getType(), posPilha);
            tabelaDeSimbolos.getSimbolo(i).setType(tipo);
            posPilha++;
            alloc++;
            i--;
        }
        GeradorDeCodigo.getInstance().geraComando(Comandos.Allocate, posInit, alloc);
    }
    
    /**
     * Adiciona o tipo da função.
     * Deve ser chamado depois da declaraçao de uma função.
     * @param tipo
     * @throws Exception 
     */
    public void colocaTipoFuncTabela(String tipo) throws Exception
    {
       int i = tabelaDeSimbolos.getSize();
       if(tabelaDeSimbolos.getSimbolo(i) != null)
           throw new Exception("O ultimo simbolo ja tem um tipo, chamada feita na hora errada");
       
       //Como a funcao é inserida sem tipo, o nivel já foi setado.
       tabelaDeSimbolos.getSimbolo(i).setType(new Rotina(tipo, label));
       //Incrementa tanto o label quanto o nivel, porque pega um label e, por ser uma função, o nível tmbém aumenta.
       label++;
       nivel++;
    }
    
    /**
     * @param t
     * @return retorna verdadeiro se achar uma variável igual a t.
     * @throws Exception 
     */
    public boolean pesquisaDeclVarTabela(Token t) throws Exception
    {
        Simbolo s = new Simbolo(t);
        if(tabelaDeSimbolos.verificaTabela(s))
        {
            Type type = tabelaDeSimbolos.getSimbolo(s).getType();
            //Se nao for procedimento entao só pode ser uma das outras opções, que são válidas
            if(type.getClass() == Var.class)
                return true;
        }
        return false;
    }
    
    /**
    * Usado no analisa leia, para saber se existe a variável na tabela, seja ela var ou função
    */
    public boolean pesquisaDeclVarFunc(Token t) throws Exception
    {
        Simbolo s = new Simbolo(t);
        if(tabelaDeSimbolos.verificaTabela(s))
        {
            Type type = tabelaDeSimbolos.getSimbolo(s).getType();
            //Se nao for procedimento entao só pode ser uma das outras opções, que são válidas
            if(!type.getType().equals("proc"))
                return true;
        }
        return false;
    }
    
    /**
     * Se puderem ter uma variavel com o mesmo nome de proceimento, só precisamos modificar esse método
     * para percorrer toda a tabela de simbolos e retorna true se achar um procedimento com o mesmo nome
     * @param t
     * @return 
     * @throws Exception 
     */
    public boolean pesquisaDeclProc(Token t) throws Exception
    {
        Simbolo s = new Simbolo(t);
        return tabelaDeSimbolos.verificaTabela(s);
    }
    
    /**
     * 
     * @param t
     * @return
     * @throws Exception 
     */
    public Type getSimboloType(Token t) throws Exception
    {
        Simbolo s = new Simbolo(t);
        return tabelaDeSimbolos.getSimbolo(s).getType();
    }
    
    /**
     * Finaliza o escopo de uma função.
     * @throws Exception 
     */
    public void finalizaEscopo() throws Exception
    {
        Simbolo aux;
        int count = tabelaDeSimbolos.getSize() - 1;
        int dealloc = 0;
        while(count >= 0)
        {
            aux = tabelaDeSimbolos.getSimbolo(count);
            if(aux != null)
            {
                //Se retirar uma variável, decrementa a pilha.
                if(aux.getType().getClass() == Var.class)
                {
                    dealloc++;
                }
            }
            tabelaDeSimbolos.excluiSimbolo(count);
        }
        posPilha = posPilha - dealloc;
        GeradorDeCodigo.getInstance().geraComando(Comandos.DALLOC, posPilha, dealloc);
        nivel--;
    }
    
    /**
     * 
     * @param s
     * @param tipo
     * @throws Exception 
     */
    public void setSimboloType(Simbolo s, String tipo) throws Exception
    {
        
        Type t;
        switch(tipo)
        {
            case "funcInt":
            case "funcBool":
            case "proc":
                t = new Rotina(tipo);
                break;
            case "int":
            case "boolean":
                t = new Var(tipo);
                break;
            default:
                throw new Exception("Tipo invalido");
        }
        tabelaDeSimbolos.getSimbolo(s).setType(t);
    }
    
    /**
     * Começa uma nova expressão
     * @throws Exception 
     */
    public void comecaExpressao() throws Exception
    {
        exp.comecaExpressao();
    }
    
    /**
     * Adiciona um operador comun na expressão
     * @param t
     * @throws Exception 
     */
    public void adicionaOperadorNaExpressao(Token t) throws Exception
    {
        exp.adicionaOperadorNaExpressao(t);
    }
    
    /**
     * Adiciona o inversor de sinal na expressão
     * @param t
     * @throws Exception 
     */
    public void adicionaInvNaExpressao(Token t) throws Exception
    {
        Token tok = new Token(t.getLexema(), "sinv", t.getLinha());
        exp.adicionaOperadorNaExpressao(tok);
    }
    
    /**
     * O método consegue relacionar o token ao simbolo.
     * Como a tabela que tem informações de posição na pilha.
     * @param t 
     */
    public void adicionaFatorNaExpressao(Token t) throws Exception
    {
        if(t.simboloToCode() == 17)
        {    
            Simbolo s = tabelaDeSimbolos.getSimbolo(t);
            exp.adicionaFatorNaExpressao(s);
        }
        else // snumero, sverdadeiro e sfalso  
        {
            exp.adicionaFatorNaExpressao(t);
        }
    }
    
    /**
     * 
     * @throws Exception 
     */
    public void terminaExpressao() throws Exception
    {
        exp.terminaExpressao();
    }
    
    /**
     * Verifica se a ultima expressão foi booleana.
     * Usado no analisaSe e no analisaEnquanto.
     * @return true se sim e false se não
     */
    public boolean booleanExp()
    {
        return exp.wasBooleanExp();
    }
}
