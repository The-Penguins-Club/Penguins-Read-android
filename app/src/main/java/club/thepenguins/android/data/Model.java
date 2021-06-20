package club.thepenguins.android.data;

public class Model {
    public String title, subtitle, Image, Content;

    public Model(String mtitle, String msubtitle, String image) {

        this.title = mtitle;
        this.subtitle = msubtitle;
        this.Image = image;
    }

    public Model(String title, String subtitle, String image, String content) {
        this.title = title;
        this.subtitle = subtitle;
        this.Image = image;
        this.Content = content;
    }
}
