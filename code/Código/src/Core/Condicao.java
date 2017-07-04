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

    public String getDireção() {
        return direção[0];
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
}
