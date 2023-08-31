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
        this.theView.addSubmitRootListener(new RootListener());
    }

    class EquationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String equation = theView.getEquation();

            if (equation.isBlank()){
                theView.displayResultMessage("Empty equation input");
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

    class RootListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String equation = theView.getEquation();
            String root = theView.getRoot();

            if (root.isBlank()){
                theView.displayResultMessage("Empty root input");
            } else if (equation.isBlank()) {
                theView.displayResultMessage("Empty equation input");
            } else {
                try {

                    theModel.setRootInput(root);

                    theView.displayResultMessage(theModel.rootChecker(equation, root));

                } catch (NumberFormatException ex){
                    theView.displayErrorMessage();
                }

            }

        }

    }

}
