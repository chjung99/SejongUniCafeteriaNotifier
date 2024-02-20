package notifier.domain.skillresponse;

import java.util.ArrayList;

public class ListCard implements Card{
    private ListItem header;
    private ArrayList<ListItem> items;

    public ListItem getHeader() {
        return header;
    }

    public void setHeader(ListItem header) {
        this.header = header;
    }

    public ArrayList<ListItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ListItem> items) {
        this.items = items;
    }
}
