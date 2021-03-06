package org.openswing.swing.pivottable.java;

import org.openswing.swing.pivottable.aggregators.java.*;
import java.io.Serializable;


/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Row field descriptor, used when defining a pivot table.</p>
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
public class RowField implements Serializable {

  /** column name in original TableModel, that identify this field */
  private String columnName;

  /** column width, expressed in pixels */
  private int width;

  /** field aggregator, that groups values; default value: GenericAggregator */
  private GenericAggregator aggregator = new GenericAggregator();

  /** column description */
  private String description;


  public RowField(String columnName,int width) {
    this.columnName = columnName;
    this.width = width;
    this.description = columnName;
  }


  public RowField(String columnName,int width,String description) {
    this.columnName = columnName;
    this.width = width;
    this.description = description;
  }


  public RowField(String columnName,int width,GenericAggregator aggregator) {
    this.columnName = columnName;
    this.width = width;
    this.aggregator = aggregator;
    this.description = columnName;
  }


  public RowField(String columnName,int width,String description,GenericAggregator aggregator) {
    this.columnName = columnName;
    this.width = width;
    this.description = description;
    this.aggregator = aggregator;
  }


  public String getColumnName() {
    return columnName;
  }
  public int getWidth() {
    return width;
  }
  public GenericAggregator getAggregator() {
    return aggregator;
  }
  public String getDescription() {
    return description;
  }


  public final boolean equals(Object obj) {
    if (obj==null || !(obj instanceof RowField))
      return false;
    return
        ((RowField)obj).getColumnName().equals(getColumnName()) &&
        ((RowField)obj).getAggregator().equals(getAggregator());
  }


  public final int hashCode() {
    return getColumnName().hashCode()*getAggregator().hashCode();
  }


}
