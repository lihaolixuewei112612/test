package vip.dtcloud.extractor.snmp.service;


import static vip.dtcloud.extractor.snmp.service.WebServiceReturnEnum.*;

/**
 * A base class for all responses returned from CAT-based web services. This is being used to address QTPs lack of exception
 * handling so that a tester can look at a result and decide what action to take next. The data field is generic, and could contain
 * either a definition of the result, or actual data retrieved from the service call.
 *
 * @author cfrede001
 */
public class WebServiceReturn  {
    /**
     * Generated Serial ID.
     */
  //  private static final long serialVersionUID = 8617437599896781546L;

    /**
     * A result code returned from the web service call.
     */
    private WebServiceReturnEnum result;

    /**
     * A human-readable message corresponding to the result.
     */
    private String message;

    public WebServiceReturn() {
        result = SUCCESS;
    }

    public WebServiceReturn(final WebServiceReturnEnum result) {
        this.result = result;
    }

    /**
     * Constructs an instance of <code>CATSWebServiceReturnMessage</code> with the specified result code and data.
     *
     * @param resultCode The result code associated with this <code>CATSWebServiceReturnMessage</code>.
     * @param data       The data associated with this <code>CATSWebServiceReturnMessage</code>.
     */
    public WebServiceReturn(final WebServiceReturnEnum result, final String message) {
        this.result = result;
        this.message = message;
    }

    /**
     * @return The value of <code>resultCode</code>.
     */
    public WebServiceReturnEnum getResult() {
        return result;
    }

    /**
     * @param result The value of <code>result</code> to set.
     */
    public void setResult(WebServiceReturnEnum result) {
        this.result = result;
    }

    /**
     * @return The value of <code>message</code>.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The value of <code>message</code> to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}