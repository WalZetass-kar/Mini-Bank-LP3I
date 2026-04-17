package view;

import controller.AccountController;
import model.Card;
import model.Account;
import model.User;
import repository.UserRepository;
import utils.UIConstants;
import utils.UIComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsView extends JPanel {
    private Card card;
    private Account account;
    private AccountController accountController;
    private UserRepository userRepository;
    private DashboardView parentDashboard;

    public SettingsView(Card card, Account account, DashboardView parentDashboard) {
        this.card = card;
        this.account = account;
        this.parentDashboard = parentDashboard;
        accountController = new AccountController();
        userRepository = new UserRepository();
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
        container.setMaximumSize(new Dimension(600, 800));

        // Header - NO EMOJI
        JLabel lblTitle = new JLabel("Pengaturan");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Kelola akun dan keamanan Anda");
        lblSubtitle.setFont(UIConstants.FONT_BODY);
        lblSubtitle.setForeground(UIConstants.TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menu Card
        JPanel menuCard = new JPanel();
        menuCard.setLayout(new BoxLayout(menuCard, BoxLayout.Y_AXIS));
        menuCard.setBackground(UIConstants.BG_CARD);
        menuCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        menuCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Change PIN
        JPanel changePinBtn = createMenuOption("Ubah PIN", "Ganti PIN kartu ATM Anda");
        changePinBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                handleChangePin();
            }
        });
        menuCard.add(changePinBtn);
        menuCard.add(Box.createVerticalStrut(10));

        // Account Info
        JPanel accountInfoBtn = createMenuOption("Informasi Akun", "Lihat detail akun Anda");
        accountInfoBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                handleAccountInfo();
            }
        });
        menuCard.add(accountInfoBtn);

        // Back button
        JButton btnBack = UIComponents.createSecondaryButton("Kembali ke Dashboard");
        btnBack.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> handleBack());

        container.add(lblTitle);
        container.add(Box.createVerticalStrut(3));
        container.add(lblSubtitle);
        container.add(Box.createVerticalStrut(15));
        container.add(menuCard);
        container.add(Box.createVerticalStrut(15));
        container.add(btnBack);

        mainPanel.add(container, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createMenuOption(String title, String subtitle) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        panel.setBackground(UIConstants.BG_CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(UIConstants.FONT_SUBTITLE_BOLD);
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(UIConstants.FONT_SMALL);
        lblSubtitle.setForeground(UIConstants.TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(lblTitle);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(lblSubtitle);

        JLabel lblArrow = new JLabel("→");
        lblArrow.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 24));
        lblArrow.setForeground(UIConstants.TEXT_MUTED);

        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(lblArrow, BorderLayout.EAST);

        panel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(UIConstants.BG_LIGHT);
                panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UIConstants.PRIMARY_ACCENT, 2),
                    BorderFactory.createEmptyBorder(19, 19, 19, 19)
                ));
            }

            public void mouseExited(MouseEvent e) {
                panel.setBackground(UIConstants.BG_CARD);
                panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
        });

        return panel;
    }

    private void handleChangePin() {
        if (parentDashboard != null) {
            parentDashboard.showChangePinView();
        }
    }

    private void handleAccountInfo() {
        User user = userRepository.getUserById(account.getUserId());

        if (user != null) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(UIConstants.BG_CARD);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel lblTitle = new JLabel("Informasi Akun");
            lblTitle.setFont(UIConstants.FONT_TITLE_BOLD);
            lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
            lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

            panel.add(lblTitle);
            panel.add(Box.createVerticalStrut(20));

            addInfoRow(panel, "Nama", user.getNama());
            addInfoRow(panel, "NIK", user.getNik());
            addInfoRow(panel, "Tanggal Lahir", user.getTanggalLahir());
            addInfoRow(panel, "Alamat", user.getAlamat());
            addInfoRow(panel, "No HP", user.getNoHp());
            addInfoRow(panel, "No. Rekening", account.getNoRekening());
            addInfoRow(panel, "Saldo", "Rp " + String.format("%,d", account.getSaldo()));
            addInfoRow(panel, "No. Kartu", card.getNoKartu());
            addInfoRow(panel, "Status Kartu", card.getStatus());

            JOptionPane.showMessageDialog(parentDashboard, panel, "Informasi Akun", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parentDashboard, "Data user tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addInfoRow(JPanel panel, String label, String value) {
        JPanel row = new JPanel(new BorderLayout(15, 0));
        row.setBackground(UIConstants.BG_CARD);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLabel = new JLabel(label + ":");
        lblLabel.setFont(UIConstants.FONT_BODY);
        lblLabel.setForeground(UIConstants.TEXT_MUTED);
        lblLabel.setPreferredSize(new Dimension(120, 25));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(UIConstants.FONT_BODY_BOLD);
        lblValue.setForeground(UIConstants.TEXT_PRIMARY);

        row.add(lblLabel, BorderLayout.WEST);
        row.add(lblValue, BorderLayout.CENTER);

        panel.add(row);
        panel.add(Box.createVerticalStrut(10));
    }

    private void handleBack() {
        if (parentDashboard != null) {
            parentDashboard.showDashboard();
        }
    }
}
