/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Panels;

import Automato.TransicaoTuring;
import Core.AutomatoFinito;
import Core.Turing;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import lfa.Main;

/**
 *
 * @author Matheus Prachedes Batista
 */
public class PanelTuring extends Automato{
    private int n;
    public PanelTuring(Main pai, int n) {
        super(pai);
        needFinal = true;
        this.n =n;
    }

    @Override
    protected void criarTransicao(Point point) {
        new InputTuringNFitas(pai,true,this,n);
    }
   
    @Override
    protected void testeRapido(){
        Turing turing = new Turing(n);
        if(view.verificaEstados(needFinal)){
            view.montarTuring(turing);
            String[] cadeia = getFitas();
            if(cadeia == null)return;
            if(turing.verificar(cadeia)){
                mostrarSaida("A cadeia foi aceita com fitas: ", turing.getFita());
            }else{
                mostrarSaida("Cadeia não foi aceita com fitas: ", turing.getFita());
            }
        }else{
            JOptionPane.showMessageDialog(this.getParent(),"O autômato parece não estar completo.\n"
                    + "É necessário pelo menos 1 estado final e exatamente 1 estado inicial.");
        }
    }

    @Override
    protected void testeEstadoPorEstado() {
        Turing turing = new Turing(n);
        if(view.verificaEstados(needFinal)){
            view.montarTuring(turing);
            String[] cadeia = getFitas();
            if(cadeia == null)return;
            if(turing.verificar(cadeia)){
                ResultadoByStepTuring resultado = new ResultadoByStepTuring(pai, true, view, turing.getCaminho(), turing.getPosLeitor(), turing.getEstadosFita(), n);
                resultado.setVisible(true);
                resultado.toFront();
            }
        }else{
            JOptionPane.showMessageDialog(this.getParent(),"O autômato parece não estar completo.\n"
                    + "É necessário pelo menos 1 estado final e exatamente 1 estado inicial.");
        }
    }

    @Override
    protected void testeMultiplos() {
        Turing turing = new Turing(n);
        if(view.verificaEstados(needFinal)){
            view.montarTuring(turing);
            MT_BigPanel m = new MT_BigPanel(pai, true, n, turing);
            m.toFront();
            m.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this.getParent(),"O autômato parece não estar completo.\n"
                    + "É necessário pelo menos 1 estado final e exatamente 1 estado inicial.");
        }
    }

    private String[] getFitas() {
        JTextField[] entradas = new JTextField[n];
        Object[] campos = new Object[n*2];
        for(int i=0;i<n;i++){
            entradas[i] = new JTextField();
            campos[i*2] = "Fita " + i + ": ";
            campos[i*2 + 1] = entradas[i];
        }
        if(JOptionPane.showConfirmDialog(pai, campos,"Entradas",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
            String[] fitas = new String[n];
            for(int i=0;i<n;i++){
                fitas[i] = entradas[i].getText();
            }
            return fitas;
        }
        return null;
    }

    private void mostrarSaida(String msg, String[] fita) {
        Object[] campos = new Object[n*2+1];
        campos[0] = msg;
        for(int i=0;i<n;i++){
            campos[(i*2)+1] = "Fita " + i + ':';
            campos[(i*2)+2] = fita[i];
        }
        JOptionPane.showMessageDialog(pai, campos);
    }
    
    
}
