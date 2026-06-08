package School_Grades;

public abstract class pessoa {
    private int id;
    private String nome;
    private String email;

    public pessoa(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public int getId() { 
        return id; 
    }
    public String getNome() {
         return nome; 
    }
    public String getEmail() { 
        return email; 
    }
    
    public abstract void exibirPerfil();
}
