package notifier.controller;


import notifier.domain.KakaoSkillPayloadDto;
import notifier.domain.KakaoSkillResponseDto;
import notifier.domain.Menu;
import notifier.domain.MenuClient;
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
        KakaoSkillResponseDto response = generateResponse(menu);
        return response;
    }

    private KakaoSkillResponseDto generateResponse(Optional<Menu> menu) {
//        todo

//        SimpleText simpleText = new SimpleText("간단한 텍스트 요소입니다.");
//        Output output = new Output(simpleText);
//        Template template = new Template(new Output[]{output});
//        Response response = new Response("2.0", template);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonResponse = objectMapper.writeValueAsString(response);
//
//        System.out.println(jsonResponse);
    }

    private Optional<Menu> getTodayMenu(String skillName, KakaoSkillPayloadDto kakaoSkillPayloadDto) {
        Optional<Menu> menu = null;
        DateService dateService = null;
        
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

