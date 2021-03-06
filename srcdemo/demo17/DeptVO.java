package demo17;

import org.openswing.swing.message.receive.java.ValueObjectImpl;
import java.math.BigDecimal;
import java.sql.Date;


/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Value Object used to store departments.</p>
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * <p> </p>
 * @author Mauro Carniel
 * @version 1.0
 */
public class DeptVO extends ValueObjectImpl {

  private String deptCode;
  private String description;
  private String status;
  private AddressVO address;


  public DeptVO() {
  }


  public String getDeptCode() {
    return deptCode;
  }
  public void setDeptCode(String deptCode) {
    this.deptCode = deptCode;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public AddressVO getAddress() {
    return address;
  }
  public void setAddress(AddressVO address) {
    this.address = address;
  }






}
