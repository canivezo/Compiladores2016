/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.util.Vector;

/**
 *
 * @author Murilo
 */
public class TabelaDeSimbolos 
{
     /**
     * É possível solucionar o nível com a adição da posição do Símbolo da rotina no próprio Símbolo
     */
    Vector<Simbolo> simbolos;

    public TabelaDeSimbolos()
    {
        simbolos = new Vector<Simbolo>();
    }

    public int getSize()
    {
        return simbolos.size();
    }

    public void adicionaSimbolo(Simbolo simbolo) throws Exception
    {
        if(simbolo == null)
            throw new Exception("simbolo invalido");

        simbolos.add(simbolo);
    }

    public Simbolo getSimbolo(int i) throws Exception
    {
        if(i < 0 || i >= simbolos.size())
            throw new Exception("posicao na tabela invalida");

        return simbolos.get(i);
    }

    public Simbolo getSimbolo(Token t) throws Exception
    {
        Token aux;
        //
        //Faz ao contrário para retornar o símbolo mais próximo ao escopo.
        //
        for(int i = (simbolos.size() - 1); i >= 0; i--)
        {
            aux = simbolos.get(i).getToken();
            if(aux.getLexema().equals(t.getLexema()) && aux.getSimbolo().equals(t.getSimbolo()))
            {
                return simbolos.get(i);
            }
        }

        throw new Exception("Simbolo nao encontrado");
    }

    /**
     * @param s
     * @return retorna o simbolo equivalente na tabela, sem considerar o seu tipo
     * @throws Exception 
     */
    public Simbolo getSimbolo(Simbolo s) throws Exception
    {
        //
        //Faz ao contrário para retornar o símbolo mais próximo ao escopo.
        //
        for(int i = simbolos.size(); i >= 0; i--)
        {
            if(simbolos.get(i).equals(s))
            {
                return simbolos.get(i);
            }
        }

        throw new Exception("Simbolo nao encontrado");
    }

    public void excluiSimbolo(int i) throws Exception
    {
        if(i < 0 || i >= simbolos.size())
            throw new Exception("posicao na tabela invalida");

        simbolos.remove(i);
    }

    public void excluiSimbolo(Token t) throws Exception
    {
        Token aux;
        //
        //Faz ao contrário para retornar o símbolo mais próximo ao escopo.
        //
        for(int i = (simbolos.size() - 1); i >= 0; i--)
        {
            aux = simbolos.get(i).getToken();
            if(aux.getLexema().equals(t.getLexema()) && aux.getSimbolo().equals(t.getSimbolo()))
            {
                simbolos.remove(i);
            }
        }
        throw new Exception("Simbolo nao encontrado");
    }

    public void excluiSimbolo(Simbolo s) throws Exception
    {
        Simbolo aux;
        for(int i = (simbolos.size() - 1); i >= 0; i--)
        {
            aux = simbolos.get(i);
            if(aux.getToken().getLexema().equals(s.getToken().getLexema()) && aux.getToken().getSimbolo().equals(s.getToken().getSimbolo()))    
            {
                //
                //Independente do tipo, a tabela apenas remove um simbolo que seja igual...
                //
                simbolos.remove(i);
            }
        }

        throw new Exception("Simbolo nao encontrado");
    }

    /**
     * Não verifica tipo
     * @param s
     * @return 
     */
    public boolean verificaTabela(Simbolo s)
    {
        for(int i = 0; i < simbolos.size(); i++)
        {
            if(simbolos.get(i).equals(s)) // se o simbolo existir retorna true
                return true;
        }
        return false;
    }

    /**
     * Pesquisa somente até uma rotina.
     * @param s 
     * @param r 
     * @return 
     */
    public boolean verificaEscopo(Simbolo s)
    {
        Type t;
        int n;
        int i = (simbolos.size() - 1);
        do
        {
            t = simbolos.get(i).getType();
            n = simbolos.get(i).getNivel();
            if(simbolos.get(i).equals(s))
                return true;
            
            i--;
        }
        while(n <= s.getNivel() && i >= 0);
        return false;
    }
}