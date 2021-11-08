import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static java.lang.Math.abs;

abstract class AnimatedThing {
    private double x;
    private double y;
    enum Attitude{RUN, STILL, JUMPING_UP, JUMPING_DOWN, DEAD}

    private ImageView spreetSheet;
    protected int index;
    protected int indexMax=6;
    private final int ground=150;


    protected Attitude attitude;
    protected Point2D sizeWindow;
    protected Rectangle2D hitbox;


    public AnimatedThing(String fileName, int x, int y, int indexMax, Point2D sizeWindow) {
        this.indexMax=indexMax;
        this.sizeWindow=sizeWindow;
        this.x=x;
        this.y=y;
        this.spreetSheet = new ImageView(new Image(fileName));
        this.spreetSheet.setViewport(new Rectangle2D(x, y, sizeWindow.getX(), sizeWindow.getY()));
        //this.hitbox=new Rectangle2D(x, y, sizeWindow.getX(), sizeWindow.getY());

    }


    /*public void update(double time) throws InterruptedException {

    }*/

    public ImageView getSpreetSheet() {
        return spreetSheet;
    }

    public double getY() {
        return y;
    }


    public double getX() {
        return x;
    }

    public Attitude getAttitude() {
        return attitude;
    }

    public int getIndex() {
        return index;
    }

    public int getIndexMax() {
        return indexMax;
    }
}