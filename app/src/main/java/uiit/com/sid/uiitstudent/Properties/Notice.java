package uiit.com.sid.uiitstudent.Properties;

public class Notice {
    String id;
    String hyperlink;
    String title;
    Boolean status;

    public Notice() {
    }

    public Notice(String id, String hyperlink, String title, Boolean status) {
        this.id = id;
        this.hyperlink = hyperlink;
        this.title = title;
        this.status = status;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
