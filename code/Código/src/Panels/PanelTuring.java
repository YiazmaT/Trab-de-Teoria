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
        new TransicaoTuring(pai,true,point,this);
    }
   
    @Override
    protected void testeRapido(){
        Turing turing = new Turing();
        if(view.verificaEstados(needFinal)){
            view.montarTuring(turing);
            String cadeia = JOptionPane.showInputDialog(getParent(),"Insira o conteudo inicial da fita: ");
            if(cadeia == null)return;
            if(turing.verificar(new String[]{cadeia})){
                JOptionPane.showMessageDialog(this.getParent(), "Cadeia aceita com fita: \n" + turing.getFita());
            }else{
                JOptionPane.showMessageDialog(this.getParent(), "Cadeia não foi aceita com fita: \n" + turing.getFita());
            }
        }else{
            JOptionPane.showMessageDialog(this.getParent(),"O autômato parece não estar completo.\n"
                    + "É necessário pelo menos 1 estado final e exatamente 1 estado inicial.");
        }
    }

    @Override
    protected void testeEstadoPorEstado() {
        Turing turing = new Turing();
        if(view.verificaEstados(needFinal)){
            view.montarTuring(turing);
            String cadeia = JOptionPane.showInputDialog(getParent(),"Insira o conteudo inicial da fita: ");
            if(cadeia == null)return;
            if(turing.verificar(new String[]{cadeia})){
                //ResultadoByStepTuring resultado = new ResultadoByStepTuring(pai,true
                        //,view,turing.getCaminho(),turing.getPosLeitor(),turing.getEstadosFita());
                
                //resultado.setVisible(true);
                //resultado.toFront();
            }
        }else{
            JOptionPane.showMessageDialog(this.getParent(),"O autômato parece não estar completo.\n"
                    + "É necessário pelo menos 1 estado final e exatamente 1 estado inicial.");
        }
    }

    @Override
    protected void testeMultiplos() {
        Turing turing = new Turing();
        if(view.verificaEstados(needFinal)){
            view.montarTuring(turing);
            //MultiplasEntradas me = new MultiplasEntradas(pai, needFinal, turing, false, true);
            //me.toFront();
            //me.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this.getParent(),"O autômato parece não estar completo.\n"
                    + "É necessário pelo menos 1 estado final e exatamente 1 estado inicial.");
        }
    }
    
    
}
