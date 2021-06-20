package club.thepenguins.android.data;

public class Model {
    public String title, subtitle, Image, Content, author;

    public Model(String title, String subtitle, String image, String content) {
        this.title = title;
        this.subtitle = subtitle;
        this.Image = image;
        this.Content = content;
    }

    public Model(String title, String subtitle, String image, String content, String author) {
        this.title = title;
        this.subtitle = subtitle;
        this.Image = image;
        this.Content = content;
        this.author = author;
    }
}
