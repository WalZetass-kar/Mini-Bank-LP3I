package view;

import controller.CardController;
import model.Card;
import model.Account;
import utils.UIConstants;
import utils.UIComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardInfoView extends JPanel {
    private Card card;
    private Account account;
    private CardController cardController;
    private DashboardView parentDashboard;

    public CardInfoView(Card card, Account account, DashboardView parentDashboard) {
        this.card = card;
        this.account = account;
        this.parentDashboard = parentDashboard;
        this.cardController = new CardController();
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
        container.setMaximumSize(new Dimension(650, 900));

        // Header - NO EMOJI
        JLabel lblTitle = new JLabel("Informasi Kartu ATM");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Detail kartu ATM Anda");
        lblSubtitle.setFont(UIConstants.FONT_BODY);
        lblSubtitle.setForeground(UIConstants.TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Card Visual
        JPanel cardVisual = createCardVisual();
        cardVisual.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Info Card
        JPanel infoCard = new JPanel();
        infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS));
        infoCard.setBackground(UIConstants.BG_CARD);
        infoCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        infoCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        addInfoRow(infoCard, "Nomor Kartu", formatCardNumber(card.getNoKartu()));
        addInfoRow(infoCard, "No. Rekening", account.getNoRekening());
        addInfoRow(infoCard, "Status", card.getStatus());
        addInfoRow(infoCard, "Masa Berlaku", card.getExpiredDate());

        // Back button
        JButton btnBack = UIComponents.createSecondaryButton("Kembali ke Dashboard");
        btnBack.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> handleBack());

        container.add(lblTitle);
        container.add(Box.createVerticalStrut(3));
        container.add(lblSubtitle);
        container.add(Box.createVerticalStrut(15));
        container.add(cardVisual);
        container.add(Box.createVerticalStrut(15));
        container.add(infoCard);
        container.add(Box.createVerticalStrut(15));
        container.add(btnBack);

        mainPanel.add(container, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createCardVisual() {
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(13, 71, 161),
                    getWidth(), getHeight(), new Color(41, 128, 185)
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setOpaque(false);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(30, 35, 30, 35));
        cardPanel.setPreferredSize(new Dimension(550, 200));
        cardPanel.setMaximumSize(new Dimension(550, 200));

        JLabel lblBankName = new JLabel("BANK LP3I PEKANBARU");
        lblBankName.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 18));
        lblBankName.setForeground(Color.WHITE);
        lblBankName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblCardType = new JLabel("ATM CARD");
        lblCardType.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 12));
        lblCardType.setForeground(new Color(255, 255, 255, 180));
        lblCardType.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblCardNumber = new JLabel(formatCardNumber(card.getNoKartu()));
        lblCardNumber.setFont(new Font("Consolas", Font.BOLD, 22));
        lblCardNumber.setForeground(Color.WHITE);
        lblCardNumber.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottomPanel.setOpaque(false);

        JPanel validPanel = new JPanel();
        validPanel.setLayout(new BoxLayout(validPanel, BoxLayout.Y_AXIS));
        validPanel.setOpaque(false);

        JLabel lblValidLabel = new JLabel("VALID THRU");
        lblValidLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 9));
        lblValidLabel.setForeground(new Color(255, 255, 255, 150));

        JLabel lblValidDate = new JLabel(card.getExpiredDate());
        lblValidDate.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 13));
        lblValidDate.setForeground(Color.WHITE);

        validPanel.add(lblValidLabel);
        validPanel.add(lblValidDate);

        bottomPanel.add(validPanel);

        cardPanel.add(lblBankName);
        cardPanel.add(Box.createVerticalStrut(3));
        cardPanel.add(lblCardType);
        cardPanel.add(Box.createVerticalGlue());
        cardPanel.add(lblCardNumber);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(bottomPanel);

        return cardPanel;
    }

    private void addInfoRow(JPanel panel, String label, String value) {
        JPanel row = new JPanel(new BorderLayout(20, 0));
        row.setBackground(UIConstants.BG_CARD);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(UIConstants.FONT_BODY);
        lblLabel.setForeground(UIConstants.TEXT_MUTED);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(UIConstants.FONT_BODY_BOLD);
        lblValue.setForeground(UIConstants.TEXT_PRIMARY);

        // Color status
        if ("Status".equals(label)) {
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
        panel.add(Box.createVerticalStrut(15));
    }

    private String formatCardNumber(String cardNumber) {
        if (cardNumber.length() == 16) {
            return cardNumber.substring(0, 4) + " " +
                   cardNumber.substring(4, 8) + " " +
                   cardNumber.substring(8, 12) + " " +
                   cardNumber.substring(12, 16);
        }
        return cardNumber;
    }

    private void handleBack() {
        if (parentDashboard != null) {
            parentDashboard.showDashboard();
        }
    }
}
