package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import clases.empleado.EmpleadoEventual;
import clases.empleado.EmpleadoPlanilla;
import clases.empleado.Medico;

public class ModeloDatos {
	
	HashMap<String, Paciente>pacientesMap;
	HashMap<String, EmpleadoPlanilla> empleadosPlanillaMap;
	HashMap<String, EmpleadoEventual>empleadosEventualMap;
	HashMap<String, Medico>medicosMap;
	ArrayList<CitaMedica>citasList;
	
	public ModeloDatos() {
		pacientesMap=new HashMap<String, Paciente>();
		empleadosPlanillaMap=new HashMap<String, EmpleadoPlanilla>();
		medicosMap=new HashMap<String, Medico>();
		empleadosEventualMap= new HashMap<String, EmpleadoEventual>();
		citasList= new ArrayList<CitaMedica>();
	}
	
	public void registrarPersona(Paciente miPaciente) {
		pacientesMap.put( miPaciente.getNumeroDeDNI(), miPaciente);
		System.out.println("Se ha registrado el paciente "+miPaciente.getNombre()+" satisfactoriamente");
	}
	
	public void registrarPersona(EmpleadoPlanilla miEmpPlanilla) {
		empleadosPlanillaMap.put(miEmpPlanilla.getNumeroDeDNI(), miEmpPlanilla);
		System.out.println("Se ha registrado el empleado por planilla "+miEmpPlanilla.getNombre()+" satisfactoriamente");
	}
	
	public void registrarPersona(EmpleadoEventual miEmpleadoEventual) {
		empleadosEventualMap.put( miEmpleadoEventual.getNumeroDeDNI(), miEmpleadoEventual);
		System.out.println("Se ha registrado el empleado eventual "+miEmpleadoEventual.getNombre()+" satisfactoriamente");
	}
	
	public void registrarPersona(Medico miMedico) {
		medicosMap.put( miMedico.getNumeroDeDNI(), miMedico);
		System.out.println("Se ha registrado el Medico "+miMedico.getNombre()+" satisfactoriamente");
	}

	public void imprimirPacientes() {
		if (pacientesMap.size()>0) {
			String msj= "PACIENTES REGISTRADOS\n";
			Iterator<String> iterador=pacientesMap.keySet().iterator();
		
			while(iterador.hasNext()) {
			
				String clave = iterador.next();
				pacientesMap.get(clave).imprimirDatosPersona(msj);
			}
		
		}else {
			System.out.println("no hay datos registrados");
		}
	}

	public void impriEmpleadosEventuales() {
		if (empleadosEventualMap.size()>0) {
			String msj="EMPLEADOS EVENTUALES REGISTRADOS\n";
		
			for (String clave : empleadosEventualMap.keySet()) {
				empleadosEventualMap.get(clave).imprimirDatosPersona(msj);
			}
		}else {
			System.out.println("No hay datos registrados");
		}	
	}

	public void imprimirEmpleadosPorPlanilla() {
		if (empleadosPlanillaMap.size()>0) {
			String msj="EMPLEADOS POR PLANILLA REGISTRADOS\n";
		
			for (EmpleadoPlanilla empleadoPlanilla : empleadosPlanillaMap.values()) {
				empleadoPlanilla.imprimirDatosPersona(msj);
				System.out.println("************ MEDICOS **********");
				imprimirMedicos();
			}
		} else {
			System.out.println("No hay datos registrados");
		}
	}

	public void imprimirMedicos() {
		if (medicosMap.size()>0) {
			String msj="MEDICOS REGISTRADOS\n";
		
			for (Map.Entry<String, Medico> elemento : medicosMap.entrySet()) {
				
				elemento.getValue().imprimirDatosPersona(msj);
			}
		
		}else {
			System.out.println("No hay datos registrados");
		}
		
	}
	
	public Paciente consultarPacientePorDocumento(String documentoPaciente) {
		Paciente miPaciente =null;
		
		if (pacientesMap.containsKey(documentoPaciente)) {
			miPaciente=pacientesMap.get(documentoPaciente);
		}
		return miPaciente;
	}
	
	public Medico consultarMedicoPorNombre(String nombreMedico) {
		for (Medico medico : medicosMap.values()) {
			if (medico.getNombre().equalsIgnoreCase(nombreMedico)) {
				return medico;
			}
		}
		
		return null;
		
	}
	
	public void registrarCitaMedica(CitaMedica miCita) {
		citasList.add(miCita);
		System.out.println("Se ha registrado la cita exitosamente \n");
		System.out.println(miCita.informacionCitaMedica());
	}

	public void imprimirCitasMedicasProgramadas() {
		String msj="CITAS MEDICAS PROGRAMADAS\n";
		CitaMedica miCita=null;
		
		System.out.println(msj+"\n");
		
		if (citasList.size()>0) {
			for (int i = 0; i < citasList.size(); i++) {
				miCita=citasList.get(i);
				System.out.println(miCita.informacionCitaMedica());
			}
		}else {
			System.out.println("No existen citas programadas");
		}
		
	}

	public void buscarPacientePorDocumento() {
		// TODO Auto-generated method stub
		
	}

	public void buscarEmpleadoEventualPorDocumento() {
		// TODO Auto-generated method stub
		
	}

	public void buscarEmpleadoPlanillaPorDocumento() {
		// TODO Auto-generated method stub
		
	}

	public String buscarMedicoPorDocumento() {
		String documento = JOptionPane.showInputDialog("Ingrese el documento a buscar");
		for (String medico : medicosMap.keySet()) {
			if (medico.equals(documento)) {
				return medico;
			}else {
				System.out.println("No se encuentra registrado");
			}
			System.out.println();
		}
		return null;
		
	}
	
}
