import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Cette classe contient les propriétés et méthodes qui ont trait à la communication entre le modèle, par le
 * biais de la classe grille, et la vue.
 */


public class Controller {
    private View view;
    private Stage stage;
    private Scene scene;

    private Robot robot = null;
    private Grille grille = null;
    private String robotStatus;

    private boolean kittenFound = false;

    /**
     * Constructeur du modèle. C'est ici qu'on initialise notre modèle, en construisant une grille
     * et un robot.
     * @param view, on passe la vue en paramètre afin de pouvoir appeler des méthodes qui vont
     *              modifier l'affichage.
     */
    public Controller(View view){
        this.view = view;
        stage = view.stage;
        scene = this.stage.getScene();
        grille = new Grille(3, 2, 6, 4, 20);
        Point nouvellePosition = grille.randomEmptyCell();
        robot = new Robot(nouvellePosition.getX(), nouvellePosition.getY());
        this.updateAll();

    }

    /**
     * Méthode attendant des évenements, dans ce cas des touches pressées, pour accomplir des actions.
     */
    public void listen() {

        scene.setOnKeyPressed(key -> {
            KeyCode k = key.getCode();
            if(kittenFound){
                view.attendrissantKitten();
            }

            if (k.equals(KeyCode.ESCAPE)) {
                System.exit(0);

            } else if (k.equals(KeyCode.F5)) {
                view.changeScreenSize();

            }else if (k.equals(KeyCode.W) || k.equals(KeyCode.UP)){
                this.up();
            }
            else if (k.equals(KeyCode.S) || k.equals(KeyCode.DOWN)){
                this.down();
            }
            else if (k.equals(KeyCode.A)|| k.equals(KeyCode.LEFT)){
                this.left();
            }
            else if (k.equals(KeyCode.D)|| k.equals(KeyCode.RIGHT)){
                this.right();
            }
            else if (k.equals(KeyCode.T)){
                this.teleporter();
            }
            else if (k.equals(KeyCode.SPACE)){
                this.view.newGame();

            }
            this.updateAll();
        });
    }

    /**
     * Méthode appelé pour mettre à jour la grille, et afficher les changements dans la vue
     */
    private void updateAll(){

        String newMessage = grille.interagir(robot);
        this.updateMessage(newMessage);

        this.updateRobotStatus();

        view.changeImageViews(this.getGrilleStatus());

        if(robot.isGameWon()){
            this.view.attendrissantKitten();
        }


    }
    /**
     * Deplace le robot a une case vide aleatoire.
     *
     */
    private void teleporter() {
        if (robot.hasTeleporteur()) {
            Point nouvellePosition = grille.randomEmptyCell();
            robot.setPosition(nouvellePosition.getX(), nouvellePosition.getY());
        }

    }

    /**
     * Deplace le robot d'une case a droite si le deplacement est possible.
     *
     */
    private void right() {
        Point p = robot.getPosition();
        if (grille.deplacementPossible(robot, p.getX() + 1, p.getY())) {
            robot.setPosition(p.getX() + 1, p.getY());
        }
    }

    /**
     * Deplace le robot d'une case a gauche si le deplacement est possible.
     *
     */
    private void left() {
        Point p = robot.getPosition();
        if (grille.deplacementPossible(robot, p.getX() - 1, p.getY())) {
            robot.setPosition(p.getX() - 1, p.getY());
        }
    }

    /**
     * Deplace le robot d'une case en bas si le deplacement est possible.
     *
     */
    private void down() {
        Point p = robot.getPosition();
        if (grille.deplacementPossible(robot, p.getX(), p.getY() + 1)) {
            robot.setPosition(p.getX(), p.getY() + 1);
        }
    }

    /**
     * Deplace le robot d'une case en haut si le deplacement est possible.
     *
     */
    private void up() {
        Point p = robot.getPosition();
        if (grille.deplacementPossible(robot, p.getX(), p.getY() - 1)) {
            robot.setPosition(p.getX(), p.getY() - 1);
        }
    }

    /**
     * envoie le statut courant du robot à la vue
     */
    public void updateRobotStatus(){
        String s = "";
        s += this.robot.getNom();
        s += " [" + this.robot.getNbrCle() + "]";
        if(this.robot.hasTeleporteur()){
            s += "T";
        }
        this.view.printRobotStatus(s);
    }

    public boolean isKittenFound() {
        return kittenFound;
    }


    /**
     *
     * @return la matrice des noms de fichiers d'images
     */
    public String[][] getGrilleStatus(){

        return grille.afficher(this.robot);

    }


    /**
     * envoie la description des NKI à afficher à la vue
     * @param s le message à envoyer à la vue
     */
    public void updateMessage(String s){
        if(s != "") view.setMessage(s);
    }
}
