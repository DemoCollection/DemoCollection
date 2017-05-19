package mo.com.democollection.model;

/**
 * Created by admin on 2017/3/1.
 */

public class Item {
    public Item(int pic_id, String title, String content) {
        this.pic_id = pic_id;
        this.title = title;
        Content = content;
    }
    public int pic_id;
    public String title;
    public String Content;
}
