package peter.datetime;

import java.time.LocalDateTime;

import peter.exception.InvalidDateTimeFormatException;

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

        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new InvalidDateTimeFormatException(
                    "OOPS!!! Invalid date & time format.");
        }

        String datePart = parts[0];
        String timePart = parts[1];

        String[] dateComponents = datePart.split("/");
        if (dateComponents.length != 3) {
            throw new InvalidDateTimeFormatException(
                    "OOPS!!! Invalid date & time format.");
        }

        if (dateComponents[0].length() != 2 || dateComponents[1].length() != 2
                || dateComponents[2].length() != 4) {
            throw new InvalidDateTimeFormatException(
                    "OOPS!!! Invalid date & time format.");
        }

        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);

        String[] timeComponents = timePart.split(":");
        if (timeComponents.length != 2) {
            throw new InvalidDateTimeFormatException(
                    "OOPS!!! Invalid date & time format.");
        }

        if (timeComponents[0].length() != 2 || timeComponents[1].length() != 2) {
            throw new InvalidDateTimeFormatException(
                    "OOPS!!! Invalid date & time format.");
        }

        int hour = Integer.parseInt(timeComponents[0]);
        int minute = Integer.parseInt(timeComponents[1]);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

}
