Documentação das Funções da DLL da Impressora

A função ImpressaoTexto imprime um texto simples na impressora. Ela recebe o texto, o alinhamento (0 para esquerda, 1 para centro e 2 para direita), o estilo do texto (como negrito ou expandido) e o tamanho da fonte.

A função Corte realiza o corte do papel após a impressão. O parâmetro define o avanço adicional antes do corte.

A função ImpressaoQRCode imprime um QR Code. Ela recebe o conteúdo do código, o tamanho do QR e o nível de correção de erro, que pode variar de 1 a 4.

A função ImpressaoCodigoBarras imprime códigos de barras nos padrões suportados pela impressora. Ela recebe o tipo do código de barras, os dados a serem codificados, a altura, a largura e o parâmetro HRI, que define se os caracteres serão exibidos abaixo do código.

A função AvancaPapel avança o papel uma quantidade de linhas, sendo normalmente utilizada após uma impressão.

A função AbreGavetaElgin abre a gaveta de dinheiro usando o comando nativo da impressora Elgin.

A função AbreGaveta abre a gaveta de dinheiro usando parâmetros personalizados, como pino de acionamento e tempos de pulso.

A função SinalSonoro aciona o bip ou alerta sonoro da impressora. Ela recebe a quantidade de bipes e o tempo de início e fim do pulso.

A função ImprimeXMLSAT imprime o XML de uma venda SAT. O parâmetro pode ser um caminho para o arquivo ou o conteúdo completo em forma de string, seguido de um parâmetro auxiliar que geralmente é zero.

A função ImprimeXMLCancelamentoSAT imprime o XML de cancelamento de uma venda SAT. Ela recebe o caminho ou conteúdo do XML de cancelamento, a assinatura criptográfica utilizada pelo SAT para gerar o QR Code e um parâmetro final geralmente definido como zero.

Essas funções compõem as operações principais utilizadas no sistema para comunicação com a impressora, permitindo impressão de textos, códigos, documentos fiscais, abertura de gaveta e controle físico do equipamento.
