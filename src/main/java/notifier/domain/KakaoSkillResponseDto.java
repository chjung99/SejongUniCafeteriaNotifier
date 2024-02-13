package notifier.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoSkillResponseDto {
    private String version;
    private SkillTemplate template;

    public class SkillTemplate {
        private Output[] outputs;
        class Output{
            @JsonProperty("simpleText")
            private SimpleText simpleText;

            public SimpleText getSimpleText() {
                return simpleText;
            }

            public void setSimpleText(SimpleText simpleText) {
                this.simpleText = simpleText;
            }
        }
        public static class SimpleText{
            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }


}
