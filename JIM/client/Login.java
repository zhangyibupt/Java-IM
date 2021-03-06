/**
 * Okénko přihlášení
 */

package JIM.client;
import java.util.regex.*;

/**
 *
 * @author cypher
 */
public class Login extends javax.swing.JDialog {
    //vzor pro IP adresu
    private Pattern ipPattern = Pattern.compile("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
    private Matcher ipMatcher = null; //matcher pro IP adresu
    private java.awt.Frame parent = null;
    private boolean modal = false;
    
    /** Creates new form Login */
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        this.modal = modal;
        setResizable(false);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        warning = new javax.swing.JLabel();
        server = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Přihlásit");

        jLabel1.setText("Uživatelské jméno:");

        jLabel2.setText("Heslo:");

        password.setPreferredSize(new java.awt.Dimension(100, 20));

        jButton1.setText("Přihlásit");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Registrovat");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jLabel3.setText("Nemám ještě účet");

        warning.setForeground(new java.awt.Color(255, 0, 0));

        server.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                serverKeyReleased(evt);
            }
        });

        jLabel4.setText("Server IP:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(username)
                                .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(server)
                                .addComponent(jButton1))
                            .addComponent(jButton2))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(warning, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(server, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * po kliknuti na tlacitko prihlasit - pokus o prihlaseni
     * @param evt
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(!validIp(server.getText())) warning.setText("Zadejte prosím platnou IP adresu.");
        else
        try {
            TCPConnection.serverIP = server.getText();
            Auth a = Auth.getInstance();
            a.login(username.getText(), password.getText());
            warning.setText(null);
            parent.setVisible(true);
            dispose();
        } catch(Exception e) {
            warning.setText("Neplatné uživatelské jméno nebo heslo.");
            e.printStackTrace();
        }
}//GEN-LAST:event_jButton1MouseClicked

    /**
     * kontrola platnosti IP adresy
     * @param evt
     */
    private void serverKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serverKeyReleased
        if(validIp(server.getText())) warning.setText("");
        else warning.setText("Zadejte prosím platnou IP adresu.");
    }//GEN-LAST:event_serverKeyReleased

    /**
     * okno pro registraci, kontroluje platnost adresy
     * @param evt
     */
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if(validIp(server.getText()))
        {
            warning.setText("");
            Register register = new Register(parent, true, this);
            register.setVisible(true);
            register.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        setVisible(true);
                        dispose();
                    }
                });
        }else warning.setText("Zadejte prosím platnou IP adresu.");
        
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * kontroluje platnost ip adresy
     * @param ip
     * @return
     */
    private boolean validIp(String ip) 
    {
        ipMatcher = ipPattern.matcher(ip);
        return ipMatcher.matches();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField server;
    private javax.swing.JTextField username;
    private javax.swing.JLabel warning;
    // End of variables declaration//GEN-END:variables

}
