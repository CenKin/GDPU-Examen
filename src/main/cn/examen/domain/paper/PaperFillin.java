package cn.examen.domain.paper;

public class PaperFillin {
    private Integer id;

    private String paperId;

    private String fillinId;

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

    public String getFillinId() {
        return fillinId;
    }

    public void setFillinId(String fillinId) {
        if(fillinId!=null){
            if(fillinId.trim().length() > 0)
                this.fillinId = fillinId;
            else
                this.fillinId = null;
        } else this.fillinId = null;
    }
}