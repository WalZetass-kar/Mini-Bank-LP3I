package view;

import controller.AccountController;
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

/**
 * Modern Dashboard with Collapsible Sidebar - Single Page Application Style
 * All features displayed in content area without opening new windows
 */
public class DashboardView extends JFrame {
    private Card card;
    private Account account;
    private AccountController accountController;
    private TransactionController transactionController;
    private JPanel sidebar;
    private JPanel contentPanel;
    private JLabel headerTitle;
    private boolean sidebarCollapsed = false;
    private static final int SIDEBAR_EXPANDED = 260;
    private static final int SIDEBAR_COLLAPSED = 70;
    private String currentView = "dashboard";

    public DashboardView(Card card, Account account) {
        this.card = card;
        this.account = account;
        this.accountController = new AccountController();
        this.transactionController = new TransactionController();
        initComponents();
    }

    private void initComponents() {
        setTitle("BANK LP3I PEKANBARU - Dashboard");
        setSize(1400, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(1200, 750));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.BG_DARK);

        // Sidebar
        sidebar = createSidebar();
        mainPanel.add(sidebar, BorderLayout.WEST);

        // Right panel (header + content)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(UIConstants.BG_DARK);

        // Header
        JPanel header = createHeader();
        rightPanel.add(header, BorderLayout.NORTH);

        // Content (will be replaced dynamically)
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(UIConstants.BG_DARK);
        showDashboardContent();

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(UIConstants.BG_DARK);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(rightPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JPanel createSidebar() {
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setBackground(UIConstants.BG_MEDIUM);
        sidebarPanel.setPreferredSize(new Dimension(SIDEBAR_EXPANDED, getHeight()));
        sidebarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, UIConstants.BORDER_DARK));

        // Top section - MINIMAL & CLEAN
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(UIConstants.BG_MEDIUM);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        // Logo section - TEXT ONLY, NO EMOJI
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setBackground(UIConstants.BG_MEDIUM);

        JLabel lblBankName = new JLabel("BANK LP3I");
        lblBankName.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 16));
        lblBankName.setForeground(UIConstants.TEXT_PRIMARY);
        lblBankName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblLocation = new JLabel("PEKANBARU");
        lblLocation.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 11));
        lblLocation.setForeground(UIConstants.PRIMARY_HOVER);
        lblLocation.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoPanel.add(lblBankName);
        logoPanel.add(Box.createVerticalStrut(3));
        logoPanel.add(lblLocation);

        // Toggle button
        JButton btnToggle = new JButton("☰");
        btnToggle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 16));
        btnToggle.setBackground(UIConstants.BG_LIGHT);
        btnToggle.setForeground(UIConstants.TEXT_PRIMARY);
        btnToggle.setFocusPainted(false);
        btnToggle.setBorderPainted(false);
        btnToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToggle.setPreferredSize(new Dimension(30, 30));
        btnToggle.addActionListener(e -> toggleSidebar(lblBankName, lblLocation));

        topPanel.add(logoPanel, BorderLayout.CENTER);
        topPanel.add(btnToggle, BorderLayout.EAST);

        // Menu section - CLEAN, NO ICONS
        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(new BoxLayout(menuContainer, BoxLayout.Y_AXIS));
        menuContainer.setBackground(UIConstants.BG_MEDIUM);
        menuContainer.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        String[][] menuItems = {
            {"Dashboard", "dashboard"},
            {"Cek Saldo", "balance"},
            {"Tarik Uang", "withdraw"},
            {"Setor Uang", "deposit"},
            {"Transfer", "transfer"},
            {"Riwayat", "history"},
            {"Info Kartu", "card"},
            {"Pengaturan", "settings"},
            {"Keluar", "logout"}
        };

        for (String[] item : menuItems) {
            JButton btn = createMenuButton(item[0], item[1]);
            menuContainer.add(btn);
            menuContainer.add(Box.createVerticalStrut(2));
        }

        JScrollPane menuScroll = new JScrollPane(menuContainer);
        menuScroll.setBorder(null);
        menuScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        menuScroll.getVerticalScrollBar().setUnitIncrement(16);
        menuScroll.getVerticalScrollBar().setBlockIncrement(50);
        menuScroll.getViewport().setBackground(UIConstants.BG_MEDIUM);
        
        // Style scrollbar
        menuScroll.getVerticalScrollBar().setBackground(UIConstants.BG_MEDIUM);
        menuScroll.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = UIConstants.BG_LIGHT;
                this.trackColor = UIConstants.BG_MEDIUM;
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });

        sidebarPanel.add(topPanel, BorderLayout.NORTH);
        sidebarPanel.add(menuScroll, BorderLayout.CENTER);

        return sidebarPanel;
    }

    private void toggleSidebar(JLabel lblBankName, JLabel lblLocation) {
        sidebarCollapsed = !sidebarCollapsed;
        int newWidth = sidebarCollapsed ? SIDEBAR_COLLAPSED : SIDEBAR_EXPANDED;
        
        sidebar.setPreferredSize(new Dimension(newWidth, sidebar.getHeight()));
        lblBankName.setVisible(!sidebarCollapsed);
        lblLocation.setVisible(!sidebarCollapsed);
        
        // Update menu buttons
        try {
            JScrollPane scrollPane = (JScrollPane)sidebar.getComponent(1);
            JPanel menuContainer = (JPanel)scrollPane.getViewport().getView();
            Component[] components = menuContainer.getComponents();
            for (Component comp : components) {
                if (comp instanceof JButton) {
                    JButton btn = (JButton) comp;
                    if (sidebarCollapsed) {
                        btn.setText("");
                        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    } else {
                        // Restore text based on button index
                        String[][] menuItems = {
                            {"Dashboard", "dashboard"},
                            {"Cek Saldo", "balance"},
                            {"Tarik Uang", "withdraw"},
                            {"Setor Uang", "deposit"},
                            {"Transfer", "transfer"},
                            {"Riwayat", "history"},
                            {"Info Kartu", "card"},
                            {"Pengaturan", "settings"},
                            {"Keluar", "logout"}
                        };
                        int btnIndex = 0;
                        for (Component c : menuContainer.getComponents()) {
                            if (c == btn && btnIndex < menuItems.length) {
                                btn.setText(menuItems[btnIndex][0]);
                                break;
                            }
                            if (c instanceof JButton) btnIndex++;
                        }
                        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        sidebar.revalidate();
        sidebar.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private JButton createMenuButton(String text, String action) {
        JButton btn = new JButton(text);
        btn.setForeground(UIConstants.TEXT_SECONDARY);
        btn.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btn.setPreferredSize(new Dimension(SIDEBAR_EXPANDED - 20, 36));

        // Active menu gets CARD style
        if (action.equals(currentView)) {
            btn.setBackground(UIConstants.PRIMARY_ACCENT);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 13));
            btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIConstants.PRIMARY_HOVER, 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
            ));
        } else {
            btn.setBackground(UIConstants.BG_MEDIUM);
            btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        }

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!action.equals(currentView)) {
                    btn.setBackground(UIConstants.BG_LIGHT);
                    btn.setForeground(UIConstants.TEXT_PRIMARY);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (!action.equals(currentView)) {
                    btn.setBackground(UIConstants.BG_MEDIUM);
                    btn.setForeground(UIConstants.TEXT_SECONDARY);
                } else {
                    btn.setBackground(UIConstants.PRIMARY_ACCENT);
                    btn.setForeground(Color.WHITE);
                }
            }
        });

        btn.addActionListener(e -> {
            currentView = action;
            updateActiveMenu();
            handleMenuClick(action);
        });

        return btn;
    }

    private void updateActiveMenu() {
        try {
            JScrollPane scrollPane = (JScrollPane)sidebar.getComponent(1);
            JPanel menuContainer = (JPanel)scrollPane.getViewport().getView();
            Component[] components = menuContainer.getComponents();
            
            String[][] menuItems = {
                {"Dashboard", "dashboard"},
                {"Cek Saldo", "balance"},
                {"Tarik Uang", "withdraw"},
                {"Setor Uang", "deposit"},
                {"Transfer", "transfer"},
                {"Riwayat", "history"},
                {"Info Kartu", "card"},
                {"Pengaturan", "settings"},
                {"Keluar", "logout"}
            };
            
            int menuIndex = 0;
            for (Component comp : components) {
                if (comp instanceof JButton && menuIndex < menuItems.length) {
                    JButton btn = (JButton) comp;
                    String action = menuItems[menuIndex][1];
                    
                    if (action.equals(currentView)) {
                        // Active menu - CARD STYLE
                        btn.setBackground(UIConstants.PRIMARY_ACCENT);
                        btn.setForeground(Color.WHITE);
                        btn.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 13));
                        btn.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(UIConstants.PRIMARY_HOVER, 1),
                            BorderFactory.createEmptyBorder(8, 15, 8, 15)
                        ));
                    } else {
                        // Inactive menu - FLAT
                        btn.setBackground(UIConstants.BG_MEDIUM);
                        btn.setForeground(UIConstants.TEXT_SECONDARY);
                        btn.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 13));
                        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
                    }
                    menuIndex++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIConstants.BG_CARD);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, UIConstants.BORDER_DARK),
            BorderFactory.createEmptyBorder(20, 35, 20, 35)
        ));

        headerTitle = new JLabel("Dashboard");
        headerTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 26));
        headerTitle.setForeground(UIConstants.TEXT_PRIMARY);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightPanel.setOpaque(false);

        JLabel lblUser = new JLabel("Rek: " + account.getNoRekening());
        lblUser.setFont(UIConstants.FONT_BODY);
        lblUser.setForeground(UIConstants.TEXT_SECONDARY);

        rightPanel.add(lblUser);

        header.add(headerTitle, BorderLayout.WEST);
        header.add(rightPanel, BorderLayout.EAST);

        return header;
    }

    private void handleMenuClick(String action) {
        switch (action) {
            case "dashboard":
                headerTitle.setText("Dashboard");
                showDashboardContent();
                break;
            case "balance":
                headerTitle.setText("Cek Saldo");
                showBalanceContent();
                break;
            case "withdraw":
                headerTitle.setText("Tarik Uang");
                showWithdrawContent();
                break;
            case "deposit":
                headerTitle.setText("Setor Uang");
                showDepositContent();
                break;
            case "transfer":
                headerTitle.setText("Transfer");
                showTransferContent();
                break;
            case "history":
                headerTitle.setText("Riwayat Transaksi");
                showHistoryContent();
                break;
            case "card":
                headerTitle.setText("Info Kartu");
                showCardInfoContent();
                break;
            case "settings":
                headerTitle.setText("Pengaturan");
                showSettingsContent();
                break;
            case "logout":
                handleLogout();
                break;
        }
    }

    private void showDashboardContent() {
        contentPanel.removeAll();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIConstants.BG_DARK);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        // Refresh balance
        Account updatedAccount = accountController.getAccountById(account.getId());
        if (updatedAccount != null) {
            account = updatedAccount;
        }

        // Balance Card
        JPanel balanceCard = createBalanceCard();
        balanceCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(balanceCard);
        panel.add(Box.createVerticalStrut(25));

        // Account Info Card
        JPanel infoCard = new JPanel();
        infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS));
        infoCard.setBackground(UIConstants.BG_CARD);
        infoCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        infoCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        infoCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfoTitle = new JLabel("Informasi Akun");
        lblInfoTitle.setFont(UIConstants.FONT_TITLE_BOLD);
        lblInfoTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblInfoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(lblInfoTitle);
        panel.add(Box.createVerticalStrut(15));

        addInfoRow(infoCard, "Nomor Rekening", account.getNoRekening());
        addInfoRow(infoCard, "Status Kartu", card.getStatus());
        addInfoRow(infoCard, "Masa Berlaku", card.getExpiredDate());

        panel.add(infoCard);
        panel.add(Box.createVerticalStrut(25));

        // About App Card
        JPanel aboutCard = new JPanel();
        aboutCard.setLayout(new BoxLayout(aboutCard, BoxLayout.Y_AXIS));
        aboutCard.setBackground(UIConstants.BG_CARD);
        aboutCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        aboutCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        aboutCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAboutTitle = new JLabel("Tentang Aplikasi");
        lblAboutTitle.setFont(UIConstants.FONT_TITLE_BOLD);
        lblAboutTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblAboutTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAppName = new JLabel("BANK LP3I PEKANBARU");
        lblAppName.setFont(UIConstants.FONT_SUBTITLE_BOLD);
        lblAppName.setForeground(UIConstants.PRIMARY_ACCENT);
        lblAppName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAppDesc = new JLabel("Sistem Perbankan Mini");
        lblAppDesc.setFont(UIConstants.FONT_BODY);
        lblAppDesc.setForeground(UIConstants.TEXT_SECONDARY);
        lblAppDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblVersion = new JLabel("Version 4.0 - Clean UI");
        lblVersion.setFont(UIConstants.FONT_SMALL);
        lblVersion.setForeground(UIConstants.TEXT_MUTED);
        lblVersion.setAlignmentX(Component.LEFT_ALIGNMENT);

        aboutCard.add(lblAboutTitle);
        aboutCard.add(Box.createVerticalStrut(12));
        aboutCard.add(lblAppName);
        aboutCard.add(Box.createVerticalStrut(5));
        aboutCard.add(lblAppDesc);
        aboutCard.add(Box.createVerticalStrut(5));
        aboutCard.add(lblVersion);

        panel.add(aboutCard);

        contentPanel.add(panel, BorderLayout.NORTH);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void addInfoRow(JPanel panel, String label, String value) {
        JPanel row = new JPanel(new BorderLayout(20, 0));
        row.setBackground(UIConstants.BG_CARD);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(UIConstants.FONT_BODY);
        lblLabel.setForeground(UIConstants.TEXT_MUTED);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(UIConstants.FONT_BODY_BOLD);
        lblValue.setForeground(UIConstants.TEXT_PRIMARY);

        // Color status
        if ("Status Kartu".equals(label)) {
            if ("AKTIF".equals(value)) {
                lblValue.setForeground(UIConstants.SUCCESS);
            } else if ("BLOKIR".equals(value)) {
                lblValue.setForeground(UIConstants.DANGER);
            }
        }

        row.add(lblLabel, BorderLayout.WEST);
        row.add(lblValue, BorderLayout.EAST);

        panel.add(row);
        panel.add(Box.createVerticalStrut(10));
    }

    private void showBalanceContent() {
        contentPanel.removeAll();
        
        Account updatedAccount = accountController.getAccountById(account.getId());
        if (updatedAccount != null) {
            account = updatedAccount;
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIConstants.BG_DARK);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 35, 15, 35));

        // Main Balance Card with Gradient
        JPanel balanceCard = createBalanceCard();
        balanceCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(balanceCard);
        panel.add(Box.createVerticalStrut(20));

        // Account Details Card
        JPanel detailsCard = new JPanel();
        detailsCard.setLayout(new BoxLayout(detailsCard, BoxLayout.Y_AXIS));
        detailsCard.setBackground(UIConstants.BG_CARD);
        detailsCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        detailsCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        detailsCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblDetailsTitle = new JLabel("Detail Rekening");
        lblDetailsTitle.setFont(UIConstants.FONT_TITLE_BOLD);
        lblDetailsTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblDetailsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        detailsCard.add(lblDetailsTitle);
        detailsCard.add(Box.createVerticalStrut(15));

        addBalanceInfoRow(detailsCard, "Nomor Rekening", account.getNoRekening());
        addBalanceInfoRow(detailsCard, "Nomor Kartu", formatCardNumber(card.getNoKartu()));
        addBalanceInfoRow(detailsCard, "Status Kartu", card.getStatus());
        addBalanceInfoRow(detailsCard, "Masa Berlaku", card.getExpiredDate());

        panel.add(detailsCard);
        panel.add(Box.createVerticalStrut(20));

        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(41, 128, 185, 30));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.PRIMARY_ACCENT, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfoTitle = new JLabel("Informasi");
        lblInfoTitle.setFont(UIConstants.FONT_BODY_BOLD);
        lblInfoTitle.setForeground(UIConstants.PRIMARY_ACCENT);
        lblInfoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfo1 = new JLabel("• Saldo diperbarui secara real-time");
        lblInfo1.setFont(UIConstants.FONT_SMALL);
        lblInfo1.setForeground(UIConstants.TEXT_PRIMARY);
        lblInfo1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfo2 = new JLabel("• Gunakan menu lain untuk melakukan transaksi");
        lblInfo2.setFont(UIConstants.FONT_SMALL);
        lblInfo2.setForeground(UIConstants.TEXT_PRIMARY);
        lblInfo2.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(lblInfoTitle);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(lblInfo1);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblInfo2);

        panel.add(infoPanel);

        contentPanel.add(panel, BorderLayout.NORTH);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void addBalanceInfoRow(JPanel panel, String label, String value) {
        JPanel row = new JPanel(new BorderLayout(20, 0));
        row.setBackground(UIConstants.BG_CARD);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(UIConstants.FONT_BODY);
        lblLabel.setForeground(UIConstants.TEXT_MUTED);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(UIConstants.FONT_BODY_BOLD);
        lblValue.setForeground(UIConstants.TEXT_PRIMARY);

        // Color status
        if ("Status Kartu".equals(label)) {
            if ("AKTIF".equals(value)) {
                lblValue.setForeground(UIConstants.SUCCESS);
            } else if ("BLOKIR".equals(value)) {
                lblValue.setForeground(UIConstants.DANGER);
            } else if ("EXPIRED".equals(value)) {
                lblValue.setForeground(UIConstants.WARNING);
            }
        }

        row.add(lblLabel, BorderLayout.WEST);
        row.add(lblValue, BorderLayout.EAST);

        panel.add(row);
        panel.add(Box.createVerticalStrut(10));
    }

    private String formatCardNumber(String cardNumber) {
        if (cardNumber != null && cardNumber.length() == 16) {
            return cardNumber.substring(0, 4) + " " +
                   cardNumber.substring(4, 8) + " " +
                   cardNumber.substring(8, 12) + " " +
                   cardNumber.substring(12, 16);
        }
        return cardNumber;
    }

    private void showWithdrawContent() {
        contentPanel.removeAll();
        contentPanel.add(new WithdrawView(card, account, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showDepositContent() {
        contentPanel.removeAll();
        contentPanel.add(new DepositView(card, account, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showTransferContent() {
        contentPanel.removeAll();
        contentPanel.add(new TransferView(card, account, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showHistoryContent() {
        contentPanel.removeAll();
        contentPanel.add(new TransactionHistoryView(card, account, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showCardInfoContent() {
        contentPanel.removeAll();
        contentPanel.add(new CardInfoView(card, account, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showSettingsContent() {
        contentPanel.removeAll();
        contentPanel.add(new SettingsView(card, account, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Public methods for navigation from embedded views
    public void showDashboard() {
        currentView = "dashboard";
        updateActiveMenu();
        headerTitle.setText("Dashboard");
        showDashboardContent();
    }

    public void showChangePinView() {
        currentView = "settings";
        updateActiveMenu();
        headerTitle.setText("Ubah PIN");
        contentPanel.removeAll();
        contentPanel.add(new ChangePinView(card, account, this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void showSettingsView() {
        currentView = "settings";
        updateActiveMenu();
        headerTitle.setText("Pengaturan");
        showSettingsContent();
    }

    private JPanel createBalanceCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(41, 128, 185));
        card.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        JLabel lblTitle = new JLabel("Saldo Tersedia");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 16));
        lblTitle.setForeground(new Color(255, 255, 255, 200));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblBalance = new JLabel("Rp " + String.format("%,d", account.getSaldo()));
        lblBalance.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 42));
        lblBalance.setForeground(Color.WHITE);
        lblBalance.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAccount = new JLabel("No. Rekening: " + account.getNoRekening());
        lblAccount.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 14));
        lblAccount.setForeground(new Color(255, 255, 255, 180));
        lblAccount.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(lblTitle);
        card.add(Box.createVerticalStrut(12));
        card.add(lblBalance);
        card.add(Box.createVerticalStrut(18));
        card.add(lblAccount);

        return card;
    }

    private JPanel createStatCard(String title, String value, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(UIConstants.BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(UIConstants.FONT_SMALL);
        lblTitle.setForeground(UIConstants.TEXT_MUTED);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(UIConstants.FONT_SUBTITLE_BOLD);
        lblValue.setForeground(accentColor);
        lblValue.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(lblTitle);
        card.add(Box.createVerticalStrut(8));
        card.add(lblValue);

        return card;
    }

    private JPanel createQuickActionCard(String title, String subtitle, String action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(UIConstants.BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(25, 20, 25, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(UIConstants.FONT_SUBTITLE_BOLD);
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(UIConstants.FONT_SMALL);
        lblSubtitle.setForeground(UIConstants.TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(lblTitle);
        card.add(Box.createVerticalStrut(8));
        card.add(lblSubtitle);
        card.add(Box.createVerticalGlue());

        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBackground(UIConstants.BG_LIGHT);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UIConstants.PRIMARY_ACCENT, 2),
                    BorderFactory.createEmptyBorder(24, 19, 24, 19)
                ));
            }

            public void mouseExited(MouseEvent e) {
                card.setBackground(UIConstants.BG_CARD);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
                    BorderFactory.createEmptyBorder(25, 20, 25, 20)
                ));
            }

            public void mouseClicked(MouseEvent e) {
                currentView = action;
                updateActiveMenu();
                handleMenuClick(action);
            }
        });

        return card;
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin keluar?",
            "Konfirmasi Logout",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            new LoginView().setVisible(true);
            dispose();
        }
    }
}
