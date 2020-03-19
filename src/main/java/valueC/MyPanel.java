package valueC;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class MyPanel extends JPanel
{
    private JLabel viLabel;
    private JLabel vfLabel;
    private JComboBox boxInit;
    private JComboBox boxFin;
    private JTextField startTextField;
    private JLabel endLabel;
    private JButton convButton;
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

        topPanel = new JPanel(new BorderLayout());
        viLabel = new JLabel("Valuta da convertire");
        viLabel.setFont(new Font(null,Font.BOLD, 20));
        vfLabel = new JLabel("Valuta convertita");
        vfLabel.setFont(new Font(null,Font.BOLD, 20));
        topPanel.add(viLabel, BorderLayout.WEST);
        topPanel.add(vfLabel, BorderLayout.EAST);

        middlePanel = new JPanel(new GridLayout(1,3,20,20));
        boxInit = new JComboBox();
        boxInit.setSize(100,20);
        boxInit.setFont(new Font(null,Font.BOLD, 15));
        convButton = new JButton("Converti");
        convButton.setFont(new Font(null,Font.BOLD, 20));
        boxFin = new JComboBox();
        boxFin.setFont(new Font(null,Font.BOLD, 15));
        boxFin.setSize(100,20);
        middlePanel.add(boxInit);
        middlePanel.add(convButton);
        middlePanel.add(boxFin);
        setComboBoxes();

        //convButton.addActionListener(new MyListener());

        bottomPanel = new JPanel(new BorderLayout());
        startTextField = new JTextField();
        startTextField.setPreferredSize(new Dimension(200,20));
        endLabel = new JLabel();
        endLabel.setPreferredSize(new Dimension(200,20));
        endLabel.setFont(new Font(null,Font.BOLD, 20));
        startTextField.setFont(new Font(null,Font.BOLD, 20));
        bottomPanel.add(startTextField, BorderLayout.WEST);
        bottomPanel.add(endLabel, BorderLayout.EAST);

        setLayout(new GridLayout(3,1,20,20));
        add(topPanel);
        add(middlePanel);
        add(bottomPanel);

        startTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                exeConversion();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                exeConversion();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                exeConversion();
            }
            private void exeConversion() {
                Double toConvert;
                try{
                    toConvert = Double.valueOf(startTextField.getText());
                    Double converted=getConversion(toConvert,names.get(boxInit.getSelectedItem().toString()),names.get(boxFin.getSelectedItem().toString()));
                    setEndLabel(converted.toString());
                }
                catch(NumberFormatException e){
                    setEndLabel("0");
                }
            }
            private Double getConversion(Double toConvert, Double startCoef, Double endCoef)
            {
                return (1/startCoef*toConvert)*endCoef;
            }
            private void setEndLabel(String converted)
            {
                endLabel.setText(converted);
            }
        });
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
}
