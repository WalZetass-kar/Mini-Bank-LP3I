package view;

import controller.CardController;
import controller.AccountController;
import model.Card;
import model.Account;
import model.User;
import repository.UserRepository;
import repository.AccountRepository;
import utils.UIConstants;
import utils.UIComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Modern Create Card by NIK View - Dark Blue Theme
 */
public class CreateCardByNIKView extends JFrame {
    private JTextField txtNIK;
    private JButton btnCreateCard, btnBack;
    private CardController cardController;
    private AccountController accountController;
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public CreateCardByNIKView() {
        cardController = new CardController();
        accountController = new AccountController();
        userRepository = new UserRepository();
        accountRepository = new AccountRepository();
        initComponents();
    }

    private void initComponents() {
        setTitle("BANK LP3I PEKANBARU - Buat Kartu ATM");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(1000, 700));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.BG_DARK);

        JPanel leftPanel = createInfoPanel();
        JPanel rightPanel = createFormPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(480);
        splitPane.setEnabled(false);
        splitPane.setBorder(null);
        splitPane.setDividerSize(0);

        mainPanel.add(splitPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(13, 27, 42),
                    0, getHeight(), new Color(27, 38, 59)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                g2d.setColor(new Color(41, 128, 185, 30));
                g2d.fillOval(-100, -100, 300, 300);
                g2d.fillOval(getWidth() - 200, getHeight() - 200, 300, 300);
            }
        };
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 50, 0, 50);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        JLabel lblIcon = new JLabel("💳");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 100));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitle = new JLabel("Buat Kartu ATM");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 38));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Dengan NIK Terdaftar");
        lblSubtitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 16));
        lblSubtitle.setForeground(UIConstants.TEXT_SECONDARY);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setOpaque(false);
        info.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        String[][] infoList = {
            {"ℹ️", "Gunakan NIK yang sudah terdaftar"},
            {"💳", "Kartu dibuat otomatis"},
            {"🔐", "PIN digenerate otomatis"},
            {"✓", "Langsung bisa digunakan"}
        };

        for (String[] item : infoList) {
            JPanel infoItem = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            infoItem.setOpaque(false);
            
            JLabel icon = new JLabel(item[0]);
            icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            
            JLabel text = new JLabel(item[1]);
            text.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 15));
            text.setForeground(UIConstants.TEXT_SECONDARY);
            
            infoItem.add(icon);
            infoItem.add(text);
            info.add(infoItem);
        }

        content.add(lblIcon);
        content.add(Box.createVerticalStrut(20));
        content.add(lblTitle);
        content.add(Box.createVerticalStrut(10));
        content.add(lblSubtitle);
        content.add(info);

        panel.add(content, gbc);
        return panel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.BG_DARK);
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 60, 0, 60);
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setOpaque(false);
        formContainer.setMaximumSize(new Dimension(500, 600));

        JLabel lblTitle = new JLabel("Masukkan NIK Anda");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("NIK harus sudah terdaftar di sistem");
        lblSubtitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 14));
        lblSubtitle.setForeground(UIConstants.TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel formCard = new JPanel();
        formCard.setLayout(new BoxLayout(formCard, BoxLayout.Y_AXIS));
        formCard.setBackground(UIConstants.BG_CARD);
        formCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        formCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblNIK = new JLabel("NIK (Nomor Induk Kependudukan)");
        lblNIK.setFont(UIConstants.FONT_BODY_BOLD);
        lblNIK.setForeground(UIConstants.TEXT_PRIMARY);
        lblNIK.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblHint = new JLabel("16 digit nomor induk kependudukan");
        lblHint.setFont(UIConstants.FONT_SMALL);
        lblHint.setForeground(UIConstants.TEXT_MUTED);
        lblHint.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtNIK = UIComponents.createTextField();
        txtNIK.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtNIK.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 15, 0));
        buttonPanel.setBackground(UIConstants.BG_CARD);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnCreateCard = UIComponents.createPrimaryButton("Buat Kartu");
        btnCreateCard.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 15));
        btnCreateCard.addActionListener(e -> handleCreateCard());

        btnBack = UIComponents.createSecondaryButton("Kembali");
        btnBack.addActionListener(e -> handleBack());

        buttonPanel.add(btnCreateCard);
        buttonPanel.add(btnBack);

        formCard.add(lblNIK);
        formCard.add(Box.createVerticalStrut(5));
        formCard.add(lblHint);
        formCard.add(Box.createVerticalStrut(10));
        formCard.add(txtNIK);
        formCard.add(Box.createVerticalStrut(30));
        formCard.add(buttonPanel);

        formContainer.add(lblTitle);
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(lblSubtitle);
        formContainer.add(Box.createVerticalStrut(30));
        formContainer.add(formCard);

        panel.add(formContainer, gbc);
        return panel;
    }

    private void handleCreateCard() {
        String nik = txtNIK.getText().trim();

        if (nik.isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIK harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nik.length() != 16) {
            JOptionPane.showMessageDialog(this, "NIK harus 16 digit", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        btnCreateCard.setEnabled(false);
        btnCreateCard.setText("Memproses...");

        SwingWorker<Card, Void> worker = new SwingWorker<Card, Void>() {
            private User user;
            private Account account;

            @Override
            protected Card doInBackground() {
                user = userRepository.getUserByNIK(nik);
                if (user == null) return null;

                account = accountRepository.getAccountByUserId(user.getId());
                if (account == null) return null;

                Card existingCard = cardController.getCardByAccountId(account.getId());
                if (existingCard != null) return existingCard;

                return cardController.createCard(account.getId());
            }

            @Override
            protected void done() {
                try {
                    Card card = get();
                    btnCreateCard.setEnabled(true);
                    btnCreateCard.setText("Buat Kartu");

                    if (card == null) {
                        JOptionPane.showMessageDialog(CreateCardByNIKView.this,
                            "NIK tidak ditemukan!\nSilakan daftar akun terlebih dahulu.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    showCardInfo(card, account);
                } catch (Exception e) {
                    btnCreateCard.setEnabled(true);
                    btnCreateCard.setText("Buat Kartu");
                    JOptionPane.showMessageDialog(CreateCardByNIKView.this,
                        "Terjadi kesalahan: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void showCardInfo(Card card, Account account) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIConstants.BG_CARD);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("✅ Kartu Berhasil Dibuat!");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 20));
        lblTitle.setForeground(UIConstants.SUCCESS);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblMsg = new JLabel("Simpan informasi berikut dengan aman:");
        lblMsg.setFont(UIConstants.FONT_BODY);
        lblMsg.setForeground(UIConstants.TEXT_PRIMARY);
        lblMsg.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(UIConstants.BG_LIGHT);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        addInfoRow(infoPanel, "Nomor Kartu:", card.getNoKartu());
        addInfoRow(infoPanel, "PIN:", card.getPin());
        addInfoRow(infoPanel, "No. Rekening:", account.getNoRekening());
        addInfoRow(infoPanel, "Status:", card.getStatus());

        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblMsg);
        panel.add(Box.createVerticalStrut(15));
        panel.add(infoPanel);

        JOptionPane.showMessageDialog(this, panel, "Kartu Berhasil Dibuat", JOptionPane.PLAIN_MESSAGE);
        handleBack();
    }

    private void addInfoRow(JPanel panel, String label, String value) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row.setOpaque(false);

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(UIConstants.FONT_BODY_BOLD);
        lblLabel.setForeground(UIConstants.TEXT_PRIMARY);
        lblLabel.setPreferredSize(new Dimension(120, 25));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(UIConstants.FONT_BODY);
        lblValue.setForeground(UIConstants.TEXT_SECONDARY);

        row.add(lblLabel);
        row.add(lblValue);
        panel.add(row);
    }

    private void handleBack() {
        new LoginView().setVisible(true);
        dispose();
    }
}
