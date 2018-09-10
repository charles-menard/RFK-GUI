/**
 * Classe pour le robot, c'est-a-dire le joueur. Le robot a un nom, une position
 * un nombre de cle (pour ouvrir les portes) et peut avoir un teleporteur.
 * Cette classe contient seulement les attributs du robot MAIS PAS les methodes
 * pour le deplacer ou le faire interagir avec des cases.
 * @author Tanahel Huot-Roberge et Charles Menard
 */
public class Robot {
    private String nom = "R. O. B.";
    private Point position = null;
    private int nbrCle = 0;
    private boolean teleporteur = false;
    private boolean gameWon = false;
    /**
     * Contructeur de Robot qui ceer un robot a la position donne
     *
     * @param x
     *            Position en x
     * @param y
     *            Position en y
     */
    public Robot(int x, int y) {
        position = new Point(x, y);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Point getPosition() {
        return new Point(position.getX(), position.getY());
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public int getNbrCle() {
        return nbrCle;
    }

    public void setNbrCle(int nbrCle) {
        this.nbrCle = nbrCle;
    }

    public boolean hasTeleporteur() {
        return teleporteur;
    }

    public void setTeleporteur(boolean teleporteur) {
        this.teleporteur = teleporteur;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean b){
        this.gameWon = b;
    }
}
