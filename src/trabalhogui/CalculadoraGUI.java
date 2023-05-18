package trabalhogui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*

Autor: Marino Ricardo

*/


public class CalculadoraGUI extends JFrame implements ActionListener {

    private JTextField numField1;
    private JTextField numField2;
    private JLabel resultLabel;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton exponentButton;
    private JButton remainderButton;
    private JButton integerDivisionButton;
    private JButton factorialButton;
    private List<String> history;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));

        numField1 = new JTextField();
        numField2 = new JTextField();
        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        exponentButton = new JButton("^");
        remainderButton = new JButton("%");
        integerDivisionButton = new JButton("//");
        factorialButton = new JButton("!");

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        exponentButton.addActionListener(this);
        remainderButton.addActionListener(this);
        integerDivisionButton.addActionListener(this);
        factorialButton.addActionListener(this);

        add(new JLabel("Número 1: "));
        add(numField1);
        add(new JLabel("Número 2: "));
        add(numField2);
        add(new JLabel("Resultado: "));
        add(resultLabel);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(exponentButton);
        add(remainderButton);
        add(integerDivisionButton);
        add(factorialButton);

        history = new ArrayList<>();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double num1 = 0;
        double num2 = 0;
        try {
            num1 = Double.parseDouble(numField1.getText());
            num2 = Double.parseDouble(numField2.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite apenas valores numéricos!");
            return;
        }

        if (e.getSource() == addButton) {
            double result = num1 + num2;
            resultLabel.setText(Double.toString(result));
            addToHistory(num1 + " + " + num2 + " = " + result);
        } else if (e.getSource() == subtractButton) {
            double result = num1 - num2;
            resultLabel.setText(Double.toString(result));
            addToHistory(num1 + " - " + num2 + " = " + result);
        } else if (e.getSource() == multiplyButton) {
            double result = num1 * num2;
            resultLabel.setText(Double.toString(result));
            addToHistory(num1 + " * " + num2 + " = " + result);
        } else if (e.getSource() == divideButton) {
            if (num2 != 0) {
                double result = num1 / num2;
                resultLabel.setText(Double.toString(result));
                addToHistory(num1 + " / " + num2 + " = " + result);
            } else {
                JOptionPane.showMessageDialog(this, "Não é possível dividir por zero!");
            }
        } else if (e.getSource() == exponentButton) {
            double result = Math.pow(num1, num2);
            resultLabel.setText(Double.toString(result));
            addToHistory(num1 + " ^ " + num2 + " = " + result);
        } else if (e.getSource() == remainderButton) {
            double result = num1 % num2;
            resultLabel.setText(Double.toString(result));
            addToHistory(num1 + " % " + num2 + " = " + result);
        } else if (e.getSource() == integerDivisionButton) {
            double result = num1 / num2; // Realiza a divisão normal
            result = Math.floor(result); // Arredonda para baixo para obter a divisão inteira
            resultLabel.setText(Double.toString(result));
            addToHistory(num1 + " // " + num2 + " = " + result);
        } else if (e.getSource() == factorialButton) {
            double result = factorial(num1);
            resultLabel.setText(Double.toString(result));
            addToHistory(num1 + "! = " + result);
        }
    }

    private double factorial(double num) {
        if (num == 0) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }

    private void addToHistory(String operation) {
        history.add(operation);
        if (history.size() > 10) {
            history.remove(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI();
            }
        });
    }
}
