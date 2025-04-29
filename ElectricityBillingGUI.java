import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class ElectricityBillingGUI extends JFrame implements ActionListener {
    JTextField nameField, meterField, monthField, unitsField;
    JTextArea outputArea;
    JButton calculateButton;

    public ElectricityBillingGUI() {
        setTitle("Electricity Billing System");
        setSize(400, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Meter Number:"));
        meterField = new JTextField();
        inputPanel.add(meterField);

        inputPanel.add(new JLabel("Month:"));
        monthField = new JTextField();
        inputPanel.add(monthField);

        inputPanel.add(new JLabel("Units Consumed:"));
        unitsField = new JTextField();
        inputPanel.add(unitsField);

        calculateButton = new JButton("Generate Bill");
        calculateButton.addActionListener(this);
        inputPanel.add(calculateButton);

        add(inputPanel, BorderLayout.NORTH);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String meter = meterField.getText();
        String month = monthField.getText();
        int units = Integer.parseInt(unitsField.getText());

        double billAmount = 0;
        if (units <= 100) billAmount = units * 5;
        else if (units <= 200) billAmount = 100 * 5 + (units - 100) * 7;
        else billAmount = 100 * 5 + 100 * 7 + (units - 200) * 10;

        String bill = "----- Electricity Bill -----\n" +
                      "Customer Name : " + name + "\n" +
                      "Meter Number  : " + meter + "\n" +
                      "Month         : " + month + "\n" +
                      "Units Consumed: " + units + "\n" +
                      "Total Bill    : ₹" + billAmount + "\n" +
                      "----------------------------\n";

        outputArea.setText(bill);

        // Save to file
        try {
            FileWriter writer = new FileWriter("ElectricityBills_GUI.txt", true);
            writer.write(bill + "\n");
            writer.close();
        } catch (IOException ex) {
            outputArea.append("\n❌ Error saving to file");
        }
    }

    public static void main(String[] args) {
        new ElectricityBillingGUI();
    }
}
