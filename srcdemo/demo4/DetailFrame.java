package demo4;

import javax.swing.*;
import java.awt.*;
import org.openswing.swing.client.*;
import java.util.*;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.internationalization.java.EnglishOnlyResourceFactory;
import org.openswing.swing.form.model.client.VOModel;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.util.java.Consts;
import java.sql.*;
import org.openswing.swing.message.receive.java.*;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.lookup.client.LookupDataLocator;
import org.openswing.swing.internationalization.java.Resources;
import javax.swing.border.*;
import javax.swing.text.MaskFormatter;
import org.openswing.swing.lookup.client.LookupListener;
import java.awt.event.*;


/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Demo Application: detail frame containing several input controls.</p>
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * <p> </p>
 * @author Mauro Carniel
 * @version 1.0
 */
public class DetailFrame extends JFrame {

  JPanel buttonsPanel = new JPanel();
  DateControl controlDate = new DateControl();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  CheckBoxControl controlCheck = new CheckBoxControl();
  LabelControl labelDate = new LabelControl();
  ComboBoxControl controlComboBox = new ComboBoxControl();
  LabelControl labelCombo = new LabelControl();
  CurrencyControl controlCurrency = new CurrencyControl();
  NumericControl controlNumeric = new NumericControl();
  RadioButtonControl controlRadioButton1 = new RadioButtonControl();
  TextControl controlText = new TextControl();
  MultiLineLabelControl labelCheckBox = new MultiLineLabelControl();
  LabelControl labelRadioButton = new LabelControl();

  private Form mainPanel = new Form();
  InsertButton insertButton = new InsertButton();
  FlowLayout flowLayout1 = new FlowLayout();
  EditButton editButton = new EditButton();
  ReloadButton reloadButton = new ReloadButton();
  SaveButton saveButton = new SaveButton();
  DeleteButton deleteButton = new DeleteButton();

  CodLookupControl controlLookup = new CodLookupControl();
  TextControl descrLookupControl = new TextControl();
  private Connection conn = null;
  CopyButton copyButton = new CopyButton();
  TextAreaControl controlTA = new TextAreaControl();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  ButtonGroup buttonGroup1 = new ButtonGroup();
  RadioButtonControl controlRadioButton2 = new RadioButtonControl();
  NavigatorBar navigatorBar = new NavigatorBar();
  FormattedTextControl controlFormattedText = new FormattedTextControl();
  CheckBoxListControl listControl1 = new CheckBoxListControl();
  PasswordControl controlPasswd = new PasswordControl();
  LinkButton linkButton1 = new LinkButton();
  GenericButton buttonPressMe = new GenericButton();
  LinkButton dynamicLinkButton = new LinkButton();
  LabelControl labelLink = new LabelControl();
  TextControl controlURI = new TextControl();
  TextControl controlLinkLabel = new TextControl();
  SpinnerListControl controlListSpinner = new SpinnerListControl();
  SpinnerNumberControl controlNumSpinner = new SpinnerNumberControl();
  FileControl controlFile = new FileControl();


  public DetailFrame(Connection conn,DetailFrameController dataController) {
    try {
      this.conn = conn;
      jbInit();

      mainPanel.setFormController(dataController);

      // link the parent grid to the current Form...
      HashSet pk = new HashSet();
      pk.add("stringValue"); // pk for Form is based on one only attribute...
      mainPanel.linkGrid(dataController.getGridFrame().getGrid(),pk,true,true,true,navigatorBar);


      setSize(700,500);
      setVisible(true);


    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }




  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    mainPanel.setVOClassName("demo4.DetailTestVO");
    mainPanel.setLayout(gridBagLayout1);
    labelCheckBox.setText("this text will\nbe translated");
    labelDate.setText("date");
    labelCombo.setTextAlignment(SwingConstants.RIGHT);
    labelCombo.setText("combobox");
    controlComboBox.setCanCopy(true);
    controlComboBox.setDomainId("ORDERSTATE");


    controlFormattedText.setAttributeName("formattedTextValue");
    controlFormattedText.setToolTipText("formattedTextValue");
    controlDate.setStrictUsage(true);

    MaskFormatter mask = new MaskFormatter("####/##.##.##");
//    MaskFormatter mask = new MaskFormatter("###-##-####");
    mask.setValidCharacters("0123456789");
//    mask.setValueContainsLiteralCharacters(false);


    controlFormattedText.setFormatter(mask);
    controlComboBox.setLinkLabel(labelCombo);
    controlComboBox.setRequired(false);
    controlCurrency.setCanCopy(true);
//    controlCurrency.setCurrencySymbolOnLeft(false);
    controlCurrency.setDecimals(2);
    controlCurrency.setMaxValue(1000.0);
    controlCurrency.setMinValue(-1000.0);
    controlCurrency.setRequired(false);
    controlCurrency.setTextAlignment(SwingConstants.RIGHT);
    controlNumeric.setCanCopy(true);
    controlNumeric.setDecimals(3);
    controlNumeric.setMinValue(-100.0);
    controlNumeric.setRequired(true);
    controlNumeric.setMaxCharacters(6);
    controlNumeric.setTextAlignment(SwingConstants.RIGHT);
    labelRadioButton.setText("radio button");
    controlText.setFont(new java.awt.Font(controlText.getFont().getName(), Font.BOLD, controlText.getFont().getSize()));
    //controlText.setMaxCharacters(7);
    controlText.setTrimText(true);
    controlText.setRpadding(true);
    controlText.setUpperCase(true);
    buttonsPanel.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    mainPanel.setBorder(titledBorder1);
    mainPanel.setInsertButton(insertButton);
    mainPanel.setCopyButton(copyButton);
    mainPanel.setEditButton(editButton);
    mainPanel.setReloadButton(reloadButton);
    mainPanel.setDeleteButton(deleteButton);
    mainPanel.setSaveButton(saveButton);
    saveButton.setEnabled(false);
    controlLookup.setAttributeName("lookupValue");
    controlLookup.setCanCopy(true);
    controlLookup.setMaxCharacters(5);
    controlLookup.setRequired(true);
    descrLookupControl.setAttributeName("descrLookupValue");
    descrLookupControl.setCanCopy(true);
    descrLookupControl.setEnabledOnInsert(false);
    descrLookupControl.setEnabledOnEdit(false);
    controlDate.setCanCopy(true);
    controlDate.setRequired(false);

    copyButton.setText("copyButton1");
    controlCheck.setCanCopy(true);
    controlRadioButton1.setCanCopy(true);
    controlRadioButton1.setText("Y");
    controlTA.setAttributeName("taValue");
    titledBorder1.setTitle("title");
    titledBorder1.setTitleColor(Color.blue);
    controlRadioButton2.setText("N");
    listControl1.setAttributeName("listValues");
    listControl1.setDomainId("LISTVALUES");
//    listControl1.setShowCheckBoxes(true);
    controlPasswd.setAttributeName("stringValue");
    controlPasswd.setTextAlignment(SwingConstants.LEFT);
    linkButton1.addActionListener(new DetailFrame_linkButton1_actionAdapter(this));
    linkButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    linkButton1.setLabel("OpenSwing Site");
    linkButton1.setUri("http://oswing.sourgeforge.net");
    linkButton1.setToolTipText("Click here to open OpenSwing Offical Site");

    dynamicLinkButton.setFont(new java.awt.Font("Dialog", 1, 11));
    dynamicLinkButton.setLabel("Dynamic Link");
    dynamicLinkButton.setLabelAttributeName("linkLabel");
    dynamicLinkButton.setUriAttributeName("uri");
    dynamicLinkButton.setTooltipAttributeName("tooltipURI");
    mainPanel.addLinkButton(dynamicLinkButton);

    controlListSpinner.setDomainId("ORDERSTATE");

    buttonPressMe.setButtonBehavior(Consts.BUTTON_TEXT_ONLY);
    buttonPressMe.setAttributeName("stringValue");
    labelLink.setText("uri");
    controlURI.setAttributeName("uri");
    controlLinkLabel.setAttributeName("linkLabel");
    controlListSpinner.setAttributeName("combo");
    controlNumSpinner.setAttributeName("year");
    controlNumSpinner.setInitialValue(new Double(2010.0));
    controlNumSpinner.setMaxValue(new Double(2015.0));
    controlNumSpinner.setMinValue(new Double(2010.0));
    controlFile.setAttributeName("file");
    controlFile.setFileNameAttributeName("filename");
    this.getContentPane().add(buttonsPanel,  BorderLayout.NORTH);
    buttonsPanel.add(insertButton, null);
    buttonsPanel.add(copyButton, null);
    buttonsPanel.add(editButton, null);
    buttonsPanel.add(reloadButton, null);
    buttonsPanel.add(saveButton, null);
    buttonsPanel.add(deleteButton, null);
    buttonsPanel.add(navigatorBar, null);
    this.getContentPane().add(mainPanel, BorderLayout.CENTER);
    mainPanel.add(controlDate,                     new GridBagConstraints(1, 0, 5, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 120, 0));
    mainPanel.add(labelCheckBox,                 new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlCheck,                  new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(labelDate,              new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlComboBox,                 new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(labelCombo,                   new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlCurrency,                  new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 80, 0));
    mainPanel.add(controlNumeric,                 new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 80, 0));
    mainPanel.add(labelRadioButton,                 new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlRadioButton1,               new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
    mainPanel.add(controlText,                 new GridBagConstraints(1, 6, 5, 1, 1.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
    mainPanel.add(controlLookup,                       new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 0), 0, 0));
    mainPanel.add(descrLookupControl,                new GridBagConstraints(1, 8, 5, 1, 1.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
    mainPanel.add(controlFormattedText,              new GridBagConstraints(3, 4, 3, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 150, 0));
    mainPanel.add(listControl1,            new GridBagConstraints(3, 1, 3, 3, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlPasswd,             new GridBagConstraints(1, 7, 5, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    mainPanel.add(buttonPressMe,                   new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 0, 0), 0, 0));
    mainPanel.add(labelLink,          new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlLinkLabel,        new GridBagConstraints(1, 11, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(linkButton1,       new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(dynamicLinkButton,       new GridBagConstraints(3, 10, 3, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlURI,         new GridBagConstraints(3, 11, 3, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlTA,               new GridBagConstraints(0, 9, 6, 1, 1.0, 1.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    mainPanel.add(controlRadioButton2,    new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    mainPanel.add(controlListSpinner,     new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
    mainPanel.add(controlNumSpinner,  new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    mainPanel.add(controlFile,    new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    controlText.setAttributeName("stringValue");
    controlText.setRequired(true);
    controlDate.setAttributeName("dateValue");
    controlCheck.setAttributeName("checkValue");
    controlRadioButton1.setAttributeName("radioButtonValue");
    controlRadioButton2.setAttributeName("radioButtonValue");
    controlCurrency.setAttributeName("currencyValue");
    controlNumeric.setAttributeName("numericValue");
    controlComboBox.setAttributeName("combo");
//    controlDate.setDateType(Consts.TYPE_DATE_TIME);
//    controlDate.setTimeFormat(Resources.H_MM_AAA);

    Calendar cal = Calendar.getInstance();
    cal.set(cal.MONTH,1);
    cal.set(cal.DAY_OF_MONTH,24);
    cal.set(cal.YEAR,1983);

    controlDate.setDefaultDate(cal);

    controlRadioButton1.setButtonGroup(buttonGroup1);
    controlRadioButton2.setButtonGroup(buttonGroup1);
    controlRadioButton1.setSelectedValue(Boolean.TRUE);
    controlRadioButton2.setSelectedValue(Boolean.FALSE);


    LookupController lookupController = new DemoLookupController(conn);
    //lookupController.setShowCustomErrorMessage(true);
    controlLookup.setLookupController(lookupController);
    lookupController.setOnInvalidCode(lookupController.ON_INVALID_CODE_RESTORE_LAST_VALID_CODE);
//    lookupController.setOnInvalidCode(lookupController.ON_INVALID_CODE_RESTORE_FOCUS);
    lookupController.addLookupListener(new LookupListener() {
      /**
       * beforeLookupAction
       *
       * @param parentVO ValueObject
       */
      public void beforeLookupAction(ValueObject parentVO) {
      }

      /**
       * codeChanged
       *
       * @param parentVO ValueObject
       * @param parentChangedAttributes Collection
       */
      public void codeChanged(ValueObject parentVO,Collection parentChangedAttributes) {
        DetailTestVO formVO = (DetailTestVO)parentVO;
        TestLookupVO lookupVO = (TestLookupVO)controlLookup.getLookupController().getLookupVO();
      }

      /**
       * codeValidated
       *
       * @param validated boolean
       */
      public void codeValidated(boolean validated) {
      }

      /**
       * forceValidate
       */
      public void forceValidate() {
      }
    });

  }


  public Form getMainPanel() {
    return mainPanel;
  }


  public SaveButton getSaveButton() {
    return saveButton;
  }


  public DeleteButton getDeleteButton() {
    return deleteButton;
  }


  public EditButton getEditButton() {
    return editButton;
  }

  void linkButton1_actionPerformed(ActionEvent e) {
    OptionPane.showMessageDialog(this,"Link pressed","Link Button",JOptionPane.INFORMATION_MESSAGE);
  }


}

class DetailFrame_linkButton1_actionAdapter implements java.awt.event.ActionListener {
  DetailFrame adaptee;

  DetailFrame_linkButton1_actionAdapter(DetailFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.linkButton1_actionPerformed(e);
  }
}
