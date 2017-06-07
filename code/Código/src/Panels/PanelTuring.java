/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Panels;

import Automato.TransicaoTuring;
import java.awt.Point;
import lfa.Main;

/**
 *
 * @author Matheus Prachedes Batista
 */
public class PanelTuring extends Automato{

    public PanelTuring(Main pai) {
        super(pai);
        needFinal = true;
    }

    @Override
    protected void criarTransicao(Point point) {
        new TransicaoTuring(pai,true,point,this);
    }
   
}
