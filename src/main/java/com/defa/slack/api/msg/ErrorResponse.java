package com.defa.slack.api.msg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private boolean ok;
    private String error;

    public ErrorResponse(String msg){
        this.ok = false;
        this.error = msg;
    }

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"ok\": false, \"error\":\"JsonProcessingException\"}";
        }
    }
}
