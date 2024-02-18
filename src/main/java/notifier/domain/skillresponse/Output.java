package notifier.domain.skillresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Output{
    @JsonProperty("simpleText")
    private SimpleText simpleText;
    @JsonProperty("simpleImage")
    private SimpleImage simpleImage;
    public SimpleText getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(SimpleText simpleText) {
        this.simpleText = simpleText;
    }

    public SimpleImage getSimpleImage() {
        return simpleImage;
    }

    public void setSimpleImage(SimpleImage simpleImage) {
        this.simpleImage = simpleImage;
    }
}
