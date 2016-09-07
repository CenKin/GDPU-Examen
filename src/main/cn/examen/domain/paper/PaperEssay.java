package cn.examen.domain.paper;

public class PaperEssay {
    private Integer id;

    private String paperId;

    private String essayId;

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

    public String getEssayId() {
        return essayId;
    }

    public void setEssayId(String essayId) {
        if(essayId!=null){
            if(essayId.trim().length() > 0)
                this.essayId = essayId;
            else
                this.essayId = null;
        } else this.essayId = null;
    }

}