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
                "C:\\Users\\gscuo\\OneDrive\\Desktop\\Projetos 2025",
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
        if (conexaoAberta) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o tipo de conexão (ex: 1 para USB, 2 para serial, etc.): ");
            tipo = scanner.nextInt();

            System.out.println("Digite o modelo: ");
            modelo = scanner.nextLine();

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

    public static void impressaoTexto() {
        if (conexaoAberta) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o dados: ");
            String dados = scanner.nextLine();

            System.out.println("Digite a posição (0 - Esquerda / 1 - Centro / 2 - Direita): ");
            int posicao = scanner.nextInt();

            System.out.println("Digite o estilo (0 - Fonte A / 1 - Fonte B / 2 - Sublinhado / 4 - Modo reverso / 8 - Negrito): ");
            int estilo = scanner.nextInt();

            System.out.println("Digite o tamanho (0 - 1x na altura e largura / 1 - 2x na altura / 2 - 3x na altura / 3 - 4x na altura / 4 - 5x na altura / 5 - 6x na altura / 6 - 7x na altura / 7 - 8x na altura / 16 - 2x na largura / 32 - 3x na largura / 48 - 4x na largura / 64 - 5x na largura / 80 - 6x na largura / 96 - 7x na largura / 112 - 8x na largura): ");
            int tamanho = scanner.nextInt();

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

            System.out.println("Digite o dados: ");
            String dados = scanner.nextLine();

            System.out.println("Digite o tamanho (Valores entre 1 e 6): ");
            int tamanho = scanner.nextInt();

            System.out.println("Digite o nível de correção a ser configurado para o QRCode (1 - 7% / 2 - 15% / 3 - 25% / 4 - 30%): ");
            int nivelCorrecao = scanner.nextInt();

            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados, tamanho, nivelCorrecao);

            if (retorno == 0) {
                System.out.println("QR Code impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir QR Code. Código: " + retorno);
            }
        }
    }

    public static void impressaoCodigoBarras() {
        if(conexaoAberta){
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o modelo (0 - UPC-A / 1 - UPC-E / 2 - JAN13 ou EAN13 / 3 - JAN8 ou EAN8 / 4 - CODE 39 / 5 - ITF / 6 - CODE BAR / 7 - CODE 93 / 8 - CODE 128): ");
            int tipo = scanner.nextInt();

            System.out.println("Digite os dados que compõe o código : ");
            String dados = scanner.nextLine();

            System.out.println("Digite a altura do código de barras (Valor de 1 até 255): ");
            int altura = scanner.nextInt();

            System.out.println("Digite a largura do código de barras (Valor de 1 até 6): ");
            int largura = scanner.nextInt();

            System.out.println("Digite a posição de impressão do conteúdo do código de barras (1 - Acima do código / 2 - Abaixo do código / 3 - Ambos / 4 - Não impresso): ");
            int HRI = scanner.nextInt();

            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(tipo, dados, altura, largura, HRI);

            if (retorno == 0) {
                System.out.println("Código de barras impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir código de barras. Código: " + retorno);
            }
        }
    }

    public static void abreGaveta() {
        if(conexaoAberta){
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o pino (0 ou 1): ");
            int pino = scanner.nextInt();

            System.out.println("Digite o tempo de inicialização do pulso (1 até 255): ");
            int ti = scanner.nextInt();

            System.out.println("Digite desativação do pulso. QRCode (1 até 255): ");
            int tf = scanner.nextInt();

            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(pino, ti, tf);

            if (retorno == 0) {
                System.out.println("Gaveta aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir a gaveta. Código: " + retorno);
            }
        }
    }

    public static void abreGavetaElgin() {
        if(conexaoAberta){
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o pino (0 ou 1): ");
            int pino = scanner.nextInt();

            System.out.println("Digite o tempo de inicialização do pulso (1 até 255): ");
            int ti = scanner.nextInt();

            System.out.println("Digite desativação do pulso. QRCode (1 até 255): ");
            int tf = scanner.nextInt();

            int retorno = ImpressoraDLL.INSTANCE.AbreGavetaElgin();
        }
    }

    public static void sinalSonoro() {
        if(conexaoAberta){
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite a quantidade de sinais emitidos (valor deve ser entre 1 e 63): ");
            int qtd = scanner.nextInt();

            System.out.println("Digite o tempo em que o sinal deve ficar ativo: ");
            int tempoInicio	 = scanner.nextInt();

            System.out.println("Digite o tempo entre um sinal e outro (tempo deve ser de 1 à 25): ");
            int tempoFim = scanner.nextInt(); //Multiplicar por 100

            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(qtd, tempoInicio, tempoFim);

            if (retorno == 0) {
                System.out.println("Sinal sonoro com sucesso.");
            } else {
                System.out.println("Erro ao sinalizar sonoramente. Código: " + retorno);
            }
        }
    }

    public static void corte() {
        if(conexaoAberta){
            System.out.println("Digite o quanto o papel deve avançar antes do corte: ");
            int avanco = scanner.nextInt();

            int retorno = ImpressoraDLL.INSTANCE.Corte(avanco);

            if (retorno == 0) {
                System.out.println("Corte realizado com sucesso.");
            } else {
                System.out.println("Erro cortar. Código: " + retorno);
            }
        }
    }

    public static void avancaPapel() {
        if(conexaoAberta){
            System.out.println("Digite o quantas linhas devem avançar: ");
            int linhas = scanner.nextInt();

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
            System.out.println("Digite os dados (Conteúdo do XML de retorno da venda do SAT ou Caminho para arquivo com dados do SAT prefixado com path=, ex:path=C:/Resposta SAT/CFe35210130197161000935590008544130085892910578.xml): ");
            String dados = scanner.nextLine();

            System.out.println("0 - Impressão do logo no cabeçalho / 1 - Extrato reduzido / 2 - Cupom em ambiente de teste / 6 - Registro de item com desconto ou acréscimo e variações no grupo totais / 7 - Imprime usando novo layout / 8 - Ativa separadores no novo layout");
            int param = scanner.nextInt();

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados, param);

            if (retorno == 0) {
                System.out.println("Xl SAT impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir XL SAT. Código: " + retorno);
            }
        }
    }

    public static void imprimeXMLCancelamentoSAT() {
        if(conexaoAberta){
            System.out.println("Digite os dados (Conteúdo do XML de retorno da venda do SAT ou Caminho para arquivo com dados do SAT prefixado com path=, ex:path=C:/Resposta SAT/CFe35210130197161000935590008544130085892910578.xml): ");
            String dados = scanner.nextLine();

            System.out.println("Digite os dados a assinatura do QRCode retornado na operação de Venda: ");
            String assQRCode = scanner.nextLine();

            System.out.println("0 - Impressão do logo no cabeçalho / 6 - Imprime usando novo layout / 7 - Ativa separadores no novo layout");
            int param = scanner.nextInt();

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(dados, assQRCode, param);

            if (retorno == 0) {
                System.out.println("Impressão Xl SAT cancelada com sucesso.");
            } else {
                System.out.println("Erro ao cancelar a impressão do XL SAT. Código: " + retorno);
            }

        }
    }



    //criar o restante das funçoes aqui!

	/* - `ImpressaoTexto()`          ("Teste de impressao", 1, 4, 0);
	- `Corte()`						(2)  usar sempre após a impressao de algum documento
	- `ImpressaoQRCode()`            ("Teste de impressao", 6, 4)
	- `ImpressaoCodigoBarras()`    (8, "{A012345678912", 100, 2, 3)
	- `AvancaPapel()`                 (2)  usar sempre após a impressao de algum documento
	- `AbreGavetaElgin()`            (1, 50, 50)
	- `AbreGaveta()`                  (1, 5, 10)
	- `SinalSonoro()`				 (4,5,5)
	- `ImprimeXMLSAT()`
	- `ImprimeXMLCancelamentoSAT()`    (assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";)
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
            System.out.println("8 - Impressao XML SAT");
            System.out.println("9 - Impressao XML Canc SAT");
            System.out.println("10 - Abrir Gaveta Elgin");
            System.out.println("11 - Abrir Gaveta");
            System.out.println("12 - Sinal Sonoro");
            System.out.println("13 - Corte");
            System.out.println("14 - Avançar papel");



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
                    break;
                case "4":
                    impressaoQRCode();
                    break;
                case "5":
                    impressaoCodigoBarras();
                    break;
                case "6":
                    if (conexaoAberta) {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File(".")); // Diretório atual do programa
                        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos XML", "xml"));

                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();

                            try {
                                String conteudoXML = lerArquivoComoString(path);
                                int retImpXMLSAT = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(conteudoXML, 0);
                                ImpressoraDLL.INSTANCE.Corte(5);
                                System.out.println(retImpXMLSAT == 0 ? "Impressão de XML realizada" : "Erro ao realizar a impressão do XML SAT! Retorno: " + retImpXMLSAT);
                            } catch (IOException e) {
                                System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Nenhum arquivo selecionado.");
                        }
                    } else {
                        System.out.println("Erro: Conexão não está aberta.");
                    }
                    break;

                case "7":
                    if (conexaoAberta) {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File(".")); // Diretório atual do programa
                        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos XML", "xml"));
                        String assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String path = selectedFile.getAbsolutePath();

                            try {
                                String conteudoXML = lerArquivoComoString(path);
                                int retImpCanXMLSAT = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(conteudoXML, assQRCode, 0);
                                ImpressoraDLL.INSTANCE.Corte(5);
                                System.out.println(retImpCanXMLSAT == 0 ? "Impressão de XML de Cancelamento realizada" : "Erro ao realizar a impressão do XML de Cancelamento SAT! Retorno: " + retImpCanXMLSAT);
                            } catch (IOException e) {
                                System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Nenhum arquivo selecionado.");
                        }
                    } else {
                        System.out.println("Erro: Conexão não está aberta.");
                    }
                    break;
                case "8":
                    imprimeXMLSAT();
                    break;
                case "9":
                    imprimeXMLCancelamentoSAT();
                    break;
                case "10":
                    abreGavetaElgin();
                    break;
                case "11":
                    abreGaveta();
                    break;
                case "12":
                    sinalSonoro();
                    break;
                case "13":
                    abreGaveta();
                    break;
                case "14":
                    corte();
                    break;
                case "15":
                    avancaPapel();
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
