package School_Grades;

import java.util.List;

public class professor extends pessoa{
    private String codigoFuncional;
    private String departamento;
    private List<String> disciplinasLecionadas;

    public professor(int id, String nome, String email, String codigoFuncional, String departamento) {
        super(id, nome, email);
        this.codigoFuncional = codigoFuncional;
        this.departamento = departamento;
    }

    // Métodos específicos (Funções do Professor)
    public void listarDisciplinas() {
        System.out.println("Disciplinas de " + getNome() + ": " + String.join(", ", disciplinasLecionadas));
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Prof: " + getNome() + " | Depto: " + departamento);
    }

    // Getters e Setters para os atributos específicos...
    public String getCodigoFuncional() {
        return codigoFuncional;
    }
    
    public void setCodigoFuncional(String codigoFuncional) {
        this.codigoFuncional = codigoFuncional;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public List<String> getDisciplinasLecionadas() {
        return disciplinasLecionadas;
    }
    
    public void setDisciplinasLecionadas(List<String> disciplinasLecionadas) {
        this.disciplinasLecionadas = disciplinasLecionadas;
    }
}
