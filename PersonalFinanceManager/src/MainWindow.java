import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private List<Income> incomes = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    public MainWindow() {
        setTitle("Personal Finance Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JTextField descriptionField = new JTextField(20);
        JTextField amountField = new JTextField(10);
        JButton addIncomeButton = new JButton("Add Income");
        JButton addExpenseButton = new JButton("Add Expense");
        JButton saveButton = new JButton("Save Data");
        JButton loadButton = new JButton("Load Data");

        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(addIncomeButton);
        inputPanel.add(addExpenseButton);
        inputPanel.add(saveButton);
        inputPanel.add(loadButton);

        add(inputPanel, BorderLayout.NORTH);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        addIncomeButton.addActionListener(e -> {
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());
            incomes.add(new Income(description, amount));
            descriptionField.setText("");
            amountField.setText("");
            updateDisplay(displayArea);
        });

        addExpenseButton.addActionListener(e -> {
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());
            expenses.add(new Expense(description, amount));
            descriptionField.setText("");
            amountField.setText("");
            updateDisplay(displayArea);
        });

        saveButton.addActionListener(e -> {
            try {
                DataManager.saveData(incomes, expenses);
                JOptionPane.showMessageDialog(this, "Data saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
            }
        });

        loadButton.addActionListener(e -> {
            try {
                DataManager.loadData(incomes, expenses);
                updateDisplay(displayArea);
                JOptionPane.showMessageDialog(this, "Data loaded successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
            }
        });
    }

    private void updateDisplay(JTextArea displayArea) {
        StringBuilder sb = new StringBuilder();
        sb.append("Incomes:\n");
        for (Income income : incomes) {
            sb.append(income.getDescription()).append(": $").append(income.getAmount()).append("\n");
        }
        sb.append("Expenses:\n");
        for (Expense expense : expenses) {
            sb.append(expense.getDescription()).append(": $").append(expense.getAmount()).append("\n");
        }
        displayArea.setText(sb.toString());
    }
}
