package resources;

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
}
