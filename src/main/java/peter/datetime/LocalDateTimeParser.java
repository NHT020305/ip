package peter.datetime;

import java.time.LocalDateTime;

import peter.exception.InvalidDateTimeFormatException;
import peter.utils.ErrorMessage;

/**
 * A utility class to parse and convert date-time strings into {@code LocalDateTime} objects.
 */
public class LocalDateTimeParser {

    protected String input;

    /**
     * Constructs a LocalDateTimeParser with the specified input string.
     *
     * @param input The date-time string to parse.
     *              The expected format is "dd/MM/yyyy HHmm".
     */

    public LocalDateTimeParser(String input) {
        this.input = input;
    }

    /**
     * Converts the input date-time string to a {@code LocalDateTime} object.
     *
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws ArrayIndexOutOfBoundsException If the input string is not in the expected format.
     * @throws NumberFormatException If date or time components cannot be parsed as integers.
     */
    public LocalDateTime convertToTime() throws InvalidDateTimeFormatException {
        String[] parts = splitInput(input);
        int[] dateComponents = parseDatePart(parts[0]);
        int[] timeComponents = parseTimePart(parts[1]);
        return LocalDateTime.of(dateComponents[2], dateComponents[1], dateComponents[0],
                timeComponents[0], timeComponents[1]);
    }

    private String[] splitInput(String input) throws InvalidDateTimeFormatException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new InvalidDateTimeFormatException(ErrorMessage.INVALID_DATE_TIME);
        }
        return parts;
    }

    private int[] parseDatePart(String datePart) throws InvalidDateTimeFormatException {
        String[] dateComponents = datePart.split("/");
        if (dateComponents.length != 3) {
            throw new InvalidDateTimeFormatException(ErrorMessage.INVALID_DATE_TIME);
        }
        if (dateComponents[0].length() != 2 || dateComponents[1].length() != 2 || dateComponents[2].length() != 4) {
            throw new InvalidDateTimeFormatException(ErrorMessage.INVALID_DATE_TIME);
        }
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);
        return new int[] { day, month, year };
    }

    private int[] parseTimePart(String timePart) throws InvalidDateTimeFormatException {
        String[] timeComponents = timePart.split(":");
        if (timeComponents.length != 2) {
            throw new InvalidDateTimeFormatException(ErrorMessage.INVALID_DATE_TIME);
        }
        if (timeComponents[0].length() != 2 || timeComponents[1].length() != 2) {
            throw new InvalidDateTimeFormatException(ErrorMessage.INVALID_DATE_TIME);
        }
        int hour = Integer.parseInt(timeComponents[0]);
        int minute = Integer.parseInt(timeComponents[1]);
        return new int[] { hour, minute };
    }


}
