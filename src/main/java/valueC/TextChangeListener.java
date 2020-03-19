package valueC;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextChangeListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        System.out.println("insertUpdate");
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("removeUpdate");
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("changeUpdate");
    }
}
