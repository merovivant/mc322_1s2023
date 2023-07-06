import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Seguradora [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco
                + "]";
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    //Método que ira ser responsável pelo cadastro de clientes, recebendo por parâmetro o objeto já pronto do cliente que será cadastrado
    public Boolean cadastrarCliente(Cliente cliente){
        listaClientes.add(cliente);
        calcularPrecoSeguroCliente(cliente);
        return true;
    }
    //O método irá receber por pârametro o nome completo do cliente a ser removido e então irá buscar na lista de clientes se o mesmo consta:
    //Se sim, o cliente será removido e o método retornará True, senão False.
    public Boolean removerCliente(String cliente){
        for(int i=0; i<=listaClientes.size(); i++){
            if(cliente.equals(listaClientes.get(i).getNome())){
                listaClientes.remove(i);
                return true;        
            }
        }
        return false;
    }
    //O método receberá por parâmetro 1 ou 2:
    //Caso 1, irá listar os clientes cadastrados do tipo pessoa física.
    //Caso 2, irá listar os cliente cadatrados do tipo pessoa jurídica.
    public void listarClientes(String tipoCliente){
        switch(tipoCliente){
            case "1":
                for(Cliente c:listaClientes){
                    if(c instanceof ClientePF){
                        System.out.println("\n"+c.toString());
                    }
                }
                break;
            case "2":
                for(Cliente c:listaClientes){
                    if(c instanceof ClientePJ){
                        System.out.println("\n"+c.toString());
                    }
                }
                break;
            default:
                for(Cliente c:listaClientes){
                    System.out.println("\n"+c.toString());
                }
        }
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    //Método que irá receber o veículo, cliente e endereço e gerar um novo sinistro.
    public Boolean gerarSinistro(Veiculo veiculo, Cliente cliente, String endereco){
        Sinistro sinistro = new Sinistro(LocalDate.now(), endereco, this, veiculo, cliente);
        listaSinistros.add(sinistro);
        return true;
    }
    public int visualizarSinistro(Cliente cliente){
        int cont = 0;
        for(Sinistro s:listaSinistros){
            if(s.getCliente().equals(cliente)){
                System.out.println(s.toString());
                cont++;
            }
            
        }
        return cont;
    }
    public void listarSinistros(){
        for(Sinistro s:listaSinistros){
            System.out.println(s.toString());
        }
    }
    public void calcularPrecoSeguroCliente(Cliente cliente){
        cliente.setValorSeguro(cliente.calculaScore() * (1 + visualizarSinistro(cliente)));
    }
    public void calcularReceita(){
        double receita = 0;
        for(Cliente c:listaClientes){
            receita += c.getValorSeguro();
        }
        System.out.println("A receita da seguradora é de: R$"+receita);
    }


}
