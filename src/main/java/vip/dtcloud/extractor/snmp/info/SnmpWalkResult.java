package vip.dtcloud.extractor.snmp.info;

import java.io.Serializable;

/**
 * Holds an entry of the SNMP Walk result.
 *
 * <pre>
 *  For a snmpwalk result like 1.3.6.1.2.1.2.2.1.6.1 = 54:d4:6f:7e:06:62,
 *  this bean holds the oid (1.3.6.1.2.1.2.2.1.6.1) and its value (54:d4:6f:7e:06:62)
 * </pre>
 * <p>
 * This is
 *
 * @author ajith
 */
public class SnmpWalkResult implements Serializable {
    /**
     * Generated Serial ID.
     */
    private static final long serialVersionUID = -3883639322632144305L;
    /**
     * Holds the Object Identifier value.
     */
    private String oid;
    /**
     * Holds the value for this above mentioned OID.
     */
    private String value;

    public SnmpWalkResult() {}

    public SnmpWalkResult(String oid, String value) {
        this.oid = oid;
        this.value = value;
    }

    // Getters and Setters.
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String toStringVal = "OID: " + getOid() + " Value :" + getValue();
        return toStringVal;
    }

}