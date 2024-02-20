package notifier.domain.skillpayload;

import java.util.Map;
public class Action {
    private String id;
    private String name;
    private Map<String, String> params;

    private Map<String, ?> detailParams;
    private Map<String, ?> clientExtra;

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

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, ?> getDetailParams() {
        return detailParams;
    }

    public void setDetailParams(Map<String, ?> detailParams) {
        this.detailParams = detailParams;
    }

    public Map<String, ?> getClientExtra() {
        return clientExtra;
    }

    public void setClientExtra(Map<String, ?> clientExtra) {
        this.clientExtra = clientExtra;
    }


    public String getDateFromDetailParams() {
        // detailParams에서 sys_date 맵 가져오기
        Map<String, ?> sysDateMap = (Map<String, ?>) this.detailParams.get("sys_date");

        // sys_date 맵이 null이 아니고, value 맵에서 "date" 키를 사용하여 값을 가져오기
        if (sysDateMap != null) {
            String dateValue = (String) sysDateMap.get("value");

            // 값이 JSON 형식으로 되어 있으므로 파싱하여 "date" 키의 값 반환
            // 여기서는 간단하게 문자열에서 "\"date\": \"" 다음에 나오는 10글자를 추출하는 방식으로 처리
            int startIndex = dateValue.indexOf("\"date\": \"") + 9;
            int endIndex = startIndex + 10;
            return dateValue.substring(startIndex, endIndex);
        }

        return null; // sys_date가 없거나 value가 없을 경우 null 반환
    }
    public String getOriginTimeFromDetailParams() {
        // detailParams에서 sys_time_period 맵 가져오기
        Map<String, ?> sysLocationMap = (Map<String, ?>) this.detailParams.get("sys_time_period");

        // sys_time_period 맵이 null이 아니면 origin 값 반환
        if (sysLocationMap != null) {
            return (String) sysLocationMap.get("origin");
        }

        return null; // sys_time_period 없을 경우 null 반환
    }

}
