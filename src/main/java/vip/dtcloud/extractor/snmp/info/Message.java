/**
 * DO NOT EDIT DIRECTLY
 */
package vip.dtcloud.extractor.snmp.info;

public class Message  {

  //指标编码
  private String code;
  //主机IP
  private String host;
  //当前系统时间
  private String time;
  //获取的指标值
  private String value;


  public Message() {
  }

  public Message(String code, String host, String time, String value) {
    this.code = code;
    this.host = host;
    this.time = time;
    this.value = value;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getCode() {
    return code;
  }

  public String getHost() {
    return host;
  }

  public String getTime() {
    return time;
  }

  public String getValue() {
    return value;
  }

public String toJSON(){
  StringBuilder sb = new StringBuilder();
  if(code.length()<=0||host.length()<=0||time.length()<=0||value.length()<=0){
    return "The index has no data";
  }else{
      String result = sb.append("{\"code\":\"").append(code)
            .append("\",\"host\":\"").append(host)
            .append("\",\"time\":\"").append(time)
            .append("\",\"value\":\"").append(value).append("\"}").toString();
      return result;
  }
}

}
