package sanastix.homeprojects;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathTeacher {

    private String equationInput;

    public void setEquationInput(String str){
        this.equationInput = str;
    }

    public String getEquationInput(){
        return equationInput;
    }

    private String FILE_DB = "equationDB.txt";
    private File equationDB = new File(FILE_DB);

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

    private int numsInEquation = 0;

    private void setNumsInEquation(int nums){
        this.numsInEquation = nums;
    }

    public int getNumCount(){
        return numsInEquation;
    }

    private char root;

    private void numCounter(String equation){
        //рахуємо кількість чисел у введеному рівнянні
        int count = 0;
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(equation);
        //Queue<String> numbers = new LinkedList<>();
        while(matcher.find()){
            //numbers.add(matcher.group());
            count++;
        }
        setNumsInEquation(count);
    }

    private boolean isInputCorrect (String equation) {

        List<String> opList = new ArrayList<>();
        //патерн невалідних двох операторів зі списку поспіль
        Pattern wrongOp = Pattern.compile("[-+=*/]{2,}");
        Matcher wrongOpMatcher = wrongOp.matcher(equation);
        //патерн валідних двох операторів поспіль
        Pattern rightOp = Pattern.compile("[-+=*/]-");
        Matcher rightOpMatcher;

        //записуємо співпадіння невалідних операторів у список
        while (wrongOpMatcher.find()){
            opList.add(wrongOpMatcher.group());
        }

        for (String s : opList){
            rightOpMatcher = rightOp.matcher(s);
            if ((s.length() > 2) || !rightOpMatcher.matches()){
                return false;
            }
        }

            /*Queue<Character> operators = new LinkedList<>();
            for (int i = 0; i < equation.length(); i++) {
                char ch = equation.charAt(i);
                if ((ch == '=') || (ch == '+') || (ch == '-') || (ch == '*') || (ch == '/')) {
                    operators.add(ch);
                }
                if (ch == 'x') {
                    root = ch;
                }
            }*/
        return true;

    }

    private boolean areBracketsCorrect (String equation){

        boolean result = false;
        Pattern pattern = Pattern.compile("\\(+|\\)+");
        Matcher matcher = pattern.matcher(equation);
        if (matcher.find()){
            String[] strArr = equation.split("=");
            for (String s : strArr) {
                result = checkBracketsBalance(s);
            }
        } else {
            result = true;
        }
        return result;

    }

    private boolean checkBracketsBalance(String str){
        Deque<Character> openBrackets = new ArrayDeque<>();
        Deque<Character> closeBrackets = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(')
                openBrackets.push(c);
            if ((c == ')') && !openBrackets.isEmpty())
                closeBrackets.push(c);
        }
        return (openBrackets.size() == closeBrackets.size());
    }

}
