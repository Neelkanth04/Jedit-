

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.text.DefaultHighlighter;



class MyHighlighterPainter extends DefaultHighlighter.DefaultHighlightPainter {
    public MyHighlighterPainter(Color var1) {
        super(var1);
    }
}

public class main extends JFrame implements FocusListener {
    private JPanel containerpanel = new JPanel();
    private JPanel mainpanel = new JPanel();
    private JPanel wordpanel = new JPanel();
    private JPanel leftpanel = new JPanel();
    private JPanel formatpanel = new JPanel();
    private JPanel searchpanel = new JPanel();
    private JPanel textpanel = new JPanel();
    private JPanel rightpanel = new JPanel();
    private JPanel titlepanel = new JPanel();
    private JPanel shapespanel = new JPanel();
    private DrawingPanel drawingpanel = new DrawingPanel();
    JTextPane textArea = new JTextPane();
    private JMenuBar jmb = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenu edit = new JMenu("Edit");
    private JMenu format = new JMenu("Font");
    private JMenu review = new JMenu("Review");
    private JMenu help = new JMenu("Help");
    JComboBox jComboBox1 = new JComboBox();
    JComboBox fontSizeBox = new JComboBox();
    JLabel wordcnt = new JLabel("Word Count: ");
    JLabel wordCount = new JLabel("");
    JLabel charcnt = new JLabel("Character Count: ");
    JLabel characterCount = new JLabel("");
    JLabel informationDisplay = new JLabel("");
    private JMenuItem _new = new JMenuItem("New", new ImageIcon(this.getClass().getResource("/resources/new.png")));
    private JMenuItem _open = new JMenuItem("Open", new ImageIcon(this.getClass().getResource("/resources/open.png")));
    private JMenuItem _save = new JMenuItem("Save", new ImageIcon(this.getClass().getResource("/resources/save.png")));
    private JMenuItem _saveas = new JMenuItem("Save as...", new ImageIcon(this.getClass().getResource("/resources/saveas.png")));
    private JMenuItem _exit = new JMenuItem("Exit", new ImageIcon(this.getClass().getResource("/resources/exit.png")));
    private JMenuItem _undo = new JMenuItem("Undo", new ImageIcon(this.getClass().getResource("/resources/undo.png")));
    private JMenuItem _redo = new JMenuItem("Redo", new ImageIcon(this.getClass().getResource("/resources/redo.png")));
    private JMenuItem _cut = new JMenuItem("Cut", new ImageIcon(this.getClass().getResource("/resources/cut.png")));
    private JMenuItem _copy = new JMenuItem("Copy", new ImageIcon(this.getClass().getResource("/resources/copy.png")));
    private JMenuItem _paste = new JMenuItem("Paste", new ImageIcon(this.getClass().getResource("/resources/paste.png")));
    private JMenuItem _find = new JMenuItem("Find", new ImageIcon(this.getClass().getResource("/resources/find.png")));
    private JMenuItem _find_and_replace = new JMenuItem("Find and Replace", new ImageIcon(this.getClass().getResource("/resources/find_and_replace.png")));
    private JMenuItem _count = new JMenuItem("Word Count", new ImageIcon(this.getClass().getResource("/resources/wordcnt.png")));
    private JMenuItem _info = new JMenuItem("Info...");
    private JMenuItem _about_us = new JMenuItem("About Me", new ImageIcon(this.getClass().getResource("/resources/aboutus.png")));
    JTextField findText = new JTextField();
    JTextField replaceWithText = new JTextField();
    JLabel jLabel1 = new JLabel("Find");
    JLabel jLabel2 = new JLabel("Replace");
    JLabel foundInPlaces = new JLabel("");
    JButton jButton4 = new JButton("Replace");
    JButton jButton2 = new JButton("Replace All");
    JButton jButton3 = new JButton("Find Next");
    JButton jButton1 = new JButton("Find All");
    private JMenu _font = new JMenu("Font");
    private JMenu _size = new JMenu("Size");
    private JMenu _case = new JMenu("Case");
    private JButton jbtRect = new JButton("Rectangle");
    private JButton jbtOval = new JButton("Oval");
    private JButton jbtline = new JButton("Line");
    private JButton jbtTriangle = new JButton("Triangle");
    private JButton jbtPentagon = new JButton("Pentagon");
    private JButton jbtCLEAR = new JButton("CLEAR");
    private JButton jbtSearch = new JButton("SEARCH");
    private JTextField jtfSearch = new JTextField("Type to search");
    String[] fonts = new String[]{"Arial", "Calibri", "Cambria", "Courier New", "Comic Sans MS", "Dialog", "Georgia", "Helevetica", "Lucida Sans", "Monospaced", "SignPainter", "Tahoma", "Times New Roman", "Verdana"};
    String[] fontSizes = new String[]{"5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90"};
    Font newFont = new Font("Times New Roman", 0, 20);
    int previousSize = 20;
    String previousFont = "Arial";
    String fn = "";
    String dir = "";
    String fileName = "New Text Document";
    Font startingFont;
    static int findNextPos = 0;
    static int oldFindNextPos = 0;
    static int numberOfWordsFound = 0;
    int count;
    int currentFileSaved;
    String copiedText;
    StyledDocument document;
    Element element;
    AttributeSet attribute;
    String previousSearchedText;
    File openedFile;
    RTFEditorKit editorKit;
    JFileChooser fileChooser;
    Highlighter.HighlightPainter myHighlightPainter;
    Highlighter.HighlightPainter removeTheHighlight;
    int removeHighlights;

    main() {
        this.startingFont = new Font(this.previousFont, 0, this.previousSize);
        this.count = 0;
        this.currentFileSaved = 0;
        this.copiedText = " ";
        this.previousSearchedText = "";
        this.fileChooser = new JFileChooser();
        this.myHighlightPainter = new MyHighlighterPainter(Color.yellow);
        this.removeTheHighlight = new MyHighlighterPainter(Color.white);
        this.removeHighlights = 0;
        this.textArea.setFont(this.newFont);
        this.jComboBox1.removeAllItems();
        String[] var1 = this.fonts;
        int var2 = var1.length;

        int var3;
        String var4;
        for(var3 = 0; var3 < var2; ++var3) {
            var4 = var1[var3];
            this.jComboBox1.addItem(var4);
        }

        var1 = this.fontSizes;
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            var4 = var1[var3];
            this.fontSizeBox.addItem(var4);
        }

        this.setTitle(this.fileName + ".rtf");
        this.file.setPreferredSize(new Dimension(50, 25));
        this.edit.setPreferredSize(new Dimension(50, 25));
        this.format.setPreferredSize(new Dimension(50, 25));
        this.review.setPreferredSize(new Dimension(70, 25));
        this.help.setPreferredSize(new Dimension(50, 25));
        this.file.setBorder(BorderFactory.createLineBorder(Color.white));
        this.edit.setBorder(BorderFactory.createLineBorder(Color.white));
        this.format.setBorder(BorderFactory.createLineBorder(Color.white));
        this.review.setBorder(BorderFactory.createLineBorder(Color.white));
        this.help.setBorder(BorderFactory.createLineBorder(Color.white));
        this.jmb.add(this.file);
        this.jmb.add(this.edit);
        this.jmb.add(this.review);
        this.jmb.add(this.help);
        this.file.setMnemonic(70);
        this.edit.setMnemonic(69);
        this.format.setMnemonic(79);
        this.review.setMnemonic(82);
        this._new.setMnemonic(78);
        this._open.setMnemonic(79);
        this._save.setMnemonic(83);
        this._saveas.setMnemonic(65);
        this._exit.setMnemonic(88);
        this._undo.setMnemonic(85);
        this._redo.setMnemonic(82);
        this._copy.setMnemonic(67);
        this._cut.setMnemonic(84);
        this._paste.setMnemonic(80);
        this._font.setMnemonic(70);
        this._size.setMnemonic(83);
        this._case.setMnemonic(67);
        this._find.setMnemonic(70);
        this._find_and_replace.setMnemonic(82);
        this._count.setMnemonic(87);
        this.help.setMnemonic(72);
        this._info.setMnemonic(73);
        this._about_us.setMnemonic(66);
        JMenuItem var26 = new JMenuItem("UPPER CASE");
        JMenuItem var27 = new JMenuItem("lower case");
        this._new.setAccelerator(KeyStroke.getKeyStroke(78, 2));
        this._open.setAccelerator(KeyStroke.getKeyStroke(79, 2));
        this._save.setAccelerator(KeyStroke.getKeyStroke(83, 2));
        this._exit.setAccelerator(KeyStroke.getKeyStroke(115, 8));
        this._undo.setAccelerator(KeyStroke.getKeyStroke(90, 2));
        this._redo.setAccelerator(KeyStroke.getKeyStroke(89, 2));
        this._copy.setAccelerator(KeyStroke.getKeyStroke(67, 2));
        this._cut.setAccelerator(KeyStroke.getKeyStroke(88, 2));
        this._paste.setAccelerator(KeyStroke.getKeyStroke(86, 2));
        this._find.setAccelerator(KeyStroke.getKeyStroke(70, 2));
        this._info.setAccelerator(KeyStroke.getKeyStroke(112, 2));
        this._new.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jMenuItem1ActionPerformed(var1);
            }
        });
        this._open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.openFileActionPerformed(var1);
            }
        });
        this._save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.saveFileActionPerformed(var1);
            }
        });
        this._saveas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jMenuItem3ActionPerformed(var1);
            }
        });
        this._exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                System.exit(0);
            }
        });
        this._cut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.cutEditActionPerformed(var1);
            }
        });
        this._copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.copyEditActionPerformed(var1);
            }
        });
        this._paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jMenuItem6ActionPerformed(var1);
            }
        });
        this.textArea.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent var1) {
            }

            public void keyPressed(KeyEvent var1) {
            }

            public void keyReleased(KeyEvent var1) {
                main.this.wordCount();
            }
        });
        this.jComboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jComboBox1ActionPerformed(var1);
            }
        });
        this.fontSizeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.fontSizeBoxActionPerformed(var1);
            }
        });
        this.textArea.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent var1) {
                main.this.wordCount();
            }

            public void mousePressed(MouseEvent var1) {
            }

            public void mouseReleased(MouseEvent var1) {
            }

            public void mouseEntered(MouseEvent var1) {
            }

            public void mouseExited(MouseEvent var1) {
            }
        });
        this.textArea.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent var1) {
                main.this.wordCount();
            }

            public void mouseMoved(MouseEvent var1) {
            }
        });
        var26.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.toUpperActionPerformed(var1);
            }
        });
        var27.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.toLowerActionPerformed(var1);
            }
        });
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jButton1ActionPerformed(var1);
            }
        });
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jButton2ActionPerformed(var1);
            }
        });
        this.jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jButton3ActionPerformed(var1);
            }
        });
        this.jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jButton4ActionPerformed(var1);
            }
        });
        this.file.add(this._new);
        this.file.add(this._open);
        this.file.addSeparator();
        this.file.add(this._save);
        this.file.add(this._saveas);
        this.file.addSeparator();
        this.file.add(this._exit);
        this.edit.add(this._cut);
        this.edit.add(this._copy);
        this.edit.add(this._paste);
        this._font.setIcon(new ImageIcon("./resources/find.png"));
        this._size.setIcon(new ImageIcon("./resources/size.png"));
        this._case.setIcon(new ImageIcon("./resources/case.png"));
        this._case.add(var26);
        this._case.add(var27);
        this.format.add(this._font);
        this.format.add(this._size);
        this.format.add(this._case);
        this.review.add(this._case);
        this.review.addSeparator();
        this.review.add(this._count);
        this.help.addSeparator();
        this.help.add(this._about_us);
        ImageIcon var28 = new ImageIcon(this.getClass().getResource("/resources/bold.png"), "Makes Text Bold");
        JToggleButton var29 = new JToggleButton(var28);
        ImageIcon var5 = new ImageIcon(this.getClass().getResource("/resources/italics.png"), "Makes Text Italic");
        JToggleButton var6 = new JToggleButton(var5);
        ImageIcon var7 = new ImageIcon(this.getClass().getResource("/resources/underline.png"), "Makes Text underlined");
        JToggleButton var8 = new JToggleButton(var7);
        ImageIcon var9 = new ImageIcon(this.getClass().getResource("/resources/strikethrough.png"), "Strikes through text");
        JToggleButton var10 = new JToggleButton(var9);
        ImageIcon var11 = new ImageIcon(this.getClass().getResource("/resources/leftalign.png"), "Left aligns text");
        JToggleButton var12 = new JToggleButton(var11);
        ImageIcon var13 = new ImageIcon(this.getClass().getResource("/resources/rightalign.png"), "Right aligns text");
        JToggleButton var14 = new JToggleButton(var13);
        ImageIcon var15 = new ImageIcon(this.getClass().getResource("/resources/centeralign.png"), "Centre aligns text");
        JToggleButton var16 = new JToggleButton(var15);
        ImageIcon var17 = new ImageIcon(this.getClass().getResource("/resources/justify.png"), "Justifies text");
        JToggleButton var18 = new JToggleButton(var17);
        ImageIcon var19 = new ImageIcon(this.getClass().getResource("/resources/increase.png"), "Increases font size");
        new JButton(var19);
        ImageIcon var21 = new ImageIcon(this.getClass().getResource("/resources/decrease.png"), "Decreases font size");
        new JButton(var21);
        ButtonGroup var23 = new ButtonGroup();
        var23.add(var12);
        var23.add(var16);
        var23.add(var14);
        var23.add(var18);
        var12.setSelected(true);
        this.formatpanel.setLayout(new BoxLayout(this.formatpanel, 2));
        this.formatpanel.add(var29);
        this.formatpanel.add(var6);
        this.formatpanel.add(var8);
        this.formatpanel.add(var10);
        this.formatpanel.add(Box.createRigidArea(new Dimension(5, 0)));
        this.formatpanel.add(var12);
        this.formatpanel.add(var16);
        this.formatpanel.add(var14);
        this.formatpanel.add(var18);
        this.formatpanel.add(Box.createRigidArea(new Dimension(5, 0)));
        this.formatpanel.add(Box.createRigidArea(new Dimension(10, 0)));
        this.formatpanel.add(this.jComboBox1);
        this.formatpanel.add(this.fontSizeBox);
        var29.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.makeTextBold(var1);
            }
        });
        var6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.makeTextItalics(var1);
            }
        });
        var8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.makeTextUnderlined(var1);
            }
        });
        var10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jMenuItem5ActionPerformed(var1);
            }
        });
        this._about_us.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jMenuItem12ActionPerformed(var1);
            }
        });
        this.mainpanel.setLayout(new BoxLayout(this.mainpanel, 2));
        this.leftpanel.setLayout(new BoxLayout(this.leftpanel, 3));
        this.rightpanel.setLayout(new BoxLayout(this.rightpanel, 3));
        this.containerpanel.setLayout(new BoxLayout(this.containerpanel, 3));
        this.formatpanel.setAlignmentX(0.0F);
        this.searchpanel.setAlignmentX(0.0F);
        this.textpanel.setAlignmentX(0.0F);
        this.leftpanel.setAlignmentX(0.0F);
        JPanel var24 = new JPanel();
        JPanel var25 = new JPanel();
        this.findText.setPreferredSize(new Dimension(300, 15));
        this.replaceWithText.setPreferredSize(new Dimension(300, 15));
        this.searchpanel.setLayout(new BoxLayout(this.searchpanel, 3));
        var24.setLayout(new BoxLayout(var24, 3));
        var24.add(this.jLabel1);
        var24.add(this.findText);
        var24.add(this.jLabel2);
        var24.add(this.replaceWithText);
        this.searchpanel.add(var24);
        var25.setLayout(new BoxLayout(var25, 2));
        var25.add(this.jButton1);
        var25.add(Box.createRigidArea(new Dimension(10, 0)));
        var25.add(this.jButton3);
        var25.add(Box.createRigidArea(new Dimension(10, 0)));
        var25.add(this.jButton4);
        var25.add(Box.createRigidArea(new Dimension(10, 0)));
        var25.add(this.jButton2);
        this.searchpanel.add(var25);
        this.searchpanel.add(this.foundInPlaces);
        this.textArea.setPreferredSize(new Dimension(500, 300));
        this.textpanel.setLayout(new BoxLayout(this.textpanel, 2));
        this.textpanel.add(this.textArea);
        this.wordpanel.setLayout(new FlowLayout(0));
        this.wordpanel.add(this.wordcnt);
        this.wordpanel.add(this.wordCount);
        this.wordpanel.add(this.charcnt);
        this.wordpanel.add(this.characterCount);
        this.wordpanel.add(this.informationDisplay);
        this.wordpanel.setBackground(Color.LIGHT_GRAY);
        this.leftpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.leftpanel.add(this.formatpanel);
        this.leftpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.leftpanel.add(this.searchpanel);
        this.leftpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.leftpanel.add(this.textpanel);
        this.leftpanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.shapespanel.setLayout(new BoxLayout(this.shapespanel, 2));
        this.shapespanel.add(this.jbtRect);
        this.jbtRect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.drawingpanel.rectangle();
            }
        });
        this.shapespanel.add(this.jbtOval);
        this.jbtOval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.drawingpanel.oval();
            }
        });
        this.shapespanel.add(this.jbtline);
        this.jbtline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.drawingpanel.line();
            }
        });
        this.shapespanel.add(this.jbtTriangle);
        this.jbtTriangle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.drawingpanel.triangle();
            }
        });
        var12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.jMenuItem2ActionPerformed(var1);
            }
        });
        var14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.rightAlignActionPerformed(var1);
            }
        });
        var18.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.justifyAlignActionPerformed(var1);
            }
        });
        var16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.centreAlignActionPerformed(var1);
            }
        });
        this.shapespanel.add(this.jbtPentagon);
        this.jbtPentagon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.drawingpanel.pentagon();
            }
        });
        this.shapespanel.add(Box.createRigidArea(new Dimension(5, 0)));
        this.shapespanel.add(this.jbtCLEAR);
        this.jbtCLEAR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                main.this.drawingpanel.clear();
            }
        });
        this.drawingpanel.setSize(200, 300);
        this.drawingpanel.setBackground(Color.LIGHT_GRAY);
        this.rightpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.rightpanel.add(new JLabel("SketchPad"));
        this.rightpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.rightpanel.add(this.shapespanel);
        this.rightpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.rightpanel.add(this.drawingpanel);
        this.rightpanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.mainpanel.add(this.leftpanel);
        this.mainpanel.add(Box.createRigidArea(new Dimension(10, 0)));
        this.mainpanel.add(this.rightpanel);
        this.add(this.jmb, "North");
        this.containerpanel.add(this.mainpanel);
        this.containerpanel.add(Box.createVerticalGlue());
        this.containerpanel.add(this.wordpanel);
        this.add(this.containerpanel);
    }

    public static void main(String[] var0) throws InterruptedException, IOException {
        File[] var1 = (new File(".")).listFiles();
        System.out.println((new File(".")).getAbsoluteFile());
        File[] var2 = var1;
        int var3 = var1.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            File var5 = var2[var4];
            System.out.println(var5.getAbsoluteFile());
        }
        SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash != null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(4000L);
        main var6 = new main();
        ((JFrame)var6).setVisible(true);
        ((JFrame)var6).setDefaultCloseOperation(3);
        ((JFrame)var6).setLocation(75, 75);
        ((JFrame)var6).setSize(1250, 600);
        ((JFrame)var6).setTitle("JEditPro");
    }


    public void focusGained(FocusEvent var1) {
        if (this.jtfSearch.getText().toLowerCase().equals("type to search")) {
            this.jtfSearch.setText("");
        }

    }

    public void focusLost(FocusEvent var1) {
        if (this.jtfSearch.getText().equals("")) {
            this.jtfSearch.setText("Type to search");
        }

    }

    private void saveFileActionPerformed(ActionEvent var1) {
        System.out.println("Saving file");
        int var2 = this.fileChooser.showSaveDialog((Component)null);
        if (var2 == 0) {
            File var3 = this.fileChooser.getSelectedFile();
            StyledDocument var4 = this.textArea.getStyledDocument();
            RTFEditorKit var5 = new RTFEditorKit();

            try {
                BufferedOutputStream var6 = new BufferedOutputStream(new FileOutputStream(var3));
                var5.write(var6, var4, var4.getStartPosition().getOffset(), var4.getLength());
                var6.flush();
                var6.close();
            } catch (BadLocationException var8) {
                var8.getCause();
            } catch (IOException var9) {
                var9.getSuppressed();
            }

            this.fileName = this.fileChooser.getName(var3);
            this.setTitle(this.fileName + ".rtf");
            this.currentFileSaved = 1;
        }

    }

    private void openFileActionPerformed(ActionEvent var1) {
        JFileChooser var2 = new JFileChooser();
        this.textArea.setText(" ");
        var2.showOpenDialog((Component)null);
        this.openedFile = var2.getSelectedFile();
        RTFEditorKit var3 = new RTFEditorKit();

        try {
            FileInputStream var4 = new FileInputStream(this.openedFile);
            var3.read(var4, this.textArea.getStyledDocument(), 0);
            var4.close();
        } catch (BadLocationException var6) {
            var6.getCause();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    private void jMenuItem1ActionPerformed(ActionEvent var1) {
        if (this.currentFileSaved == 0) {
            this.saveFileActionPerformed(var1);
        } else {
            this.setTitle("New Text Document");
            this.textArea.setText(" ");
            this.textArea.setFont(this.newFont);
            this.currentFileSaved = 0;
        }

    }

    private void jMenuItem3ActionPerformed(ActionEvent var1) {
        System.out.println("Save As");
        int var2 = this.fileChooser.showSaveDialog((Component)null);
        if (var2 == 0) {
            File var3 = this.fileChooser.getSelectedFile();
            StyledDocument var4 = this.textArea.getStyledDocument();
            RTFEditorKit var5 = new RTFEditorKit();

            try {
                BufferedOutputStream var6 = new BufferedOutputStream(new FileOutputStream(var3));
                var5.write(var6, var4, var4.getStartPosition().getOffset(), var4.getLength());
                var6.flush();
                var6.close();
            } catch (BadLocationException var8) {
                var8.getCause();
            } catch (IOException var9) {
                var9.getSuppressed();
            }

            this.fileName = this.fileChooser.getName(var3);
            this.setTitle(this.fileName + ".rtf");
            this.currentFileSaved = 1;
        }

    }

    private void cutEditActionPerformed(ActionEvent var1) {
        this.document = (StyledDocument)this.textArea.getDocument();
        int var2 = this.textArea.getSelectionEnd();
        int var3 = this.textArea.getSelectionStart();
        this.element = this.document.getCharacterElement(var3);
        this.attribute = this.element.getAttributes();
        this.copiedText = this.textArea.getSelectedText();

        try {
            System.out.println("CUT");
            this.document.remove(var3, this.textArea.getSelectedText().length());
        } catch (BadLocationException var5) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var5);
        }

    }

    private void copyEditActionPerformed(ActionEvent var1) {
        System.out.println("COPY");
        this.document = (StyledDocument)this.textArea.getDocument();
        int var2 = this.textArea.getSelectionEnd();
        int var3 = this.textArea.getSelectionStart();
        this.element = this.document.getCharacterElement(var3);
        this.attribute = this.element.getAttributes();
        this.copiedText = this.textArea.getSelectedText();
    }

    private void jMenuItem6ActionPerformed(ActionEvent var1) {
        try {
            System.out.println("PASTE");
            this.document.insertString(this.textArea.getCaretPosition(), " " + this.copiedText + " ", this.attribute);
        } catch (BadLocationException var3) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var3);
        }

    }

    private void makeTextBold(ActionEvent var1) {
        System.out.println("Making Text Bold");
        StyledDocument var2 = (StyledDocument)this.textArea.getDocument();
        int var3 = this.textArea.getSelectionEnd();
        int var4 = this.textArea.getSelectionStart();
        Element var5 = var2.getCharacterElement(var4);
        AttributeSet var6 = var5.getAttributes();
        if (var3 != var4) {
            SimpleAttributeSet var7 = new SimpleAttributeSet(var6.copyAttributes());
            StyleConstants.setBold(var7, !StyleConstants.isBold(var6));
            var2.setCharacterAttributes(var4, this.textArea.getSelectedText().length(), var7, true);
        }
    }

    private void makeTextItalics(ActionEvent var1) {
        System.out.println("Making Text Italics");
        StyledDocument var2 = (StyledDocument)this.textArea.getDocument();
        int var3 = this.textArea.getSelectionEnd();
        int var4 = this.textArea.getSelectionStart();
        Element var5 = var2.getCharacterElement(var4);
        AttributeSet var6 = var5.getAttributes();
        if (var3 != var4) {
            SimpleAttributeSet var7 = new SimpleAttributeSet(var6.copyAttributes());
            StyleConstants.setItalic(var7, !StyleConstants.isItalic(var6));
            var2.setCharacterAttributes(var4, this.textArea.getSelectedText().length(), var7, true);
        }
    }

    private void makeTextUnderlined(ActionEvent var1) {
        System.out.println("Making Text Italics");
        StyledDocument var2 = (StyledDocument)this.textArea.getDocument();
        int var3 = this.textArea.getSelectionEnd();
        int var4 = this.textArea.getSelectionStart();
        Element var5 = var2.getCharacterElement(var4);
        AttributeSet var6 = var5.getAttributes();
        if (var3 != var4) {
            SimpleAttributeSet var7 = new SimpleAttributeSet(var6.copyAttributes());
            StyleConstants.setUnderline(var7, !StyleConstants.isUnderline(var6));
            var2.setCharacterAttributes(var4, this.textArea.getSelectedText().length(), var7, true);
        }
    }

    private void jMenuItem5ActionPerformed(ActionEvent var1) {
        System.out.println("Strikethrough");
        StyledDocument var2 = (StyledDocument)this.textArea.getDocument();
        int var3 = this.textArea.getSelectionEnd();
        int var4 = this.textArea.getSelectionStart();
        Element var5 = var2.getCharacterElement(var4);
        AttributeSet var6 = var5.getAttributes();
        if (var3 != var4) {
            SimpleAttributeSet var7 = new SimpleAttributeSet(var6.copyAttributes());
            StyleConstants.setStrikeThrough(var7, !StyleConstants.isStrikeThrough(var6));
            var2.setCharacterAttributes(var4, this.textArea.getSelectedText().length(), var7, true);
        }
    }

    public void wordCount() {
        boolean var2 = false;
        String var1;
        if (this.textArea.getSelectedText() == null) {
            var1 = this.textArea.getText();
        } else {
            var1 = this.textArea.getSelectedText();
            var2 = true;
        }

        boolean var3 = false;
        int var4 = 0;
        int var5 = 0;
        boolean var6 = false;
        if (var1.isEmpty()) {
            var4 = 0;
            var5 = 0;
        } else {
            String[] var7 = var1.split(" ");
            String[] var8 = var7;
            int var9 = var7.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                String var11 = var8[var10];
                String[] var12 = var11.split("\n");
                if (var12.length != 1) {
                    var6 = true;
                    String[] var13 = var12;
                    int var14 = var12.length;

                    for(int var15 = 0; var15 < var14; ++var15) {
                        String var16 = var13[var15];
                        if (!var16.equals("")) {
                            ++var4;
                        }

                        var5 += var16.length();
                    }
                }

                if (!var6) {
                    ++var4;
                    var5 += var11.length();
                }

                var6 = false;
            }
        }

        this.characterCount.setText(String.valueOf(var5));
        this.wordCount.setText(String.valueOf(var4));
        if (var2) {
            this.informationDisplay.setText("WORD COUNT OF SELECTED TEXT ONLY");
        } else {
            this.informationDisplay.setText("WORD COUNT OF ENTIRE DOCUMENT");
        }

    }

    private void textAreaMouseClicked(MouseEvent var1) {
        this.wordCount();
    }

    private void textAreaKeyReleased(KeyEvent var1) {
        this.wordCount();
    }

    private void jComboBox1ActionPerformed(ActionEvent var1) {
        System.out.println("Setting font to " + this.jComboBox1.getSelectedItem());
        String var2 = (String)this.jComboBox1.getSelectedItem();
        Font var3 = new Font(var2, 0, this.previousSize);
        if (this.textArea.getSelectedText() == null) {
            this.textArea.setFont(var3);
        } else {
            StyledDocument var4 = (StyledDocument)this.textArea.getDocument();
            int var5 = this.textArea.getSelectionEnd();
            int var6 = this.textArea.getSelectionStart();
            Element var7 = var4.getCharacterElement(var6);
            AttributeSet var8 = var7.getAttributes();
            SimpleAttributeSet var9 = new SimpleAttributeSet(var8.copyAttributes());
            StyleConstants.setFontFamily(var9, var2);
            var4.setCharacterAttributes(var6, this.textArea.getSelectedText().length(), var9, true);
        }

        this.previousFont = var2;
    }

    private void fontSizeBoxActionPerformed(ActionEvent var1) {
        System.out.println("Setting font to " + this.jComboBox1.getSelectedItem());
        String var2 = this.fontSizeBox.getSelectedItem().toString();
        int var3 = Integer.valueOf(var2);
        StyledDocument var4 = (StyledDocument)this.textArea.getDocument();
        int var5 = this.textArea.getSelectionEnd();
        int var6 = this.textArea.getSelectionStart();
        Element var7 = var4.getCharacterElement(var6);
        AttributeSet var8 = var7.getAttributes();
        if (var5 == var6) {
            Font var10 = new Font(this.previousFont, 0, var3);
            this.textArea.setFont(var10);
        } else {
            SimpleAttributeSet var9 = new SimpleAttributeSet(var8.copyAttributes());
            StyleConstants.setFontSize(var9, var3);
            var4.setCharacterAttributes(var6, this.textArea.getSelectedText().length(), var9, true);
            this.previousSize = var3;
        }
    }

    public void highlight(JTextComponent var1, String var2, Highlighter.HighlightPainter var3) {
        try {
            Highlighter var4 = var1.getHighlighter();
            Document var5 = var1.getDocument();
            String var6 = var5.getText(0, var5.getLength());

            for(int var7 = 0; (var7 = var6.toUpperCase().indexOf(var2.toUpperCase(), var7)) >= 0; var7 += var2.length()) {
                var4.addHighlight(var7, var7 + var2.length(), var3);
            }
        } catch (BadLocationException var8) {
            System.out.println(var8.getMessage());
        }

    }

    public static void findNextHighlight(JTextComponent var0, String var1, Highlighter.HighlightPainter var2) {
        try {
            Highlighter var3 = var0.getHighlighter();
            Document var4 = var0.getDocument();
            String var5 = var4.getText(0, var4.getLength());
            if ((findNextPos = var5.toUpperCase().indexOf(var1.toUpperCase(), findNextPos)) >= 0) {
                System.out.println("Inside findNextHighlight\nFINDNEXTPOS : " + findNextPos + "\n");
                var3.addHighlight(findNextPos, findNextPos + var1.length(), var2);
                findNextPos += var1.length();
            }
        } catch (BadLocationException var6) {
            System.out.println(var6.getMessage());
        }

    }

    public void removeHighlighter(JTextComponent var1, int var2) {
        Highlighter var3 = this.textArea.getHighlighter();
        Highlighter.Highlight[] var4 = var3.getHighlights();
        if (var2 == 1) {
            for(int var5 = 0; var5 < var4.length; ++var5) {
                if (var4[var5].getPainter() instanceof MyHighlighterPainter) {
                    var3.removeHighlight(var4[var5]);
                }
            }
        }

    }

    private void jButton1ActionPerformed(ActionEvent var1) {
        String var2 = this.textArea.getText();
        if (this.count > 0) {
            this.count = 0;
            this.removeHighlighter(this.textArea, 1);
        }

        if (var2.isEmpty()) {
            JOptionPane.showMessageDialog((Component)null, "Please enter the word to be searched");
        } else {
            String[] var3 = var2.split(" ");
            String[] var4 = var3;
            int var5 = var3.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String var7 = var4[var6];
                if (var7.contains("\n")) {
                    String[] var8 = var7.split("\n");
                    String[] var9 = var8;
                    int var10 = var8.length;

                    for(int var11 = 0; var11 < var10; ++var11) {
                        String var12 = var9[var11];
                        if (var12.contains(this.findText.getText())) {
                            ++this.count;
                        }
                    }
                } else if (var7.contains(this.findText.getText())) {
                    ++this.count;
                }
            }
        }

        this.foundInPlaces.setText("Found in " + this.count + " places");
        this.highlight(this.textArea, this.findText.getText(), this.myHighlightPainter);
    }

    private void jButton4ActionPerformed(ActionEvent var1) {
        StyledDocument var2 = (StyledDocument)this.textArea.getDocument();
        int var3 = findNextPos - this.findText.getText().length();

        try {
            System.out.println("Inside replace -> pos : " + var3 + "\n FIND NEXT POS : " + findNextPos);
            Element var4 = var2.getCharacterElement(var3);
            AttributeSet var5 = var4.getAttributes();
            var2.remove(var3, this.findText.getText().length());
            var2.insertString(var3, this.replaceWithText.getText(), var5);
        } catch (BadLocationException var6) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

    }

    private void jButton3ActionPerformed(ActionEvent var1) {
        if (this.findText.getText().equals(this.previousSearchedText)) {
            this.previousSearchedText = this.findText.getText();
            findNextPos = 0;
        }

        if (findNextPos == 0) {
            System.out.println("FindNextPos : " + findNextPos + "\n");
            findNextHighlight(this.textArea, this.findText.getText(), this.myHighlightPainter);
        } else {
            try {
                System.out.println("FindNextPos : " + findNextPos);
                System.out.println("OldFindNextPos : " + oldFindNextPos + "\n");
                Highlighter var2 = this.textArea.getHighlighter();
                Document var3 = this.textArea.getDocument();
                String var4 = var3.getText(0, var3.getLength());
                this.removeHighlighter(this.textArea, 1);
                findNextHighlight(this.textArea, this.findText.getText(), this.myHighlightPainter);
                oldFindNextPos = findNextPos;
            } catch (BadLocationException var5) {
                System.out.println(var5.getMessage());
            }
        }

    }

    private void jButton2ActionPerformed(ActionEvent var1) {
        this.removeHighlighter(this.textArea, 1);
        StyledDocument var2 = (StyledDocument)this.textArea.getDocument();
        int var3 = 0;

        try {
            while((var3 = this.textArea.getText().toUpperCase().indexOf(this.findText.getText().toUpperCase(), var3)) >= 0) {
                System.out.println(var3);
                Element var4 = var2.getCharacterElement(var3);
                AttributeSet var5 = var4.getAttributes();
                var2.remove(var3, this.findText.getText().length());
                var2.insertString(var3, this.replaceWithText.getText(), var5);
                var3 += this.replaceWithText.getText().length();
            }
        } catch (BadLocationException var6) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

    }

    private void jMenuItem12ActionPerformed(ActionEvent var1) {
        JOptionPane.showMessageDialog((Component)null, "Created by \nNilaansh");
    }

    private void toLowerActionPerformed(ActionEvent var1) {
        System.out.println("Making Text LOWERCASE");
        String var2 = this.textArea.getSelectedText().toUpperCase();
        StyledDocument var3 = this.textArea.getStyledDocument();
        int var4 = this.textArea.getSelectionEnd();
        int var5 = this.textArea.getSelectionStart();
        Element var6 = var3.getCharacterElement(var5);
        AttributeSet var7 = var6.getAttributes();
        if (var4 != var5) {
            try {
                var3.remove(var5, var2.length());
                var3.insertString(var5, var2.toLowerCase(), var7);
            } catch (BadLocationException var9) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var9);
            }

        }
    }

    private void toUpperActionPerformed(ActionEvent var1) {
        System.out.println("Making Text UPPERCASE");
        String var2 = this.textArea.getSelectedText().toUpperCase();
        StyledDocument var3 = this.textArea.getStyledDocument();
        int var4 = this.textArea.getSelectionEnd();
        int var5 = this.textArea.getSelectionStart();
        Element var6 = var3.getCharacterElement(var5);
        AttributeSet var7 = var6.getAttributes();

        try {
            var3.remove(var5, var2.length());
            var3.insertString(var5, var2.toUpperCase(), var7);
        } catch (BadLocationException var9) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var9);
        }

    }

    private void centreAlignActionPerformed(ActionEvent var1) {
        System.out.println("Aligning text to CENTER");
        String var2 = this.textArea.getText();
        this.textArea.setText("");
        StyledDocument var3 = this.textArea.getStyledDocument();
        SimpleAttributeSet var4 = new SimpleAttributeSet();
        StyleConstants.setAlignment(var4, 1);
        var3.setParagraphAttributes(0, var3.getLength(), var4, false);

        try {
            var3.insertString(0, var2, var4);
        } catch (BadLocationException var6) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

    }

    private void justifyAlignActionPerformed(ActionEvent var1) {
        System.out.println("Aligning text to JUSTIFY");
        String var2 = this.textArea.getText();
        this.textArea.setText("");
        StyledDocument var3 = this.textArea.getStyledDocument();
        SimpleAttributeSet var4 = new SimpleAttributeSet();
        StyleConstants.setAlignment(var4, 3);
        var3.setParagraphAttributes(0, var3.getLength(), var4, false);

        try {
            var3.insertString(0, var2, var4);
        } catch (BadLocationException var6) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

    }

    private void jMenuItem2ActionPerformed(ActionEvent var1) {
        System.out.println("Aligning text to LEFT");
        String var2 = this.textArea.getText();
        this.textArea.setText("");
        StyledDocument var3 = this.textArea.getStyledDocument();
        SimpleAttributeSet var4 = new SimpleAttributeSet();
        StyleConstants.setAlignment(var4, 0);
        var3.setParagraphAttributes(0, var3.getLength(), var4, false);

        try {
            var3.insertString(0, var2, var4);
        } catch (BadLocationException var6) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

    }

    private void rightAlignActionPerformed(ActionEvent var1) {
        System.out.println("Aligning text to RIGHT");
        String var2 = this.textArea.getText();
        this.textArea.setText("");
        StyledDocument var3 = this.textArea.getStyledDocument();
        SimpleAttributeSet var4 = new SimpleAttributeSet();
        StyleConstants.setAlignment(var4, 2);
        var3.setParagraphAttributes(0, var3.getLength(), var4, false);

        try {
            var3.insertString(0, var2, var4);
        } catch (BadLocationException var6) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, (String)null, var6);
        }

    }

    public static class DrawingPanel extends JPanel {
        final int X = 5;
        private boolean rect = false;
        private boolean oval = false;
        private boolean line = false;
        private boolean triangle = false;
        private boolean pentagon = false;
        int r_x1;
        int r_y1;
        int r_w;
        int r_h;
        int l_x1;
        int l_x2;
        int l_y1;
        int l_y2;
        int t_x1;
        int t_y1;
        int t_x2;
        int t_y2;
        int t_x3;
        int t_y3;
        int p_x1;
        int p_x2;
        int p_x3;
        int p_x4;
        int p_x5;
        int p_y1;
        int p_y2;
        int p_y3;
        int p_y4;
        int p_y5;

        public DrawingPanel() {
        }

        public void rectangle() {
            this.r_x1 = 100;
            this.r_y1 = 150;
            this.r_w = 150;
            this.r_h = 100;
            this.rect = true;
            this.oval = false;
            this.line = false;
            this.triangle = false;
            this.pentagon = false;
            this.repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent var1) {
                    DrawingPanel.this.dragmouse(var1);
                }

                public void mouseMoved(MouseEvent var1) {
                }
            });
        }

        public void oval() {
            this.r_x1 = 200;
            this.r_y1 = 150;
            this.r_w = 100;
            this.r_h = 200;
            this.rect = false;
            this.line = false;
            this.oval = true;
            this.pentagon = false;
            this.triangle = false;
            this.repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent var1) {
                    DrawingPanel.this.dragmouse(var1);
                }

                public void mouseMoved(MouseEvent var1) {
                }
            });
        }

        public void line() {
            this.l_x1 = 100;
            this.l_y1 = 200;
            this.l_x2 = 200;
            this.l_y2 = 150;
            this.rect = false;
            this.line = true;
            this.oval = false;
            this.pentagon = false;
            this.triangle = false;
            this.repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent var1) {
                    DrawingPanel.this.dragmouse(var1);
                }

                public void mouseMoved(MouseEvent var1) {
                }
            });
        }

        public void triangle() {
            this.t_x1 = 110;
            this.t_y1 = 110;
            this.t_x2 = 100;
            this.t_y2 = 200;
            this.t_x3 = 200;
            this.t_y3 = 190;
            this.rect = false;
            this.line = false;
            this.oval = false;
            this.pentagon = false;
            this.triangle = true;
            this.repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent var1) {
                    DrawingPanel.this.dragmouse(var1);
                }

                public void mouseMoved(MouseEvent var1) {
                }
            });
        }

        public void pentagon() {
            this.p_x1 = 150;
            this.p_y1 = 100;
            this.p_x2 = 100;
            this.p_x3 = 120;
            this.p_x4 = 180;
            this.p_x5 = 200;
            this.p_y2 = 140;
            this.p_y5 = 140;
            this.p_y3 = 180;
            this.p_y4 = 180;
            this.rect = false;
            this.line = false;
            this.oval = false;
            this.pentagon = true;
            this.triangle = false;
            this.repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent var1) {
                    DrawingPanel.this.dragmouse(var1);
                }

                public void mouseMoved(MouseEvent var1) {
                }
            });
        }

        public void clear() {
            this.rect = false;
            this.line = false;
            this.oval = false;
            this.pentagon = false;
            this.triangle = false;
            this.repaint();
        }

        protected void paintComponent(Graphics var1) {
            super.paintComponent(var1);
            if (this.rect) {
                var1.fillRect(this.r_x1, this.r_y1, this.r_w, this.r_h);
                var1.setColor(Color.white);
                var1.fillOval(this.r_x1 - 5, this.r_y1 - 5, 10, 10);
                var1.fillOval(this.r_x1 + this.r_w - 5, this.r_y1 - 5, 10, 10);
                var1.fillOval(this.r_x1 - 5, this.r_y1 + this.r_h - 5, 10, 10);
                var1.fillOval(this.r_x1 + this.r_w - 5, this.r_y1 + this.r_h - 5, 10, 10);
                var1.setColor(Color.BLUE);
                var1.fillOval(this.r_x1 + this.r_w / 2 - 5, this.r_y1 + this.r_h / 2 - 5, 10, 10);
            } else if (this.oval) {
                var1.setColor(Color.CYAN);
                var1.fillOval(this.r_x1, this.r_y1, this.r_w, this.r_h);
                var1.setColor(Color.white);
                var1.fillOval(this.r_x1 - 5, this.r_y1 - 5, 10, 10);
                var1.fillOval(this.r_x1 + this.r_w - 5, this.r_y1 - 5, 10, 10);
                var1.fillOval(this.r_x1 - 5, this.r_y1 + this.r_h - 5, 10, 10);
                var1.fillOval(this.r_x1 + this.r_w - 5, this.r_y1 + this.r_h - 5, 10, 10);
                var1.setColor(Color.RED);
                var1.fillOval(this.r_x1 + this.r_w / 2 - 5, this.r_y1 + this.r_h / 2 - 5, 10, 10);
            } else if (this.line) {
                Graphics2D var2 = (Graphics2D)var1;
                ((Graphics2D)var1).setStroke(new BasicStroke(5.0F));
                var1.setColor(Color.GREEN);
                var1.drawLine(this.l_x1, this.l_y1, this.l_x2, this.l_y2);
                var1.setColor(Color.white);
                var1.fillOval(this.l_x1 - 5, this.l_y1 - 5, 10, 10);
                var1.fillOval(this.l_x2 - 5, this.l_y2 - 5, 10, 10);
                var1.setColor(Color.black);
                var1.fillOval((this.l_x1 + this.l_x2) / 2 - 5, (this.l_y1 + this.l_y2) / 2 - 5, 10, 10);
            } else {
                int[] var3;
                int[] var4;
                if (this.triangle) {
                    var1.setColor(Color.orange);
                    var4 = new int[]{this.t_x1, this.t_x2, this.t_x3};
                    var3 = new int[]{this.t_y1, this.t_y2, this.t_y3};
                    var1.fillPolygon(var4, var3, 3);
                    var1.setColor(Color.white);
                    var1.fillOval((this.t_x1 + this.t_x2 + this.t_x3) / 3 - 5, (this.t_y1 + this.t_y2 + this.t_y3) / 3 - 5, 10, 10);
                    var1.setColor(Color.YELLOW);
                    var1.fillOval(this.t_x1 - 5, this.t_y1 - 5, 10, 10);
                    var1.fillOval(this.t_x2 - 5, this.t_y2 - 5, 10, 10);
                    var1.fillOval(this.t_x3 - 5, this.t_y3 - 5, 10, 10);
                } else if (this.pentagon) {
                    var1.setColor(Color.YELLOW);
                    var4 = new int[]{this.p_x1, this.p_x2, this.p_x3, this.p_x4, this.p_x5};
                    var3 = new int[]{this.p_y1, this.p_y2, this.p_y3, this.p_y4, this.p_y5};
                    var1.fillPolygon(var4, var3, 5);
                    var1.setColor(Color.orange);
                    var1.fillOval(this.p_x1 - 5, this.p_y1 - 5, 10, 10);
                    var1.fillOval(this.p_x2 - 5, this.p_y2 - 5, 10, 10);
                    var1.fillOval(this.p_x3 - 5, this.p_y3 - 5, 10, 10);
                    var1.fillOval(this.p_x4 - 5, this.p_y4 - 5, 10, 10);
                    var1.fillOval(this.p_x5 - 5, this.p_y5 - 5, 10, 10);
                    var1.setColor(Color.black);
                    var1.fillOval((this.p_x1 + this.p_x2 + this.p_x3 + this.p_x4 + this.p_x5) / 5 - 5, (this.p_y1 + this.p_y2 + this.p_y3 + this.p_y4 + this.p_y5) / 5 - 5, 10, 10);
                }
            }

        }

        public void dragmouse(MouseEvent var1) {
            int var2;
            int var3;
            if (!this.rect && !this.oval) {
                if (this.line) {
                    if (var1.getX() <= (this.l_x1 + this.l_x2) / 2 + 5 && var1.getX() >= (this.l_x1 + this.l_x2) / 2 - 5 && var1.getY() <= (this.l_y1 + this.l_y2) / 2 + 5 && var1.getY() >= (this.l_y1 + this.l_y2) / 2 - 5) {
                        var2 = (this.l_x1 + this.l_x2) / 2;
                        var3 = (this.l_y1 + this.l_y2) / 2;
                        this.l_x1 += var1.getX() - var2;
                        this.l_y1 += var1.getY() - var3;
                        this.l_x2 += var1.getX() - var2;
                        this.l_y2 += var1.getY() - var3;
                        this.repaint();
                    } else if (var1.getX() <= this.l_x1 + 5 && var1.getX() >= this.l_x1 - 5 && var1.getY() <= this.l_y1 + 5 && var1.getY() >= this.l_y1 - 5) {
                        this.l_x1 = var1.getX();
                        this.l_y1 = var1.getY();
                        this.repaint();
                    } else if (var1.getX() <= this.l_x2 + 5 && var1.getX() >= this.l_x2 - 5 && var1.getY() <= this.l_y2 + 5 && var1.getY() >= this.l_y2 - 5) {
                        this.l_x2 = var1.getX();
                        this.l_y2 = var1.getY();
                        this.repaint();
                    }
                } else if (this.triangle) {
                    if (var1.getX() <= (this.t_x1 + this.t_x2 + this.t_x3) / 3 + 5 && var1.getX() >= (this.t_x1 + this.t_x2 + this.t_x3) / 3 - 5 && var1.getY() <= (this.t_y1 + this.t_y2 + this.t_y3) / 3 + 5 && var1.getY() >= (this.t_y1 + this.t_y2 + this.t_y3) / 3 - 5) {
                        var2 = (this.t_x1 + this.t_x2 + this.t_x3) / 3;
                        var3 = (this.t_y1 + this.t_y2 + this.t_y3) / 3;
                        this.t_x1 += var1.getX() - var2;
                        this.t_y1 += var1.getY() - var3;
                        this.t_x2 += var1.getX() - var2;
                        this.t_y2 += var1.getY() - var3;
                        this.t_x3 += var1.getX() - var2;
                        this.t_y3 += var1.getY() - var3;
                        this.repaint();
                    } else if (var1.getX() >= this.t_x1 - 5 && var1.getX() <= this.t_x1 + 5 && var1.getY() >= this.t_y1 - 5 & var1.getY() <= this.t_y1 + 5) {
                        this.t_x1 = var1.getX();
                        this.t_y1 = var1.getY();
                        this.repaint();
                    } else if (var1.getX() >= this.t_x2 - 5 && var1.getX() <= this.t_x2 + 5 && var1.getY() >= this.t_y2 - 5 && var1.getY() <= this.t_y2 + 5) {
                        this.t_x2 = var1.getX();
                        this.t_y2 = var1.getY();
                        this.repaint();
                    } else if (var1.getX() >= this.t_x3 - 5 && var1.getX() <= this.t_x3 + 5 && var1.getY() >= this.t_y3 - 5 && var1.getY() <= this.t_y3 + 5) {
                        this.t_x3 = var1.getX();
                        this.t_y3 = var1.getY();
                        this.repaint();
                    }
                } else if (this.pentagon) {
                    if (var1.getX() <= (this.p_x1 + this.p_x2 + this.p_x3 + this.p_x4 + this.p_x5) / 5 + 5 && var1.getX() >= (this.p_x1 + this.p_x2 + this.p_x3 + this.p_x4 + this.p_x5) / 5 - 5 && var1.getY() >= (this.p_y1 + this.p_y2 + this.p_y3 + this.p_y4 + this.p_y5) / 5 - 5 && var1.getY() <= (this.p_y1 + this.p_y2 + this.p_y3 + this.p_y4 + this.p_y5) / 5 + 5) {
                        var2 = (this.p_x1 + this.p_x2 + this.p_x3 + this.p_x4 + this.p_x5) / 5;
                        var3 = (this.p_y1 + this.p_y2 + this.p_y3 + this.p_y4 + this.p_y5) / 5;
                        this.p_x1 += var1.getX() - var2;
                        this.p_y1 += var1.getY() - var3;
                        this.p_x2 += var1.getX() - var2;
                        this.p_y2 += var1.getY() - var3;
                        this.p_x3 += var1.getX() - var2;
                        this.p_y3 += var1.getY() - var3;
                        this.p_x4 += var1.getX() - var2;
                        this.p_y4 += var1.getY() - var3;
                        this.p_x5 += var1.getX() - var2;
                        this.p_y5 += var1.getY() - var3;
                        this.repaint();
                    } else if (var1.getX() <= this.p_x1 + 5 & var1.getX() >= this.p_x1 - 5 && var1.getY() >= this.p_y1 - 5 && var1.getY() <= this.p_y1 + 5) {
                        this.p_x1 = var1.getX();
                        this.p_y1 = var1.getY();
                        this.repaint();
                    } else if (var1.getX() <= this.p_x2 + 5 & var1.getX() >= this.p_x2 - 5 && var1.getY() >= this.p_y2 - 5 && var1.getY() <= this.p_y2 + 5) {
                        this.p_x2 = var1.getX();
                        this.p_y2 = var1.getY();
                        this.repaint();
                    } else if (var1.getX() <= this.p_x3 + 5 & var1.getX() >= this.p_x3 - 5 && var1.getY() >= this.p_y3 - 5 && var1.getY() <= this.p_y3 + 5) {
                        this.p_x3 = var1.getX();
                        this.p_y3 = var1.getY();
                        this.repaint();
                    } else if (var1.getX() <= this.p_x4 + 5 & var1.getX() >= this.p_x4 - 5 && var1.getY() >= this.p_y4 - 5 && var1.getY() <= this.p_y4 + 5) {
                        this.p_x4 = var1.getX();
                        this.p_y4 = var1.getY();
                        this.repaint();
                    } else if (var1.getX() <= this.p_x5 + 5 & var1.getX() >= this.p_x5 - 5 && var1.getY() >= this.p_y5 - 5 && var1.getY() <= this.p_y5 + 5) {
                        this.p_x5 = var1.getX();
                        this.p_y5 = var1.getY();
                        this.repaint();
                    }
                }
            } else {
                if (var1.getX() <= this.r_x1 + this.r_w / 2 + 5 && var1.getX() >= this.r_x1 + this.r_w / 2 - 5 && var1.getY() <= this.r_y1 + this.r_h / 2 + 5 && var1.getY() >= this.r_y1 + this.r_h / 2 - 5) {
                    this.r_x1 = var1.getX() - this.r_w / 2;
                    this.r_y1 = var1.getY() - this.r_h / 2;
                    this.repaint();
                }

                if (var1.getX() <= this.r_x1 + this.r_w + 5 && var1.getX() >= this.r_x1 + this.r_w - 5 && var1.getY() <= this.r_y1 + this.r_h + 5 && var1.getY() >= this.r_y1 + this.r_h - 5) {
                    this.r_w = var1.getX() - this.r_x1;
                    this.r_h = var1.getY() - this.r_y1;
                    if (this.r_w < 20) {
                        this.r_w = 20;
                    }

                    if (this.r_h < 20) {
                        this.r_h = 20;
                    }

                    this.repaint();
                }

                if (var1.getX() <= this.r_x1 + 5 && var1.getX() >= this.r_x1 - 5 && var1.getY() <= this.r_y1 + 5 && var1.getY() >= this.r_y1 - 5) {
                    var2 = this.r_x1 + this.r_w;
                    var3 = this.r_y1 + this.r_h;
                    this.r_x1 = var1.getX();
                    this.r_y1 = var1.getY();
                    this.r_w = var2 - this.r_x1;
                    this.r_h = var3 - this.r_y1;
                    if (this.r_w < 20) {
                        this.r_w = 20;
                    }

                    if (this.r_h < 20) {
                        this.r_h = 20;
                    }

                    this.repaint();
                }

                if (var1.getX() <= this.r_x1 + this.r_w + 5 && var1.getX() >= this.r_x1 + this.r_w - 5 && var1.getY() <= this.r_y1 + 5 && var1.getY() >= this.r_y1 - 5) {
                    var2 = this.r_y1 + this.r_h;
                    this.r_w = var1.getX() - this.r_x1;
                    this.r_y1 = var1.getY();
                    this.r_h = var2 - this.r_y1;
                    if (this.r_w < 20) {
                        this.r_w = 20;
                    }

                    if (this.r_h < 20) {
                        this.r_h = 20;
                    }

                    this.repaint();
                }

                if (var1.getX() <= this.r_x1 + 5 && var1.getX() >= this.r_x1 - 5 && var1.getY() <= this.r_y1 + this.r_h + 5 && var1.getY() >= this.r_y1 + this.r_h - 5) {
                    var2 = this.r_x1 + this.r_w;
                    this.r_h = var1.getY() - this.r_y1;
                    this.r_x1 = var1.getX();
                    this.r_w = var2 - this.r_x1;
                    if (this.r_w < 20) {
                        this.r_w = 20;
                    }

                    if (this.r_h < 20) {
                        this.r_h = 20;
                    }

                    this.repaint();
                }
            }

        }
    }
}
