public class Cpf {
    private String str;
    private long num;
    private String verif;

    public CPF(string cpf){
        this.str = cpf;
        this.Tratar();
        this.setVerif(str.substring(9, 11));
        this.setNum(str);
    }

    private setNum(String cpf){
        this.num = Long.parseLong(cpf);
    }

    private setVerif(String verif){
        this.verif = verif;
    }

    public String get(){
        return str;
    }

    public boolean Validar(){
        if(str.length() == 11 && num%11111111111L!=0){
            if(verificadores.equals(calcularDigitosVerificadores(cpf))){
                return true;
            }
        } 
        return false;
    }

    public void Tratar(){
        this.str.replaceAll("[^0-9]","");
        this.setNvalue(str);
    }

}
