package view;

import controller.SettingsController;
import model.Card;
import model.Account;
import utils.UIConstants;
import utils.UIComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangePinView extends JPanel {
    private Card card;
    private Account account;
    private JPasswordField txtOldPin, txtNewPin, txtConfirmPin;
    private JButton btnChange, btnBack;
    private SettingsController settingsController;
    private DashboardView parentDashboard;

    public ChangePinView(Card card, Account account, DashboardView parentDashboard) {
        this.card = card;
        this.account = account;
        this.parentDashboard = parentDashboard;
        settingsController = new SettingsController();
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
        container.setMaximumSize(new Dimension(600, 900));

        // Header - NO EMOJI
        JLabel lblTitle = new JLabel("Ubah PIN");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Ganti PIN kartu ATM Anda");
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
        JPanel infoPanel = UIComponents.createWarningPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfo = new JLabel("Penting:");
        lblInfo.setFont(UIConstants.FONT_BODY_BOLD);
        lblInfo.setForeground(UIConstants.WARNING);
        lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfo1 = new JLabel("• PIN harus 6 digit angka");
        lblInfo1.setFont(UIConstants.FONT_SMALL);
        lblInfo1.setForeground(UIConstants.TEXT_PRIMARY);
        lblInfo1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblInfo2 = new JLabel("• Jangan berikan PIN kepada siapapun");
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

        // Old PIN
        JLabel lblOldPin = new JLabel("PIN Lama");
        lblOldPin.setFont(UIConstants.FONT_BODY_BOLD);
        lblOldPin.setForeground(UIConstants.TEXT_PRIMARY);
        lblOldPin.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtOldPin = UIComponents.createPasswordField();
        txtOldPin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtOldPin.setAlignmentX(Component.LEFT_ALIGNMENT);

        formCard.add(lblOldPin);
        formCard.add(Box.createVerticalStrut(8));
        formCard.add(txtOldPin);
        formCard.add(Box.createVerticalStrut(20));

        // New PIN
        JLabel lblNewPin = new JLabel("PIN Baru");
        lblNewPin.setFont(UIConstants.FONT_BODY_BOLD);
        lblNewPin.setForeground(UIConstants.TEXT_PRIMARY);
        lblNewPin.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtNewPin = UIComponents.createPasswordField();
        txtNewPin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtNewPin.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblHint1 = new JLabel("Minimal 6 digit angka");
        lblHint1.setFont(UIConstants.FONT_SMALL);
        lblHint1.setForeground(UIConstants.TEXT_MUTED);
        lblHint1.setAlignmentX(Component.LEFT_ALIGNMENT);

        formCard.add(lblNewPin);
        formCard.add(Box.createVerticalStrut(8));
        formCard.add(txtNewPin);
        formCard.add(Box.createVerticalStrut(5));
        formCard.add(lblHint1);
        formCard.add(Box.createVerticalStrut(20));

        // Confirm PIN
        JLabel lblConfirmPin = new JLabel("Konfirmasi PIN Baru");
        lblConfirmPin.setFont(UIConstants.FONT_BODY_BOLD);
        lblConfirmPin.setForeground(UIConstants.TEXT_PRIMARY);
        lblConfirmPin.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtConfirmPin = UIComponents.createPasswordField();
        txtConfirmPin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtConfirmPin.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblHint2 = new JLabel("Masukkan PIN baru sekali lagi");
        lblHint2.setFont(UIConstants.FONT_SMALL);
        lblHint2.setForeground(UIConstants.TEXT_MUTED);
        lblHint2.setAlignmentX(Component.LEFT_ALIGNMENT);

        formCard.add(lblConfirmPin);
        formCard.add(Box.createVerticalStrut(8));
        formCard.add(txtConfirmPin);
        formCard.add(Box.createVerticalStrut(5));
        formCard.add(lblHint2);
        formCard.add(Box.createVerticalStrut(30));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 15, 0));
        buttonPanel.setBackground(UIConstants.BG_CARD);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnChange = UIComponents.createPrimaryButton("Ubah PIN");
        btnChange.addActionListener(e -> handleChangePin());

        btnBack = UIComponents.createSecondaryButton("Batal");
        btnBack.addActionListener(e -> handleBack());

        buttonPanel.add(btnChange);
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

    private void handleChangePin() {
        String oldPin = new String(txtOldPin.getPassword());
        String newPin = new String(txtNewPin.getPassword());
        String confirmPin = new String(txtConfirmPin.getPassword());

        if (oldPin.isEmpty() || newPin.isEmpty() || confirmPin.isEmpty()) {
            JOptionPane.showMessageDialog(parentDashboard, "Semua field harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(parentDashboard,
            "Apakah Anda yakin ingin mengubah PIN?\n\nPIN lama tidak dapat dikembalikan.",
            "Konfirmasi Ubah PIN",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        btnChange.setEnabled(false);
        btnChange.setText("Memproses...");

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                return settingsController.changePin(card.getId(), oldPin, newPin, confirmPin);
            }

            @Override
            protected void done() {
                try {
                    String result = get();
                    btnChange.setEnabled(true);
                    btnChange.setText("Ubah PIN");

                    if ("SUCCESS".equals(result)) {
                        JOptionPane.showMessageDialog(parentDashboard,
                            "PIN berhasil diubah!\n\nSilakan gunakan PIN baru Anda untuk login selanjutnya.",
                            "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);

                        card.setPin(newPin);
                        handleBack();
                    } else {
                        JOptionPane.showMessageDialog(parentDashboard, result, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    btnChange.setEnabled(true);
                    btnChange.setText("Ubah PIN");
                    JOptionPane.showMessageDialog(parentDashboard, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void handleBack() {
        if (parentDashboard != null) {
            parentDashboard.showSettingsView();
        }
    }
}
