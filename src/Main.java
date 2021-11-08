// add any usefull package line
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.stage.Stage;


public class Main extends Application{

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Runner");
        Group root = new Group();
        GameScene s = new GameScene(root, 800, 400, 50, 50);


        primaryStage.setScene(s);
        primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
           }
}
