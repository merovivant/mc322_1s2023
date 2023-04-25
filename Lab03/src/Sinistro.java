import java.time.LocalDate;

public class Sinistro {
    private int id;
    private LocalDate data;
    private String endereco;
    private static int total_ocorrencias = 0;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        total_ocorrencias++;
        this.id = total_ocorrencias;
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Sinistro [ID=" + id + ", Data=" + data + ", Endereço=" + endereco + ", Seguradora=" + seguradora.getNome()
                + ", Veículo=" + veiculo.getMarca() + "-" +veiculo.getModelo() + ", Cliente=" + cliente.getNome() + "]";
    }
    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
