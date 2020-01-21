package pl.project.pk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.project.pk.database.dbutils.DbManager;
import pl.project.pk.utils.FxmlUtils;

import java.util.Locale;

public class Main extends Application {

    private static final String BORDER_PANE_MAIN_FXML = "/fxml/main/BorderPaneMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

//        Locale.setDefault(Locale.ENGLISH);
        Pane borderPane= FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);

        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();

        DbManager.initDatabase();

    }
}
