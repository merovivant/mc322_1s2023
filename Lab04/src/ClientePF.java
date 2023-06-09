import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClientePF extends Cliente{
    private Cpf cpf;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos,String cpf, String genero,
    LocalDate dataLicenca, String educacao, LocalDate dataNascimento, String classeEconomica) {
        super(nome, endereco, listaVeiculos);
        setCpf(cpf);
        this.cpf = new Cpf(cpf);
        setGenero(genero);
        setDataLicenca(dataLicenca);
        setEducacao(educacao);
        setDataNascimento(dataNascimento);
        setClasseEconomica(classeEconomica);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Cliente " + this.getNome() + 
                "\n    Endereco: " + this.getEndereco() + 
                "\n    CPF: " + cpf.getStr() + 
                "\n    Gênero: " + this.getGenero() + 
                "\n    Data da Licença: " + this.getDataLicenca().format(formatter)+ 
                "\n    Educação: " + this.getEducacao() + 
                "\n    Data de Nascimento: " + this.getDataNascimento().format(formatter) + 
                "\n    Classe Econômica: " + this.getClasseEconomica().toString() +
                "\n    Veículos Associados: " + this.getListaVeiculos();
            }
    
    public Cpf getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = new Cpf(cpf);
    }

    public String getGenero() {
        switch (genero) {
            case "M":
                return "Masculino";
            case "F":
                return "Feminino";
            default:
                return "Outro";
        }
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }



    // public double calculaScore(){

    // }

}
