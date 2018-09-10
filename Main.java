import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Charles Ménard (les parties du modèle ont été faite pour le TP1
 * à l'ide de Tanahel Huot-Roberge)
 */


public class Main extends Application  {



    public static void main(String[] args) {

        launch(args);
    }

    /**
     * Fonction qui gère ce qui se passe dans l'application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        View view = new View(primaryStage);

    }




}