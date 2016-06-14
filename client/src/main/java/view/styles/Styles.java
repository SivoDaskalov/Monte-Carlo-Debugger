/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.styles;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sdaskalov
 */
public class Styles {

    public static final int strut = 30;
    public static Border padding = new EmptyBorder(10, 10, 10, 10);
    
    public static final String valueFormat = "%.5f";
    public static final Font valueFont = new Font("Arial", Font.PLAIN, 14);
    public static final Color valueColor = Color.BLACK;

    public static final Font titleFont = new Font("Arial", Font.BOLD, 18);
    public static final Font labelFont = new Font("Arial", Font.ITALIC, 14);
    public static final Color labelColor = new Color(80, 80, 80);

    public static final Color defaultBackgroundColor = Color.WHITE;
    public static final Color selectedBackgroundColor = new Color(232, 239, 248);
    public static final Color currentlyDebuggedBackgroundColor = new Color(189, 230, 170);
}
