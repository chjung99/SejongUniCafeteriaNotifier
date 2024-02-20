package notifier.domain.skillresponse;

import java.util.ArrayList;

public class ListCard implements Card{
    private Header header;
    private ArrayList<ListItem> items;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ArrayList<ListItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ListItem> items) {
        this.items = items;
    }
}
