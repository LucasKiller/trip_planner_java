package classes;

import java.io.Serializable;

import enums.ResponseType;

public class Response implements Serializable {
    
    private ResponseType type;
    private Object[] parameters;

    public Response(ResponseType type, Object... parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public ResponseType getType() {
        return type;
    }

    public Object[] getParameters() {
        return parameters;
    }


}
