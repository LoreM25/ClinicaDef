import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    // Archivo _ administradores
    private static final String ARCHIVO = "C:\\Users\\lore\\Documents\\Evidencia CompJava\\Clinicaint1\\Adminsitradores.txt";

    public static void main(String[] args) {
        Map<String, String> usuarios = load();

        Administrador.loadDoctores();
        Administrador.loadPacientes();

        Scanner sc = new Scanner(System.in);
        boolean inicioSesion = false;
        // Inicio de sesión.
        while(!inicioSesion) {
            System.out.print("Usuario: ");
            String usuario = sc.nextLine();

            System.out.print("Contraseña: ");
            String contr = sc.nextLine();


            if (autentUsuario(usuario, contr, usuarios)) {
                inicioSesion = true;
                System.out.println("Bienvenido.");
                int op;
                do {
                    System.out.println("\nSeleccione una opción del menú: ");
                    System.out.println("-----------------------");
                    System.out.println("1. Dar de alta a doctor.");
                    System.out.println("2. Dar de alta a paciente.");
                    System.out.println("3. Programar cita.");
                    System.out.println("4. Salir.");
                    op = sc.nextInt();
                    sc.nextLine(); // Consume la nueva línea después del nextInt()
                    switch (op) {
                        case 1:
                            // método.
                            altaDoctor(sc);
                            break;
                        case 2:
                            // método. Dar de alta PACIENTE
                            altaPaciente(sc);
                            break;
                        case 3:
                            // método. Crear CITA
                            crearCita(sc);
                            break;
                        default:
                            break;
                    }
                } while (op != 4);
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos, intente otra vez.");
            }
        }
        sc.close();
    }

    // Método para autenticar.
    private static boolean autentUsuario(String usuario, String contr, Map<String, String> usuarios) {
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contr);
    }

    // Cargar archivo
    private static Map<String, String> load() {
        Map<String, String> usuarios = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir usuario y contraseña
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String usuario = partes[0].trim();
                    String contraseña = partes[1].trim();
                    usuarios.put(usuario, contraseña);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
        return usuarios;
    }

    private static void altaDoctor(Scanner sc) {
        System.out.print("Id: ");
        int idDoctor = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea después del nextInt()

        System.out.print("Nombre doctor: ");
        String nombreDoctor = sc.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();

        Administrador.getDoctores().add(new Doctor(idDoctor, nombreDoctor, especialidad));
        Administrador.AltaDoctores();
    }

    private static void altaPaciente(Scanner sc) {
        System.out.print("Id: ");
        int idPaciente = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea después del nextInt()

        System.out.print("Nombre paciente: ");
        String nombrePaciente = sc.nextLine();

        Administrador.getPacientes().add(new Paciente(idPaciente, nombrePaciente));
        Administrador.AltaPacientes();
    }

    private static void crearCita(Scanner sc) {
        System.out.print("Número de cita: ");
        int idCita = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea después del nextInt()

        System.out.print("Motivo de la cita: ");
        String motivo = sc.nextLine();

        System.out.print("Nombre del doctor: ");
        String nombreDoctor = sc.nextLine();
        Doctor doctor = buscarDoctor(nombreDoctor);

        System.out.print("Nombre del paciente: ");
        String nombrePaciente = sc.nextLine();
        Paciente paciente = buscarPaciente(nombrePaciente);

        System.out.print("Fecha y hora de la cita (YYYY-MM-DD HH:mm): ");
        String fechaHoraStr = sc.nextLine();
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Administrador.crearCita(idCita, fechaHora, motivo, doctor, paciente);
    }

    private static Doctor buscarDoctor(String nombreDoctor) {
        for (Doctor doctor : Administrador.getDoctores()) {
            if (nombreDoctor.equalsIgnoreCase(doctor.getNombreDoc())) {
                return doctor;
            }
        }
        System.out.println("Doctor no encontrado.");
        return null;
    }

    private static Paciente buscarPaciente(String nombrePaciente) {
        for (Paciente paciente : Administrador.getPacientes()) {
            if (nombrePaciente.equalsIgnoreCase(paciente.getNomPac())) {
                return paciente;
            }
        }
        System.out.println("Paciente no encontrado.");
        return null;
    }
}
