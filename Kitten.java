/**
 * Objet kitten qui herite de case, et qui ajoute comme attribut le nom du
 * kitten et sa position. La partie se termine (le robot gagne) lorsque le robot
 * passe sur la case du kitten.
 */

public class Kitten extends Case {
    private String nom = "caramel";
    private Point position = null;

    /**
     * Constucteur de Kitten
     *
     * @param x
     *            position en x du kitten
     * @param y
     *            position en y du kitten
     * @param c
     *            character qui represente le kitten
     */
    public Kitten(int x, int y, char c) {
        position = new Point(x, y);
        representation = c;
        fileName = "nki/" + ((int) Math.floor(82 * Math.random())+1) + ".png";
    }

    public String getNom() {
        return nom;
    }

    public Point getPosition() {
        return new Point(position.getX(), position.getY());
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    @Override
    public boolean interactionPossible(Robot robot) {
        return true;
    }

    @Override
    public void interagir(Robot robot) {
        // Imprime le message de gagner et exit le thread principal de l'application
        robot.setGameWon(true);
    }

}

