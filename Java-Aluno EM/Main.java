import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;

public class Main {

    // Interface que representa a DLL, usando JNA
    public interface ImpressoraDLL extends Library {

        // Caminho completo para a DLL
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\gabriel_portilho\\Desktop\\Java-Aluno EM\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );

        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);

        int FechaConexaoImpressora();

        int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);

        int Corte(int avanco);

        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);

        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);

        int AvancaPapel(int linhas);

        int StatusImpressora(int param);

        int AbreGavetaElgin();

        int AbreGaveta(int pino, int ti, int tf);

        int SinalSonoro(int qtd, int tempoInicio, int tempoFim);

        int ModoPagina();

        int LimpaBufferModoPagina();

        int ImprimeModoPagina();

        int ModoPadrao();

        int PosicaoImpressaoHorizontal(int posicao);

        int PosicaoImpressaoVertical(int posicao);

        int ImprimeXMLSAT(String dados, int param);

        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
    }

    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;
    private static final Scanner scanner = new Scanner(System.in);

    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static void fecharConexao() {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
            if (retorno == 0) {
                conexaoAberta = false;
                System.out.println("Conexão fechada com sucesso.");
            } else {
                System.out.println("Erro ao fechar conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está fechada.");
        }
    }


    public static void configurarConexao() {
        if (!conexaoAberta) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o tipo de conexão (ex: 1 para USB, 2 para serial, etc.): ");
            tipo = scanner.nextInt();

            System.out.println("Digite o modelo: ");
            modelo = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Digite a conexão: ");
            conexao = scanner.nextLine();

            System.out.println("Digite parâmetro: ");
            parametro = scanner.nextInt();

        }
    }

    public static void abreConexaoImpressora() {

        //sempre que for chamar uma funçao da biblioteca, usar como abaixo (ImpressoraDLL.INSTANCE.nomeDaFuncao)

        if (!conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está aberta.");
        }
    }

    //`ImpressaoTexto()`          ("Teste de impressao", 1, 4, 0);

    public static void impressaoTexto() {
        if (conexaoAberta) {

            ImpressoraDLL.INSTANCE.LimpaBufferModoPagina();


            String dados = "Teste de impressão: As aftas ardem e doem e as feridas idem";

            int posicao = 1;

            int estilo = 4;

            int tamanho = 0;

            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(dados, posicao, estilo, tamanho);

            if (retorno == 0) {
                System.out.println("Texto impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir texto. Código: " + retorno);
            }
        }
    }


    public static void impressaoQRCode() {
        if (conexaoAberta) {
            Scanner scanner = new Scanner(System.in);

            String dados = "Teste de impressao";

            int tamanho = 6;

            int nivelCorrecao = 4;

            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados, tamanho, nivelCorrecao);

            if (retorno == 0) {
                System.out.println("QR Code impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir QR Code. Código: " + retorno);
            }
        }
    }

    	// - `ImpressaoCodigoBarras()`    (8, "{A012345678912", 100, 2, 3)


    public static void impressaoCodigoBarras() {
        if(conexaoAberta){
            Scanner scanner = new Scanner(System.in);

            int tipo = 8;

            String dados = "{A012345678912";

            int altura = 100;

            int largura = 2;

            int HRI = 3;

            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(tipo, dados, altura, largura, HRI);

            if (retorno == 0) {
                System.out.println("Código de barras impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir código de barras. Código: " + retorno);
            }
        }
    }

    //	- `AbreGaveta()`                  (1, 5, 10)
    public static void abreGaveta() {
        if(conexaoAberta){
            Scanner scanner = new Scanner(System.in);

            int pino = 1;

            int ti = 5;

            int tf = 10;

            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(pino, ti, tf);

            if (retorno == 0) {
                System.out.println("Gaveta aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir a gaveta. Código: " + retorno);
            }
        }
    }

    	// - `AbreGavetaElgin()`            (1, 50, 50)
    public static void abreGavetaElgin() {
        if(conexaoAberta){
            int retorno = ImpressoraDLL.INSTANCE.AbreGavetaElgin();
        }
    }


    	// - `SinalSonoro()`				 (4,5,5)
    public static void sinalSonoro() {
        if(conexaoAberta){
            Scanner scanner = new Scanner(System.in);

            int qtd = 4;

            int tempoInicio	 = 5;

            int tempoFim = 5; //Multiplicar por 100

            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(qtd, tempoInicio, tempoFim);

            if (retorno == 0) {
                System.out.println("Sinal sonoro com sucesso.");
            } else {
                System.out.println("Erro ao sinalizar sonoramente. Código: " + retorno);
            }
        }
    }

    //	- `Corte()`						(2)  usar sempre após a impressao de algum documento
    public static void corte() {
        if(conexaoAberta){

            int avanco = 2;

            int retorno = ImpressoraDLL.INSTANCE.Corte(avanco);

            if (retorno == 0) {
                System.out.println("Corte realizado com sucesso.");
            } else {
                System.out.println("Erro cortar. Código: " + retorno);
            }
        }
    }

    	// - `AvancaPapel()`                 (2)  usar sempre após a impressao de algum documento
    public static void avancaPapel() {
        if(conexaoAberta){
            int linhas = 2;

            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);

            if (retorno == 0) {
                System.out.println("Papel avançado com sucesso.");
            } else {
                System.out.println("Erro ao avançar papel. Código: " + retorno);
            }

        }
    }

    public static void imprimeXMLSAT() {
        if(conexaoAberta){
            String dados = "path=C:\\Users\\gabriel_portilho\\Desktop\\Java-Aluno EM\\XMLSAT.xml";

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados, 0);

            if(retorno == 0) {
                System.out.println("Impressão Xl SAT cancelada com sucesso.");
            } else {
                System.out.println("Erro ao cancelar a impressão do XL SAT. Código: " + retorno);
            }
        }
    }

    public static void imprimeXMLCancelamentoSAT() {
        if(conexaoAberta){
            String dados = "path=C:\\Users\\gabriel_portilho\\Desktop\\Java-Aluno EM\\CANC_SAT.xml";

            String assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(dados, assQRCode, 0);

            if(retorno == 0) {
                System.out.println("Impressão Xl SAT cancelada com sucesso.");
            } else {
                System.out.println("Erro ao cancelar a impressão do XL SAT. Código: " + retorno);
            }

        }
    }



    //criar o restante das funçoes aqui!

	/* -
	- `ImpressaoQRCode()`            ("Teste de impressao", 6, 4)
	- `ImprimeXMLSAT()`
	- `ImprimeXMLCancelamentoSAT()`    (assQRCode = ;)
	*/


    public static void main(String[] args) {
        while (true) {
            System.out.println("\n*************************************************");
            System.out.println("**************** MENU IMPRESSORA *******************");
            System.out.println("*************************************************\n");

            System.out.println("1 - Configurar Conexao");
            System.out.println("2 - Abrir Conexao");
            System.out.println("3 - Impressao Texto");
            System.out.println("4 - Impressao QRCode");
            System.out.println("5 - Impressao Cod Barras");
            System.out.println("6 - Impressao XML SAT");
            System.out.println("7 - Impressao XML SAT");
            System.out.println("8 - Abrir Gaveta Elgin");
            System.out.println("9 - Abrir Gaveta");
            System.out.println("10 - Sinal Sonoro");
            System.out.println("0 - Fechar Conexao e Sair");

            String escolha = capturarEntrada("\nDigite a opção desejada: ");

            if(escolha.equals("0")) {
                fecharConexao();
                System.out.println("Programa encerrado.");
                break;
            }
            switch (escolha) {
                case "1":
                    //chamar as funçoes aqui
                    configurarConexao();
                    break;
                case "2":
                    abreConexaoImpressora();
                    break;
                case "3":
                    impressaoTexto();
                    ImpressoraDLL.INSTANCE.Corte(2);
                    ImpressoraDLL.INSTANCE.AvancaPapel(2);
                    break;
                case "4":
                    impressaoQRCode();
                    ImpressoraDLL.INSTANCE.Corte(2);
                    ImpressoraDLL.INSTANCE.AvancaPapel(2);
                    break;
                case "5":
                    impressaoCodigoBarras();
                    ImpressoraDLL.INSTANCE.Corte(2);
                    ImpressoraDLL.INSTANCE.AvancaPapel(2);

                    break;
                case "6":
                    imprimeXMLSAT();
                    ImpressoraDLL.INSTANCE.Corte(2);
                    ImpressoraDLL.INSTANCE.AvancaPapel(2);

                    break;
                case "7":
                    imprimeXMLCancelamentoSAT();
                    ImpressoraDLL.INSTANCE.Corte(2);
                    ImpressoraDLL.INSTANCE.AvancaPapel(2);

                    break;
                case "8":
                    abreGavetaElgin();
                    break;
                case "9":
                    abreGaveta();
                    break;
                case "10":
                    sinalSonoro();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }

        scanner.close();
    }

    private static String lerArquivoComoString(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] data = fis.readAllBytes();
        fis.close();
        return new String(data, StandardCharsets.UTF_8);
    }

}