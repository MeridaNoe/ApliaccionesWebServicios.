
package utez;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Services", targetNamespace = "utez")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Services {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "guessNumber", targetNamespace = "utez", className = "utez.GuessNumber")
    @ResponseWrapper(localName = "guessNumberResponse", targetNamespace = "utez", className = "utez.GuessNumberResponse")
    @Action(input = "utez/Services/guessNumberRequest", output = "utez/Services/guessNumberResponse")
    public boolean guessNumber(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}