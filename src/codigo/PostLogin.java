/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PostLogin extends javax.swing.JFrame {
    
    static int MAX_PELIS = 200;
    static int CARATULA_X = 75;
    static int CARATULA_Y = 85;
    static int NUM_USUARIOS = 200;
    
    Connection conexion;//para almacenar la conexion
    Statement estado;//almacenar el estado de la conexion
    ResultSet resultado;//resultado de la consulta
    static Login login = new Login();
    private Dimension dim;

    String[] datosPeliculas = new String[7];
    String[] datosUsuario = new String[5];

    String[][] datosPeliculasx2 = new String[MAX_PELIS][8];
    String[][] datosUsuariox2 = new String[NUM_USUARIOS][5];
    
    private void ponerPelisEnPantalla() {
        PanelCine.setLayout(null);

        for (int row = 0, peli = 0; row < 9; row++) {
            for (int col = 0; col < 18 && datosPeliculasx2[peli][0] != null; col++) {
                JLabel label = new JLabel();
                label.setBounds(10 + col * (CARATULA_X + 10), 10 + row * (CARATULA_Y +10), CARATULA_X, CARATULA_Y);
                // lo de format -----int  3  ---->  %06d" ---- 000003[7]
                ImageIcon fotoPeli = new ImageIcon(
                        getClass().getResource("/caratulas/" + String.format("%06d", Integer.parseInt(datosPeliculasx2[peli][7])) + ".jpg"));

                Image image = fotoPeli.getImage(); // transform it 
                Image newimg = image.getScaledInstance(CARATULA_X, CARATULA_Y, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                fotoPeli = new ImageIcon(newimg);  // transform it backaledInstance(60, 80, Image.SCALE_SMOOTH);
                label.setIcon(fotoPeli);
                label.setText(datosPeliculasx2[peli][7]);
                
                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e){
                        int i = 0;
                        for (i = 0; i < datosPeliculasx2.length; i++){
                            if (datosPeliculasx2[i][7] == label.getText().toString()) 
                            break;
                        }
                        peliculas.setVisible(true);
                        imagen.setIcon(label.getIcon());
                        titulo.setText(datosPeliculasx2[i][0]);
                        fechaPublicacion.setText(datosPeliculasx2[i][1]);
                        genero.setText(datosPeliculasx2[i][2]);
                        pais.setText(datosPeliculasx2[i][3]);
                        imdb.setText(datosPeliculasx2[i][4]);
                        calificacion.setText(datosPeliculasx2[i][5]);
                        descripcion.setText(datosPeliculasx2[i][6]);
                        descripcion.setEditable(false);
                        imagen.setText(datosPeliculasx2[i][7]);
                    }
                });
                
                this.setVisible(false);
                PanelCine.add(label);
                peli++;
            }
        }

    }
     private void usuarioMenu() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://172.16.3.79/VideoClub", "bbdd1", "Ysg19.pv");
            estado = conexion.createStatement();
            resultado = estado.executeQuery("SELECT * FROM VideoClub.usuarios");
            resultado.first();
            while (resultado.next()) {
                datosUsuario[0] = resultado.getString("DNI");
                datosUsuario[1] = resultado.getString("Nombre");
                datosUsuario[2] = resultado.getString("Apellido");
                datosUsuario[3] = resultado.getString("Penalizacion");
                datosUsuario[4] = resultado.getString("email");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver");
        } catch (SQLException ex) {
            System.out.println("No se ha podido conectar a la BBDD");
        }
    }
      private void pelicula() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://172.16.3.79/VideoClub", "bbdd1", "Ysg19.pv");
            estado = conexion.createStatement();
            resultado = estado.executeQuery("SELECT * FROM VideoClub.peliculas");
            resultado.first();
            int i = 0;
            do {
                datosPeliculasx2[i][0] = resultado.getString("titulo");
                datosPeliculasx2[i][1] = resultado.getString("año");
                datosPeliculasx2[i][2] = resultado.getString("pais");
                datosPeliculasx2[i][3] = resultado.getString("genero");
                datosPeliculasx2[i][4] = resultado.getString("imdb");
                datosPeliculasx2[i][5] = resultado.getString("clasificacion_imdb");
                datosPeliculasx2[i][6] = resultado.getString("resumen");
                datosPeliculasx2[i][7] = resultado.getString("id_pelicula");
                i++;
            } while (resultado.next());
        } catch (ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el Driver");
        } catch (SQLException ex) {
            System.out.println("No se ha podido conectar a la BBDD");
        }
    }
               
        public PostLogin() {
        
        initComponents();
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
//      setIconImage(new ImageIcon(getClass().getResource("/img/claqueta1.png")).getImage());
        dim = super.getToolkit().getScreenSize();
        super.setSize(1366, 768);
        this.setLocation(0, 0);
        peliculas.setSize(500, 600);
        peliculas.setLocation(150, 90);
        usuarioDialog.setSize(500, 350);
        usuarioDialog.setLocation(400, 90);
        pelicula();
        usuarioMenu();
        ponerPelisEnPantalla();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        peliculas = new javax.swing.JDialog();
        imagen = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        fechaPublicacion = new javax.swing.JLabel();
        genero = new javax.swing.JLabel();
        pais = new javax.swing.JLabel();
        imdb = new javax.swing.JLabel();
        calificacion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        usuarioDialog = new javax.swing.JDialog();
        dni = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        apellido = new javax.swing.JLabel();
        penalizacion = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PanelCine = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuCerrarSesion = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        peliculas.setLocation(new java.awt.Point(400, 100));

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setText("imagen");

        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("titulo");

        fechaPublicacion.setText("fechaPublicacion");

        genero.setText("genero");

        pais.setText("pais");

        imdb.setText("imdb");

        calificacion.setText("calificacion");

        descripcion.setColumns(20);
        descripcion.setLineWrap(true);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        javax.swing.GroupLayout peliculasLayout = new javax.swing.GroupLayout(peliculas.getContentPane());
        peliculas.getContentPane().setLayout(peliculasLayout);
        peliculasLayout.setHorizontalGroup(
            peliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imagen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(peliculasLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(peliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(peliculasLayout.createSequentialGroup()
                        .addGroup(peliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaPublicacion)
                            .addComponent(imdb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(genero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                .addContainerGap())
        );
        peliculasLayout.setVerticalGroup(
            peliculasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(peliculasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imdb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        usuarioDialog.setLocation(new java.awt.Point(400, 100));

        dni.setText("dni");

        nombre.setText("nombre");

        apellido.setText("apellido");

        penalizacion.setText("penalización");

        email.setText("email");

        javax.swing.GroupLayout usuarioDialogLayout = new javax.swing.GroupLayout(usuarioDialog.getContentPane());
        usuarioDialog.getContentPane().setLayout(usuarioDialogLayout);
        usuarioDialogLayout.setHorizontalGroup(
            usuarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuarioDialogLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(usuarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usuarioDialogLayout.createSequentialGroup()
                        .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(usuarioDialogLayout.createSequentialGroup()
                        .addGroup(usuarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(penalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(apellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(usuarioDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addComponent(jSeparator1)
        );
        usuarioDialogLayout.setVerticalGroup(
            usuarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuarioDialogLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(usuarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(penalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 100));

        javax.swing.GroupLayout PanelCineLayout = new javax.swing.GroupLayout(PanelCine);
        PanelCine.setLayout(PanelCineLayout);
        PanelCineLayout.setHorizontalGroup(
            PanelCineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 966, Short.MAX_VALUE)
        );
        PanelCineLayout.setVerticalGroup(
            PanelCineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        jMenu1.setText("Usuario");

        jMenuCerrarSesion.setText("Perfil");
        jMenuCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuCerrarSesion);

        jMenuItem2.setText("Cerrar Sesión");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelCine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelCine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCerrarSesionActionPerformed
        usuarioDialog.setVisible(true);
        String user = datosUsuario[0];
        dni.setText("DNI:  " + datosUsuario[0]);
        nombre.setText("Nombre:  " + datosUsuario[1]);
        apellido.setText("Apellido:  " + datosUsuario[2]);
        penalizacion.setText("Penalización:  " + datosUsuario[3]);
        email.setText("E-mail:  " + datosUsuario[4]);
        ImageIcon imagenusuario = new ImageIcon(getClass().getResource("/fotosUsuarios/5062544.jpg"));
                ImageIcon fotaca = new ImageIcon(imagenusuario.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
                foto.setIcon(fotaca);
//        fotoUserLabel.setIcon(fotoUser.getIcon());
    }//GEN-LAST:event_jMenuCerrarSesionActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(PostLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PostLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PostLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PostLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PostLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCine;
    private javax.swing.JLabel apellido;
    private javax.swing.JLabel calificacion;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JLabel dni;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fechaPublicacion;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel genero;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel imdb;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuCerrarSesion;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel pais;
    private javax.swing.JDialog peliculas;
    private javax.swing.JLabel penalizacion;
    private javax.swing.JLabel titulo;
    private javax.swing.JDialog usuarioDialog;
    // End of variables declaration//GEN-END:variables
}
