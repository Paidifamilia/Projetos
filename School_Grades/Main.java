package School_Grades;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        consulta sistema = new consulta();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE CONSULTA ACADÊMICA ===");
            System.out.println("1. Cadastrar Aluno");
            Thread.sleep(1000);
            System.out.println("2. Cadastrar Professor");
            Thread.sleep(200);
            System.out.println("3. Listar Alunos (Ordem Alfabética)");
            Thread.sleep(200);
            System.out.println("4. Listar Professores (Ordem Alfabética)");
            Thread.sleep(200);
            System.out.println("5. Buscar Aluno por ID");
            Thread.sleep(200);
            System.out.println("0. Sair");
            Thread.sleep(200);
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    String continuarA;
                    do {
                        System.out.println("\n--- Novo Cadastro de Aluno ---");
                        System.out.print("ID do Aluno (Banco de dados): ");
                        int idA = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome: ");
                        String nomeA = scanner.nextLine();
                        System.out.print("Email: ");
                        String emailA = scanner.nextLine();
                        System.out.print("RA (Matrícula): ");
                        String ra = scanner.nextLine();
                        System.out.print("Curso: ");
                        String cursoA = scanner.nextLine();

                        sistema.cadastrarAluno(new aluno(idA, nomeA, emailA, ra, cursoA));
                        System.out.println("Aluno cadastrado com sucesso!");
                        Thread.sleep(300);

                        // Validação da continuidade
                        do {
                            System.out.print("Deseja cadastrar mais? (S/N): ");
                            continuarA = scanner.nextLine().trim();
                        } while (!continuarA.equalsIgnoreCase("S") && !continuarA.equalsIgnoreCase("N"));

                    } while (continuarA.equalsIgnoreCase("S"));
                    break;

                case 2:
                    String continuarP;
                    do {
                        System.out.println("\n--- Novo Cadastro de Professor ---");
                        System.out.print("ID do Professor (Banco de dados): ");
                        int idP = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome: ");
                        String nomeP = scanner.nextLine();
                        System.out.print("Email: ");
                        String emailP = scanner.nextLine();
                        System.out.print("Código Funcional: ");
                        String codP = scanner.nextLine();
                        System.out.print("Departamento: ");
                        String deptoP = scanner.nextLine();

                        sistema.cadastrarProfessor(new professor(idP, nomeP, emailP, codP, deptoP));
                        System.out.println("Professor cadastrado com sucesso!");
                        Thread.sleep(300);

                        // Validação da continuidade
                        do {
                            System.out.print("Deseja cadastrar mais? (S/N): ");
                            continuarP = scanner.nextLine().trim();
                        } while (!continuarP.equalsIgnoreCase("S") && !continuarP.equalsIgnoreCase("N"));

                    } while (continuarP.equalsIgnoreCase("S"));
                    break;

                case 3:
                    System.out.println("\n--- Alunos em Ordem Alfabética ---");
                    List<aluno> alunosOrdenados = sistema.listarAlunosAlfabeticamente();
                    if (alunosOrdenados.isEmpty()) System.out.println("Nenhum aluno cadastrado.");
                    else alunosOrdenados.forEach(aluno::exibirPerfil);
                    break;

                case 4:
                    System.out.println("\n--- Professores em Ordem Alfabética ---");
                    List<professor> profsOrdenados = sistema.listarProfessoresAlfabeticamente();
                    if (profsOrdenados.isEmpty()) System.out.println("Nenhum professor cadastrado.");
                    else profsOrdenados.forEach(professor::exibirPerfil);
                    break;

                case 5:
                    System.out.print("Digite o RA (Matrícula) do aluno que deseja buscar: ");
                    String raBusca = scanner.nextLine().trim();
                    
                    Optional<aluno> alunoRA = sistema.buscarAlunoPorMatricula(raBusca);
                    System.out.println("\n--- Resultado da Busca ---");
                    alunoRA.ifPresentOrElse(aluno::exibirPerfil,  () -> System.out.println("Aluno com o RA '" + raBusca + "' não foi encontrado."));
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}