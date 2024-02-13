package notifier.domain.skillpayload;

import java.util.Map;
import java.util.Optional;

public class UserRequest {
    private String timezone;
    private Block block;
    private String utterance;
    private String lang;
    private User user;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getUtterance() {
        return utterance;
    }

    public void setUtterance(String utterance) {
        this.utterance = utterance;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static class Block {
        private String id;
        private String name;

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
    }

    private static class User {
        private String id;
        private Optional<String> type;
        private Optional<Map<String, String>> properties;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Optional<String> getType() {
            return type;
        }

        public void setType(Optional<String> type) {
            this.type = type;
        }

        public Optional<Map<String, String>> getProperties() {
            return properties;
        }

        public void setProperties(Optional<Map<String, String>> properties) {
            this.properties = properties;
        }
    }
}
