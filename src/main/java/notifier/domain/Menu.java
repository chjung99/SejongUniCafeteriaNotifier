package notifier.domain;

import java.util.List;

public class Menu {
    private Long id;
    private String date;
    private String mealTime;
    private List<String> items;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

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
