/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apotek;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author ifan
 */
public class penjualan extends javax.swing.JFrame {
private Connection con;
    private Statement stat;
    private ResultSet res;
private Object tabel;
    /**
     * Creates new form penjualan
     */
    public penjualan() {
        initComponents();
        koneksi();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
Dimension frameSize=this.getSize();
if(frameSize.height > screenSize.height){
frameSize.height=screenSize.height;
}
if(frameSize.width > screenSize.width){
frameSize.width=screenSize.width;
}
this.setLocation((screenSize.width - frameSize.width) / 2,
(screenSize.height = screenSize.height) / 8);
Table.setModel(tableModel);
Tabel(Table, new int[]{90,200,90,90,90,70,100,100});
setDefaultTable();
SetEditOff();
TampilComboTransaksi();
TampilComboKasir();
TampilComboObat();
TampilComboPengunjung();
    }
private void koneksi(){
    try{
       Class.forName("com.mysql.jdbc.Driver");
       con=DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
       stat=con.createStatement();
   } catch (Exception e){
       JOptionPane.showMessageDialog(null, e);
   }
    }
private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
private void Tabel(javax.swing.JTable tb, int lebar[] ) {
tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
int kolom=tb.getColumnCount();
for(int i=0;i < kolom;i++) {
javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
tbc.setPreferredWidth(lebar[i]);
tb.setRowHeight(17);
}
}
private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
return new javax.swing.table.DefaultTableModel(
new Object[][] {},
new String [] {"Kode Penjualan","detail penjualan","Kode supplier","Kode Obat","Harga","Kode Pembeli","Jumlah","Total"}
){
boolean[] canEdit = new boolean[]{
false, false, false, false
};
public boolean isCellEditable(int rowIndex, int columnIndex){
return canEdit[columnIndex];
}
}; 
}
String data[]=new String[8];
private void setDefaultTable() {
String stat ="";
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement stt = kon.createStatement();
String SQL = "SELECT * FROM penjualan";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
data[0] = res.getString(1);
data[1] = res.getString(2);
data[2] = res.getString(3);
data[3] = res.getString(4);
data[4] = res.getString(5);
data[5] = res.getString(6);
data[6] = res.getString(7);
data[7] = res.getString(8);
tableModel.addRow(data);
}
res.close();
stt.close();
kon.close();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
} 
int row = 0;
public void Tampil(){
row = Table.getSelectedRow();
KodePembayaran.setText(tableModel.getValueAt(row, 0).toString());
no_faktur.setSelectedItem(tableModel.getValueAt(row, 1).toString());
KodeKasir.setSelectedItem(tableModel.getValueAt(row, 2).toString());
KodeObat.setSelectedItem(tableModel.getValueAt(row, 3).toString());
Harga.setText(tableModel.getValueAt(row, 4).toString());
KodePengunjung.setSelectedItem(tableModel.getValueAt(row, 5).toString());
Jumlah.setText(tableModel.getValueAt(row, 6).toString());
Total.setText(tableModel.getValueAt(row, 7).toString());
Save.setEnabled(false);
Delete.setEnabled(true);
SetEditOn();
} 
public void BersihData(){
KodePembayaran.setText("");
no_faktur.setSelectedIndex(0);
KodeKasir.setSelectedIndex(0);
KodeObat.setSelectedIndex(0);
Harga.setText("");
KodePengunjung.setSelectedIndex(0);
Jumlah.setText("");
Total.setText("");
}
public void SetEditOff(){
KodePembayaran.setEnabled(false);
no_faktur.setEnabled(false);
KodeKasir.setEnabled(false);
KodeObat.setEnabled(false);
Harga.setEnabled(false);
KodePengunjung.setEnabled(false);
Jumlah.setEnabled(false);
Total.setEnabled(false);
}
public void SetEditOn(){
KodePembayaran.setEnabled(true);
no_faktur.setEnabled(true);
KodeKasir.setEnabled(true);
KodeObat.setEnabled(true);
Harga.setEnabled(true);
KodePengunjung.setEnabled(true);
Jumlah.setEnabled(true);
Total.setEnabled(true);
}
public void TampilComboTransaksi(){
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement  stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM detai penjualan";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
no_faktur.addItem(res.getString("no_faktur"));
}
} catch (SQLException ex) {
}
}
public void TampilComboKasir(){
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement  stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM supplier";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
KodeKasir.addItem(res.getString("kode_supplier"));
}
} catch (SQLException ex) {
}
}
public void TampilComboObat(){
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement  stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM obat";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
KodeObat.addItem(res.getString("kode_obat"));
}
} catch (SQLException ex) {
}
}
public void TampilComboPengunjung(){
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement  stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM pembeli";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
KodePengunjung.addItem(res.getString("kode_pembeli"));
}
} catch (SQLException ex) {
}
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Jumlah = new javax.swing.JTextField();
        Total = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        KodePembayaran = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AddNew = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        no_faktur = new javax.swing.JComboBox();
        KodeKasir = new javax.swing.JComboBox();
        KodeObat = new javax.swing.JComboBox();
        KodePengunjung = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        Harga = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Total.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TotalCaretUpdate(evt);
            }
        });

        jLabel2.setText("KODE_PENJUALAN :");

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("TABEL PENJUALAN");

        jLabel6.setText("KODE_PEMBELI :");

        jLabel5.setText("KODE_OBAT");

        jLabel4.setText("KODE_SUPPLIER :");

        jLabel3.setText("DETAIL_PENJUALAN :");

        jLabel8.setText("TOTAL :");

        jLabel7.setText("JUMLAH :");

        AddNew.setText("Add New");
        AddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewActionPerformed(evt);
            }
        });

        Close.setText("CLOSE");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        Save.setText("SAVE");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Delete.setText("DELETE");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        no_faktur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pilih no_faktur" }));
        no_faktur.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                no_fakturItemStateChanged(evt);
            }
        });

        KodeKasir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih kode_supplier" }));
        KodeKasir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KodeKasirItemStateChanged(evt);
            }
        });

        KodeObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Kode Obat" }));
        KodeObat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KodeObatItemStateChanged(evt);
            }
        });

        KodePengunjung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Kode Pembeli" }));
        KodePengunjung.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KodePengunjungItemStateChanged(evt);
            }
        });

        jLabel9.setText("HARGA :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(AddNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Close)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(KodePembayaran, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(KodeKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(no_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(KodeObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Jumlah)
                            .addComponent(Total, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(KodePengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(KodePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(no_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(KodeKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(KodeObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(KodePengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save)
                    .addComponent(Delete)
                    .addComponent(Close)
                    .addComponent(AddNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
BersihData();
KodePembayaran.requestFocus();
Save.setEnabled(true);
Delete.setEnabled(false);
SetEditOn();   
    }//GEN-LAST:event_AddNewActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_CloseActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
String KP=KodePembayaran.getText();
String KT=no_faktur.getSelectedItem().toString();
String KK=KodeKasir.getSelectedItem().toString();
String KO=KodeObat.getSelectedItem().toString(); 
String H=Harga.getText();
String K=KodePengunjung.getSelectedItem().toString();
String J=Jumlah.getText();
String T=Total.getText();
if ((KP.isEmpty()) |(KT.isEmpty()) | (KK.isEmpty()) |(KO.isEmpty()) |(H.isEmpty())|(K.isEmpty())|(J.isEmpty())|(T.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodePembayaran.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement stt = kon.createStatement();
String SQL = "insert into bayar values("+
"'"+KodePembayaran.getText()+"',"+
"'"+no_faktur.getSelectedItem()+"',"+
"'"+KodeKasir.getSelectedItem()+"',"+
"'"+KodeObat.getSelectedItem()+"',"+
"'"+Harga.getText()+"',"+
"'"+KodePengunjung.getSelectedItem()+"',"+
"'"+Jumlah.getText()+"',"+
"'"+Total.getText()+"')";
stt.executeUpdate(SQL);
data[0] = KodePembayaran.getText();
data[1] = no_faktur.getSelectedItem().toString();
data[2] = KodeKasir.getSelectedItem().toString();
data[3] = KodeObat.getSelectedItem().toString();
data[4] = Harga.getText();
data[5] = KodePengunjung.getSelectedItem().toString();
data[6] = Jumlah.getText();
data[7] = Total.getText();
tableModel.insertRow(0, data);
stt.close();
kon.close();
BersihData();
Save.setEnabled(false);
SetEditOff();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
} 
    }//GEN-LAST:event_SaveActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
String KP=KodePembayaran.getText();
String KT=no_faktur.getSelectedItem().toString();
String KK=KodeKasir.getSelectedItem().toString();
String KO=KodeObat.getSelectedItem().toString(); 
String H=Harga.getText();
String K=KodePengunjung.getSelectedItem().toString();
String J=Jumlah.getText();
String T=Total.getText();
if ((KP.isEmpty()) |(KT.isEmpty()) | (KK.isEmpty()) |(KO.isEmpty()) |(H.isEmpty())|(K.isEmpty())|(J.isEmpty())|(T.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodePembayaran.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement stt = kon.createStatement();
String SQL = "Delete From bayar Where kode_pembayaran='"+KodePembayaran.getText().toString()+"'";
stt.executeUpdate(SQL);
data[0] = KodePembayaran.getText();
data[1] = no_faktur.getSelectedItem().toString();
data[2] = KodeKasir.getSelectedItem().toString();
data[3] = KodeObat.getSelectedItem().toString();
data[4] = Harga.getText();
data[5] = KodePengunjung.getSelectedItem().toString();
data[6] = Jumlah.getText();
data[7] = Total.getText();
tableModel.removeRow(row);
stt.close();
kon.close();
BersihData();
Save.setEnabled(false);
SetEditOff();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
} 
    }//GEN-LAST:event_DeleteActionPerformed

    private void no_fakturItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_no_fakturItemStateChanged
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM detail_penjualan where no_faktur='"+ 
no_faktur.getSelectedItem().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
} catch (SQLException ex) {
}
    }//GEN-LAST:event_no_fakturItemStateChanged

    private void KodeKasirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KodeKasirItemStateChanged
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM supplier where kode_supplier='"+ 
KodeKasir.getSelectedItem().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
} catch (SQLException ex) {
}
    }//GEN-LAST:event_KodeKasirItemStateChanged

    private void KodeObatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KodeObatItemStateChanged
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM obat where kode_obat='"+ 
KodeObat.getSelectedItem().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
Harga.setText(res.getString("harga"));
} catch (SQLException ex) {
}
    }//GEN-LAST:event_KodeObatItemStateChanged

    private void KodePengunjungItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KodePengunjungItemStateChanged
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/apotek","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM pembeli where kode_pembeli='"+ 
KodePengunjung.getSelectedItem().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
} catch (SQLException ex) {
}
    }//GEN-LAST:event_KodePengunjungItemStateChanged

    private void TotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TotalCaretUpdate
double a,b,f;
a=Double.parseDouble(Harga.getText());
b=Double.parseDouble(Jumlah.getText());
f=a*b;
Total.setText(String.valueOf(f));
    }//GEN-LAST:event_TotalCaretUpdate

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
if (evt.getClickCount()==1) {
Tampil();
} 
    }//GEN-LAST:event_TableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new penjualan().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNew;
    private javax.swing.JButton Close;
    private javax.swing.JButton Delete;
    private javax.swing.JTextField Harga;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JComboBox KodeKasir;
    private javax.swing.JComboBox KodeObat;
    private javax.swing.JTextField KodePembayaran;
    private javax.swing.JComboBox KodePengunjung;
    private javax.swing.JButton Save;
    private javax.swing.JTable Table;
    private javax.swing.JTextField Total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox no_faktur;
    // End of variables declaration//GEN-END:variables
}
