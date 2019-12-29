import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GeneratorDate {
    static String positiveDate(int valueDay) {
        if (valueDay == -500) {
            LocalDate date = (LocalDate.now()).plusDays(valueDay);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" ");
            return formatter.format(date);
        }
        LocalDate date = (LocalDate.now()).plusDays(valueDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(date);
    }
}
