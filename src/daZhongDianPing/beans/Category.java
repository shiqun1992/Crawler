package daZhongDianPing.beans;

/**
 * Created by shiqun on 2015/11/27.
 */
public class Category {

    int categoryID;
    String categoryName;
    String URL;

    public Category(int ID,String categoryName,String URL)
    {
        this.categoryID = ID;
        this.categoryName =categoryName;
        this.URL= URL;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getURL() {
        return URL;
    }
}
