public class Cnpj {
    private String str;
    private long num;
    private String verif;

    public Cnpj(String cnpj){
        this.str = cnpj;
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
    private void setNum(String cnpj){
        this.num = Long.parseLong(cnpj);
    }
    
    public String getVerif() {
        return verif;
    }
    private void setVerif(String verif){
        this.verif = verif;
    }
    
    private Boolean conferirVerif(){
        int soma = 0, digito1, digito2;
        int[] sequencia = {5,4,3,2,9,8,7,6,5,4,3,2};
        for (int i = 0; i<=11; i++) {
            soma += Character.getNumericValue(str.charAt(i)) * sequencia[i];
        }
        switch (soma%11) {
            case 0:
            case 1:
                digito1 = 0;
                break;
            default:
                digito1 = 11-(soma%11);
                break;
        }
        soma = 0;
        int[] sequencia2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};
        for (int i=0; i<=12; i++){
            soma += Character.getNumericValue(str.charAt(i)) * sequencia2[i];
        }
        switch (soma%11) {
            case 0:
            case 1:
                digito2 = 0;
                break;
            default:
                digito2 = 11-(soma%11);
                break;
        }
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
        if(str.length() == 14 && num%11111111111111L!=0){
            this.setVerif(str.substring(12, 14));
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
