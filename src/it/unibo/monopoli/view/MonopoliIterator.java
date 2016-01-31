package it.unibo.monopoli.view;

import java.awt.Point;
import java.util.Iterator;

public class MonopoliIterator implements Iterator<Point> {

    private int currentX = 11;
    private int currentY = 10;

    private int directionX = -1;
    private int directionY = 0;

    private int index = -1;

    public int getCurrentIndex() {
        return index;
    }

    public Point getCurrentPoint() {
        return new Point(currentX, currentY);
    }

    @Override
    public boolean hasNext() {
        return !(currentX == 10 && currentY == 9);
    }

    @Override
    public Point next() {
        currentX += directionX;
        currentY += directionY;

        // final Point p = getCurrentPoint();

        if (currentX == 0 && currentY == 10) {
            directionX = 0;
            directionY = -1;
        } else if (currentX == 0 && currentY == 0) {
            directionX = 1;
            directionY = 0;
        } else if (currentX == 10 && currentY == 0) {
            directionX = 0;
            directionY = 1;
        } else if (currentX == 10 && currentY == 10) {
            directionX = -1;
            directionY = 0;
            index = -1;
        }

        index++;

        return getCurrentPoint();
    }

    public static int getIdByPoint(Point p) {
        MonopoliIterator mi = new MonopoliIterator();

        while (mi.hasNext()) {
            Point p1 = mi.next();

            if (p1.x == p.x && p1.y == p.y) {
                return mi.getCurrentIndex();
            }
        }

        throw new IllegalArgumentException();
    }

    public static Point getPointById(int id) {
        MonopoliIterator mi = new MonopoliIterator();

        while (mi.hasNext()) {
            mi.next();

            if (id == mi.getCurrentIndex()) {
                return mi.getCurrentPoint();
            }

        }
        throw new IllegalArgumentException();
    }
}
