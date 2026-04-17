package view;

import controller.TransactionController;
import model.Card;
import model.Account;
import model.Transaction;
import utils.UIConstants;
import utils.UIComponents;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TransactionHistoryView extends JPanel {
    private Card card;
    private Account account;
    private JTable table;
    private JButton btnBack;
    private TransactionController transactionController;
    private DashboardView parentDashboard;

    public TransactionHistoryView(Card card, Account account, DashboardView parentDashboard) {
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

        // Header - NO EMOJI
        JLabel lblTitle = new JLabel("Riwayat Transaksi");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Semua transaksi Anda");
        lblSubtitle.setFont(UIConstants.FONT_BODY);
        lblSubtitle.setForeground(UIConstants.TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout(0, 15));
        cardPanel.setBackground(UIConstants.BG_CARD);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Table Panel
        String[] columnNames = {"Tanggal", "Jenis", "Jumlah", "Deskripsi"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Transaction> transactions = transactionController.getTransactionHistory(account.getId());
        
        if (transactions.isEmpty()) {
            model.addRow(new Object[]{"", "Belum ada transaksi", "", ""});
        } else {
            for (Transaction t : transactions) {
                Object[] row = {
                    t.getDate(),
                    t.getType(),
                    "Rp " + String.format("%,d", t.getAmount()),
                    t.getDescription()
                };
                model.addRow(row);
            }
        }

        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setGridColor(UIConstants.BORDER_DARK);
        table.setSelectionBackground(UIConstants.BG_LIGHT);
        table.setSelectionForeground(UIConstants.TEXT_PRIMARY);
        table.setBackground(UIConstants.BG_CARD);
        table.setForeground(UIConstants.TEXT_PRIMARY);

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(UIConstants.PRIMARY_ACCENT);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);

        // Center align for specific columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1));
        scrollPane.getViewport().setBackground(UIConstants.BG_CARD);

        cardPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(UIConstants.BG_CARD);

        btnBack = UIComponents.createSecondaryButton("Kembali ke Dashboard");
        btnBack.setPreferredSize(new Dimension(200, 45));
        btnBack.addActionListener(e -> handleBack());

        buttonPanel.add(btnBack);

        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        container.add(lblTitle);
        container.add(Box.createVerticalStrut(3));
        container.add(lblSubtitle);
        container.add(Box.createVerticalStrut(15));
        container.add(cardPanel);

        mainPanel.add(container, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void handleBack() {
        if (parentDashboard != null) {
            parentDashboard.showDashboard();
        }
    }
}
