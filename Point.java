/**
 * Objet point qui definit un point 2D dans l'espace
 *
 * @author Tanahel Huot-Roberge et Charles Menard
 */
public class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean egal(int x, int y) {
        return x == this.x && y == this.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}