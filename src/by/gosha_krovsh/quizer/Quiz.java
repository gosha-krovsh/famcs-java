package by.gosha_krovsh.quizer;

import by.gosha_krovsh.quizer.task_generators.GroupTaskGenerator;
import by.gosha_krovsh.quizer.task_generators.PoolTaskGenerator;
import by.gosha_krovsh.quizer.tasks.TextTask;

import java.util.HashMap;

public class Quiz {
    static HashMap<String, Quiz> getQuizMap() {
        // TODO(George) realise after Task and TaskGenerator
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Text", new Quiz(new PoolTaskGenerator(true, new TextTask("Да?", "Да")), 1));
        return map;
    }

    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    Task nextTask() throws RuntimeException {
        if (taskCount == 0) {
            throw new RuntimeException("Quiz is finished");
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
                break;
            }
            case WRONG: {
                isInputCorrect = true;
                ++wrongAnswerNumber;
                break;
            }
            case INCORRECT_INPUT: {
                isInputCorrect = false;
                ++incorrectInputNumber;
                break;
            }
        }
        return result;
    }

    public double getMark() throws RuntimeException {
        if (taskCount > 0) {
            throw new RuntimeException("Quiz is not finished");
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
