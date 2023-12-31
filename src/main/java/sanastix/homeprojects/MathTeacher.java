package sanastix.homeprojects;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathTeacher {

    private String root;

    public String getRoot() {
        return root;
    }

    public void setRootInput(String root) {
        this.root = root;
    }

    private String equationInput;

    public void setEquationInput(String str){
        this.equationInput = str;
    }

    public String getEquationInput(){
        return equationInput;
    }

    private final String FILE_DB = "equationDB.txt";
    private final File equationDB = new File(FILE_DB);

    private void saveEquationIntoFile (String equation){

        try(FileWriter fileWriter = new FileWriter(equationDB, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(equation);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String equationChecker(String eq){

        numCounter(eq);

        if(!isInputCorrect(eq)){
            return "Incorrect equation input";
        }

        if(!areBracketsCorrect(eq)){
            return "Incorrect brackets input";
        }

        saveEquationIntoFile(eq);
        return "The equation was saved";

    }

    public String rootChecker(String eq, String r){

        if (evaluateEquationByGivenRoot(eq, r)){
            return "The entered number is the root of the equation";
        }

        return "The entered number is not the root of the equation";

    }

    private int numsInEquation = 0;

    private void setNumsInEquation(int nums){
        this.numsInEquation = nums;
    }

    public int getNumCount(){
        return numsInEquation;
    }

    private void numCounter(String equation){

        int count = 0;
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(equation);

        while(matcher.find()){
            count++;
        }

        setNumsInEquation(count);

    }

    private boolean isInputCorrect (String equation) {

        List<String> opList = new ArrayList<>();

        //патерн невалідних операторів поспіль
        Pattern wrongOp = Pattern.compile("[-+=*/]{2,}");
        Matcher wrongOpMatcher = wrongOp.matcher(equation);

        //патерн валідних двох операторів поспіль
        Pattern rightOp = Pattern.compile("[-+=*/]-");
        Matcher rightOpMatcher;

        while (wrongOpMatcher.find()){
            opList.add(wrongOpMatcher.group());
        }

        for (String s : opList){
            rightOpMatcher = rightOp.matcher(s);
            if ((s.length() > 2) || !rightOpMatcher.matches()){
                return false;
            }
        }

        return true;

    }

    private boolean areBracketsCorrect (String equation){

        String[] strArr = equation.split("=");
        boolean leftStringResult = bracketsSeeker(strArr[0]);
        boolean rightStringResult = bracketsSeeker(strArr[1]);

        return (leftStringResult && rightStringResult);

    }

    private boolean bracketsSeeker (String string){

        Pattern pattern = Pattern.compile("\\(+|\\)+");
        Matcher matcher = pattern.matcher(string);

        boolean result;

        if (matcher.find()) {
            result = checkBracketsBalance(string);
        } else {
            result = true;
        }

        return result;
    }

    private boolean checkBracketsBalance(String str){

        List<Character> openBrackets = new ArrayList<>();
        List<Character> closeBrackets = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(')
                openBrackets.add(c);
            if (c == ')') {
                if (openBrackets.isEmpty()) {
                    return false;
                } else {
                    closeBrackets.add(c);
                }
            }
        }

        return (openBrackets.size() == closeBrackets.size());

    }

    private boolean evaluateEquationByGivenRoot(String inputExpression, String inputRoot){

        String [] strArr = inputExpression.split("=");

        float difference = (float) Math.pow(10, -9);

        Expression exp1 = new ExpressionBuilder(strArr[0])
                .variables("x")
                .build()
                .setVariable("x", Double.parseDouble(inputRoot));

        float leftResult = (float) exp1.evaluate();

        Expression exp2 = new ExpressionBuilder(strArr[1])
                .variables("x")
                .build()
                .setVariable("x", Double.parseDouble(inputRoot));

        float rightResult = (float) exp2.evaluate();

        float resultDifference = Math.abs(leftResult - rightResult);

        //returns true, if given root evaluates equation and [abs]diff between
        //left and right side of equation is less than Math.pow(10, -9)
        return resultDifference < difference;

    }

}
