package cn.examen.domain.course;

/**
 * Created by Administrator on 2016/9/2.
 */
public class CourseEx extends Course{
    private String collId;
    private String collName;
    private String majorId;
    private String majorName;

    public CourseEx(){
        super();
    }

    public CourseEx(Course course, Major major, College coll){
        super.setCourseId(course.getCourseId());
        super.setCourseName(course.getCourseName());
        this.collId = coll.getCollId();
        this.collName = coll.getCollName();
        this.majorId = major.getMajorId();
        this.majorName = major.getMajorName();
    }

    public String getCollId() {
        return collId;
    }

    public void setCollId(String collId) {
        if(collId!=null){
            if(collId.trim().length() > 0)
                this.collId = collId;
            else
                this.collId = null;
        } else this.collId = null;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        if(collName!=null){
            if(collName.trim().length() > 0)
                this.collName = collName;
            else
                this.collName = null;
        } else this.collName = null;
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

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        if(majorName!=null){
            if(majorName.trim().length() > 0)
                this.majorName = majorName;
            else
                this.majorName = null;
        } else this.majorName = null;
    }
}
