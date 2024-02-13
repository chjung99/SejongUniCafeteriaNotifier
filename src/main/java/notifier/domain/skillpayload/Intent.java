package notifier.domain.skillpayload;

import java.util.Optional;

import java.util.List;

public class Intent {
    private String id;
    private String name;
    private Optional<Extra> extra;

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Extra> getExtra() {
        return extra;
    }

    public void setExtra(Optional<Extra> extra) {
        this.extra = extra;
    }

    // Inner classes
    public static class Extra {
        private Reason reason;
        private Knowledge knowledge;

        // Getter and Setter methods for Reason and Knowledge
        public Reason getReason() {
            return reason;
        }

        public void setReason(Reason reason) {
            this.reason = reason;
        }

        public Knowledge getKnowledge() {
            return knowledge;
        }

        public void setKnowledge(Knowledge knowledge) {
            this.knowledge = knowledge;
        }
    }

    public static class Reason {
        private int code;
        private String message;

        // Getter and Setter methods for code and message
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class Knowledge {
        private String responseType;
        private List<MatchedKnowledge> matchedKnowledges;

        // Getter and Setter methods for responseType and matchedKnowledges
        public String getResponseType() {
            return responseType;
        }

        public void setResponseType(String responseType) {
            this.responseType = responseType;
        }

        public List<MatchedKnowledge> getMatchedKnowledges() {
            return matchedKnowledges;
        }

        public void setMatchedKnowledges(List<MatchedKnowledge> matchedKnowledges) {
            this.matchedKnowledges = matchedKnowledges;
        }
    }

    public static class MatchedKnowledge {
        private List<String> categories;
        private String question;
        private String answer;
        private String imageUrl;
        private String landingUrl;

        // Getter and Setter methods for categories, question, answer, imageUrl, and landingUrl
        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLandingUrl() {
            return landingUrl;
        }

        public void setLandingUrl(String landingUrl) {
            this.landingUrl = landingUrl;
        }
    }
}

