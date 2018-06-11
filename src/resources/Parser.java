package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Parser {

    /**
     * Private constructor
     */
    private Parser(){};

    /**
     * Check if String value can be changed to an integer
     * @param val   The value to perform a check on
     * @return      (bool) True/False
     */
    public static boolean isInt(String val){
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Reads an array of strings, command-line parameters, and places them in a dictionary
     * @param parameters    The args collection from Main
     * @return              (Map) A dictionary (argument=[value/empty string])
     */
    public static Map<String, String> splitParameters (String[] parameters) {
        Map<String, String> result = new HashMap<>();
        for (String value : parameters) {
            if (value.indexOf("=") > 0) {
                String[] split = value.split("=");
                result.put(split[0].toLowerCase(), split[1]);
            }else{
                result.put(value.toLowerCase(), "");
            }
        }

        return result;
    }

    /**
     * Performs a check how many of the main categories has been passed as arguments
     * (Schools, Students, Events, Teachers)
     * @param arguments The dictionary returned from splitParameters
     * @return          The amount of main categories passed
     */
    public static int categoryControl(Map<String, String> arguments){
        String[] mainCategories = {"-schools","-students","-events","-teachers"};
        int hits = 0;
        for (String category : mainCategories) {
            if (arguments.containsKey(category))
                hits++;
        }

        return hits;
    }

    /**
     * Throughout, in the CSVs ie., the date formats are stored as yyyyMMdd format.
     * This will parse that format to a Date object instead
     * @param date      The yyyyMMdd value
     * @return          (Date) The date object for that day
     */
    public static Date parseStringToDate(String date) {
        Date formatDate = new Date();
        try {
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd");
            formatDate = dFormat.parse(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return formatDate;
    }

    public static String parseDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }
}
