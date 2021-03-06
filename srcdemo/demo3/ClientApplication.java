package demo3;

import java.util.*;

import org.openswing.swing.internationalization.java.EnglishOnlyResourceFactory;
import org.openswing.swing.domains.java.*;
import java.sql.*;
import org.openswing.swing.util.client.*;
import org.openswing.swing.internationalization.java.*;
import org.openswing.swing.table.profiles.client.FileGridProfileManager;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.util.java.Consts;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;


/**
 * <p>Title: OpenSwing Demo</p>
 * <p>Description: Used to start application from main method:
 * it creates an editable grid frame.</p>
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * <p> </p>
 * @author Mauro Carniel
 * @version 1.0
 */
public class ClientApplication {


  Connection conn = null;



  public ClientApplication() {

//    try {
//      ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"; // "com.jtattoo.plaf.aero.McWinLookAndFeel";
//      Properties props = new Properties();
//      props.put("backgroundColor", "238 238 238");
//      props.put("logoString", "JAllInOne");
//      String color = "220 220 220";
//      props.put("disabledBackgroundColor", color);
//      props.put("systemTextFont", "Verdana PLAIN 11");
//      props.put("controlTextFont", "Verdana PLAIN 11");
//      props.put("menuTextFont", "Verdana PLAIN 11");
//      props.put("userTextFont", "Verdana PLAIN 11");
//      props.put("subTextFont", "Verdana PLAIN 11");
//      Class.forName(ClientSettings.LOOK_AND_FEEL_CLASS_NAME).getMethod(
//          "setCurrentTheme", new Class[] {Properties.class}).invoke(null,
//          new Object[] {props});
//      UIManager.setLookAndFeel(ClientSettings.LOOK_AND_FEEL_CLASS_NAME);
//
//    }
//    catch (Throwable ex1) {
//      ex1.printStackTrace();
//    }


    Hashtable domains = new Hashtable();
    Properties props = new Properties();
    props.setProperty("this text will be translated","This text will be translated");
    props.setProperty("date","Date");
    props.setProperty("combobox","Combobox");
    props.setProperty("opened","Opened");
    props.setProperty("suspended","Suspended");
    props.setProperty("delivered","Delivered");
    props.setProperty("closed","Closed");
    props.setProperty("radio button","Radio Button");
    props.setProperty("stringValue","Text");
    props.setProperty("dateValue","Date");
    props.setProperty("checkValue","CheckBox");
    props.setProperty("radioButtonValue","RadioButton");
    props.setProperty("comboValue","ComboBox");
    props.setProperty("currencyValue","Currency");
    props.setProperty("numericValue","Number");
    props.setProperty("lookupValue","Lookup Code");
    props.setProperty("descrLookupValue","Lookup Description");
    props.setProperty("button","Button");
    props.setProperty("formattedTextValue","Formatted Text");
    props.setProperty("intValue","Integer Value");
    props.setProperty("multiLineTextValue","Multi Line Text Value");
    props.setProperty("year","Year");
    props.setProperty("spinnerValue","Spinner Value");
    props.setProperty("...","...");
    ClientSettings clientSettings = new ClientSettings(
        new EnglishOnlyResourceFactory("�",props,true),
        domains
    );
    ClientSettings.FILTER_PANEL_ON_GRID = true;

    Domain orderStateDomain = new Domain("ORDERSTATE");
    orderStateDomain.addDomainPair(new Integer(0),"opened");
    orderStateDomain.addDomainPair(new Integer(1),"suspended");
    orderStateDomain.addDomainPair(new Integer(2),"delivered");
    orderStateDomain.addDomainPair(new Integer(3),"closed");
    domains.put(
      orderStateDomain.getDomainId(),
      orderStateDomain
    );
    ClientSettings.ALLOW_OR_OPERATOR = false;
    ClientSettings.INCLUDE_IN_OPERATOR = false;
    ClientSettings.GRID_PROFILE_MANAGER = new FileGridProfileManager();
    ClientSettings.SELECT_DATA_IN_EDITABLE_GRID = true;

//    ClientSettings.BUTTON_BEHAVIOR=Consts.BUTTON_IMAGE_AND_TEXT;
//    ClientSettings.BUTTON_HORIZONTAL_TEXT_POSITION=SwingConstants.LEADING;

    createConnection();


    new GridFrameController(conn);
  }


  public static void main(String[] argv) {
    new ClientApplication();
  }


  /**
   * Create the database connection (using Hypersonic DB - in memory) and initialize tables...
   */
  private void createConnection() {
    try {
      Class.forName("org.hsqldb.jdbcDriver");
      conn = DriverManager.getConnection("jdbc:hsqldb:mem:"+"a"+Math.random(),"sa","");
      PreparedStatement stmt = null;
      try {
        stmt = conn.prepareStatement("create table DEMO3(TEXT VARCHAR,FORMATTED_TEXT NUMERIC(12),DECNUM DECIMAL(10,2),CURRNUM DECIMAL(10,2),THEDATE DATE,COMBO NUMERIC(1),CHECK_BOX CHAR(1),RADIO CHAR(1),CODE VARCHAR,INT_VALUE NUMERIC(6),ML_TEXT VARCHAR,YEAR NUMERIC,PRIMARY KEY(TEXT))");
//        stmt = conn.prepareStatement("create table DEMO3(TEXT VARCHAR,FORMATTED_TEXT VARCHAR(20),DECNUM DECIMAL(10,2),CURRNUM DECIMAL(10,2),THEDATE DATE,COMBO NUMERIC(1),CHECK_BOX CHAR(1),RADIO CHAR(1),CODE VARCHAR,INT_VALUE NUMERIC(6),ML_TEXT VARCHAR,YEAR NUMERIC,PRIMARY KEY(TEXT))");
        stmt.execute();
        stmt.close();

        stmt = conn.prepareStatement("create table DEMO3_LOOKUP(CODE VARCHAR,DESCRCODE VARCHAR,PRIMARY KEY(CODE))");
        stmt.execute();

        for(int i=1;i<900;i++) {
          stmt.close();
          stmt = conn.prepareStatement("insert into DEMO3 values('ABC"+i+"',"+((i+1)%9)+"23224444,"+12+i+0.33+","+1234+i+0.560+",?,3,'Y','Y','A"+i+"',"+i+",'ABC"+i+"\nDEF"+i+"',2010)");
          stmt.setObject(1,new java.sql.Date(System.currentTimeMillis()+86400000*i));
          stmt.execute();
        }

        stmt = conn.prepareStatement("insert into DEMO3 values('ABC"+0+"',"+((0+1)%9)+"23224444,"+12+","+1234+0+0.560+",?,3,'Y','Y','A"+0+"',"+0+",'ABC"+0+"\nDEF"+0+"',2010)");
        stmt.setObject(1,new java.sql.Date(System.currentTimeMillis()));
        stmt.execute();

        for(int i=0;i<900;i++) {
          stmt.close();
          stmt = conn.prepareStatement("insert into DEMO3_LOOKUP values('A"+i+"','ABCDEF"+String.valueOf(i)+"')");
          stmt.execute();
        }

      }
      catch (SQLException ex1) {
        ex1.printStackTrace();
      }
      finally {
        try {
          stmt.close();
        }
        catch (SQLException ex2) {
        }
      }

      conn.commit();

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }




}
