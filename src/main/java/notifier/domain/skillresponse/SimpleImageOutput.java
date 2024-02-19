package notifier.domain.skillresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleImageOutput implements Output{
    @JsonProperty("simpleImage")
    private SimpleImage simpleImage;

    public SimpleImage getSimpleImage() {
        return simpleImage;
    }

    public void setSimpleImage(SimpleImage simpleImage) {
        this.simpleImage = simpleImage;
    }
}
