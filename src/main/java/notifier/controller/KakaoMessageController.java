package notifier.controller;


import notifier.domain.KakaoSkillPayloadDto;
import notifier.domain.KakaoSkillResponseDto;
import notifier.domain.Menu;
import notifier.domain.MenuClient;
import notifier.domain.skillresponse.Output;
import notifier.domain.skillresponse.SimpleText;
import notifier.domain.skillresponse.SkillTemplate;
import notifier.service.DateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;


@RestController
@RequestMapping("/api/message")
public class KakaoMessageController {
    @PostMapping
    public KakaoSkillResponseDto create(@RequestBody KakaoSkillPayloadDto kakaoSkillPayloadDto){
        String skillName = kakaoSkillPayloadDto.getAction().getName();
        Optional<Menu> menu = getTodayMenu(skillName, kakaoSkillPayloadDto);
        KakaoSkillResponseDto response = generateResponse(skillName, menu);
        return response;
    }

    private KakaoSkillResponseDto generateResponse(String mealTime, Optional<Menu> menu) {
        KakaoSkillResponseDto response = new KakaoSkillResponseDto();
        SimpleText simpleText = new SimpleText();

        String menuTextsTitle = getMenuTextsTitle(mealTime);
        String concatenatedMenuTexts = concatenateMenuItems(menu);

        simpleText.setText(menuTextsTitle +"\n\n" + concatenatedMenuTexts);
        Output output = new Output();
        output.setSimpleText(simpleText);
        

        SkillTemplate template = new SkillTemplate();
        template.setOutputs(new Output[]{output});

        response.setVersion("2.0");
        response.setTemplate(template);
        
        return response;
    }

    private String getMenuTextsTitle(String mealTime) {
        if(mealTime.equals("lunchMenu")){
            return "\uD83C\uDF7D\uFE0F 오늘의 점심 \uD83C\uDF5A";
        } else if (mealTime.equals("dinnerMenu")) {
            return "\uD83C\uDF7D\uFE0F 오늘의 저녁 \uD83C\uDF5A";
        }
        return "mealTime ERROR";
    }

    private String concatenateMenuItems(Optional<Menu> menuOptional) {
        StringBuilder concatenatedItems = new StringBuilder();

        menuOptional.ifPresent(menu -> {
            for (String item : menu.getItems()) {
                concatenatedItems.append("✔\uFE0F ").append(item).append("\n");
            }
        });

        // 마지막 쉼표 및 공백 제거
//        if (concatenatedItems.length() > 0) {
//            concatenatedItems.delete(concatenatedItems.length() - 2, concatenatedItems.length());
//        }

        return concatenatedItems.toString();
    }

    private Optional<Menu> getTodayMenu(String skillName, KakaoSkillPayloadDto kakaoSkillPayloadDto) {
        Optional<Menu> menu = null;
        DateService dateService = new DateService();
        
        RestTemplate restTemplate = new RestTemplate();
        MenuClient menuClient = new MenuClient(restTemplate);
        if (skillName.equals("lunchMenu")){
            menu = menuClient.requestMenu(dateService.getToday(), "lunch");
        } else if (skillName.equals("dinnerMenu")) {
            menu = menuClient.requestMenu(dateService.getToday(), "dinner");
        }
        return menu;
    }
}

