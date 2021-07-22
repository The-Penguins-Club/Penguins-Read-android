package club.thepenguins.android.data;

public class CommentModel {

    private String image, author, time, content, parent, child;

    public CommentModel(String image, String author, String time, String content, String parent, String child) {
        this.image = image;
        this.author = author;
        this.time = time;
        this.content = content;
        this.parent = parent;
        this.child = child;
    }

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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }
}
