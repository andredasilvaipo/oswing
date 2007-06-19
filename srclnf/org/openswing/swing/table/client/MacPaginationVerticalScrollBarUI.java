package org.openswing.swing.table.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import com.sun.java.swing.plaf.mac.MacScrollBarUI;
import com.sun.java.swing.plaf.mac.MacScrollButton;
import com.sun.java.swing.plaf.mac.MacLookAndFeel;
import javax.swing.plaf.ColorUIResource;


/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Vertical scrollbar UI, used inside the pagination vertical scrollbar of the grid control, for Mac LnF.</p>
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
public class MacPaginationVerticalScrollBarUI extends MacScrollBarUI implements PaginationVerticalScrollbarUI {

  protected JButton nextPgButton;
  protected JButton prevPgButton;


  public MacPaginationVerticalScrollBarUI() {
    super();
  }


  public static ComponentUI createUI(JComponent c)    {
      return new MacPaginationVerticalScrollBarUI();
  }

  protected JButton createPageButton(int orientation)  {
    return new PageArrowButton(orientation);
  }


  protected void installDefaults() {
    super.installDefaults();
    nextPgButton = createPageButton(SOUTH);
    prevPgButton = createPageButton(NORTH);
    scrollbar.add(prevPgButton);
    scrollbar.add(nextPgButton);
  }


  protected void layoutVScrollbar(JScrollBar sb) {
      Dimension sbSize = sb.getSize();
      Insets sbInsets = sb.getInsets();

      /*
       * Width and left edge of the buttons and thumb.
       */
      int itemW = sbSize.width - (sbInsets.left + sbInsets.right);
      int itemX = sbInsets.left;

      /* Nominal locations of the buttons, assuming their preferred
       * size will fit.
       */
      int prevPgButtonH = prevPgButton.getPreferredSize().height;
      int prevPgButtonY = sbInsets.top;

      int decrButtonH = decrButton.getPreferredSize().height;
      int decrButtonY = prevPgButtonY+prevPgButtonH;

      int incrButtonH = incrButton.getPreferredSize().height;
      int nextPgButtonH = nextPgButton.getPreferredSize().height;

      int incrButtonY = sbSize.height - (sbInsets.bottom + incrButtonH+nextPgButtonH);
      int nextPgButtonY = sbSize.height - (sbInsets.bottom + nextPgButtonH);



      /* The thumb must fit within the height left over after we
       * subtract the preferredSize of the buttons and the insets.
       */
      int sbInsetsH = sbInsets.top + sbInsets.bottom;
      int sbButtonsH = decrButtonH + incrButtonH +prevPgButtonH +nextPgButtonH;
      float trackH = sbSize.height - (sbInsetsH + sbButtonsH);

      /* Compute the height and origin of the thumb.   The case
       * where the thumb is at the bottom edge is handled specially
       * to avoid numerical problems in computing thumbY.  Enforce
       * the thumbs min/max dimensions.  If the thumb doesn't
       * fit in the track (trackH) we'll hide it later.
       */
      float min = sb.getMinimum();
      float extent = sb.getVisibleAmount();
      float range = sb.getMaximum() - min;
      float value = sb.getValue();

      int thumbH = (range <= 0)
          ? getMaximumThumbSize().height : (int)(trackH * (extent / range));
      thumbH = Math.max(thumbH, getMinimumThumbSize().height);
      thumbH = Math.min(thumbH, getMaximumThumbSize().height);

      int thumbY = incrButtonY - thumbH;
      if (sb.getValue() < (sb.getMaximum() - sb.getVisibleAmount())) {
          float thumbRange = trackH - thumbH;
          thumbY = (int)(0.5f + (thumbRange * ((value - min) / (range - extent))));
          thumbY +=  decrButtonY + decrButtonH;
      }

      /* If the buttons don't fit, allocate half of the available
       * space to each and move the lower one (incrButton) down.
       */
      int sbAvailButtonH = (sbSize.height - sbInsetsH);
      if (sbAvailButtonH < sbButtonsH) {
          incrButtonH = decrButtonH = sbAvailButtonH / 2;
          incrButtonY = sbSize.height - (sbInsets.bottom + incrButtonH);
      }
      prevPgButton.setBounds(itemX, prevPgButtonY, itemW, prevPgButtonH);
      decrButton.setBounds(itemX, decrButtonY, itemW, decrButtonH);
      incrButton.setBounds(itemX, incrButtonY, itemW, incrButtonH);
      nextPgButton.setBounds(itemX, nextPgButtonY, itemW, nextPgButtonH);

      /* Update the trackRect field.
       */
      int itrackY = decrButtonY + decrButtonH;
      int itrackH = incrButtonY - itrackY;
      trackRect.setBounds(itemX, itrackY, itemW, itrackH);

      /* If the thumb isn't going to fit, zero it's bounds.  Otherwise
       * make sure it fits between the buttons.  Note that setting the
       * thumbs bounds will cause a repaint.
       */
      if(thumbH >= (int)trackH)	{
          setThumbBounds(0, 0, 0, 0);
      }
      else {
          if ((thumbY + thumbH) > incrButtonY) {
              thumbY = incrButtonY - thumbH;
          }
          if (thumbY  < (decrButtonY + decrButtonH)) {
              thumbY = decrButtonY + decrButtonH + 1;
          }
          setThumbBounds(itemX, thumbY, itemW, thumbH);
      }
  }

  protected void installListeners() {
    super.installListeners();
  }

  protected void uninstallListeners() {
    super.uninstallListeners();
  }

  public JButton getNextPgButton() {
    return nextPgButton;
  }

  public JButton getDecrButton() {
    return decrButton;
  }

  public JButton getIncrButton() {
    return incrButton;
  }

  public JButton getPrevPgButton() {
    return prevPgButton;
  }


  /**
   * <p>Title: OpenSwing Framework</p>
   * <p>Description: Inner class used to render the scrollbar buttons.</p>
   * @author Mauro Carniel
   * @version 1.0
   */
  class PageArrowButton extends MacScrollButton {

    private Color background = UIManager.getColor("ScrollBar.arrowBackground");
    private Color highlight = UIManager.getColor("ScrollBar.arrowHighlight");
    private Color shadow = UIManager.getColor("ScrollBar.arrowShadow");
    private Color pressedBackground = UIManager.getColor("ScrollBar.pressedArrowBackground");
    private Color pressedHighlight = UIManager.getColor("ScrollBar.pressedArrowHighlight");
    private Color pressedShadow = UIManager.getColor("ScrollBar.pressedArrowShadow");
    private Color arrowColor = UIManager.getColor("ScrollBar.arrowColor");
    private int buttonWidth;
    private final ColorUIResource gray0 = new ColorUIResource(238, 238, 238);
    private final ColorUIResource gray3 = new ColorUIResource(187, 187, 187);
    private final ColorUIResource gray6 = new ColorUIResource(136, 136, 136);
    private final ColorUIResource gray9 = new ColorUIResource(85, 85, 85);


    public PageArrowButton(int direction) {
      super(direction,incrButton.getPreferredSize().width);
      buttonWidth = incrButton.getPreferredSize().width;
    }

    public void paint(Graphics g) {
      if (background==null)
        background = Color.white;
      if (highlight==null)
        highlight = new Color(240,240,240);
      if (shadow==null)
        shadow = new Color(20,20,20);
      if (pressedHighlight==null)
        pressedHighlight = new Color(200,200,200);
      if (pressedShadow==null)
        pressedShadow = new Color(10,10,10);
      if (arrowColor==null)
        arrowColor = new Color(1,1,1);

      ButtonModel buttonmodel = getModel();
      boolean flag = buttonmodel.isArmed() && buttonmodel.isPressed();
      if(isEnabled())
          g.setColor(flag ? pressedBackground : background);
      else
          g.setColor(gray0);
      g.fillRect(0, 0, buttonWidth, buttonWidth);
      g.setColor(flag ? pressedShadow : highlight);
      g.drawLine(0, 0, buttonWidth - 2, 0);
      g.drawLine(0, 0, 0, buttonWidth - 2);
      g.setColor(flag ? pressedHighlight : shadow);
      g.drawLine(buttonWidth - 1, 1, buttonWidth - 1, buttonWidth - 1);
      g.drawLine(1, buttonWidth - 1, buttonWidth - 1, buttonWidth - 1);

      g.setColor(((Color) (isEnabled() ? arrowColor : ((Color) (gray6)))));
      int h = -3;
      switch(getDirection())
      {
      case 1: // '\001'
          g.drawLine(6, 5+h, 7, 5+h);
          g.drawLine(5, 6+h, 8, 6+h);
          g.drawLine(4, 7+h, 9, 7+h);
          g.drawLine(3, 8+h, 10, 8+h);
          h = getHeight()-14;
          g.drawLine(6, 5+h, 7, 5+h);
          g.drawLine(5, 6+h, 8, 6+h);
          g.drawLine(4, 7+h, 9, 7+h);
          g.drawLine(3, 8+h, 10, 8+h);
          break;

      case 5: // '\005'
          g.drawLine(3, 5+h, 10, 5+h);
          g.drawLine(4, 6+h, 9, 6+h);
          g.drawLine(5, 7+h, 8, 7+h);
          g.drawLine(6, 8+h, 7, 8+h);
          h = getHeight()-14;
          g.drawLine(3, 5+h, 10, 5+h);
          g.drawLine(4, 6+h, 9, 6+h);
          g.drawLine(5, 7+h, 8, 7+h);
          g.drawLine(6, 8+h, 7, 8+h);

          break;
      }

    }
  }



}
