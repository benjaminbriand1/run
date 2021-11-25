import javafx.scene.image.ImageView;


public class Camera {
    private double X;
    private double Y;
    private int width;
    private int height;
    private int Ymin;
    private ImageView backGround;
    private ImageView Left;
    private ImageView Right;


    public Camera(Integer X, Integer Y, int width, int height, ImageView backGround, ImageView Left, ImageView Right) {
        this.X=X;
        this.Y=Y;
        this.Ymin=Y;
        this.width=width;
        this.height=height;
        this.backGround=backGround;
        this.Left=Left;
        this.Right=Right;
        }

    public void update(double time, Hero hero ) throws InterruptedException {
        double yhero= hero.getY();
        Y=-yhero;

        backGround.setY(Y);
        Left.setY(Y);
        Right.setY(Y);


    }

    @Override
    public String toString() {
        return X+","+Y;
    }

}

