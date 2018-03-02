package namato.icndb.pojo;

public class JokeValue {

    private String id;
    private String joke;
    private String[] categories;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getJoke() { return joke; }

    public void setJoke(String joke) { this.joke = joke; }

    public String[] getCategories() { return categories; }

    public void setCategories(String[] categories) { this.categories = categories; }

    public int getCategoriesCount() { return categories.length; }

    public String getCategory(int i) { return categories[i]; }
}