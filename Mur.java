/**
 * Classe pour les objets de type Mur. Herite de case, en plus d'avoir comme
 * attribut une position. Le robot ne peut pas traverser ni interagir avec les
 * murs.
 *
 * @author Tanahel Huot-Roberge et Charles Menard
 */
public class Mur extends Case {
    private Point position = null;

    /**
     * Contructeur de Mur qui ceer un mur a la position donne
     *
     * @param x
     *            Position en x
     * @param y
     *            Position en y
     */
    public Mur(int x, int y) {
        position = new Point(x, y);
        representation = '%';
        fileName = "skins/wall.png";
    }




    public Point getPosition() {
        return new Point(position.getX(), position.getY());
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    @Override
    public boolean interactionPossible(Robot robot) {
        return false;
    }

    @Override
    public void interagir(Robot robot) {
    }
}

