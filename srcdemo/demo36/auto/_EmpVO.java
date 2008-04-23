package demo36.auto;

/** Class _EmpVO was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _EmpVO extends org.apache.cayenne.CayenneDataObject {

    public static final String DEPT_CODE_PROPERTY = "deptCode";
    public static final String EMP_CODE_PROPERTY = "empCode";
    public static final String FIRST_NAME_PROPERTY = "firstName";
    public static final String HIRE_DATE_PROPERTY = "hireDate";
    public static final String LAST_NAME_PROPERTY = "lastName";
    public static final String NOTE_PROPERTY = "note";
    public static final String SALARY_PROPERTY = "salary";
    public static final String SEX_PROPERTY = "sex";
    public static final String TASK_CODE_PROPERTY = "taskCode";
    public static final String DEPT_PROPERTY = "dept";
    public static final String TASK_PROPERTY = "task";

    public static final String EMP_CODE_PK_COLUMN = "EMP_CODE";

    public void setDeptCode(String deptCode) {
        writeProperty("deptCode", deptCode);
    }
    public String getDeptCode() {
        return (String)readProperty("deptCode");
    }
    
    
    public void setEmpCode(String empCode) {
        writeProperty("empCode", empCode);
    }
    public String getEmpCode() {
        return (String)readProperty("empCode");
    }
    
    
    public void setFirstName(String firstName) {
        writeProperty("firstName", firstName);
    }
    public String getFirstName() {
        return (String)readProperty("firstName");
    }
    
    
    public void setHireDate(java.util.Date hireDate) {
        writeProperty("hireDate", hireDate);
    }
    public java.util.Date getHireDate() {
        return (java.util.Date)readProperty("hireDate");
    }
    
    
    public void setLastName(String lastName) {
        writeProperty("lastName", lastName);
    }
    public String getLastName() {
        return (String)readProperty("lastName");
    }
    
    
    public void setNote(String note) {
        writeProperty("note", note);
    }
    public String getNote() {
        return (String)readProperty("note");
    }
    
    
    public void setSalary(java.math.BigDecimal salary) {
        writeProperty("salary", salary);
    }
    public java.math.BigDecimal getSalary() {
        return (java.math.BigDecimal)readProperty("salary");
    }
    
    
    public void setSex(String sex) {
        writeProperty("sex", sex);
    }
    public String getSex() {
        return (String)readProperty("sex");
    }
    
    
    public void setTaskCode(String taskCode) {
        writeProperty("taskCode", taskCode);
    }
    public String getTaskCode() {
        return (String)readProperty("taskCode");
    }
    
    
    public void setDept(demo36.DeptVO dept) {
        setToOneTarget("dept", dept, true);
    }

    public demo36.DeptVO getDept() {
        return (demo36.DeptVO)readProperty("dept");
    } 
    
    
    public void setTask(demo36.TasksVO task) {
        setToOneTarget("task", task, true);
    }

    public demo36.TasksVO getTask() {
        return (demo36.TasksVO)readProperty("task");
    } 
    
    
}
