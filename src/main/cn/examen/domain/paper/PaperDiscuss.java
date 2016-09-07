package cn.examen.domain.paper;

public class PaperDiscuss {
    private Integer id;

    private String paperId;

    private String discussId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        if(paperId!=null){
            if(paperId.trim().length() > 0)
                this.paperId = paperId;
            else
                this.paperId = null;
        } else this.paperId = null;
    }

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        if(discussId!=null){
            if(discussId.trim().length() > 0)
                this.discussId = discussId;
            else
                this.discussId = null;
        } else this.discussId = null;
    }
}