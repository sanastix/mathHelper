package sanastix.homeprojects;

import java.awt.event.ActionListener;
import javax.swing.*;

public class MathView extends JFrame{

    private JLabel equationLabel = new JLabel("Enter your equation: ");
    private JTextField equation = new JTextField(25);
    private JButton submitEquationInputButton = new JButton("Submit");

    private JLabel numberAmount = new JLabel("Amount of numbers: ");
    private JTextField numberCounter = new JTextField(10);
 
    // private JLabel rootCheck = new JLabel("Enter the root: ");
    // private JTextField rootInput = new JTextField(5);
    //private JButton submitRootInputButton = new JButton("Submit");
    


    MathView(){

        JPanel calcPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);

        calcPanel.add(equationLabel);
        calcPanel.add(equation);
        calcPanel.add(submitEquationInputButton);

        calcPanel.add(numberAmount);
        calcPanel.add(numberCounter);

        // calcPanel.add(rootCheck);
        // calcPanel.add(rootInput);
        // calcPanel.add(submitRootInputButton);

        this.add(calcPanel);

    }

    public String getEquation(){
        return equation.getText();
    }

    void addSubmitEquationListener(ActionListener listenForSubmitEquationButton){
        submitEquationInputButton.addActionListener(listenForSubmitEquationButton);
    }

    void displayErrorMessage(){
        JOptionPane.showMessageDialog(this, "Incorrect input");
    }

    public int getNumCounter(){
        return Integer.parseInt(numberCounter.getText());
    }

    public void setNumCounter(int numCount){
        numberCounter.setText(Integer.toString(numCount));
    }

    void displayResultMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

}
