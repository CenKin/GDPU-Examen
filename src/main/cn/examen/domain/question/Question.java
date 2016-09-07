package cn.examen.domain.question;

import cn.examen.utils.annotation.ExcelAnnotation;

public class Question {
    @ExcelAnnotation(columnName="id")
    protected String id;
    @ExcelAnnotation(columnName="courseId")
    protected String courseId;
    @ExcelAnnotation(columnName="courseName")
    protected String courseName;
    @ExcelAnnotation(columnName="content")
    protected String content;
    @ExcelAnnotation(columnName="section")
    protected String section;
    @ExcelAnnotation(columnName="diff")
    protected Integer diff;
    @ExcelAnnotation(columnName="imagepath")
    protected String imagepath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id!=null){
            if(id.trim().length() > 0)
                this.id = id;
            else
                this.id = null;
        } else this.id = null;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(content!=null){
            if(content.trim().length() > 0)
                this.content = content;
            else
                this.content = null;
        } else this.content = null;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        if(section!=null){
            if(section.trim().length() > 0)
                this.section = section;
            else
                this.section = null;
        } else this.section = null;
    }

    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        if(diff!=null){
            if(diff >= 1 && diff <= 5)
                this.diff = diff;
            else
                this.diff = null;
        } else this.diff = null;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        if(imagepath!=null){
            if(imagepath.trim().length() > 0)
                this.imagepath = imagepath;
            else
                this.imagepath = null;
        } else this.imagepath = null;
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
}
