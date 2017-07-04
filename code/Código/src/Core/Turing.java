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
public class Turing{
    private ArrayList<Estado> estados;
    private Estado estadoInicial;
    private ArrayList<Integer> caminho;
    private ArrayList<Integer[]> posLeitor;
    private ArrayList<String[]> estadosFita;
    private String[] saidaFita;
    private int numFitas = 1;
    public Turing(){
        init();
    }

    
    public boolean verificar(String[] cadeia) {
        resetar();
        Integer[] posLeitura = new Integer[numFitas];
        for(int i=0;i<numFitas;i++){
            posLeitura[i] = 0;
        }
        return proximoEstado(estadoInicial,cadeia,posLeitura);
    }

    
    public void resetar() {
        saidaFita = new String[numFitas];
        caminho.clear();
        posLeitor.clear();
        estadosFita.clear();
    }
    
    private boolean proximoEstado(Estado estadoAtual, String[] cadeia, Integer[] posLeitura) {
        saidaFita = cadeia;
        posLeitor.add(posLeitura);
        estadosFita.add(cadeia);
        caminho.add(estados.indexOf(estadoAtual));
        if(estadoAtual.isFinal()){//Se estado é final, cadeia reconhecida
            return true;
        }
        ArrayList<Transicao> trans = estadoAtual.getTransicoes();
        for(int i=0;i<trans.size();i++){//Para cada transição deste estado
            Transicao t = trans.get(i);
            if(valida(t,cadeia,posLeitura)){//Se o caracter na fita bater com o caracter da transição
                String[] novaCadeia = replace(t,cadeia,posLeitura);//A nova fita será o caracter lido substituido pelo caracter de escrita da transição
                Integer[] novaPosLeitura = atualizaPosLeitura(t,posLeitura);//A posição de leitura da fita é alterada de acordo com a transição (-1,+1)
                if(proximoEstado(t.getEstadoDestino(), novaCadeia, novaPosLeitura)){//Chamada recursiva com o novo estado, a nova fita e a nova posição de leitura
                    return true;
                }
            }
        }
        caminho.remove(caminho.size()-1);
        posLeitor.remove(posLeitor.size()-1);
        estadosFita.remove(estadosFita.size()-1);
        return false;
    }
    //Verifica se o caracter da transição bate com o caracter da fita
    private boolean valida(Transicao t, String[] cadeia, Integer[] posLeitura) {
        for(int i=0;i<cadeia.length;i++){
            if(!valida(t.getCaracter(i),cadeia[i],posLeitura[i])){
                return false;
            }
        }
        return true;
    }

    private boolean valida(char caracterLido, String cadeia, int posLeitura){
        char charFita;
        if(posLeitura>=cadeia.length() || posLeitura < 0)charFita = ' ';//Se a posição de leitura estiver fora da fita, o caracter de leitura da fita é um espaço em branco
        else charFita = cadeia.charAt(posLeitura);//Caso contrario é o proprio caracter da fita
        if(charFita==caracterLido)return true;
        return false;
    }
    
    //Altera o caracter que foi lido pelo caracter da transição
    private String[] replace(Transicao t, String[] cadeia, Integer[] posLeitura) {
        String[] novaCadeia = new String[numFitas];
        for(int i=0;i<cadeia.length;i++){
            novaCadeia[i] = replace(t.getSaida(i),cadeia[i],posLeitura[i]);
        }
        return novaCadeia;
    }

    private String replace(char caracterEscrito, String cadeia, int posLeitura){
        String novaCadeia;
        if(posLeitura<0)novaCadeia = caracterEscrito + cadeia;//Se a leitura ocorreu antes da fita, o caracter escrito fica antes
        else if(posLeitura>=cadeia.length())novaCadeia = cadeia + caracterEscrito;//Se a leitura ocorreu depois da fita, o caracter escrito fica depois
        else{//Caso contrario é só substituir o caracter na posição tal
            char[] chars = cadeia.toCharArray();
            chars[posLeitura] = caracterEscrito;
            novaCadeia = new String(chars);
        }
        return novaCadeia;
    }
    
    public void clear() {
        init();
    }

    private void init() {
        saidaFita = new String[numFitas];
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
            String[] leitura,escrita;
            String[] dir = condicao.getDireções();
            int[] direção = new int[numFitas];
            leitura = condicao.getCondicões();
            escrita = condicao.getSaidas();
            for(int i=0;i<dir.length;i++){
                if(dir[i].equals("R"))direção[i]=1;
                else if(dir[i].equals("L"))direção[i]=-1;
                else if(dir[i].equals("S"))direção[i]=0;
                if(leitura[i].equals("λ"))leitura[i] = " ";
                if(escrita[i].equals("λ"))escrita[i] = " ";
            }
            e.addTransicao(estados.get(destino), leitura, escrita, direção);
        }
    }

    public void setInicial(int i) {
        estadoInicial = estados.get(i);
    }

    public String[] getFita() {
        return saidaFita;
    }

    public ArrayList<Integer> getCaminho() {
        return caminho;
    }

    public void setCaminho(ArrayList<Integer> caminho) {
        this.caminho = caminho;
    }

    public ArrayList<Integer[]> getPosLeitor() {
        return posLeitor;
    }

    public void setPosLeitor(ArrayList<Integer[]> posLeitor) {
        this.posLeitor = posLeitor;
    }

    public ArrayList<String[]> getEstadosFita() {
        return estadosFita;
    }

    public void setEstadosFita(ArrayList<String[]> estadosFita) {
        this.estadosFita = estadosFita;
    }

   
    public String[] getSaida() {
        return saidaFita; 
    }

    private Integer[] atualizaPosLeitura(Transicao t, Integer[] posLeitura) {
        Integer[] novaPosLeitura = new Integer[numFitas];
        for(int i=0;i<numFitas;i++){
            novaPosLeitura[i] = posLeitura[i] + t.getDireção(i);
            if(posLeitura[i] < 0)novaPosLeitura[i]++;
        }
        return novaPosLeitura;
    }
    
    
    
}
