public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
    //     this.nome = nome;
    //     this.cpf = cpf;
    //     this.dataNascimento = dataNascimento;
    //     this.idade = idade;
    //     this.endereco = endereco;
    // }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean validarCPF(String cpf){
        cpf = cpf.replaceAll("[^0-9]","");
        if(cpf.length() == 11){
            long cpf_num = Long.parseLong(cpf);
            if (cpf_num%11111111111L!=0){
                String verificadores = cpf.substring(9, 11);
                if(verificadores.equals(calcularDigitosVerificadores(cpf))){
                    return true;
                }
            } 
        } 
        return false;
    }

    private String calcularDigitosVerificadores(String cpf){
        int soma = 0, digito1, digito2;
        for (int peso = 10, i = 0; i<=8; i++, peso--) {
             soma += Character.getNumericValue(cpf.charAt(i)) * peso;
        }
        digito1 = (soma*10)%11;
        digito1 = digito1 == 10 ? 0:digito1;
        soma = 0;
        for (int peso=11, i=0; i<=9; i++, peso--){
            soma += Character.getNumericValue(cpf.charAt(i)) * peso;
        }
        digito2 = (soma*10)%11;
        digito2 = digito2 == 10 ? 0:digito2;
        return "" + digito1 + digito2;
    }
}
