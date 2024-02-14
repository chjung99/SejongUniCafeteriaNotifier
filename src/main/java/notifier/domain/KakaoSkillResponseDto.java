package notifier.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import notifier.domain.skillresponse.SkillTemplate;

public class KakaoSkillResponseDto {
    private String version;
    private SkillTemplate template;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public SkillTemplate getTemplate() {
        return template;
    }

    public void setTemplate(SkillTemplate template) {
        this.template = template;
    }
}
