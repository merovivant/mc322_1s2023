import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePF extends Cliente{
    private Cpf cpf;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;
    
    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cpf, String genero,
    LocalDate dataLicenca, String educacao, LocalDate dataNascimento, String classeEconomica) {
        super(nome, endereco, listaVeiculos);
        this.cpf = new Cpf(cpf);
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
    }

    @Override
    public String toString() {
        return "ClientePF [nome=" + this.getNome() + ", endereco=" + this.getEndereco() + ", listaVeiculos=" + this.getListaVeiculos().toString() +", cpf=" + cpf.getStr() + ", genero=" + genero + ", dataLicenca=" + dataLicenca + ", educacao="
                + educacao + ", dataNascimento=" + dataNascimento + ", classeEconomica=" + classeEconomica + "]";
    }
    
    public Cpf getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = new Cpf(cpf);
    }

    public String getGenero() {
        return genero;
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
}
