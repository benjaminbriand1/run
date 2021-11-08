import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class StartGame {
    public Button button1;



    private int start;

    public StartGame(Group w, int width, int height, int Xmin, int Ymin) {
        this.button1= new Button("START GAME");
        //w.getChildren().add(this.button1);
        button1.setLayoutX(width/2-Xmin);
        button1.setLayoutY(height/2-Ymin);
        //button1.setMaxSize(300,200);

}

    public boolean startG(){
        button1.setOnAction(new EventHandler<ActionEvent>() {
            private int start;

            @Override
            public void handle(ActionEvent actionEvent) {
                this.start=1;
                System.out.println("start !"+ start);
            }
        });

        if (start==1){
            System.out.println("true");
            return true;

        }
        else {
            return false;
        }
    }
}
