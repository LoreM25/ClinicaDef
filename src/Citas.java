import java.time.LocalDateTime;

public class Citas {
    private int id_cita;
    private LocalDateTime fecha_hora;
    private String motivo;
    private Doctor nombre_doctor;
    private Paciente nombre_paciente;

    public Citas(int id_cita, LocalDateTime fecha_hora, String motivo, Doctor nombre_doctor, Paciente nombre_paciente) {
        this.id_cita = id_cita;
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.nombre_doctor = nombre_doctor;
        this.nombre_paciente = nombre_paciente;
    }

    // métodos get set
    public int getIdCita() {
        return id_cita;
    }

    public void setIdCita(int id_cita) {
        this.id_cita = id_cita;
    }

    public LocalDateTime getFH() {
        return fecha_hora;
    }

    public void setFH(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getMot() {
        return motivo;
    }

    public void setMot(String motivo) {
        this.motivo = motivo;
    }

    public Doctor getDoctor() {
        return nombre_doctor;
    }

    public Paciente getPaciente() {
        return nombre_paciente;
    }
}

