import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class staticThing {
    private double x;
    private double y;
    private double height;
    private double width;
    private ImageView imageView;

    public staticThing(String fileName){
            Image image = new Image(fileName);
            this.height=image.getHeight();
            this.width=image.getWidth();
            this.imageView=new ImageView(image);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
