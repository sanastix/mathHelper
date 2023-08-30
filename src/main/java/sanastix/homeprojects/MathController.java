package sanastix.homeprojects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MathController {

    private final MathView theView;
    private final MathTeacher theModel;

    public MathController(MathView theView, MathTeacher theModel){
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addSubmitEquationListener(new EquationListener());
    }

    class EquationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String equation = theView.getEquation();

            if (equation.isBlank()){
                theView.displayResultMessage("Empty input");
            } else {
                try {

                    theModel.setEquationInput(equation);

                    theView.displayResultMessage(theModel.equationChecker(equation));

                    theView.setNumCounter(theModel.getNumCount());

                } catch (NumberFormatException ex) {
                    theView.displayErrorMessage();
                }
            }

        }

    }

}
