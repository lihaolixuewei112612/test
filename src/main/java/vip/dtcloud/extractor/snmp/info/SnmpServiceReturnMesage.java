package vip.dtcloud.extractor.snmp.info;

import vip.dtcloud.extractor.snmp.service.WebServiceReturn;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class SnmpServiceReturnMesage extends WebServiceReturn {
    /**
     * Generated Serial Version ID.
     */
 //   private static final long serialVersionUID = -8614099182546651086L;
    /**
     * Holds the status of operation represented by SnmpServiceReturnEnum.
     */
    private SnmpServiceReturnEnum serviceCode;
    /**
     * The return value received from the SNMP agent.
     */
    private String resultObject;

    private Integer snmpErrorCode;

    private List<SnmpWalkResult> complexResultObject;

    /**
     * By default we want to select the general SNMP_SERVICE_SUCCESS. Make sure
     * the base return message is also set.
     */
    public SnmpServiceReturnMesage() {
        this.serviceCode = SnmpServiceReturnEnum.SNMP_SERVICE_SUCCESS;
    }

    /**
     * Constructor creates a SnmpServiceReturnMesage object with the supplied
     * SnmpServiceReturnEnum set.
     *
     * @param serviceCode SnmpServiceReturnEnum
     */
    public SnmpServiceReturnMesage(final SnmpServiceReturnEnum serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Method returns the status of snmp operation represented by
     * SnmpServiceReturnEnum.
     *
     * @return SnmpServiceReturnEnum
     */
    public SnmpServiceReturnEnum getServiceCode() {
        return serviceCode;
    }

    /**
     * Method for setting the status of snmp operation.
     *
     * @param serviceCode SnmpServiceReturnEnum
     */
    public void setServiceCode(final SnmpServiceReturnEnum serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Method returns the status of snmp operation represented by
     * SnmpServiceReturnEnum.
     *
     * @return SnmpServiceReturnEnum
     */
    public Integer getSnmpErrorCode() {
        return snmpErrorCode;
    }

    /**
     * Method for setting the status retur from SNMP4J.
     */
    public void setSnmpErrorCode(final Integer snmpErrorCode) {
        this.snmpErrorCode = snmpErrorCode;
    }

    /**
     * Getter method for the value received for the snmp operation.
     *
     * @return The value received for the snmp operation.
     */
    public String getResultObject() {
        return resultObject;
    }

    /**
     * Setter method for the value received for snmp operation.
     *
     * @param resultObject Value got from the snmp operation.
     */

    public void setResultObject(final String resultObject) {
        this.resultObject = resultObject;
    }

    /**
     * Returns the complex result.
     * This will be used in case of SNMP WALK.
     *
     * @return
     */

    public List<SnmpWalkResult> getComplexResultObject() {
        return complexResultObject;
    }

    /**
     * Sets the List of strings. This will be used in case of snmp walk.
     *
     * @param complexResultObject
     */

    public void setComplexResultObject(final List<SnmpWalkResult> complexResultObject) {
        this.complexResultObject = new ArrayList<SnmpWalkResult>(complexResultObject);
    }

    @Override
    public String toString() {
        return "ResultObject " + resultObject + "ComplexResultObject " + complexResultObject;
    }

}