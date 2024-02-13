package notifier.controller;


import notifier.domain.KakaoSkillPayloadDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/message")
public class KakaoMessageController {
    @PostMapping
    public ChatbotResponse create(@RequestBody KakaoSkillPayloadDto kakaoSkillPayloadDto){
        System.out.println(kakaoSkillPayloadDto);

        ChatbotResponse response = new ChatbotResponse();
        response.setVersion("2.0");

        ChatbotResponse.Template template = new ChatbotResponse.Template();
        ChatbotResponse.Output output = new ChatbotResponse.Output();
        ChatbotResponse.Output.TextCard textCard = new ChatbotResponse.Output.TextCard();
        textCard.setTitle("챗봇 관리자센터에 오신 것을 환영합니다.");
        textCard.setDescription("챗봇 관리자센터로 챗봇을 제작해 보세요. \n카카오톡 채널과 연결하여, 이용자에게 챗봇 서비스를 제공할 수 있습니다.");

        ChatbotResponse.Output.Button introButton = new ChatbotResponse.Output.Button();
        introButton.setAction("webLink");
        introButton.setLabel("소개 보러가기");
        introButton.setWebLinkUrl("https://chatbot.kakao.com/docs/getting-started-overview/");

        ChatbotResponse.Output.Button createButton = new ChatbotResponse.Output.Button();
        createButton.setAction("webLink");
        createButton.setLabel("챗봇 만들러 가기");
        createButton.setWebLinkUrl("https://chatbot.kakao.com/");

        textCard.setButtons(new ChatbotResponse.Output.Button[] {introButton, createButton});
        output.setTextCard(textCard);
        template.setOutputs(new ChatbotResponse.Output[] {output});

        response.setTemplate(template);

        return response;
//        Menu menu = new Menu();
//        menu.setDate(menuRequest.getDate());
//        menu.setMealTime(menuRequest.getMealTime());
//        menu.setItems(menuRequest.getItems());
//        menuService.join(menu);
    }
}

