import java.io.Serializable;

/**
 * @author miche
 */

public class News implements Serializable {

    private String title;
    private String category;
    private String dare;
    private String image;

    public News(String title, String category, String dare, String image) {
        this.title = title;
        this.category = category;
        this.dare = dare;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDare() {
        return dare;
    }

    public String getImage() {
        return image;
    }
}