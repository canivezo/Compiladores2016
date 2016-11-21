/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;

/**
 *
 * @author lucas
 */
public class Label {
    
    private int linha;
    private String label;
    
    /**
     *
     * @param lin
     * @param lab
     * @throws Exception
     */
    public Label(int lin, String lab) throws Exception
    {
        setLinha(lin);
        setLabel(lab);
    }
    
    private void setLinha(int lin) throws Exception
    {
        if(lin < 0)
            throw new Exception("linha inválida");
        else
        linha = lin;
    }
    
    private void setLabel(String lab) throws Exception
    {
        if(lab.equals(""))
            throw new Exception("linha inválida");
        
        label = lab;
    }
    
    public int getLinha()
    {
        return linha;
    }
    
    public String getLabel()
    {
        return label;
    }
}
