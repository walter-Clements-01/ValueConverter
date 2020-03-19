package valueC;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesNames {
    private String Pblshd;
    private List<CurrenciesDetail> CcyTbl;

    public String getPblshd() {
        return Pblshd;
    }

    public List<CurrenciesDetail> getCcyTbl() {
        return CcyTbl;
    }

    public class CurrenciesDetail
    {
        private String CtryNm;
        private String CcyNm;
        private String Ccy;
        private String CcyNbr;
        private String CcyMnrUnts;

        public String getCtryNm() {
            return CtryNm;
        }

        public String getCcyNm() {
            return CcyNm;
        }

        public String getCcy() {
            return Ccy;
        }

        public String getCcyNbr() {
            return CcyNbr;
        }

        public String getCcyMnrUnts() {
            return CcyMnrUnts;
        }
    }
}
