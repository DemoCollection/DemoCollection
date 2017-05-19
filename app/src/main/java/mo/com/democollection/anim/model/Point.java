package mo.com.democollection.anim.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2017/2/15.
 */

//坐标点位置
public class Point implements Parcelable {

    public int x;
    public int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected Point(Point src) {
        this.x = src.x;
        this.y = src.y;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final void newgate() {
        x = -x;
        y = -y;
    }

    public final void offset(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        android.graphics.Point point = (android.graphics.Point) o;
        if (x != point.x) return false;
        if (y != point.y) return false;
        return true;
    }

    public final boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
            Point r = new Point();
            r.readFromParcel(in);
            return r;
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }
    };
    public void readFromParcel(Parcel in) {
        x = in.readInt();
        y = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(x);
        out.writeInt(y);
    }
}
