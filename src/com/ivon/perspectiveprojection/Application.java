package com.ivon.perspectiveprojection;

import acm.graphics.GLine;
import acm.program.GraphicsProgram;

/**
 * Created by Owner on 1/11/2016.
 */
public class Application extends GraphicsProgram {

    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;

    //Basic screen settings
    public static final int APPLICATION_WIDTH = 1280;
    public static final int APPLICATION_HEIGHT = 720;

    private static final Vector c = new Vector(0, -200, 10);
    private static final Vector t = new Vector(0, 0, 0);
    private static final Vector e = new Vector(0, 200, 800);

    private static final Vector x = new Vector(-50, 0, 800);

    private static final int ROTATE_WIDTH = 300;
    private static final int DELAY = 5;     // ms

    //start our Recurse objects
    public static void main(String[] args) {
        new Application().start(args);
    }

    public void run() {
        Cube cube = new Cube(x, 100);
        while(true) {
            for (int i=-ROTATE_WIDTH; i<=ROTATE_WIDTH; i++) {
                Vector _c = new Vector(c.get(X)+i, c.get(Y), c.get(Z));
                Vector _e = new Vector(e.get(X)-i, e.get(Y), e.get(Z));
                drawCube(cube, _c, t, _e);
                sleep();
            }
            for (int i=ROTATE_WIDTH; i>=-ROTATE_WIDTH; i--) {
                Vector _c = new Vector(c.get(X)+i, c.get(Y), c.get(Z));
                Vector _e = new Vector(e.get(X)-i, e.get(Y), e.get(Z));
                drawCube(cube, _c, t, _e);
                sleep();
            }
        }
    }

    public void drawCube(Cube cube, Vector c, Vector t, Vector e) {
        GLine[] lines = cube.toLines(c, t, e);
        removeAll();
        drawLines(lines);
    }

    public void drawLines(GLine[] lines) {
        for (GLine line : lines) {
            drawLine(line);
        }
    }

    public void drawLine(GLine line) {
        line.setStartPoint(
                line.getStartPoint().getX() + APPLICATION_WIDTH/2,
                line.getStartPoint().getY() + APPLICATION_HEIGHT/2
        );
        line.setEndPoint(
                line.getEndPoint().getX() + APPLICATION_WIDTH/2,
                line.getEndPoint().getY() + APPLICATION_HEIGHT/2
        );
        add(line);
    }

    public void sleep() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

}
