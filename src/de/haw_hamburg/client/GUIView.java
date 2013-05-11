/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.haw_hamburg.client;

import de.haw_hamburg.common.OutgoingMessage;
import de.haw_hamburg.common.User;
import java.awt.event.KeyEvent;
import java.util.List;

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
        chatEntryBoxScrollPane = new javax.swing.JScrollPane();
        chatEntryBox = new javax.swing.JTextArea();
        submitButton = new javax.swing.JButton();
        chatLogScrollPane = new javax.swing.JScrollPane();
        chatLogTextArea = new javax.swing.JTextArea();
        clientsListScrollPane = new javax.swing.JScrollPane();
        userList = new javax.swing.JList();

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

        chatEntryBox.setColumns(20);
        chatEntryBox.setRows(5);
        chatEntryBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enterPressed(evt);
            }
        });
        chatEntryBoxScrollPane.setViewportView(chatEntryBox);

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

        userList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        userList.setFocusTraversalKeysEnabled(false);
        userList.setFocusable(false);
        userList.setOpaque(false);
        userList.setSelectionBackground(new java.awt.Color(255, 255, 255));
        userList.setSelectionForeground(new java.awt.Color(0, 0, 0));
        clientsListScrollPane.setViewportView(userList);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(chatLogScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 673, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(chatEntryBoxScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 592, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(submitButton)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(clientsListScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(chatLogScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(chatEntryBoxScrollPane)
                            .add(submitButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(clientsListScrollPane))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitChatText(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitChatText
        submit();
    }//GEN-LAST:event_submitChatText

    private void enterPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enterPressed
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            submit();
        }
    }//GEN-LAST:event_enterPressed

    private void submit() {
        // 1. Get Text from InputBox
        String chatEntry = chatEntryBox.getText();

        if (chatEntry.length() <= 100) {
            submitButton.setEnabled(false);
            // 2. clear the input box
            chatEntryBox.setText("");
            // 3. Add the text to the Chatlog
            addEntryToChatLogScrollPane(chatEntry);
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
    private javax.swing.JTextArea chatEntryBox;
    private javax.swing.JScrollPane chatEntryBoxScrollPane;
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

    public void addEntryToChatLogScrollPane(String message) {
        this.chatLogTextArea.append(message);
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
