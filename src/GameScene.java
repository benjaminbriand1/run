import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import static java.lang.Math.random;

public class GameScene extends Scene {
    private int Xmin;
    private int Ymin;
    private int H,W;
    public Camera camera;
    public int numberOfLifes;
    private Hero hero;
    private Group parent;
    private staticThing background= new staticThing("C:\\Users\\brian\\OneDrive\\Documents\\java\\desert.png");
    private staticThing left = new staticThing("C:\\Users\\brian\\OneDrive\\Documents\\java\\desert.png");
    private staticThing right = new staticThing("C:\\Users\\brian\\OneDrive\\Documents\\java\\desert.png");
    private staticThing heart = new staticThing("C:\\Users\\brian\\OneDrive\\Documents\\java\\heart.png");
    private ImageView backGround;
    private ImageView Left;
    private ImageView Right;
    private ArrayList<Foe> foe;
    private int numberFoe=5;
    private ImageView[] life;
    private int numberOfPoints=0;
    private StartGame startGame;
    private TextField text;
    public AnimationTimer timer;


    public GameScene(Group parent, int width, int height, int Xmin, int Ymin) {
        super(parent, width, height);
        this.Ymin=Ymin;
        this.H=height;
        this.W=width;
        this.parent=parent;

        this.backGround = background.getImageView();
        this.Left = left.getImageView();
        this.Right = right.getImageView();
        this.text = new TextField();
        this.startGame = new StartGame(parent, width, height, Xmin, Ymin);

        double getw= background.getWidth();
        double geth= background.getHeight();

        Right.setX(getw);
        Left.setX(-getw);
        parent.getChildren().addAll( backGround, Right, Left);
        parent.getChildren().add(startGame.button1);
        camera = new Camera(Xmin,Ymin);

        hero = new Hero("C:\\Users\\brian\\OneDrive\\Documents\\java\\heros.png", 0,0, 6,Xmin+100, 300-Ymin);
        hero.getSpreetSheet().setX(Xmin+100); //sert a placer le hero en x
        hero.getSpreetSheet().setY(400-Ymin);//sert a placer le hero en y
        parent.getChildren().add(hero.getSpreetSheet());
        this.numberOfLifes= hero.numberOfLifes;

        foe = new ArrayList<>();

        for (int i=1; i<=numberFoe; i++) {
            Foe ennemy = new Foe((int) ((width+ Xmin)*i+Math.random()*1000), 350 - Ymin);
            System.out.println(ennemy.getX());
            foe.add(ennemy);
            parent.getChildren().add(ennemy.getSpreetSheet());
        }

        this.timer = new AnimationTimer() {
            public void handle ( long time){
                long t = (long) (time / 1000000000.0);
                try {
                    hero.update(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    camera.update(t, hero);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    update(t);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Foe o : foe) {
                    try {
                        o.update(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        };

        this.setOnMouseClicked( (event)-> {
            System.out.println("Jump");
            hero.jump();
        });
        this.life = new ImageView[numberOfLifes];
        startGame.button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        timer.start();
                        parent.getChildren().remove(startGame.button1);

                        text.setText("Points : "+numberOfPoints);
                        text.setEditable(false);
                        parent.getChildren().add(text);

                        heart.getImageView().setViewport(new Rectangle2D(120,150,100,50));

                        for (int i=0;i<numberOfLifes;i++){
                            heart=new staticThing("C:\\Users\\brian\\OneDrive\\Documents\\java\\heart.png");
                            heart.getImageView().setViewport(new Rectangle2D(120,150,100,50));
                            life[i]=heart.getImageView();
                            parent.getChildren().add(life[i]);
                            life[i].setX(width-55*(1+i));
                        }
                    }
                });

        timer.handle(1000);




    }


    public void update(long t) throws InterruptedException {
        //Thread.sleep(50);
        this.Xmin += 25;
        this.numberOfPoints++;
        text.setText("Points : "+numberOfPoints);
        if (Xmin > 800) {
            Xmin = 0;
        }
        this.Right.setX(W - Xmin);
        this.backGround.setX(-Xmin);
        this.Left.setX(2 * W - Xmin);

       // hero.getSpreetSheet().setX(hero.getX() - camera.getX());
      //  hero.getImageView().setY(hero.getY() - camera.getY());
        for (Foe ennemy:foe) {
            if (hero.isInvicible()==false) {
                if (collision(hero.hitbox, ennemy.hitbox)) {
                    hero.setInvincibility(25);
                    hero.isInvicible();
                    hero.touchHero();
                    ennemy.getSpreetSheet().setY(1000);
                    numberOfPoints-=50;
                }
            }
        }

        this.numberOfLifes= hero.numberOfLifes;
        if (numberOfLifes!=life.length){
            parent.getChildren().remove(life[numberOfLifes]);
        }

        if(numberOfLifes==0){
            timer.stop();
        }

    }

    public boolean collision(Rectangle2D hitBox1, Rectangle2D hitBox2 ){
        return hitBox1.intersects(hitBox2);
    }


}
