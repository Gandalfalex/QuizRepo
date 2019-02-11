package Quiz_Packages.Settings;

import java.awt.event.ActionListener;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ICH
 */
public class GUI_Settings extends javax.swing.JFrame {      //Benutzeroberfläche wird ertellt.

    public static final String NEWQUIZ = "NEWQUIZ";        //Variablen (Z. 16-19) werden deklariert und initialisiert.
    public static final String CHANCES = "CHANCES";         // Variablen mit final sind unveränderbar (konstant)
    public static final String AOQ = "AOQ";
    public static final String FINISHED = "FINISHED";

    public GUI_Settings() {         //Konstruktor erzeugt die GUI (Benutzeroberfläche)
        initComponents();           //Methode
        this.setLocationRelativeTo(null);       //zentriert die GUI. Fenster wird in der Mitte angezeigt.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        otherQuiz = new javax.swing.JButton();
        quizName = new javax.swing.JLabel();
        amountOfChances = new javax.swing.JTextField();
        amountOfQuestions = new javax.swing.JTextField();
        finished = new javax.swing.JButton();
        maxQuestions = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(80, 80), new java.awt.Dimension(80, 80), new java.awt.Dimension(32767, 80));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        otherQuiz.setText("neue Datei");
        otherQuiz.setActionCommand(NEWQUIZ);
        amountOfChances.setText("");
        amountOfQuestions.setVisible(true);

        finished.setText("Ok");
        finished.setActionCommand(FINISHED);

        jLabel1.setText("Wie Viele Chancen sollen zur Verfügung stehen");

        maxQuestions.setText("Mit wie vielen Fragen wollen Sie spielen?");
        finished.setText("Ok");



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(quizName)
                                        .addComponent(maxQuestions)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(amountOfChances, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(finished, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(otherQuiz, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                                .addComponent(amountOfQuestions, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addContainerGap(234, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(otherQuiz)
                                .addGap(11, 11, 11)
                                .addComponent(quizName)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(amountOfChances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(maxQuestions)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amountOfQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(finished)
                                .addContainerGap(144, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountOfChances;
    private javax.swing.JTextField amountOfQuestions;
    private javax.swing.JButton finished;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel maxQuestions;
    private javax.swing.JButton otherQuiz;
    private javax.swing.JLabel quizName;
    private javax.swing.Box.Filler filler1;

    // End of variables declaration//GEN-END:variables

    public void setQuizName(String s) {
        quizName.setText(s);
    }

    public void setMaxQuestion(int i) {
        maxQuestions.setText("Sie können maximal " + i + " Fragen nutzen");
    }

    public String getAmountOfChances() {
        return amountOfChances.getText();
    }

    public String getAmountOfQuestions() {
        return amountOfQuestions.getText();
    }

    public void setVisibleQ(boolean show) { // Max Anzahl an Fragen kann nur nach Spieldatei- Auswahl festgelegt werden
        if (show) { // wenn übergabe wahr ist, dann blende Button und Textfeld ein
            amountOfQuestions.setVisible(true);
        } else {
            amountOfQuestions.setVisible(false);
        }
    }

    public void addListener(ActionListener listener) {
        otherQuiz.addActionListener(listener);
        finished.addActionListener(listener);
    }
}
