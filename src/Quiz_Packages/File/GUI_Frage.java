package Quiz_Packages.File;

import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Klasse, die die Benutzeroberfläche für die Fragen im Quiz erstellt.
 * @author ICH
 */
public class GUI_Frage extends javax.swing.JFrame {

    public static final String START_GAME ="START_GAME";
    public static final String NEW_QUESTION = "NEW_QUESTION";
    public static final String SETTINGS = "SETTINGS";
    
    
    //Konstruktor der Klasse GUI_Frage (von NetBeans erstellt)
    public GUI_Frage() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
	
	
	//Von NetBeans selbst erstellter Code für das Aussehen der GUI.
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        start_game = new javax.swing.JButton();
        new_Question = new javax.swing.JButton();
        Question = new javax.swing.JTextField();
        AnswerA = new javax.swing.JTextField();
        AnswerB = new javax.swing.JTextField();
        CorrectAnswer = new javax.swing.JTextField();
        AnswerC = new javax.swing.JTextField();
        AnswerD = new javax.swing.JTextField();
        chances = new javax.swing.JTextField();
        settings = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        start_game.setText("Start");

        new_Question.setText("Frage hinzufügen");


        settings.setText("Einstellungen");
        jLabel1.setText("Antwort A");
        jLabel2.setText("Antwort B");
        jLabel3.setText("Antwort C");
        jLabel4.setText("Antwort D");
        jLabel5.setText("richtige Antwort");
        jLabel6.setText("Frage");
        jLabel7.setText("Chancen");
        start_game.setActionCommand(START_GAME);
        new_Question.setActionCommand(NEW_QUESTION);
 
        settings.setActionCommand(SETTINGS);
        Question.setText("");
        AnswerA.setText("");
        AnswerB.setText("");
        CorrectAnswer.setText("");
        AnswerC.setText("");
        AnswerD.setText("");
        chances.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(start_game, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AnswerC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AnswerB)
                            .addComponent(CorrectAnswer)
                            .addComponent(AnswerD)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(new_Question, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AnswerA)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel6))
                            .addComponent(Question, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(chances))))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(Question, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chances, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AnswerA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AnswerB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AnswerC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AnswerD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(CorrectAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(new_Question, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(start_game, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	
	
    private void AnswerAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnswerAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnswerAActionPerformed

    /**
     * @param args the command line arguments
     */
    
	
	//Namensdeklaration der Javax.Swing Objekte in der GUI.
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AnswerA;
    private javax.swing.JTextField AnswerB;
    private javax.swing.JTextField AnswerC;
    private javax.swing.JTextField AnswerD;
    private javax.swing.JTextField CorrectAnswer;
    private javax.swing.JTextField Question;
    private javax.swing.JTextField chances;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton new_Question;
    private javax.swing.JButton settings;
    private javax.swing.JButton start_game;
    // End of variables declaration//GEN-END:variables


	
	/**
	 * Setter-Methode, die "Zuhörer" (Listener) hinzufügt
	 * @param listener
	 * @return
	 */
    public void addListener(ActionListener listener){			//Parameterübergabe listener (als ActionListener -> reagiert auf Aktionen auf der GUI)
        new_Question.addActionListener(listener);				//füge ActionListener  für new_Question-Button hinzu mithilfe von listener
        start_game.addActionListener(listener);					//füge ActionListener  für start_game-Button hinzu
        settings.addActionListener(listener);					//füge ActionListener  für settings-Button hinzu
    }
	
	/** Getter-Methoden
	 * @return getText() von Question, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer, chnaces
	 */
    public String getQuestion(){	
        return Question.getText();								//gebe den im Textfeld Question enthaltenen Text zurück
    }
    public String getAntwortA(){
        return AnswerA.getText();								//gebe den im Textfeld AnswerA enthaltenen Text zurück
    }
    public String getAntwortB(){
        return AnswerB.getText();								//gebe den im Textfeld AnswerB enthaltenen Text zurück
    }
    public String getAntwortC(){
        return AnswerC.getText();								//gebe den im Textfeld AnswerC enthaltenen Text zurück
    }
    public String getAntwortD(){
        return AnswerD.getText();								//gebe den im Textfeld AnswerD enthaltenen Text zurück
    }
    public String getRichtigeAntwort(){
        return CorrectAnswer.getText();							//gebe den im Textfeld CorrectAnswer enthaltenen Text zurück
    }
    public String getChances(){
        return chances.getText();								//gebe den im Textfeld chances enthaltenen Text zurück
    }

    




}
