
/**
 * Objet cle represente par le caractere '\'' et ' ' lorsqu'elle est deja prise
 *
 * @author Tanahel Huot-Roberge et Charles menard
 *
 */
public class Cle extends Case {
    private Point position = null;
    private boolean prise = false;

    /**
     * Contructeur de Cle qui ceer une cle a la position donne
     *
     * @param x
     *            Position en x
     * @param y
     *            Position en y
     */
    public Cle(int x, int y) {
        position = new Point(x, y);
        representation = '\'';
        fileName = "skins/key.png";
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
        // Ajoute une cle au robot et rend la cle "invisible" (prise) si elle ne l'est
        // pas deja
        if (!prise) {
            robot.setNbrCle(robot.getNbrCle() + 1);
            representation = ' ';
            prise = true;
            fileName = "skins/back.png";
        }
    }

}

