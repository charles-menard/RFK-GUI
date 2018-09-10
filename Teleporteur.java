/**
 * Classe pour les objets de type téléporteur.
 * Hérite de case en plus d'avoir une position.
 * Il n'y a qu'un téléporteur par grille. Lorsque le robot marche sur le téléporteur,
 * celui-ci disparait et l'attribut hasTeleporteur du robot tombe à vrai.
 *
 */

public class Teleporteur extends Case {
    private Point position = null;
    private boolean pris = false;

    /**
     * Contructeur de Teleporteur qui ceer un Teleporteur a la position donne
     *
     * @param x
     *            Position en x
     * @param y
     *            Position en y
     */
    public Teleporteur(int x, int y) {

        position = new Point(x, y);
        representation = 'T';
        fileName = "nki/" + ((int) Math.floor(82 * Math.random())+1) + ".png";
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public boolean interactionPossible(Robot robot) {
        return true;
    }

    @Override
    public void interagir(Robot robot) {
        // Ajoute un teleporteur au robot et le "invisible" (pris) si il ne l'est
        // pas deja
        if (!pris) {
            robot.setTeleporteur(true);
            representation = ' ';
            fileName = "skins/back.png";
            pris = true;
        }
    }


}