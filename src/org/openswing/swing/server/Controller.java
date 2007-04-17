package org.openswing.swing.server;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import org.openswing.swing.message.send.java.*;
import org.openswing.swing.message.receive.java.*;
import org.openswing.swing.internationalization.java.ResourcesFactory;
import org.openswing.swing.logger.server.*;
import org.openswing.swing.internationalization.java.*;
import org.openswing.swing.internationalization.server.*;


/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Server Controller (HTTP Servlet).
 * It receives all client requests and dispatch them to the correct action class.
 * This controller requires a user authentication before processing other requests: that request have method name: "login"
 * The web.xml file must have the following parameters:
 * </p>
 * <ul>
 * <li>"actionClasses" - i.e. the class name that collects all action classes; it must derive from "org.openswing.swing.server.ActionCollection"
 * <li>"connectionSource" - i.e. the class name that manages the database connections; it must implements "org.openswing.swing.server.ConnectionSource" class; possible values: "org.openswing.swing.server.DataSourceConnection", "org.openswing.swing.server.NoConnectionSource", "org.openswing.swing.server.PoolerConnectionSource"
 * <li>"resourceFactory" - i.e. the class name that defines the internationalization settings; it must implements "org.openswing.swing.internationalization.ServerResourcesFactory" class
 * <li>"sessionIdGenerator" - i.e. the class name that generated session identifiers; it must implements "org.openswing.swing.server.SessionIdGenerator" class; possible values: "org.openswing.swing.server.DefaultSessionIdGenerator"
 * <li>"logger" - i.e. the class name that manages the server log; it must implements "org.openswing.swing.logger.server.LoggerMethods" class; possible values: "org.openswing.swing.logger.server.ConsoleLogger", "org.openswing.swing.logger.server.Log4JLogger"
 * <li>"controllerCallbacks" - i.e. the class name that derives from ControllerCallbacks class; it must derive from "org.openswing.swing.server.ControllerCallbacks" class
 * </ul>
 *
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 *
 * <p> This file is part of OpenSwing Framework.
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the (LGPL) Lesser General Public
 * License as published by the Free Software Foundation;
 *
 *                GNU LESSER GENERAL PUBLIC LICENSE
 *                 Version 2.1, February 1999
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *       The author may be contacted at:
 *           maurocarniel@tin.it</p>
 *
 * @author Mauro Carniel
 * @version 1.0
 */
public class Controller extends HttpServlet {

  /** collections of pairs client request, action class */
  private ActionsCollection actions = null;

  /** identifier of session identifiers stored in the servlet context */
  public static final String SESSION_IDS = "SESSION_IDS";

  /** identifier of user sessions info stored in the servlet context */
  public static final String USER_SESSIONS = "USER_SESSIONS";

  /** identifier of internationalization settings (Resources object) stored in the servlet context */
  public static final String RESOURCES_FACTORY = "RESOURCES_FACTORY";

  /** session identifiers generator */
  public static final String SESSION_ID_GENERATOR = "SESSION_ID_GENERATOR";

  /** action classes */
  public static final String ACTION_CLASSES = "ACTION_CLASSES";

  /** class that derives from ControllerCallbacks */
  public static final String CONTROLLER_CALLBACKS = "CONTROLLER_CALLBACKS";


  /**
   * Initialize global variables.
   */
  public void init() throws ServletException {
    // initialize the logger class (LoggerMethods object), if it is defined in web.xml by "logger" parameter...
    boolean logOk = false;
    try {
      String logger = super.getInitParameter("logger");
      if (logger != null) {
        LoggerMethods loggerObject = (LoggerMethods)Class.forName(logger).newInstance();
        Logger.init(loggerObject,Logger.LOG_ALL);
      }
      Logger.info("NONAME",this.getClass().getName(),"init","Initialized log manager");
      logOk = true;
    }
    catch (Throwable ex) {
      this.getServletContext().log("Error on initializing logger class",ex);
    }

    // initialize action classes collection, if it is defined in web.xml by "actionClasses" parameter...
    try {
      String actionClasses = super.getInitParameter("actionClasses");
      if (actionClasses != null) {
        actions = (ActionsCollection)Class.forName(actionClasses).newInstance();
        this.getServletContext().setAttribute(ACTION_CLASSES,actions);
      }
    }
    catch (Throwable ex) {
      String msg = "Error on initializing action classes collection";
      if (logOk)
        Logger.error("NONAME",this.getClass().getName(),"init",msg,ex);
      else
        this.getServletContext().log(msg,ex);
    }

    // initialize connection resource, if it is defined in web.xml by "connectionSource" parameter...
    try {
      String connectionSource = super.getInitParameter("connectionSource");
      if (connectionSource != null) {
        ConnectionManager.initConnectionSource(this, connectionSource);
      }
    }
    catch (Throwable ex) {
      String msg = "Error on initializing connection source";
      if (logOk)
        Logger.error("NONAME",this.getClass().getName(),"init",msg,ex);
      else
        this.getServletContext().log(msg,ex);
    }

    // initialize internationalization settings (Resources object), if it is defined in web.xml by "resourceFactory" parameter...
    try {
      String resourceFactory = super.getInitParameter("resourceFactory");
      if (resourceFactory != null) {
        ServerResourcesFactory factory = (ServerResourcesFactory) Class.forName(resourceFactory).newInstance();
        factory.init(getServletContext());
        this.getServletContext().setAttribute(RESOURCES_FACTORY,factory);
      }
    }
    catch (Throwable ex) {
      String msg = "Error on initializing internationalization settings";
      if (logOk)
        Logger.error("NONAME",this.getClass().getName(),"init",msg,ex);
      else
        this.getServletContext().log(msg,ex);
    }

    // initialize the session identifiers generator (SessionIdGenerator object), if it is defined in web.xml by "sessionIdGenerator" parameter...
    try {
      String sessionIdGenerator = super.getInitParameter("sessionIdGenerator");
      if (sessionIdGenerator != null) {
        SessionIdGenerator generator = (SessionIdGenerator) Class.forName(sessionIdGenerator).newInstance();
        this.getServletContext().setAttribute(SESSION_ID_GENERATOR,generator);
      }
    }
    catch (Throwable ex) {
      String msg = "Error on initializing session identifiers generator";
      if (logOk)
        Logger.error("NONAME",this.getClass().getName(),"init",msg,ex);
      else
        this.getServletContext().log(msg,ex);
    }

    // initialize the controller callbacks class (object derived by ControllerCallbacks), if it is defined in web.xml by "controllerCallbacks" parameter...
    ControllerCallbacks controllerCallbacksObj = null;
    try {
      String controllerCallbacks = super.getInitParameter("controllerCallbacks");
      if (controllerCallbacks != null) {
        controllerCallbacksObj = (ControllerCallbacks)Class.forName(controllerCallbacks).newInstance();
        this.getServletContext().setAttribute(CONTROLLER_CALLBACKS,controllerCallbacksObj);
      }
    }
    catch (Throwable ex) {
      String msg = "Error on initializing controller callbacks class";
      if (logOk)
        Logger.error("NONAME",this.getClass().getName(),"init",msg,ex);
      else
        this.getServletContext().log(msg,ex);
    }

    if (controllerCallbacksObj != null)
      controllerCallbacksObj.afterInit(super.getServletContext());

    if (logOk)
      Logger.info("NONAME",this.getClass().getName(),"init","Servlet Initialization completed.");
  }


  /**
   * Process the HTTP GET request: this method is used to receive browser HTTP requests (like document requests...)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String sessionId = request.getParameter("sessionId");
      String docId = request.getParameter("docId");

      // retrieve session identifiers...
      HashSet authenticatedIds = (HashSet)this.getServletContext().getAttribute(
          SESSION_IDS);
      if (authenticatedIds == null) {
        authenticatedIds = new HashSet();
        this.getServletContext().setAttribute(SESSION_IDS, authenticatedIds);
      }

      if (sessionId == null) {
        // the URI does not contain the session identifier
        Logger.error(
            "NONAME",
            this.getClass().getName(),
            "doGet",
            "Session identifier not specified",
            null
            );
        PrintWriter pw = response.getWriter();
        pw.println("<html><head><title>ERROR</title></html>");
        pw.println(
            "<body>Access denied: you must specify a session identifier</body>");
        pw.println("</html>");
        pw.close();
      }
      else if (authenticatedIds.contains(sessionId)) {
        if (docId == null) {
          Logger.error(
              "NONAME",
              this.getClass().getName(),
              "doGet",
              "Document identifier not specified",
              null
              );
          PrintWriter pw = response.getWriter();
          pw.println("<html><head><title>ERROR</title></html>");
          pw.println(
              "<body>Document not available: you must specify a document identifier</body>");
          pw.println("</html>");
          pw.close();
        }
        else {
          // document retrieving...
          Object doc = getServletContext().getAttribute(docId);
          getServletContext().removeAttribute(docId);
          if (doc == null) {
            Logger.error(
                "NONAME",
                this.getClass().getName(),
                "doGet",
                "Document identifier not valid",
                null
                );
            PrintWriter pw = response.getWriter();
            pw.println("<html><head><title>ERROR</title></html>");
            pw.println(
                "<body>Document not available: you must specify a valid document identifier</body>");
            pw.println("</html>");
            pw.close();
          }
          else {
            if (docId.endsWith(".xls")) {
              response.setContentType("application/vnd.ms-excel");
            }
            else if (docId.endsWith(".xls") || docId.endsWith(".csv")) {
              response.setContentType("application/vnd.ms-excel");
            }
            else if (docId.endsWith(".pdf")) {
              response.setContentType("application/pdf");
            }
            else if (docId.endsWith(".rtf")) {
              response.setContentType("application/rtf");
            }
            else if (docId.endsWith(".html")) {
              response.setContentType("text/html");
            }
            else if (docId.endsWith(".xml")) {
              response.setContentType("text/xml");
            }

            OutputStream out = response.getOutputStream();
            out.write( (byte[]) doc);
            out.close();
          }
        }
      }
      else {
        // the session identifier not valid...
        Logger.error(
            "NONAME",
            this.getClass().getName(),
            "doGet",
            "Session identifier not valid: '" + sessionId + "'",
            null
            );
        PrintWriter pw = response.getWriter();
        pw.println("<html><head><title>ERROR</title></html>");
        pw.println(
            "<body>Access denied: you must specify a valid session identifier</body>");
        pw.println("</html>");
        pw.close();
      }
    }
    catch (Throwable ex) {
      Logger.error(
          "NONAME",
          this.getClass().getName(),
          "doGet",
          "Error on receiving a GET request",
          ex
      );
      try {
        PrintWriter pw = response.getWriter();
        pw.println("<html><head><title>ERROR</title></html>");
        pw.println("<body>An error occours on processing the request:<br>"+ex.toString()+"</body>");
        pw.println("</html>");
        pw.close();
      }
      catch (IOException ex1) {
      }
    }

  }


  /**
   * Process the HTTP POST request: this method is coupled with ClientUtils.getData method.
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // retrieve user sessions info and the session identifiers...
    Response answer = null;
    HashSet authenticatedIds = (HashSet)this.getServletContext().getAttribute(SESSION_IDS);
    if (authenticatedIds==null) {
      authenticatedIds = new HashSet();
      this.getServletContext().setAttribute(SESSION_IDS,authenticatedIds);
    }

    Hashtable userSessions = (Hashtable)this.getServletContext().getAttribute(USER_SESSIONS);
    if (userSessions==null) {
      userSessions = new Hashtable();
      this.getServletContext().setAttribute(USER_SESSIONS,userSessions);
    }

    // retrieve internationalization settings (Resources object)...
    ServerResourcesFactory factory = (ServerResourcesFactory)this.getServletContext().getAttribute(RESOURCES_FACTORY);

    // read the client request...
    try {
      ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
      Command command = (Command) ois.readObject();

      if (!ConnectionManager.isConnectionSourceCreated() || command.getMethodName().equals("databaseAlreadyExixts")) {
        // database connection error: all requests will pass. throught..
        Action action = (Action)actions.get( command.getMethodName() );
        if (action!=null)
          answer = action.executeCommand(
              command.getInputParam(),
              null,
              request,
              response,
              request.getSession(true),
              this.getServletContext()
          );
      } else if (command.getSessionId()==null &&
          !command.getMethodName().equals("login")) {
        // received a client request BEFORE authentication...
        answer = new ErrorResponse("Cannot process the request: authentication needed!");
      } else if (command.getSessionId()!=null &&
                 !authenticatedIds.contains(command.getSessionId())) {
        // received a client request BEFORE authentication...
        answer = new ErrorResponse("Cannot process the request: authentication needed!");
      } else if (command.getSessionId()!=null &&
                 authenticatedIds.contains(command.getSessionId()) &&
                 command.getMethodName().equals("logout")) {
        // received a logout client request...

        authenticatedIds.remove(command.getSessionId());
        userSessions.remove(command.getSessionId());
        answer = new TextResponse(factory.getResources(((UserSessionParameters)userSessions.get(command.getSessionId())).getLanguageId()).getResource("User disconnected"));
      } else {
        // received a client request BEFORE authentication AND method is login
        // OR
        // received a client request AFTER authentication...
        Action action = (Action)actions.get( command.getMethodName() );
        if (action!=null)
          answer = action.executeCommand(
              command.getInputParam(),
              command.getSessionId()==null?null:(UserSessionParameters)userSessions.get(command.getSessionId()),
              request,
              response,
              request.getSession(true),
              this.getServletContext()
          );
        else {
          String msg = "Client request not supported";
          if (command.getSessionId()!=null) {
            // translate the error message...
            msg = factory.getResources(((UserSessionParameters)userSessions.get(command.getSessionId())).getLanguageId()).getResource(msg);
          }
          answer = new ErrorResponse( msg + ": '" + command.getMethodName() + "'" );
        }
      }
      ois.close();
    }
    catch (Exception ex) {
      this.getServletContext().log("Error on processing client request",ex);
      ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
      oos.writeObject(new ErrorResponse(ex.getMessage()));
      oos.close();
      return;
    }
    ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
    oos.writeObject(answer);
    oos.close();
  }


}
