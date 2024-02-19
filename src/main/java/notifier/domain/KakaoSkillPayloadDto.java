package notifier.domain;


import notifier.domain.skillpayload.Action;
import notifier.domain.skillpayload.Bot;
import notifier.domain.skillpayload.Intent;
import notifier.domain.skillpayload.UserRequest;

public class KakaoSkillPayloadDto {
    private Intent intent;
    private UserRequest userRequest;
    private Bot bot;
    private Action action;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
