/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import Automato.Transicao.*;

/**
 *
 * @author Matheus Prachedes Batista
 */
public class Condicao {
    private String[] condicao;
    private String[] saida;
    private String[] direção;

    public Condicao(String[] a, String[] b, String[] indices) {
        condicao = a;
        saida = b;
        direção = indices;
    }

    public String getDireção() {
        return direção[0];
    }
    
    public String[] getCondicões(){
        return condicao;
    }
    
    public String[] getSaidas(){
        return saida;
    }
    
    public String[] getDireções(){
        return direção;
    }
    
    public void setDireção(String direção) {
        this.direção[0] = direção;
    }

    public void init(int tam){
        condicao = new String[tam];
        saida = new String[tam];
        direção = new String[tam];
    }
    
    public Condicao(String condicao, String saida, String direção) {
        init(1);
        this.condicao[0] = condicao;
        this.saida[0] = saida;
        this.direção[0] = direção;
    }
    
    
    public Condicao(String condicao, String saida){
        init(1);
        this.condicao[0] = condicao;
        this.saida[0] = saida;
    }
    
    public Condicao(String condicao){
        init(1);
        this.condicao[0] = condicao;
        this.saida[0] = "";
    }
    
    public String getCondicao(){
        return condicao[0];
    }
    
    public String getSaida(){
        return saida[0];
    }
    
    public boolean equals(Object o){
        if(o instanceof Condicao){
            Condicao c = (Condicao)o;
            if(c.condicao.length != condicao.length)return false;
            for(int i=0;i<condicao.length;i++){
                if(!condicao[i].equals(c.condicao[i]))return false;
                if(!saida[i].equals(c.saida[i]))return false;
                if(!direção[i].equals(c.direção[i]))return false;
            }
            
            return true;
        }
        return false;
    }
    
    public String getDrawString(){
        String s = "";
        int i=0;
        for(;i<condicao.length-1;i++){
            s+= condicao[i]+":"+saida[i]+":"+direção[i]+" | ";
        }
        s+=condicao[i]+":"+saida[i]+":"+direção[i];
        return s;
    }
    
    
    
}
