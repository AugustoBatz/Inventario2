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
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author sys515
 */
public class Ventas extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection Consulta = con.conexion();
    Connection Insertar = con.conexion();
    private int año=0;
    private int mes=0;
    private int dia=0;
    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }; //para la tabla
    private String nitglobal = null;

    /**
     * Creates new form Ventas
     */
    
    private void SYN()
    {
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select Serie,INCREMENTO from Resoluciones where INCREMENTO!=RangoF order by Fecha ASC Limit 1");
            while(Ca.next())
            {
                Numero.setText(Ca.getString(2));
                Serie.setText(Ca.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
    public Ventas() {
        initComponents();
        Producto.requestFocus();
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        SYN();
        Cantidad.setText("");
        

        AutoCompleteDecorator.decorate(Producto);
        AutoCompleteDecorator.decorate(Nit);

        modelo.setRowCount(0);
        modelo.addColumn("Cantidad");
        modelo.addColumn("Nombre");
        modelo.addColumn("Marca");
        modelo.addColumn("Precio Producto");
        modelo.addColumn("Precio Unitario");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Nombre = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelcliente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Nit = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        NombreM = new javax.swing.JLabel();
        ApellidoM = new javax.swing.JLabel();
        N = new javax.swing.JLabel();
        NY = new javax.swing.JLabel();
        addcli = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Factura = new javax.swing.JTable();
        panelfactura = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Serie = new javax.swing.JLabel();
        Numero = new javax.swing.JLabel();
        Fech = new com.toedter.calendar.JDateChooser();
        panelproducto = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Producto = new javax.swing.JComboBox<>();
        NombrePM = new javax.swing.JLabel();
        NombreP = new javax.swing.JLabel();
        MarcaM = new javax.swing.JLabel();
        Marca = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Existencia = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Cantidad = new javax.swing.JTextField();
        addfila = new javax.swing.JButton();
        elimfila = new javax.swing.JButton();
        breturn = new javax.swing.JButton();
        panelventab = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        TC1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TC = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(189, 189, 189));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 65, -1, -1));
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 57, 66, -1));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 78, 64, -1));

        panelcliente.setBackground(new java.awt.Color(189, 189, 189));

        jLabel2.setText("Cliente");

        Nit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NitActionPerformed(evt);
            }
        });

        jLabel6.setText("Nit");

        NombreM.setText("Nombre");

        ApellidoM.setText("Apellido");

        N.setText("jLabel14");

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

        javax.swing.GroupLayout panelclienteLayout = new javax.swing.GroupLayout(panelcliente);
        panelcliente.setLayout(panelclienteLayout);
        panelclienteLayout.setHorizontalGroup(
            panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelclienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(NombreM))
                .addGap(22, 22, 22)
                .addGroup(panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nit, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(panelclienteLayout.createSequentialGroup()
                        .addGroup(panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addcli, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelclienteLayout.createSequentialGroup()
                                .addComponent(N)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ApellidoM)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NY)
                            .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panelclienteLayout.setVerticalGroup(
            panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelclienteLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombreM)
                    .addComponent(N)
                    .addComponent(ApellidoM)
                    .addComponent(NY))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(addcli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        jPanel1.add(panelcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 350, 140));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 560, 270));

        panelfactura.setBackground(new java.awt.Color(189, 189, 189));

        jLabel3.setText("Fecha");

        jLabel4.setText("Factura");

        jLabel5.setText("Serie");

        jLabel1.setText("Numero");

        Serie.setText("jLabel15");

        Numero.setText("jLabel15");

        javax.swing.GroupLayout panelfacturaLayout = new javax.swing.GroupLayout(panelfactura);
        panelfactura.setLayout(panelfacturaLayout);
        panelfacturaLayout.setHorizontalGroup(
            panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelfacturaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addContainerGap(292, Short.MAX_VALUE))
            .addGroup(panelfacturaLayout.createSequentialGroup()
                .addGroup(panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelfacturaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(32, 32, 32)
                        .addComponent(Fech, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelfacturaLayout.createSequentialGroup()
                        .addGroup(panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addGap(41, 41, 41)
                        .addGroup(panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Numero)
                            .addComponent(Serie))))
                .addGap(0, 123, Short.MAX_VALUE))
        );
        panelfacturaLayout.setVerticalGroup(
            panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelfacturaLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGroup(panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelfacturaLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3))
                    .addGroup(panelfacturaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Fech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Serie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Numero))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel1.add(panelfactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        panelproducto.setBackground(new java.awt.Color(189, 189, 189));

        jLabel9.setText("Producto");

        jLabel10.setText("Codigo");

        Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductoActionPerformed(evt);
            }
        });

        NombrePM.setText("Nombre");

        NombreP.setText("jLabel11");

        MarcaM.setText("Marca");

        Marca.setText("jLabel11");

        jLabel12.setText("Existencia");

        Existencia.setText("jLabel13");

        jLabel11.setText("Cantidad");

        Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadKeyTyped(evt);
            }
        });

        addfila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/masa10.png"))); // NOI18N
        addfila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addfilaActionPerformed(evt);
            }
        });

        elimfila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menosa8.png"))); // NOI18N

        javax.swing.GroupLayout panelproductoLayout = new javax.swing.GroupLayout(panelproducto);
        panelproducto.setLayout(panelproductoLayout);
        panelproductoLayout.setHorizontalGroup(
            panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelproductoLayout.createSequentialGroup()
                .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelproductoLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel9))
                    .addGroup(panelproductoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(NombrePM)
                            .addComponent(MarcaM, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(37, 37, 37)
                        .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Existencia)
                            .addComponent(Marca)
                            .addComponent(NombreP)
                            .addComponent(Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelproductoLayout.createSequentialGroup()
                                .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addfila, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(elimfila, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        panelproductoLayout.setVerticalGroup(
            panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelproductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombrePM)
                    .addComponent(NombreP))
                .addGap(18, 18, 18)
                .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MarcaM)
                    .addComponent(Marca))
                .addGap(18, 18, 18)
                .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(Existencia))
                .addGap(36, 36, 36)
                .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelproductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addfila, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elimfila, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(panelproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 340, 260));

        breturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresara7.png"))); // NOI18N
        breturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breturnActionPerformed(evt);
            }
        });
        jPanel1.add(breturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 360, 40, -1));

        panelventab.setBackground(new java.awt.Color(189, 189, 189));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cajara23.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelventabLayout = new javax.swing.GroupLayout(panelventab);
        panelventab.setLayout(panelventabLayout);
        panelventabLayout.setHorizontalGroup(
            panelventabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelventabLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
        );
        panelventabLayout.setVerticalGroup(
            panelventabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelventabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel1.add(panelventab, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, -1, -1));

        jLabel14.setText("Total");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 440, -1, -1));

        TC1.setText("0.0");
        jPanel1.add(TC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 440, 70, -1));

        jLabel13.setText("Total");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, -1, -1));

        TC.setText("0");
        jPanel1.add(TC, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, 60, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
    private void ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductoActionPerformed
        String Completo = (String) Producto.getSelectedItem();
        llenarPM(Completo);
// TODO add your handling code here:
    }//GEN-LAST:event_ProductoActionPerformed
    private int RetornoIdLoteNuevo() {
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT NoLote FROM Lote WHERE Producto_id ='" + id((String) Producto.getSelectedItem()) + "'&& Disponible=true &&NoLote= (SELECT MAX(NoLote) FROM Lote WHERE Producto_id ='" + id((String) Producto.getSelectedItem()) + "')");

            while (Ca.next()) {

                return Integer.parseInt(Ca.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private String Facturacion(int x, String Codigo) {
        BigDecimal cantidad = BigDecimal.valueOf(0.0);
        cantidad = BigDecimal.valueOf(Double.parseDouble(Cantidad.getText()));
        BigDecimal PrecioTotal = BigDecimal.valueOf(0.0);
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Cantidad,PrecioUnitario FROM Lote  where Disponible=true && Producto_id='" + Codigo + "' ORDER BY Fecha ASC Limit " + x + "");
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

    private int getidPro(String Nom, String Marca) {
        int id3 = 0;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM Producto WHERE Nombre='" + Nom + "'&& Marca='" + Marca + "'");
            while (Ca.next()) {
                id3 = Integer.parseInt(Ca.getString(1));
            }
            return id3;

        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void crearLotesNuevos(String idF, String ve[], String idP) {

        double cantidad = Double.parseDouble(ve[0]);
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT NoLote,Cantidad,PrecioUnitario,PrecioTotal,Descripcion FROM Lote  where Disponible=true&& Producto_id='"
                    + idP + "' ORDER BY Fecha ASC Limit "
                    + CuantosLotes2(idP,ve[0]) + "");
            while (Ca.next()) {
                if (CuantosLotes2(idP,ve[0]) == 1) {
                    double aux = cantidad - Double.parseDouble(Ca.getString(2));
                    cantidad = cantidad - aux;
                    PreparedStatement CrearLot = Insertar.prepareStatement("INSERT INTO LoteVenta(NoLote,Cantidad,PrecioUnitario,PrecioTotal,Descripcion,Producto_id,FacturaVenta_id,Fecha) "
                            + "VALUES(?,?,?,?,?,?,?,?)");
                    CrearLot.setString(1, Ca.getString(1));
                    CrearLot.setString(2, ve[0]);
                    CrearLot.setString(3, Ca.getString(3));
                    double precioTo = 0;
                    precioTo = (Double.parseDouble(ve[0])) * (Double.parseDouble(Ca.getString(3)));
                    CrearLot.setString(4, String.valueOf(precioTo));
                    CrearLot.setString(5, Ca.getString(5));
                    CrearLot.setString(6, idP);
                    CrearLot.setString(7, idF);
                    CrearLot.setString(8, año+"-"+mes+"-"+dia);
                    CrearLot.executeUpdate();
                    CrearLot.close();
                    if (cantidad == 0) {
                        PreparedStatement Actualizar = Insertar.prepareStatement("UPDATE Lote set Cantidad=0, Disponible=false where Disponible=true && Producto_id=" + idP + ""
                                + "&& NoLote=" + Ca.getString(1) + "");
                        Actualizar.executeUpdate();
                        Actualizar.close();
                    } else {

                        PreparedStatement Actualizar = Insertar.prepareStatement("UPDATE Lote set Cantidad=Cantidad-'" + ve[0] + "'where Disponible=true && Producto_id=" + idP + ""
                                + "&& NoLote=" + Ca.getString(1) + "");
                        Actualizar.executeUpdate();
                        Actualizar.close();
                    }

                } else {
                    if (cantidad < Double.parseDouble(Ca.getString(2))) {
                        PreparedStatement CrearLot = Insertar.prepareStatement("INSERT INTO LoteVenta(NoLote,Cantidad,PrecioUnitario,PrecioTotal,Descripcion,Producto_id,FacturaVenta_id,Fecha) "
                                + "VALUES(?,?,?,?,?,?,?,?)");
                        CrearLot.setString(1, Ca.getString(1));
                        CrearLot.setString(2, String.valueOf(cantidad));
                        CrearLot.setString(3, Ca.getString(3));
                        double precioTo = 0;
                        precioTo = cantidad * (Double.parseDouble(Ca.getString(3)));
                        CrearLot.setString(4, String.valueOf(precioTo));
                        CrearLot.setString(5, Ca.getString(5));
                        CrearLot.setString(6, idP);
                        CrearLot.setString(7, idF);
                        CrearLot.setString(8, año+"-"+mes+"-"+dia);
                        CrearLot.executeUpdate();
                        CrearLot.close();
                        PreparedStatement Actualizar = Insertar.prepareStatement("UPDATE Lote set Cantidad=Cantidad-'" + String.valueOf(cantidad) + "'where Disponible=true && Producto_id=" + idP + ""
                                + "&& NoLote=" + Ca.getString(1) + "");
                        Actualizar.executeUpdate();
                        Actualizar.close();

                    }
                    if (cantidad == Double.parseDouble(Ca.getString(2))) {
                        PreparedStatement CrearLot = Insertar.prepareStatement("INSERT INTO LoteVenta(NoLote,Cantidad,PrecioUnitario,PrecioTotal,Descripcion,Producto_id,FacturaVenta_id,Fecha) "
                                + "VALUES(?,?,?,?,?,?,?,?)");
                        CrearLot.setString(1, Ca.getString(1));
                        CrearLot.setString(2, String.valueOf(cantidad));
                        CrearLot.setString(3, Ca.getString(3));
                        double precioTo = 0;
                        precioTo = cantidad * (Double.parseDouble(Ca.getString(3)));
                        CrearLot.setString(4, String.valueOf(precioTo));
                        CrearLot.setString(5, Ca.getString(5));
                        CrearLot.setString(6, idP);
                        CrearLot.setString(7, idF);
                        CrearLot.setString(8, año+"-"+mes+"-"+dia);
                        CrearLot.executeUpdate();
                        CrearLot.close();
                        PreparedStatement Actualizar = Insertar.prepareStatement("UPDATE Lote set Cantidad=0, Disponible=false where Disponible=true && Producto_id=" + idP + ""
                                + "&& NoLote=" + Ca.getString(1) + "");
                        Actualizar.executeUpdate();
                        Actualizar.close();
                    }
                    if (cantidad > Double.parseDouble(Ca.getString(2))) {

                        cantidad = cantidad - Double.parseDouble(Ca.getString(2));

                        PreparedStatement CrearLot = Insertar.prepareStatement("INSERT INTO LoteVenta(NoLote,Cantidad,PrecioUnitario,PrecioTotal,Descripcion,Producto_id,FacturaVenta_id,Fecha) "
                                + "VALUES(?,?,?,?,?,?,?,?)");
                        CrearLot.setString(1, Ca.getString(1));
                        CrearLot.setString(2, Ca.getString(2));
                        CrearLot.setString(3, Ca.getString(3));
                        CrearLot.setString(4, Ca.getString(4));
                        CrearLot.setString(5, Ca.getString(5));
                        CrearLot.setString(6, idP);
                        CrearLot.setString(7, idF);
                        CrearLot.setString(8, año+"-"+mes+"-"+dia);
                        CrearLot.executeUpdate();
                        CrearLot.close();
                        PreparedStatement Actualizar = Insertar.prepareStatement("UPDATE Lote set Cantidad=0,Disponible=false where Disponible=true && Producto_id=" + idP + ""
                                + "&& NoLote=" + Ca.getString(1) + "");

                        Actualizar.executeUpdate();
                        Actualizar.close();

                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    private String crearFactura(String Serie, String Numero) {
        int idUsuario = 0;
        try {
            PreparedStatement CrearLot = Insertar.prepareStatement("INSERT INTO FacturaVenta(Total,Serie,Numero,Cliente_id,Resoluciones_id,Fecha,Cantidad_Prod,Anulado"
                    + ") VALUES(?,?,?,?,?,?,?,0)", Statement.RETURN_GENERATED_KEYS);
            CrearLot.setString(1, TC1.getText());
            CrearLot.setString(2, Serie);
            CrearLot.setString(3, Numero);
            CrearLot.setString(4, String.valueOf(getidProve(nitglobal)));
            CrearLot.setString(5, idRes());   
            CrearLot.setString(6, año+"-"+mes+"-"+dia);
            CrearLot.setString(7,TC.getText());
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
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
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
                BigDecimal To = BigDecimal.valueOf(Double.parseDouble(TC1.getText())).add(BigDecimal.valueOf(Double.parseDouble(Facturacion(CuantosLotes(id(Completo)), id(Completo))))).setScale(2, BigDecimal.ROUND_DOWN);
                TC1.setText(String.valueOf(To));
            } else {
                JOptionPane.showMessageDialog(null, "No se puede");

            }
            int aux=0;
            aux=Integer.valueOf(TC.getText());
            aux=aux+Integer.valueOf(Cantidad.getText());
            TC.setText(String.valueOf(aux));
        }
        }
      Cantidad.setText("");
// TODO add your handling code here:
    }//GEN-LAST:event_addfilaActionPerformed
    private String idRes()
    {   
        String id=null;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select id from Resoluciones where INCREMENTO!=RangoF ORDER BY Fecha ASC Limit 1");
            while(Ca.next())
            {
                id=Ca.getString(1);
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
        {
        año = Fech.getCalendar().get(Calendar.YEAR);
         mes = Fech.getCalendar().get(Calendar.MONTH) + 1;
         dia = Fech.getCalendar().get(Calendar.DAY_OF_MONTH);
    }                                        

    
    catch(NullPointerException ex) {
    }
     if(año==0&&dia==0&&mes==00)   
    {
        JOptionPane.showMessageDialog(this, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
    }
     else{
        if (Serie.getText().equals("") || Numero.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo(s) Vacio(s)");

        } else {
            String x[] = new String[5];
            String idFac = crearFactura(Serie.getText(), Numero.getText());
            for (int i = 0; i < Factura.getRowCount(); i++) {
                for (int j = 0; j < Factura.getColumnCount(); j++) {
                    x[j] = Factura.getValueAt(i, j).toString().trim();

                }
                crearLotesNuevos(idFac, x, id2(x[1], x[2]));

                Menu xx = new Menu();

                dispose();
                xx.setVisible(true);

                
            }
            
            JOptionPane.showMessageDialog(null, "Factura Creada");

        }
     }

    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void NitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NitActionPerformed
        nitglobal = (String) Nit.getSelectedItem();
        String Completo = (String) Nit.getSelectedItem();
        llenarCl(Completo);

    }//GEN-LAST:event_NitActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Menu y = new Menu();
        y.setVisible(true);
        dispose();  // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

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

    private void breturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breturnActionPerformed
        // TODO add your handling code here:
        Menu men = new Menu();
        men.setVisible(true);
        dispose();


    }//GEN-LAST:event_breturnActionPerformed

    private void FacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FacturaKeyTyped
    }//GEN-LAST:event_FacturaKeyTyped

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
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas().setVisible(true);
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
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel NombreM;
    private javax.swing.JLabel NombreP;
    private javax.swing.JLabel NombrePM;
    private javax.swing.JLabel Numero;
    private javax.swing.JComboBox<String> Producto;
    private javax.swing.JLabel Serie;
    private javax.swing.JLabel TC;
    private javax.swing.JLabel TC1;
    private javax.swing.JButton actualizar;
    private javax.swing.JButton addcli;
    private javax.swing.JButton addfila;
    private javax.swing.JButton breturn;
    private javax.swing.JButton elimfila;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelcliente;
    private javax.swing.JPanel panelfactura;
    private javax.swing.JPanel panelproducto;
    private javax.swing.JPanel panelventab;
    // End of variables declaration//GEN-END:variables
}
