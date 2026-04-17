package view;

import controller.CardController;
import controller.AccountController;
import model.Card;
import model.Account;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class CreateCardView extends JFrame {
    private JButton btnCreateCard;
    private JButton btnLogout;
    private CardController cardController;
    private AccountController accountController;
    private Account account;
    
    // Modern color scheme
    private final Color PRIMARY_COLOR = new Color(13, 71, 161);
    private final Color PRIMARY_LIGHT = new Color(25, 118, 210);
    private final Color SUCCESS_COLOR = new Color(76, 175, 80);
    private final Color DANGER_COLOR = new Color(244, 67, 54);
    private final Color CARD_BG = Color.WHITE;
    private final Color TEXT_PRIMARY = new Color(33, 33, 33);
    private final Color TEXT_SECONDARY = new Color(117, 117, 117);

    public CreateCardView(Account account) {
        this.account = account;
        cardController = new CardController();
        accountController = new AccountController();
        initComponents();
    }

    private void initComponents() {
        setTitle("BANK LP3I PEKANBARU - Buat Kartu ATM");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(500, 600));

        // Main Panel with gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_COLOR, 0, h, PRIMARY_LIGHT);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(CARD_BG);
        cardPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        cardPanel.setMaximumSize(new Dimension(450, 550));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(CARD_BG);
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblIcon = new JLabel("💳");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 72));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblIcon);
        headerPanel.add(Box.createVerticalStrut(15));

        JLabel lblTitle = new JLabel("Buat Kartu ATM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblTitle);
        headerPanel.add(Box.createVerticalStrut(10));

        JLabel lblSubtitle = new JLabel("Kartu ATM Anda Belum Dibuat");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(TEXT_SECONDARY);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblSubtitle);

        cardPanel.add(headerPanel);
        cardPanel.add(Box.createVerticalStrut(30));

        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(227, 242, 253));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_LIGHT, 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

        JLabel lblInfoTitle = new JLabel("ℹ️  Informasi Kartu ATM");
        lblInfoTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblInfoTitle.setForeground(PRIMARY_COLOR);
        lblInfoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(lblInfoTitle);
        infoPanel.add(Box.createVerticalStrut(15));

        String[] infoItems = {
            "✓ Nomor Kartu: 16 digit (otomatis)",
            "✓ PIN: 6 digit (otomatis)",
            "✓ Status: AKTIF",
            "✓ Masa Berlaku: 5 tahun",
            "✓ Nomor Rekening: " + account.getNoRekening()
        };

        for (String item : infoItems) {
            JLabel lblItem = new JLabel(item);
            lblItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblItem.setForeground(TEXT_PRIMARY);
            lblItem.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(lblItem);
            infoPanel.add(Box.createVerticalStrut(8));
        }

        cardPanel.add(infoPanel);
        cardPanel.add(Box.createVerticalStrut(30));

        // Warning Panel
        JPanel warningPanel = new JPanel();
        warningPanel.setLayout(new BoxLayout(warningPanel, BoxLayout.Y_AXIS));
        warningPanel.setBackground(new Color(255, 243, 224));
        warningPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 152, 0), 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        warningPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel lblWarning = new JLabel("<html><b>⚠️ Penting:</b><br>" +
            "Setelah kartu dibuat, catat nomor kartu dan PIN Anda.<br>" +
            "PIN akan digunakan untuk login dan transaksi.</html>");
        lblWarning.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblWarning.setForeground(TEXT_PRIMARY);
        warningPanel.add(lblWarning);

        cardPanel.add(warningPanel);
        cardPanel.add(Box.createVerticalStrut(30));

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 15));
        buttonPanel.setBackground(CARD_BG);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        btnCreateCard = createStyledButton("Buat Kartu ATM Sekarang", SUCCESS_COLOR, Color.WHITE);
        btnCreateCard.addActionListener(e -> handleCreateCard());
        buttonPanel.add(btnCreateCard);

        btnLogout = createStyledButton("Logout", DANGER_COLOR, Color.WHITE);
        btnLogout.addActionListener(e -> handleLogout());
        buttonPanel.add(btnLogout);

        cardPanel.add(buttonPanel);

        // Add card to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(cardPanel, gbc);

        add(mainPanel);
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        
        button.addMouseListener(new MouseAdapter() {
            Color originalBg = bgColor;
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalBg);
            }
        });
        
        return button;
    }

    private void handleCreateCard() {
        // Show loading
        btnCreateCard.setEnabled(false);
        btnCreateCard.setText("Membuat Kartu...");

        SwingWorker<Card, Void> worker = new SwingWorker<Card, Void>() {
            @Override
            protected Card doInBackground() {
                return cardController.createCard(account.getId());
            }

            @Override
            protected void done() {
                try {
                    Card card = get();
                    btnCreateCard.setEnabled(true);
                    btnCreateCard.setText("Buat Kartu ATM Sekarang");

                    if (card != null) {
                        // Show success dialog with card details
                        showCardDetails(card);
                        
                        // Open dashboard
                        DashboardView dashboard = new DashboardView(card, account);
                        dashboard.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(CreateCardView.this,
                            "Gagal membuat kartu ATM.\nSilakan coba lagi.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    btnCreateCard.setEnabled(true);
                    btnCreateCard.setText("Buat Kartu ATM Sekarang");
                    JOptionPane.showMessageDialog(CreateCardView.this,
                        "Terjadi kesalahan: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void showCardDetails(Card card) {
        // Create custom dialog
        JDialog dialog = new JDialog(this, "Kartu ATM Berhasil Dibuat", true);
        dialog.setSize(450, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CARD_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Success icon
        JLabel lblIcon = new JLabel("✅");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 64));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblIcon);
        panel.add(Box.createVerticalStrut(15));

        // Title
        JLabel lblTitle = new JLabel("Kartu ATM Berhasil Dibuat!");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(SUCCESS_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(20));

        // Card details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(245, 247, 250));
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        addDetailRow(detailsPanel, "Nomor Kartu", card.getNoKartu());
        addDetailRow(detailsPanel, "PIN", card.getPin());
        addDetailRow(detailsPanel, "Status", card.getStatus());
        addDetailRow(detailsPanel, "Berlaku Sampai", card.getExpiredDate());

        panel.add(detailsPanel);
        panel.add(Box.createVerticalStrut(20));

        // Warning
        JLabel lblWarning = new JLabel("<html><center><b>⚠️ PENTING!</b><br>" +
            "Catat dan simpan nomor kartu dan PIN Anda.<br>" +
            "Gunakan untuk login selanjutnya.</center></html>");
        lblWarning.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblWarning.setForeground(new Color(255, 152, 0));
        lblWarning.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblWarning);
        panel.add(Box.createVerticalStrut(20));

        // OK Button
        JButton btnOk = createStyledButton("OK, Saya Sudah Catat", PRIMARY_LIGHT, Color.WHITE);
        btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOk.setMaximumSize(new Dimension(250, 45));
        btnOk.addActionListener(e -> dialog.dispose());
        panel.add(btnOk);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
        rowPanel.setBackground(new Color(245, 247, 250));
        rowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblLabel.setForeground(TEXT_SECONDARY);
        lblLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblValue.setForeground(TEXT_PRIMARY);
        lblValue.setAlignmentX(Component.LEFT_ALIGNMENT);

        rowPanel.add(lblLabel);
        rowPanel.add(Box.createVerticalStrut(3));
        rowPanel.add(lblValue);

        panel.add(rowPanel);
        panel.add(Box.createVerticalStrut(12));
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin logout?\n\nKartu ATM belum dibuat.",
            "Konfirmasi Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
            this.dispose();
        }
    }
}
