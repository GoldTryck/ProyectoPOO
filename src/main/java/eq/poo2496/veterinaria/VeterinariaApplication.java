package eq.poo2496.veterinaria;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class VeterinariaApplication extends Application {

	public static void main(String[] args) {
		//SpringApplication.run(VeterinariaApplication.class, args);
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Stage stage1 = stage;
		stage1.show();
		stage1.setTitle("Veterinaria");
	}
}
