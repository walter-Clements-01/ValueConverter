package valueC;

import valueC.utils.ConversionUtil;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

public class ItemChangeListener implements ItemListener {

    private JTextField startTextField;
    private JTextField endTextField;
    private Map<String, Double> names;
    private JComboBox boxInit;
    private JComboBox boxFin;

    private String startText;
    private Double startRate;
    private Double endRate;

    public ItemChangeListener(JTextField startTextField, JTextField endTextField, Map<String, Double> names, JComboBox boxInit, JComboBox boxFin)
    {
        this.startTextField=startTextField;
        this.endTextField=endTextField;
        this.names=names;
        this.boxInit=boxInit;
        this.boxFin=boxFin;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        setStatus();
        String convertedText= ConversionUtil.exeConversion(startText,startRate,endRate);
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
    }
}
