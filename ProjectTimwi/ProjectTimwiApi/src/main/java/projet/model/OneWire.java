package projet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import projet.service.model.Wire;

public class OneWire{
    @JsonProperty("wire-name")
    private String name;

    @JsonProperty("wire-value")
    private Integer value;

    public OneWire(Wire wire){
        this.name = wire.getName();
        this.value = wire.getValue();
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}