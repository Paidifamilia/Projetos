package School_Grades;

import java.util.Map;

public class aluno extends pessoa {
    private String matricula;
    private String curso;
    private Map<String, Double> notas; // Disciplina -> Nota

    public aluno(int id, String nome, String email, String matricula, String curso) {
        super(id, nome, email);
        this.matricula = matricula;
        this.curso = curso;
    }

    // Métodos específicos (Funções do Aluno)
    public double calcularMedia() {
        if (notas == null || notas.isEmpty()) return 0.0;
        double soma = 0;
        for (double nota : notas.values()) {
            soma += nota;
        }
        return soma / notas.size();
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Aluno: " + getNome() + " | Matrícula: " + matricula + " | Curso: " + curso);
    }

    // Getters e Setters para os atributos específicos...
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public String getCurso() {
        return curso;
    }
    
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public Map<String, Double> getNotas() {
        return notas;
    }
    
    public void setNotas(Map<String, Double> notas) {
        this.notas = notas;
    }
}