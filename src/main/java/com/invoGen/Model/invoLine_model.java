/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.invoGen.Model;

/**
 *
 * @author toshıba pc
 */

import com.invoGen.Model.invoFrame_model;

/**
 *
 * @author toshıba pc
 */
public class invoLine_model {
private String itemName;
private double itemPrice;
private int itemCount;
private double lineTotal;
private invoFrame_model header;

    public invoLine_model(String itemName, double itemPrice, int itemCount, invoFrame_model header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
        this.setLineTotal(this.itemCount*this.itemPrice);
    }

     
    public invoFrame_model getHeader() {
        return header;
    }

    public void setHeader(invoFrame_model header) {
        this.header = header;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getLineTotal(){
        return lineTotal;
    }

    private void setLineTotal(double lineTotal) {
this.lineTotal = lineTotal;
  }
     public String getDataAsCSV() {
        return "" + getHeader().getInvNum() + "," + getItemName() + "," + getItemPrice() + "," + getItemCount();
    }
}


/**
 *
 * @author toshıba pc
 */