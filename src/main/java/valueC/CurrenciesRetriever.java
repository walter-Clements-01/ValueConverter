package valueC;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class CurrenciesRetriever
{
    /*public static void  main(String args[]) throws Exception {
        getCurrencies();
    }*/
    public Currencies getCurrencies() throws Exception
    {
        URL url = new URL("http://data.fixer.io/api/latest?access_key=2885eda4c69474b6c7e7ec9c7e264813");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");
        int code = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println(code);
        System.out.println(content);
        con.disconnect();

        Gson gson = new Gson();
        Currencies obj = gson.fromJson(content.toString(), Currencies.class);
        InputStream inputStream = getClass().getResourceAsStream("/CurrenciesNames.json");
        String currenciesJson = convert(inputStream,"UTF-8");
        CurrenciesNames obj2 = gson.fromJson(currenciesJson,CurrenciesNames.class);
        setNames(obj, obj2);

        System.out.println(obj2.getPblshd());
        System.out.println(obj.isSuccess());
        System.out.println(obj.getRates());
        return obj;
    }
    public String convert(InputStream inputStream, String charset) throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        return stringBuilder.toString();
    }
    private void setNames(Currencies currencies, CurrenciesNames currenciesNames)
    {
        Map<String, Double> ratesNames = new HashMap<>();
        //ArrayList<String> codes = new ArrayList(currencies.getRates().keySet());

        List<CurrenciesNames.CurrenciesDetail> details = currenciesNames.getCcyTbl();
        for(Map.Entry<String,Double> entryRate : currencies.getRates().entrySet())
        {
            for(CurrenciesNames.CurrenciesDetail detail : details)
            {
                if(entryRate.getKey().equals(detail.getCcy()))
                {
                    ratesNames.put(detail.getCtryNm()+" ("+entryRate.getKey()+")",entryRate.getValue());
                }
            }
        }
        Map<String, Double> treeMap = new TreeMap<String, Double>(ratesNames);
        currencies.setRatesNames(treeMap);
    }
}
