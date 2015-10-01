package tk.jviewer.dao.quiz;

import tk.jviewer.model.Question;
import tk.jviewer.model.Result;
import tk.jviewer.model.Test;

import java.util.List;

/**
 * Testing Data Access Object interface.
 *
 * @author Evgeny Mironenko
 */
public interface QuizDao {

    long createQuiz(String name, int questionsToAnswerToPass);

    void updateQuiz(Test quiz);

    List<Test> findQuizzes();

    void removeQuiz(Test quiz);

}
