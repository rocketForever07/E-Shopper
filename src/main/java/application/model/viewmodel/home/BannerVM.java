package application.model.viewmodel.home;

public class BannerVM {
    private String name;
    private String altText;
    private String imageUrl;

    public BannerVM(String name, String altText, String imageUrl) {
        this.name = name;
        this.altText = altText;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

