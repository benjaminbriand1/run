import javafx.scene.Group;
import javafx.scene.control.Button;

public class StartGame {
    public Button button1;

    public StartGame(Group w, int width, int height, int Xmin, int Ymin) {
        this.button1= new Button("START GAME");
        button1.setLayoutX((width / 2) - Xmin);
        button1.setLayoutY(height-Ymin);
    }
}
