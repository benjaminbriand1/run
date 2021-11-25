import javafx.geometry.Point2D;

public class Foe extends AnimatedThing{
    private int x;
    private int y;
    private int dx=0;
    private int index=0;

    public Foe(int x, int y){
        super("C:\\Users\\brian\\OneDrive\\Documents\\java\\pictureRunner\\enemy.png", 30,30, 11,new Point2D(100,70));
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

