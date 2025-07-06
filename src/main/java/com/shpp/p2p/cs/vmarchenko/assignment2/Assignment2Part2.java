package com.shpp.p2p.cs.vmarchenko.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    public void run(){
        GOval go = new GOval(0, 0, getHeight() / 4.0, getWidth() / 4.0);
        go.setFilled(true);
        add(go);

        GOval go2 = new GOval(0, getWidth(), getHeight() / 4.0, getWidth() / 4.0);
        go2.setFilled(true);
        add(go2);

        GOval go3 = new GOval(getHeight(), getWidth(), getHeight() / 4.0, getWidth() / 4.0);
        go3.setFilled(true);
        add(go3);

        GOval go4 = new GOval(getHeight(), 0, getHeight() / 4.0, getWidth() / 4.0);
        go4.setFilled(true);
        add(go4);

        GRect rect = new GRect((getHeight() / 4.0) / 2, (getWidth() / 4.0) / 2,
                getHeight(), getWidth());
        rect.setFilled(true);
        rect.setColor(Color.RED);
        add(rect);
    }
}
