package vip.dtcloud.extractor.snmp.service;

/**
 * Generic Enum class for CATS Return messages.
 * enough room (100) for future response codes.
 *		Failure return codes should be negative.
 *		Success return codes should be positive.
 * Example:
 *		Error codes for Allocation might start at -10 and go to -19.
 *		Success codes for Allocation would then start at 10 and go to 19.
 * @author cfrede001
 */
public enum WebServiceReturnEnum {
	SUCCESS(0, "Success"),
	FAILURE(-1, "General Failure occurred");


	private final int returnCode;
    private final String message;

    WebServiceReturnEnum(int returnCode, String message) {
		this.returnCode = returnCode;
		this.message = message;
	}

	int getReturnCode() {
		return returnCode;
	}

	String getMessage() {
		return message;
	}
}