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
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getFillinId() {
        return fillinId;
    }

    public void setFillinId(String fillinId) {
        this.fillinId = fillinId == null ? null : fillinId.trim();
    }
}