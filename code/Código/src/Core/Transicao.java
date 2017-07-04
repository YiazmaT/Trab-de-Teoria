/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

/**
 *  Classe que represanta uma transição de um estado para outro de um automato
 * @author Matheus Prachedes Batista & Eymar Ferrario de Lima
 */
public class Transicao {
    /**
     * Caracter que deve ser lido para ocorrer a transição
     * Caso a transição seja vazia, a variavel será nula
     */
    private String[] caracter;
    private String[] saida;
    private int[] direção;
    /**
     * index do estado destino
     */
    private Estado estadoDestino;
    
    public void init(int tam){
        caracter = new String[tam];
        saida = new String[tam];
        direção = new int[tam];
    }
    
    public Transicao(Character leitura,Character escrita, Estado estadoDestino, int direção){
        this.caracter[0] = leitura==null?"":leitura.toString();
        this.estadoDestino = estadoDestino;
        this.direção[0] = direção;
        this.saida[0] = escrita==null?"":escrita.toString();
    }
    
    public Transicao(Character caracter, Estado estadoDestino){
        this.caracter[0] = caracter==null?"":caracter.toString();
        this.estadoDestino = estadoDestino;
    }
    
    public Transicao(Character caracter, Estado estadoDestino, String saida){
        this.caracter[0] = caracter==null?"":caracter.toString();;
        this.estadoDestino = estadoDestino;
        this.saida[0] = saida.equals("λ")?"":saida;
    }

    public Transicao(String transicao, Estado estadoDestino) {
        this.estadoDestino = estadoDestino;
        this.caracter[0] = transicao;
    }
    
    public Character getCaracter(){
        return getCaracter(0);
    }
    public Estado getEstadoDestino(){
        return estadoDestino;
    }
    public String getSaida(){
        return getSaida(0)+"";
    }
    
    public void setEstadoDestino(Estado destino){
        this.estadoDestino = destino;
    }
    /**
     * Verifica se é possivel seguir por essa transição dado a cadeia que está
     * sendo reconhecida e a posição que está sendo lida da cadeia
     * 
     * @param posLeitura posição que está sendo lida da cadeia
     * @param entrada cadeia que está sendo reconhecida
     * @return boolean indicando se é valido seguir por essa transição
     */
    public boolean valida(int posLeitura, String entrada) {
        if(getCaracter() == null){
            if(posLeitura <= entrada.length())return true;
        }else{
            if(posLeitura<entrada.length() && entrada.charAt(posLeitura) == getCaracter())return true;
        }
        return false;
    }

    /**
     * Metodo que indica quanto deve ser incrementado no index de leitura da cadeia
     * apos o automato seguir por essa transição
     * Basicamente, se a transição é vazia o incremento é 0
     * se não é 1
     * @return 
     */
    public int incremento() {
        if(caracter[0].length()==0)return 0;
        return 1;
    }
    
    public String getTransicao(){
        if(caracter[0].contains("|"))return "("+caracter+")";
        return caracter[0];
    }
    
    public int getDireção(){
        return direção[0];
    }
    
    public String concat(String trans) {
        return trans + "|" + (getTransicao().length()==0?"λ":caracter);
    }

    public void setTransicao(String transicao) {
        this.caracter[0] = transicao;
    }

    public char getCaracter(int i) {
        return caracter[i].length()==0?null:caracter[i].charAt(0);
    }

    public char getSaida(int i) {
        return saida[i].charAt(0);
    }

    public int getDireção(int i) {
        return direção[i];
    }
    
    
}
