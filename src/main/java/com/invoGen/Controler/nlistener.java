/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.invoGen.Controler;

/**
 *
 * @author toshıba pc
 */

import com.invoGen.İnvoGen_Frame_Run;
import com.invoGen.Model.invoFrame_model;
import com.invoGen.Model.invoLine_model;
import com.invoGen.Model.invoHeadTab_model;
import com.invoGen.Model.invoLinTab_model;
import com.invoGen.İnvoGen_Frame_Run;
import com.invoGen.View.İnvoHeadDia_view;
import com.invoGen.View.İnvolinDia_view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;






/**
 *
 * @author toshıba pc
 */
public class nlistener implements ActionListener, ListSelectionListener {

 private İnvoGen_Frame_Run frame;
    private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    
    public nlistener(İnvoGen_Frame_Run frame) {
        this.frame = frame;
    }
 @Override
    public void actionPerformed(ActionEvent e) {
switch (e.getActionCommand()){
case "CreateNewInvoice":
    
    displayNewInvoiceDialog();
    break;
case "DeleteInvoice":
     deleteInvoice();
    break;
    case "LoadFile":
  LoadFile();
  break;
  case "SaveFile":
 savedata();
case "CreateNewLine":
    displayNewLineDialog();
 break;
    
case "DeleteLine":
    deleteLineBtn();
    break;

 case "createInvCancel":
     createInvCancel();
     break;
     case "createInvOK":
         createInvOK();
     break;
     case "createLineCancel":
         createLineCancel();
         break;
     case "createLineOK":
         createLineOK();
         break;

}
    }
   private void LoadFile() {
        JOptionPane.showMessageDialog(frame, "Please, select header file!", "Attention", JOptionPane.WARNING_MESSAGE);
        JFileChooser openFile = new JFileChooser();
        int result = openFile.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = openFile.getSelectedFile();
            try{
            FileReader headerFr = new FileReader(headerFile);
            BufferedReader headerBr = new BufferedReader (headerFr);
            String headerLine = null;
            
            while (( headerLine = headerBr.readLine()) != null) {
            String[] headerParts = headerLine.split(",");
            String invNumStr = headerParts[0];
            String invDateStr = headerParts[1];
            String custName = headerParts[2];
 
            int invNum = Integer.parseInt(invNumStr);
            Date invDate = df.parse(invDateStr);
            
            invoFrame_model inv = new invoFrame_model(invNum, custName, invDate);
            frame.getInvoicesArray().add(inv);
            
            }
            
            JOptionPane.showMessageDialog(frame, "Please, select lines file!", "Attention", JOptionPane.WARNING_MESSAGE);
                result = openFile.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = openFile.getSelectedFile();
                    BufferedReader linesBr= new BufferedReader(new FileReader(linesFile));
                    String linesLine = null;
                    while ((linesLine = linesBr.readLine()) !=null) {
                        String[] lineParts = linesLine.split(",");
                        String invNumStr = lineParts[0];
                        String itemName = lineParts[1];
                        String itemPriceStr = lineParts[2];
                        String itemCountStr = lineParts[3];
                 int invNum = Integer.parseInt(invNumStr);
   double itemPrice = Double.parseDouble(itemPriceStr);
   int itemCount = Integer.parseInt(itemCountStr);
   invoFrame_model header = findInvoiceByNum(invNum);
   invoLine_model invLine = new invoLine_model(itemName, itemPrice, itemCount, header);
                  header.getLines().add(invLine);
                    }
 frame.setInvHeaderTableModel(new invoHeadTab_model(frame.getInvoicesArray()));
 frame.getInvoicesTable().setModel(frame.getInvHeaderTableModel());
 frame.getInvoicesTable().validate();

                }
                 System.out.println("Check");
            } catch (ParseException ex) {
                ex.printStackTrace();
               JOptionPane.showMessageDialog(frame, "Date Format Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Number Format Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "File Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Read Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        displayInvoices();
    }  


   
         
        
      private invoFrame_model findInvoiceByNum(int invNum){
    invoFrame_model header = null;
    for(invoFrame_model inv : frame.getInvoicesArray()) {
        if (invNum == inv.getInvNum()){
            header = inv;
            break;
        }
    }
    return header ;
}                 
    private void savedata() {
String headers = "";
        String lines = "";
        for (invoFrame_model header : frame.getInvoicesArray()) {
            headers += header.getDataAsCSV();
            headers += "\n";
            for (invoLine_model line : header.getLines()) {
                lines += line.getDataAsCSV();
                lines += "\n";
            }
        }
        JOptionPane.showMessageDialog(frame, "Please, select file to save header data!", "Attention", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fileChooser.getSelectedFile();
            try {
                FileWriter hFW = new FileWriter(headerFile);
                hFW.write(headers);
                hFW.flush();
                hFW.close();

                JOptionPane.showMessageDialog(frame, "Please, select file to save lines data!", "Attention", JOptionPane.WARNING_MESSAGE);
                result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fileChooser.getSelectedFile();
                    FileWriter lFW = new FileWriter(linesFile);
                    lFW.write(lines);
                    lFW.flush();
                    lFW.close();
                }
                                           JOptionPane.showMessageDialog(null, "File Saved Successfully ! ");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   

    @Override
    public void valueChanged(ListSelectionEvent e) {
    System.out.println("invoice is selected");
invoicesTableRowSelected();
    }

    private void invoicesTableRowSelected() {
int selectedRowIndex = frame.getInvoicesTable().getSelectedRow();
if (selectedRowIndex >= 0){
    invoFrame_model row = frame.getInvHeaderTableModel().getInvoicesArray().get(selectedRowIndex);
    frame.getCustNameTF().setText(row.getCustomerName());
    frame.getInvDateTF().setText(df.format(row.getInvDate()));
    frame.getInvNumLbl().setText("" + row.getInvNum());
    frame.getInvTotalLbl().setText("" + row.getInvTotal());
    ArrayList<invoLine_model> lines = row.getLines();
    frame.setInvLineTableModel(new invoLinTab_model(lines));
    frame.getInvLineTable().setModel(frame.getInvLineTableModel());
    frame.getInvLineTableModel().fireTableDataChanged();
    
}
    }
 private void deleteInvoice() {
        int invIndex = frame.getInvoicesTable().getSelectedRow();
        invoFrame_model header = frame.getInvHeaderTableModel().getInvoicesArray().get(invIndex);
        frame.getInvHeaderTableModel().getInvoicesArray().remove(invIndex);
       frame.getInvHeaderTableModel().fireTableDataChanged();
        frame.setInvLineTableModel(new invoLinTab_model(new ArrayList<invoLine_model>()));
        frame.getInvLineTable().setModel(frame.getInvLineTableModel());
        frame.getInvLineTableModel().fireTableDataChanged();
        frame.getCustNameTF().setText("");
        frame.getInvDateTF().setText("");
        frame.getInvNumLbl().setText("");
        frame.getInvTotalLbl().setText("");
        displayInvoices();
             JOptionPane.showMessageDialog(null, "The invoice is deleted ");
 

     }  

   
    private void deleteLineBtn() {
       
           
        int lineIndex = frame.getInvLineTable().getSelectedRow();
        invoLine_model line = frame.getInvLineTableModel().getInvoiceLines().get(lineIndex);
           frame.getInvLineTableModel().getInvoiceLines().remove(lineIndex);
            frame.getInvHeaderTableModel().fireTableDataChanged();
        frame.getInvLineTableModel().fireTableDataChanged();
                frame.getInvTotalLbl().setText("" + line.getHeader().getInvTotal());
         JOptionPane.showMessageDialog(null, " The line Deleted");
          displayInvoices();
        }      
      
    
     
     private void displayInvoices(){
         for (invoFrame_model header :frame.getInvoicesArray()) {
             System.out.println(header);
         }
     }
      
private void displayNewInvoiceDialog() {
frame.setHeaderDialog(new İnvoHeadDia_view(frame));
frame.getHeaderDialog().setVisible(true);
       
    }
  private void displayNewLineDialog() {
frame.setLineDialog(new İnvolinDia_view(frame));
frame.getLineDialog().setVisible(true);

    }
    
    private void createInvCancel() {
frame.getHeaderDialog().setVisible(false);
frame.getHeaderDialog().dispose();
frame.setHeaderDialog(null);
    }

    private void createInvOK() {
String custName = frame.getHeaderDialog().getCustNameField().getText();
String invDateStr = frame.getHeaderDialog().getInvDateField().getText();
frame.getHeaderDialog().setVisible(false);
frame.getHeaderDialog().dispose();
frame.setHeaderDialog(null);
try {
    Date invDate = df.parse(invDateStr);
    int invNum = getNextInvoiceNum();
    invoFrame_model invoiceFrame1 = new invoFrame_model(invNum, custName, invDate);
    frame.getInvoicesArray().add(invoiceFrame1);
    frame.getInvHeaderTableModel().fireTableDataChanged();}
    catch (ParseException ex) {
    JOptionPane.showMessageDialog(frame , "The Date Format is Wrong, please Modify " , "Error" , JOptionPane.ERROR_MESSAGE);
  ex.printStackTrace();
  displayInvoices();
}
    }
    
    private int getNextInvoiceNum() {
        int max = 0;
        for(invoFrame_model header : frame.getInvoicesArray()) {
            if (header.getInvNum()> max) {
                max = header.getInvNum();
                
            }
        }
        return max + 1;
    }

    private void createLineCancel() {
frame.getLineDialog().setVisible(false);
frame.getLineDialog().dispose();
frame.setLineDialog(null);
    }

    private void createLineOK() {
String itemName = frame.getLineDialog().getItemNameField().getText();
String itemCountStr = frame.getLineDialog().getItemCountField().getText();
String itemPriceStr = frame.getLineDialog().getItemPriceField().getText();
frame.getLineDialog().setVisible(false);
frame.getLineDialog().dispose();
frame.setLineDialog(null);
int itemCount = Integer.parseInt(itemCountStr);
double itemPrice = Double.parseDouble(itemPriceStr);
 int headerIndex = frame.getInvoicesTable().getSelectedRow();
        invoFrame_model invoice = frame.getInvHeaderTableModel().getInvoicesArray().get(headerIndex); 
        invoLine_model invoiceLine = new invoLine_model(itemName, itemPrice, itemCount, invoice);
    invoice.addInvLine(invoiceLine);
        frame.getInvLineTableModel().fireTableDataChanged();
        frame.getInvHeaderTableModel().fireTableDataChanged();
        frame.getInvTotalLbl().setText("" + invoice.getInvTotal());
      displayInvoices();   
    }

    
    }

