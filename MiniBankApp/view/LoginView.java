package view;

import controller.AuthController;
import controller.AccountController;
import controller.CardController;
import model.Card;
import model.Account;
import utils.UIConstants;
import utils.UIComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * Ultra Modern Login View - Dark Blue Theme
 */
public class LoginView extends JFrame {
    private JTextField txtNoKartu;
    private JPasswordField txtPin;
    private JButton btnLogin, btnRegister, btnCreateCard;
    private JCheckBox chkShowPin;
    private AuthController authController;
    private AccountController accountController;
    private CardController cardController;

    public LoginView() {
        authController = new AuthController();
        accountController = new AccountController();
        cardController = new CardController();
        initComponents();
    }

    private void initComponents() {
        setTitle("BANK LP3I PEKANBARU");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(1000, 700));

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.BG_DARK);

        // Left Panel - Branding (40%)
        JPanel leftPanel = createModernBrandingPanel();
        
        // Right Panel - Login Form (60%)
        JPanel rightPanel = createModernLoginPanel();

        // Split pane for responsive
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(480);
        splitPane.setEnabled(false);
        splitPane.setBorder(null);
        splitPane.setDividerSize(0);

        mainPanel.add(splitPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JPanel createModernBrandingPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(13, 27, 42),
                    0, getHeight(), new Color(27, 38, 59)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Decorative circles
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

        // Logo
        JLabel lblLogo = new JLabel("🏦");
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 100));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bank name
        JLabel lblBank = new JLabel("BANK LP3I");
        lblBank.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 42));
        lblBank.setForeground(UIConstants.TEXT_PRIMARY);
        lblBank.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblLocation = new JLabel("PEKANBARU");
        lblLocation.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        lblLocation.setForeground(UIConstants.PRIMARY_HOVER);
        lblLocation.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tagline
        JLabel lblTagline = new JLabel("Modern Banking Experience");
        lblTagline.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 16));
        lblTagline.setForeground(UIConstants.TEXT_SECONDARY);
        lblTagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Features
        JPanel features = new JPanel();
        features.setLayout(new BoxLayout(features, BoxLayout.Y_AXIS));
        features.setOpaque(false);
        features.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        String[][] featureList = {
            {"⚡", "Transaksi Cepat & Aman"},
            {"🔒", "Keamanan Terjamin"},
            {"💳", "Kartu ATM Digital"},
            {"📱", "Akses 24/7"}
        };

        for (String[] feature : featureList) {
            JPanel featureItem = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            featureItem.setOpaque(false);
            
            JLabel icon = new JLabel(feature[0]);
            icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            
            JLabel text = new JLabel(feature[1]);
            text.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 15));
            text.setForeground(UIConstants.TEXT_SECONDARY);
            
            featureItem.add(icon);
            featureItem.add(text);
            features.add(featureItem);
        }

        content.add(lblLogo);
        content.add(Box.createVerticalStrut(20));
        content.add(lblBank);
        content.add(Box.createVerticalStrut(5));
        content.add(lblLocation);
        content.add(Box.createVerticalStrut(15));
        content.add(lblTagline);
        content.add(features);

        panel.add(content, gbc);
        return panel;
    }

    private JPanel createModernLoginPanel() {
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
        formContainer.setMaximumSize(new Dimension(450, 800));

        // Welcome text
        JLabel lblWelcome = new JLabel("Selamat Datang");
        lblWelcome.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 32));
        lblWelcome.setForeground(UIConstants.TEXT_PRIMARY);
        lblWelcome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Masuk ke akun Anda");
        lblSubtitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 15));
        lblSubtitle.setForeground(UIConstants.TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Form card
        JPanel formCard = new JPanel();
        formCard.setLayout(new BoxLayout(formCard, BoxLayout.Y_AXIS));
        formCard.setBackground(UIConstants.BG_CARD);
        formCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        formCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Nomor Kartu
        JLabel lblNoKartu = new JLabel("Nomor Kartu ATM");
        lblNoKartu.setFont(UIConstants.FONT_BODY_BOLD);
        lblNoKartu.setForeground(UIConstants.TEXT_PRIMARY);
        lblNoKartu.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtNoKartu = UIComponents.createTextField();
        txtNoKartu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtNoKartu.setAlignmentX(Component.LEFT_ALIGNMENT);

        // PIN
        JLabel lblPin = new JLabel("PIN");
        lblPin.setFont(UIConstants.FONT_BODY_BOLD);
        lblPin.setForeground(UIConstants.TEXT_PRIMARY);
        lblPin.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtPin = UIComponents.createPasswordField();
        txtPin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtPin.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Show PIN
        chkShowPin = new JCheckBox("Tampilkan PIN");
        chkShowPin.setFont(UIConstants.FONT_SMALL);
        chkShowPin.setForeground(UIConstants.TEXT_SECONDARY);
        chkShowPin.setBackground(UIConstants.BG_CARD);
        chkShowPin.setFocusPainted(false);
        chkShowPin.setAlignmentX(Component.LEFT_ALIGNMENT);
        chkShowPin.addActionListener(e -> {
            txtPin.setEchoChar(chkShowPin.isSelected() ? (char) 0 : '●');
        });

        // Login button
        btnLogin = UIComponents.createPrimaryButton("Masuk");
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btnLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnLogin.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 16));
        btnLogin.addActionListener(e -> handleLogin());

        formCard.add(lblNoKartu);
        formCard.add(Box.createVerticalStrut(8));
        formCard.add(txtNoKartu);
        formCard.add(Box.createVerticalStrut(20));
        formCard.add(lblPin);
        formCard.add(Box.createVerticalStrut(8));
        formCard.add(txtPin);
        formCard.add(Box.createVerticalStrut(10));
        formCard.add(chkShowPin);
        formCard.add(Box.createVerticalStrut(30));
        formCard.add(btnLogin);

        // Divider
        JPanel divider = new JPanel();
        divider.setLayout(new BoxLayout(divider, BoxLayout.X_AXIS));
        divider.setOpaque(false);
        divider.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        divider.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSeparator sep1 = new JSeparator();
        sep1.setForeground(UIConstants.BORDER_DARK);
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(UIConstants.BORDER_DARK);
        
        JLabel lblOr = new JLabel(" atau ");
        lblOr.setFont(UIConstants.FONT_SMALL);
        lblOr.setForeground(UIConstants.TEXT_MUTED);

        divider.add(sep1);
        divider.add(lblOr);
        divider.add(sep2);

        // Action buttons
        btnRegister = UIComponents.createSecondaryButton("📝 Buat Akun Baru");
        btnRegister.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnRegister.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRegister.addActionListener(e -> handleRegister());

        btnCreateCard = UIComponents.createSecondaryButton("💳 Buat Kartu dengan NIK");
        btnCreateCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnCreateCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCreateCard.addActionListener(e -> handleCreateCardByNIK());

        formContainer.add(lblWelcome);
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(lblSubtitle);
        formContainer.add(Box.createVerticalStrut(40));
        formContainer.add(formCard);
        formContainer.add(Box.createVerticalStrut(25));
        formContainer.add(divider);
        formContainer.add(Box.createVerticalStrut(25));
        formContainer.add(btnRegister);
        formContainer.add(Box.createVerticalStrut(12));
        formContainer.add(btnCreateCard);

        panel.add(formContainer, gbc);
        return panel;
    }

    private void handleLogin() {
        String noKartu = txtNoKartu.getText().trim();
        String pin = new String(txtPin.getPassword());

        if (noKartu.isEmpty() || pin.isEmpty()) {
            showError("Nomor kartu dan PIN harus diisi");
            return;
        }

        btnLogin.setEnabled(false);
        btnLogin.setText("Memproses...");

        SwingWorker<Card, Void> worker = new SwingWorker<Card, Void>() {
            @Override
            protected Card doInBackground() {
                return authController.login(noKartu, pin);
            }

            @Override
            protected void done() {
                try {
                    Card card = get();
                    btnLogin.setEnabled(true);
                    btnLogin.setText("Masuk");

                    if (card == null) {
                        showError("Login gagal! Nomor kartu atau PIN salah");
                        return;
                    }

                    if ("BLOKIR".equals(card.getStatus())) {
                        showError("Kartu Anda terblokir!\nHubungi customer service");
                        return;
                    }

                    if ("EXPIRED".equals(card.getStatus())) {
                        showError("Kartu Anda sudah expired!");
                        return;
                    }

                    Account account = accountController.getAccountById(card.getAccountId());
                    DashboardView dashboard = new DashboardView(card, account);
                    dashboard.setVisible(true);
                    dispose();
                } catch (Exception e) {
                    btnLogin.setEnabled(true);
                    btnLogin.setText("Masuk");
                    showError("Terjadi kesalahan: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void handleRegister() {
        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
        dispose();
    }

    private void handleCreateCardByNIK() {
        CreateCardByNIKView createCardView = new CreateCardByNIKView();
        createCardView.setVisible(true);
        dispose();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
