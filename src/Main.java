// add any usefull package line
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application{
    public GameScene s;
    public Group root;


    public void start(Stage primaryStage) {
        int Xmin=50, Ymin=150, height=250, width=600;

        root = new Group();
        s = new GameScene(root, width, height, Xmin, Ymin);

        primaryStage.setTitle("Runner");
        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, width, height);
        ImageView backGround = new ImageView(new Image("C:\\Users\\brian\\OneDrive\\Documents\\java\\pictureRunner\\desert.png"));
        secondaryLayout.getChildren().add(backGround);
        primaryStage.setScene(secondScene);
        primaryStage.show();

        Button buttonStart = new Button("START");
        secondaryLayout.getChildren().add(buttonStart);
        Button buttonRestart = new Button("RESTART");

        buttonStart.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(s);
            primaryStage.show();

            root.getChildren().add(buttonRestart);

            buttonRestart.setLayoutY(200);
            System.out.println(secondaryLayout.getChildren());
        });

        buttonRestart.setOnAction((ActionEvent event) -> {
            s.timer.stop();
            root = new Group();
            s = new GameScene(root, width, height, Xmin, Ymin);
            root.getChildren().add(buttonRestart);
            buttonRestart.setLayoutY(200);

            primaryStage.setScene(s);
            primaryStage.show();
        });
    }



    public static void main(String[] args) {
        launch(args);
           }
}
