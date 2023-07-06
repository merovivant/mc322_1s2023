public class ExceptionCadastro extends IllegalArgumentException {
    private int codigoMudar;
    public ExceptionCadastro(String mensagem, int codigoMudar){
        super(mensagem);
        this.codigoMudar = codigoMudar;
    }
    public int getCodigoMudar() {
        return codigoMudar;
    }
    public void setCodigoMudar(int codigoMudar) {
        this.codigoMudar = codigoMudar;
    }
    
}
