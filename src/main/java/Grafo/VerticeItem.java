/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 * 
 */
public class VerticeItem extends Vertice{
    
    private List<String> itens = itens = new ArrayList<String>();
    
    public VerticeItem(){
        super();
    }
    
    public VerticeItem(int indice){
        super(indice);
    }
    
    public List getItens(){
        return this.itens;
    }
    
    public void setItens(List itens){
        this.itens = itens;
    }
    
    public void addItem(String elemento){
        this.itens.add(elemento);
    }
}
