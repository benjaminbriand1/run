import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing{
    private double x;
    private double y;
    private double a_x, a_y;
    private double v_x, v_y;
    private double f_x, f_y;
    private final int ground=150;
    private double invincibility;
    protected int numberOfLifes;

    private double g = 1.5;
    private double m = 10;


    public Hero(String fileName, int x, int y, int indexMax, int Xmin, int Ymin) {
        super(fileName, x,y, indexMax,new Point2D(75,100));
        this.numberOfLifes=3;
        this.x=Xmin;
        this.y=Ymin;
            }

    public void jump() {
        if (y >= ground+sizeWindow.getY()){
            f_y+=125;
        }

    }

    @Override
    public void update(long time) throws InterruptedException {
        super.update(time);
        updateAttitude();
        a_y = ( g- f_y/m);
        v_y+=2*a_y;
        y+=v_y;

        if (y > ground + sizeWindow.getY()) {
            if (v_y > 0) {
                v_y = 0;
            }
            y = ground +sizeWindow.getY();
        }

        hitbox=new Rectangle2D(this.x,super.getSpreetSheet().getY(),sizeWindow.getX(), sizeWindow.getY());
        a_x=f_x/m;
        v_x+=a_x;
        x += v_x;

        super.getSpreetSheet().setY(y);

        f_y=0;

    }

    public void updateAttitude(){
        if (v_y<=-0.1){
            attitude = Attitude.JUMPING_UP;
        }
        else if (v_y>=0.1){
            attitude = Attitude.JUMPING_DOWN;
        }
        else {
            attitude = Attitude.RUN;
        }

        if (numberOfLifes==0){
            attitude = Attitude.DEAD;
        }

        /*if (v_x==0){
            attitude = Attitude.STILL;
        }*/
    }

    public double getInvincibility() {
        return invincibility;
    }

    public void setInvincibility(double invincibility) {
        this.invincibility = invincibility;
    }

    public boolean isInvicible() {
        if(invincibility>0) {
            invincibility--;
        }
        if(invincibility==0){
            return false;
        }
        else {
            return true;
        }

    }

    public void touchHero(){
        numberOfLifes--;
        if(numberOfLifes==0){
            updateAttitude();
        }
    }

}
