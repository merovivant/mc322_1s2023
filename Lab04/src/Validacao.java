import java.time.LocalDate;

public class Validacao {
    public static boolean validaCPF(String cpf_s){
        cpf_s = cpf_s.replaceAll("[^0-9]","");
        Long cpf_n = Long.parseLong(cpf_s);
        if(cpf_s.length() == 11 && cpf_n%11111111111L!=0){
            int verif = Integer.parseInt(cpf_s.substring(9, 11));
            if(verifCPF(cpf_s,verif)){
                return true;
            }
        }
        return false;
    }

    public static boolean validaCNPJ(String cnpf_s){
        cnpf_s = cnpf_s.replaceAll("[^0-9]","");
        Long cnpf_n = Long.parseLong(cnpf_s);
        if(cnpf_s.length() == 14 && cnpf_n%11111111111111L!=0){
            int verif = Integer.parseInt(cnpf_s.substring(12, 14));
            if(verifCNPJ(cnpf_s,verif)){
                return true;
            }
        }
        return false;
    }
    
    private static Boolean verifCPF(String cpf, int verif){
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
        if (verif == digito1*10+digito2){
            return true;
        } else{
            return false;
        }
    }

    private static Boolean verifCNPJ(String cnpj, int verif){
        int soma = 0, digito1, digito2;
        int[] sequencia = {5,4,3,2,9,8,7,6,5,4,3,2};
        for (int i = 0; i<=11; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * sequencia[i];
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
            soma += Character.getNumericValue(cnpj.charAt(i)) * sequencia2[i];
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
        if (verif == digito1*10+digito2){
            return true;
        } else{
            return false;
        }
    }

    public static Boolean validaNome(String nome){
        String aux = nome.replaceAll("[^0-9]","");
        if(aux.length() == 0 && nome.length() > 0){
            return true;
        }
        return false;

    }

    public static Boolean validaData(String data){
        LocalDate dataAtual = LocalDate.now();
        int anoAtual = dataAtual.getYear();

        String[] partes = data.split("[\\s\\.\\/]+");
        if(partes.length == 3){
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int ano = Integer.parseInt(partes[2]);
            if(ano > 0 && ano <= anoAtual && mes > 0 && mes < 13 && dia > 0){
                if(mes == 2){
                    if(ano%4 == 0){
                        if(dia < 30){
                            return true;
                        }
                    } else{
                        if(dia < 29){
                            return true;
                        }
                    }
                } else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
                    if(dia < 31){
                        return true;
                    }
                } else{
                    if(dia < 32){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
