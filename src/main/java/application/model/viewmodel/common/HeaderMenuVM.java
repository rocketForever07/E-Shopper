package application.model.viewmodel.common;

public class HeaderMenuVM {
    private String name;
    private String link;
    private String faName;

    public HeaderMenuVM() {
    }

    public HeaderMenuVM(String name, String link, String faName) {
        this.name = name;
        this.link = link;
        this.faName = faName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }
}
