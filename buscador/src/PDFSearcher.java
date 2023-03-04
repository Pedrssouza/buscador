import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFSearcher extends JFrame implements ActionListener {

    private JTextField searchField;
    private JButton searchButton;

    public PDFSearcher() {
        super("PDF Searcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        getContentPane().add(searchPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PDFSearcher();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchTerm = searchField.getText();
            openPDF(searchTerm);
        }
    }

    public static void openPDF(String searchTerm) {
        String folderPath = "C:/Users/Pedro Henrique/OneDrive/Área de Trabalho/Teste";
        List<File> pdfFiles = searchForPDFs(folderPath, searchTerm);

        if (pdfFiles.size() == 1) {
            try {
                File file = pdfFiles.get(0);
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (pdfFiles.size() > 1) {
            JOptionPane.showMessageDialog(null, "More than one file found for search term: " + searchTerm);
        } else {
            JOptionPane.showMessageDialog(null, "No files found for search term: " + searchTerm);
        }
    }

    public static List<File> searchForPDFs(String folderPath, String searchTerm) {
        List<File> pdfFiles = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".pdf")
                    && file.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                pdfFiles.add(file);
            }
        }

        return pdfFiles;
    }
}
