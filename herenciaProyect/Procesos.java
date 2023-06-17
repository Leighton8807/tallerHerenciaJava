package herenciaProyect;

import java.util.Iterator;

import javax.swing.JOptionPane;

import clases.CitaMedica;
import clases.ModeloDatos;
import clases.Paciente;
import clases.empleado.EmpleadoEventual;
import clases.empleado.EmpleadoPlanilla;
import clases.empleado.Medico;

public class Procesos {
	ModeloDatos miModeloDatos;
	public Procesos() {
		miModeloDatos=new ModeloDatos();
		presentarMenuOpciones();
	}

	private void presentarMenuOpciones() {

		String menu = "MENU HOSPITAL \n\n";
		menu +="1. Registrar Paciente \n";
		menu +="2. Registrar Empleado \n";
		menu +="3. Registrar Cita Medica \n";
		menu +="4. Imprimir Informacion \n";
		menu +="5. Buscar por documento \n";
		menu +="6. Salir\n\n";
		menu +="Seleccion una opcion \n";
		
		int opcion=0;
		
		do {
			opcion=Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (opcion) {
			case 1: registrarPaciente(); break;
			case 2: registrarEmpleado(); break;
			case 3: registrarCitaMedica(); break;
			case 4: imprimirInformacion(); break;
			case 5: buscarPorDocumento(); break;
			case 6: System.out.println("El sistema ha terminado"); break;
			
			default: System.out.println("No esiste el codigo, verifique nuevamente"); break;
			
			}
		} while (opcion!=6);
	}

	private void buscarPorDocumento() {
		String buscar ="Buscar Por Documento \n\n";
		buscar+="1. Buscar paciente \n";
		buscar+="2. Buscar empleado eventual\n";
		buscar+="3. Buscar empleado planilla\n";
		buscar+="4. Buscar medico\n";
		buscar+="5. Volver al menu de inicio\n";
		
		int buscador = Integer.parseInt(JOptionPane.showInputDialog(buscar));
		
		switch (buscador) {
		case 1: miModeloDatos.buscarPacientePorDocumento(); break;
		case 2: miModeloDatos.buscarEmpleadoEventualPorDocumento(); break;
		case 3: miModeloDatos.buscarEmpleadoPlanillaPorDocumento(); break;
		case 4: miModeloDatos.buscarMedicoPorDocumento(); break;
		case 5: presentarMenuOpciones(); break;
			default: System.out.println("Ingrese una opcion valida"); break;
		}
	}

	private void registrarPaciente() {
		Paciente miPaciente = new Paciente();
		miPaciente.registrarDatos();
		
		miModeloDatos.registrarPersona(miPaciente);
		
	}

	private void registrarEmpleado() {
		String menuTipoEmpleado="Registro de empleado\n";
		menuTipoEmpleado+="1. Empleado Eventual\n";
		menuTipoEmpleado+="2. Empleado por Planilla\n";
		menuTipoEmpleado+="Seleccione el tipo de empleado a registrar\n";
		
		int tipoEmpleado=Integer.parseInt(JOptionPane.showInputDialog(menuTipoEmpleado));
		
		switch (tipoEmpleado) {
		case 1: 
			EmpleadoEventual miEmpleadoEventual = new EmpleadoEventual();
			miEmpleadoEventual.registrarDatos();
			miModeloDatos.registrarPersona(miEmpleadoEventual);
			break;
		case 2:
			String resp = JOptionPane.showInputDialog("Ingrese si, si es un medico, de lo contrario es otro tipo de empleado");
			if (resp.equalsIgnoreCase("si")) {
				Medico miMedico = new Medico();
				miMedico.registrarDatos();
				miModeloDatos.registrarPersona(miMedico);
			}else {
				EmpleadoPlanilla miEmpleadoPlanilla = new EmpleadoPlanilla();
				miEmpleadoPlanilla.registrarDatos();
				miModeloDatos.registrarPersona(miEmpleadoPlanilla);
			}
			break;

		default: System.out.println("El tipo de empleado no es valido, intentelo nuevamente");
			break;
		}
	}

	private void registrarCitaMedica() {
		String documentoPaciente= JOptionPane.showInputDialog("Ingrese el documento del paciente");
		
		Paciente pacienteEncontrado=miModeloDatos.consultarPacientePorDocumento(documentoPaciente);
		
		if (pacienteEncontrado!=null) {
			String nombreMedico=JOptionPane.showInputDialog("Ingrese el nombre del medico");
			Medico medicoEncontrado=miModeloDatos.consultarMedicoPorNombre(nombreMedico);
			
			if (medicoEncontrado!=null) {
				String servicios= JOptionPane.showInputDialog("Ingrese el servicio o motivo de la consulta");
				String fechaConsulta=JOptionPane.showInputDialog("Ingrese la fecha dela consulta");
				String horaConsulta=JOptionPane.showInputDialog("Ingrese la hora de la consulta");
				
				String lugar="La cita sera en el consultorio "+medicoEncontrado.getNumeroDeConsultorio();
				CitaMedica miCita=new CitaMedica(pacienteEncontrado, medicoEncontrado, servicios, fechaConsulta, horaConsulta, lugar);
				miModeloDatos.registrarCitaMedica(miCita);
			}else {
				System.out.println("El medico no se encuentra registrado en el sistema");
			}
			
		}else {
			System.out.println("El paciente no se encuentra registrado en el sistema");
		}
	}

	private void imprimirInformacion() {
		String menuImprimir="MENU IMPRESIONES\n\n";
		menuImprimir+="1. Listar Pacientes \n";
		menuImprimir+="2. Listar Empleados Eventuales\n";
		menuImprimir+="3. Listar Empleados por planilla\n";
		menuImprimir+="4. Listar Medicos \n";
		menuImprimir+="5. Listar Citas Programadas \n";
		menuImprimir+="Ingrese una opcion";
		
		System.out.println("***********************************************************************");
		
		int opcion=Integer.parseInt(JOptionPane.showInputDialog(menuImprimir));
		
		switch (opcion) {
		
		case 1: miModeloDatos.imprimirPacientes(); break;
		case 2: miModeloDatos.impriEmpleadosEventuales(); break;
		case 3: miModeloDatos.imprimirEmpleadosPorPlanilla(); break;
		case 4: miModeloDatos.imprimirMedicos(); break;
		case 5: miModeloDatos.imprimirCitasMedicasProgramadas(); break;

		default: System.out.println("No existe esa opcion");
			break;
		}
	}
	
	
	
	
}
