package vip.dtcloud.extractor.snmp.info;

/**
 * enum declaration for data types that can be used for SNMP SET.
 *
 * @author HuangYao
 */
public enum DataTypes {
    /**
     * Snmp Integer data type.
     */
    INTEGER("Integer"),
    /**
     * Snmp 32-bit counter data type.
     */
    COUNTER32("Counter32"),
    /**
     * Snmp 64-bit counter data type.
     */
    COUNTER64("Counter64"),
    /**
     * Snmp 32-bit gauge data type. Which represents a 64bit unsigned integer type.
     */
    GAUGE32("Gauge32"),
    /**
     * Snmp unsigned 32-bit integer data type.
     */
    UNSIGNEDINTEGER32("UnsignedInteger32"),
    /**
     * Snmp time ticks data type. It represents the time in 1/100 seconds since some epoch.
     */
    TIMETICKS("TimeTicks"),
    /**
     * String data type.
     */
    STRING("String"),
    /**
     * Snmp object identifier data type.
     */
    OID("OID"),
    /**
     * Snmp ip address data type.
     */
    IPADDRESS("IpAddress"),
    /**
     * TCP/IP transport address format.
     */
    TCPADDRESS("TcpAddress"),
    /**
     * The UDP/IP transport address format.
     */
    UDPADDRESS("UdpAddress");

    /**
     * The variable holding value of the Enum constant.
     */
    private final String value;

    /**
     * The constructor for creating the Enum constants.
     *
     * @param value The value of Enum constant.
     */
    private DataTypes(final String value) {
        this.value = value;
    }
}