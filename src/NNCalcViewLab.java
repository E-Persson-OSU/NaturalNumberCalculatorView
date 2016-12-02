import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Test class for NaturalNumber calculator GUI (view in MVC).
 *
 * @author Put your name here
 */
public final class NNCalcViewLab extends JFrame implements ActionListener {

    /**
     * Text areas.
     */
    private final JTextArea tTop = new JTextArea("0", TEXT_AREA_HEIGHT,
            TEXT_AREA_WIDTH),
            tBottom = new JTextArea("0", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

    /**
     * Operator and related buttons.
     */
    private final JButton bClear = new JButton("Clear"),
            bSwap = new JButton("Swap"), bEnter = new JButton("Enter"),
            bAdd = new JButton("+"), bSubtract = new JButton("-"),
            bMultiply = new JButton("*"), bDivide = new JButton("/"),
            bPower = new JButton("^"), bRoot = new JButton("Root");

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits = { new JButton("1"), new JButton("2"),
            new JButton("3"), new JButton("4"), new JButton("5"),
            new JButton("6"), new JButton("7"), new JButton("8"),
            new JButton("9"), new JButton("0") };

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1;

    /**
     * No-argument constructor.
     */
    public NNCalcViewLab() {

        // Create the JFrame being extended

        /*
         * Call the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Natural Number Calculator");

        // Set up the GUI widgets --------------------------------------------

        //calc panel
        JPanel calc = new JPanel(
                new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));

        //add everything to panels

        this.add(calc);

        /*
         * Create widgets
         */

        // Set up the GUI widgets --------------------------------------------

        /*
         * Text areas should wrap lines, and should be read-only; they cannot be
         * edited because allowing keyboard entry would require checking whether
         * entries are digits, which we don't want to have to do
         */
        //create JTextArea panel
        this.tTop.setEditable(false);
        this.tBottom.setEditable(false);
        this.tTop.setLineWrap(true);
        this.tBottom.setLineWrap(true);

        /*
         * Initially, the following buttons should be disabled: divide (divisor
         * must not be 0) and root (root must be at least 2) -- hint: see the
         * JButton method setEnabled
         */
        this.bRoot.setEnabled(false);
        this.bDivide.setEnabled(false);

        /*
         * Create scroll panes for the text areas in case number is long enough
         * to require scrolling
         */
        ScrollPane sBottom = new ScrollPane();
        ScrollPane sTop = new ScrollPane();
        sBottom.add(this.tBottom);
        sTop.add(this.tTop);

        /*
         * Create main button panel
         */
        //create JButton panel

        JPanel numberButtonPanel = new JPanel(new GridLayout(
                MAIN_BUTTON_PANEL_GRID_ROWS, MAIN_BUTTON_PANEL_GRID_COLUMNS));

        /*
         * Add the buttons to the main button panel, from left to right and top
         * to bottom
         */
        for (JButton add : this.bDigits) {
            add.addActionListener(this);
            numberButtonPanel.add(add);
        }

        numberButtonPanel.add(this.bDivide);
        numberButtonPanel.add(this.bMultiply);
        numberButtonPanel.add(this.bRoot);
        numberButtonPanel.add(this.bAdd);
        numberButtonPanel.add(this.bSubtract);
        numberButtonPanel.add(this.bPower);

        /*
         * Create side button panel
         */
        JPanel sideButtonPanel = new JPanel(new GridLayout(
                SIDE_BUTTON_PANEL_GRID_ROWS, SIDE_BUTTON_PANEL_GRID_COLUMNS));
        /*
         * Add the buttons to the side button panel, from left to right and top
         * to bottom
         */

        sideButtonPanel.add(this.bClear);
        sideButtonPanel.add(this.bEnter);
        sideButtonPanel.add(this.bSwap);

        /*
         * Create combined button panel organized using flow layout, which is
         * simple and does the right thing: sizes of nested panels are natural,
         * not necessarily equal as with grid layout
         */
        JPanel buttonPanel = new JPanel(new FlowLayout());

        /*
         * Add the other two button panels to the combined button panel
         */
        buttonPanel.add(numberButtonPanel);
        buttonPanel.add(sideButtonPanel);

        /*
         * Organize main window
         */

        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */
        calc.add(sTop);
        calc.add(sBottom);
        calc.add(buttonPanel);

        // Set up the observers ----------------------------------------------

        /*
         * Register this object as the observer for all GUI events
         */
        this.bDivide.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bRoot.addActionListener(this);
        this.bAdd.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bClear.addActionListener(this);
        this.bEnter.addActionListener(this);
        this.bSwap.addActionListener(this);

        // Set up the main application window --------------------------------

        /*
         * Make sure the main window is appropriately sized, exits this program
         * on close, and becomes visible to the user
         */

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.tBottom.toString().equals("0")) {
            this.tBottom.setText(event.getActionCommand());
        } else {
            this.tBottom.append(event.getActionCommand());
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        NNCalcViewLab view = new NNCalcViewLab();
    }

}
