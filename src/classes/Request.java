package classes;

import java.io.Serializable;

import enums.RequestType;


public class Request implements Serializable {
    private RequestType type;
    private Object[] parameters;

    public Request(RequestType type, Object... parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public RequestType getType() {
        return type;
    }

    public Object[] getParameters() {
        return parameters;
    }

}