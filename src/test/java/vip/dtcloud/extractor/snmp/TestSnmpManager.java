package vip.dtcloud.extractor.snmp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import vip.dtcloud.extractor.snmp.info.SnmpServiceReturnMesage;
import vip.dtcloud.extractor.snmp.snmp.SnmpManager;
import vip.dtcloud.extractor.snmp.snmp.SnmpManagerImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.lang.reflect.Field;

/**
 * The test class for SnmpManagerImpl class.
 * Powermock and Easymock are used for mocking the dependent classes.
 */
public class TestSnmpManager {
    /**
     * Holds the engine boot up count for snmp V3.
     */
    private static final int DEFAULT_ENGINE_REBOOTS = 0;
    /**
     * The object of the class to be tested.
     */
    private SnmpManagerImpl snmpManagerImpl;
    /**
     * The IP address of the target machine.
     */
    private String targetIP = "10.3.0.117";
    /**
     * The community name of the V1/V2 snmp agents.
     */
    private String communityName = "public";
    /**
     * The object identifier.
     */
    private String oId = ".1.3.6.1.3.1.1.1.0";
    /**
     * The  string value used for the snmp set operations.
     */
    private String setValue = "Set Test Name";
    /**
     * The  integer value used for the snmp set operations.
     */
    private String setIntValue = "2";
    /**
     * The  OID value used for the snmp set operations.
     */
    private String setOIDValue = ".1.3.6.1.3.1.1.2.1";
    /**
     * The  IP value used for the snmp set operations.
     */
    private String setIPValue = "192.160.168.90";
    /**
     * The string value used for the snmp get operations.
     */
    private String getValue = "Test Name";
    /**
     * The value used as the target IP to make IOException.
     */
    private String invalidTargetIP = "0000";
    /**
     * The security name of the user for snmp V3 agents.
     */
    private String userName = "newUser";
    /**
     * The authentication password for snmp V3 agents.
     */
    private String authenticationId = "abc12345";
    /**
     * The privacy password for snmp V3 agents.
     */
    private String privacyId = "abc12345";
    /**
     * Invalid password, it contains less than 8 characters.
     */
    private String invalidAuthenticationId = "abc";
    /**
     * Invalid OID.
     */
    private String invalidOid = ".q.w.2.3";
    /**
     * The description of SnmpServiceReturnEnum SNMP_SERVICE_SUCCESS.
     */
    private String successEnumDescription = "General Snmp service success";

    /**
     * The byte array size.
     */
    private int arraySize = 10;
    /**
     * The value of SnmpServiceReturnEnum SNMP_SERVICE_SUCCESS.
     */
    private int successEnumValue = 0;
    /**
     * String as the Type of the value to be set.
     */
    private String typeString = "String";
    /**
     * integer as the Type of the value to be set.
     */
    private String typeInt = "integer";
    /**
     * counter32 as Type of the value to be set.
     */
    private String typeCounter32 = "counter32";
    /**
     * counter64 as Type of the value to be set.
     */
    private String typeCounter64 = "counter64";
    /**
     * GAUGE32 as Type of the value to be set.
     */
    private String typeGAUGE32 = "GAUGE32";
    /**
     * UNSIGNEDINTEGER32 as Type of the value to be set.
     */
    private String typeUNSIGNEDINTEGER32 = "UNSIGNEDINTEGER32";
    /**
     * TIMETICKS as Type of the value to be set.
     */
    private String typeTIMETICKS = "TIMETICKS";
    /**
     * OID as Type of the value to be set.
     */
    private String typeOID = "oid";
    /**
     * IPADDRESS as Type of the value to be set.
     */
    private String typeIPADDRESS = "IPADDRESS";

    /**
     * Method which executes before each test method. It initializes
     * SnmpManagerImpl object and enabling the logger debug level.
     *
     * @throws Exception Throws IllegalArgumentException and IllegalAccessException
     */
    @Before
    public void setUp() throws Exception {
        /**
         * Creating an object of SnmpManagerImpl class
         */
        snmpManagerImpl = new SnmpManagerImpl();
        /**
         * Enabling the logger with default access level
         */
        final Field logger = Whitebox.getField(SnmpManagerImpl.class, "logger");
        try {
            final Logger logger2 = (Logger) logger.get(null);
            //logger2.setLevel(Level.DEBUG);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which executes after each test method. It assigns null value to
     * the SnmpManagerImpl object created.
     */
    @After
    public void tearDown() {
        snmpManagerImpl = null;
    }

    /**
     * 1.看下能否采集到数据
     * 2.采集到数据是否是我们想要的数据，格式是什么样的 等等
     * 3.试通后持续采集至少1个小时看下情况
     * */
    @Test
    public void snmpWalkIT() {
        try {
            SnmpManager snmpManager = snmpManagerImpl;
//            SnmpServiceReturnMesage snmpServiceReturnMesage0 = snmpManager.walk(".1.3.6", "public",
//                    "127.0.0.1", 161);
//            System.out.println(snmpServiceReturnMesage0.getComplexResultObject());
            // 交换机
            // 192.168.1.1 华为 5720 .1.3.6.1.4.1.2011.5.25.31.1.1.1.1.5
            // Jssyj@123
            SnmpServiceReturnMesage snmpServiceReturnMesage = snmpManager.walk(".1.3.6.1.4.1.2011.5.25.31.1.1.1.1.5", "Jssyj@123",
                    "192.168.1.1", 161);
            System.out.println("HUAWEI: " + snmpServiceReturnMesage.getComplexResultObject());

            // formatted output
            JAXBContext jaxbContext = JAXBContext.newInstance(SnmpServiceReturnMesage.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(snmpServiceReturnMesage, stringWriter);
            System.out.println(stringWriter);

            // 192.168.10.251 H3C 3600 .1.3.6.1.2.1.1
            // Jssyj@123
            SnmpServiceReturnMesage snmpServiceReturnMesage1 = snmpManager.walk(".1.3.6.1.2.1.1", "Jssyj@123",
                    "192.168.10.251", 161);
            System.out.println("H3C: " + snmpServiceReturnMesage1.getComplexResultObject());

            // formatted output
            JAXBContext jaxbContext1 = JAXBContext.newInstance(SnmpServiceReturnMesage.class);
            Marshaller marshaller1 = jaxbContext1.createMarshaller();
            marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter1 = new StringWriter();
            marshaller.marshal(snmpServiceReturnMesage1, stringWriter1);
            System.out.println(stringWriter1);

//            Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
//            StringReader stringReader = new StringReader(stringWriter.getBuffer().toString());
//            snmpServiceReturnMesage = (SnmpServiceReturnMesage) unMarshaller.unmarshal(stringReader);
//            System.out.println(snmpServiceReturnMesage.getComplexResultObject());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}