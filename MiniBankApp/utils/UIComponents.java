package utils;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Reusable UI Components
 * Provides consistent styled components across the application
 */
public class UIComponents {
    
    /**
     * Create a styled primary button
     */
    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(UIConstants.FONT_BODY_BOLD);
        button.setBackground(UIConstants.PRIMARY_ACCENT);
        button.setForeground(UIConstants.TEXT_PRIMARY);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(button.getPreferredSize().width, UIConstants.BUTTON_HEIGHT));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(UIConstants.PRIMARY_HOVER);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIConstants.PRIMARY_ACCENT);
            }
        });
        
        return button;
    }
    
    /**
     * Create a styled secondary button
     */
    public static JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(UIConstants.FONT_BODY_BOLD);
        button.setBackground(UIConstants.BG_LIGHT);
        button.setForeground(UIConstants.TEXT_PRIMARY);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(button.getPreferredSize().width, UIConstants.BUTTON_HEIGHT));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(UIConstants.brighten(UIConstants.BG_LIGHT, 1.2f));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIConstants.BG_LIGHT);
            }
        });
        
        return button;
    }
    
    /**
     * Create a styled success button
     */
    public static JButton createSuccessButton(String text) {
        JButton button = new JButton(text);
        button.setFont(UIConstants.FONT_BODY_BOLD);
        button.setBackground(UIConstants.SUCCESS);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(button.getPreferredSize().width, UIConstants.BUTTON_HEIGHT));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(UIConstants.brighten(UIConstants.SUCCESS, 1.1f));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIConstants.SUCCESS);
            }
        });
        
        return button;
    }
    
    /**
     * Create a styled danger button
     */
    public static JButton createDangerButton(String text) {
        JButton button = new JButton(text);
        button.setFont(UIConstants.FONT_BODY_BOLD);
        button.setBackground(UIConstants.DANGER);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(button.getPreferredSize().width, UIConstants.BUTTON_HEIGHT));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(UIConstants.brighten(UIConstants.DANGER, 1.1f));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIConstants.DANGER);
            }
        });
        
        return button;
    }
    
    /**
     * Create a styled text field
     */
    public static JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(UIConstants.FONT_BODY);
        field.setBackground(UIConstants.BG_LIGHT);
        field.setForeground(UIConstants.TEXT_PRIMARY);
        field.setCaretColor(UIConstants.TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, UIConstants.INPUT_HEIGHT));
        
        // Focus effect
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UIConstants.PRIMARY_ACCENT, 2),
                    BorderFactory.createEmptyBorder(9, 11, 9, 11)
                ));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
        });
        
        return field;
    }
    
    /**
     * Create a styled password field
     */
    public static JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(UIConstants.FONT_BODY);
        field.setBackground(UIConstants.BG_LIGHT);
        field.setForeground(UIConstants.TEXT_PRIMARY);
        field.setCaretColor(UIConstants.TEXT_PRIMARY);
        field.setEchoChar('●');
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, UIConstants.INPUT_HEIGHT));
        
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UIConstants.PRIMARY_ACCENT, 2),
                    BorderFactory.createEmptyBorder(9, 11, 9, 11)
                ));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
        });
        
        return field;
    }
    
    /**
     * Create a styled text area
     */
    public static JTextArea createTextArea(int rows) {
        JTextArea area = new JTextArea(rows, 20);
        area.setFont(UIConstants.FONT_BODY);
        area.setBackground(UIConstants.BG_LIGHT);
        area.setForeground(UIConstants.TEXT_PRIMARY);
        area.setCaretColor(UIConstants.TEXT_PRIMARY);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        
        return area;
    }
    
    /**
     * Create a styled label
     */
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConstants.FONT_BODY);
        label.setForeground(UIConstants.TEXT_PRIMARY);
        return label;
    }
    
    /**
     * Create a styled title label
     */
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConstants.FONT_LARGE_BOLD);
        label.setForeground(UIConstants.TEXT_PRIMARY);
        return label;
    }
    
    /**
     * Create a styled subtitle label
     */
    public static JLabel createSubtitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConstants.FONT_SUBTITLE_BOLD);
        label.setForeground(UIConstants.TEXT_SECONDARY);
        return label;
    }
    
    /**
     * Create a styled muted label
     */
    public static JLabel createMutedLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConstants.FONT_SMALL);
        label.setForeground(UIConstants.TEXT_MUTED);
        return label;
    }
    
    /**
     * Create a card panel
     */
    public static JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.BG_CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_DARK, 1),
            BorderFactory.createEmptyBorder(
                UIConstants.SPACING_LARGE,
                UIConstants.SPACING_LARGE,
                UIConstants.SPACING_LARGE,
                UIConstants.SPACING_LARGE
            )
        ));
        return panel;
    }
    
    /**
     * Create an info panel (blue accent)
     */
    public static JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.withAlpha(UIConstants.INFO, 20));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.INFO, 1),
            BorderFactory.createEmptyBorder(
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM
            )
        ));
        return panel;
    }
    
    /**
     * Create a success panel (green accent)
     */
    public static JPanel createSuccessPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.withAlpha(UIConstants.SUCCESS, 20));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.SUCCESS, 1),
            BorderFactory.createEmptyBorder(
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM
            )
        ));
        return panel;
    }
    
    /**
     * Create a warning panel (orange accent)
     */
    public static JPanel createWarningPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.withAlpha(UIConstants.WARNING, 20));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.WARNING, 1),
            BorderFactory.createEmptyBorder(
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM,
                UIConstants.SPACING_MEDIUM
            )
        ));
        return panel;
    }
    
    /**
     * Create a menu button for sidebar
     */
    public static JButton createMenuButton(String text, String icon) {
        JButton button = new JButton(icon + "  " + text);
        button.setFont(UIConstants.FONT_BODY_BOLD);
        button.setBackground(UIConstants.BG_MEDIUM);
        button.setForeground(UIConstants.TEXT_SECONDARY);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(UIConstants.BG_LIGHT);
                button.setForeground(UIConstants.TEXT_PRIMARY);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIConstants.BG_MEDIUM);
                button.setForeground(UIConstants.TEXT_SECONDARY);
            }
        });
        
        return button;
    }
    
    /**
     * Create a styled separator
     */
    public static JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(UIConstants.BORDER_DARK);
        separator.setBackground(UIConstants.BORDER_DARK);
        return separator;
    }
}
