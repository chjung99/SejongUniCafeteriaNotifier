package notifier.domain.skillresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarouselOutput implements Output{
    @JsonProperty("carousel")
    private Carousel carousel;

    public Carousel getCarousel() {
        return carousel;
    }

    public void setCarousel(Carousel carousel) {
        this.carousel = carousel;
    }
}
