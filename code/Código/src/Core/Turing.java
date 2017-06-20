/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import java.util.ArrayList;

/**
 *
 * @author Matheus Prachedes Batista
 */
public class Turing implements ReconhecedorCadeia{
    private ArrayList<Estado> estados;
    private Estado estadoInicial;
    private ArrayList<Integer> caminho;
    private ArrayList<Integer> posLeitor;
    private ArrayList<String> estadosFita;
    private String saidaFita;
    
    public Turing(){
        init();
    }

    @Override
    public boolean verificar(String cadeia) {
        resetar();
        //caminho.add(estados.indexOf(estadoInicial));
        //posLeitor.add(0);
        //estadosFita.add(cadeia);
        return proximoEstado(estadoInicial,cadeia,0);
    }

    @Override
    public void resetar() {
        saidaFita = "";
        caminho.clear();
        posLeitor.clear();
        estadosFita.clear();
    }

    
    private boolean proximoEstado(Estado estadoAtual, String cadeia, int posLeitura) {
        saidaFita = cadeia;
        posLeitor.add(posLeitura);
        estadosFita.add(cadeia);
        caminho.add(estados.indexOf(estadoAtual));
        if(estadoAtual.isFinal()){
            return true;
        }
        ArrayList<Transicao> trans = estadoAtual.getTransicoes();
        for(int i=0;i<trans.size();i++){
            Transicao t = trans.get(i);
            if(valida(t,cadeia,posLeitura)){
                String novaCadeia = replace(t,cadeia,posLeitura);
                int novaPosLeitura = posLeitura+t.getDireção();
                if(posLeitura<0)novaPosLeitura++;
                if(proximoEstado(t.getEstadoDestino(), novaCadeia, novaPosLeitura)){//Obs: Rezar para funcionar
                    return true;
                }
            }
        }
        caminho.remove(caminho.size()-1);
        posLeitor.remove(posLeitor.size()-1);
        estadosFita.remove(estadosFita.size()-1);
        return false;
    }

    private boolean valida(Transicao t, String cadeia, int posLeitura) {
        char charFita;
        if(posLeitura>=cadeia.length() || posLeitura < 0)charFita = ' ';
        else charFita = cadeia.charAt(posLeitura);
        if(charFita==t.getCaracter())return true;
        return false;
    }

    private String replace(Transicao t, String cadeia, int posLeitura) {
        String novaCadeia;
        if(posLeitura<0)novaCadeia = t.getSaida().charAt(0) + cadeia;
        else if(posLeitura>=cadeia.length())novaCadeia = cadeia + t.getSaida().charAt(0);
        else{
            char[] chars = cadeia.toCharArray();
            chars[posLeitura] = t.getSaida().charAt(0);
            novaCadeia = new String(chars);
        }
        return novaCadeia;
    }

    public void clear() {
        init();
    }

    private void init() {
        saidaFita = "";
        caminho = new ArrayList<>();
        posLeitor = new ArrayList<>();
        estadosFita = new ArrayList<>();
        estados = new ArrayList<>();}

    public void addEstado(boolean isFinal) {
        estados.add(new Estado(isFinal));
    }

    public void addTransicao(int origem, int destino, ArrayList<Condicao> condicoes) {
        Estado e = estados.get(origem);
        for(Condicao condicao : condicoes){
            Character leitura,escrita;
            int direção;
            if(condicao.getDireção() == "R")direção = 1;
            else direção = -1;
            if(condicao.getCondicao().startsWith("λ"))leitura = ' ';
            else leitura = condicao.getCondicao().charAt(0);
            if(condicao.getSaida().startsWith("λ"))escrita = ' ';
            else escrita = condicao.getSaida().charAt(0);
            e.addTransicao(estados.get(destino), leitura, escrita,direção);
        }
    }

    public void setInicial(int i) {
        estadoInicial = estados.get(i);
    }

    public String getFita() {
        return saidaFita;
    }

    public ArrayList<Integer> getCaminho() {
        return caminho;
    }

    public void setCaminho(ArrayList<Integer> caminho) {
        this.caminho = caminho;
    }

    public ArrayList<Integer> getPosLeitor() {
        return posLeitor;
    }

    public void setPosLeitor(ArrayList<Integer> posLeitor) {
        this.posLeitor = posLeitor;
    }

    public ArrayList<String> getEstadosFita() {
        return estadosFita;
    }

    public void setEstadosFita(ArrayList<String> estadosFita) {
        this.estadosFita = estadosFita;
    }

    @Override
    public String getSaida() {
        return saidaFita; 
    }
    
    
    
}
