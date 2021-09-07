package by.gosha_krovsh.quizer.tasks;

import by.gosha_krovsh.quizer.Operator;
import by.gosha_krovsh.quizer.Result;
import by.gosha_krovsh.quizer.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// leftNumber <operator> rightNumber = answer
public class ExpressionTask implements Task {
    public ExpressionTask(int leftNumber, int rightNumber, Operator operator) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
        this.operator = operator;

        this.answer = Integer.toString(calculateResult());
    }

    @Override
    public String getText() {
        return Integer.toString(leftNumber) + getOperatorString() + Integer.toString(rightNumber);
    }

    @Override
    public Result validate(String answer) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(answer);
        if (!matcher.matches()) {
            return Result.INCORRECT_INPUT;
        }

        return answer.equals(this.answer) ? Result.OK : Result.WRONG;
    }

    private int calculateResult(){
        switch (this.operator) {
            case PLUS: return leftNumber + rightNumber;
            case MINUS: return leftNumber - rightNumber;
            case MULTIPLY: return leftNumber * rightNumber;
            case DIVIDE: return leftNumber / rightNumber;
        }
        return 0;
    }

    private String getOperatorString() {
        //TODO(George) correct divide and throw exceptions
        switch (this.operator) {
            case PLUS: return "+";
            case MINUS: return "-";
            case MULTIPLY: return "*";
            case DIVIDE: return "/";
        }
        return "";
    }

    private final int leftNumber;
    private final int rightNumber;
    private final Operator operator;

    private final String answer;
}
