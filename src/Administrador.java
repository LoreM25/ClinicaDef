import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Administrador {
    private static final String DOCS_LISTA = "Doctores.csv";
    private static final String PACC_LISTA = "Pacientes.csv";
    private static final String CITAS_LISTA = "Citas.csv";

    private static ArrayList<Doctor> doctores = new ArrayList<>();
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static ArrayList<Citas> citas = new ArrayList<>();

    // métodos DOCTORES
    public static void loadDoctores() {
        try (BufferedReader br = new BufferedReader(new FileReader(DOCS_LISTA))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id_doctor = Integer.parseInt(parts[0]);
                String nombre_doctor = parts[1];
                String especialidad = parts[2];
                doctores.add(new Doctor(id_doctor, nombre_doctor, especialidad));
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los doctores: " + e.getMessage());
        }
    }

    public static void AltaDoctores() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DOCS_LISTA, true))) {
            for (Doctor doctor : doctores) {
                pw.println(doctor.getIdDoc() + "," + doctor.getNombreDoc() + "," + doctor.getEspecialidad());
            }
        } catch (IOException e) {
            System.err.println("Error al dar de alta los doctores: " + e.getMessage());
        }
    }

    public static ArrayList<Doctor> getDoctores() {
        return doctores;
    }

    // métodos PACIENTES
    public static void loadPacientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(PACC_LISTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split(",");
                int id_paciente = Integer.parseInt(parts[0]);
                String nombre_paciente = parts[1];
                pacientes.add(new Paciente(id_paciente, nombre_paciente));
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los pacientes: " + e.getMessage());
        }
    }

    public static void AltaPacientes() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PACC_LISTA, true))) {
            for (Paciente paciente : pacientes) {
                pw.println(paciente.getIdPac() + "," + paciente.getNomPac());
            }
        } catch (IOException e) {
            System.err.println("Error al dar de alta los pacientes: " + e.getMessage());
        }
    }

    public static ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    // métodos CITAS
    public static void crearCita(int id, LocalDateTime fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        boolean fechaOcupada = citas.stream()
                .anyMatch(cita -> cita.getDoctor().equals(doctor) && cita.getFH().equals(fechaHora));

        if (fechaOcupada) {
            System.out.println("El doctor ya tiene una cita programada en esa fecha y hora.");
            return;
        }

        Citas cita = new Citas(id, fechaHora, motivo, doctor, paciente);
        citas.add(cita);
        System.out.println("Cita creada correctamente.");

        guardarCita(cita);
    }

    private static void guardarCita(Citas cita) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CITAS_LISTA, true))) {
            writer.write(String.format("%d,%s,%s,%d,%d\n",
                    cita.getIdCita(), cita.getFH(), cita.getMot(), cita.getDoctor().getIdDoc(), cita.getPaciente().getIdPac()));
        } catch (IOException e) {
            System.err.println("Error al guardar la cita: " + e.getMessage());
        }
    }
}
