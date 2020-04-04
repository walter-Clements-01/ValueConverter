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
    private JComboBox boxInit;
    private JComboBox boxFin;

    private String startText;
    private Double startRate;
    private Double endRate;

    public TextChangeListener(JTextField startTextField, JTextField endTextField, Map<String, Double> names, JComboBox boxInit, JComboBox boxFin)
    {
        this.startTextField=startTextField;
        this.endTextField=endTextField;
        this.names=names;
        this.boxInit=boxInit;
        this.boxFin=boxFin;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        setStatus();
        String convertedText=ConversionUtil.exeConversion(startText,startRate,endRate);
        setTextField(convertedText);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setStatus();
        String convertedText=ConversionUtil.exeConversion(startText,startRate,endRate);
        setTextField(convertedText);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        setStatus();
        String convertedText=ConversionUtil.exeConversion(startText,startRate,endRate);
        setTextField(convertedText);
    }

    private void setStatus()
    {
        /*if(direction.equals("start"))
        {*/
            startText=startTextField.getText();
            startRate=names.get(boxInit.getSelectedItem().toString());
            endRate=names.get(boxFin.getSelectedItem().toString());
        /*}
        else
        {
            startText=endTextField.getText();
            startRate=names.get(boxFin.getSelectedItem().toString());
            endRate=names.get(boxInit.getSelectedItem().toString());
        }*/
    }

    private void setTextField(String convertedText)
    {
        /*if(direction.equals("start"))
        {*/
            try
            {
                endTextField.setText(convertedText);
            }
            catch(IllegalStateException e)
            {
                System.out.println("Caught Start");
            }
        /*}
        else
        {
            try
            {
                startTextField.setText(convertedText);
            }
            catch(IllegalStateException e)
            {
                System.out.println("Caught End");
            }
        }*/
    }
}