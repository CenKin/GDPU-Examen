package cn.examen.domain.course;

public class MajorCourse {
    private Integer id;

    private String majorId;

    private String courseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        if(majorId!=null){
            if(majorId.trim().length() > 0)
                this.majorId = majorId;
            else
                this.majorId = null;
        } else this.majorId = null;
    }

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
}