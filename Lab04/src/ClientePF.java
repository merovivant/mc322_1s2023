import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClientePF extends Cliente{
    private String cpf;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos,String cpf, String genero,
    String dataLicenca, String educacao, String dataNascimento, int rendaMensalPerCapita) {
        super(nome, endereco, listaVeiculos);
        setCPF(cpf);
        setGenero(genero);
        setDataLicenca(dataLicenca);
        setEducacao(educacao);
        setDataNascimento(dataNascimento);
        setClasseEconomica(rendaMensalPerCapita);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Cliente " + this.getNome() + 
                "\n___________________________________________" +
                "\n    Endereco: " + this.getEndereco() + 
                "\n    CPF: " + this.getCPF() + 
                "\n    Gênero: " + this.getGenero() + 
                "\n    Data da Licença: " + this.getDataLicenca().format(formatter)+ 
                "\n    Educaçao: " + this.getEducacao() + 
                "\n    Data de Nascimento: " + this.getDataNascimento().format(formatter) + 
                "\n    Classe Econômica: " + this.getClasseEconomica().toString()+
                "\n    Valor Seguro: R$" + this.getValorSeguro();
            }
    
    public String getCPF() {
        return cpf;
    }
    public void setCPF(String cpf) {
        if(Validacao.validaCPF(cpf)){
            this.cpf = cpf.replaceAll("[^0-9]", "");
        }
        else{
            throw new ExceptionCadastro("CPF inválido",2);
        }
    }

    public String getGenero() {
        switch (genero) {
            case "M":
                return "Masculino";
            case "F":
                return "Feminino";
            default:
                return "Outro";
        }
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(String dataLicenca) {
        if(Validacao.validaData(dataLicenca)){
            String[] partes = dataLicenca.split("[\\s\\.\\/]+");
            this.dataLicenca = LocalDate.of(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]), Integer.parseInt(partes[0]));
        }
        else{
            throw new ExceptionCadastro("Data inválida",5);
        }
    }

    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        if(Validacao.validaData(dataNascimento)){
            String[] partes = dataNascimento.split("[\\s\\.\\/]+");
            this.dataNascimento = LocalDate.of(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]), Integer.parseInt(partes[0]));
        }
        else{
            throw new ExceptionCadastro("Data inválida",7);
        }
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(int rendaMensalPerCapita) {
        if (rendaMensalPerCapita > 23850) {
            this.classeEconomica = "A1"; //A1: Classe alta
        } else if (rendaMensalPerCapita > 11925) {
            this.classeEconomica = "A2"; // A2: Classe média alta
        } else if (rendaMensalPerCapita > 5962.50) {
            this.classeEconomica = "B1"; // B1: Classe média
        } else if (rendaMensalPerCapita > 2981.25) {
            this.classeEconomica = "B2"; // B2: Classe média baixa
        } else if (rendaMensalPerCapita > 1490.62) {
            this.classeEconomica = "C1"; // C1: Classe baixa
        } else if (rendaMensalPerCapita > 745.31) {
            this.classeEconomica = "C2"; // C2: Classe muito baixa
        } else if (rendaMensalPerCapita > 372.66) {
            this.classeEconomica = "D"; // D: Classe mais baixa
        } else {
            this.classeEconomica = "E"; // E: Classe de extrema pobreza
        }
    }
    public double calculaScore(){
        LocalDate hoje = LocalDate.now();
        int idade = hoje.getYear() - this.getDataNascimento().getYear();
        if(idade>=18 && idade<30){
            return CalcSeguro.VALOR_BASE.valor * CalcSeguro.FATOR_18_30.valor * getListaVeiculos().size();
        }
        if(idade>=30 && idade<60){
            return CalcSeguro.VALOR_BASE.valor * CalcSeguro.FATOR_30_60.valor * getListaVeiculos().size();
        }
        if(idade>=60 && idade<90){
            return CalcSeguro.VALOR_BASE.valor * CalcSeguro.FATOR_60_90.valor * getListaVeiculos().size();
        }
        return CalcSeguro.VALOR_BASE.valor * getListaVeiculos().size();
    }

}
