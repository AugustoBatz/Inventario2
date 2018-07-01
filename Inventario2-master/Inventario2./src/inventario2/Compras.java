package inventario2;

//import com.mxrck.autocompleter.TextAutoCompleter;
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
public class Compras extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = con.conexion();
    Connection Consulta = con.conexion();
    Connection Consulta2 = con.conexion();
    Connection tr = con.conexion();
    private String nitglobal = null;
    private int tp=0;
    private int n2=0;
    private int año=0;
    private int mes=0;
    private int dia=0;
    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    
    /**
     * Creates new form Compras
     */
    public Compras() {
        initComponents();

        this.setSize(1265, 740);
        AutoCompleteDecorator.decorate(Otro);
        AutoCompleteDecorator.decorate(Otro1);
        this.setResizable(false);
        modelo.setRowCount(0);
        modelo.addColumn("Codigo"); //0
        this.setTitle("Compras - Sistema Inventario BTZ");

        modelo.addColumn("Cantidad"); //1
        modelo.addColumn("Medida"); //2
        modelo.addColumn("Nombre");//3
        modelo.addColumn("Marca");//4
        modelo.addColumn("Costo Unitario");//5
        modelo.addColumn("Costo Total");//6
        modelo.addColumn("Descripcion");//7
        modelo.addColumn("Ganancia");//8
        modelo.addColumn("Precio Unitario");//9
        modelo.addColumn("Precio Total");//10
        Factura.setModel(modelo);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        //Costo.setText("");

        Cantidad.setText("");
        Serie.setText("");
        Numero.setText("");
        Ganancia.setText("");
        Serie.requestFocus();
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Nombre,Existencia,Marca,Codigo FROM Producto");
            while (Ca.next()) {

                Otro.addItem(Ca.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Statement sx2 = Consulta2.createStatement();
            ResultSet Ca2 = sx2.executeQuery("SELECT Nit FROM Proveedor");
            while (Ca2.next()) {
                Otro1.addItem(Ca2.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        Costo.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
             Costo.setText(null);
             tp=0;
             n2=0;
            }
        });
        
        Factura.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {
                    String mensaje=mensaje(String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 1)));                   
                    String a=String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 3));
                    String b=String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 4));
                    System.out.println("seleciones la fila "+a+" y "+b);
              
                    Factura.setValueAt(mensaje, row, 1);

                    
                 
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
  
    private int getidPro(String Nom, String Marca,String uni) {
        int id3 = 0;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM Producto WHERE Nombre='" + Nom + "'&& Marca='" + Marca + "' && Medida='"+uni+"'");
            while (Ca.next()) {
                id3 = Integer.parseInt(Ca.getString(1));
            }
            return id3;

        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    private BigDecimal x(Double r)
    {
        return BigDecimal.valueOf(r).setScale(2, BigDecimal.ROUND_DOWN);
    }
    private int getidProve(String nit) {
        int nit2 = 0;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM Proveedor WHERE Nit='" + nit + "'");
            while (Ca.next()) {
                nit2 = Integer.parseInt(Ca.getString(1));

            }
            return nit2;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The1 content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        rSEstiloTablaHeader1 = new rojeru_san.complementos.RSEstiloTablaHeader();
        jPanel1 = new javax.swing.JPanel();
        jPaneldedatos = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Serie = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Numero = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Nombre2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Apellido = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Otro1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        Fech = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        panel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Otro = new javax.swing.JComboBox<>();
        Nombre = new javax.swing.JLabel();
        Marca = new javax.swing.JLabel();
        Cantidad = new javax.swing.JTextField();
        addfila = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Descripcion = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Costo = new javax.swing.JTextField();
        Ganancia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Unidad = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Totales = new javax.swing.JLabel();
        TotalCantidad = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Factura = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1000, 1000));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(189, 189, 189));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPaneldedatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPaneldedatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Información de Facturación");
        jPaneldedatos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel8.setText("Serie");
        jPaneldedatos.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));
        jPaneldedatos.add(Serie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 73, -1));

        jLabel14.setText("Numero");
        jPaneldedatos.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        Numero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NumeroFocusLost(evt);
            }
        });
        Numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NumeroKeyTyped(evt);
            }
        });
        jPaneldedatos.add(Numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 67, -1));

        jLabel10.setText("Nombre");
        jPaneldedatos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 159, -1, -1));

        Nombre2.setText("jLabel9");
        jPaneldedatos.add(Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 113, -1));

        jLabel11.setText("Representante");
        jPaneldedatos.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 182, -1, -1));

        Apellido.setText("jLabel14");
        jPaneldedatos.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 113, -1));

        jLabel13.setText("Nit Proveedor");
        jPaneldedatos.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 211, -1, -1));

        Otro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Otro1ActionPerformed(evt);
            }
        });
        jPaneldedatos.add(Otro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 234, 143, -1));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("Agregar proveedor");
        jPaneldedatos.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-agregar-administrador-80.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-agregar-administrador-filled-50.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPaneldedatos.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 80, 80));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-actualizar-50.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-actualizar-filled-50.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPaneldedatos.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 50, 50));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Actualizar");
        jPaneldedatos.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, -1, -1));

        jLabel22.setText("Proveedor");
        jPaneldedatos.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 130, -1, -1));

        Fecha.setText("Fecha");
        jPaneldedatos.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));
        jPaneldedatos.add(Fech, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 70, 160, 40));

        jPanel1.add(jPaneldedatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 520, 290));

        jPanel2.setBackground(new java.awt.Color(189, 189, 189));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3499, 37, -1, -1));

        panel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Producto");
        panel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 20, 84, -1));

        Otro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtroActionPerformed(evt);
            }
        });
        panel3.add(Otro, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 12, 301, -1));

        Nombre.setText("Nombre");
        panel3.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 118, -1));

        Marca.setText("Marca");
        panel3.add(Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 107, -1));

        Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadActionPerformed(evt);
            }
        });
        Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadKeyTyped(evt);
            }
        });
        panel3.add(Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 81, -1));

        addfila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/masa10.png"))); // NOI18N
        addfila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addfilaActionPerformed(evt);
            }
        });
        panel3.add(addfila, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 272, 30, 30));

        jLabel2.setText("Cantidad");
        panel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 136, -1, -1));

        jLabel6.setText("CostoUnitario");
        panel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 174, -1, -1));

        jLabel5.setText("Descripcion");
        panel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 207, -1, -1));

        jLabel19.setText("Porcentaje de ganancia");
        panel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        Descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescripcionActionPerformed(evt);
            }
        });
        panel3.add(Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 242, 346, 60));

        jLabel20.setText("%");
        panel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 30, 30));

        jLabel23.setText("Producto:");
        panel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel24.setText("Marca");
        panel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 89, -1, -1));

        Costo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CostoFocusLost(evt);
            }
        });
        Costo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CostoKeyTyped(evt);
            }
        });
        panel3.add(Costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 81, 29));

        Ganancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GananciaKeyTyped(evt);
            }
        });
        panel3.add(Ganancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 51, -1));

        jLabel4.setText("Unidad");
        panel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, -1));

        Unidad.setText("Unidad");
        panel3.add(Unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, -1, -1));

        jPanel1.add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 570, 320));

        jLabel9.setText("Cantidad Total");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, -1, -1));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setText("Regresar");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 590, -1, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setText("Comprar");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 450, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-volver-asignación-50.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-volver-asignación-filled-50.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 530, 64, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-comprar-50.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-comprar-filled-50.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 380, 80, 68));

        Totales.setText("0.0");
        jPanel1.add(Totales, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 640, 92, -1));

        TotalCantidad.setText("0");
        jPanel1.add(TotalCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 640, 89, -1));

        jLabel3.setText("Total");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 640, -1, -1));

        Factura.setModel(new javax.swing.table.DefaultTableModel(
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
        Factura.setColorSelBackgound(new java.awt.Color(10, 221, 254));
        Factura.setFuenteHead(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jScrollPane3.setViewportView(Factura);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 1130, 280));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1130, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int lotereciente(int idProd) {

        int Loteant = 0;
        try {

            Statement xq = Consulta2.createStatement();
            try (ResultSet red = xq.executeQuery("SELECT NoLote FROM Lote WHERE Producto_id ='" + String.valueOf(idProd) + "'&& NoLote= (SELECT MAX(NoLote) FROM Lote WHERE Producto_id ='" + String.valueOf(idProd) + "') ")) {
                while (red.next()) {
                    Loteant = Integer.parseInt(red.getString(1));

                }

                Loteant++;
            }
            return Loteant;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    
    private void CrearLote(int idProd, int lotegrande, String idProv,String inf[],int id) {
        try {
            int idUsuario = 0;

            PreparedStatement CrearLot = tr.prepareStatement("INSERT INTO Lote(Producto_id,CostoUnitario,Cantidad,CostoTotal,Descripcion,NoLote,Ganancia,PrecioUnitario,PrecioTotal,Fecha,FacturaCompra_id,Disponible) VALUES(?,?,?,?,?,?,?,?,?,?,?,1)",
                    Statement.RETURN_GENERATED_KEYS);

            CrearLot.setString(1, String.valueOf(idProd));
            CrearLot.setString(2, inf[5]);
            CrearLot.setString(3, inf[1]);
            CrearLot.setString(4, inf[6]);
            CrearLot.setString(5, inf[7]);
            CrearLot.setString(6, String.valueOf(lotegrande));
            CrearLot.setString(7, inf[8]);
            CrearLot.setString(8, inf[9]);
            CrearLot.setString(9, inf[10]);
            CrearLot.setString(10, año+"-"+mes+"-"+dia);
            CrearLot.setString(11, String.valueOf(id));
            CrearLot.executeUpdate();

            try (ResultSet rs = CrearLot.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new RuntimeException("no devolvió el ID");
                }

                idUsuario = rs.getInt(1);
                CrearLot.close();

            }

          

        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
     
   
    }

    private void CreaReg(int idProd, int lotegrande, String idProv,String inf[],int id) {
        try {
           

            PreparedStatement CrearLot = tr.prepareStatement("INSERT INTO Registro_Compras(Producto_id,CostoUnitario,Cantidad,CostoTotal,Descripcion,Ganancia,PrecioUnitario,PrecioTotal,Fecha,FacturaCompra_id) VALUES(?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            CrearLot.setString(1, String.valueOf(idProd));
            CrearLot.setString(2, inf[5]);
            CrearLot.setString(3, inf[1]);
            CrearLot.setString(4, inf[6]);
            CrearLot.setString(5, inf[7]);
            CrearLot.setString(6, inf[8]);
            CrearLot.setString(7, inf[9]);
            CrearLot.setString(8, inf[10]);
            CrearLot.setString(9, año+"-"+mes+"-"+dia);
            CrearLot.setString(10, String.valueOf(id));
            CrearLot.executeUpdate();

            try (ResultSet rs = CrearLot.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new RuntimeException("no devolvió el ID");
                }

                
                CrearLot.close();

            }


        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    private String obtenerid(String nit) {

        String idProv = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rd = st.executeQuery("SELECT NombreV, Representante, id FROM Proveedor WHERE Nit ='" + nit + "'");
            while (rd.next()) {
                Nombre2.setText(rd.getString(1));
                Apellido.setText(rd.getString(2));
               
                idProv = rd.getString(3);
            }
            return idProv;
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    private void NM(String Codigo) {
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Nombre,Marca FROM Producto WHERE Codigo='" + Codigo + "'");
            while (Ca.next()) {
                Nombre.setText(Ca.getString(1));
                Marca.setText(Ca.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SubCompras men = new SubCompras();
        men.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SubCompras men = new SubCompras();
        men.setVisible(true);
        dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    
    try
        {
 
       año = Fech.getCalendar().get(Calendar.YEAR);
         mes = Fech.getCalendar().get(Calendar.MONTH) + 1;
         dia = Fech.getCalendar().get(Calendar.DAY_OF_MONTH);
                                            
    }//GEN-LAST:event_jButton1ActionPerformed

    
    catch(NullPointerException ex) {
    }
    if(año==0&&dia==0&&mes==00)   
    {
        JOptionPane.showMessageDialog(this, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
    }
    else
    {
        if(Serie.getText().equals("")||Numero.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Información de Factura Faltante");            
        }
        else
        {
             if(Factura.getRowCount()==0)
            {
                JOptionPane.showMessageDialog(null, "Factura Vacía");            

            }
            else
            {     
                int idF=generarFactura();
                String x[] = new String[11];
                for (int i = 0; i < Factura.getRowCount(); i++) 
                {
                    for (int j = 0; j < Factura.getColumnCount(); j++) {
                    x[j] = Factura.getValueAt(i, j).toString().trim();

                }
                int iddd = getidPro(x[3], x[4],x[2]);
            
            
                int loteee = lotereciente(iddd);
                CrearLote(iddd, loteee, obtenerid(nitglobal),x,idF);
                CreaReg(iddd, loteee, obtenerid(nitglobal),x,idF);

                }
        }
         
        JOptionPane.showMessageDialog(null, "Productos Comprado Con Exito");

        Menu men = new Menu();
        men.setVisible(true);
        dispose();
        }
    }
    }
        private int generarFactura()
    {
        int id=0;
        try {
            PreparedStatement CrearLot = tr.prepareStatement("INSERT INTO FacturaCompra(Serie,Numero,Total_Factura,Proveedor_id,Cantidad_Prod,Fecha,Anulado) VALUES(?,?,?,?,?,?,0)",
                    Statement.RETURN_GENERATED_KEYS);
            
            CrearLot.setString(1, Serie.getText());
            CrearLot.setString(2, Numero.getText());
            CrearLot.setString(3, Totales.getText());
            CrearLot.setString(4, String.valueOf(getidProve(nitglobal)));
                    CrearLot.setString(5,TotalCantidad.getText());

            CrearLot.setString(6, año+"-"+mes+"-"+dia);
            CrearLot.executeUpdate();
             try (ResultSet rs = CrearLot.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new RuntimeException("no devolvió el ID");
                }

                id = rs.getInt(1);
                CrearLot.close();

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Otro1.removeAllItems();
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Nit,id FROM Proveedor");
            while (Ca.next()) {

                Otro1.addItem(Ca.getString(1));
            }
            Ca.close();
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        IngresarProve x = new IngresarProve();
        x.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Otro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Otro1ActionPerformed
        nitglobal = (String) Otro1.getSelectedItem();
        String id3 = null;
        id3 = obtenerid(nitglobal);
    }//GEN-LAST:event_Otro1ActionPerformed

    private void CostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyTyped
        
        int k = (int) evt.getKeyChar();
        if (k == 10) {
            Costo.transferFocus();
            tp=1;
            Costo.setText(""+x(Double.parseDouble(Costo.getText())));

        }
        else{
            if(k==46)
            {
                tp++;
            }

            if(tp>1)
            {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                JOptionPane.showMessageDialog(null, "Punto de mas", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
                tp--;
            }

            if (k >= 97 && k <= 127 || k >= 58 && k <= 97) {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
            }
            if (k == 241 || k == 209) {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
            }
            if (k >= 33 && k <= 45 || k==47 ) {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                JOptionPane.showMessageDialog(null, "No puede ingresar Simbolos!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_CostoKeyTyped
    private Boolean CompararEntrada(String Nombre,String Marca,String unidad)
    {
        
        String x[] = new String[3];
        if(Factura.getRowCount()!=0)
        {
        for (int i = 0; i < Factura.getRowCount(); i++)
        {
            
                x[0] = Factura.getValueAt(i, 3).toString().trim();
                x[1] = Factura.getValueAt(i, 4).toString().trim();
                x[2] = Factura.getValueAt(i, 2).toString().trim();
                if(x[0].equals(Nombre)&& x[1].equals(Marca)&&x[2].equals(unidad))
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
            JOptionPane.showMessageDialog(null, "Ingrese la Cantidad que desea comprar de: " + Nombre.getText());

        } 
        else 
        {
            
            if(CompararEntrada(Nombre.getText(),Marca.getText(),(String) Unidad.getText())==false)
            {
                JOptionPane.showMessageDialog(null, "Ya tiene este Producto Registrdo en la Factura");
            }
            else
            {
                String Completo = (String) Otro.getSelectedItem();
                try
                {
                
                modelo.addRow(new Object[]{(String) Otro.getSelectedItem(), Cantidad.getText(),(String) Unidad.getText(), Nombre.getText(), Marca.getText(), Costo.getText(), CostoTotal(Double.parseDouble(Cantidad.getText()),Double.parseDouble(Costo.getText())), Descripcion.getText(),
                    Ganancia.getText(),PrecioUnitario(Double.parseDouble(Costo.getText()),Double.parseDouble(Ganancia.getText()),Double.parseDouble(Costo.getText()))
                ,PrecioTotal(          PrecioUnitario(Double.parseDouble(Costo.getText()),Double.parseDouble(Ganancia.getText()),Double.parseDouble(Costo.getText())),Double.parseDouble(Cantidad.getText()))});
                }
                catch(NumberFormatException ex) 
                {
                }
                int TC=0;
                TC=Integer.valueOf(TotalCantidad.getText());
                BigDecimal auxT=BigDecimal.valueOf(Double.parseDouble(Totales.getText()));
                auxT=auxT.add(PrecioTotal(PrecioUnitario(Double.parseDouble(Costo.getText()),Double.parseDouble(Ganancia.getText()),Double.parseDouble(Costo.getText())),Double.parseDouble(Cantidad.getText())));
                Totales.setText(String.valueOf((auxT)));
                TC=TC+Integer.valueOf(Cantidad.getText());
                TotalCantidad.setText(String.valueOf(TC));
            }
        
        }
        Cantidad.setText("");
        Costo.setText("");
        Descripcion.setText("");
        Ganancia.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_addfilaActionPerformed
     private BigDecimal CostoTotal(Double Cantidad,Double Costo)
    {
        return BigDecimal.valueOf(Cantidad).multiply(BigDecimal.valueOf(Costo)).setScale(2, BigDecimal.ROUND_DOWN);
    }
    private BigDecimal PrecioTotal(BigDecimal Uni,Double T)
    {
        return Uni.multiply(BigDecimal.valueOf(T)).setScale(2, BigDecimal.ROUND_DOWN);
    }
    private BigDecimal PrecioUnitario(Double PreU,Double G,Double CU)
    {
        BigDecimal w=BigDecimal.valueOf(PreU).multiply(BigDecimal.valueOf(G)).setScale(2, BigDecimal.ROUND_DOWN);      
        w=w.divide(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_DOWN);
        w=w.add(BigDecimal.valueOf(CU)).setScale(2, BigDecimal.ROUND_DOWN);
        return w;
    }
    private void CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyTyped
         int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 127 || k >= 58 && k <= 97) {
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
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadKeyTyped

    private void CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadActionPerformed

    private void OtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtroActionPerformed
        String Completo = (String) Otro.getSelectedItem();
        NM(Completo);
        LlenarUnidad(Completo);
    }//GEN-LAST:event_OtroActionPerformed

    private void LlenarUnidad(String s)
    {
        try {
            Statement xq = cn.createStatement();
            ResultSet red = xq.executeQuery("SELECT Medida FROM Producto WHERE Codigo ='"+s+"' ");
            while(red.next())
            {
                Unidad.setText(red.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    private void DescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DescripcionActionPerformed

    private void CostoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CostoFocusLost
       try
       {
        Costo.setText(""+x(Double.parseDouble(Costo.getText())));
       }
        catch(NumberFormatException ex) {
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_CostoFocusLost

    private void GananciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GananciaKeyTyped
     int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 127 || k >= 58 && k <= 97) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 241 || k == 209) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k >= 33 && k <=47 ) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar Simbolos!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 10) {
            Ganancia.transferFocus();
        } 
// TODO add your handling code here:
    }//GEN-LAST:event_GananciaKeyTyped

    private void NumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumeroKeyTyped
     int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 127 || k >= 58 && k <= 97) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 241 || k == 209) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k >= 33 && k <=47 ) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar Simbolos!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 10) {
            Numero.transferFocus();
        } 
// TODO add your handling code here:
    }//GEN-LAST:event_NumeroKeyTyped

    private void NumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NumeroFocusLost
Fech.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroFocusLost

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
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Apellido;
    private javax.swing.JTextField Cantidad;
    private javax.swing.JTextField Costo;
    private javax.swing.JTextField Descripcion;
    private rojerusan.RSTableMetro Factura;
    private com.toedter.calendar.JDateChooser Fech;
    private javax.swing.JLabel Fecha;
    private javax.swing.JTextField Ganancia;
    private javax.swing.JLabel Marca;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Nombre2;
    private javax.swing.JTextField Numero;
    private javax.swing.JComboBox<String> Otro;
    private javax.swing.JComboBox<String> Otro1;
    private javax.swing.JTextField Serie;
    private javax.swing.JLabel TotalCantidad;
    private javax.swing.JLabel Totales;
    private javax.swing.JLabel Unidad;
    private javax.swing.JButton addfila;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPaneldedatos;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel panel3;
    private rojeru_san.complementos.RSEstiloTablaHeader rSEstiloTablaHeader1;
    // End of variables declaration//GEN-END:variables
}
