import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente{
    private Cnpj cnpj;
    private LocalDate dataFundacao;

    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, LocalDate dataFundacao) {
        super(nome, endereco, listaVeiculos);
        this.cnpj = new Cnpj(cnpj);
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "ClientePJ [Nome=" + this.getNome()+ ", endereco=" + this.getEndereco() + ", listaVeiculos=" + this.getListaVeiculos().toString() + ", CNPJ=" + cnpj.getStr() + ", Data de Fundação=" + dataFundacao + "]";
    }

    public Cnpj getCnpj() {
        return cnpj;
    }
    public void setCnpj(Cnpj cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
}
