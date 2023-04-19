public class Documento {
    public enum Categoria{
        CPF (11),
        CNPJ (14);
        private final int tamanho;
        Categoria(int tamanho){
            this.tamanho = tamanho;
        }
    }

    private Categoria categoria;
    private String string;
    private long num;
    private String verif;

    // public Documento(String documento, Categoria categoria){
    //     this.string = documento;
    //     this.Validar();
    // }

    public Documento(Documento.Categoria categoria, String string) {
        this.categoria = categoria;
        this.string = string;
    }
    public String getString() {
        return string;
    }
    public void setString(String string) {
        this.string = string;
    }

    public long getNum() {
        return num;
    }
    private void setNum(String documento){
        this.num = Long.parseLong(documento);
    }
    
    public String getVerif() {
        return verif;
    }
    public void setVerif(){
        this.verif = string.substring(categoria.tamanho-2, categoria.tamanho);
    }
    
    private Boolean verifCPF(){
        int soma = 0, digito1, digito2;
        if(categoria == Categoria.CPF){
            for (int peso = 10, i = 0; i<=categoria.tamanho-3; i++, peso--) {
                soma += Character.getNumericValue(string.charAt(i)) * peso;
            }
            digito1 = (soma*10)%11;
            digito1 = digito1 == 10 ? 0:digito1;
            soma = 0;
            for (int peso=11, i=0; i<=categoria.tamanho-2; i++, peso--){
                soma += Character.getNumericValue(string.charAt(i)) * peso;
            }
            digito2 = (soma*10)%11;
            digito2 = digito2 == 10 ? 0:digito2;
            String verif_calculated = "" + digito1 + digito2;
            if (verif.equals(verif_calculated)){
                return true;
            } else{
                return false;
            }
        } else {
            
        }

    }

    private boolean Validar(){
        Tratar();
        this.setNum(string);
        if(string.length() == categoria.tamanho && num%11111111111L!=0){
            this.setVerif();
            if(conferirVerif()){
                return true;
            }
        } 
        return false;
    }

    private void Tratar(){
        this.setStr(string.replaceAll("[^0-9]",""));
    }
}
