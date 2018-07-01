/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Inventario extends javax.swing.JFrame {

    Ingreso ss = new Ingreso();
    Conexion con = new Conexion();
    Connection cn = con.conexion();
    Connection tr = con.conexion();
    Connection Consulta = con.conexion();

    DefaultTableModel modeloBusqueda = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    private String[] volumen=new String[3];
    private String[] unidad=new String[3];
    private String[] longitud=new String[3];
    private String[] area=new String[3];
    private   String[] DI=new String [5];


    /**
     * Creates new form Inventario
     */
    public Inventario() {

        initComponents();
         volumen[0]="Litros";
    volumen[1]="cm3";
    volumen[2]="Galon";
    unidad[0]="Cajas";
    unidad[1]="pares";
    unidad[2]="docenas";
    longitud[0]="m";
    longitud[1]="cm";
    longitud[2]="pie";
    area[0]="cm2";
    area[1]="m2";
    area[2]="pie2";
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 4 - this.getSize().width / 4, dim.height / 10 - this.getSize().height / 10);
        this.setTitle("Inventario De La 'Empresa' - Sistema Inventario BTZ");

        this.setResizable(false);
//pruebas
     TipoE.addItem("Longitud");
                TipoE.addItem("Volumen");
                TipoE.addItem("Area");
                TipoE.addItem("Unidades");
        //this.setUndecorated(true);
        this.setSize(1275, 700);

        //this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        //
        // this.setSize(475, 500);
        try {

            modeloBusqueda.addColumn("Codigo");
            modeloBusqueda.addColumn("Nombre");
            modeloBusqueda.addColumn("Marca");
            modeloBusqueda.addColumn("Unidad");
            modeloBusqueda.addColumn("Existencia");
            modeloBusqueda.addColumn("Stock Minimo");
            Inventario.setModel(modeloBusqueda);

            String datos[] = new String[6];
            int contar = 0;
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Codigo,Nombre,Marca,Medida,Existencia,StockMinimo FROM Producto");
            while (Ca.next()) {
                datos[0] = Ca.getString(1);
                datos[1] = Ca.getString(2);
                datos[2] = Ca.getString(3);
                datos[3] = Ca.getString(4);
                datos[4] = Ca.getString(5);
                datos[5] = Ca.getString(6);
                modeloBusqueda.addRow(datos);
                contar++;
            }
            Inventario.setModel(modeloBusqueda);
            if (contar == 0) {
                JOptionPane.showMessageDialog(null, "No hay Productos en la base de datos");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        Inventario.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                //try{
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {

                    String x = String.valueOf(Inventario.getValueAt(Inventario.getSelectedRow(), 2));
                    String xr = String.valueOf(Inventario.getValueAt(Inventario.getSelectedRow(), 1));
                    String brz = String.valueOf(Inventario.getValueAt(Inventario.getSelectedRow(), 3));

                    MostrarLotes ere = new MostrarLotes(x, xr, brz);
                    ere.setVisible(true);

                }
                /*catch()
                         {
                 
                 }*/
            }
        });
        FormatoTabla ft = new FormatoTabla(0);
        Inventario.setDefaultRenderer(Object.class, ft);
        Todo.setSelected(true);
        LLenar.setVisible(false);
        Edit1.setEnabled(false);
        PanelEditar.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        Panel = new javax.swing.JPanel();
        Act = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Inventario = new rojerusan.RSTableMetro();
        P2 = new javax.swing.JRadioButton();
        Todo = new javax.swing.JRadioButton();
        Generar = new javax.swing.JButton();
        LLenar = new rojerusan.RSComboMetro();
        jLabel5 = new javax.swing.JLabel();
        PanelEditar = new javax.swing.JPanel();
        NO = new javax.swing.JLabel();
        Edit2 = new javax.swing.JLabel();
        CodigoE = new javax.swing.JTextField();
        ProductoE = new javax.swing.JTextField();
        Edit3 = new javax.swing.JLabel();
        MarcaE = new javax.swing.JTextField();
        Modificar = new javax.swing.JButton();
        Edit4 = new javax.swing.JLabel();
        StockE = new javax.swing.JTextField();
        TipoE = new javax.swing.JComboBox<>();
        MedidaE = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Edit1 = new javax.swing.JLabel();

        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Menu.add(Editar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(new java.awt.Dimension(1040, 602));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Panel.setBackground(new java.awt.Color(189, 189, 189));
        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Act.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-actualizar-50.png"))); // NOI18N
        Act.setBorderPainted(false);
        Act.setContentAreaFilled(false);
        Act.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-actualizar-filled-50.png"))); // NOI18N
        Act.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActActionPerformed(evt);
            }
        });
        Panel.add(Act, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 510, 40, 42));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-producto-50.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setDefaultCapable(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-producto-filled-50.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 51, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-volver-asignación-50.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-volver-asignación-filled-50.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Panel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 490, 49, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INVENTARIO");
        Panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 200, 44));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Agregar producto");
        Panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Regresar");
        Panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, -1, -1));

        Inventario.setModel(new javax.swing.table.DefaultTableModel(
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
        Inventario.setColorBackgoundHead(new java.awt.Color(85, 1, 156));
        Inventario.setComponentPopupMenu(Menu);
        jScrollPane2.setViewportView(Inventario);

        Panel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1010, 320));

        P2.setText("Producto");
        P2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P2ActionPerformed(evt);
            }
        });
        Panel.add(P2, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 81, -1, -1));

        Todo.setText("Todo");
        Todo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TodoActionPerformed(evt);
            }
        });
        Panel.add(Todo, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 81, -1, -1));

        Generar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-vuelto-asignación-50.png"))); // NOI18N
        Generar.setBorderPainted(false);
        Generar.setContentAreaFilled(false);
        Generar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-vuelto-asignación-filled-50.png"))); // NOI18N
        Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarActionPerformed(evt);
            }
        });
        Panel.add(Generar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, -1, -1));

        LLenar.setColorArrow(new java.awt.Color(86, 0, 169));
        LLenar.setColorBorde(new java.awt.Color(86, 0, 169));
        LLenar.setColorFondo(new java.awt.Color(86, 0, 169));
        LLenar.setFocusCycleRoot(true);
        LLenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LLenarActionPerformed(evt);
            }
        });
        Panel.add(LLenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 76, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Actualizar");
        Panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, -1, -1));

        PanelEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelEditar.setLayout(null);

        NO.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        NO.setText("Stock");
        PanelEditar.add(NO);
        NO.setBounds(10, 230, 95, 22);

        Edit2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Edit2.setText("Código");
        PanelEditar.add(Edit2);
        Edit2.setBounds(10, 20, 69, 22);
        PanelEditar.add(CodigoE);
        CodigoE.setBounds(10, 50, 70, 34);

        ProductoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductoEActionPerformed(evt);
            }
        });
        PanelEditar.add(ProductoE);
        ProductoE.setBounds(10, 120, 80, 34);

        Edit3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Edit3.setText("Producto");
        PanelEditar.add(Edit3);
        Edit3.setBounds(10, 90, 95, 22);

        MarcaE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarcaEActionPerformed(evt);
            }
        });
        PanelEditar.add(MarcaE);
        MarcaE.setBounds(10, 190, 80, 34);

        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        PanelEditar.add(Modificar);
        Modificar.setBounds(110, 230, 100, 27);

        Edit4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Edit4.setText("Marca");
        PanelEditar.add(Edit4);
        Edit4.setBounds(10, 160, 95, 22);

        StockE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockEActionPerformed(evt);
            }
        });
        StockE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                StockEKeyTyped(evt);
            }
        });
        PanelEditar.add(StockE);
        StockE.setBounds(10, 260, 82, 34);

        TipoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoEActionPerformed(evt);
            }
        });
        PanelEditar.add(TipoE);
        TipoE.setBounds(102, 40, 110, 34);

        PanelEditar.add(MedidaE);
        MedidaE.setBounds(99, 120, 110, 34);

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        PanelEditar.add(jButton3);
        jButton3.setBounds(110, 270, 100, 27);

        Panel.add(PanelEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 130, 220, 320));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Generar Reporte");
        Panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, -1, -1));

        Edit1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Edit1.setText("Modificar");
        Panel.add(Edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, -1, -1));

        getContentPane().add(Panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActActionPerformed
        actua();
    }//GEN-LAST:event_ActActionPerformed
     private void llenarOtro()
    {   
         MedidaE.removeAllItems();
        String sele = (String) TipoE.getSelectedItem();
       
        if(sele.equals("Longitud"))
        {
            for (int i = 0; i <longitud.length; i++) {
                MedidaE.addItem(longitud[i]);
            }
        }
        if(sele.equals("Unidades"))
        {
            for (int i = 0; i <unidad.length; i++) {
                MedidaE.addItem(unidad[i]);
            }
        }
        if(sele.equals("Volumen"))
        {
            for (int i = 0; i <volumen.length; i++) {
                MedidaE.addItem(volumen[i]);
            }
        }
        if(sele.equals("Area"))
        {
            for (int i = 0; i <area.length; i++) {
                MedidaE.addItem(area[i]);
            }
        }
    }
    private void actua() {
        try {

            DefaultTableModel modeloBusqueda = new DefaultTableModel() {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            modeloBusqueda.addColumn("Codigo");
            modeloBusqueda.addColumn("Nombre");
            modeloBusqueda.addColumn("Marca");
            modeloBusqueda.addColumn("Unidad");
            modeloBusqueda.addColumn("Existencia");
            modeloBusqueda.addColumn("Stock Minimo");
            Inventario.setModel(modeloBusqueda);

            String datos[] = new String[6];
            int contar = 0;
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Codigo,Nombre,Marca,Medida,Existencia,StockMinimo FROM Producto");
            while (Ca.next()) {
                datos[0] = Ca.getString(1);
                datos[1] = Ca.getString(2);
                datos[2] = Ca.getString(3);
                datos[3] = Ca.getString(4);
                datos[4] = Ca.getString(5);
                datos[5] = Ca.getString(6);
                modeloBusqueda.addRow(datos);
                contar++;
            }
            Inventario.setModel(modeloBusqueda);
            if (contar == 0) {
                JOptionPane.showMessageDialog(null, "No hay Productos en la base de datos");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ss.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Menu men = new Menu();
        men.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Menu men = new Menu();
        men.setVisible(true);
        dispose(); // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private Boolean Autorizar() {
        return true;
    }

   

  
    
    private void llenarCamposAc()
    {
        CodigoE.setText(String.valueOf(Inventario.getValueAt(Inventario.getSelectedRow(), 0)));
        ProductoE.setText(String.valueOf(Inventario.getValueAt(Inventario.getSelectedRow(), 1)));
        MarcaE.setText(String.valueOf(Inventario.getValueAt(Inventario.getSelectedRow(), 2)));
        StockE.setText(String.valueOf(Inventario.getValueAt(Inventario.getSelectedRow(), 5)));
        DI[3]=String.valueOf(Inventario.getValueAt(Inventario.getSelectedRow(), 3));
    }
  
     


    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int seleccionar = 0;
        seleccionar = Inventario.getSelectedRow();
        if (seleccionar == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un fila valida");
        } else {
            if (Autorizar() == true) {
                PanelEditar.setVisible(true);
                Edit1.setEnabled(true);
                llenarCamposAc();
               
                 
                 DI[0]=CodigoE.getText();
                 DI[1]=ProductoE.getText();
                 DI[2]=MarcaE.getText();
                 DI[4]=StockE.getText();
                //actualizar(seleccionar);
                //actua();
            } else {
                JOptionPane.showMessageDialog(null, "Acceso Denegado");

            }
        }


    

        // TODO add your handling code here:
    }//GEN-LAST:event_EditarActionPerformed

    
    private void TodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TodoActionPerformed

        if (Todo.isSelected() == true) {
            P2.setSelected(false);
            llena();
            LLenar.setVisible(false);
        } else {
            P2.setSelected(true);
            LLenar.removeAllItems();

            prod();
            LLenar.setVisible(true);

            Todo.setSelected(false);
        }


    }//GEN-LAST:event_TodoActionPerformed

    private void llena() {
        modeloBusqueda.setRowCount(0);

        try {

            String datos[] = new String[6];
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Codigo,Nombre,Marca,Medida,Existencia,StockMinimo FROM Producto");
            while (Ca.next()) {
                datos[0] = Ca.getString(1);
                datos[1] = Ca.getString(2);
                datos[2] = Ca.getString(3);
                datos[3] = Ca.getString(4);
                datos[4] = Ca.getString(5);
                datos[5] = Ca.getString(6);
                modeloBusqueda.addRow(datos);
            }
            Inventario.setModel(modeloBusqueda);

        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarActionPerformed
     
        // TODO add your handling code here:
    }//GEN-LAST:event_GenerarActionPerformed
    
    private void P2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P2ActionPerformed
        if (P2.isSelected() == true) {
            LLenar.removeAllItems();

            prod();
            LLenar.setVisible(true);

            Todo.setSelected(false);
        } else {
            Todo.setSelected(true);

            P2.setSelected(false);
            llena();
            LLenar.setVisible(false);
        }
    }//GEN-LAST:event_P2ActionPerformed

    private void LLenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LLenarActionPerformed
        String Completo = (String) LLenar.getSelectedItem();
        llenarBus(Completo);
        // TODO add your handling code here:
    }//GEN-LAST:event_LLenarActionPerformed

    private void ProductoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductoEActionPerformed

    private void MarcaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarcaEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MarcaEActionPerformed

    private void StockEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StockEActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        PanelEditar.setVisible(false);
        Edit1.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed

        String[] datos = new String[5];
        datos[0] = CodigoE.getText();
        datos[1] = ProductoE.getText();
        datos[2] = MarcaE.getText();
        datos[3] =(String)MedidaE.getSelectedItem();
        datos[4] = StockE.getText();
        if( PC(DI,datos)==true|| PMNM(DI,datos)==true|| PS(DI,datos)==true)
        {
            try {
                PreparedStatement ActualizarProveedor = cn.prepareStatement("UPDATE Producto SET Codigo='"+datos[0]+"',Nombre='"+datos[1]+"',Marca='"+datos[2]+"',Medida='"+datos[3]+"',StockMinimo='"+datos[4]+"' where id= '"+EI(DI)+"'");
                        ActualizarProveedor.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }

            actua();
             PanelEditar.setVisible(false);
           Edit1.setEnabled(false);
           JOptionPane.showMessageDialog(null, "Se modifico con exito el registro");

        }
           
        else
        {
           JOptionPane.showMessageDialog(null, "No se modifico ningun registro");
           PanelEditar.setVisible(false);
           Edit1.setEnabled(false);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_ModificarActionPerformed

    private void TipoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoEActionPerformed
llenarOtro();        // TODO add your handling code here:
    }//GEN-LAST:event_TipoEActionPerformed

    private void StockEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StockEKeyTyped
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
            StockE.transferFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_StockEKeyTyped
    private Boolean PC(String x[],String y[])
    {
        if(x[0].equals(y[0]))
        {
            return false;
        }
        else
        {
            int c=0;
            try {
                //como no son iguales verificamos el nuevo codigo en la BD
                Statement sx = Consulta.createStatement();
                ResultSet Ca = sx.executeQuery("SELECT id From Producto where Codigo='"+y[0]+"'");
                while(Ca.next())
                {
                    c++;
                }
                if(c==0)
                {
                    //confirmamos que este codigo no es siendo utilizado en nuestr bd y pasamos a validar nombre marca y medida
                    return true;
                }
                else
                {
                    //sino desplegamos el mensaje de que el codigo esta siendo utilizado
                    JOptionPane.showMessageDialog(null, "Este Codigo ya esta Registrado: "+CodigoE.getText());
                    CodigoE.setText("");
                    return false;

                }
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
        return null;
    }
    private Boolean PMNM(String x[],String y[])
    {
        if(x[1].equals(y[1])&&x[2].equals(y[2])&&x[3].equals(y[3]))//por si no se cambio el nombre
        {
            return false;
        }
        else        
        {
            int c=0;
            try //sino son iguales pasamos a validar el nombre marca y medida
            {
                System.out.println("Consulta");
                Statement sx = Consulta.createStatement();
                ResultSet Ca = sx.executeQuery("SELECT id From Producto where Nombre='"+y[1]+"' && Marca='"+y[2]+"' && Medida='"+y[3]+"'");
                
                while(Ca.next())
                {
                    c++;
                }
                if(c==0)
                {
                    //validamos el Stock 
                   
                    return true;
                }
                else
                {
                  JOptionPane.showMessageDialog(null, "Estos datos ya estan siendo utilizado: "+ProductoE.getText()+", "+
                          MarcaE.getText()+", "+(String)MedidaE.getSelectedItem());
                  return false;
                  
                }
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    private Boolean PS(String x[],String y[])
    {
        if(x[4].equals(y[4]))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private String EI(String x[])
    {
        String id=null;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id From Producto where Nombre='"+x[1]+"' && Marca='"+x[2]+"' && Medida='"+x[3]+"'");
            while(Ca.next())
            {
                id=Ca.getString(1);
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    private void llenarBus(String Cod) {
        modeloBusqueda.setRowCount(0);

        try {

            String datos[] = new String[6];
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Codigo,Nombre,Marca,Medida,Existencia,StockMinimo FROM Producto where Codigo='" + Cod + "'");
            while (Ca.next()) {
                datos[0] = Ca.getString(1);
                datos[1] = Ca.getString(2);
                datos[2] = Ca.getString(3);
                datos[3] = Ca.getString(4);
                datos[4] = Ca.getString(5);
                datos[5] = Ca.getString(6);
                modeloBusqueda.addRow(datos);
            }
            Inventario.setModel(modeloBusqueda);

        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void prod() {
        AutoCompleteDecorator.decorate(LLenar);

        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Codigo FROM Producto");
            while (Ca.next()) {

                LLenar.addItem(Ca.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Inventario().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Act;
    private javax.swing.JTextField CodigoE;
    private javax.swing.JLabel Edit1;
    private javax.swing.JLabel Edit2;
    private javax.swing.JLabel Edit3;
    private javax.swing.JLabel Edit4;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JButton Generar;
    private rojerusan.RSTableMetro Inventario;
    private rojerusan.RSComboMetro LLenar;
    private javax.swing.JTextField MarcaE;
    private javax.swing.JComboBox<String> MedidaE;
    private javax.swing.JPopupMenu Menu;
    private javax.swing.JButton Modificar;
    private javax.swing.JLabel NO;
    private javax.swing.JRadioButton P2;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel PanelEditar;
    private javax.swing.JTextField ProductoE;
    private javax.swing.JTextField StockE;
    private javax.swing.JComboBox<String> TipoE;
    private javax.swing.JRadioButton Todo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
