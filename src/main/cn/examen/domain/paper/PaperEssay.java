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
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getEssayId() {
        return essayId;
    }

    public void setEssayId(String essayId) {
        this.essayId = essayId == null ? null : essayId.trim();
    }
}