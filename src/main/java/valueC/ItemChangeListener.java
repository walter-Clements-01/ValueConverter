package valueC;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ItemChangeListener implements ItemListener
{
    @Override
    public void itemStateChanged(ItemEvent e) { exeConversion("start"); }

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
}
