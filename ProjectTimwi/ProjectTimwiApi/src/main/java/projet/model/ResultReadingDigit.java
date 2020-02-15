package projet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultReadingDigit {

    private String nextString;

    public ResultReadingDigit(String pNextString){
        this.nextString = pNextString;
    }

    @JsonProperty("next_string")
    public String getNextString(){
        return nextString;
    }
}
