package net.sf.jabref.gui.entryeditor;

import java.util.HashMap;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.jabref.gui.BasePanel;
import net.sf.jabref.gui.JabRefFrame;
import net.sf.jabref.logic.pdf.PdfCommentImporter;
import net.sf.jabref.model.entry.FieldName;

public class PdfCommentsTab {

    private final JPanel panel = new JPanel();

    private JList<String> commentList = new JList<>();

    private final JScrollPane scrollPane = new JScrollPane();

    //TODO add final

    private EntryEditor parent;

    private String tabTitle;

    private JabRefFrame frame;

    private BasePanel basePanel;

    public PdfCommentsTab(EntryEditor parent, JabRefFrame frame, BasePanel basePanel) {
        this.parent = parent;
        this.frame = frame;
        this.basePanel = basePanel;
        this.tabTitle = "PDF comments";
        this.setUpPdfCommentsTab();
    }

    private void setUpPdfCommentsTab() {
        Optional<String> field = parent.getEntry().getField(FieldName.FILE);
        if (field.isPresent()) {
            DefaultListModel<String> listModel = new DefaultListModel<>();
            commentList.setModel(listModel);
            PdfCommentImporter commentImporter = new PdfCommentImporter();
            HashMap<String, String> importedNotes = commentImporter.importNotes(field.get());
            for (String note : importedNotes.values()) listModel.addElement(note);

        }

    }

}
