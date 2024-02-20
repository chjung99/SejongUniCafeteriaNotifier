package notifier.domain.skillresponse;

import java.util.Map;

public class ListItem {
    private String title;
    private String description;
    private String action;
    private String messageText;
//    private Map<String, ?> extra;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

//    public Map<String, ?> getExtra() {
//        return extra;
//    }
//
//    public void setExtra(Map<String, ?> extra) {
//        this.extra = extra;
//    }
}
