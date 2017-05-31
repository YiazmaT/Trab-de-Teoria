/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Maquinas;

/**
 *
 * @author Matheus Prachedes Batista
 */
public class Norma {
    private Variavel[] variaveis;
    private Variavel[] pilha;
    private Variavel topoPilha = new Variavel(0,0);
    
    public Norma(int numVariavel,int tamPilha){
        variaveis = new Variavel[numVariavel];
        pilha = new Variavel[tamPilha];
        for(int i=0;i<numVariavel;i++){
            variaveis[i] = new Variavel(0,0);
        }
        for(int j=0;j<tamPilha;j++){
            pilha[j] = new Variavel(0,0);
        }
    }
    /**
     * Verifica se o valor da variavel é 0
     * @param valor valor a ser verificado
     * @return true se o valor for zero, false caso contrario
     */
    public boolean isZero(int valor){
        return valor == 0;
    }

    public Variavel[] getPilha() {
        return pilha;
    }
    
    public int getIndicePilha(){
        return this.topoPilha.getMagnitude();
    }
    /**
     * Adiciona o valor 1 na variavel indicada pelo endereço no parametro
     * @param variavel endereço da variavel a ser somada
     */
    public void add(int variavel){
        Variavel v = variaveis[variavel];
        if(isZero(v.sinal)){//Se Positivo apenas adicionar na magnitude
            v.magnitude++;
        }else{//Senão subtrai da magnitude
            v.magnitude--;
            if(isZero(v.magnitude)){//Se a magnitude for zero, mudar o sinal para positivo
                v.sinal--;
            }
        }
    }
    
    /**
     * Subtrai o valor 1 da variavel indicada pelo endereço no parametro
     * @param variavel endereço da variael a ser subtraida
     */
    public void sub(int variavel){
        Variavel v = variaveis[variavel];
        if(isZero(v.sinal)){
            if(isZero(v.magnitude)){
                v.sinal++;
                v.magnitude++;
            }else{
                v.magnitude--;
            }
        }else{
            v.magnitude++;
        }
    }
    
    /**
     * Seta em 0 o valor da variavel indicada
     * @param variavel variavel a ser zerada
     */
    public void setarZero(int variavel){
        Variavel v = variaveis[variavel];
        if(isZero(v.sinal)){//Positivo
            while(!isZero(v.magnitude)){
                sub(variavel);
            }
        }else{//Negativo
            while(!isZero(v.magnitude)){
                add(variavel);
            }
        }
        if(!(v.sinal==0))v.sinal--;
    }
    
    /**
     * Realiza a operação A := A+B sem preservar o valor de B
     * @param a endereço do operando A
     * @param b endereço do operando B
     */
    public void add(int a, int b){
        Variavel A = variaveis[a];
        Variavel B = variaveis[b];
        while(!isZero(B.magnitude)){
            if(isZero(B.sinal)){//B Positivo
                add(a);
                sub(b);
            }
            else{//B negativo
                sub(a);
                add(b);
            }
        }
//        if(isZero(B.sinal)){//Positivo
//            while(!isZero(B.magnitude)){
//                sub(b);
//                add(a);
//            }
//        }else{//Negativo
//            while(!isZero(B.magnitude)){
//                add(b);
//                add(a);
//            }
//        }
    }
    
    public void sub(int a, int b,int c){
        Variavel B = variaveis[b];
        if(B.sinal==0)B.sinal++;
        else B.sinal--;
        add(a,b,c);
        if(B.sinal==0)B.sinal++;
        else B.sinal--;
        
    }
    
    public void setar(int a, int n){
        setarZero(a);
        
        for(int i=0;i<Math.abs(n);i++){
            add(a);
        }
        if(n<0)variaveis[a].sinal++;
    }
    
    /**
     * Realiza a operação A:= A+B, preservando o valor de B utilizando a variavel
     * C
     * @param a endereço da Variavel A
     * @param b endereço da Variavel B
     * @param c endereço da Variavel C
     */
    public void add(int a, int b, int c){
        Variavel A = variaveis[a];
        Variavel B = variaveis[b];
        Variavel C = variaveis[c];
        setarZero(c);
        while(!isZero(B.magnitude)){
            if(isZero(B.sinal)){//B Positivo
                add(a);
                add(c);
                sub(b);
            }
            else{//B negativo
                sub(a);
                sub(c);
                add(b);
            }
        }
        add(b,c);
    }
    
    /**
     * Reliza a operação A:=B preservando o valor de C
     * A operação é identica a realizar:
     * A:=0
     * A:=A+B
     * 
     * @param a Endereço da variavel A
     * @param b Endereço da variavel B
     * @param c Endereço da variavel C
     */
    public void setar(int a, int b,int c){
        setarZero(a);
        add(a,b,c);
    }
    /**
     * funciona para numeros negativos?
     * @param a
     * @param b
     * @param c
     * @param d 
     */
    public void mult(int a, int b, int c, int d){
        Variavel C = variaveis[c];
        setarZero(c);
        add(c,a);//Mover o conteudo de A para C;
        while(!isZero(C.magnitude)){//Até c for zero, soma B em A e subtrai 1 em C
            add(a,b,d);
            sub(c);
        }
    }
    
    public void mod(int a, int b,int c,int d,int e){
        Variavel D = variaveis[d];
        Variavel B = variaveis[b];
        setar(c, a, e);
        setar(d,b,e);
        menorQue(d,c);
        while(!(D.sinal==0) || (D.magnitude == 0)){
            B.sinal++;
            add(a,b,e);
            B.sinal--;
            setar(c,a,e);
            setar(d,b,e);
            menorQue(d,c);
        }
    }
    
    public boolean menorQue(int a, int b){
        Variavel A = variaveis[a];
        Variavel B = variaveis[b];
        
        if(B.sinal == 0)B.sinal++;
        else B.sinal--;
        add(a, b);     
        
        if(A.sinal == 0)return false;
        return true;
    }
    
    public boolean menorQue(int a, int b, int c,int d,int e){
        setarZero(c);
        add(c,a,d);
        setarZero(d);
        add(d,b,e);
        return menorQue(c,d);
    }
    
    public boolean menorIgual(int a,int b){
        Variavel A = variaveis[a];
        Variavel B = variaveis[b];
        if(menorQue(a,b))return true;
        if(A.magnitude == 0)return true;
        return false;
    }
    
    public void fatorial(int a, int b, int c, int d){
        Variavel B = variaveis[b];
        setarZero(b);
        add(b,a,c);
        sub(b);
        while(!isZero(B.magnitude)){
            mult(a,b,c,d);
            sub(b);
        }
    }
    
    public void pow(int a, int b, int c, int d,int e){
        Variavel B = variaveis[b];
        setarZero(e);
        add(e,a,c);
        sub(b);
        while(!isZero(B.magnitude)){
            mult(a,e,c,d);
            sub(b);
        }
    }
    
    public boolean primo(int a, int b, int c, int d, int e,int f){
        Variavel B = variaveis[b];
        //Se a == 1;
        setar(b,1);
        sub(b,a,c);
        if(B.magnitude==0){
            return false;
        }
        //Se a == 2
        setar(b,2);
        sub(b,a,c);
        if(B.magnitude==0){
            B.magnitude++;
            return true;
        }
        //Se a%2==0;
        setar(b,2);
        setarZero(c);
        add(c,a,d);
        mod(c,b,d,e,f);
        if(variaveis[c].magnitude==0){
            setar(b,0);
            return false;
        }
        setar(b,3);
        menorQue(b,a,c,d,e);
        while(!(variaveis[c].sinal==0)){
            setarZero(c);
            add(c,a,e);
            mod(c,b,d,e,f);
            if(variaveis[c].magnitude==0){
                setar(b,0);
                return false;
            }
            add(b);
            add(b);
            menorQue(b,a,c,d,e);
        }
        setar(b,1);
        return true;
    }
    
    public void inserirPilha(int a){
        Variavel C = pilha[topoPilha.magnitude];
        Variavel A = variaveis[a];
        while(!(A.magnitude==0)){
            A.magnitude--;
            C.magnitude++;
        }
        topoPilha.magnitude++;
    }
    
    public void removerPilha(int a){
        Variavel C = pilha[topoPilha.magnitude];
        Variavel A = variaveis[a];
        setarZero(a);
        while(!(C.magnitude==0)){
            C.magnitude--;
            A.magnitude++;
        }
        if(!(topoPilha.magnitude==0))topoPilha.magnitude--;
    }
    
    public Variavel[] getVariaveis() {
        return variaveis;
    }
    
    public class Variavel{
        public static final int POSITIVO = 0;
        public static final int NEGATIVO = 1;
        
        private int sinal;
        private int magnitude;
        
        public Variavel(int sinal, int magnitude){
            this.sinal = sinal;
            this.magnitude = magnitude;
        }

        public int getSinal() {
            return sinal;
        }

        public void setSinal(int sinal) {
            this.sinal = sinal;
        }

        public int getMagnitude() {
            return magnitude;
        }

        public void setMagnitude(int magnitude) {
            this.magnitude = magnitude;
        }

        @Override
        public String toString() {
            return "(" + sinal + "," + magnitude + ")";
        }
        
        
    }
}
