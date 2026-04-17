package view;

import controller.TransactionController;
import model.Card;
import model.Account;
import utils.UIConstants;
import utils.UIComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DepositView extends JPanel {
    private Card card;
    private Account account;
    private JTextField txtAmount;
    private JButton btnDeposit, btnBack;
    private TransactionController transactionController;
    private DashboardView parentDashboard;

    public DepositView(Card card, Account account, DashboardView parentDashboard) {
        this.card = card;
        this.account = account;
        this.parentDashboard = parentDashboard;
        transactionController = new TransactionController();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(UIConstants.BG_DARK);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(UIConstants.BG_DARK);
        mainPanel.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.setBorder(BorderFactory.createEmptyBorder(15, 35, 15, 35));
        container.setMaximumSize(new Dimension(600, 700));

        // Header - NO EMOJI
        JLabel lblTitle = new JLabel("Setor Uang");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Tambahkan saldo ke rekening Anda");
        lblSubtitle.setFont(UIConstants.FONT_BODY);
        lblSubtitle.setForeground(UIConstants.TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Form Card
        JPanel formCard = new JPanel();
        formCard.setLayout(new BoxLayout(formCard, BoxLayout.Y_AXIS));
        formCard.setBackground(UIConstants.BG_CARD);
        formCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        formCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Info panel
        JPanel infoPanel = UIComponents.createSuccessPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfo = new JLabel("Informasi:");
        lblInfo.setFont(UIConstants.FONT_BODY_BOLD);
        lblInfo.setForeground(UIConstants.SUCCESS);
        lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfo1 = new JLabel("• Saldo akan langsung bertambah");
        lblInfo1.setFont(UIConstants.FONT_SMALL);
        lblInfo1.setForeground(UIConstants.TEXT_PRIMARY);
        lblInfo1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfo2 = new JLabel("• Transaksi tercatat otomatis");
        lblInfo2.setFont(UIConstants.FONT_SMALL);
        lblInfo2.setForeground(UIConstants.TEXT_PRIMARY);
        lblInfo2.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(lblInfo);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(lblInfo1);
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(lblInfo2);

        formCard.add(infoPanel);
        formCard.add(Box.createVerticalStrut(25));

        // Amount
        JLabel lblAmount = new JLabel("Jumlah Setoran");
        lblAmount.setFont(UIConstants.FONT_BODY_BOLD);
        lblAmount.setForeground(UIConstants.TEXT_PRIMARY);
        lblAmount.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtAmount = UIComponents.createTextField();
        txtAmount.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtAmount.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblHint = new JLabel("Contoh: 100000, 500000, 1000000");
        lblHint.setFont(UIConstants.FONT_SMALL);
        lblHint.setForeground(UIConstants.TEXT_MUTED);
        lblHint.setAlignmentX(Component.LEFT_ALIGNMENT);

        formCard.add(lblAmount);
        formCard.add(Box.createVerticalStrut(8));
        formCard.add(txtAmount);
        formCard.add(Box.createVerticalStrut(5));
        formCard.add(lblHint);
        formCard.add(Box.createVerticalStrut(30));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 15, 0));
        buttonPanel.setBackground(UIConstants.BG_CARD);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnDeposit = UIComponents.createSuccessButton("Setor Uang");
        btnDeposit.addActionListener(e -> handleDeposit());

        btnBack = UIComponents.createSecondaryButton("Kembali");
        btnBack.addActionListener(e -> handleBack());

        buttonPanel.add(btnDeposit);
        buttonPanel.add(btnBack);

        formCard.add(buttonPanel);

        container.add(lblTitle);
        container.add(Box.createVerticalStrut(3));
        container.add(lblSubtitle);
        container.add(Box.createVerticalStrut(15));
        container.add(formCard);

        mainPanel.add(container, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void handleDeposit() {
        String amountStr = txtAmount.getText().trim();

        if (amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(parentDashboard, "Jumlah harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            long amount = Long.parseLong(amountStr);

            btnDeposit.setEnabled(false);
            btnDeposit.setText("Memproses...");

            SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() {
                    return transactionController.deposit(account.getId(), amount);
                }

                @Override
                protected void done() {
                    try {
                        String result = get();
                        btnDeposit.setEnabled(true);
                        btnDeposit.setText("Setor Uang");

                        if ("SUCCESS".equals(result)) {
                            JOptionPane.showMessageDialog(parentDashboard,
                                "Setor tunai berhasil!\n\nJumlah: Rp " + String.format("%,d", amount),
                                "Sukses",
                                JOptionPane.INFORMATION_MESSAGE);
                            handleBack();
                        } else {
                            JOptionPane.showMessageDialog(parentDashboard, result, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                        btnDeposit.setEnabled(true);
                        btnDeposit.setText("Setor Uang");
                        JOptionPane.showMessageDialog(parentDashboard, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            worker.execute();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentDashboard, "Jumlah harus berupa angka", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleBack() {
        if (parentDashboard != null) {
            parentDashboard.showDashboard();
        }
    }
}
