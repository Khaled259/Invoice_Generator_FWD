/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.invoGen.Model;

/**
 *
 * @author toshıba pc
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author toshıba pc
 */
public class invoFrame_model {
  private int invNum;
private String customerName;
private Date invDate;
private ArrayList<invoLine_model> lines;



    public invoFrame_model(int invNum, String customerName, Date invDate) {
        this.invNum = invNum;
        this.customerName = customerName;
        this.invDate = invDate;
       // this.lines = new ArrayList<>();
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        String str="InvoiceFram1{" + "invNum=" + invNum + ", customerName=" + customerName + ", invDate=" + invDate + '}' ;
       for(invoLine_model line: getLines()){
           str += "\n\t" + line;
       } 
        return str;
    }

    public ArrayList<invoLine_model> getLines() {
        if (lines == null)
            lines = new ArrayList<>();
        return lines;
    }

    public void setLines(ArrayList<invoLine_model> lines) {
        this.lines = lines;
    }

    public double getInvTotal() {
        double total = 0.0;
        for (invoLine_model line : getLines()){
            total += line.getLineTotal();
        }
        return total;
    }

     public void addInvLine(invoLine_model line){
        getLines().add(line);
        
     }
 public String getDataAsCSV() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getInvNum() + "," + df.format(getInvDate()) + "," + getCustomerName();

}
 }

/**
 *
 * @author toshıba pc
 */
