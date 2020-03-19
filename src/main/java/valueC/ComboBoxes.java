package valueC;

import java.io.Serializable;
import java.util.Map;

public class ComboBoxes implements Serializable {
    private String boxInit;
    private String boxFin;

    public String getBoxInit() {
        return boxInit;
    }

    public void setBoxInit(String boxInit) {
        this.boxInit = boxInit;
    }

    public String getBoxFin() {
        return boxFin;
    }

    public void setBoxFin(String boxFin) {
        this.boxFin = boxFin;
    }
}
