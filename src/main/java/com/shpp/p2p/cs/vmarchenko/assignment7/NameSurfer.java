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

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

        add(new JLabel("Name: "), "North");

        nameField = new JTextField(10);
        add(nameField, "North");
        nameField.setActionCommand("nameEntered");
        nameField.addActionListener(this);

        JButton graphButton = new JButton("Graph");
        add(graphButton, "North");
        graphButton.setActionCommand("graphPressed");

        JButton clearButton = new JButton("Clear");
        add(clearButton, "North");
        clearButton.setActionCommand("clearPressed");

        graph = new NameSurferGraph();
        add(graph);

        addActionListeners();
    }

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
            nameField.setText("");
        }

        if(command.equals("clearPressed")) {
            graph.clear();
            graph.update();
        }
    }
}
