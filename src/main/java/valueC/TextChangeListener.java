package valueC;

import valueC.utils.ConversionUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Map;

public class TextChangeListener implements DocumentListener
{
    private JTextField startTextField;
    private JTextField endTextField;
    private Map<String, Double> names;

    public TextChangeListener(String direction)
    {

    }
    @Override
    public void insertUpdate(DocumentEvent e) { ConversionUtil.exeConversion(); }

    @Override
    public void removeUpdate(DocumentEvent e) { ConversionUtil.exeConversion(); }

    @Override
    public void changedUpdate(DocumentEvent e) { ConversionUtil.exeConversion(); }

    public void setTextField(String converted, String direction)
    {
        if(direction.equals("start"))
        {
            try
            {
                endTextField.setText(converted);
            }
            catch(IllegalStateException e)
            {
                System.out.println("Caught Start");
            }
        }
        else
        {
            try
            {
                startTextField.setText(converted);
            }
            catch(IllegalStateException e)
            {
                System.out.println("Caught End");
            }
        }
    }
}