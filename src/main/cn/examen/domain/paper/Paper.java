package cn.examen.domain.paper;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

public class Paper {
    private String paperId;
    private String userId;
    private String userRealname;
    private String collId;
    private String majorId;
    private String courseId;
    private String courseName;
    private Integer choiceScore;
    private Integer fillinScore;
    private Integer essayScore;
    private Integer discussScore;
    private String paperType;
    private String useClasses;
    private String useTerm;
    private Integer fromYear;
    private Integer toYear;
    private Date createtime;
    private Integer vetted;
    private String assessorId;
    private String assessorName;

    public Paper() {
        super();
    }
    
    public Paper(Paper paper){
        this.paperId = paper.getPaperId();
    	this.userId = paper.getUserId();
    	this.userRealname = paper.getUserRealname();
    	this.collId = paper.getCollId();
    	this.majorId = paper.getMajorId();
    	this.courseId = paper.getCourseId();
    	this.courseName = paper.getCourseName();
    	this.choiceScore = paper.getChoiceScore();
    	this.fillinScore = paper.getFillinScore();
    	this.essayScore = paper.getEssayScore();
    	this.discussScore = paper.getDiscussScore();
    	this.createtime = paper.getCreatetime();
    	this.paperType = paper.getPaperType();
    	this.useClasses = paper.getUseClasses();
    	this.useTerm = paper.getUseTerm();
    	this.fromYear = paper.getFromYear();
    	this.toYear = paper.getToYear();
    	this.vetted = paper.getVetted();
    	this.assessorId = paper.getAssessorId();
    	this.assessorName = paper.getAssessorName();
    }

    public String getCollId() {
        return collId;
    }

    public void setCollId(String collId) {
        if(collId!=null){
            if(collId.length() > 0)
                this.collId = collId;
            else
                this.collId = null;
        } else this.collId = null;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        if(paperId!=null){
            if(paperId.length() > 0)
                this.paperId = paperId;
            else
                this.paperId = null;
        } else this.paperId = null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if(userId!=null){
            if(userId.length() > 0)
                this.userId = userId;
            else
                this.userId = null;
        } else this.userId = null;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        if(userRealname!=null){
            if(userRealname.length() > 0)
                this.userRealname = userRealname;
            else
                this.userRealname = null;
        } else this.userRealname = null;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        if(majorId!=null){
            if(majorId.length() > 0)
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
            if(courseId.length() > 0)
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
            if(courseName.length() > 0)
                this.courseName = courseName;
            else
                this.courseName = null;
        } else this.courseName = null;
    }

    public Integer getChoiceScore() {
        return choiceScore;
    }

    public void setChoiceScore(Integer choiceScore) {
        if(choiceScore!=null){
            if(choiceScore < 0)
                this.choiceScore = choiceScore;
            else
                this.choiceScore = null;
        } else this.choiceScore = null;
    }

    public Integer getFillinScore() {
        return fillinScore;
    }

    public void setFillinScore(Integer fillinScore) {
        if(fillinScore!=null){
            if(fillinScore < 0)
                this.fillinScore = fillinScore;
            else
                this.fillinScore = null;
        } else this.fillinScore = null;
    }

    public Integer getEssayScore() {
        return essayScore;
    }

    public void setEssayScore(Integer essayScore) {
        if(essayScore!=null){
            if(essayScore < 0)
                this.essayScore = essayScore;
            else
                this.essayScore = null;
        } else this.essayScore = null;
    }

    public Integer getDiscussScore() {
        return discussScore;
    }

    public void setDiscussScore(Integer discussScore) {
        if(discussScore!=null){
            if(discussScore < 0)
                this.discussScore = discussScore;
            else
                this.discussScore = null;
        } else this.discussScore = null;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        if(paperType!=null){
            if(paperType.length() > 0)
                this.paperType = paperType;
            else
                this.paperType = null;
        } else this.paperType = null;
    }

    public String getUseClasses() {
        return useClasses;
    }

    public void setUseClasses(String useClasses) {
        if(useClasses!=null){
            if(useClasses.length() > 0)
                this.useClasses = useClasses;
            else
                this.useClasses = null;
        } else this.useClasses = null;
    }

    public String getUseTerm() {
        return useTerm;
    }

    public void setUseTerm(String useTerm) {
        if(useTerm!=null){
            if(useTerm.length() > 0)
                this.useTerm = useTerm;
            else
                this.useTerm = null;
        } else this.useTerm = null;
    }

    public Integer getFromYear() {
        return fromYear;
    }

    public void setFromYear(Integer fromYear) {
        if(fromYear!=null){
            if(fromYear < 0)
                this.fromYear = fromYear;
            else
                this.fromYear = null;
        } else this.fromYear = null;
    }

    public Integer getToYear() {
        return toYear;
    }

    public void setToYear(Integer toYear) {
        if(toYear!=null){
            if(toYear < 0)
                this.toYear = toYear;
            else
                this.toYear = null;
        } else this.toYear = null;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getVetted() {
        return vetted;
    }

    public void setVetted(Integer vetted) {
        if(vetted!=null){
            if(vetted > 0 && vetted < 5)
                this.vetted = vetted;
            else
                this.vetted = null;
        } else this.vetted = null;
    }

    public String getAssessorId() {
        return assessorId;
    }

    public void setAssessorId(String assessorId) {
        if(assessorId!=null){
            if(assessorId.length() > 0)
                this.assessorId = assessorId;
            else
                this.assessorId = null;
        } else this.assessorId = null;
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        if(assessorName!=null){
            if(assessorName.length() > 0)
                this.assessorName = assessorName;
            else
                this.assessorName = null;
        } else this.assessorName = null;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("paperId",getPaperId())
                .append("userId",getUserId())
                .append("userRealname",getUserRealname())
                .append("collId",getCollId())
                .append("majorId",getMajorId())
                .append("courseId",getCourseId())
                .append("courseName",getCourseName())
                .append("choiceScore",getChoiceScore())
                .append("fillinScore",getFillinScore())
                .append("essayScore",getEssayScore())
                .append("discussScore",getDiscussScore())
                .append("paperType",getPaperType())
                .append("useClasses",getUseClasses())
                .append("useTerm",getUseTerm())
                .append("fromYear",getFromYear())
                .append("toYear",getToYear())
                .append("createtime",getCreatetime())
                .append("vetted",getVetted())
                .append("assessorId",getAssessorId())
                .append("assessorName",getAssessorName())
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getPaperId())
                .append(getUserId())
                .append(getUserRealname())
                .append(getCollId())
                .append(getMajorId())
                .append(getCourseId())
                .append(getCourseName())
                .append(getChoiceScore())
                .append(getFillinScore())
                .append(getEssayScore())
                .append(getDiscussScore())
                .append(getPaperType())
                .append(getUseClasses())
                .append(getUseTerm())
                .append(getFromYear())
                .append(getToYear())
                .append(getCreatetime())
                .append(getVetted())
                .append(getAssessorId())
                .append(getAssessorName())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Paper == false) return false;
        if(this == obj) return true;
        Paper other = (Paper)obj;
        return new EqualsBuilder()
                .append(getPaperId(), other.getPaperId())
                .isEquals();
    }
}