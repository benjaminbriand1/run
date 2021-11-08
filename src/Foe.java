
import javafx.geometry.Point2D;


public class Foe extends AnimatedThing{
    private int x;
    private int y;
    private int dx=0;
    private int index=0;

    public void setX(int x) {
        this.x = x;

    }

    public Foe(int x, int y){
        super("C:\\Users\\brian\\OneDrive\\Documents\\java\\ennemypierrebonsens-removebg-preview.png", 0,230, 11,new Point2D(50,50));
        super.getSpreetSheet().setX(x);
        super.getSpreetSheet().setY(y);
        this.x=x;
        this.y=y;
    }


    @Override
    public double getX() {
        return x;
    }

}

