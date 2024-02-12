public class Doctor {
    private int id_doctor;
    private String nombre_doctor;
    private String especialidad;
     
    public Doctor(int id_doctor, String nombre_doctor, String especialidad)
    {
        this.id_doctor = id_doctor;
        this.nombre_doctor = nombre_doctor;
        this.especialidad = especialidad;
    }

    // get set
    public int getIdDoc() {
        return id_doctor;
    }
    public void setIdDoc(int id_doctor) {
        this.id_doctor = id_doctor;
    }
    
    public String getNombreDoc() {
        return nombre_doctor;
    }
    public void setNombreDoc(String nombre_doctor) {
        this.nombre_doctor = nombre_doctor;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
