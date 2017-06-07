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
    private String condicao;
    private String saida;
    private String direção;

    public String getDireção() {
        return direção;
    }

    public void setDireção(String direção) {
        this.direção = direção;
    }

    public Condicao(String condicao, String saida, String direção) {
        this.condicao = condicao;
        this.saida = saida;
        this.direção = direção;
    }
    
    
    public Condicao(String condicao, String saida){
        this.condicao = condicao;
        this.saida = saida;
    }
    
    public Condicao(String condicao){
        this.condicao = condicao;
        this.saida = "";
    }
    
    public String getCondicao(){
        return condicao;
    }
    
    public String getSaida(){
        return saida;
    }
}
