package by.gosha_krovsh.quizer.tasks;

import by.gosha_krovsh.quizer.Operator;
import by.gosha_krovsh.quizer.Result;
import by.gosha_krovsh.quizer.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionTask implements Task {
    public ExpressionTask(int leftNumber, int rightNumber, Operator operator) {
        this.lhs = leftNumber;
        this.rhs = rightNumber;
        this.operator = operator;

        this.answer = Integer.toString(calculateResult());
    }

    @Override
    public String getText() {
        return Integer.toString(lhs) + getOperatorString() + Integer.toString(rhs);
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
            case PLUS: return lhs + rhs;
            case MINUS: return lhs - rhs;
            case MULTIPLY: return lhs * rhs;
            case DIVIDE: return lhs / rhs;
        }
        return 0;
    }

    private String getOperatorString() {
        switch (this.operator) {
            case PLUS: return "+";
            case MINUS: return "-";
            case MULTIPLY: return "*";
            case DIVIDE: return "/";
        }
        return "";
    }

    private final int lhs;
    private final int rhs;
    private final Operator operator;

    private final String answer;
}
