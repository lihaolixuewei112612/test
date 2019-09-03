package vip.dtcloud.extractor.snmp.info;

/**
 * @author TATA
 */
public enum SnmpServiceReturnEnum {
    /**
     * General Snmp service success.
     */
    SNMP_SERVICE_SUCCESS(0, "General Snmp service success"),
    /**
     * General Snmp service failure.
     */
    SNMP_SERVICE_FAILURE(-10, "General Snmp service failure"),
    /**
     * Snmp requst time out.
     */
    SNMP_SERVICE_TIME_OUT(-11, "Snmp requst time out"),
    /**
     * Snmp input parameter error.
     */
    SNMP_SERVICE_INVALID_INPUT(-12, "Snmp input parameter error");

    /**
     * Holding the code of an enum.
     */
    private final int value;
    /**
     * Holding the description of an enum.
     */
    private final String genericDescription;

    /**
     * Two argument constructor. Creates an enum with supplied Code and Description.
     *
     * @param value              Code corresponding to an enum constant.
     * @param genericDescription Description corresponding to an enum constant.
     */
    SnmpServiceReturnEnum(final int value, final String genericDescription) {
        this.value = value;
        this.genericDescription = genericDescription;
    }

    /**
     * Getter method for the Code of an enum constant.
     *
     * @return Code corresponding to an enum constant.
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter method for the Description of an enum constant.
     *
     * @return Description corresponding to an enum constant.
     */
    public String getDescription() {
        return genericDescription;
    }
}