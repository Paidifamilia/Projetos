package School_Grades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class consulta {
    private List<aluno> alunos;
    private List<professor> professores;

    public consulta() {
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
    }

    // Métodos para cadastrar dados de teste
    public void cadastrarAluno(aluno aluno) { 
        this.alunos.add(aluno); 
    }
    public void cadastrarProfessor(professor professor) { 
        this.professores.add(professor); 
    }

    // Métodos de Consulta de Alunos
    public Optional<aluno> buscarAlunoPorMatricula(String matricula) { 
        return alunos.stream() 
                .filter(a -> a.getMatricula().equalsIgnoreCase(matricula))
                .findFirst();
    }

    public List<aluno> listarAlunosPorCurso(String curso) { 
        return alunos.stream() 
                .filter(a -> a.getCurso().equalsIgnoreCase(curso))
                .collect(Collectors.toList());
    }

    public List<aluno> listarAlunosAlfabeticamente() {
        return alunos.stream()
                .sorted((a1, a2) -> a1.getNome().compareToIgnoreCase(a2.getNome()))
                .collect(Collectors.toList());
    }

    public Optional<aluno> buscarAlunoPorId(int id) {
        return alunos.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    // Métodos de Consulta de Professores
    public Optional<professor> buscarProfessorPorCodigo(String codigo) {
        return professores.stream() 
                .filter(p -> p.getCodigoFuncional().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public List<professor> buscarProfessoresPorDepartamento(String departamento) {
        return professores.stream() 
                .filter(p -> p.getDepartamento().equalsIgnoreCase(departamento))
                .collect(Collectors.toList());
    }

    public List<professor> listarProfessoresAlfabeticamente() {
        return professores.stream()
                .sorted((p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()))
                .collect(Collectors.toList());
    }
}