/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.util.Vector;

/**
 *
 * @author lucas
 */
public class VerificaExpressao {
    
    private interface vetorExp<Generic> {
        public Generic getExp();
        public void setExp(Generic t);
    }
    
    /**
     * Classes internas que ajudam a gerar a expressao
     */
    private class ExpSimb implements vetorExp<Simbolo>{
            
        private Simbolo simbolo;
        
        public ExpSimb(Simbolo s)
        {
            setExp(s);
        }
        
        @Override
        public Simbolo getExp()
        {
            return simbolo;
        }
        
        @Override
        public void setExp(Simbolo t)
        {
            simbolo = t;
        }
    }

    private class ExpOp implements vetorExp<Token>{

        private Token token;

        public ExpOp(Token t)
        {
            setExp(t);
        }
        
        public Token getExp()
        {
            return token;
        }

        public void setExp(Token t)
        {
            token = t;
        }
    }
    
    private Vector<vetorExp> expressao;
    private Vector<Token> pilha;
    
    public VerificaExpressao()
    {
        expressao = null;
    }
    
    /**
     * Verifica se um operador deve ser adicionado na pilha.
     * Um operador não deve ser adicionado caso t tenha maior precedencia do que está na pilha.
     * Outras coisas podem fazer com que essa funcao retorne false, por exemplo pilha == null.
     * @param t
     * @return true se for para adicionar, false se não.
     */
    private boolean isToAddOperador(Token t) throws Exception
    {
        if(pilha == null) return false;
        if(pilha.size() == 0) // primeiro operador na pilha
        {
            return true;
        }
        else
        {
            int pilhaPos = (pilha.size() - 1);
            return precedencia(t.simboloToCode(), pilha.get(pilhaPos).simboloToCode());
        }
    }
    
    /**
     * Verifica a precedencia entre o fator e pilha.
     * @param fator
     * @param pilha
     * @return true se fator tiver precedencia sobre a pilha
     * @throws Exception 
     */
    private boolean precedencia(int fator, int pilha) throws Exception
    {
        if(codigoDePrecedencia(fator) > codigoDePrecedencia(pilha)) 
            return true;
        
        return false;
    }
    
    /**
     * Codifica cada tipo de operador conforme sua precedencia. 
     * 8 para a mais alta e 1 para a mais baixa.
     * Abre e fecha parentesis são tratados no adicionaOperadorNaExpressao
     * @param i
     * @return retorna o código de precedencia.
     * @throws Exception 
     */
    private int codigoDePrecedencia(int i) throws Exception
    {   
        if(i == 22 || i == 23) return 8; // abre e fecha parentesis.
        
        if(i == 36 || i == 0) return 7; //Nao e NEG. O zero seria um menos como um operador de sinal, INV.
        
        if(i == 32 || i == 33) return 5; //+ e -.
        
        if(i == 24 || i == 25 || i == 27 || i == 28) return 4;// > , >= , < , <=.
        
        if(i == 26 || i == 29) return 3;// = , !=.
        
        if(i == 34) return 2; // AND.
        
        if(i == 35) return 1; // OR.
        
        throw new Exception("codigo inválido");
    }
    
    /**
     * Percorre a this.expressao e gera todo o código.
     * Deve verificar também a validade da expressão.
     */
    private void geraExp()
    {
        
    }
    
    /**
     * Inicia
     * @throws Exception 
     */
    public void comecaExpressao() throws Exception
    {
        if(expressao != null) 
            throw new Exception("começando uma expressao sem terminar uma");
        
        expressao = new Vector<vetorExp>();
        pilha = new Vector<Token>();
    }
    
    /**
     * 
     * @param s
     * @throws Exception 
     */
    public void adicionaFatorNaExpressao(Simbolo s) throws Exception
    {
        if(expressao == null) 
            throw new Exception("adicionando na expressao sem começar uma");
        
        expressao.add(new ExpSimb(s));
    }
    /**
     * Adiciona os operadores na pilha e em alguns casos na expressão.
     * @param t
     * @throws Exception 
     */
    public void adicionaOperadorNaExpressao(Token t) throws Exception
    {
        if(expressao == null) 
            throw new Exception("adicionando na expressao sem começar uma");
        
        if(t.simboloToCode() == 22) // Abre parênteses, empiha direto.
        {
            pilha.add(t);
        }
        else
        {
            if(t.simboloToCode() == 23) // Fecha parênteses, desempilha até achar um abre parênteses.
            {
                int posPilha = pilha.size() - 1;
                Token aux = pilha.get(posPilha);
                if(aux.simboloToCode() == 22)
                      throw new Exception("Nada entre um abre e fecha parentesis. Expresao invalida");
                
                while(pilha.get(posPilha).simboloToCode() != 22) // enquanto não acha o abre parênteses
                {
                    aux = pilha.get(posPilha);  
                    posPilha--;
                    if(posPilha < 0)
                        throw new Exception("Fecha parentesis nao encontrado. Expresao invalida");
                }
            }
            else
            {
                int posPilha = pilha.size() - 1;
                //Operador com maior ou igual prescedencia
                while(!isToAddOperador(t))
                {
                    //adiciona na pós fixa os operadores
                    expressao.add(new ExpOp(pilha.remove(posPilha)));
                }
                pilha.add(t);
            }
        }
    }
    
    /**
     * Termina a expressão e chama a função que gera o código.
     * @throws Exception 
     */
    public void terminaExpressao() throws Exception
    {
        if(expressao == null) 
            throw new Exception("terminando uma expressao sem começar uma");
        
        //Tirar todo mundo da pilha
        int i = pilha.size() - 1;
        while(i >= 0)
        {
            expressao.add(new ExpOp(pilha.remove(i)));
        }
        
        //chama o gerador de Expressão
        geraExp();
        expressao = null;
    }
}