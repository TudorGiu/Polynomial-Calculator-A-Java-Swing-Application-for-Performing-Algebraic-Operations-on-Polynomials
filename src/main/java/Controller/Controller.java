package Controller;


import Model.DivisionByZeroException;
import Model.Operations;
import Model.Polynomial;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public static String performAddition(String input1, String input2){
        Polynomial p1 = testValidInput(input1);
        Polynomial p2 = testValidInput(input2);
        if(p1 == null || p2 == null)
            return "Invalid input";
        else
        {
            return Operations.addition(p1,p2).toString();
        }
    }

    public static String performSubtraction(String input1, String input2){
        Polynomial p1 = testValidInput(input1);
        Polynomial p2 = testValidInput(input2);
        if(p1 == null || p2 == null)
            return "Invalid input";
        else
        {
            return Operations.subtraction(p1,p2).toString();
        }
    }

    public static String performMultiplication(String input1, String input2){
        Polynomial p1 = testValidInput(input1);
        Polynomial p2 = testValidInput(input2);
        if(p1 == null || p2 == null)
            return "Invalid input";
        else
        {
            return Operations.multiplication(p1,p2).toString();
        }
    }

    public static String performDivision(String input1, String input2){
        Polynomial p1 = testValidInput(input1);
        Polynomial p2 = testValidInput(input2);
        if(p1 == null || p2 == null)
            return "Invalid input";
        else
        {
            String s = null;
            try {
                s = Operations.division(p1,p2).toString();
            } catch (DivisionByZeroException ex) {
                s = "Invalid division by 0";
            }
            return s;
        }
    }

    public static String performDifferentiation(String input1){
        Polynomial p1 = testValidInput(input1);
        if(p1 == null)
            return "Invalid input";
        else
        {
           return Operations.differentiation(p1).toString();
        }
    }

    public static String performIntegration(String input1){
        Polynomial p1 = testValidInput(input1);
        if(p1 == null)
           return "Invalid input";
        else
        {
            return Operations.integration(p1).toString();
        }
    }

    private static Polynomial testValidInput(String input) {

        if(input.isEmpty()){
            Polynomial result = new Polynomial();
            result.addMonomial(0, 0.);
            return result;
        }

        input = input.replace("-", "+-");
        input = input.replaceAll(" ", "");

        String[] args = Arrays.stream(input.split("\\+", 0)).filter(e -> e.trim().length() > 0).toArray(String[]::new);

        Pattern pattern = Pattern.compile("-?\\d*(\\.\\d+)?x(\\^\\d+)?|-?\\d+(\\.\\d+)?");

        boolean ok = false;

        int counter = 0;

        for (String s : args) {
            Matcher matcher = pattern.matcher(s);

            while (matcher.find())
                if (matcher.start() == 0 && matcher.end() == s.length())
                    counter++;
        }

        if(counter == args.length)
            ok = true;
        else
            ok = false;

        if(ok == true)
        {
            Polynomial result = new Polynomial();

            for(String s : args){
                if(s != ""){
                    IntegerAndDouble integerAndDouble = monomialStringToPoly(s);
                    if(result.getMonomials().containsKey(integerAndDouble.getInteger()))
                        result.getMonomials().put(integerAndDouble.getInteger(), integerAndDouble.getaDouble() + result.getMonomials().get(integerAndDouble.getInteger()));
                    else
                        result.getMonomials().put(integerAndDouble.getInteger(), integerAndDouble.getaDouble());
                }
            }

            result.clearZeros();

            if(result.getMonomials().isEmpty())
                result.getMonomials().put(0, 0.);

            return result;
        }else
            return null;
    }

    private static IntegerAndDouble monomialStringToPoly(String source){

        Integer exp;
        Double coef;

        String[] splitAfterExpSign = source.split("\\^");

        if(splitAfterExpSign.length == 2)
        {
            exp = Integer.parseInt(splitAfterExpSign[1]);
        }
        else if(splitAfterExpSign[0].contains("x"))
            exp = 1;
        else
            exp = 0;

        String[] splitAfterX = splitAfterExpSign[0].split("x");

        if(splitAfterX.length == 0)
            coef = 1.;
        else if(splitAfterX.length == 1 && splitAfterX[0].charAt(0) == '-'){
            coef = -1.;
        } else {
            coef = Double.parseDouble(splitAfterX[0]);
        }
        return new IntegerAndDouble(exp, coef);
    }
}
