package by.gosha_krovsh.quizer;

import java.util.HashMap;

public class Quiz {
    static HashMap<String, Quiz> getQuizMap() {
        // TODO(George) realise after Task and TaskGenerator
        return new HashMap<String, Quiz>();
    }

    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    Task nextTask() {
        if (taskCount == 0) {
            // TODO(George) throw exception
        }

        if (isInputCorrect) {
            currentTask = generator.generate();
            --taskCount;
        }
        return currentTask;
    }

    Result provideAnswer(String answer) {
        Result result = currentTask.validate(answer);
        switch (result) {
            case OK: {
                isInputCorrect = true;
                ++correctAnswerNumber;
            }
            case WRONG: {
                isInputCorrect = true;
                ++wrongAnswerNumber;
            }
            case INCORRECT_INPUT: {
                isInputCorrect = false;
                ++incorrectInputNumber;
            }
        }
        return result;
    }

    public double getMark() {
        if (taskCount > 0) {
            // TODO(George) throw exception
        }

        return 1. * correctAnswerNumber / (correctAnswerNumber + wrongAnswerNumber);
    }

    boolean isFinished() {
        return taskCount == 0;
    }

    public int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    public int getWrongAnswerNumber() {
        return wrongAnswerNumber;
    }

    public int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }

    private int correctAnswerNumber;
    private int wrongAnswerNumber;
    private int incorrectInputNumber;

    private Task currentTask;
    private boolean isInputCorrect = true;

    private final TaskGenerator generator;
    private int taskCount;
}
