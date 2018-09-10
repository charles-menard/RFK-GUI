import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.Hashtable;

public class View {
    public Stage stage;
    private String firstRobotStatus = "R.O.B. []";
    private Text robotStatus;
    private VBox root;
    private Scene scene;
    private String firstMessage = "Bienvenue dans RobotFindsKitten : Super Dungeon Master 3000 Ultra Turbo Edition !";
    private Text message;
    private ImageView[][] imageViewsMatrix;

    private Hashtable<String, Image> hash = new Hashtable<>();
    private final int width = 22;
    private final int height = 11;
    private final String wall = "skins/wall.png";
    private GridPane grid;
    private MediaPlayer mediaPlayer;
    private final String trolol = "Trololo Sing Along!__AAC_128k.m4a"; //replace by your music
    private final String pesteNoire = "02 - Chute Pour Une Culbute.mp3"; //replace by your music
    private final String victory = "You found kitten! Way to go, robot. Jimmy Whooper <3 R.O.B.";
    private final String splash = "skins/found-kitten.png";
    private Controller controller;

    public View(Stage stage) {
        this.stage = stage;

        this.newGame();

        this.stage.show();
    }

    /**
     * Affiche dans la fenêtre le param status
     * @param status le statut du robot qui sera affiché dans la fenêtre
     */
    public void printRobotStatus(String status) {
        robotStatus.setText(status);

    }

    /**
     * Initialise une grid pane de la bonne taille avec une image de départ.
     * @param urlOfFirstImage chemin du fichier de l'image
     * @param width largeur de la grille
     * @param height hauteur de la grille
     */
    public void initializeGridPane(String urlOfFirstImage, int width, int height) {

        grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        root.setVgrow(grid, Priority.ALWAYS);
        Image imgHappy = new Image(urlOfFirstImage);

        for (int i = 0; i < this.width; i++) {

            for (int j = 0; j < this.height; j++) {

                ImageView iv = new ImageView();

                imageViewsMatrix[i][j] = iv;

                iv.setImage(imgHappy);
                iv.setFitWidth(30.0);
                iv.setFitHeight(30.0);
                iv.setPreserveRatio(true);

                grid.add(iv, i, j);
            }
        }
        grid.setPadding(new Insets(20));

        root.getChildren().add(grid);

    }

    /**
     *Met le stage en plein écran
     */
    public void changeScreenSize(){

        if (!stage.isMaximized()){
            stage.setMaximized(true);
        }
        else{
            stage.setMaximized(false);
        }
    }
    /**
     *  * on passe dans  matrixOfFileName, et si l'image correspondant au filename
     * est présente dans la hashtable hash, on la prend, sinon on crée une image
     *                         et on change l'imageview dans la matrice d'affichage
     * @see #hash
     * @param matrixOfFileName Matrice contenant les noms des fichiers
     *                         avec lesquels remplacer les images courantes
     *


     */
    public void changeImageViews(String[][] matrixOfFileName){

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                String filename = matrixOfFileName[i][j];

                Image tryGetIV = hash.get(filename);
                if(tryGetIV != null){

                    imageViewsMatrix[i][j].setImage(tryGetIV);
                } else {
                    Image img = new Image(filename);

                    hash.put(filename, img);
                    imageViewsMatrix[i][j].setImage(img);
                }
            }
        }
    }

    /**
     * Change la scène pour mettre le chaton attendrissant, change aussi la musique
     * et attend pour commencer une autre partie
     */
    public void attendrissantKitten() {

        StackPane splashScreenRoot = new StackPane();
        Scene splashScreenScene = new Scene(splashScreenRoot);
        Image splashImg = new Image(splash);
        ImageView splashViewer = new ImageView(splashImg);
        splashScreenRoot.getChildren().add(splashViewer);

        Text victoryMsg = new Text(victory);
        victoryMsg.setFill(Color.RED);
        splashScreenRoot.getChildren().add(victoryMsg);
        splashScreenRoot.setAlignment(Pos.BOTTOM_CENTER);

        stage.setScene(splashScreenScene);
        stage.setTitle("YOURE WINNER!!!!!");
        stage.resizableProperty().setValue(Boolean.FALSE);
        changeMusic(pesteNoire);

        splashScreenScene.setOnKeyPressed(key -> {
            if (key.getCode().equals(KeyCode.SPACE)) {
                stage.resizableProperty().setValue(Boolean.TRUE);

                this.newGame();

            }
        });
    }

    /**
     * Change la musique par le fichier spécifié par path
     * @param path chemin du fichier audio
     */
    public void changeMusic(String path){
        //ajouter la musique
        if(this.mediaPlayer != null) this.mediaPlayer.stop();

        Media sound = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * Affiche le paramètre s dans la scène, dans une composante Text
     * @param s la string à afficher
     */
    public void setMessage(String s){
        this.message.setText(s);
    }

    /**
     * Initilialise les composante de la vue et mets une musique d'ambiance.
     */
    public void newGame(){

        root = new VBox();
        scene = new Scene(this.root, Color.color(Math.random(), Math.random(), Math.random()));
        root.setFillWidth(true);
        root.setAlignment(Pos.CENTER);
        message = new Text(firstMessage);
        message.setFill(Color.WHITESMOKE);

        robotStatus = new Text(firstRobotStatus);
        robotStatus.setFill(Color.WHITESMOKE);
        root.getChildren().add(message);

        imageViewsMatrix = new ImageView[width][height];
        initializeGridPane(wall, width, height);
        root.getChildren().add(robotStatus);

        stage.setTitle("RobotFindsKitten");
        stage.setScene(scene);
        changeMusic(trolol);


        Controller controller = new Controller(this);
        controller.listen();

    }
}