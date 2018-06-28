package inventario2;

//import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

        this.setSize(1000, 1000);
        AutoCompleteDecorator.decorate(Otro);
        AutoCompleteDecorator.decorate(Otro1);
        
        modelo.setRowCount(0);
        modelo.addColumn("Codigo"); //0

        modelo.addColumn("Cantidad"); //1
        modelo.addColumn("Nombre");//2
        modelo.addColumn("Marca");//3
        modelo.addColumn("Costo Unitario");//4
        modelo.addColumn("Costo Total");//5
        modelo.addColumn("Descripcion");//6
        modelo.addColumn("Ganancia");//7 
        modelo.addColumn("Precio Unitario");//8
        modelo.addColumn("Precio Total");//9
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
                    String a=String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 2));
                    String b=String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 3));
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
        Fech = new com.toedter.calendar.JDateChooser();
        Fecha = new javax.swing.JLabel();
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Factura = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        Totales = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TotalCantidad = new javax.swing.JLabel();

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

        jPaneldedatos.setBackground(new java.awt.Color(189, 189, 189));

        jLabel7.setText("Factura");

        jLabel8.setText("Serie");

        jLabel14.setText("Numero");

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

        jLabel10.setText("Nombre");

        Nombre2.setText("jLabel9");

        jLabel11.setText("Representante");

        Apellido.setText("jLabel14");

        jLabel13.setText("Nit Proveedor");

        Otro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Otro1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("Agregar proveedor");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ingresoproveedor.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizara17.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Actualizar");

        jLabel22.setText("Proveedor");

        Fecha.setText("Fecha");

        javax.swing.GroupLayout jPaneldedatosLayout = new javax.swing.GroupLayout(jPaneldedatos);
        jPaneldedatos.setLayout(jPaneldedatosLayout);
        jPaneldedatosLayout.setHorizontalGroup(
            jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneldedatosLayout.createSequentialGroup()
                .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(Serie, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jPaneldedatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Otro1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel22)
                            .addGroup(jPaneldedatosLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPaneldedatosLayout.createSequentialGroup()
                                .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneldedatosLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneldedatosLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(42, 42, 42)))
                                .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(22, 22, 22)
                .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneldedatosLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPaneldedatosLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneldedatosLayout.createSequentialGroup()
                        .addComponent(Fecha)
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneldedatosLayout.createSequentialGroup()
                        .addComponent(Fech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        jPaneldedatosLayout.setVerticalGroup(
            jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneldedatosLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPaneldedatosLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(jPaneldedatosLayout.createSequentialGroup()
                        .addComponent(Fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Fech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneldedatosLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Nombre2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPaneldedatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(Apellido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Otro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPaneldedatosLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        jPanel2.setBackground(new java.awt.Color(189, 189, 189));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );

        jLabel1.setText("Producto");

        Otro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtroActionPerformed(evt);
            }
        });

        Nombre.setText("Nombre");

        Marca.setText("Marca");

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

        addfila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/masa10.png"))); // NOI18N
        addfila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addfilaActionPerformed(evt);
            }
        });

        jLabel2.setText("Cantidad");

        jLabel6.setText("CostoUnitario");

        jLabel5.setText("Descripcion");

        jLabel19.setText("Porcentaje de ganancia");

        Descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescripcionActionPerformed(evt);
            }
        });

        jLabel20.setText("%");

        jLabel23.setText("Nombre:");

        jLabel24.setText("Apellido:");

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

        Ganancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GananciaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addfila, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel5))
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(128, 128, 128)
                                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Costo, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                            .addComponent(Cantidad)))
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19)))
                                .addGap(17, 17, 17)
                                .addComponent(Ganancia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Otro, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(250, 250, 250))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Otro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nombre)
                            .addComponent(jLabel23))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Marca)
                                .addComponent(jLabel24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Ganancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)))
                        .addGap(22, 22, 22)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(Costo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addfila, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPaneldedatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPaneldedatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Factura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cantidad", "Nombre", "Marca", "Costo Unitario", "Costo Total"
            }
        ));
        jScrollPane1.setViewportView(Factura);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setText("Total");

        Totales.setText("0.0");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprasa7.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setText("Comprar");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresara10.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setText("Regresar");

        jLabel9.setText("Total");

        TotalCantidad.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Totales, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1113, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TotalCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(TotalCantidad))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(Totales)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)))
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



    private float precio() {
        float cu = Float.parseFloat(Costo.getText());
        float ganancia = Float.parseFloat(Ganancia.getText());
        ganancia = ganancia / 100;
        float preciou = 0;
        preciou = cu + cu * ganancia;
        return preciou;
    }

    private void CrearLote(int idProd, int lotegrande, String idProv,String inf[],int id) {
        try {
            int idUsuario = 0;

            PreparedStatement CrearLot = tr.prepareStatement("INSERT INTO Lote(Producto_id,CostoUnitario,Cantidad,CostoTotal,Descripcion,NoLote,Ganancia,PrecioUnitario,PrecioTotal,Fecha,FacturaCompra_id,Disponible) VALUES(?,?,?,?,?,?,?,?,?,?,?,1)",
                    Statement.RETURN_GENERATED_KEYS);

            CrearLot.setString(1, String.valueOf(idProd));
            CrearLot.setString(2, inf[4]);
            CrearLot.setString(3, inf[1]);
            CrearLot.setString(4, inf[5]);
            CrearLot.setString(5, inf[6]);
            CrearLot.setString(6, String.valueOf(lotegrande));
            CrearLot.setString(7, inf[7]);
            CrearLot.setString(8, inf[8]);
            CrearLot.setString(9, inf[9]);
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
            CrearLot.setString(2, inf[4]);
            CrearLot.setString(3, inf[1]);
            CrearLot.setString(4, inf[5]);
            CrearLot.setString(5, inf[6]);
            CrearLot.setString(6, inf[7]);
            CrearLot.setString(7, inf[8]);
            CrearLot.setString(8, inf[9]);
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
            ResultSet rd = st.executeQuery("SELECT Nombre, Representante, id FROM Proveedor WHERE Nit ='" + nit + "'");
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
                String x[] = new String[10];
                for (int i = 0; i < Factura.getRowCount(); i++) 
                {
                    for (int j = 0; j < Factura.getColumnCount(); j++) {
                    x[j] = Factura.getValueAt(i, j).toString().trim();

                }
                int iddd = getidPro(x[2], x[3]);
            
            
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
            PreparedStatement CrearLot = tr.prepareStatement("INSERT INTO FacturaCompra(Serie,Numero,Total,Proveedor_id,Fecha) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            CrearLot.setString(1, Serie.getText());
            CrearLot.setString(2, Numero.getText());
            CrearLot.setString(3, "0.0");
            CrearLot.setString(4, String.valueOf(getidProve(nitglobal)));
            CrearLot.setString(5, año+"-"+mes+"-"+dia);
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
    private Boolean CompararEntrada(String Nombre,String Marca)
    {
        
        String x[] = new String[2];
        if(Factura.getRowCount()!=0)
        {
        for (int i = 0; i < Factura.getRowCount(); i++)
        {
            
                x[0] = Factura.getValueAt(i, 2).toString().trim();
                x[1] = Factura.getValueAt(i, 3).toString().trim();
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
            JOptionPane.showMessageDialog(null, "Ingrese la Cantidad que desea comprar de: " + Nombre.getText());

        } 
        else 
        {
            
            if(CompararEntrada(Nombre.getText(),Marca.getText())==false)
            {
                JOptionPane.showMessageDialog(null, "Ya tiene este Producto Registrdo en la Factura");
            }
            else
            {
                String Completo = (String) Otro.getSelectedItem();
                try
                {
                
                modelo.addRow(new Object[]{(String) Otro.getSelectedItem(), Cantidad.getText(), Nombre.getText(), Marca.getText(), Costo.getText(), CostoTotal(Double.parseDouble(Cantidad.getText()),Double.parseDouble(Costo.getText())), Descripcion.getText(),
                    Ganancia.getText(),PrecioUnitario(Double.parseDouble(Costo.getText()),Double.parseDouble(Ganancia.getText()),Double.parseDouble(Costo.getText()))
                ,PrecioTotal(          PrecioUnitario(Double.parseDouble(Costo.getText()),Double.parseDouble(Ganancia.getText()),Double.parseDouble(Costo.getText())),Double.parseDouble(Cantidad.getText()))});
                }
                catch(NumberFormatException ex) 
                {
                }
                int TC=0;
                TC=Integer.valueOf(TotalCantidad.getText());
                BigDecimal auxT=BigDecimal.valueOf(Double.parseDouble(Totales.getText()));
                auxT=auxT.add(PrecioTotal(          PrecioUnitario(Double.parseDouble(Costo.getText()),Double.parseDouble(Ganancia.getText()),Double.parseDouble(Costo.getText())),Double.parseDouble(Cantidad.getText())));
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
    }//GEN-LAST:event_OtroActionPerformed

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
    private javax.swing.JTable Factura;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPaneldedatos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel panel3;
    // End of variables declaration//GEN-END:variables
}
