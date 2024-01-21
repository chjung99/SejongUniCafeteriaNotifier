package notifier.domain;

import java.util.Date;
import java.util.List;

public class Menu {
    private Long id;
    private Date date;
    private String mealTime;
    private List<String> items;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getMealTime() {
        return mealTime;
    }
}
