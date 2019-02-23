package ObjectsToSend;

import java.io.Serializable;

public class Square extends Shape implements Serializable {
    private int height;
    private int width;

    public Square(int x, int y, int height, int width) {
        super(x, y);
        setHeight(height);
        setWidth(width);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
