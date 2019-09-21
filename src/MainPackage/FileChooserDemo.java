package MainPackage;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import FetchingExcelData.ExcelLib;
import WriteData.CreateDef;
import WriteData.CreateGpMod;
import WriteData.CreateIniFile;
import WriteData.CreateUninstallFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import FetchingExcelData.ExcelLib;
import WriteData.CreateDef;
/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class FileChooserDemo extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton browseButton,runButton;
    JTextArea log;
    JFileChooser fc;

    public FileChooserDemo() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(10,40);
        log.setMargin(new Insets(25,25,25,25));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        
        browseButton = new JButton("Browse");
        browseButton.addActionListener(this);
        
        runButton = new JButton("Run");
        runButton.addActionListener(this);
        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
      
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        
        buttonPanel.add(browseButton);
        buttonPanel.add(browseButton);
        buttonPanel.add(runButton);

        //Add the buttons and the log to this panel.
       add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == browseButton) {
            int returnVal = fc.showOpenDialog(FileChooserDemo.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String filePath=file.getAbsolutePath();
             
                ExcelLib excel=new ExcelLib();
                System.out.println(filePath);
                try 
        		{
        			int numberOfFields=excel.countFieldID(filePath,"Field Logic");
        			
        			
        			String fieldDescription[]=new String[numberOfFields];
        			String variable[]=new String[numberOfFields];
        			String documentName=excel.getDocumentName(filePath,"Form Info & Overview");
        			String formName=excel.getFormName(filePath, "Form Info & Overview");
        			String applicationName=excel.getApplicationName(filePath, "Form Info & Overview");
        			
        			String phaseName=excel.getPhase(filePath, "Form Info & Overview");
        			File dir = new File("D:\\"+documentName);
        			dir.mkdir();
        			
        			
        			
        		
        			PrintWriter outFile = new PrintWriter(new File("D:\\"+documentName+"\\"+documentName+".def"));
    				
        			PrintWriter outFile2=new PrintWriter(new File("D:\\"+documentName+"\\"+documentName+".ini"));
        			PrintWriter outFile3=new PrintWriter(new File("D:\\"+documentName+"\\"+documentName+"_UNINSTALL.txt"));
        			
        			outFile.println("1,-4,@FS:20<>@NULL,\"<GPFont(False,False,False,@BK:71,@BK:70)GPFont>{@FS:20}{-@FS:21}</GPFont>\",\" \",print barcode in this field");
        			for(int i=3;i<=numberOfFields-1;i++)
        			{
        				
        		    fieldDescription[i]= excel.getFieldDescription(filePath,"Field Logic", i);
        		  
        			
        			variable[i]=excel.getAPVariable(filePath,"Field Logic", i);
        			
        			CreateDef.createDef(fieldDescription, variable, documentName,i,outFile);
        			
        			}
        			
        			
         			CreateIniFile.createUpgarecodefile(documentName, outFile2);
         			CreateUninstallFile.createUninstallFile(formName, applicationName,outFile3);
         			
         			
        			outFile.close();
        			outFile2.close();
        			outFile3.close();
        			System.out.println("Def file has been created and located in D drive");
        			
        			
        			
        		} catch (EncryptedDocumentException | InvalidFormatException | IOException ex) {
        			// TODO Auto-generated catch block
        			ex.printStackTrace();
        		}
        		catch(Exception e1) {
        			
        		}
        		
                //This is where a real application would open the file.
                log.append("Creating files for: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooserDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new FileChooserDemo());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}
