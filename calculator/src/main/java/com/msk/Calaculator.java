package com.msk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;

public class Calaculator extends JFrame implements ActionListener {

    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 80;
    private static final Font BUTTON_FONT = new Font("Unicode", Font.BOLD, 25);
    private static final Color BUTTON_BACKGROUND = new Color(0xD4D4D2);
    private static final Color NUMBER_COLOR = new Color(0x1C1C1C);
    private static final Color OPERATOR_COLOR = new Color(0x505050);
    private static final Color EQUALS_COLOR = new Color(0x04890B);
    private static final Color AC_COLOR = new Color(0xFF1100);

    private JTextField inputField;
    private final JButton[] numberButtons = new JButton[10];
    private JButton bAdd, bSub, bMul, bDiv, bEq, bAc;

    public Calaculator() {
        super("Calaculator");
        initializeUI();
    }

    private void initializeUI() {
        setSize(500, 600);
        setLocation(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input field configuration
        inputField = new JTextField();
        inputField.setFont(new Font("Unicode", Font.BOLD, 40));
        inputField.setForeground(new Color(0xff6000));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setPreferredSize(new Dimension(500, 60));
        inputField.setEditable(false); // Defensive: User cannot directly modify inputField
        add(inputField, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        add(buttonPanel, BorderLayout.CENTER);
        createButtons(buttonPanel);

        setVisible(true);
    }

    private void createButtons(JPanel panel) {
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i), NUMBER_COLOR, BUTTON_BACKGROUND, BUTTON_FONT);
            numberButtons[i].addActionListener(this);
        }
        bAdd = createButton("+", OPERATOR_COLOR, BUTTON_BACKGROUND, BUTTON_FONT);
        bSub = createButton("-", OPERATOR_COLOR, BUTTON_BACKGROUND, BUTTON_FONT);
        bMul = createButton("*", OPERATOR_COLOR, BUTTON_BACKGROUND, BUTTON_FONT);
        bDiv = createButton("/", OPERATOR_COLOR, BUTTON_BACKGROUND, BUTTON_FONT);
        bEq = createButton("=", EQUALS_COLOR, BUTTON_BACKGROUND, BUTTON_FONT);
        bAc = createButton("AC", AC_COLOR, BUTTON_BACKGROUND, BUTTON_FONT);

        // ActionListeners
        bAdd.addActionListener(this);
        bSub.addActionListener(this);
        bMul.addActionListener(this);
        bDiv.addActionListener(this);
        bEq.addActionListener(this);
        bAc.addActionListener(this);

        panel.add(numberButtons[7]); panel.add(numberButtons[8]); panel.add(numberButtons[9]); panel.add(bAc);
        panel.add(numberButtons[4]); panel.add(numberButtons[5]); panel.add(numberButtons[6]); panel.add(bAdd);
        panel.add(numberButtons[1]); panel.add(numberButtons[2]); panel.add(numberButtons[3]); panel.add(bSub);
        panel.add(numberButtons[0]); panel.add(bMul); panel.add(bDiv); panel.add(bEq);
    }

    private JButton createButton(String text, Color foreground, Color background, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();

        // Check number buttons
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                inputField.setText(input + i);
                return;
            }
        }

        // Check operator buttons
        if (e.getSource() == bAdd || e.getSource() == bSub || e.getSource() == bMul || e.getSource() == bDiv) {
            if (!input.isEmpty() && isLastCharValid(input)) { // Defensive: Prevent consecutive operators
                JButton operatorButton = (JButton) e.getSource();
                inputField.setText(input + operatorButton.getText());
            }
        } else if (e.getSource() == bAc) {
            inputField.setText("");
        } else if (e.getSource() == bEq) {
            evaluateExpression(input);
        } else {
            // Handle unexpected button press
            JOptionPane.showMessageDialog(this, "Unexpected input!", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean isLastCharValid(String input) {
        char lastChar = input.charAt(input.length() - 1);
        return Character.isDigit(lastChar);
    }

    private void evaluateExpression(String input) {
        if (input.isEmpty()) { // Defensive: Empty input check
            JOptionPane.showMessageDialog(this, "Input is empty!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        if (engine == null) { // Defensive: Ensure script engine is available
            JOptionPane.showMessageDialog(this, "Script engine unavailable!", "Engine Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Object result = engine.eval(input);
            inputField.setText(result.toString());
        } catch (Exception exception) {
            // Defensive: Catch errors in expression evaluation
            JOptionPane.showMessageDialog(this, "Invalid expression! Please check your input.", "Calculation Error", JOptionPane.ERROR_MESSAGE);
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calaculator::new);
    }
}
