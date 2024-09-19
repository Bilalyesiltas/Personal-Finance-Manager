public class PersonalFinanceManager {
    public static void main(String[] args) {
        // GUI'nin thread güvenli bir şekilde başlatılması
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
