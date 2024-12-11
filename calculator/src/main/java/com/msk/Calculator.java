package com.msk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.*;
import javax.swing.*;


public class Calculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JButton bAdd, bSub, bMul, bDiv, bEq, bAc;

    public Calculator() {
        // Set the title and size of the JFrame
        super("Calculator");
        setSize(500, 600);
        setLocation(400, 100);
        setLayout(new BorderLayout()); // Use BorderLayout for the JFrame

        // Initialize the input field
        inputField = new JTextField("");
        inputField.setFont(new Font("Unicode", Font.BOLD, 40));
        inputField.setForeground(new Color(0xff6000));
        inputField.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
        inputField.setPreferredSize(new Dimension(500, 60)); // Increase the input field size
        add(inputField, BorderLayout.NORTH); // Add input field to the top part (NORTH)

        // Initialize the buttons
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b3 = new JButton("3");
        b2 = new JButton("2");
        b1 = new JButton("1");
        b0 = new JButton("0");
        bEq = new JButton("=");
        bDiv = new JButton("/");
        bMul = new JButton("x");
        bSub = new JButton("-");
        bAdd = new JButton("+");
        bAc = new JButton("AC");


        // set Font for all Buttons
        Font btnFont = new Font("Unicode",Font.BOLD,25);

        b1.setFont(btnFont);
        b2.setFont(btnFont);
        b3.setFont(btnFont);
        b4.setFont(btnFont);
        b5.setFont(btnFont);
        b6.setFont(btnFont);
        b7.setFont(btnFont);
        b8.setFont(btnFont);
        b9.setFont(btnFont);
        b0.setFont(btnFont);
        bAdd.setFont(btnFont);
        bMul.setFont(btnFont);
        bSub.setFont(btnFont);
        bDiv.setFont(btnFont);
        bAc.setFont(btnFont);
        bEq.setFont(btnFont);

        bAc.setForeground(new Color(0xFF1100));


        //Change the Background of all Buttons
        Color BgColor = new Color(0xD4D4D2);

        b1.setBackground(BgColor);
        b2.setBackground(BgColor);
        b3.setBackground(BgColor);
        b4.setBackground(BgColor);
        b5.setBackground(BgColor);
        b6.setBackground(BgColor);
        b7.setBackground(BgColor);
        b8.setBackground(BgColor);
        b9.setBackground(BgColor);
        b0.setBackground(BgColor);
        bAdd.setBackground(BgColor);
        bMul.setBackground(BgColor);
        bSub.setBackground(BgColor);
        bDiv.setBackground(BgColor);
        bAc.setBackground(BgColor);
        bEq.setBackground(BgColor);

        //Change the Background of all Buttons
        Color FgColor = new Color(0x1C1C1C);

        b1.setForeground(FgColor);
        b2.setForeground(FgColor);
        b3.setForeground(FgColor);
        b4.setForeground(FgColor);
        b5.setForeground(FgColor);
        b6.setForeground(FgColor);
        b7.setForeground(FgColor);
        b8.setForeground(FgColor);
        b9.setForeground(FgColor);
        b0.setForeground(FgColor);

        Color operatorColor = new Color(0x505050);
        bAdd.setForeground(operatorColor);
        bMul.setForeground(operatorColor);
        bSub.setForeground(operatorColor);
        bDiv.setForeground(operatorColor);
        bEq.setForeground(new Color(0x04890B));

        // Set the preferred size for buttons to make them smaller
        Dimension buttonSize = new Dimension(80, 80); // Set each button size (Width x Height)
        b7.setPreferredSize(buttonSize);
        b8.setPreferredSize(buttonSize);
        b9.setPreferredSize(buttonSize);
        b4.setPreferredSize(buttonSize);
        b5.setPreferredSize(buttonSize);
        b6.setPreferredSize(buttonSize);
        b3.setPreferredSize(buttonSize);
        b2.setPreferredSize(buttonSize);
        b1.setPreferredSize(buttonSize);
        b0.setPreferredSize(buttonSize);
        bEq.setPreferredSize(buttonSize);
        bDiv.setPreferredSize(buttonSize);
        bMul.setPreferredSize(buttonSize);
        bSub.setPreferredSize(buttonSize);
        bAdd.setPreferredSize(buttonSize);
        bAc.setPreferredSize(buttonSize);

        // Create a JPanel for the calculator buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4)); // 4x4 grid for the calculator buttons

        // Add buttons to the buttonPanel
        buttonPanel.add(b7);
        buttonPanel.add(b8);
        buttonPanel.add(b9);
        buttonPanel.add(bAc);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);
        buttonPanel.add(bAdd);
        buttonPanel.add(b3);
        buttonPanel.add(b2);
        buttonPanel.add(b1);
        buttonPanel.add(bSub);
        buttonPanel.add(b0);
        buttonPanel.add(bMul);
        buttonPanel.add(bDiv);
        buttonPanel.add(bEq);

        // Add the button panel to the center part of the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners for all buttons
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b3.addActionListener(this);
        b2.addActionListener(this);
        b1.addActionListener(this);
        b0.addActionListener(this);
        bAdd.addActionListener(this);
        bSub.addActionListener(this);
        bMul.addActionListener(this);
        bDiv.addActionListener(this);
        bEq.addActionListener(this);
        bAc.addActionListener(this);

        // Set visible after setting layout
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        Object result = "";

        if (e.getSource().equals(b1)) {
            inputField.setText(input + "1");
        } else if (e.getSource().equals(b2)) {
            inputField.setText(input + "2");
        } else if (e.getSource().equals(b3)) {
            inputField.setText(input + "3");
        } else if (e.getSource().equals(b4)) {
            inputField.setText(input + "4");
        } else if (e.getSource().equals(b5)) {
            inputField.setText(input + "5");
        } else if (e.getSource().equals(b6)) {
            inputField.setText(input + "6");
        } else if (e.getSource().equals(b7)) {
            inputField.setText(input + "7");
        } else if (e.getSource().equals(b8)) {
            inputField.setText(input + "8");
        } else if (e.getSource().equals(b9)) {
            inputField.setText(input + "9");
        } else if (e.getSource().equals(bAdd)) {
            inputField.setText(input + "+");
        } else if (e.getSource().equals(bSub)) {
            inputField.setText(input + "-");
        } else if (e.getSource().equals(bMul)) {
            inputField.setText(input + "*");
        } else if (e.getSource().equals(bDiv)) {
            inputField.setText(input + "/");
        } else if (e.getSource().equals(bAc)){
            inputField.setText("");
        } else if (e.getSource().equals(bEq)) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");

            try {
                result = engine.eval(input);
            } catch (Exception ee) {
                ee.printStackTrace();
            }

            inputField.setText(result.toString());
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
