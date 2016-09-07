package cn.examen.domain.course;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Major {
    private String majorId;
    private String majorName;
    private String collId;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("collId",getMajorId())
                .append("collName",getMajorName())
                .append("collId",getCollId())
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getMajorId())
                .append(getMajorName())
                .append(getCollId())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Major == false) return false;
        if(this == obj) return true;

        Major other = (Major)obj;
        boolean b0 = new EqualsBuilder().append(getCollId(), other.getCollId()).isEquals();
        if(!b0) return false;

        boolean b1 = new EqualsBuilder().append(getMajorId(), other.getMajorId()).isEquals();
        boolean b2 = new EqualsBuilder().append(getMajorName(), other.getMajorName()).isEquals();
        return b1 || b2;
    }
}