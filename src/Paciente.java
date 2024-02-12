public class Paciente {
    private int id_paciente;
    private String nombre_paciente;

    public Paciente(int id_paciente, String nombre_paciente){
        this.id_paciente = id_paciente;
        this.nombre_paciente = nombre_paciente;
    }


    // get set
    public int getIdPac(){
        return id_paciente;
    }
    public void setIdPac(int id_paciente){
        this.id_paciente = id_paciente;
    }

    public String getNomPac(){
        return nombre_paciente;
    }
    public void setNombPac(String nombre_paciente){
        this.nombre_paciente = nombre_paciente;
    }

}