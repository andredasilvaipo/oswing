package org.openswing.swing.export.java;

import java.io.Serializable;
import java.util.ArrayList;
import org.openswing.swing.message.send.java.GridParams;
import java.util.Map;
import org.openswing.swing.table.java.GridDataLocator;
import java.util.Hashtable;


/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Export information needed to export data on the server side.</p>
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
public class ExportOptions implements Serializable {

  /** columns to export */
  private ArrayList exportColumns = null;

  /** attribute names related to the columns to export */
  private ArrayList exportAttrColumns = null;

  /** filtered columns; collection of pairs: attributeName, FilterWhereClause[2] */
  private Map filteredColumns = null;

  /** sorted columns */
  private ArrayList currentSortedColumns = new ArrayList();

  /** ordering versus of sorted columns */
  private ArrayList currentSortedVersusColumns = new ArrayList();

  /** other grid parameters */
  private Map otherGridParams = null;

  /** server method name to invoke on the server */
  private String serverMethodName = null;

  /** maximum number of rows to export */
  private int maxRows;

  /** grid data locator */
  private GridDataLocator gridDataLocator = null;

  /** columns width */
  private Hashtable columnsWidth = null;

  /** columns type */
  private Hashtable columnsType = null;

  /** valueObjectType v.o. type */
  private Class valueObjectType = null;

  /** date format */
  private String dateFormat = null;

  /** time format */
  private String timeFormat = null;

  /** date+time format */
  private String dateTimeFormat = null;

  /** export format */
  private String exportType = null;

  public static final int TYPE_TEXT = 0;
  public static final int TYPE_DATE = 1;
  public static final int TYPE_DATE_TIME = 2;
  public static final int TYPE_TIME = 3;
  public static final int TYPE_INT = 4;
  public static final int TYPE_DEC = 5;
  public static final int TYPE_CHECK = 6;
  public static final int TYPE_COMBO = 7;
  public static final int TYPE_LOOKUP = 8;
  public static final int TYPE_PERC = 9;
  public static final int TYPE_CURRENCY = 10;

  public static final String XLS_FORMAT = "XLS";
  public static final String CSV_FORMAT1 = "CSV (,)";
  public static final String CSV_FORMAT2 = "CSV (;)";
  public static final String XML_FORMAT = "XML";
  public static final String HTML_FORMAT = "HTML";


  /**
   * Method called by Grid.
   * @param exportColumns columns to export
   * @param exportAttrColumns attribute names related to the columns to export
   * @param filteredColumns  filtered columns; collection of pairs: attributeName, FilterWhereClause[2]
   * @param currentSortedColumns sorted columns
   * @param currentSortedVersusColumns ordering versus of sorted columns
   * @param otherGridParams other grid parameters
   * @param maxRows maximum number of rows to export
   * @param valueObjectType v.o. type
   * @param gridDataLocator grid data locator
   * @param columnsWidth columns width
   * @param columnsType columns type
   * @param dateFormat date format
   * @param timeFormat time format
   * @param dateTimeFormat date+time format
   * @param exportType export format
   */
  public ExportOptions(
      ArrayList exportColumns,
      ArrayList exportAttrColumns,
      Map filteredColumns,
      ArrayList currentSortedColumns,
      ArrayList currentSortedVersusColumns,
      Map otherGridParams,
      int maxRows,
      Class valueObjectType,
      GridDataLocator gridDataLocator,
      Hashtable columnsWidth,
      Hashtable columnsType,
      String dateFormat,
      String timeFormat,
      String dateTimeFormat,
      String exportType
   ) {
    this.exportColumns = exportColumns;
    this.exportAttrColumns = exportAttrColumns;
    this.filteredColumns = filteredColumns;
    this.currentSortedColumns = currentSortedColumns;
    this.currentSortedVersusColumns = currentSortedVersusColumns;
    this.otherGridParams = otherGridParams;
    this.maxRows = maxRows;
    this.valueObjectType = valueObjectType;
    this.gridDataLocator = gridDataLocator;
    this.columnsWidth = columnsWidth;
    this.columnsType = columnsType;
    this.dateFormat = dateFormat;
    this.timeFormat = timeFormat;
    this.dateTimeFormat = dateTimeFormat;
    this.exportType = exportType;
  }


  /**
   * @return attribute names related to the columns to export
   */
  public final ArrayList getExportAttrColumns() {
    return exportAttrColumns;
  }

  /**
   * @return columns to export
   */
  public final ArrayList getExportColumns() {
    return exportColumns;
  }


  /**
   * @return sorted columns
   */
  public final ArrayList getCurrentSortedColumns() {
    return currentSortedColumns;
  }

  /**
   * @return ordering versus of sorted columns
   */
  public final ArrayList getCurrentSortedVersusColumns() {
    return currentSortedVersusColumns;
  }


  /**
   * @return other grid parameters
   */
  public final Map getOtherGridParams() {
    return otherGridParams;
  }


  /**
   * @return filteredColumns; collection of pairs: attributeName, FilterWhereClause[2]
   */
  public final Map getFilteredColumns() {
    return filteredColumns;
  }


  /**
   * @return server method name to invoke on the server
   */
  public final String getServerMethodName() {
    return serverMethodName;
  }


  /**
   * @return maximum number of rows to export
   */
  public final int getMaxRows() {
    return maxRows;
  }


  /**
   * Set the server method name to invoke on the server.
   * @param serverMethodName server method name to invoke on the server
   */
  public final void setServerMethodName(String serverMethodName) {
    this.serverMethodName = serverMethodName;
  }


  /**
   * @return grid data locator
   */
  public final GridDataLocator getGridDataLocator() {
    return gridDataLocator;
  }


  /**
   * Set the grid data locator.
   * @param gridDataLocator grid data locator
   */
  public final void setGridDataLocator(GridDataLocator gridDataLocator) {
    this.gridDataLocator = gridDataLocator;
  }


  /**
   * @return columns type
   */
  public final Hashtable getColumnsType() {
    return columnsType;
  }


  /**
   * @return columns width
   */
  public final Hashtable getColumnsWidth() {
    return columnsWidth;
  }


  /**
   * @return valueObjectType v.o. type
   */
  public final Class getValueObjectType() {
    return valueObjectType;
  }


  /**
   * @return date format
   */
  public final String getDateFormat() {
    return dateFormat;
  }

  /**
   * @return time format
   */
  public final String getTimeFormat() {
    return timeFormat;
  }


  /**
   * @return date+time format
   */
  public final String getDateTimeFormat() {
    return dateTimeFormat;
  }


  /**
   * @return export format
   */
  public final String getExportType() {
    return exportType;
  }




}