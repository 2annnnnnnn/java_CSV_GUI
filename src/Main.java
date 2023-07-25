import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Main extends Application{


    private static Scene1con scene1;

    public void start(Stage primaryStage) throws IOException{

        URL picker_scene_location = Main.class.getResource("Scene1.fxml");
        FXMLLoader loader = new FXMLLoader(picker_scene_location);
        Parent root = loader.load();
        Scene picker_scene = new Scene(root);
        this.scene1 = loader.getController();


        primaryStage.setScene(picker_scene);
        primaryStage.show();
    }

    


    

}
