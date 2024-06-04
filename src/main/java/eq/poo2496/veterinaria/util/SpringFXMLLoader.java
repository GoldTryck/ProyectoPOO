package eq.poo2496.veterinaria.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import static eq.poo2496.veterinaria.Main.context;

public class SpringFXMLLoader {
    public void loadView(String filePath, Pane child) throws IOException{
        URL url = getClass().getResource(filePath);

        FXMLLoader loaded = new FXMLLoader(url);
        child.getChildren().clear();
        loaded.setControllerFactory(context::getBean);
        child.getChildren().add(loaded.load());
    }
}

