package demo47.client;

import java.util.*;
import org.openswing.swing.mdi.client.*;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.internationalization.java.EnglishOnlyResourceFactory;
import org.openswing.swing.util.client.*;
import org.openswing.swing.permissions.client.*;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import org.openswing.swing.internationalization.java.Language;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.openswing.swing.internationalization.java.XMLResourcesFactory;
import org.openswing.swing.domains.java.Domain;
import org.openswing.swing.internationalization.java.*;
import org.openswing.swing.miscellaneous.client.TipInternalFrame;
import org.openswing.swing.miscellaneous.client.TipPanelContent;
import java.math.BigDecimal;
import org.openswing.swing.tree.java.OpenSwingTreeNode;


/**
 * <p>Title: OpenSwing Demo</p>
 * <p>Description: Used to start application from main method: it creates an MDI Frame app.
 * A database profile management and database grid permissions mangament are applied for this application.
 * Use "ADMIN/admin" or "GUEST/guest" to log on.</p>
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * <p> </p>
 * @author Mauro Carniel
 * @version 1.0
 */
public class ClientApplication implements MDIController,LoginController {


  private DemoClientFacade clientFacade = null;
  

  public ClientApplication() {
    clientFacade = new DemoClientFacade();

    Properties props = new Properties();
    
    Hashtable domains = new Hashtable();
    Domain orderStateDomain = new Domain("ORDER_STATE");
    orderStateDomain.addDomainPair("O","Open");
    orderStateDomain.addDomainPair("C","Closed");
    domains.put(
    		orderStateDomain.getDomainId(),
    		orderStateDomain
    );


    ClientSettings clientSettings = new ClientSettings(
        new EnglishOnlyResourceFactory("$",props,false),
        domains
    );

    ClientSettings.BACKGROUND = "background4.jpg";
    ClientSettings.TREE_BACK = "treeback2.jpg";
    ClientSettings.VIEW_BACKGROUND_SEL_COLOR = true;
    ClientSettings.VIEW_MANDATORY_SYMBOL = true;
    ClientSettings.FILTER_PANEL_ON_GRID = true;
    ClientSettings.RELOAD_LAST_VO_ON_FORM = true;
    ClientSettings.SHOW_FILTER_SYMBOL = true;
    ClientSettings.SHOW_SORTING_ORDER = true;
    ClientSettings.ASK_BEFORE_CLOSE = true;
    ClientSettings.LOOKUP_AUTO_COMPLETITION_WAIT_TIME = 1000;
    ClientSettings.BACKGROUND_SEL_COLOR = new Color(245,245,182);
    ClientSettings.GRID_SELECTION_BACKGROUND = new Color(245,245,182);
    ClientSettings.GRID_ACTIVE_CELL_BACKGROUND = new Color(245,245,182);
    ClientSettings.TREE_SELECTION_BACKGROUND = new Color(245,245,182);

    MDIFrame mdi = new MDIFrame(this);
  }


  /**
   * Method called after MDI creation.
   */
  public void afterMDIcreation(MDIFrame frame) {
    MDIFrame.addStatusComponent(new Clock());
  }


   /**
   * @see JFrame getExtendedState method
   */
  public int getExtendedState() {
    return JFrame.MAXIMIZED_BOTH;
  }


  /**
   * @return client facade, invoked by the MDI Frame tree/menu
   */
  public ClientFacade getClientFacade() {
    return clientFacade;
  }


  /**
   * Method used to destroy application.
   */
  public void stopApplication() {
    System.exit(0);
  }


  /**
   * Defines if application functions must be viewed inside a tree panel of MDI Frame.
   * @return <code>true</code> if application functions must be viewed inside a tree panel of MDI Frame, <code>false</code> no tree is viewed
   */
  public boolean viewFunctionsInTreePanel() {
    return true;
  }


  /**
   * Defines if application functions must be viewed in the menubar of MDI Frame.
   * @return <code>true</code> if application functions must be viewed in the menubar of MDI Frame, <code>false</code> otherwise
   */
  public boolean viewFunctionsInMenuBar() {
    return true;
  }


  /**
   * @return <code>true</code> if the MDI frame must show a login menu in the menubar, <code>false</code> no login menu item will be added
   */
  public boolean viewLoginInMenuBar() {
    return false;
  }


  /**
   * @return application title
   */
  public String getMDIFrameTitle() {
    return "Demo 47";
  }


  /**
   * @return text to view in the about dialog window
   */
  public String getAboutText() {
    return
        "This is an MDI Frame demo application\n"+
        "created in order to show how to connect Swing based front-end with EJB3.0/JPA\n"+
        "\n"+
        "Copyright: Copyright (C) 2006 Mauro Carniel\n"+
        "Author: Mauro Carniel";
  }


  /**
   * @return image name to view in the about dialog window
   */
  public String getAboutImage() {
    return "about.jpg";
  }


  /**
   * @param parentFrame parent frame
   * @return a dialog window to logon the application; the method can return null if viewLoginInMenuBar returns false
   */
  public JDialog viewLoginDialog(JFrame parentFrame) {
	  return null;
  }



  /**
   * @return maximum number of failed login
   */
  public int getMaxAttempts() {
    return 3;
  }


  /**
   * Method called by MDI Frame to authenticate the user.
   * @param loginInfo login information, like username, password, ...
   * @return <code>true</code> if user is correcly authenticated, <code>false</code> otherwise
   */
  public boolean authenticateUser(Map loginInfo) throws Exception {
	  return true;
  }


  public static void main(String[] argv) {
    new ClientApplication();
  }


  /**
   * Method called by LoginDialog to notify the sucessful login.
   * @param loginInfo login information, like username, password, ...
   */
  public void loginSuccessful(Map loginInfo) {
  }


  /**
   * @return <code>true</code> if the MDI frame must show a change language menu in the menubar, <code>false</code> no change language menu item will be added
   */
  public boolean viewChangeLanguageInMenuBar() {
    return false;
  }


  /**
   * @return list of languages supported by the application
   */
  public ArrayList getLanguages() {
    ArrayList list = new ArrayList();
    list.add(new Language("EN","English"));
    return list;
  }



  /**
   * @return application functions (ApplicationFunction objects), organized as a tree
   */
  public DefaultTreeModel getApplicationFunctions() {
    DefaultMutableTreeNode root = new OpenSwingTreeNode();
    DefaultTreeModel model = new DefaultTreeModel(root);
    ApplicationFunction n1 = new ApplicationFunction("General",null);
    ApplicationFunction n11 = new ApplicationFunction("Customers","getCustomers","men.gif","getCustomers");
    ApplicationFunction n12 = new ApplicationFunction("Articles","getArticles","grid.gif","getArticles");
    n1.add(n11);
    n1.add(n12);
    root.add(n1);
    ApplicationFunction n2 = new ApplicationFunction("Orders",null);
    ApplicationFunction n21 = new ApplicationFunction("Orders","getOrders","calendar.gif","getOrders");
    n2.add(n21);
    root.add(n2);

    return model;
  }


  /**
   * @return <code>true</code> if the MDI frame must show a panel in the bottom, containing last opened window icons, <code>false</code> no panel is showed
   */
  public boolean viewOpenedWindowIcons() {
    return true;
  }



  /**
   * @return <code>true</code> if the MDI frame must show the "File" menu in the menubar of the frame, <code>false</code> to hide it
   */
  public boolean viewFileMenu() {
     return true;
  }

}
