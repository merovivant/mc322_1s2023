import java.util.ArrayList;

public abstract class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private double valorSeguro;

    public abstract String toString();

    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos) {
        setNome(nome);
        setEndereco(endereco);
        setListaVeiculos(listaVeiculos);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getListaVeiculos() {
        String lista="";
        for(Veiculo veiculo : listaVeiculos){
            lista+="\n         "+veiculo.toString();
        }
        return lista;
    }
    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }   

    // public double calculaScore(){

    // }
}