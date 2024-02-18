package notifier.controller;


import notifier.domain.KakaoSkillPayloadDto;
import notifier.domain.KakaoSkillResponseDto;
import notifier.domain.Menu;
import notifier.domain.MenuClient;
import notifier.domain.skillresponse.*;
import notifier.domain.skillresponse.Output;
import notifier.service.DateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;


@RestController
public class KakaoMessageController {
    @RequestMapping("/api/message")
    @PostMapping
    public KakaoSkillResponseDto createTextResponse(@RequestBody KakaoSkillPayloadDto kakaoSkillPayloadDto){
        String skillName = kakaoSkillPayloadDto.getAction().getName();
        Optional<Menu> menu = getTodayMenu(skillName, kakaoSkillPayloadDto);
        KakaoSkillResponseDto response = generateSimpleTextResponse(skillName, menu);
        return response;
    }
    @RequestMapping("/api/image")
    @PostMapping
    public KakaoSkillResponseDto createImageResponse(@RequestBody KakaoSkillPayloadDto kakaoSkillPayloadDto){
        KakaoSkillResponseDto response = generateSimpleImageResponse();
        return response;
    }

    private KakaoSkillResponseDto generateSimpleImageResponse() {
        KakaoSkillResponseDto response = new KakaoSkillResponseDto();
        SimpleImage simpleImage = new SimpleImage();

        String thisWeekMenuUrl = getThisWeekMenuUrl();
        String thisWeekAlterText = getThisWeekAlterText();

        simpleImage.setImageUrl(thisWeekMenuUrl);
        simpleImage.setAltText(thisWeekAlterText);

        SimpleImageOutput output = new SimpleImageOutput();
        output.setSimpleImage(simpleImage);


        SkillTemplate template = new SkillTemplate();
        template.setOutputs(new Output[]{output});

        response.setVersion("2.0");
        response.setTemplate(template);

        return response;
    }

    private String getThisWeekAlterText() {
        String alterText = "이번 주 식단표입니다.";

        return alterText;
    }

    private String getThisWeekMenuUrl() {
        String menuUrl = "https://objectstorage.ap-chuncheon-1.oraclecloud.com/p/qlTVtSwqGyTdhGV29W3xC3JABjrOy3TpGmKK6foFtyK0opZrTxmEAV-JVRzcC5UQ/n/axn4dve0qg0d/b/sejong-uni-cafeteria-notifier/o/this_weeks_menu.JPG";

        return menuUrl;
    }

    private KakaoSkillResponseDto generateSimpleTextResponse(String mealTime, Optional<Menu> menu) {
        KakaoSkillResponseDto response = new KakaoSkillResponseDto();
        SimpleText simpleText = new SimpleText();

        String menuTextsTitle = getMenuTextsTitle(mealTime);
        String concatenatedMenuTexts = concatenateMenuItems(menu);

        simpleText.setText(menuTextsTitle +"\n\n" + concatenatedMenuTexts);
        SimpleTextOutput output = new SimpleTextOutput();
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
        if (concatenatedItems.length() > 0) {
            concatenatedItems.delete(concatenatedItems.length() - 1, concatenatedItems.length());
        }

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

