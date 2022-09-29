import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService(name = "Service", targetNamespace = "utez")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Service {
    @WebMethod(operationName = "responseMessage")
    public String responeMessage(@WebParam(name = "message")
                                     String message){
        return "El mensaje recibido fue "+message;
    }

    public static void main(String[] args) {
        System.out.println("Initializing server");
        Endpoint.publish("http://localhost:7777/Service", new Service());
        System.out.println("Waiting for request");
    }
}
