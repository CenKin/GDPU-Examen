package cn.examen.domain.course;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Course {
    private String courseId;
    private String courseName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        if(courseId!=null){
            if(courseId.trim().length() > 0)
                this.courseId = courseId;
            else
                this.courseId = null;
        } else this.courseId = null;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if(courseName!=null){
            if(courseName.trim().length() > 0)
                this.courseName = courseName;
            else
                this.courseName = null;
        } else this.courseName = null;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("collId",getCourseId())
                .append("collName",getCourseName())
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getCourseId())
                .append(getCourseName())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Course == false) return false;
        if(this == obj) return true;
        Course other = (Course)obj;

        boolean b1 = new EqualsBuilder().append(getCourseId(), other.getCourseId()).isEquals();
        boolean b2 = new EqualsBuilder().append(getCourseName(), other.getCourseName()).isEquals();
        return b1 || b2;
    }
}