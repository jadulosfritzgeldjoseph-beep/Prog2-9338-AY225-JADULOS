import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RecordSystem extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtId, txtName, txtGrade;

    public RecordSystem() {
        setTitle("Student Record System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Name", "Grade"}, 0);
        table = new JTable(model);
        loadData("MOCK_DATA.csv");

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        txtId = new JTextField();
        txtName = new JTextField();
        txtGrade = new JTextField();
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");

        inputPanel.add(new JLabel(" ID:")); inputPanel.add(txtId);
        inputPanel.add(new JLabel(" Name:")); inputPanel.add(txtName);
        inputPanel.add(new JLabel(" Grade:")); inputPanel.add(txtGrade);
        inputPanel.add(btnAdd); inputPanel.add(btnDelete);

        btnAdd.addActionListener(e -> {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String grade = txtGrade.getText().trim();

            if (id.isEmpty() || name.isEmpty() || grade.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: All fields must be filled!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            model.addRow(new Object[]{id, name, grade});
            txtId.setText(""); txtName.setText(""); txtGrade.setText("");
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    model.addRow(line.split(","));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecordSystem().setVisible(true));
    }
}