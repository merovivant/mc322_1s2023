public class Cpf {
    private String str;
    private long num;
    private String verif;

    public Cpf(String cpf){
        this.str = cpf;
        Validar();
    }

    public String getStr() {
        return str;
    }
    public void setStr(String str) {
        this.str = str;
    }

    public long getNum() {
        return num;
    }
    private void setNum(String cpf){
        this.num = Long.parseLong(cpf);
    }
    
    public String getVerif() {
        return verif;
    }
    private void setVerif(String verif){
        this.verif = verif;
    }
    
    private Boolean conferirVerif(){
        int soma = 0, digito1, digito2;
        for (int peso = 10, i = 0; i<=8; i++, peso--) {
             soma += Character.getNumericValue(str.charAt(i)) * peso;
        }
        digito1 = (soma*10)%11;
        digito1 = digito1 == 10 ? 0:digito1;
        soma = 0;
        for (int peso=11, i=0; i<=9; i++, peso--){
            soma += Character.getNumericValue(str.charAt(i)) * peso;
        }
        digito2 = (soma*10)%11;
        digito2 = digito2 == 10 ? 0:digito2;
        String verif_calculated = "" + digito1 + digito2;
        if (verif.equals(verif_calculated)){
            return true;
        } else{
            return false;
        }
    }

    private boolean Validar(){
        Tratar();
        this.setNum(str);
        if(str.length() == 11 && num%11111111111L!=0){
            this.setVerif(str.substring(9, 11));
            if(conferirVerif()){
                return true;
            }
        } 
        return false;
    }

    private void Tratar(){
        this.setStr(str.replaceAll("[^0-9]",""));
    }

}
