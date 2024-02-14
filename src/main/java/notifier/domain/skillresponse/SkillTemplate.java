package notifier.domain.skillresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillTemplate {
    private Output[] outputs;

    public Output[] getOutputs() {
        return outputs;
    }

    public void setOutputs(Output[] outputs) {
        this.outputs = outputs;
    }
}