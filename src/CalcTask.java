import java.util.Scanner;

class Calculator{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Main result = new Main();
        System.out.println("Input:");
        String exp = in.nextLine();
        String answer = result.calc(exp);


        System.out.println("Output:\n" + answer);
    }
}

class Main{
    public static String calc(String input){
        boolean romanOrArab = false;
        String exp = "throws Exception";
        Main romanCheck = new Main();
        Main arabToRoman = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3){
            return exp;
        }
        Integer num1 = 0;
        Integer num2 = 0;
        try {
            num1 = Integer.valueOf(inputSplit[0]);
            num2 = Integer.valueOf(inputSplit[2]);
        } catch (NumberFormatException e) {
            try {
                num1 = romanCheck.romanToArab(inputSplit[0]);
                num2 = romanCheck.romanToArab(inputSplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException ex) {
                return exp;
            }
        }

        if ((num1 < 1) || (num1 > 10) || (num2 < 1) || (num2 > 10)){
            return exp;
        }

        String sign = inputSplit[1];
        switch (sign) {
            case "+" -> result = num1 + num2;
            case "-" -> result =  num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> result = num1 / num2;
            default -> {
                return exp;
            }
        }

        String output;

        if (romanOrArab){
            if(result < 1){
                return exp;
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }

        return output;
    }

    Integer romanToArab(String romanInput){
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }

        return result;
    }

    String arabToRome(int arabInput){
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}
