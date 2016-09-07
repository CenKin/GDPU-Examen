package cn.examen.domain.paper;

public class PaperChoice {
    private Integer id;

    private String paperId;

    private String choiceId;

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

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        if(choiceId!=null){
            if(choiceId.trim().length() > 0)
                this.choiceId = choiceId;
            else
                this.choiceId = null;
        } else this.choiceId = null;
    }
}