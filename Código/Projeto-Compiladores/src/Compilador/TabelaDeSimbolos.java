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
        Vector<Simbolo> simbolos;
    
        public TabelaDeSimbolos()
        {
            simbolos = new Vector<Simbolo>();
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
            for(int i = simbolos.size(); i >= 0; i--)
            {
                aux = simbolos.get(i).getToken();
                if(aux.getLexema().equals(t.getLexema()) && aux.getSimbolo().equals(t.getSimbolo()))
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
        
        public boolean verificaTabela()
        {
            return false;
        }
        
        public boolean verificarEscopo()
        {
            return false;
        }
}