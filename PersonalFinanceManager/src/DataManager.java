import java.io.*;
import java.util.*;

public class DataManager {
    private static final String FILE_PATH = "finance_data.txt";

    public static void saveData(List<Income> incomes, List<Expense> expenses) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("Incomes:\n");
            for (Income income : incomes) {
                writer.write(income.getDescription() + "," + income.getAmount() + "\n");
            }
            writer.write("Expenses:\n");
            for (Expense expense : expenses) {
                writer.write(expense.getDescription() + "," + expense.getAmount() + "\n");
            }
        }
    }

    public static void loadData(List<Income> incomes, List<Expense> expenses) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean readingIncomes = true;
            while ((line = reader.readLine()) != null) {
                if (line.equals("Incomes:")) {
                    readingIncomes = true;
                } else if (line.equals("Expenses:")) {
                    readingIncomes = false;
                } else {
                    String[] parts = line.split(",");
                    if (readingIncomes) {
                        incomes.add(new Income(parts[0], Double.parseDouble(parts[1])));
                    } else {
                        expenses.add(new Expense(parts[0], Double.parseDouble(parts[1])));
                    }
                }
            }
        }
    }
}
