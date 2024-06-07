package eq.poo2496.veterinaria.util;

import eq.poo2496.veterinaria.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;

public class SpringFXMLLoader extends Main {
    public static void loadView(String filePath, Pane child) throws IOException{
        URL url = SpringFXMLLoader.class.getResource(filePath);

        FXMLLoader toLoad = new FXMLLoader(url);
        toLoad.setControllerFactory(context::getBean);
        Parent view = toLoad.load();
        child.getChildren().clear();
        child.getChildren().add(view);
    }
}

