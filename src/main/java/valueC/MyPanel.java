package valueC;

import com.sun.deploy.security.SelectableSecurityManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

public class MyPanel extends JPanel
{
    private JLabel viLabel;
    private JLabel vfLabel;
    private JComboBox boxInit;
    private JComboBox boxFin;
    private JTextField startTextField;
    private JTextField endTextField;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private CurrenciesRetriever currenciesRetriever;
    private Currencies currencies;
    private Map<String, Double> rates;
    private Map<String, Double> names;
    public MyPanel() throws Exception {
        currenciesRetriever = new CurrenciesRetriever();
        currencies = currenciesRetriever.getCurrencies();
        rates=currencies.getRates();
        names=currencies.getRatesNames();

        topPanel = new JPanel(new GridLayout(1,2,20,20));
        viLabel = new JLabel("Valuta da convertire");
        viLabel.setFont(new Font(null,Font.BOLD, 30));
        vfLabel = new JLabel("Valuta convertita");
        vfLabel.setFont(new Font(null,Font.BOLD, 30));
        topPanel.add(viLabel, BorderLayout.CENTER);
        topPanel.add(vfLabel, BorderLayout.CENTER);

        middlePanel = new JPanel(new GridLayout(1,2,20,20));
        boxInit = new JComboBox();
        boxInit.setSize(100,75);
        boxInit.setFont(new Font(null,Font.BOLD, 15));
        boxFin = new JComboBox();
        boxFin.setFont(new Font(null,Font.BOLD, 15));
        boxFin.setSize(100,75);
        middlePanel.add(boxInit);
        middlePanel.add(boxFin);
        setComboBoxes();

        bottomPanel = new JPanel(new GridLayout(1,2,20,20));

        startTextField = new JTextField();
        startTextField.setPreferredSize(new Dimension(200,75));
        startTextField.setFont(new Font(null,Font.BOLD, 20));

        endTextField = new JTextField();
        endTextField.setPreferredSize(new Dimension(200,75));
        endTextField.setFont(new Font(null,Font.BOLD, 20));

        bottomPanel.add(startTextField, BorderLayout.WEST);
        bottomPanel.add(endTextField, BorderLayout.EAST);

        setLayout(new GridLayout(3,1,20,20));
        add(topPanel);
        add(middlePanel);
        add(bottomPanel);

        boxInit.addItemListener(new ItemChangeListener());
        boxFin.addItemListener(new ItemChangeListener());
        startTextField.getDocument().addDocumentListener(new TextChangeListener("start"));
        endTextField.getDocument().addDocumentListener(new TextChangeListener("end"));
    }
   public void setComboBoxes() throws Exception {
       for (Map.Entry<String, Double> entry : names.entrySet()) {

           boxInit.addItem(entry.getKey());
           boxFin.addItem(entry.getKey());
       }
       boxInit.setSelectedItem(ComboBoxUtil.load().getBoxInit());
       boxFin.setSelectedItem(ComboBoxUtil.load().getBoxFin());
    }
    public ComboBoxes getCurrentComboBoxes()
    {
        ComboBoxes comboBoxes = new ComboBoxes();
        comboBoxes.setBoxInit(boxInit.getSelectedItem().toString());
        comboBoxes.setBoxFin(boxFin.getSelectedItem().toString());
        return comboBoxes;
    }

    public void exeConversion(String textField) {
        Double toConvert;
        try{
            if(textField.equals("start"))
            {
                toConvert = Double.valueOf(startTextField.getText());
                Double converted=getConversion(toConvert,names.get(boxInit.getSelectedItem().toString()),names.get(boxFin.getSelectedItem().toString()));
                setTextField(converted.toString(),textField);
            }
            else
            {
                toConvert = Double.valueOf(endTextField.getText());
                Double converted=getConversion(toConvert,names.get(boxFin.getSelectedItem().toString()),names.get(boxInit.getSelectedItem().toString()));
                setTextField(converted.toString(),textField);
            }

        }
        catch(NumberFormatException e){
            setTextField("0",textField);
        }
    }
    public Double getConversion(Double toConvert, Double startCoef, Double endCoef)
    {
        return (1/startCoef*toConvert)*endCoef;
    }
    public void setTextField(String converted, String textField)
    {
        if(textField.equals("start"))
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
    private class TextChangeListener implements DocumentListener
    {
        String textField;
        public TextChangeListener(String textField)
        {
            this.textField=textField;
        }
        @Override
        public void insertUpdate(DocumentEvent e) { exeConversion(textField); }

        @Override
        public void removeUpdate(DocumentEvent e) { exeConversion(textField); }

        @Override
        public void changedUpdate(DocumentEvent e) { exeConversion(textField); }
    }
    private class ItemChangeListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e) { exeConversion("start"); }
    }
}
