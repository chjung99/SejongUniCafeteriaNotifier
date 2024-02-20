package notifier.domain.skillresponse;

import java.util.ArrayList;

public class Carousel {
    private String type;
    private ArrayList<Card> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Card> getItems() {
        return items;
    }

    public void setItems(ArrayList<Card> items) {
        this.items = items;
    }
}
