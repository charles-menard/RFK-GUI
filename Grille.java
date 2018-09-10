import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe s'occupe de ce qui a trait a la grille de jeu. La grille est une
 * matrice de case, qui sont les items, portes ou murs du jeu. On garde
 * egalement la trace des cellules vides restantes.
 *
 * On trouve dans cette classe les methodes pour creer une grille, mettre les
 * items aux bons endroits et placer les murs et les portes. Contient egalement
 * la methode pour afficher la grille et pour retourner les deplacements
 * possibles d'un robot.
 *
 *
 */
public class Grille {
    private Case[][] grille = null;
    private ArrayList<Point> cellulesVides = null;
    private int width;
    private int height;

    // variable qui change et garde en memoire un int generer par newRandomInt
    private int randomInt = 0;

    /**
     * Constructeur de Grille qui creer une nouvelle grille d'abort vide et la
     * remplis les cases au hasard
     *
     * @param nbrPiecesX
     *            Nombre de pieces en x de notre grille
     * @param nbrPiecesY
     *            Nombre de pieces en y de notre grille
     * @param largeurPiece
     *            Largeur d'une piece excluant les murs qui l'entour
     * @param hauteurPiece
     *            Hauteur d'une piece excluant les murs qui l'entour
     * @param nbrNonKitten
     *            Nombre de NonKitten item a ajouter a la grille
     */
    public Grille(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece, int nbrNonKitten) {
        // Cree la grille et la liste de cellule vide
        this.height = nbrPiecesY + 1 + nbrPiecesY * hauteurPiece;
        this.width = nbrPiecesX + 1 + nbrPiecesX * largeurPiece;

        grille = new Case[width][height];
        cellulesVides = new ArrayList<>();

        // Ajoute les Objet dans la grille
        creerCles(nbrPiecesX, nbrPiecesY, largeurPiece, hauteurPiece);
        creerMursPortes(largeurPiece, hauteurPiece);
        creerNKI(nbrNonKitten);
        creerTeleporteur();
        creerKitten();
    }

    /**
     * Ajoute les nonKitten items a des cases vides au hasard et enleve cette case
     * des cellules vide
     *
     * @param nbrNonKitten
     *            nombre de nonKitten a ajouter
     */
    private void creerNKI(int nbrNonKitten) {
        for (int i = 0; i < nbrNonKitten; i++) {
            // Prend une case vide parmis la liste des casesVides et l'enleve de la liste en
            // sachant le l'indexe generer aleatoirement randomInt
            Point temp = randomEmptyCell();
            cellulesVides.remove(randomInt);

            int x = temp.getX();
            int y = temp.getY();
            grille[x][y] = new NonKitten(x, y, Case.getRandomSymbole());
        }
    }

    /**
     * Ajoute le Teleporteur a la grille aleatoirement et l'enleve le la liste de
     * case vde
     */
    private void creerTeleporteur() {
        // Prend une case vide parmis la liste des casesVides et l'enleve de la liste en
        // sachant le l'indexe generer aleatoirement randomInt
        Point temp = randomEmptyCell();
        cellulesVides.remove(randomInt);

        int x = temp.getX();
        int y = temp.getY();
        grille[x][y] = new Teleporteur(x, y);

    }

    /**
     * Ajoute le Kitten a la grille aleatoirement et l'enleve de la liste de case
     * vde
     */
    private void creerKitten() {
        // Prend une case vide parmis la liste des casesVides et l'enleve de la liste en
        // sachant le l'indexe generer aleatoirement randomInt
        Point temp = randomEmptyCell();
        cellulesVides.remove(randomInt);

        int x = temp.getX();
        int y = temp.getY();
        grille[x][y] = new Kitten(x, y, Case.getRandomSymbole());
    }

    /**
     * Creer une cle dans chaque prece de la grille aleatoirement
     *
     * @param nbrPiecesX
     *            Nombre de pieces en x de notre grille
     * @param nbrPiecesY
     *            Nombre de pieces en y de notre grille
     * @param largeurPiece
     *            Largeur d'une piece excluant les murs qui l'entour
     * @param hauteurPiece
     *            Hauteur d'une piece excluant les murs qui l'entour
     */
    private void creerCles(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece) {
        for (int i = 0; i < nbrPiecesX; i++) {
            for (int j = 0; j < nbrPiecesY; j++) {

                // Trouve un x et y aleatoire dans la piece ij
                newRandomInt(largeurPiece);
                int x = i * largeurPiece + i + 1 + randomInt;
                newRandomInt(hauteurPiece);
                int y = j * hauteurPiece + j + 1 + randomInt;

                grille[x][y] = new Cle(x, y);
            }
        }
    }

    /**
     * Donne une case aleatoire encore vide dans la grille
     *
     * @return cette case aleatoire
     */
    public Point randomEmptyCell() {
        newRandomInt(cellulesVides.size());
        return cellulesVides.get(randomInt);
    }

    /**
     * Fonction qui change le random Int pour un nouveau int entre 0 inclus et max
     * exclus
     *
     * @param max
     *            Borne superieur exclus du nouveau int random
     */
    private void newRandomInt(int max) {
        Random random = new Random();
        randomInt = random.nextInt(max);
    }

    /**
     * Fonction qui creer les murs et les ajoutes a la grille ET Ajoute les points
     * vide dans EmptyCells si c'est pas un mur
     *
     * @param largeurPiece
     *            Largeur d'une piece excluant les murs qui l'entour
     * @param hauteurPiece
     *            Hauteur d'une piece excluant les murs qui l'entour
     */
    private void creerMursPortes(int largeurPiece, int hauteurPiece) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {

                // Verifie s'il devrait avoir un mur dans la case (i,j) sinon on l'ajoute au
                // cellulesVide
                if (i % (largeurPiece + 1) == 0 || j % (hauteurPiece + 1) == 0) {

                    // Si c'est la position est celle d'une porte cree une porte sinon un mur
                    if (verifierPositionPorte(i, j, largeurPiece, hauteurPiece)) {
                        grille[i][j] = new Porte(i, j);
                    }

                    else {
                        grille[i][j] = new Mur(i, j);
                    }
                }

                else {
                    // Si la case n'est pas deja une cle, on l'ajoute a la iste des vides
                    if (grille[i][j] == null) {
                        cellulesVides.add(new Point(i, j));
                    }
                }

            }
        }
    }

    /**
     * V�rifie si la position donn� par (x,y) devrait �tre une porte.
     *
     * @param x
     *            position en x de la case � v�rifier
     *
     * @param y
     *            y position en x de la case � v�rifier
     *
     * @param largeurPiece
     *            la largeur des pi�ces de la grille
     *
     * @param hauteurPiece
     *            la hauteur des pi�ces de la grille
     */
    private boolean verifierPositionPorte(int x, int y, int largeurPiece, int hauteurPiece) {
        boolean reponse = false;
        if (x != 0 && x != grille.length - 1 && y != 0 && y != grille[x].length - 1) {
            if (y % (hauteurPiece + 1) == 0) {
                if ((x - ((largeurPiece + 1) / 2)) % (largeurPiece + 1) == 0 && x % (largeurPiece + 1) != 0) {
                    reponse = true;
                }
            } else if ((y - (hauteurPiece + 1) / 2) % (hauteurPiece + 1) == 0 && x % (largeurPiece + 1) == 0) {
                reponse = true;
            }
        }
        return reponse;
    }

    /**
     * Verifie si le robot peut se deplacer a la case x, y
     *
     * @param robot
     *            Le robot a deplacer
     * @param x
     *            Le x de la position a verifier
     * @param y
     *            Le y de la position a verifier
     * @return Vrai si le robot peut se deplacer a cette place
     */
    public boolean deplacementPossible(Robot robot, int x, int y) {
        return grille[x][y] == null || grille[x][y].interactionPossible(robot);
    }

    /**
     *  Retourne une matrice contenant les noms de fichiers
     *  des images à afficher
     *
     *  @return La matrice des noms des fichiers d'image
     *
     * @param robot
     *            Le robot a afficher dans la grille
     */
    public String[][] afficher(Robot robot) {
        //TODO
        Point positionRobot = robot.getPosition();
        String[][] fileNameMatrix = new String[width][height];

        for (int i = 0; i < width; i++) {
            // Imprime une ligne ensuite fait un saut de ligne
            for (int j = 0; j < height; j++) {


                if (positionRobot.getX() == i && positionRobot.getY() == j) {
                    fileNameMatrix[i][j] = "skins/rob.png";
                }

                else if (grille[i][j] == null) {
                    fileNameMatrix[i][j] = "skins/back.png";
                }

                else {
                    fileNameMatrix[i][j] = grille[i][j].getFileName();

                }
            }


        }
        return fileNameMatrix;
    }

    /**
     * Fait interagir le robot avec la case sur la quelle il se situe ou fait rien
     * si cette case est null
     *
     * @param robot
     *            Le robot a faire interagir
     */
    public String interagir(Robot robot) {
        Point positionRobot = robot.getPosition();
        Case c = grille[positionRobot.getX()][positionRobot.getY()];
        String newMessage = "";
        if (c instanceof NonKitten) {
            newMessage = ((NonKitten) c).getDescription();
        } else if (c != null){
            c.interagir(robot);
        }
        return newMessage;
    }
}

