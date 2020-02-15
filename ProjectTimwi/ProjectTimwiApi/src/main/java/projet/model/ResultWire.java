package projet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import projet.service.model.Wire;

import java.util.ArrayList;
import java.util.List;

public class ResultWire {

    private List<OneWire> wires = new ArrayList<>();

    public List<OneWire> getWires() {
        return wires;
    }
}
