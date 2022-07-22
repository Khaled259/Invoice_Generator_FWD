/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.invoGen.View;

/**
 *
 * @author toshıba pc
 */

import com.invoGen.İnvoGen_Frame_Run;
import com.invoGen.İnvoGen_Frame_Run;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author toshıba pc
 */
public class İnvoHeadDia_view extends JDialog{
private JTextField custNameField;
    private JTextField invDateField;
    private JLabel custNameLbl;
    private JLabel invDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public İnvoHeadDia_view(İnvoGen_Frame_Run frame) {

        custNameLbl = new JLabel("Customer Name:");
        custNameField = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
 
        okBtn.setActionCommand("createInvOK");
        cancelBtn.setActionCommand("createInvCancel");
       okBtn.addActionListener(frame.getListener());
         cancelBtn.addActionListener(frame.getListener());
        setLayout(new GridLayout(3, 2));
        
        add(invDateLbl);
        add(invDateField);
        add(custNameLbl);
        add(custNameField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }
    public İnvoHeadDia_view(com.invoGen.View.invoGen_Frame invoiceFrame) {
        
    }

    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getInvDateField() {
        return invDateField;
    }

    
}
