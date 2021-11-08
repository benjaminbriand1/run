import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;


import static java.lang.Math.*;

public class GameScene extends Scene {
    private int Xmin;
    private int Ymin;
    private int H,W;
    public Camera camera;

    private int numberOfLifes;
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
    private int numberFoe=10;
    private int maxFoeX;
    private ImageView[] life;
    private int numberOfPoints=0;
    private StartGame startGame;
    private TextField text;
    public AnimationTimer timer;
    public int numberEnnemy=0;
    public Stage finalWindow = new Stage();
    public StackPane secondaryLayout = new StackPane();


    public GameScene(Group parent, int width, int height, int Xmin, int Ymin) {
        super(parent, width, height);
        this.Ymin=Ymin;
        this.H=height;
        this.W=width;
        this.parent=parent;

        this.backGround = background.getImageView();
        //background.getImageView().setX(Xmin);
        //background.getImageView().setY(-Ymin);
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
        hero.getSpreetSheet().setY(250);//sert a placer le hero en y
        parent.getChildren().add(hero.getSpreetSheet());
        this.numberOfLifes= hero.numberOfLifes;

        foe = new ArrayList<>();

        for (int i=0; i<numberFoe; i++) {
            int v = (int) ((width + Xmin) * (i+1) + random() * 1000);
            if(i>0){
                System.out.println(foe.get(i-1).getX()+";"+v);
                if(v-foe.get(i-1).getX()<400){
                    v = (int) (foe.get(i-1).getX()+400);
                }

            }
            Foe enemy = new Foe( v, 350 - Ymin);
            System.out.println(enemy.getX());
            foe.add(enemy);
            parent.getChildren().add(enemy.getSpreetSheet());
        }

        this.maxFoeX= (int) foe.get(numberFoe-1).getX();
        System.out.println(maxFoeX);

        timer = new AnimationTimer() {
            long past;
            public void handle ( long now){
                double time=(now-past)*pow(10,-9);
                try {
                    hero.update(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    camera.update(time, hero);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    update(time);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                past=now;
            }
        };

        this.setOnMouseClicked( (event)-> {
            hero.jump();
        });

        this.life = new ImageView[numberOfLifes];
        startGame.button1.setOnAction((ActionEvent event) -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
                });
    }


    public void update(double time) throws InterruptedException {
        this.Xmin +=10;
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
        if(!foe.isEmpty()) {
            for (Foe enemy : foe) {
                int x = (int) enemy.getSpreetSheet().getX() - 10;
                enemy.getSpreetSheet().setX(x);
                //System.out.println(enemy.getSpreetSheet().getX());
                enemy.hitbox = new Rectangle2D(x, enemy.getSpreetSheet().getY(), 50, 50);
                //System.out.println("ennemy : "+ennemy.hitbox);
                if (hero.isInvicible() == false) {
                    if (collision(hero.hitbox, enemy.hitbox)) {
                        hero.setInvincibility(1750);
                        hero.isInvicible();
                        hero.touchHero();
                        parent.getChildren().remove(enemy.getSpreetSheet());
                        numberOfPoints -= 50;
                    }
                }
            }

        }


        this.numberOfLifes= hero.numberOfLifes;
        if (numberOfLifes!=life.length){
            parent.getChildren().remove(life[numberOfLifes]);
        }
        if(numberOfLifes==0 || 10*numberOfPoints>maxFoeX+500) {
            timer.stop();
            Label secondLabel = new Label("");

            if(numberOfLifes==0) secondLabel.setText( "YOU LOSE !!!\n"+"You have "+numberOfPoints+" points !");
            if(10*numberOfPoints>maxFoeX+500) secondLabel.setText( "YOU WIN !!!\n"+"You have "+numberOfPoints+" points !");

            secondaryLayout.getChildren().add(secondLabel);
            finalWindow.setTitle("Finish");
            Scene secondScene = new Scene(secondaryLayout, 400, 200);
            finalWindow.setScene(secondScene);

            // Specifies the modality for new window.
            finalWindow.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            finalWindow.initOwner(super.getWindow());
            // Set position of second window, related to primary window.
            finalWindow.setX(Xmin+300);
            finalWindow.setY(Ymin+200);
            finalWindow.show();
        }
    }

    public boolean collision(Rectangle2D hitBox1, Rectangle2D hitBox2 ){
        return hitBox1.intersects(hitBox2);
    }
}

