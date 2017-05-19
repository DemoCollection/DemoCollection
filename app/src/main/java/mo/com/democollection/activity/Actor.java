package mo.com.democollection.activity;

import android.content.Context;

/**
 * Created by admin on 2017/3/1.
 */
public class Actor
{
    String name;

    String picName;

    String works;

    String role;

    String[] pics;

    public Actor(String name, String picName, String works, String role, String[] pics)
    {
        this.name = name;
        this.picName = picName;
        this.works = works;
        this.role = role;
        this.pics = pics;
    }

    public int getImageResourceId( Context context )
    {
        try
        {
            return context.getResources().getIdentifier(this.picName, "drawable", context.getPackageName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }


    public int getImageResourceId(Context context, String picName)
    {
        try
        {
            return context.getResources().getIdentifier(picName, "drawable", context.getPackageName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }

}
