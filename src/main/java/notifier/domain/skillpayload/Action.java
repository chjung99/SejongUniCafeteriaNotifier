package notifier.domain.skillpayload;

import java.util.Map;
public class Action {
    private String id;
    private String name;
    private Map<String, String> params;

    private Map<String, ?> detailParms;
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

    public Map<String, ?> getDetailParms() {
        return detailParms;
    }

    public void setDetailParms(Map<String, ?> detailParms) {
        this.detailParms = detailParms;
    }

    public Map<String, ?> getClientExtra() {
        return clientExtra;
    }

    public void setClientExtra(Map<String, ?> clientExtra) {
        this.clientExtra = clientExtra;
    }
}
