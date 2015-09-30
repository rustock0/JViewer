package tk.jviewer.service;

import tk.jviewer.model.Answer;
import tk.jviewer.model.Question;
import tk.jviewer.model.Test;

import java.util.List;

/**
 * Created by Sergey Yaskov on 29.09.2015.
 */
public interface QuizService {

    Test createQuiz();

    List<Test> findQuizzes();

    void updateQuiz(Test quiz);

    List<Question> findQuestionsForQuiz(long quizId);

    Question findQuestion(long id);

    void createQuestion(Test quiz, String text);

    void createAnswer(Question question, Answer answer);

    void removeAnswer(Question question, long answerId);

}