package utils;

import java.awt.*;

/**
 * UI Constants - Centralized design system
 * Theme: Dark Blue Professional Banking
 */
public class UIConstants {
    
    // ==================== COLOR PALETTE ====================
    
    // Primary Colors (Dark Blue Theme)
    public static final Color PRIMARY_DARK = new Color(13, 27, 42);        // #0D1B2A - Very Dark Blue
    public static final Color PRIMARY = new Color(27, 38, 59);             // #1B263B - Dark Blue
    public static final Color PRIMARY_LIGHT = new Color(30, 55, 92);       // #1E375C - Medium Dark Blue
    public static final Color PRIMARY_ACCENT = new Color(41, 128, 185);    // #2980B9 - Bright Blue
    public static final Color PRIMARY_HOVER = new Color(52, 152, 219);     // #3498DB - Light Blue
    
    // Background Colors
    public static final Color BG_DARK = new Color(15, 23, 42);             // #0F172A - Very Dark
    public static final Color BG_MEDIUM = new Color(30, 41, 59);           // #1E293B - Medium Dark
    public static final Color BG_LIGHT = new Color(71, 85, 105);           // #475569 - Light Dark (lebih terang untuk input fields)
    public static final Color BG_CARD = new Color(30, 41, 59);             // #1E293B - Card Background
    
    // Text Colors
    public static final Color TEXT_PRIMARY = new Color(248, 250, 252);     // #F8FAFC - Almost White
    public static final Color TEXT_SECONDARY = new Color(203, 213, 225);   // #CBD5E1 - Light Gray
    public static final Color TEXT_MUTED = new Color(148, 163, 184);       // #94A3B8 - Muted Gray
    
    // Accent Colors
    public static final Color SUCCESS = new Color(34, 197, 94);            // #22C55E - Green
    public static final Color WARNING = new Color(251, 146, 60);           // #FB923C - Orange
    public static final Color DANGER = new Color(239, 68, 68);             // #EF4444 - Red
    public static final Color INFO = new Color(59, 130, 246);              // #3B82F6 - Blue
    
    // Border Colors
    public static final Color BORDER_DARK = new Color(51, 65, 85);         // #334155
    public static final Color BORDER_LIGHT = new Color(71, 85, 105);       // #475569
    
    // ==================== TYPOGRAPHY ====================
    
    public static final String FONT_FAMILY = "Segoe UI";
    public static final String FONT_FAMILY_MONO = "Consolas";
    
    // Font Sizes
    public static final int FONT_SIZE_XLARGE = 28;
    public static final int FONT_SIZE_LARGE = 22;
    public static final int FONT_SIZE_TITLE = 18;
    public static final int FONT_SIZE_SUBTITLE = 16;
    public static final int FONT_SIZE_BODY = 14;
    public static final int FONT_SIZE_SMALL = 12;
    public static final int FONT_SIZE_TINY = 10;
    
    // Font Styles
    public static final Font FONT_XLARGE_BOLD = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_XLARGE);
    public static final Font FONT_LARGE_BOLD = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_LARGE);
    public static final Font FONT_TITLE_BOLD = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_TITLE);
    public static final Font FONT_SUBTITLE_BOLD = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_SUBTITLE);
    public static final Font FONT_BODY_BOLD = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_BODY);
    public static final Font FONT_BODY = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE_BODY);
    public static final Font FONT_SMALL = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE_SMALL);
    public static final Font FONT_SMALL_BOLD = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_SMALL);
    public static final Font FONT_TINY = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE_TINY);
    public static final Font FONT_MONO = new Font(FONT_FAMILY_MONO, Font.PLAIN, FONT_SIZE_BODY);
    
    // ==================== SPACING ====================
    
    public static final int SPACING_TINY = 4;
    public static final int SPACING_SMALL = 8;
    public static final int SPACING_MEDIUM = 12;
    public static final int SPACING_LARGE = 16;
    public static final int SPACING_XLARGE = 24;
    public static final int SPACING_XXLARGE = 32;
    
    // ==================== DIMENSIONS ====================
    
    // Button Sizes
    public static final int BUTTON_HEIGHT = 40;
    public static final int BUTTON_HEIGHT_SMALL = 32;
    public static final int BUTTON_HEIGHT_LARGE = 48;
    
    // Input Sizes
    public static final int INPUT_HEIGHT = 40;
    public static final int INPUT_HEIGHT_SMALL = 32;
    
    // Border Radius
    public static final int RADIUS_SMALL = 4;
    public static final int RADIUS_MEDIUM = 8;
    public static final int RADIUS_LARGE = 12;
    
    // Sidebar
    public static final int SIDEBAR_WIDTH_EXPANDED = 240;
    public static final int SIDEBAR_WIDTH_COLLAPSED = 60;
    
    // ==================== HELPER METHODS ====================
    
    /**
     * Create a brighter version of a color for hover effects
     */
    public static Color brighten(Color color, float factor) {
        int r = Math.min(255, (int)(color.getRed() * factor));
        int g = Math.min(255, (int)(color.getGreen() * factor));
        int b = Math.min(255, (int)(color.getBlue() * factor));
        return new Color(r, g, b);
    }
    
    /**
     * Create a darker version of a color
     */
    public static Color darken(Color color, float factor) {
        int r = (int)(color.getRed() * factor);
        int g = (int)(color.getGreen() * factor);
        int b = (int)(color.getBlue() * factor);
        return new Color(r, g, b);
    }
    
    /**
     * Add alpha transparency to a color
     */
    public static Color withAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
}
