import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClientePJ extends Cliente{
    private Cnpj cnpj;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, LocalDate dataFundacao, int qtdeFuncionarios) {
        super(nome, endereco, listaVeiculos);
        setCnpj(cnpj);
        setDataFundacao(dataFundacao);
        setQtdeFuncionarios(qtdeFuncionarios);
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Cliente " + this.getNome() +
                "\n    Endereco: " + this.getEndereco() +
                "\n    CNPJ: " + this.getCnpj().getStr() +
                "\n    Data de Fundação: " + this.getDataFundacao().format(formatter) +
                "\n    Veículos Associados: " + this.getListaVeiculos();
    }
    
    public Cnpj getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = new Cnpj(cnpj);
    }
    
    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }
    // public double calculaScore(){
        
        // }
    }
    