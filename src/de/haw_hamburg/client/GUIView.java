/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.haw_hamburg.client;

import de.haw_hamburg.common.OutgoingMessage;
import de.haw_hamburg.common.User;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author patrick
 */
public class GUIView extends javax.swing.JFrame {

    public static GUIView getGUIView() {
        if (GUIView.guiView == null) {
            GUIView.guiView = new GUIView();
        }
        return GUIView.guiView;
    }
    private GUI gui;

    GUIView(GUI gui) {
        initComponents();
        setGUI(gui);
        setVisible(true);
    }

    public void setUserName(String userName) {
		this.userName = userName;
	}
    
    public String getUsername() {
        return this.userName;
    }

    /**
     * Creates new form GUI
     */
    private GUIView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        submitButton = new javax.swing.JButton();
        chatLogScrollPane = new javax.swing.JScrollPane();
        chatLogTextArea = new javax.swing.JTextArea();
        clientsListScrollPane = new javax.swing.JScrollPane();
        userList = new javax.swing.JList();
        chatEntryTextField = new javax.swing.JTextField();

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jDialog2Layout = new org.jdesktop.layout.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        submitButton.setText("send");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitChatText(evt);
            }
        });

        chatLogScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        chatLogTextArea.setEditable(false);
        chatLogTextArea.setColumns(20);
        chatLogTextArea.setRows(5);
        chatLogTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chatLogTextArea.setEnabled(false);
        chatLogTextArea.setRequestFocusEnabled(false);
        chatLogScrollPane.setViewportView(chatLogTextArea);

        clientsListScrollPane.setEnabled(false);
        clientsListScrollPane.setHorizontalScrollBar(null);

        userList.setFocusTraversalKeysEnabled(false);
        userList.setFocusable(false);
        userList.setOpaque(false);
        userList.setSelectionBackground(new java.awt.Color(255, 255, 255));
        userList.setSelectionForeground(new java.awt.Color(0, 0, 0));
        clientsListScrollPane.setViewportView(userList);

        chatEntryTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chatEntryTextFieldKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(chatLogScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 673, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(chatEntryTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 592, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(submitButton)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(clientsListScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(chatLogScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 554, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(chatEntryTextField)
                            .add(submitButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(clientsListScrollPane))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitChatText(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitChatText
        submit();
    }//GEN-LAST:event_submitChatText

    private void chatEntryTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chatEntryTextFieldKeyReleased
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            submit();
        }
    }//GEN-LAST:event_chatEntryTextFieldKeyReleased

    private void submit() {
        // 1. Get Text from InputBox
        String chatEntry = new String(chatEntryTextField.getText());

        if (chatEntry.length() <= 100) {
        	submitButton.setEnabled(false);
        	
            // 2. clear the input box
            chatEntryTextField.setText("");
            // 3. Add the text to the Chatlog
            addEntryToChatLogScrollPane(getUsername() + ": " + chatEntry + "\n");
            // 4. Send to all connected clients via a ClientCommunicator
            ClientCommunicator.sendMessage(getClient().getUserListWithoutUs(), OutgoingMessage.createOutgoingMessage(getUsername(), chatEntry));
            submitButton.setEnabled(true);
        } else {
            ErrorDialog errorDialog = new ErrorDialog(this, true);
            errorDialog.setVisible(true);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed"
        // desc=" Look and feel setting code (optional) ">
		/*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase
         * /tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField chatEntryTextField;
    private javax.swing.JScrollPane chatLogScrollPane;
    private javax.swing.JTextArea chatLogTextArea;
    private javax.swing.JScrollPane clientsListScrollPane;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JButton submitButton;
    private javax.swing.JList userList;
    // End of variables declaration//GEN-END:variables
    private String userName;
    private static GUIView guiView;
    
    private final Logger LOG=Logger.getLogger(GUIView.class.getName());

    public void addEntryToChatLogScrollPane(String message) {
    	LOG.info("Appending message: "+message);
        this.chatLogTextArea.append(message.trim()+"\n");
    }

    private void setGUI(GUI gui) {
        if (gui != null) {
            this.gui = gui;
        }
    }

    private GUI getGUI() {
        return this.gui;
    }

    private Client getClient() {
        return getGUI().getClient();
    }

    void setUserList(List<User> newUserList) {
        synchronized(userList) {
            this.userList.setListData(newUserList.toArray());
        }
    }

}
