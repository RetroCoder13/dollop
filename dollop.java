import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class dollop {
    public static int lineNumber = 0;
    public static final String[] numberFunctions = new String[]{"ADD","SUB","MUL","DIV","POW","RAND"};
    public static final String[] compareFunctions = new String[]{"AND","OR","NOT","EQL","GRT","LST"};
    public static void main(String args[]){
        String[] input;
        HashMap<String,String> variables = new HashMap<String,String>();
        variables.put("PI",Double.toString(Math.PI));
        variables.put("E",Double.toString(Math.E));

        if(args.length != 0){
            function_run(args[0], variables);
        }

        while(true){
            input = System.console().readLine().split(" ");
            function(input, variables);
        }
    }

    public static void function(String[] input, HashMap<String,String> variables){
        if(input[0].equals("OUT")){
            if(Arrays.asList(numberFunctions).contains(input[1])){
                function_out(Double.toString(function_numbers(input[2], input[3], input[1], variables)), variables);
            } else if(Arrays.asList(compareFunctions).contains(input[1])){
                if(input[1].equals("NOT")){
                    function_out(Boolean.toString(function_compare(input[2], "false", input[1], variables)), variables);
                } else {
                    function_out(Boolean.toString(function_compare(input[2], input[3], input[1], variables)), variables);
                }
            } else {
                for(int i=0;i<input.length-1;i++){
                    function_out(input[i+1], variables);
                    function_out(" ", variables);
                }
            }
            function_out("\n", variables);
        }

        if(input[0].equals("VAR")){
            if(!(input[1].equals(input[1].toUpperCase()) && variables.get(input[1]) != null)){
                if(Arrays.asList(numberFunctions).contains(input[2])){
                    function_var(input[1], Double.toString(function_numbers(input[3], input[4], input[2], variables)), variables);
                } else {
                    function_var(input[1], input[2], variables);
                }
            }
        }

        if(input[0].equals("RUN")){
            function_run(input[1], variables);
        }

        if(input[0].equals("EXT")){
            System.exit(0);
        }

        if(input[0].equals("IF")){
            if(input[1].equals("NOT")){
                function_if(input[2], "false", input[1], Arrays.copyOfRange(input, 3, input.length), variables);
            } else {
                function_if(input[2], input[3], input[1], Arrays.copyOfRange(input, 4, input.length), variables);
            }
        }

        if(input[0].equals("GO")){
            function_go(input[1], variables);
        }
    }

    public static void function_run(String path, HashMap<String,String> variables){
        File file = new File(path);
        if(file.exists()){
            try{
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("\\Z");
                String[] fileRead = scanner.next().split("\n");
                while(lineNumber <= fileRead.length-1){
                    String[] input = fileRead[lineNumber].split(" ");
                    function(input, variables);
                    lineNumber++;
                }
                scanner.close();
            } catch(FileNotFoundException e) {}
        }
        lineNumber = 0;
    }

    public static void function_out(String output, HashMap<String,String> variables){
        if(Character.toString(output.charAt(0)).equals("{") && Character.toString(output.charAt(output.length()-1)).equals("}") && variables.keySet().contains(output.substring(1, output.length()-1))){
            System.out.print(variables.get(output.substring(1, output.length()-1)));
        } else if(output.equals("IN")){
            System.out.print(function_in());
        } else if (output.equals("\\n")){
            System.out.println();
        } else {
            System.out.print(output);
        }
    }
    public static String function_in(){
        return System.console().readLine();
    }

    public static void function_var(String variableName, String input, HashMap<String,String> variables){
        if(input.equals("IN")){
            variables.put(variableName, function_in());
        } else {
            variables.put(variableName, input);
        }
    }

    public static double function_numbers(String a, String b, String operation, HashMap<String,String> variables){
        if(variables.keySet().contains(a) && variables.keySet().contains(b)){
            if(operation.equals("ADD")){
                return function_add(Double.parseDouble(variables.get(a)), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("SUB")){
                return function_sub(Double.parseDouble(variables.get(a)), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("MUL")){
                return function_mul(Double.parseDouble(variables.get(a)), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("DIV")){
                return function_div(Double.parseDouble(variables.get(a)), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("POW")){
                return function_pow(Double.parseDouble(variables.get(a)), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("RAND")){
                return function_rand(Double.parseDouble(variables.get(a)), Double.parseDouble(variables.get(b)));
            }
        } else if(variables.keySet().contains(a)){
            if(operation.equals("ADD")){
                return function_add(Double.parseDouble(variables.get(a)), Double.parseDouble(b));
            } else if(operation.equals("SUB")){
                return function_sub(Double.parseDouble(variables.get(a)), Double.parseDouble(b));
            } else if(operation.equals("MUL")){
                return function_mul(Double.parseDouble(variables.get(a)), Double.parseDouble(b));
            } else if(operation.equals("DIV")){
                return function_div(Double.parseDouble(variables.get(a)), Double.parseDouble(b));
            } else if(operation.equals("POW")){
                return function_pow(Double.parseDouble(variables.get(a)), Double.parseDouble(b));
            } else if(operation.equals("RAND")){
                return function_rand(Double.parseDouble(variables.get(a)), Double.parseDouble(b));
            }
        } else if(variables.keySet().contains(b)){
            if(operation.equals("ADD")){
                return function_add(Double.parseDouble(a), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("SUB")){
                return function_sub(Double.parseDouble(a), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("MUL")){
                return function_mul(Double.parseDouble(a), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("DIV")){
                return function_div(Double.parseDouble(a), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("POW")){
                return function_pow(Double.parseDouble(a), Double.parseDouble(variables.get(b)));
            } else if(operation.equals("RAND")){
                return function_rand(Double.parseDouble(a), Double.parseDouble(variables.get(b)));
            }
        } else {
            if(operation.equals("ADD")){
                return function_add(Double.parseDouble(a), Double.parseDouble(b));
            } else if(operation.equals("SUB")){
                return function_sub(Double.parseDouble(a), Double.parseDouble(b));
            } else if(operation.equals("MUL")){
                return function_mul(Double.parseDouble(a), Double.parseDouble(b));
            } else if(operation.equals("DIV")){
                return function_div(Double.parseDouble(a), Double.parseDouble(b));
            } else if(operation.equals("POW")){
                return function_pow(Double.parseDouble(a), Double.parseDouble(b));
            } else if(operation.equals("RAND")){
                return function_rand(Double.parseDouble(a), Double.parseDouble(b));
            }
        }
        return 0;
    }
    public static double function_add(double a, double b){
        return a+b;
    }
    public static double function_sub(double a, double b){
        return a-b;
    }
    public static double function_mul(double a, double b){
        return a*b;
    }
    public static double function_div(double a, double b){
        return a/b;
    }
    public static double function_pow(double a, double b){
        return Math.pow(a,b);
    }
    public static double function_rand(double a, double b){
        return (int)((Math.random() * (b+1 - a)) + a);
    }

    public static boolean function_compare(String a, String b, String operation, HashMap<String,String> variables){
        if(variables.keySet().contains(a) && variables.keySet().contains(b)){
            if(operation.equals("AND")){
                return function_and(Boolean.parseBoolean(variables.get(a)), Boolean.parseBoolean(variables.get(b)));
            } else if(operation.equals("OR")){
                return function_or(Boolean.parseBoolean(variables.get(a)), Boolean.parseBoolean(variables.get(b)));
            } else if(operation.equals("NOT")){
                return function_not(Boolean.parseBoolean(variables.get(a)));
            } else if(operation.equals("EQL")){
                return function_equals(variables.get(a).toString(), variables.get(b).toString());
            } else if(operation.equals("GRT")){
                return function_greaterthan(Double.parseDouble(variables.get(a).toString()), Double.parseDouble(variables.get(b).toString()));
            } else if(operation.equals("LST")){
                return function_lessthan(Double.parseDouble(variables.get(a).toString()), Double.parseDouble(variables.get(b).toString()));
            } else {
                return false;
            }
        } else if(variables.keySet().contains(a)){
            if(operation.equals("AND")){
                return function_and(Boolean.parseBoolean(variables.get(a)), Boolean.parseBoolean(b));
            } else if(operation.equals("OR")){
                return function_or(Boolean.parseBoolean(variables.get(a)), Boolean.parseBoolean(b));
            } else if(operation.equals("NOT")){
                return function_not(Boolean.parseBoolean(variables.get(a)));
            } else if(operation.equals("EQL")){
                return function_equals(variables.get(a).toString(), b.toString());
            } else if(operation.equals("GRT")){
                return function_greaterthan(Double.parseDouble(variables.get(a).toString()), Double.parseDouble(b));
            } else if(operation.equals("LST")){
                return function_lessthan(Double.parseDouble(variables.get(a).toString()), Double.parseDouble(b));
            } else {
                return false;
            }
        } else if(variables.keySet().contains(b)){
            if(operation.equals("AND")){
                return function_and(Boolean.parseBoolean(a), Boolean.parseBoolean(variables.get(b)));
            } else if(operation.equals("OR")){
                return function_or(Boolean.parseBoolean(a), Boolean.parseBoolean(variables.get(b)));
            } else if(operation.equals("NOT")){
                return function_not(Boolean.parseBoolean(a));
            } else if(operation.equals("EQL")){
                return function_equals(a.toString(), variables.get(b).toString());
            } else if(operation.equals("GRT")){
                return function_greaterthan(Double.parseDouble(a), Double.parseDouble(variables.get(b).toString()));
            } else if(operation.equals("LST")){
                return function_lessthan(Double.parseDouble(a), Double.parseDouble(variables.get(b).toString()));
            } else {
                return false;
            }
        } else {
            if(operation.equals("AND")){
                return function_and(Boolean.parseBoolean(a), Boolean.parseBoolean(b));
            } else if(operation.equals("OR")){
                return function_or(Boolean.parseBoolean(a), Boolean.parseBoolean(b));
            } else if(operation.equals("NOT")){
                return function_not(Boolean.parseBoolean(a));
            } else if(operation.equals("EQL")){
                return function_equals(a.toString(), b.toString());
            } else if(operation.equals("GRT")){
                return function_greaterthan(Double.parseDouble(a), Double.parseDouble(b));
            } else if(operation.equals("LST")){
                return function_lessthan(Double.parseDouble(a), Double.parseDouble(b));
            } else {
                return false;
            }
        }
    }
    public static boolean function_and(boolean a, boolean b){
        return a && b;
    }
    public static boolean function_or(boolean a, boolean b){
        return a || b;
    }
    public static boolean function_not(boolean a){
        return !a;
    }
    public static boolean function_equals(String a, String b){
        return a.equals(b);
    }
    public static boolean function_greaterthan(double a, double b){
        return a > b;
    }
    public static boolean function_lessthan(double a, double b){
        return a < b;
    }

    public static void function_if(String a, String b, String operation, String[] output, HashMap<String,String> variables){
        if(function_compare(a, b, operation, variables)){
            function(output, variables);
        };
    }

    public static void function_go(String newLine, HashMap<String,String> variables){
        if(variables.keySet().contains(newLine)){
            lineNumber = Math.round(Float.parseFloat(variables.get(newLine)))-2;
        } else {
            lineNumber = Integer.parseInt(newLine)-2;
        }
    }
}
