import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

import static java.lang.Math.abs;

public class Hero extends AnimatedThing{
    private double x;
    private double y;
    private double a_x, a_y;
    private double v_x, v_y;
    private double f_x, f_y;
    private final int ground=150;
    private double invincibility;
    protected int numberOfLifes;
    private int duration=0;

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
            f_y+=250;
        }

    }

    //@Override
    public void update(double time) throws InterruptedException {
        if (time>1) time=0;
        //Thread.sleep(15);
        duration++;
        //System.out.println(duration);
        if (duration==8) {
            index++;

            duration=0;
            if (index > indexMax) {
                index = 1;
            }
            //System.out.println(index);
            if (index == 1) {
                super.getSpreetSheet().setViewport(new Rectangle2D(20, 0, 60, 100));
            }
            if (index == 2) {
                super.getSpreetSheet().setViewport(new Rectangle2D(95, 0, 70, 100));
            }
            if (index == 3) {
                super.getSpreetSheet().setViewport(new Rectangle2D(170, 10, 85, 90));
            }
            if (index == 4) {
                super.getSpreetSheet().setViewport(new Rectangle2D(270, 10, 65, 90));
            }
            if (index == 5) {
                super.getSpreetSheet().setViewport(new Rectangle2D(345, 0, 70, 100));
            }
            if (index == 6) {
                super.getSpreetSheet().setViewport(new Rectangle2D(95, 0, 70, 100));
            }
        }

        if (attitude==Attitude.JUMPING_DOWN) {
            super.getSpreetSheet().setViewport(new Rectangle2D(95,160,70,105));
        }

        if (attitude==Attitude.JUMPING_UP) {
            super.getSpreetSheet().setViewport(new Rectangle2D(20,160,60,105));
        }

        if (attitude==Attitude.STILL){
            super.getSpreetSheet().setViewport(new Rectangle2D(20, 0, 60, 100));
        }

        if (attitude==Attitude.DEAD){
            //
        }
        updateAttitude();
        a_y = ( g- f_y/m);
        v_y+=a_y;
        y+=v_y;

        if (y > ground + sizeWindow.getY()) {
            if (v_y > 0) {
                v_y = 0;
            }
            y = ground +sizeWindow.getY();
        }

        hitbox=new Rectangle2D(this.x,super.getSpreetSheet().getY(),sizeWindow.getX(), sizeWindow.getY());
        //System.out.println(hitbox);
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
