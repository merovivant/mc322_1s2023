public class Sinistro {
    private int id;
    private String data;
    private String endereco;
    private static int total_ocorrencias = 0;

    public Sinistro(String data, String endereco) {
        total_ocorrencias++;
        this.id = total_ocorrencias;
        this.data = data;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Sinistro [id=" + id + ", data=" + data + ", endereco=" + endereco + "]";
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
