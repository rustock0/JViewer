package tk.jviewer.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Answer representation.
 *
 * @author Evgeny Mironenko
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = -1283565263146494192L;

    private Integer id;
    private String text;
    private boolean correct;

    public Answer() {
    }

    public Answer(String text) {
        this(null, text);
    }

    public Answer(String text, boolean correct) {
        this(null, text, correct);
    }

    public Answer(Integer id, String text) {
        this(id, text, false);
    }

    public Answer(Integer id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return new EqualsBuilder()
                .append(correct, answer.correct)
                .append(text, answer.text)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(text)
                .append(correct)
                .toHashCode();
    }

    public static Answer lookupById(final List<Answer> answers, final Integer id) {
        for (final Answer answer : answers) {
            if (answer.getId().equals(id)) {
                return answer;
            }
        }

        throw new RuntimeException("No answer with id " + id + " found");
    }

    public static Answer createTextualAnswer(final String text) {
        return new Answer(text, true);
    }

    public static Answer createSelectableAnswer(final String text, final boolean correct) {
        return new Answer(text, correct);
    }

}
