package notifier.domain.skillresponse;


public class SkillTemplate {
    private Output[] outputs;
    private QuickReply[] quickReplies;

    public Output[] getOutputs() {
        return outputs;
    }

    public void setOutputs(Output[] outputs) {
        this.outputs = outputs;
    }

    public QuickReply[] getQuickReplies() {
        return quickReplies;
    }

    public void setQuickReplies(QuickReply[] quickReplies) {
        this.quickReplies = quickReplies;
    }
}