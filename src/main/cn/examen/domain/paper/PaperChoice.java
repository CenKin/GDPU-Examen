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
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId == null ? null : choiceId.trim();
    }
}