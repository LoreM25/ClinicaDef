import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
// actualización Main
    public static void main(String[] args) throws Exception {
        verificarArchivos();

        final Map<String,String> usuarios = new HashMap<>();
        {
            usuarios.put("admin1", "1234");
            usuarios.put("admin2", "5678");
        }

        Administrador.loadDoctores();
        Administrador.loadPacientes();

        Scanner sc = new Scanner(System.in);
        boolean InicioSesion = false;
        // Inicio de sesión.
        while(!InicioSesion) {
            System.out.print("Usuario: ");
            String usuario = sc.nextLine();

            System.out.print("Contraseña: ");
            String contr = sc.nextLine();


            if (AutentUsuario(usuario, contr, usuarios)) {
                InicioSesion = true;
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
                    switch (op) {
                        case 1:
                            // método.
                            System.out.print("Id doctor: ");
                            int id_doctor = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Nombre doctor: ");
                            String nombre_doctor = sc.nextLine();

                            System.out.print("Especialida: ");
                            String especialidad = sc.nextLine();

                            // sistemaCitas.getDoctores().add(new Doctor(id, nombreCompleto, especialidad));
                            Administrador.getDoctores().add(new Doctor(id_doctor, nombre_doctor, especialidad));
                            Administrador.AltaDoctores();
                            System.out.println("Se han guardado los datos correctamente");
                            break;
                        case 2:
                            // método. Dar de alta PACIENTE
                            System.out.print("Id paciente: ");
                            int id_paciente = sc.nextInt();
                            sc.nextLine();
                        
                            System.out.print("Nombre paciente: ");
                            String nombre_paciente = sc.nextLine();

                            Administrador.getPacientes().add(new Paciente(id_paciente, nombre_paciente));
                            Administrador.AltaPacientes();
                            break;
                        
                        case 3:
                            // método. Crear CITA
                            System.out.print("Número de cita: ");
                            int id_cita = sc.nextInt();
                            sc.nextLine(); // Consumir la nueva línea
                            
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
                            
                            Administrador.crearCita(id_cita, fechaHora, motivo, doctor, paciente);
                            System.out.println("Se han guardado los datos correctamente");
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

    // autenticar inicio sesión.
    private static boolean AutentUsuario(String usuario, String contr, Map<String, String> usuarios) {
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contr);
    }
    
    // métodos para crear cita.
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

    // verificar archivos csv
    private static void verificarArchivos(){
        if (!archivoExiste(Administrador.Docs_Lista)) {
            regenerarArchivo(Administrador.Docs_Lista);
        }
        if (!archivoExiste(Administrador.PacC_Lista)) {
            regenerarArchivo(Administrador.PacC_Lista);
        }
        if (!archivoExiste(Administrador.Citas_Lista)) {
            regenerarArchivo(Administrador.Citas_Lista);
        }
    }

    private static boolean archivoExiste(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    private static void regenerarArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            archivo.createNewFile();
            System.out.println("Se ha regenerado el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al regenerar el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
}
