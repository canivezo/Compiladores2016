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

    private Exception Exception(String inválido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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
    
    private class ExpNum implements vetorExp<Token>{

        private Token token;

        public ExpNum(Token t)
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
    
    //Pós fixa
    private Vector<vetorExp> expressao;
    //Pilha de operadores
    private Vector<Token> pilha;
    
    private boolean wasBool = false;
    
    public VerificaExpressao()
    {
        expressao = null;
    }
    
    /**
     * Verifica se um operador deve ser adicionado na pilha.
     * Um operador não deve ser adicionado caso t tenha menor precedencia do que está na pilha.
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
     * 7 para a mais alta e 0 para a mais baixa.
     * Abre e fecha parentesis são tratados no adicionaOperadorNaExpressao
     * @param i
     * @return retorna o código de precedencia.
     * @throws Exception 
     */
    private int codigoDePrecedencia(int i) throws Exception
    {   
        if(i == 36 || i == 0) return 7; //Nao e INV. O zero seria um menos como um operador de sinal, INV.
        
        if(i == 32 || i == 33) return 6; //* e /.
        
        if(i == 30 || i == 31) return 5; //+ e -.
        
        if(i == 24 || i == 25 || i == 27 || i == 28) return 4;// > , >= , < , <=.
        
        if(i == 26 || i == 29) return 3;// = , !=.
        
        if(i == 34) return 2; // AND.
        
        if(i == 35) return 1; // OR.
        
        if(i == 22 || i == 23) return 0; // abre e fecha parentesis.
        
        throw new Exception("codigo inválido");
    }
    
    /**
     * Percorre a this.expressao e gera todo o código.
     * Deve verificar também a validade da expressão.
     */
    private void geraExp() throws Exception
    {
        //seta essa variável se o resultado for booleano
        int pos = 0;
        while(pos < expressao.size())
        {
            if(expressao.get(pos).getClass() == ExpOp.class)
            {
                ExpOp op = (ExpOp) expressao.get(pos);
                switch(op.getExp().simboloToCode())
                {
                    case 0: // inv
                        GeradorDeCodigo.getInstance().geraComando(Comandos.INV);
                        break;
                    case 24:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.CMA);
                        break;
                    case 25:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.CMAQ);    
                        break;
                    case 26:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.CEQ);
                        break;
                    case 27:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.CME);
                        break;
                    case 28:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.CMEQ);
                        break;
                    case 29:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.CDIF);
                        break;
                    case 30:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.ADD);
                        break;
                    case 31:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.SUB);
                        break;
                    case 32:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.MULT);
                        break;
                    case 33:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.DIVI);
                        break;
                    case 34:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.AND); 
                        break;
                    case 35:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.OR);
                        break;
                    case 36:
                        GeradorDeCodigo.getInstance().geraComando(Comandos.NEG);
                    default:
                        throw Exception("Inválido");
                }
            }
            else //Não é operador. snumero ou sidentificador
            {
                if(expressao.get(pos).getClass() == ExpNum.class)
                {
                    ExpNum num = (ExpNum) expressao.get(pos);
                    if(num.getExp().simboloToCode() == 18)
                    {
                       GeradorDeCodigo.getInstance().geraComando(Comandos.LDC, num.getExp().getLexema());
                    }
                    else
                    {
                        if(num.getExp().simboloToCode() == 38) // verdadeiro
                        {
                            GeradorDeCodigo.getInstance().geraComando(Comandos.LDC, 1);
                        }
                        else //falso
                        {
                            GeradorDeCodigo.getInstance().geraComando(Comandos.LDC, 0);
                        }
                    }
                }
                else
                {
                    ExpSimb sim = (ExpSimb) expressao.get(pos);
                    if(sim.getExp().getType().getClass() == Rotina.class)
                    {
                        GeradorDeCodigo.getInstance().geraComando(Comandos.CALL, Comandos.Label+""+sim.getExp().getType().getInfo());
                        GeradorDeCodigo.getInstance().geraComando(Comandos.LDV, 0);
                    }
                    else
                    {
                        GeradorDeCodigo.getInstance().geraComando(Comandos.LDV, sim.getExp().getType().getInfo());
                    }
                }
            }
            pos++;
        }
        
        /**
         * Chama a função para verificar o tipo da expressão e se ela é válida.
         * Caso não seja lança excessão
         */
        wasBool = expType();
    }
    
    /**
     * 
     * @return 
     */
    private boolean expType() throws Exception
    {
        Vector<Integer> tipo = new Vector<Integer>();
        /**
         * tipo 0 = int
         * tipo 1 = booleano
         */
        int pos = 0;
        while(pos < expressao.size())
        {
            //Guardar os tipos conforme aparecem as variáveis
            //A cada expressão que aparecer, retirar os tipos anteriores e deixar apenas o tipo resultante.
            //Assim será possível validar tudo.
            if(expressao.get(pos).getClass() == ExpOp.class)
            {
                ExpOp op = (ExpOp) expressao.get(pos);
                int tipoPos = tipo.size() - 1;
                switch(op.getExp().simboloToCode())
                {
                case 0:     //Inv só aceita o tipo inteiro
                    if(tipo.get(tipoPos) != 0)
                    {
                        throw new Exception("Expressão inválida");
                    }
                    break;
                case 24:    //Maior não pode fazer a comparação se for entre tipos diferentes, Int > Bool
                    if((tipo.get(tipoPos) == 0 && tipo.get(tipoPos - 1) == 1) || (tipo.get(tipoPos) == 1 && tipo.get(tipoPos - 1) == 0)) 
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 1);
                    }
                    break;
                case 25:    //MaiorIgual
                    if((tipo.get(tipoPos) == 0 && tipo.get(tipoPos - 1) == 1) || (tipo.get(tipoPos) == 1 && tipo.get(tipoPos - 1) == 0)) 
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 1);
                    }
                    break;
                case 26:    //Igual
                    if((tipo.get(tipoPos) == 0 && tipo.get(tipoPos - 1) == 1) || (tipo.get(tipoPos) == 1 && tipo.get(tipoPos - 1) == 0)) 
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 1);
                    }
                    break;
                case 27:    //Menor
                    if((tipo.get(tipoPos) == 0 && tipo.get(tipoPos - 1) == 1) || (tipo.get(tipoPos) == 1 && tipo.get(tipoPos - 1) == 0)) 
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 1);
                    }
                    break;
                case 28:    //MenorIgual
                    if((tipo.get(tipoPos) == 0 && tipo.get(tipoPos - 1) == 1) || (tipo.get(tipoPos) == 1 && tipo.get(tipoPos - 1) == 0)) 
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 1);
                    }
                    break;
                case 29:    //Diferente
                    if((tipo.get(tipoPos) == 0 && tipo.get(tipoPos - 1) == 1) || (tipo.get(tipoPos) == 1 && tipo.get(tipoPos - 1) == 0)) 
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 1);
                    }
                    break;
                case 30:    //Mais só aceita o Tipo Int
                    if(tipo.get(tipoPos) == 1 || tipo.get(tipoPos - 1) == 1)
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 0);
                    }
                    break;
                case 31:    //Menos
                    if(tipo.get(tipoPos) == 1 || tipo.get(tipoPos - 1) == 1)
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 0);
                    }
                    break;
                case 32:    //Multiplicação
                    if(tipo.get(tipoPos) == 1 || tipo.get(tipoPos - 1) == 1)
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 0);
                    }
                    break;
                case 33:    //Divisão
                    if(tipo.get(tipoPos) == 1 || tipo.get(tipoPos - 1) == 1)
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 0);
                    }
                    break;
                case 34:    //And
                    if(tipo.get(tipoPos) == 0 || tipo.get(tipoPos - 1) == 0)
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 1);
                    }
                    break;
                case 35:    //Or
                    if(tipo.get(tipoPos) == 0 || tipo.get(tipoPos - 1) == 0)
                    {
                        throw new Exception("Expressão inválida");
                    }
                    else
                    {
                        //Tira o ultimo, e seta o penultimo para booleano, que é o tipo gerado por essa expressão.
                        tipo.remove(tipoPos);
                        tipoPos--;
                        tipo.set(tipoPos, 1);
                    }
                    break;
                case 36:    //Not
                    if(tipo.get(tipoPos) != 0)
                    {
                        throw new Exception("Expressão inválida");
                    }
                }
            }
            else //Não é operador. snumero ou sidentificador
            {
                if(expressao.get(pos).getClass() == ExpNum.class)
                {
                    ExpNum num = (ExpNum) expressao.get(pos);
                    if(num.getExp().simboloToCode() == 18)
                    {
                        //Numeros são inteiros
                        tipo.add(0);
                    }
                    else
                    {
                        //Verdadeiro ou falso são booleanos
                        tipo.add(1);
                    }
                }
                else
                {
                    ExpSimb sim = (ExpSimb) expressao.get(pos);
                    if(sim.getExp().getType().getType().equals("inteiro") || sim.getExp().getType().getType().equals("funcInt")) //inteiro
                    {
                        tipo.add(0);
                    }
                    else //booleano
                    {
                        tipo.add(1);
                    }
                }
            }
            pos++;
        }
        if(tipo.get(0) == 0) 
            return false;
        
        return true;
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
     * Adiciona um Símbolo na expressão
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
     * Adiciona um Numero na expressão
     * @param s
     * @throws Exception 
     */
    public void adicionaFatorNaExpressao(Token t) throws Exception
    {
        if(expressao == null) 
            throw new Exception("adicionando na expressao sem começar uma");
        
        expressao.add(new ExpNum(t));
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
                //Pode existir um abre e fecha parentesis sem nenhuma expressão
                while(pilha.get(posPilha).simboloToCode() != 22) // enquanto não acha o abre parênteses
                {
                    aux = pilha.remove(posPilha);
                    expressao.add(new ExpOp(aux));
                    posPilha--;
                    if(posPilha < 0)
                        throw new Exception("Fecha parentesis nao encontrado. Expresao invalida");
                }
                //removendo o abre parentesis
                pilha.remove(posPilha);
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
            i--;
        }
        
        //chama o gerador de Expressão
        geraExp();
        expressao = null;
    }
    
    /**
     * @return true se for booleana e false se não.
     */
    public boolean wasBooleanExp()
    {
        return wasBool;
    }
}