package view;

import controller.AuthController;
import utils.UIConstants;
import utils.UIComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Modern Register View - Dark Blue Theme
 */
public class RegisterView extends JFrame {
    private JTextField txtNama, txtNik, txtTanggalLahir, txtNoHp;
    private JTextArea txtAlamat;
    private JButton btnRegister, btnBack;
    private AuthController authController;

    public RegisterView() {
        authController = new AuthController();
        initComponents();
    }

    private void initComponents() {
        setTitle("BANK LP3I PEKANBARU - Registrasi");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(1000, 750));

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.BG_DARK);

        // Left Panel - Info
        JPanel leftPanel = createInfoPanel();
        
        // Right Panel - Form
        JPanel rightPanel = createFormPanel();

        // Split pane
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

        JLabel lblIcon = new JLabel("📝");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 100));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitle = new JLabel("Buat Akun Baru");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 38));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Bergabung dengan Bank LP3I");
        lblSubtitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 16));
        lblSubtitle.setForeground(UIConstants.TEXT_SECONDARY);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Benefits
        JPanel benefits = new JPanel();
        benefits.setLayout(new BoxLayout(benefits, BoxLayout.Y_AXIS));
        benefits.setOpaque(false);
        benefits.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        String[][] benefitList = {
            {"✓", "Saldo awal Rp 50.000"},
            {"✓", "Kartu ATM gratis"},
            {"✓", "Transaksi mudah & cepat"},
            {"✓", "Keamanan terjamin"}
        };

        for (String[] benefit : benefitList) {
            JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            item.setOpaque(false);
            
            JLabel check = new JLabel(benefit[0]);
            check.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 20));
            check.setForeground(UIConstants.SUCCESS);
            
            JLabel text = new JLabel(benefit[1]);
            text.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 15));
            text.setForeground(UIConstants.TEXT_SECONDARY);
            
            item.add(check);
            item.add(text);
            benefits.add(item);
        }

        content.add(lblIcon);
        content.add(Box.createVerticalStrut(20));
        content.add(lblTitle);
        content.add(Box.createVerticalStrut(10));
        content.add(lblSubtitle);
        content.add(benefits);

        panel.add(content, gbc);
        return panel;
    }

    private JPanel createFormPanel() {
        // DEBUG: Print untuk memastikan method dipanggil
        System.out.println("DEBUG: createFormPanel() called");
        
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.BG_DARK);
        panel.setLayout(new BorderLayout());  // PERBAIKAN: Gunakan BorderLayout yang lebih stabil
        
        // DEBUG: Set background berbeda untuk testing
        panel.setBackground(new Color(15, 23, 42));  // BG_DARK
        
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setOpaque(false);
        formContainer.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));  // Padding

        // Title
        JLabel lblTitle = new JLabel("Lengkapi Data Diri");
        lblTitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        lblTitle.setForeground(UIConstants.TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Isi formulir di bawah untuk membuat akun");
        lblSubtitle.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 14));
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
        formCard.setMaximumSize(new Dimension(600, 2000));  // Set max width

        // Nama Lengkap
        addFormField(formCard, "Nama Lengkap", "Sesuai KTP");
        txtNama = UIComponents.createTextField();
        txtNama.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtNama.setAlignmentX(Component.LEFT_ALIGNMENT);
        formCard.add(txtNama);
        formCard.add(Box.createVerticalStrut(20));

        // NIK
        addFormField(formCard, "NIK", "16 digit nomor induk kependudukan");
        txtNik = UIComponents.createTextField();
        txtNik.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtNik.setAlignmentX(Component.LEFT_ALIGNMENT);
        formCard.add(txtNik);
        formCard.add(Box.createVerticalStrut(20));

        // Tanggal Lahir
        addFormField(formCard, "Tanggal Lahir", "Format: YYYY-MM-DD (contoh: 1990-01-15)");
        txtTanggalLahir = UIComponents.createTextField();
        txtTanggalLahir.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtTanggalLahir.setAlignmentX(Component.LEFT_ALIGNMENT);
        formCard.add(txtTanggalLahir);
        formCard.add(Box.createVerticalStrut(20));

        // Alamat
        addFormField(formCard, "Alamat Lengkap", "Sesuai KTP");
        txtAlamat = UIComponents.createTextArea(3);
        JScrollPane scrollAlamat = new JScrollPane(txtAlamat);
        scrollAlamat.setBorder(BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1));
        scrollAlamat.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        scrollAlamat.setAlignmentX(Component.LEFT_ALIGNMENT);
        formCard.add(scrollAlamat);
        formCard.add(Box.createVerticalStrut(20));

        // No HP
        addFormField(formCard, "Nomor HP", "10-13 digit");
        txtNoHp = UIComponents.createTextField();
        txtNoHp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtNoHp.setAlignmentX(Component.LEFT_ALIGNMENT);
        formCard.add(txtNoHp);
        formCard.add(Box.createVerticalStrut(30));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 15, 0));
        buttonPanel.setBackground(UIConstants.BG_CARD);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnRegister = UIComponents.createSuccessButton("Daftar Sekarang");
        btnRegister.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 15));
        btnRegister.addActionListener(e -> handleRegister());

        btnBack = UIComponents.createSecondaryButton("Kembali");
        btnBack.addActionListener(e -> handleBack());

        buttonPanel.add(btnRegister);
        buttonPanel.add(btnBack);

        formCard.add(buttonPanel);

        // Add components to formContainer
        formContainer.add(lblTitle);
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(lblSubtitle);
        formContainer.add(Box.createVerticalStrut(30));
        formContainer.add(formCard);
        formContainer.add(Box.createVerticalGlue());  // Push content to top

        // PERBAIKAN KRITIS: Wrap in scroll pane dan ADD ke panel utama
        JScrollPane scrollPane = new JScrollPane(formContainer);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // DEBUG: Print ukuran panel
        System.out.println("DEBUG: formContainer size = " + formContainer.getPreferredSize());
        System.out.println("DEBUG: panel size = " + panel.getPreferredSize());

        // ✅ INI YANG PENTING: ADD scrollPane ke panel utama!
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // DEBUG: Pastikan panel visible
        panel.setVisible(true);
        System.out.println("DEBUG: Panel visible = " + panel.isVisible());
        
        return panel;
    }

    private void addFormField(JPanel panel, String label, String hint) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(UIConstants.FONT_BODY_BOLD);
        lbl.setForeground(UIConstants.TEXT_PRIMARY);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(5));

        JLabel lblHint = new JLabel(hint);
        lblHint.setFont(UIConstants.FONT_SMALL);
        lblHint.setForeground(UIConstants.TEXT_MUTED);
        lblHint.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblHint);
        panel.add(Box.createVerticalStrut(8));
    }

    private void handleRegister() {
        String nama = txtNama.getText().trim();
        String nik = txtNik.getText().trim();
        String tanggalLahir = txtTanggalLahir.getText().trim();
        String alamat = txtAlamat.getText().trim();
        String noHp = txtNoHp.getText().trim();

        btnRegister.setEnabled(false);
        btnRegister.setText("Memproses...");

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                return authController.register(nama, nik, tanggalLahir, alamat, noHp);
            }

            @Override
            protected void done() {
                try {
                    String result = get();
                    btnRegister.setEnabled(true);
                    btnRegister.setText("Daftar Sekarang");

                    if ("SUCCESS".equals(result)) {
                        JOptionPane.showMessageDialog(RegisterView.this,
                            "✅ Registrasi Berhasil!\n\n" +
                            "Rekening Anda telah dibuat dengan saldo awal Rp 50.000\n" +
                            "Silakan buat kartu ATM untuk melanjutkan.",
                            "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                        handleBack();
                    } else {
                        JOptionPane.showMessageDialog(RegisterView.this,
                            result,
                            "Registrasi Gagal",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    btnRegister.setEnabled(true);
                    btnRegister.setText("Daftar Sekarang");
                    JOptionPane.showMessageDialog(RegisterView.this,
                        "Terjadi kesalahan: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void handleBack() {
        new LoginView().setVisible(true);
        dispose();
    }
}
