package src.utils;

public class DateFormatValidator {
    
    private static final String[] SUPPORTED_FORMATS = {
        "dd/MM/yyyy", 
        "MM/dd/yyyy", 
        "yyyy/MM/dd"
    };
    
    /**
     * Validates if the given date format is supported
     * @param dateFormat the date format string to validate
     * @throws DateFormatException if the format is not supported
     */
    public static void validateDateFormat(String dateFormat) throws DateFormatException {
        if (dateFormat == null || dateFormat.trim().isEmpty()) {
            throw new DateFormatException("Date format cannot be null or empty");
        }
        
        for (String supportedFormat : SUPPORTED_FORMATS) {
            if (supportedFormat.equals(dateFormat)) {
                return; // Valid format found
            }
        }

        throw new DateFormatException(
            "Invalid date format: '" + dateFormat + "'. " +
            "Supported formats are: dd/MM/yyyy, MM/dd/yyyy, yyyy/MM/dd"
        );
    }
    
    /**
     * Gets all supported date formats
     * @return array of supported date format strings
     */
    public static String[] getSupportedFormats() {
        return SUPPORTED_FORMATS.clone();
    }
    
    /**
     * Gets a formatted string of all supported formats for display
     * @return formatted string of supported formats
     */
    public static String getSupportedFormatsString() {
        return String.join(", ", SUPPORTED_FORMATS);
    }
}
