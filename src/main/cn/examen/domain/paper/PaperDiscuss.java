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
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        this.discussId = discussId == null ? null : discussId.trim();
    }
}