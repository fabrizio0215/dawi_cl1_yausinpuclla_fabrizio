package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Alumno;
import model.Carrera;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class Registro_Yausin extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCarrera;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	
	 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
	 EntityManager em = fabrica.createEntityManager();
	 private JTextField txtTlf;
	 private JTextField txtEdad;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro_Yausin frame = new Registro_Yausin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registro_Yausin() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(177, 137, 95, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setRowHeaderView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Cod. Alumno");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCarrera = new JComboBox();
		cboCarrera.setBounds(303, 10, 102, 22);
		contentPane.add(cboCarrera);
		
		JLabel lblCategora = new JLabel("Carrera");
		lblCategora.setBounds(227, 14, 95, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nombre");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(122, 42, 89, 20);
		contentPane.add(txtNombre);
		
		JLabel lblStock = new JLabel("Apellido");
		lblStock.setBounds(10, 76, 102, 14);
		contentPane.add(lblStock);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(122, 73, 89, 20);
		contentPane.add(txtApellido);
		
		JLabel lblPrecio = new JLabel("Dni");
		lblPrecio.setBounds(10, 107, 102, 14);
		contentPane.add(lblPrecio);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(122, 104, 86, 20);
		contentPane.add(txtDni);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(227, 76, 102, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(227, 107, 102, 14);
		contentPane.add(lblEdad);
		
		txtTlf = new JTextField();
		txtTlf.setColumns(10);
		txtTlf.setBounds(303, 73, 89, 20);
		contentPane.add(txtTlf);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(303, 104, 89, 20);
		contentPane.add(txtEdad);
		
		llenaCombo1();
	}
	

	void llenaCombo1() {
		//obteber un listado de las categorias 
		List<Carrera> lstCarrera=em.createQuery("select c from Carrera c", Carrera.class).getResultList();
		
		//pasar el listado al combo
		cboCarrera.addItem("Seleccione.."); //INDICE  0
		for (Carrera c : lstCarrera) {
			cboCarrera.addItem(c.getNombre_carrera());
		}
	}
	
	
	void listado() {
		List<Alumno> lstAlumno=em.createQuery("select a from Alumno a", Alumno.class).getResultList();
					
				for (Alumno a : lstAlumno) {
					imprimir("Nombre...:"+a.getNombre_alumno());
					imprimir("Apellido.........:"+a.getApellido_alumno());
					imprimir("Telefono.....:"+a.getTlf_alumno());
					imprimir("Carrera..:"+a.getCod_carrera()+"-"+a.getObjCarrera().getNombre_carrera());
					imprimir("Carrera..:"+a.getCod_carrera()+"-"+a.getObjCarrera().getNombre_carrera());

					imprimir("---------------------------");
				}
	}
	
	void imprimir(String s) {
		txtSalida.append(s + "\n");
	}
	
	void registrar() {
		//leer las cajas
		String idcod = txtCodigo.getText();
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String dni = txtDni.getText();
		String telefono = txtTlf.getText();
		int edad = Integer.parseInt(txtEdad.getText());
		int carrera = cboCarrera.getSelectedIndex();
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		 EntityManager em = fabrica.createEntityManager();
		//crear un objeto de producto
		Alumno a = new Alumno();
		a.setCod_alumno(idcod);
		a.setNombre_alumno(nombre);
		a.setApellido_alumno(apellido);
		a.setDni_alumno(dni);
		a.setTlf_alumno(telefono);
		a.setEdad_alumno(edad);
		a.setCod_carrera(carrera);
		
		try {
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(this, "Registró...");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erroral registrar: " + e.getMessage());
		}
	}
}
