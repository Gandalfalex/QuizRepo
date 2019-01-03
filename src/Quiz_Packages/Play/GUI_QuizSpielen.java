package Quiz_Packages.Play;

import java.awt.event.ActionListener;




public class GUI_QuizSpielen extends javax.swing.JFrame {

    public static final String BUTTONA = "BUTTONA";
    public static final String BUTTONB = "BUTTONB";
    public static final String BUTTONC = "BUTTONC";
    public static final String BUTTOND = "BUTTOND";
    public static final String BUTTONWAIT = "BUTTONWAIT";
    public static final String FINISHED_QUIZ = "FINISHED_QUIZ";

    
    
    public GUI_QuizSpielen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelFrage = new javax.swing.JLabel();
        buttonB = new javax.swing.JButton();
        buttonD = new javax.swing.JButton();
        buttonA = new javax.swing.JButton();
        buttonC = new javax.swing.JButton();
        buttonWait = new javax.swing.JButton();
        finished = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        labelFrage.setText("jLabel1");
        buttonA.setText("A");
        buttonA.setActionCommand(GUI_QuizSpielen.BUTTONA);

        buttonB.setText("B");
        buttonB.setActionCommand(GUI_QuizSpielen.BUTTONB);

        buttonC.setText("C");
        buttonC.setActionCommand(GUI_QuizSpielen.BUTTONC);

        buttonD.setText("D");
        buttonD.setActionCommand(GUI_QuizSpielen.BUTTOND);

        buttonWait.setActionCommand(GUI_QuizSpielen.BUTTONWAIT);
        buttonWait.setVisible(false);
        buttonWait.setText("Sie können diese Frage überspringen");

        finished.setText("Statistik anzeigen");
        finished.setActionCommand(FINISHED_QUIZ);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelFrage)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonA, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonC, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonD, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonB, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buttonWait, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finished, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(labelFrage)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonB, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonA, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonC, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonD, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(buttonWait, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(finished, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonA;
    private javax.swing.JButton buttonB;
    private javax.swing.JButton buttonC;
    private javax.swing.JButton buttonD;
    private javax.swing.JButton buttonWait;
    private javax.swing.JButton finished;
    private javax.swing.JLabel labelFrage;
    // End of variables declaration//GEN-END:variables





 public void SetButtonsAText(String s){buttonA.setText(s);}                 
    public void SetButtonsBText(String s){buttonB.setText(s);}
    public void SetButtonsCText(String s){buttonC.setText(s);}
    public void SetButtonsDText(String s){buttonD.setText(s);}
    public void SetLableText(String s){labelFrage.setText(s);}
    public void SetButtonWaitText(String s){buttonWait.setText(s);}
    public void enableButtonWait(){buttonWait.setVisible(true);}
    public void disableButtonWait(){buttonWait.setVisible(false);}
    public void disableButtons(){
        buttonA.hide();
        buttonB.hide();
        buttonC.hide();
        buttonD.hide();
    }
    public void setFinishedVisible(){finished.setVisible(true);}
    public String getButtonsAText(){return buttonA.getText();}
    public String getButtonsBText(){return buttonB.getText();}
    public String getButtonsCText(){return buttonC.getText();}
    public String getButtonsDText(){return buttonD.getText();}
    public String getLabel(){return labelFrage.getText();}
    public void addListener(ActionListener actions){
        buttonA.addActionListener(actions);
        buttonB.addActionListener(actions);
        buttonC.addActionListener(actions);
        buttonD.addActionListener(actions);
        buttonWait.addActionListener(actions);
        finished.addActionListener(actions);
    }




}
