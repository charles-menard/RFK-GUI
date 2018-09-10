/**
 * Classe pour les objets de type Porte.
 * Herite de case. Le robot ne peut pas traverser la porte a moins
 * d'avoir au moins une cle en sa possession pour la debarrer.
 * @author Tanahel Huot-Roberge et Charles Menard
 */
public class Porte extends Case {
    private Point position = null;
    private boolean ouverte = false;

    /**
     * Contructeur de Cle qui ceer un robot a la position donne
     *
     * @param x
     *            Position en x
     * @param y
     *            Position en y
     */
    public Porte(int x, int y) {
        position = new Point(x, y);
        representation = '!';
        fileName = "skins/door.png";
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public boolean isOuverte() {
        return ouverte;
    }

    @Override
    public boolean interactionPossible(Robot robot) {
        return robot.getNbrCle() > 0||this.isOuverte();
    }

    @Override
    public void interagir(Robot robot) {
        // Ouvre la porte enleve une cle au robot si le robot au moins une cle et si la
        // porte n'est pas deja ouverte
        if (!ouverte && interactionPossible(robot)) {
            ouverte = true;
            representation = ' ';
            fileName = "skins/back.png";
            robot.setNbrCle(robot.getNbrCle() - 1);
        }
    }


}

