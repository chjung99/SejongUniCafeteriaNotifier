package notifier.service;


import java.time.DayOfWeek;
import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

public class DateService {

    public String getToday() {
        return LocalDate.now().toString();
    }
    public List<String> getWeekDates(String inputDateStr) {
        List<String> weekDates = new ArrayList<>();

        // 입력된 날짜를 LocalDate 객체로 파싱
        LocalDate inputDate = LocalDate.parse(inputDateStr);

        // 이번 주의 월요일부터 금요일까지의 날짜를 리스트에 추가
        LocalDate monday = inputDate.with(DayOfWeek.MONDAY); // 이번 주 월요일
        LocalDate friday = inputDate.with(DayOfWeek.FRIDAY); // 이번 주 금요일

        while (!monday.isAfter(friday)) {
            weekDates.add(monday.toString());
            monday = monday.plusDays(1);
        }

        return weekDates;
    }
}
