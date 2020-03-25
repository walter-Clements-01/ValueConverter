package valueC;

import valueC.utils.ComboBoxUtil;

import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame
{
    private MyPanel myPanel;
    public MyFrame() throws Exception {
        setTitle("Convertitore di valuta");
        setVisible(true);
        setResizable(true);
        myPanel = new MyPanel();
        add(myPanel);
        pack();
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                ComboBoxUtil.save(myPanel.getCurrentComboBoxes());
                System.exit(0);
            }
        });
    }
}
