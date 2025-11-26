Autores:
Enzo Araujo
Gabriel Portilho
Giulia dos Santos
Ystella Trolezi

1. Introdução

Este projeto implementa um sistema de impressão utilizando a DLL da impressora por meio da biblioteca JNA em Java. O sistema é capaz de realizar operações essenciais em ambientes de ponto de venda, como impressão de textos, códigos de barras, QR Codes, documentos fiscais SAT, além do controle físico da impressora, incluindo corte de papel, avanço, abertura de gaveta de dinheiro e sinal sonoro.
O objetivo é demonstrar, na prática, o funcionamento das funções disponibilizadas pela DLL e como integrá-las em uma aplicação Java funcional e interativa.

2. Estrutura do Projeto

O repositório contém os seguintes elementos principais:
– Código-fonte Java com todas as chamadas para a DLL.
– Diretório contendo a DLL da impressora.
– Pasta com arquivos auxiliares, como XML SAT e XML de Cancelamento.
– Este arquivo README com toda a documentação do projeto.

3. Como Executar o Projeto

4. Baixe ou clone o repositório.
   A primeira coisa a fazer é pegar o projeto. Você pode baixar o arquivo ZIP do repositório ou usar o comando git clone caso tenha familiaridade com Git. Depois disso, extraia tudo e deixe em uma pasta fácil de localizar.

5. Certifique-se de que a DLL da impressora está no diretório correto.
   A DLL é fundamental, porque é ela que contém todas as funções que a impressora utiliza. Normalmente ela já está dentro da pasta “dll”, mas é importante conferir. Se ela não estiver lá, o sistema não vai conseguir se comunicar com a impressora.

6. Abra o projeto em sua IDE (por exemplo, IntelliJ IDEA ou Eclipse).
   Depois de verificar a DLL, abra sua IDE e importe o projeto. No IntelliJ, você pode usar a opção “Open” e selecionar a pasta do projeto. No Eclipse, a opção é “Import existing project”. Assim, você consegue visualizar o código organizado.

7. Configure a porta ou conexão da impressora conforme necessário.
   O programa só vai funcionar se souber onde a impressora está conectada. Você pode configurar isso no próprio menu do programa. Dependendo da impressora, ela pode usar USB, IP de rede ou até porta COM (serial). Basta selecionar o tipo correto no menu.

8. Execute o programa.
   Agora que tudo está configurado, procure pela classe principal (geralmente chamada de Main) e execute. O console irá abrir e mostrar um menu com as opções de impressão.

9. Use o menu exibido no console para testar funções como imprimir texto, imprimir QR Code, imprimir código de barras, imprimir documentos XML SAT, abrir a gaveta, emitir sinal sonoro, cortar ou avançar o papel.
   Cada opção do menu corresponde a uma funcionalidade diferente da impressora. Basta digitar o número desejado e pressionar Enter para testar.

10. Para demonstração em sala, conecte a impressora real e execute cada função.
    Se você estiver fazendo uma apresentação, basta conectar a impressora real no computador. Depois, execute o programa e teste cada função para mostrar seu funcionamento, como impressão de texto, QR Code, código de barras, impressão dos XML, abertura da gaveta, sinal sonoro e corte de papel.

11. Confirme que a impressora está ligada e com papel.
    Isso é importante para evitar erros durante a apresentação. Caso o papel tenha acabado ou a impressora esteja desligada, os comandos não funcionarão.

12. Teste novamente a comunicação antes de demonstrar para garantir que a porta ou conexão não foi alterada.
    Em alguns computadores, a porta USB pode mudar quando você conecta em outra entrada. Por isso, é bom testar novamente antes de apresentar.

13. Se tudo estiver funcionando, siga para a demonstração das funcionalidades na ordem que achar melhor, mostrando como o sistema interage diretamente com a impressora.
    Assim, você consegue demonstrar claramente a integração entre o programa Java, a DLL e a impressora física.

14. Funcionamento Geral

O sistema utiliza a biblioteca JNA para acessar funções de uma DLL nativa da impressora. Essas funções realizam desde a impressão básica até o acionamento de componentes físicos. O fluxo principal consiste em: configurar os parâmetros da impressora; abrir a conexão com a DLL; executar as funções de impressão ou controle; aplicar corte e avanço; e encerrar a conexão ao final.

5. Documentação das Funções da DLL

ImpressaoTexto – Imprime texto simples, com alinhamento, estilo e tamanho configuráveis.
Corte – Executa o corte do papel, com opção de avanço.
ImpressaoQRCode – Imprime QR Code definindo tamanho e nível de correção.
ImpressaoCodigoBarras – Imprime códigos de barras com ajuste de altura, largura e exibição de texto (HRI).
AvancaPapel – Avança o papel uma quantidade de linhas.
AbreGavetaElgin – Abre a gaveta pelo comando nativo da Elgin.
AbreGaveta – Abre a gaveta com pino e tempos personalizados.
SinalSonoro – Emite bipes configuráveis.
ImprimeXMLSAT – Imprime XML de venda SAT a partir de arquivo ou conteúdo.
ImprimeXMLCancelamentoSAT – Imprime XML de cancelamento com assinatura criptográfica.

6. Descrição do Código

6.1 Carregamento da DLL
A DLL é carregada via JNA através da interface ImpressoraDLL, que declara todas as funções utilizadas.

6.2 Controle de Conexão
O sistema utiliza uma variável conexaoAberta para evitar comandos sem conexão inicializada.

6.3 Impressão de Texto
Envia texto com estilo, alinhamento e tamanho, seguido de corte e avanço.

6.4 Impressão de QR Code
Imprime QR Code com parâmetros fixos e finaliza com corte e avanço.

6.5 Impressão de Código de Barras
Utiliza tipo, dados, altura, largura e opção HRI, finalizando com corte e avanço.

6.6 Impressão de XML SAT
Envia à impressora o caminho de um XML SAT e realiza corte ao final.

6.7 Impressão de XML de Cancelamento
Envia o caminho do XML e a assinatura criptográfica, também com corte ao final.

6.8 Abertura de Gaveta
Pode ser realizada pelo comando nativo ou por parâmetros personalizados da DLL.

6.9 Sinal Sonoro
Aciona o bip da impressora conforme quantidade e tempo definidos.

6.10 Corte e Avanço
Funções auxiliares usadas ao final da maioria das operações.

6.11 Menu Principal
Permite ao usuário selecionar cada função individualmente, incluindo abertura de conexão, impressões variadas, abertura de gaveta, sinal sonoro e finalização.

7. Conclusão

O projeto demonstra como integrar uma DLL de impressora com uma aplicação Java via JNA, permitindo controle completo de impressão e dos recursos físicos do equipamento. As funções são organizadas em métodos separados e acessíveis por um menu simples, facilitando uso e demonstração em ambiente real.


