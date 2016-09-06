package cn.examen.domain.question;

import cn.examen.utils.annotation.ExcelAnnotation;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Choice extends Question{

    @ExcelAnnotation(columnName="answer1")
    private String answer1;
    @ExcelAnnotation(columnName="answer2")
    private String answer2;
    @ExcelAnnotation(columnName="answer3")
    private String answer3;
    @ExcelAnnotation(columnName="answer4")
    private String answer4;
    @ExcelAnnotation(columnName="rightAnswer")
    private String rightAnswer;

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1 == null ? null : answer1.trim();
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2 == null ? null : answer2.trim();
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3 == null ? null : answer3.trim();
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4 == null ? null : answer4.trim();
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer == null ? null : rightAnswer.trim();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("courseId",getCourseId())
                .append("content",getContent())
                .append("section",getSection())
                .append("answer1",getAnswer1())
                .append("answer2",getAnswer2())
                .append("answer3",getAnswer3())
                .append("answer4",getAnswer4())
                .append("rightAnswer",getRightAnswer())
                .append("diff",getDiff())
                .append("imagepath",getImagepath())
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .append(getCourseId())
                .append(getContent())
                .append(getSection())
                .append(getAnswer1())
                .append(getAnswer2())
                .append(getAnswer3())
                .append(getAnswer4())
                .append(getRightAnswer())
                .append(getDiff())
                .append(getImagepath())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Choice == false) return false;
        if(this == obj) return true;
        Choice other = (Choice)obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}