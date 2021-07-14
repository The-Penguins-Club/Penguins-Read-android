package club.thepenguins.android.data;

public class CommentModel {

    private String image, author, time, content;

    public CommentModel(String image, String author, String time, String content) {

        this.image = image;
        this.author = author;
        this.time = time;
        this.content = content;
    }

    public CommentModel(String author, String time, String content) {
        this.author = author;
        this.time = time;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
