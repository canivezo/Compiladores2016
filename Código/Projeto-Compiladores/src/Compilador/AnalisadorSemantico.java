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
    private int label = 0;
    private int posPilha = 0;
    private int nivel = -1;
    
    public AnalisadorSemantico() 
    {
        tabelaDeSimbolos = new TabelaDeSimbolos();
    }
    
    /*
    * No caso de procedimentos, o simbolo é inserido com o tipo.
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
    
    public void colocaTipoTabela(Type t) throws Exception
    {
        int i = tabelaDeSimbolos.getSize();
        while(i >= 0 && tabelaDeSimbolos.getSimbolo(i).getType() == null)
        {
            i--;
            Type tipo = new Var(t.getType(), posPilha);
            tabelaDeSimbolos.getSimbolo(i).setType(tipo);
            posPilha++;
        }
    }
    
    /**
    * Deve ser chamado depois da declaraçao de uma função
    */
    public void colocaTipoFuncTabela(String tipo) throws Exception
    {
       int i = tabelaDeSimbolos.getSize();
       if(tabelaDeSimbolos.getSimbolo(i) != null)
           throw new Exception("O ultimo simbolo ja tem um tipo, chamada feita na hora errada");
       
       tabelaDeSimbolos.getSimbolo(i).setType(new Rotina(tipo, label));
       label++;
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
    
    public Type getSimboloType(Token t) throws Exception
    {
        Simbolo s = new Simbolo(t);
        return tabelaDeSimbolos.getSimbolo(s).getType();
    }
    
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
        //gerar o dealloc
        //o nivel é decrementado já que acabou uma funcao
        nivel--;
    }
    
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
    
    public void comecaExpressao()
    {
        
    }
    
    public void adicionaOperadorNaExpressao(Token t)
    {
        
    }
    
    /**
     * O método consegue relacionar o token ao simbolo.
     * Como a tabela que tem informações de posição na pilha.
     * @param t 
     */
    public void adicionaFatorNaExpressao(Token t)
    {
        
    }
    
    public void terminaExpressao()
    {
        
    }
}
