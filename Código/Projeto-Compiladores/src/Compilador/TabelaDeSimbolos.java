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
        Token token = null;
        protected Vector<Simbolo> TabelaDeSimbolos = new Vector<Simbolo>();

        public void setTabelaDeSimbolos(Vector<Simbolo> TabelaDeSimbolos) 
        {
		this.TabelaDeSimbolos = TabelaDeSimbolos;
	}
        
	public Vector<Simbolo> getTabelaDeSimbolos() 
        {
		return TabelaDeSimbolos;
	}
 
        public void adicionaSimbolo(int expType, int typ, Token token) throws Exception
        {
		TabelaDeSimbolos.add(new Simbolo(expType, typ, token)); // Adiciona o elemento especificado no final da lista.
	}
        
        public void excluiSimbolo(Simbolo s)
        {
                TabelaDeSimbolos.remove(s);
        }
        public void insereTipo(String typ) 
        {
                TabelaDeSimbolos.get(TabelaDeSimbolos.size() - 1).getType()=typ;
        }
        
        public void verificaEscopo()
        {
            
        }
        
        public int verificaTabela(String lexema) 
        {
            for (int i = TabelaDeSimbolos.size() - 1; i >= 0; i--) 
            {
                if (TabelaDeSimbolos.get(i).getToken().equals(lexema)) 
                {
                    return i;
                }
            }
        }
}