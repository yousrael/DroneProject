package objects;

/**
 * Created by yousra on 15/02/17.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionDrone {

    private String id;
    private List<Integer> position = null;
    private String dated;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
        this.position = position;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

