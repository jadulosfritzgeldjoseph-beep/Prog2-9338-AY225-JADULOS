    import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AttendanceTracker {
    private static DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Attendance Management System");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 600));
        sidebar.setBackground(new Color(15, 23, 42));
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
        
        JLabel logo = new JLabel("EDU-TRACK");
        logo.setForeground(new Color(99, 102, 241));
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        sidebar.add(logo);

        JPanel mainArea = new JPanel(new BorderLayout(20, 0));
        mainArea.setBackground(new Color(241, 245, 249));
        mainArea.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel formPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        formPanel.setPreferredSize(new Dimension(300, 0));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JTextField nameField = new JTextField();
        String[] levels = {"BSIT - 1A", "BSIT - 2A", "BSIT - 3A", "BSIT - 4A"};
        JComboBox<String> courseBox = new JComboBox<>(levels);
        
        JButton btnAdd = new JButton("RECORD ATTENDANCE");
        btnAdd.setBackground(new Color(79, 70, 229));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdd.setFocusPainted(false);

        formPanel.add(new JLabel("STUDENT NAME"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("COURSE / SECTION"));
        formPanel.add(courseBox);
        formPanel.add(new JLabel(""));
        formPanel.add(btnAdd);

        String[] cols = {"Name", "Course", "Time Logged"};
        tableModel = new DefaultTableModel(cols, 0);
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));

        btnAdd.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
                tableModel.insertRow(0, new Object[]{name, courseBox.getSelectedItem(), time});
                nameField.setText("");
            }
        });

        mainArea.add(formPanel, BorderLayout.WEST);
        mainArea.add(scrollPane, BorderLayout.CENTER);

        frame.add(sidebar, BorderLayout.WEST);
        frame.add(mainArea, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
