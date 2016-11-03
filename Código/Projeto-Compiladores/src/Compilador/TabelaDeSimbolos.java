/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.util.Vector;
import Compilador.*;

/**
 *
 * @author Murilo
 */
public class TabelaDeSimbolos 
{
        Token token = new Token();
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
               //if verificarDuplicacaoToken
		TabelaDeSimbolos.add(new Simbolo(1, 2, token)); // Adiciona o elemento especificado no final da lista.
              //else
                    //erro, semantico variavel duplicada
	}
        
        //Perguntas?
        // Tu poderia me mostrar qual é metodo que vc colocou no diagrama corresponde ao da apostila?
        /*
        Excluir escopo, é vc excluir as variáveis que há em um escopo de uma determinada função ou procedimento quando vc encontrar o fim???
        Exemplo:
        Programa teste
        Var a, b: inteiro
        Procedimento sub
        Início
        Var a, b : inteiro
        ... 
        Fim
        Procedimento somar
        Início
        ... 
        Fim
        Adicionando na tabela
        teste, 0
        a, 1
        b, 1
        Sub, 0
        a, 1
        b, 1
        Somar, 0
        
        acho melhor a gente seguir os métodos q tem na apostila pra gente não se perder, os nomes e tal... 
        
        outra coias é a parte de excluirEscopo, 
        */
        //
        
        public void excluiEscopo()
        {
                  
        }
        public void verificarEscopo()
        {
            
        }
  
}
