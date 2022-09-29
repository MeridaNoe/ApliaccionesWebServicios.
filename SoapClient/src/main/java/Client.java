import mx.edu.utez.soapclient.*;


public class Client {
    public static void main(String[] args) {
        ServiceService serviceService = new ServiceService();
        Service service = serviceService.getServicePort();
        String response = service.responseMessage("Hola");
        System.out.println(response);
    }
}
