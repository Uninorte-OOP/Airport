
package core.controllers.utils;

public class Response {
    private String message;
    private int status;
    private Object object;

    //Constructor sencillo
    public Response(String message, int status) {
        this.message = message;
        this.status = status;
    }
    
    //Constructor mas complejo
    public Response(String message, int status, Object object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }

    
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }
}
