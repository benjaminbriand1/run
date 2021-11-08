import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Foe extends AnimatedThing{
    private int x;
    private int y;
    private int dx=0;
    private int index=0;

    public Foe(int x, int y){
        super("C:\\Users\\brian\\OneDrive\\Documents\\java\\ennemypierrebonsens-removebg-preview.png", 0,230, 11,new Point2D(50,50));
        super.getSpreetSheet().setX(x);
        super.getSpreetSheet().setY(y);
        this.x=x;
        this.y=y;
    }

    public void update(long t) throws InterruptedException {

        super.update(t);
        dx+=20;
        super.getSpreetSheet().setX(x-dx);

        hitbox=new Rectangle2D(x-dx,super.getSpreetSheet().getY(),sizeWindow.getX(), sizeWindow.getY());


    }

    @Override
    public double getX() {
        return x;
    }
}
