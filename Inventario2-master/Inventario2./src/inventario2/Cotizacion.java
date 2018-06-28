/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.Debug.id;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author sys515
 */
public class Cotizacion extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    Connection Consulta = con.conexion();
    Connection Insertar = con.conexion();
    String nitglobal=null;
    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }; 
    private int año=0,mes=0,dia=0;
//para la tabla
    /**
     * Creates new form Cotizacion
     */
    public Cotizacion() {
        initComponents();
        Cantidad.setText("");
        

        AutoCompleteDecorator.decorate(Producto);
        AutoCompleteDecorator.decorate(Nit);

        modelo.setRowCount(0);
        modelo.addColumn("Cantidad");//0
        modelo.addColumn("Nombre");//1
        modelo.addColumn("Marca");//2
        modelo.addColumn("Precio Producto");//3
        modelo.addColumn("Precio Unitario");//4
        Factura.setModel(modelo);

        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Codigo FROM Producto");
            while (Ca.next()) {

                Producto.addItem(Ca.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Nit FROM Cliente");
            while (Ca.next()) {

                Nit.addItem(Ca.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        Factura.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {
                    int e=ext(String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 1)),
                            String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 2)));
                    String mensaje=mensaje(String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 0)));
                    
                    if(Integer.parseInt(mensaje)>e)
                    {
                         JOptionPane.showMessageDialog(null, "Excede La existencia");

                    }
                    else
                    {
                        Double x=(Double.parseDouble(String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 4))))*Double.parseDouble(mensaje);
                        Factura.setValueAt(mensaje, row, 0);
                        Factura.setValueAt(String.valueOf(x), row, 3);
                    }
                 
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Factura = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Producto = new javax.swing.JComboBox<>();
        NombrePM = new javax.swing.JLabel();
        MarcaM = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        NombreP = new javax.swing.JLabel();
        Marca = new javax.swing.JLabel();
        Existencia = new javax.swing.JLabel();
        addfila = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NombreM = new javax.swing.JLabel();
        N = new javax.swing.JLabel();
        ApellidoM = new javax.swing.JLabel();
        NY = new javax.swing.JLabel();
        addcli = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        Nit = new javax.swing.JComboBox<>();
        Totales = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Cantidad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Fech = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Factura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cantidad", "Nombre", "Marca", "Precio Producto", "Precio Unitario"
            }
        ));
        Factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FacturaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(Factura);

        jLabel10.setText("Codigo");

        jLabel9.setText("Producto");

        Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductoActionPerformed(evt);
            }
        });

        NombrePM.setText("Nombre");

        MarcaM.setText("Marca");

        jLabel12.setText("Existencia");

        jLabel11.setText("Cantidad");

        NombreP.setText("jLabel11");

        Marca.setText("jLabel11");

        Existencia.setText("jLabel13");

        addfila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/masa10.png"))); // NOI18N
        addfila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addfilaActionPerformed(evt);
            }
        });

        jLabel2.setText("Cliente");

        jLabel6.setText("Nit");

        NombreM.setText("Nombre");

        N.setText("jLabel14");

        ApellidoM.setText("Apellido");

        NY.setText("jLabel15");

        addcli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clientead11.png"))); // NOI18N
        addcli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcliActionPerformed(evt);
            }
        });

        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizara17.png"))); // NOI18N
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        Nit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NitActionPerformed(evt);
            }
        });

        Totales.setText("0000");

        jLabel14.setText("Total");

        Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadKeyTyped(evt);
            }
        });

        jButton1.setText("Generar Cotizacion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(NombrePM)
                            .addComponent(MarcaM, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Existencia)
                            .addComponent(Marca)
                            .addComponent(NombreP)
                            .addComponent(Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addfila, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(341, 341, 341)
                        .addComponent(jLabel14)
                        .addGap(12, 12, 12)
                        .addComponent(Totales)))
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(Fech, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(NombreM))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(Nit, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addcli, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(N)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ApellidoM)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NY)
                            .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(241, 241, 241))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NombreM)
                            .addComponent(N)
                            .addComponent(ApellidoM)
                            .addComponent(NY)))
                    .addComponent(Fech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(addcli))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NombrePM)
                            .addComponent(NombreP))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MarcaM)
                            .addComponent(Marca))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(Existencia))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addfila, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jButton1))
                    .addComponent(Totales))
                .addGap(271, 271, 271))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FacturaKeyTyped
        
    }//GEN-LAST:event_FacturaKeyTyped
     private String mensaje(String x)
    {
        int numero=0;
        try
        {
        
            numero=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("introduce numero"));
            return String.valueOf(numero);
        }
        catch(NumberFormatException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Solo se permiten numeros");
 
        }
        return x;
    }
    private int ext(String N,String M)
    {
         try {
            int y=0;
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Existencia FROM Producto Where Nombre='" +N + "' && Marca='"+M+"'");
            while (Ca.next()) {
                y=Integer.parseInt(Ca.getString(1));
                
            }
            return y;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    private void ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductoActionPerformed
        String Completo = (String) Producto.getSelectedItem();
        llenarPM(Completo);
     
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductoActionPerformed

    private void llenarPM(String Codigo) {

        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Nombre,Marca,Existencia FROM Producto Where Codigo='" + Codigo + "'");
            while (Ca.next()) {

                NombreP.setText(Ca.getString(1));
                Marca.setText(Ca.getString(2));
                Existencia.setText(Ca.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private Boolean CompararEntrada(String Nombre,String Marca)
    {
        
        String x[] = new String[2];
        if(Factura.getRowCount()!=0)
        {
        for (int i = 0; i < Factura.getRowCount(); i++)
        {
            
                x[0] = Factura.getValueAt(i, 1).toString().trim();
                x[1] = Factura.getValueAt(i, 2).toString().trim();
                if(x[0].equals(Nombre)&& x[1].equals(Marca))
                {
                    return false;
                }
               
                
           
        }
        }
        else{
            return true;

        }
        return true;
        
    }
     private int CuantosLotes(String Codigo) {
        int cantidad = 0;
        int NoLotes = 0;
        cantidad = Integer.parseInt(Cantidad.getText());

        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Cantidad FROM Lote  where Producto_id='" + Codigo + "' ORDER BY Fecha ASC");
            while (Ca.next()) {
                if (cantidad > 0) {
                    cantidad = cantidad - Integer.parseInt(Ca.getString(1));
                    NoLotes++;

                }
            }
            return NoLotes;
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
     private String Facturacion(int x, String Codigo) {
        BigDecimal cantidad = BigDecimal.valueOf(0.0);
        cantidad = BigDecimal.valueOf(Double.parseDouble(Cantidad.getText()));
        BigDecimal PrecioTotal = BigDecimal.valueOf(0.0);
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Cantidad,PrecioUnitario FROM Lote  where Producto_id='" + Codigo + "' ORDER BY Fecha ASC Limit " + x + "");
            while (Ca.next()) {
                if (x == 1) {
                    PrecioTotal = PrecioTotal.add(BigDecimal.valueOf(Double.parseDouble(Cantidad.getText())).multiply(BigDecimal.valueOf( Double.parseDouble(Ca.getString(2))))).setScale(2, BigDecimal.ROUND_DOWN);
                } else {
                    if (Double.parseDouble(String.valueOf(cantidad)) < Double.parseDouble(Ca.getString(1))) {

                        PrecioTotal = PrecioTotal.add(cantidad.multiply(BigDecimal.valueOf(Double.parseDouble(Ca.getString(2))))).setScale(2, BigDecimal.ROUND_DOWN);                                ;

                    }
                    if (Double.parseDouble(String.valueOf(cantidad)) == Double.parseDouble(Ca.getString(1))) {

                        PrecioTotal = PrecioTotal.add(BigDecimal.valueOf(Double.parseDouble(Ca.getString(1))).multiply(BigDecimal.valueOf(Double.parseDouble(Ca.getString(2))))).setScale(2, BigDecimal.ROUND_DOWN); 
                                
                    }
                    if (Double.parseDouble(String.valueOf(cantidad)) > Double.parseDouble(Ca.getString(1))) {

                        cantidad = cantidad.subtract(BigDecimal.valueOf(Double.parseDouble(Ca.getString(1)))).setScale(2, BigDecimal.ROUND_DOWN);
                        PrecioTotal = PrecioTotal.add((BigDecimal.valueOf(Double.parseDouble(Ca.getString(1))).multiply(BigDecimal.valueOf(Double.parseDouble(Ca.getString(2)))))).setScale(2, BigDecimal.ROUND_DOWN);;
                                

                    }

                }
            }
            return String.valueOf(PrecioTotal);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Precio Total " + PrecioTotal);
        return null;

    }
     private String id(String Codigo) {
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM Producto Where Codigo='" + Codigo + "'");
            while (Ca.next()) {

                return Ca.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private void addfilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addfilaActionPerformed

        if (Cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la Cantidad que desea comprar de: " + NombreP.getText());

        } else {
            if(CompararEntrada(NombreP.getText(),Marca.getText())==false){
                JOptionPane.showMessageDialog(null, "Ya tiene este Producto Registrdo en la Factura");

            }
            else{
                if (Integer.parseInt(Cantidad.getText()) <= Integer.parseInt(Existencia.getText())) {
                    String Completo = (String) Producto.getSelectedItem();
                    String Fact=Facturacion(CuantosLotes(id(Completo)), id(Completo));
                Double r=Double.parseDouble(Fact);
                Double PrecioUnita=r/(Double.parseDouble(Cantidad.getText()));
                BigDecimal PrecioUnitar=BigDecimal.valueOf(PrecioUnita).setScale(2, BigDecimal.ROUND_UP);
                
                    modelo.addRow(new Object[]{Cantidad.getText(), NombreP.getText(), Marca.getText(),
                        Facturacion(CuantosLotes(id(Completo)), id(Completo)), PrecioUnitar});
                BigDecimal To = BigDecimal.valueOf(Double.parseDouble(Totales.getText())).add(BigDecimal.valueOf(Double.parseDouble(Facturacion(CuantosLotes(id(Completo)), id(Completo))))).setScale(2, BigDecimal.ROUND_DOWN);
                Totales.setText(String.valueOf(To));
            } else {
                JOptionPane.showMessageDialog(null, "No se puede");

            }
        }
        }
        Cantidad.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_addfilaActionPerformed

    private void addcliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcliActionPerformed
        // TODO add your handling code here:
        Clientes cl = new Clientes();
        cl.setVisible(true);
    }//GEN-LAST:event_addcliActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        // TODO add your handling code here:

        Nit.removeAllItems();
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Nit,id FROM Cliente");
            while (Ca.next()) {

                Nit.addItem(Ca.getString(1));
            }
            Ca.close();
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void NitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NitActionPerformed
        nitglobal = (String) Nit.getSelectedItem();
        String Completo = (String) Nit.getSelectedItem();
        llenarCl(Completo);
    }//GEN-LAST:event_NitActionPerformed

      private void llenarCl(String Codigo) {

        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Nombre,Apellido FROM Cliente Where Nit='" + Codigo + "'");
            while (Ca.next()) {

                N.setText(Ca.getString(1));
                NY.setText(Ca.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 241 || k == 209) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k >= 33 && k <= 47) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar Simbolos!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 10) {
            Cantidad.transferFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try
        {
        año = Fech.getCalendar().get(Calendar.YEAR);
         mes = Fech.getCalendar().get(Calendar.MONTH) + 1;
         dia = Fech.getCalendar().get(Calendar.DAY_OF_MONTH);
    }                                        

    catch(NullPointerException ex) 
    {
    }
     if(año==0&&dia==0&&mes==00)   
    {
        JOptionPane.showMessageDialog(this, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
    }
    else
    {
        String idC=GC("1");
        String x[]=new String[5];
        for (int i = 0; i < Factura.getRowCount(); i++) {
                for (int j = 0; j < Factura.getColumnCount(); j++) {
                    x[j] = Factura.getValueAt(i, j).toString().trim();

                }
                CrearLoteC(idC, x, id2(x[1], x[2]));

                

                
          }
    }
    //imprimir();
    // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    private void imprimir()
    {
                try {
            Connection tr = con.conexion();
            JasperReport reporte= null;
            String path="/home/sys515/Documentos/ProyecoPEPS/Inventario2-master/Inventario2./src/Reportes/ventas.jasper";
            reporte= (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint =JasperFillManager.fillReport(reporte,null,tr);
            JasperViewer view = new JasperViewer(jprint,false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

            // TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    private int CuantosLotes2(String id,String n) {
        int cantidad = 0;
        int NoLotes = 0;
        cantidad = Integer.parseInt(n);

        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Cantidad FROM Lote  where Producto_id='" + id + "' ORDER BY Fecha ASC");
            while (Ca.next()) {
                if (cantidad > 0) {
                    cantidad = cantidad - Integer.parseInt(Ca.getString(1));
                    NoLotes++;

                }
            }
            return NoLotes;
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }   
    private void CrearLoteC(String idF, String ve[], String idP) {

        try {
                    PreparedStatement CrearLot = Insertar.prepareStatement("INSERT INTO Lote_Coti(Cantidad,PrecioUnitario,PrecioTotal,Descripcion,Producto_id,Cotizacion_id,Fecha) "
                            + "VALUES(?,?,?,?,?,?,?)");
                    
                    
                    CrearLot.setString(1, ve[0]);
                    CrearLot.setString(2, ve[4]);
                    CrearLot.setString(3, ve[3]);
                    CrearLot.setString(4, "");
                    CrearLot.setString(5, idP);
                    CrearLot.setString(6, idF);
                    CrearLot.setString(7, año+"-"+mes+"-"+dia);
                    CrearLot.executeUpdate();
                    CrearLot.close();
                   

        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String id2(String Nombre, String Marca) {
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM Producto Where Nombre='" + Nombre + "'&& Marca='" + Marca + "'");
            while (Ca.next()) {

                return Ca.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
    private int getidProve(String nit) {
        int nit2 = 0;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM Cliente WHERE Nit='" + nit + "'");
            while (Ca.next()) {
                nit2 = Integer.parseInt(Ca.getString(1));

            }
            return nit2;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    private String GC(String Numero)
    {
                int idUsuario = 0;

        try {
                PreparedStatement CrearLot = Consulta.prepareStatement("INSERT INTO Cotizacion(Numero,Fecha,Cliente_id"
                    + ") VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
                CrearLot.setString(1, Numero);
                CrearLot.setString(2, año+"-"+mes+"-"+dia);
                CrearLot.setString(3, String.valueOf(getidProve(nitglobal)));
                CrearLot.executeUpdate();
                try (ResultSet rs = CrearLot.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new RuntimeException("no devolvió el ID");
                }

                idUsuario = rs.getInt(1);
                CrearLot.close();

            }
                            return String.valueOf(idUsuario);

        } catch (SQLException ex) {
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cotizacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ApellidoM;
    private javax.swing.JTextField Cantidad;
    private javax.swing.JLabel Existencia;
    private javax.swing.JTable Factura;
    private com.toedter.calendar.JDateChooser Fech;
    private javax.swing.JLabel Marca;
    private javax.swing.JLabel MarcaM;
    private javax.swing.JLabel N;
    private javax.swing.JLabel NY;
    private javax.swing.JComboBox<String> Nit;
    private javax.swing.JLabel NombreM;
    private javax.swing.JLabel NombreP;
    private javax.swing.JLabel NombrePM;
    private javax.swing.JComboBox<String> Producto;
    private javax.swing.JLabel Totales;
    private javax.swing.JButton actualizar;
    private javax.swing.JButton addcli;
    private javax.swing.JButton addfila;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
