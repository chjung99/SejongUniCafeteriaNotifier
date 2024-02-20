package notifier.controller;


import notifier.domain.KakaoSkillPayloadDto;
import notifier.domain.KakaoSkillResponseDto;
import notifier.domain.Menu;
import notifier.domain.MenuClient;
import notifier.domain.skillpayload.Action;
import notifier.domain.skillresponse.*;
import notifier.domain.skillresponse.Output;
import notifier.service.DateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.*;


@RestController
public class KakaoMessageController {
    @RequestMapping("/api/message")
    @PostMapping
    public KakaoSkillResponseDto createTextResponse(@RequestBody KakaoSkillPayloadDto kakaoSkillPayloadDto) {
        String skillName = kakaoSkillPayloadDto.getAction().getName();
        if (skillName.equals("lunchMenu") || skillName.equals("dinnerMenu")) {
            Optional<Menu> menu = getTodayMenu(skillName);
            KakaoSkillResponseDto response = generateSimpleTextResponse(skillName, menu);
            return response;
        } else if (skillName.equals("weeklyMenu")) {
            KakaoSkillResponseDto response = generateCarouselListCardResponse();
            return response;
        } else if (skillName.equals("searchMenu")) {
            Action action = kakaoSkillPayloadDto.getAction();
            String date = action.getOriginDateFromDetailParams();
            String timePeriod = action.getOriginTimeFromDetailParams();
            String mealTime = "";
            if (timePeriod.equals("점심")) {
                mealTime = "lunch";
            } else if (timePeriod.equals("저녁")) {
                mealTime = "dinner";
            }
            RestTemplate restTemplate = new RestTemplate();
            MenuClient menuClient = new MenuClient(restTemplate);
            Optional<Menu> menu = menuClient.requestMenu(date, mealTime);
            if (mealTime.equals("lunch")) {
                KakaoSkillResponseDto response = generateSimpleTextResponse("lunchMenu", menu);

                return response;
            } else if (mealTime.equals("dinner")) {
                KakaoSkillResponseDto response = generateSimpleTextResponse("dinnerMenu", menu);

                return response;
            }
        }
        return null;
    }


    private KakaoSkillResponseDto generateCarouselListCardResponse() {
        KakaoSkillResponseDto response = new KakaoSkillResponseDto();
        CarouselOutput output = new CarouselOutput();
        Carousel carousel = new Carousel();

        DateService dateService = new DateService();

        List<String> weekDates = dateService.getWeekDates(dateService.getToday());
        ArrayList<Card> cardItems = new ArrayList<>();

        for (String date : weekDates) {
            ListCard listCard = new ListCard();
            Header header = new Header();
            header.setTitle("이번 주 계절 밥상");

            ArrayList<ListItem> items = new ArrayList<>();

            // 점심 아이템
            ListItem lunchItem = new ListItem();
            lunchItem.setTitle("점심");
            lunchItem.setDescription("test");
//            lunchItem.setAction("message");
//            lunchItem.setMessageText(date + " 점심");

//            Map<String, String> lunchExtra = new HashMap<>();
//            lunchExtra.put("sys_date", date);
//            lunchExtra.put("sys_time_period", "lunch");
//
//            lunchItem.setExtra(lunchExtra);
            items.add(lunchItem);

            // 저녁 아이템
            ListItem dinnerItem = new ListItem();
            dinnerItem.setTitle("저녁");
            dinnerItem.setDescription("test");
//            dinnerItem.setAction("message");
//            dinnerItem.setMessageText(date + " 저녁");

//            Map<String, String> dinnerExtra = new HashMap<>();
//            dinnerExtra.put("sys_date", date);
//            dinnerExtra.put("sys_time_period", "dinner");
//
//            dinnerItem.setExtra(dinnerExtra);
            items.add(dinnerItem);

            listCard.setHeader(header);
            listCard.setItems(items);
            cardItems.add(listCard);
        }

        carousel.setType("listCard");
        carousel.setItems(cardItems);

        output.setCarousel(carousel);

        SkillTemplate template = new SkillTemplate();
        template.setOutputs(new Output[]{output});

        response.setVersion("2.0");
        response.setTemplate(template);

        return response;
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

    private Optional<Menu> getTodayMenu(String skillName) {
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

