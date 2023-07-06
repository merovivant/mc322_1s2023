import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClientePJ extends Cliente{
    private String cnpj;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, String dataFundacao, int qtdeFuncionarios) {
        super(nome, endereco, listaVeiculos);
        setCNPJ(cnpj);
        setDataFundacao(dataFundacao);
        setQtdeFuncionarios(qtdeFuncionarios);
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Cliente " + this.getNome() +
                "\n___________________________________________" +
                "\n    Endereco: " + this.getEndereco() +
                "\n    CNPJ: " + this.getCNPJ() +
                "\n    Data de Fundaçao: " + this.getDataFundacao().format(formatter)+
                "\n    Valor Seguro: R$" + this.getValorSeguro();
    }
    
    public String getCNPJ() {
        return cnpj;
    }
    public void setCNPJ(String cnpj) {
        if(Validacao.validaCNPJ(cnpj)){
            this.cnpj = cnpj.replaceAll("[^0-9]", "");
        }
        else{
            throw new ExceptionCadastro("CNPJ inválido", 2);
        }
    }
    
    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(String dataFundacao) {
        if(Validacao.validaData(dataFundacao)){
            String[] partes = dataFundacao.split("[\\s\\.\\/]+");
            this.dataFundacao = LocalDate.of(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]), Integer.parseInt(partes[0]));
        }
        else {
            throw new ExceptionCadastro("Data inválida", 4);
        }
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }
    public double calculaScore(){
        return CalcSeguro.VALOR_BASE.valor * ((1+this.getQtdeFuncionarios())/100) * getListaVeiculos().size();
    }
}
    