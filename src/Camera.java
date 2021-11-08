import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import java.lang.Math.*;

public class Camera {
    private int X;
    private int Y;

    private double k=1;
    private double m=70;
    private double f=10;

    private double a_x;
    private double a_y;

    private double v_x;
    private double v_y;

    public Camera (Integer X, Integer Y) {
        this.X=X;
        this.Y=Y;
        }

    public void update(long time, Hero hero ) throws InterruptedException {
        double xhero= hero.getX();
        double yhero= hero.getY();

        double k_m =k/m;
        double f_m =f/m;

        a_x = k_m*(xhero - X) + (f_m * v_x);
        v_x+=a_x;
        X+=v_x;

        a_y = (k_m * (yhero - Y)) + (f_m * v_y);
        v_y+=a_y;
        Y+=v_y;

    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }

    @Override
    public String toString() {
        return X+","+Y;
    }
}
