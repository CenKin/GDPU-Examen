package cn.examen.domain.question;

import cn.examen.utils.annotation.ExcelAnnotation;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Fillin extends Question{
    @ExcelAnnotation(columnName="correctionFill")
    private String correctionFill;

    public String getCorrectionFill() {
        return correctionFill;
    }

    public void setCorrectionFill(String correctionFill) {
        this.correctionFill = correctionFill == null ? null : correctionFill.trim();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("courseId",getCourseId())
                .append("content",getContent())
                .append("section",getSection())
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
                .append(getDiff())
                .append(getImagepath())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Fillin == false) return false;
        if(this == obj) return true;
        Fillin other = (Fillin)obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}