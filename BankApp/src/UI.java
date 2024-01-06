import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    int page;
    StringBuilder sb;
    Account user;
    {
        user = new Account();
        sb = new StringBuilder();
    }
    UI() {
        this.setTitle("BankApp");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(400, 500);
        this.setLocationRelativeTo(null);

        Container cnt = this.getContentPane();
        cnt.setLayout(null);
        cnt.setBackground(new Color(255, 255, 255));

        JLabel titleLabel = new JLabel();
        titleLabel.setText("BankApp");
        titleLabel.setLocation(0, 0);
        titleLabel.setSize(387, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(6, 202, 246));
        titleLabel.setForeground(new Color(255, 255, 255));
        cnt.add(titleLabel);

        panel = new JPanel();
        panel.setLocation(10, 50);
        panel.setSize(367, 350);
        panel.setBackground(new Color(2, 2, 2));
        panel.setLayout(null);
        cnt.add(panel);

        exitBtn = new JButton();
        exitBtn.setLocation(10, 410);
        exitBtn.setSize(367, 30);
        exitBtn.setForeground(new Color(255, 255, 255));
        exitBtn.setBackground(new Color(250, 71, 77));
        exitBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cnt.add(exitBtn);

        exitBtn.addActionListener(new Handler());
    }
    class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (page == -1) {
                if (e.getSource() == forgetPIN) {
                    JOptionPane.showMessageDialog(null, "PIN is 1234",
                            "PIN", JOptionPane.INFORMATION_MESSAGE);
                } else if (e.getSource() == btn[2][3]) {
                    if (user.PIN_match(String.valueOf(sb))) {
                        menuPage();
                    } else {
                        pinField.setText("Wrong PIN");
                    }
                    sb.delete(0, sb.length());
                    pinField.setText(String.valueOf(sb));
                } else if (e.getSource() == btn[2][2]) {
                    if (!sb.isEmpty()) {
                        sb.delete(sb.length() - 1, sb.length());
                        pinField.setText(String.valueOf(sb));
                    }
                } else {
                    int done = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (e.getSource() == btn[i][j]) {
                                sb.append(btn[i][j].getText());
                                pinField.setText(String.valueOf(sb));
                                done = 1;
                                break;
                            }
                        }
                        if (done == 1) break;
                    }
                }
            } else if (page == 0) {
                double amount;
                try {
                    amount = Integer.parseInt(amountBox.getText());
                } catch (Exception ex) {
                    amount = 0.0;
                }
                if (e.getSource() == deposit) {
                    user.deposit(amount);
                    balDisplay.setText(String.valueOf("$ " + user.getBalance() + " USD"));
                    amountBox.setText("");
                    JOptionPane.showMessageDialog(null, " $ " + amount +
                            " USD added successfully!",
                            "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                } else if (e.getSource() == withdraw) {
                    if (user.withdraw(amount)) {
                        balDisplay.setText(String.valueOf("$ " + user.getBalance() + " USD"));
                        amountBox.setText("");
                        JOptionPane.showMessageDialog(null, " $ " + amount +
                                        " USD withdrawal success!",
                                "Withdraw Done", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        amountBox.setText("");
                        JOptionPane.showMessageDialog(null,
                                "Insufficient balance!",
                                "Less Balance", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            if (e.getSource() == exitBtn) {
                System.exit(0);
            }
        }
    }
    public void launch() {
        page = -1;

        panel.removeAll();
        panel.repaint();
        panel.setBackground(new Color(255, 255, 255));

        JLabel pinPrompt = new JLabel();
        pinPrompt.setText("Enter PIN to continue");
        pinPrompt.setLocation(0, 0);
        pinPrompt.setSize(367, 80);
        pinPrompt.setOpaque(true);
        pinPrompt.setBackground(new Color(255, 255, 255));
        pinPrompt.setHorizontalAlignment(SwingConstants.CENTER);
        pinPrompt.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        panel.add(pinPrompt);

        pinField = new JTextField();
        pinField.setText("");
        pinField.setLocation(0, 80);
        pinField.setSize(367, 60);
        pinField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        pinField.setHorizontalAlignment(SwingConstants.CENTER);
        pinField.setBackground(new Color(255, 255, 255));
        pinField.setDisabledTextColor(new Color(78, 73, 248));
        pinField.setEnabled(false);
        panel.add(pinField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLocation(0, 150);
        buttonsPanel.setSize(367, 160);
        buttonsPanel.setBackground(new Color(255, 255, 255));
        buttonsPanel.setLayout(new GridLayout(3, 4, 10, 10));
        panel.add(buttonsPanel);

        btn = new JButton[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                btn[i][j] = new JButton();
                btn[i][j].setBackground(new Color(16, 186, 166));
                btn[i][j].setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
                btn[i][j].setForeground(new Color(255, 255, 255));
                btn[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn[i][j].addActionListener(new Handler());
                buttonsPanel.add(btn[i][j]);
            }
        }
        btn[0][0].setText("1");
        btn[0][1].setText("2");
        btn[0][2].setText("3");
        btn[0][3].setText("4");
        btn[1][0].setText("5");
        btn[1][1].setText("6");
        btn[1][2].setText("7");
        btn[1][3].setText("8");
        btn[2][0].setText("9");
        btn[2][1].setText("0");
        btn[2][2].setText("<");
        btn[2][2].setBackground(new Color(250, 71, 77));
        btn[2][3].setText("OK");
        btn[2][3].setBackground(new Color(6, 202, 246));

        forgetPIN = new JButton();
        forgetPIN.setText("Forgot Pin?");
        forgetPIN.setLocation(100, 310);
        forgetPIN.setSize(167, 40);
        forgetPIN.setHorizontalAlignment(SwingConstants.CENTER);
        forgetPIN.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 20));
        forgetPIN.setBackground(new Color(255, 255, 255));
        forgetPIN.setForeground(new Color(120, 120, 120));
        forgetPIN.setBorderPainted(false);
        forgetPIN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(forgetPIN);

        exitBtn.setText("Exit Program");
        forgetPIN.addActionListener(new Handler());
    }
    public void menuPage() {
        page = 0;

        panel.removeAll();
        panel.repaint();

        JLabel balanceIndicator = new JLabel();
        balanceIndicator.setText("Current Balance:");
        balanceIndicator.setLocation(0, 0);
        balanceIndicator.setSize(367, 50);
        balanceIndicator.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        panel.add(balanceIndicator);

        balDisplay = new JTextField();
        balDisplay.setText(String.valueOf("$ " + user.getBalance() + " USD"));
        balDisplay.setLocation(0, 50);
        balDisplay.setSize(367, 60);
        balDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        balDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        balDisplay.setBackground(new Color(255, 255, 255));
        balDisplay.setDisabledTextColor(new Color(0, 193, 85));
        balDisplay.setEnabled(false);
        panel.add(balDisplay);

        JLabel amountPrompt = new JLabel();
        amountPrompt.setText("Enter amount:");
        amountPrompt.setLocation(0, 130);
        amountPrompt.setSize(167, 40);
        amountPrompt.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
        panel.add(amountPrompt);

        amountBox = new JTextField();
        amountBox.setLocation(167, 130);
        amountBox.setSize(200, 40);
        amountBox.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        amountBox.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(amountBox);

        deposit = new JButton();
        deposit.setText("DEPOSIT");
        deposit.setLocation(20, 200);
        deposit.setSize(327, 50);
        deposit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        deposit.setBackground(new Color(6, 202, 246));
        deposit.setForeground(new Color(255, 255, 255));
        deposit.setBorderPainted(false);
        deposit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(deposit);

        withdraw = new JButton();
        withdraw.setText("WITHDRAW");
        withdraw.setLocation(20, 280);
        withdraw.setSize(327, 50);
        withdraw.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        withdraw.setBackground(new Color(250, 71, 77));
        withdraw.setForeground(new Color(255, 255, 255));
        withdraw.setBorderPainted(false);
        withdraw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(withdraw);

        exitBtn.setText("Log Out");
        exitBtn.setBackground(new Color(3, 78, 116));

        deposit.addActionListener(new Handler());
        withdraw.addActionListener(new Handler());
    }
    private final JPanel panel;
    private JButton[][] btn;
    private JButton forgetPIN, exitBtn, deposit, withdraw;
    private JTextField pinField, balDisplay, amountBox;
}
