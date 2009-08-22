package org.openswing.swing.internationalization.java;

import java.util.*;

/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Class for retrieve the collection of all internationalization properties:
 * translations, data/numeric/currency formats.
 * No translation is performed, date/numeric/currency formats are based on english formats.</p>
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
 * @author Mauro Carniel/Zolt�n Zidarics/Attila Szomor
 * @version 1.0
 */
public class HungarianOnlyResourceFactory extends ResourcesFactory {

    /** internationalization settings */
    private Resources resources = null;

    /**
     * Constructor.
     * @param currencySymbol currency symbol
     * @param additionalDictionary additional descriptions
     * @param showResourceNotFoundWarning warn when no resource key not found
     */
    public HungarianOnlyResourceFactory(String currencySymbol, Properties additionalDictionary, boolean showResourceNotFoundWarning) {
        this(currencySymbol, additionalDictionary, showResourceNotFoundWarning, '/');
    }

    /**
     * Constructor.
     * @param currencySymbol currency symbol
     * @param additionalDictionary additional descriptions
     * @param showResourceNotFoundWarning warn when no resource key not found
     * @param dateFormatSeparator date format separator; for example: '-' or '/'
     */
    public HungarianOnlyResourceFactory(String currencySymbol, Properties additionalDictionary, boolean showResourceNotFoundWarning, char dateFormatSeparator) {
        Properties dictionary = new Properties();

        dictionary.putAll(additionalDictionary);

        // grid...
        dictionary.setProperty("of", "/");
        dictionary.setProperty("page", "Oldal");
        dictionary.setProperty("Remove Filter", "Sz\u0171r\u0151felt�tel t�rl�se");
        dictionary.setProperty("This column is not sorteable", "Ezen oszlop alapj�n nem rendezhet\u0151");
        dictionary.setProperty("Sorting not allowed", "A rendez�s nem enged�lyezett");
        dictionary.setProperty("Maximum number of sorted columns", "Maxim�lisan rendezhet\u0151 oszlopok sz�ma");
        dictionary.setProperty("Sorting not applicable", "Rendez�s nem hajthat� v�cgre");
        dictionary.setProperty("Selected Row", "Kijel�lt sor");
        dictionary.setProperty("Selected Rows", "Kijel�lt sorok");
        dictionary.setProperty("Cancel changes and reload data?", "Eldobja a v�ltozt�sokat �s �jrat�lti az adatokat?");
        dictionary.setProperty("Attention", "Figyelmeztet�s");
        dictionary.setProperty("Loading data...", "Adatok bet�lt�se...");
        dictionary.setProperty("Error while loading data", "Hiba bet�lt�s k�zben");
        dictionary.setProperty("Loading Data Error", "Adathiba");
        dictionary.setProperty("Delete Rows?", "Sor t�rl�se?");
        dictionary.setProperty("Delete Confirmation", "T�rl�s meger\u0151s�t�s");
        dictionary.setProperty("Error while deleting rows.", "Hiba sor t�rl�se k�zben.");
        dictionary.setProperty("Deleting Error", "T�rl�s hiba");
        dictionary.setProperty("Error while saving", "Hiba ment�s k�zben");
        dictionary.setProperty("Saving Error", "Ment�s hiba");
        dictionary.setProperty("A mandatory column is empty.", "A k�telez\u0151en kit�ltend\u0151 oszlop �res.");
        dictionary.setProperty("Value not valid", "Hib�s �rt�k");
        dictionary.setProperty("sorting conditions", "Rendez�si felt�telek");
        dictionary.setProperty("filtering conditions", "Sz\u0171r�si felt�telek");
        dictionary.setProperty("filtering and sorting settings", "Sz\u0171r�si �s rendez�si be�ll�t�sok");
        dictionary.setProperty("Filtering/Sorting data (CTRL+F)", "Adat sz\u0171r�s/rendez�s (CTRL+F)");
        dictionary.setProperty("upload file", "F�jl felt�lt�se");
        dictionary.setProperty("download file", "F�jl let�lt�se");

        // export...
        dictionary.setProperty("grid export", "T�bla export�l�s");
        dictionary.setProperty("export", "Export�l�s");
        dictionary.setProperty("exportmnemonic", "X");
        dictionary.setProperty("column", "Oszlop");
        dictionary.setProperty("sel.", "Jel�l");
        dictionary.setProperty("you must select at least one column", "Legal�bb egy oszlopot ki kell jel�lnie");
        dictionary.setProperty("columns to export", "Export�land� oszlopok");
        dictionary.setProperty("export type", "Export form�tum");

        // import...
        dictionary.setProperty("grid import", "T�bla Import�l�s");
        dictionary.setProperty("file to import", "Import file");
        dictionary.setProperty("import", "Import");
        dictionary.setProperty("importmnemonic", "M");
        dictionary.setProperty("columns to import", "Import�land� oszlopok");
        dictionary.setProperty("import type", "Import form�tum");
        dictionary.setProperty("error while importing data", "Hiba import�l�s k�zben");
        dictionary.setProperty("import completed", "Import�l�s k�sz");

        // quick filter...
        dictionary.setProperty("To value", "Tartalomra");
        dictionary.setProperty("Filter by", "Sz\u0171r�s alapja");
        dictionary.setProperty("From value", "�rt�kt\u0151l");
        dictionary.setProperty("equals", "egyenl\u0151");
        dictionary.setProperty("contains", "tartalmazza");
        dictionary.setProperty("starts with", "kezd\u0151dik");
        dictionary.setProperty("ends with", "v�gz\u0151dik");

        // select/deselect all
        dictionary.setProperty("select all", "Az �sszes kiejl�l�se");
        dictionary.setProperty("deselect all", "Kijel�l�sek megsz�ntet�se");

        // copy/cut/paste
        dictionary.setProperty("copy", "M�sol");
        dictionary.setProperty("copymnemonic", "C");
        dictionary.setProperty("cut", "Kiv�g");
        dictionary.setProperty("cutmnemonic", "X");
        dictionary.setProperty("paste", "Beilleszt");
        dictionary.setProperty("pastemnemonic", "V");

        // lookup...
        dictionary.setProperty("Code is not correct.", "A k�d nem korrekt");
        dictionary.setProperty("Code Validation", "K�d valid�l�s");
        dictionary.setProperty("Code Selection", "K�d v�laszt�s");

        // form...
        dictionary.setProperty("Confirm deliting data?", "Biztos t�r�lni akarja?");
        dictionary.setProperty("Error while saving: incorrect data.", "Hiba ment�s k�zben: inkorrekt adat.");
        dictionary.setProperty("Error on deleting:", "Hiba t�rl�s k�zben:");
        dictionary.setProperty("Error on Loading", "Hiba bet�lt�s k�zben");
        dictionary.setProperty("Error while loading data:", "Hiba bet�lt�s k�zben:");
        dictionary.setProperty("Error on setting value to the input control having the attribute name", "Hiba be�ll�t�s k�zben az attributumnak nincs �rt�ke");

        // toolbar buttons...
        dictionary.setProperty("Delete record (CTRL+D)", "Rekord t�rl�s (CTRL+D)");
        dictionary.setProperty("Edit record (CTRL+E)", "Rekord szerkeszt�se (CTRL+E)");
        dictionary.setProperty("New record (CTRL+I)", "�j rekord (CTRL+I)");
        dictionary.setProperty("Reload record/Cancel current operation (CTRL+Z)", "Az aktu�lis oper�ci� t�rl�se/rekord �jrat�lt�se (CTRL+Z)");
        dictionary.setProperty("Save record (CTRL+S)", "Rekord ment�se (CTRL+S)");
        dictionary.setProperty("Copy record (CTRL+C)", "Rekord m�sol�sa (CTRL+C)");
        dictionary.setProperty("Export record (CTRL+X)", "Rekord export�l�sa (CTRL+X)");
        dictionary.setProperty("Import records (CTRL+M)", "Rekords import�l�sa (CTRL+M)");
        dictionary.setProperty("Load the first block of records", "Az els\u0151 blokk bet�lt�se");
        dictionary.setProperty("Select the previous row in grid", "A t�bla el\u0151z\u0151 sor�nak kiv�laszt�sa");
        dictionary.setProperty("Select the next row in grid", "A t�bla k�vetkez\u0151 sor�nak kiv�laszt�sa");
        dictionary.setProperty("Load the previous block of records", "Az el\u0151z\u0151 blokk bet�lt�se");
        dictionary.setProperty("Load the next block of records", "A k�vetkez\u0151 blokk bet�lt�se");
        dictionary.setProperty("Load the last block of records", "Az utols� blokk bet�lt�se");

        dictionary.setProperty("Insert", "Besz�r�s");
        dictionary.setProperty("Edit", "Szerkeszt�s");
        dictionary.setProperty("Copy", "M�sol�s");
        dictionary.setProperty("Delete", "T�rl�s");
        dictionary.setProperty("Save", "Ment�s");
        dictionary.setProperty("Reload", "�jrat�lt�s");
        dictionary.setProperty("Export", "Export");
        dictionary.setProperty("Filter", "Sz\u0171r�s");

        // MDI Frame...
        dictionary.setProperty("file", "F�jl");
        dictionary.setProperty("exit", "Kil�p�s");
        dictionary.setProperty("filemnemonic", "F");
        dictionary.setProperty("exitmnemonic", "K");
        dictionary.setProperty("change user", "Felhaszn�l� v�lt�s");
        dictionary.setProperty("changeusermnemonic", "V");
        dictionary.setProperty("changelanguagemnemonic", "L");
        dictionary.setProperty("help", "S�g�");
        dictionary.setProperty("about", "N�vjegy");
        dictionary.setProperty("helpmnemonic", "S");
        dictionary.setProperty("aboutmnemonic", "N");
        dictionary.setProperty("are you sure to quit application?", "Biztosan kil�p?");
        dictionary.setProperty("quit application", "Kil�p�s");
        dictionary.setProperty("forcegcmnemonic", "F");
        dictionary.setProperty("Force GC", "Tisztogat�s");
        dictionary.setProperty("Java Heap", "Java Heap");
        dictionary.setProperty("used", "haszn�lt");
        dictionary.setProperty("allocated", "allok�lva");
        dictionary.setProperty("change language", "Nyelv v�lt�s");
        dictionary.setProperty("changemnemonic", "N");
        dictionary.setProperty("cancelmnemonic", "C");
        dictionary.setProperty("cancel", "Eldob");
        dictionary.setProperty("yes", "Igen");
        dictionary.setProperty("no", "Nem");
        dictionary.setProperty("Functions", "Funkci�k");
        dictionary.setProperty("Error while executing function", "Hiba a funkci� v�grehajt�sa k�zben");
        dictionary.setProperty("Error", "Hiba");
        dictionary.setProperty("infoPanel", "Info");
        dictionary.setProperty("imageButton", "N�vjegy");
        dictionary.setProperty("Window", "Ablak");
        dictionary.setProperty("windowmnemonic", "W");
        dictionary.setProperty("Close All", "Bez�r mindent");
        dictionary.setProperty("closeallmnemonic", "A");
        dictionary.setProperty("Press ENTER to find function", "ENTER a funkci� keres�shez");
        dictionary.setProperty("Find Function", "Funkci� keres�s");
        dictionary.setProperty("Operation in progress...", "Oper�ci� v�grehajt�s alatt...");
        dictionary.setProperty("close window", "Ablak bez�r�s");
        dictionary.setProperty("reduce to icon", "Ikonm�ret");
        dictionary.setProperty("save changes?", "V�ltoz�sok elment�se?");
        dictionary.setProperty("confirm window closing", "Ablak bez�r�s meger\u0151s�t�se");
        dictionary.setProperty("change background", "H�tt�r csere");
        dictionary.setProperty("reset background", "H�tt�r friss�t�s");

        dictionary.setProperty("switch", "V�lt�s");
        dictionary.setProperty("switchmnemonic", "S");
        dictionary.setProperty("window name", "Ablak neve");
        dictionary.setProperty("opened windows", "Nyitott ablakok");
        dictionary.setProperty("tile horizontally", "V�zszintes elrendez�s");
        dictionary.setProperty("tilehorizontallymnemonic", "H");
        dictionary.setProperty("tile vertically", "F�gg\u0151leges elrendez�s");
        dictionary.setProperty("tileverticallymnemonic", "V");
        dictionary.setProperty("cascade", "Lez�dul� elrendez�s");
        dictionary.setProperty("cascademnemonic", "C");
        dictionary.setProperty("minimize", "Kis m�ret");
        dictionary.setProperty("minimizemnemonic", "M");
        dictionary.setProperty("minimize all", "Mind kis m�retre");
        dictionary.setProperty("minimizeallmnemonic", "A");

        // server...
        dictionary.setProperty("Client request not supported", "A k�r�s kiszolg�l�sa nem megval�s�tott");
        dictionary.setProperty("User disconnected", "Felhaszn�l� ki�cpett");
        dictionary.setProperty("Updating not performed: the record was previously updated.", "A m�dos�t�s nem siker�lt: a rekordot el\u0151z\u0151leg m�dos�tott�k.");

        // wizard...
        dictionary.setProperty("back", "Vissza");
        dictionary.setProperty("next", "K�vetkez\u0151");
        dictionary.setProperty("finish", "V�ge");

        // image panel...
        dictionary.setProperty("image selection", "K�p v�laszt�s");

        // tip of the day panel...
        dictionary.setProperty("show 'tip of the day' after launching", "Programind�t�skor napi tippek");
        dictionary.setProperty("previous tip", "El\u0151z\u0151 tipp");
        dictionary.setProperty("next tip", "K�vetkez\u0151 tipp");
        dictionary.setProperty("close", "Bez�r");
        dictionary.setProperty("tip of the day", "Napi tippek");
        dictionary.setProperty("select tip", "Tipp kiv�laszt�s");
        dictionary.setProperty("tip name", "Tipp neve");
        dictionary.setProperty("tips list", "Tippek list�ja");

        // progress panel...
        dictionary.setProperty("progress", "Folyamatban");

        // licence agreement...
        dictionary.setProperty("i accept the terms in the licence agreement", "Elfogadom a licensz-szerz\u0151d�sben foglaltakat");
        dictionary.setProperty("ok", "Rendben");
        dictionary.setProperty("i do not accept the terms in the licence agreement", "Nem fogadom el a licensz-szerz\u0151d�sben foglaltakat");

        // property grid control
        dictionary.setProperty("property name", "N�v");
        dictionary.setProperty("property value", "�rt�k");

        // grid profile
        dictionary.setProperty("grid profile management", "T�bla profil management");
        dictionary.setProperty("restore default grid profile", "Az alap�rtelmezett t�blaprofil visszat�lt�se");
        dictionary.setProperty("create new grid profile", "�j t�blaprofil");
        dictionary.setProperty("profile description", "Profil le�r�s");
        dictionary.setProperty("remove current grid profile", "T�blaprofil t�rl�se");
        dictionary.setProperty("select grid profile", "T�blaprofil v�laszt�s");
        dictionary.setProperty("default profile", "Alap�rtelmezett profil");

        // search box
        dictionary.setProperty("search", "Keres�s");
        dictionary.setProperty("not found", "Nem tal�lhat�");

        // drag...
        dictionary.setProperty("drag", "Drag");

        // pivot table...
        dictionary.setProperty("pivot table settings", "Pivot t�bla be�ll�t�sok");
        dictionary.setProperty("row fields", "Sor mez\u0151k");
        dictionary.setProperty("column fields", "Oszlop mez\u0151k");
        dictionary.setProperty("data fields", "Adat mez\u0151k");
        dictionary.setProperty("filtering conditions", "Sz\u0171r�si felt�telek");
        dictionary.setProperty("field", "Mez\u0151");
        dictionary.setProperty("checked", "Kijel�lt");
        dictionary.setProperty("at least one data field must be selected", "Minimum egy adat mez\u0151t k�telez\u0151 v�lasztani.");
        dictionary.setProperty("at least one row field must be selected", "Minimum egy sor mez\u0151t k�telez\u0151 v�lasztani.");
        dictionary.setProperty("at least one column field must be selected", "Minimum egy oszlop mez\u0151t k�telez\u0151 v�lasztani.");
        dictionary.setProperty("expand all", "Felfedi mind");
        dictionary.setProperty("collapse all", "Elrejti mind");

        // LoginDialog
        dictionary.setProperty("Logon", "Bejelentkez�s");
        dictionary.setProperty("Login", "Bel�p�s");
        dictionary.setProperty("L", "B");
        dictionary.setProperty("Exit", "Kil�p�s");
        dictionary.setProperty("E", "K");
        dictionary.setProperty("store account", "Elments�k a felhaszn�l�t");
        dictionary.setProperty("Username", "Felhaszn�l�");
        dictionary.setProperty("Password", "Jelsz�");

        resources = new Resources(
                dictionary,
                currencySymbol,
                '.',
                ',',
                Resources.YMD,
                true,
                dateFormatSeparator,
                "HH:mm",
                "HU",
                showResourceNotFoundWarning);
    }

    /**
     * @return internationalization settings, according with the current language
     */
    public final Resources getResources() {
        return resources;
    }

    /**
     * Load dictionary, according to the specified language id.
     * @param langId language id identifier
     */
    public final void setLanguage(String langId) throws UnsupportedOperationException {
        if (!resources.getLanguageId().equals(langId)) {
            throw new UnsupportedOperationException("Nem t�mogatott nyelv.");
        }
    }

    /**
     * @param langId language id identifier
     * @return internationalization settings, according with the language specified
     */
    public final Resources getResources(String langId) throws UnsupportedOperationException {
        if (!resources.getLanguageId().equals(langId)) {
            throw new UnsupportedOperationException("Nem t�mogatott nyelv.");
        }
        return resources;
    }
}
