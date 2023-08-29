package sanastix.homeprojects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MathController {

    private final MathView theView;
    private final MathTeacher theModel;

    public MathController(MathView theView, MathTeacher theModel){
        this.theView = theView;
        this.theModel = theModel;

        //слухає, чи отримали ми рівняння та передає це відповідному класу
        this.theView.addSubmitEquationListener(new EquationListener());
    }

    class EquationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //тут все виконується, коли ми отримали рівняння
            String equation;

            try{
                //отримуємо введене рівняння від вью
                equation = theView.getEquation();
                //передаємо моделі введене рівняння
                theModel.setEquationInput(equation);

                //рівняння перевіряється та видає повідомлення
                theView.displayResultMessage(theModel.equationChecker(equation));

                //рахується кількість чисел у введеному рівнянні та виводиться в текст.полі
                theView.setNumCounter(theModel.getNumCount());

            }
            catch (NumberFormatException ex){
                theView.displayErrorMessage();
            }
        }
    }

}
