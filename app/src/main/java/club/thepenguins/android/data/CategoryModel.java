package club.thepenguins.android.data;

public class CategoryModel {

    private String catName;
    private int id;


    public CategoryModel(String catName, int id) {
        this.catName = catName;
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
