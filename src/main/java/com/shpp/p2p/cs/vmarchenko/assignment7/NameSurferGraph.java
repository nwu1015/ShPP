package com.shpp.p2p.cs.vmarchenko.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private final HashMap<String, NameSurferEntry> nameEntries;
    private final Color[] colors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        this.nameEntries = new HashMap<>();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        nameEntries.clear();
    }

	
	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        nameEntries.put(entry.getName(), entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        int retreat = getWidth() / NDECADES;
        createVerticalLines(retreat);

        // Horizontal lines
        createLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        createLine(0, getHeight() - GRAPH_MARGIN_SIZE,
                getWidth(), getHeight() - GRAPH_MARGIN_SIZE);

        drawGraph(retreat);
    }

    /**
     * Method for creating a line at given points
     *
     * @param x1 x-coordinate of the first point
     * @param y1 y-coordinate of the first point
     * @param x2 x-coordinate of the second point
     * @param y2 y-coordinate of the second point
     */
    public void createLine(double x1, double y1, double x2, double y2) {
        GLine line = new GLine(x1, y1, x2, y2);
        add(line);
    }

    /**
     * Method for creating a line at given points
     *
     * @param x1 x-coordinate of the first point
     * @param y1 y-coordinate of the first point
     * @param x2 x-coordinate of the second point
     * @param y2 y-coordinate of the second point
     * @param color line color
     */
    public void createLine(double x1, double y1, double x2, double y2, Color color) {
        GLine line = new GLine(x1, y1, x2, y2);
        line.setColor(color);
        add(line);
    }

    /**
     * Method for constructing vertical lines used to indicate centuries in the program.
     *
     * @param retreat uniform distance between vertical lines
     */
    public void createVerticalLines(int retreat){
        for(int i = 0; i < NDECADES; i++) {
            createLine(i * retreat, 0, i * retreat, getHeight(), Color.BLACK);

            int year = START_DECADE + i * 10;
            GLabel label = new GLabel(Integer.toString(year));
            add(label, i * retreat, getHeight() - label.getDescent());
        }
    }

    /**
     * Filling the graph with data
     *
     * @param retreat uniform distance between vertical lines
     */
    public void drawGraph(int retreat) {
        int colorIndex = 0;
        for(NameSurferEntry entry : nameEntries.values()) {
            drawEntry(entry, retreat, colorIndex);

            colorIndex++;
            if(colorIndex == colors.length) {
                colorIndex = 0;
            }
        }
    }

    /**
     * Draw a graph for a given name
     *
     * @param entry given name
     * @param retreat uniform distance between vertical lines
     * @param colorIndex color index with which to draw the graph
     */
    public void drawEntry(NameSurferEntry entry, int retreat, int colorIndex) {
        for (int i = 0; i < NDECADES; i++) {
            int rank = entry.getRank(i);
            double x = i * retreat;
            double y = getYPosition(rank);
            String rankString = (rank == 0) ? "*" : Integer.toString(rank);

            GLabel label = new GLabel(entry.getName() + " " + rankString, x, y);
            label.setColor(colors[colorIndex]);
            add(label);

            if (i != 0) {
                double previousPointX = (i - 1) * retreat;
                int previousRank = entry.getRank(i - 1);
                double previousY = getYPosition(previousRank);

                createLine(previousPointX, previousY, x, y, colors[colorIndex]);
            }
        }
    }

    /**
     * Finding the y-coordinate for a specific name at a specific time
     *
     * @param rank the level of popularity of a name at a specific time
     * @return y-coordinate for a specific name at a specific time
     */
    public double getYPosition(int rank){
        if (rank == 0) {
            return getHeight() - GRAPH_MARGIN_SIZE;
        } else {
            return GRAPH_MARGIN_SIZE + (double) rank / MAX_RANK * (getHeight() - GRAPH_MARGIN_SIZE);
        }
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}