package sanastix.homeprojects;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
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
        if(!isInputCorrect(eq)||!areBracketsCorrect(eq)){
            return "Incorrect input";
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
        Queue<String> numbers = new LinkedList<>();
        while(matcher.find()){
            numbers.add(matcher.group());
            count++;
        }
        setNumsInEquation(count);
    }

    private boolean isInputCorrect (String equation) {

        //перевіряємо рівняння на коректність операторів
        Pattern wrongOp = Pattern.compile("=?[-+=*/]{2,}");
        Matcher wrongOpMatcher = wrongOp.matcher(equation);
        Pattern rightOp = Pattern.compile("=[-+=*/](-)");
        Matcher rightOpMatcher = rightOp.matcher(equation);

        if (wrongOpMatcher.find() && !rightOpMatcher.find()){
            return false;
        }

        //виокремлюємо з рівняння оператори
        Queue<Character> operators = new LinkedList<>();
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if ((ch == '=') || (ch == '+') || (ch == '-') || (ch == '*') || (ch == '/')){
                operators.add(ch);
            }
            if (ch == 'x'){
                root = ch;
            }
        }
        return true;

    }

    private boolean areBracketsCorrect (String equation){

        boolean result = true;
        String[] strArr = equation.split("=");
        for (String s : strArr) {
            result = checkBracketsBalance(s);
        }
        return result;

    }

    private boolean checkBracketsBalance(String str){
        Deque<Character> brackets = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                brackets.push(c);
            } else if (c == ')'){
                if (!brackets.isEmpty()){
                    brackets.pop();
                } else {
                    return false;
                }
            }
        }
        return brackets.isEmpty();
    }

}
