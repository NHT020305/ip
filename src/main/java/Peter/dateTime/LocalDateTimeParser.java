package Peter.dateTime;

import java.time.LocalDateTime;

public class LocalDateTimeParser {

    protected String input;

    public LocalDateTimeParser(String input) {
        this.input = input;
    }

    public LocalDateTime convertToTime() {
        String[] parts = input.split(" ");
        String datePart = parts[0];
        String timePart = parts[1];

        String[] dateComponents = datePart.split("/");
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);

        int hour = Integer.parseInt(timePart.substring(0, 2));
        int minute = Integer.parseInt(timePart.substring(2, 4));

        return LocalDateTime.of(year, month, day, hour, minute);
    }

}
