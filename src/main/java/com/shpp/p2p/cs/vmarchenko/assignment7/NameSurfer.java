package com.shpp.p2p.cs.vmarchenko.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private NameSurferGraph graph;
    private NameSurferDataBase dataBase;
    private JTextField nameField;

	/* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

        this.add(new JLabel("Name: "), "North");

        nameField = new JTextField(10);
        this.add(nameField, "North");
        nameField.setActionCommand("nameEntered");
        nameField.addActionListener(this);

        JButton graphButton = new JButton("Graph");
        this.add(graphButton, "North");
        graphButton.setActionCommand("graphPressed");

        JButton clearButton = new JButton("Clear");
        this.add(clearButton, "North");
        clearButton.setActionCommand("clearPressed");

        graph = new NameSurferGraph();
        add(graph);

        this.addActionListeners();
    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("graphPressed") || command.equals("nameEntered")) {
            NameSurferEntry entry = dataBase.findEntry(nameField.getText().toLowerCase().trim());
            if(entry != null) {
                graph.addEntry(entry);
                graph.update();
            }
        }

        if(command.equals("clearPressed")) {
            graph.clear();
            graph.update();
        }
    }
}
