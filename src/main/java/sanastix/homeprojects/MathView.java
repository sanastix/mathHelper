package sanastix.homeprojects;

import java.awt.event.ActionListener;
import javax.swing.*;

public class MathView extends JFrame{

    private JLabel equationLabel = new JLabel("Enter your equation: ");
    private JTextField equation = new JTextField(25);
    private JButton submitEquationInputButton = new JButton("Submit");

    private JLabel numberAmount = new JLabel("Amount of numbers: ");
    private JTextField numberCounter = new JTextField(10);


    MathView(){

        JPanel calcPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);

        calcPanel.add(equationLabel);
        calcPanel.add(equation);
        calcPanel.add(submitEquationInputButton);

        calcPanel.add(numberAmount);
        calcPanel.add(numberCounter);

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

    public void setNumCounter(int numCount){
        numberCounter.setText(Integer.toString(numCount));
    }

    void displayResultMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

}
