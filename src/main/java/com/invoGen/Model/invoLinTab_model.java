/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.invoGen.Model;

/**
 *
 * @author toshıba pc
 */

import com.invoGen.Model.invoLine_model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


/**
 *
 * @author toshıba pc
 */
public class invoLinTab_model extends AbstractTableModel {   

    private List<invoLine_model>invoiceLines;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    
    public invoLinTab_model(List<invoLine_model>invoiceLines) {
    this.invoiceLines = invoiceLines;
     
    }

   public List<invoLine_model> getInvoiceLines() {
        return invoiceLines;
    }
    @Override
    public int getRowCount() {
return invoiceLines.size();
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }
        

    
@Override
    public String getColumnName(int columnIndex){
      switch (columnIndex) {
          case 0 :
              return "Item Name";
          case 1 :
              return "item Price";
          case 2 :
              return "Count";
          case 3 :
              return "Line Total";
          default:
              return "";
      }
    }
   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        invoLine_model row =invoiceLines.get(rowIndex);
        switch(columnIndex){
            case 0: return row.getItemName();
            case 1 : return row.getItemPrice();
            case 2 : return row.getItemCount();
            case 3 : return row.getLineTotal();
            default:
            return null;
    }
}

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
          case 0 :
              return String.class;
          case 1 :
              return Double.class;
          case 2 :
              return Integer.class;
          case 3 :
              return Double.class;
          default:
              return Object.class;
    }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
    }


 }
 

/**
 *
 * @author toshıba pc
 */