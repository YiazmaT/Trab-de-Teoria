/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panels;

/**
 *
 * @author YiazmaT
 */
public class Celula extends javax.swing.JPanel {
    private String registrador;
    private int sinal;
    private int num;
    /**
     * Creates new form Celula
     */
    public Celula(String registrador, int sinal, int num) {
        initComponents();
        this.registrador = registrador;
        this.sinal = sinal;
        this.num = num;
        this.atualizar();
        this.setMaximumSize(getPreferredSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registradorTextField = new javax.swing.JLabel();
        valoresTextField = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        registradorTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        registradorTextField.setText("A:");

        valoresTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valoresTextField.setText("(0 , 000)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registradorTextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valoresTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registradorTextField)
                    .addComponent(valoresTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void setValores(int sinal, int num){
        this.sinal = sinal;
        this.num = num;
        this.atualizar();
    }
    
    private void atualizar(){
        registradorTextField.setText(registrador + ":");
        valoresTextField.setText("("+sinal+" , "+num+")");
    }

    public String getRegistrador() {
        return registrador;
    }

    public void setRegistrador(String registrador) {
        this.registrador = registrador;
    }

    public int getSinal() {
        return sinal;
    }

    public void setSinal(int sinal) {
        this.sinal = sinal;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel registradorTextField;
    private javax.swing.JLabel valoresTextField;
    // End of variables declaration//GEN-END:variables
}
