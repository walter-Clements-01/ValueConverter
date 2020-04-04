package valueC.utils;

public class ConversionUtil {

    public static String exeConversion(String startText, Double startRate, Double endRate) {
        Double toConvert;
        String convertedText = "";
        try {
            toConvert = Double.valueOf(startText);
            Double converted = getConversion(toConvert, startRate, endRate);
            convertedText = String.format("%.3f", converted).replace(",",".");
        } catch (NumberFormatException e) {
            convertedText = "0";
        }
        return convertedText;
    }

    public static Double getConversion(Double toConvert, Double startCoef, Double endCoef) {
        return ((1 / startCoef * toConvert) * endCoef);
    }

}
