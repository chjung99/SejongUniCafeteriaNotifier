package notifier.controller;

public class ChatbotResponse {
    private String version;
    private Template template;

    // Getter and Setter methods

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public static class Template {
        private Output[] outputs;

        public Output[] getOutputs() {
            return outputs;
        }

        public void setOutputs(Output[] outputs) {
            this.outputs = outputs;
        }
        // Getter and Setter methods
    }

    public static class Output {
        private TextCard textCard;

        public TextCard getTextCard() {
            return textCard;
        }

        public void setTextCard(TextCard textCard) {
            this.textCard = textCard;
        }

        // Getter and Setter methods


        public static class TextCard {
            private String title;
            private String description;
            private Button[] buttons;

            // Getter and Setter methods

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

            public Button[] getButtons() {
                return buttons;
            }

            public void setButtons(Button[] buttons) {
                this.buttons = buttons;
            }
        }

        public static class Button {
            private String action;
            private String label;
            private String webLinkUrl;

            // Getter and Setter methods

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getWebLinkUrl() {
                return webLinkUrl;
            }

            public void setWebLinkUrl(String webLinkUrl) {
                this.webLinkUrl = webLinkUrl;
            }
        }
    }
}
