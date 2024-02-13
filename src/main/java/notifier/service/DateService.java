package notifier.service;

import java.time.LocalDate;

public class DateService {

    public String getToday() {
        return LocalDate.now().toString();
    }
}
