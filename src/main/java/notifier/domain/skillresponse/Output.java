package notifier.domain.skillresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Output{
    @JsonProperty("simpleText")
    private SimpleText simpleText;

    public SimpleText getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(SimpleText simpleText) {
        this.simpleText = simpleText;
    }
}
