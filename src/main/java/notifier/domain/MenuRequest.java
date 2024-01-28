package notifier.domain;

import java.util.List;

public class MenuRequest {
    private String date;
    private String mealTime;
    private List<String> items;

    public String getDate() {
        return date;
    }
    public String getMealTime() {
        return mealTime;
    }
    public List<String> getItems() {
        return items;
    }
}
