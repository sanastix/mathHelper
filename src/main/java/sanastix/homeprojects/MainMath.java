package sanastix.homeprojects;

public class MainMath
{
    public static void main(String[] args) {

        MathView theView = new MathView();

        MathTeacher theModel = new MathTeacher();

        MathController theController = new MathController(theView, theModel);

        theView.setVisible(true);

    }

}
