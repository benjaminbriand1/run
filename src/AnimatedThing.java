import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class AnimatedThing {
    private double x;
    private double y;
    enum Attitude{RUN, STILL, JUMPING_UP, JUMPING_DOWN, DEAD}

    private ImageView spreetSheet;
    private int index;
    private int indexMax;
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


    public void update(long t) throws InterruptedException {
        //index=(int) ((t%(indexMax*1000))/1000);
        Thread.sleep(30);

        if (attitude==Attitude.RUN) {
            if (index >= indexMax) {
                index = 0;
            }
            index++;
            if (index == 1) {
                spreetSheet.setViewport(new Rectangle2D(20, 0, 60, 100));
            }
            if (index == 2) {
                spreetSheet.setViewport(new Rectangle2D(95, 0, 70, 100));
            }
            if (index == 3) {
                spreetSheet.setViewport(new Rectangle2D(170, 10, 85, 90));
            }
            if (index == 4) {
                spreetSheet.setViewport(new Rectangle2D(270, 10, 65, 90));
            }
            if (index == 5) {
                spreetSheet.setViewport(new Rectangle2D(345, 0, 70, 100));
            }
            if (index == 6) {
                spreetSheet.setViewport(new Rectangle2D(95, 0, 70, 100));
            }
        }

        if (attitude==Attitude.JUMPING_DOWN) {
            spreetSheet.setViewport(new Rectangle2D(95,160,70,105));
        }

        if (attitude==Attitude.JUMPING_UP) {
            spreetSheet.setViewport(new Rectangle2D(20,160,60,105));
        }

        if (attitude==Attitude.STILL){
            spreetSheet.setViewport(new Rectangle2D(20, 0, 60, 100));
        }

        if (attitude==Attitude.DEAD){
            //
        }
    }

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
}