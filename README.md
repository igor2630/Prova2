Sistema de Gerenciamento de Estacionamento

Turma POO 3ºSemestre Fatec Manhã 

Professor: Prof. Mestre Marcos Roberto de Moraes (Maromo)

Alunos:
1. Igor Gabriel Domingos
2. Evandro Henrique de Farias Costa

Tema escolhido: Sistema de Gerenciamento de Estacionamento

O projeto consiste em um sistema para gerenciar 
as 10 vagas de um estacionamento, 
controlando a entrada e saída de veículos, 
registrando as estadias e calculando os valores a serem pagos.

Arquitetura das classes:

Estacionamento: responsável pelo controle individual das 10 vagas, verificando a disponibilidade e registrando a entrada e saída dos veículos. \
Veiculo: responsável por representar cada veículo, armazenando suas informações, como placa e modelo. \
TicketEstacionamento: representa o registro de estadia de um veículo, armazenando informações como horário de entrada, horário de saída, número da vaga e valor a ser pago. Implementa a interface Tarifavel e Comparable. \
VagaNaoDisponivelException: exceção personalizada utilizada quando não existem vagas disponíveis no estacionamento. \
Tarifavel: interface que define o método utilizado para calcular o valor da estadia de um veículo. \
TelaEstacionamento: responsável pela interface gráfica (GUI) do sistema e pela interação com o usuário. \
Principal: responsável por iniciar o sistema por meio do método main().

Durante a entrada de um veículo, a classe Estacionamento verifica se existe alguma vaga disponível. Caso exista, registra o veículo na primeira vaga livre e gera um ticket. Caso contrário, utiliza a exceção VagaNaoDisponivelException. \
Ao registrar a saída, o sistema calcula o valor da estadia por meio do método definido pela interface Tarifavel, libera a vaga ocupada e armazena o ticket no histórico.

Instruções de execução:

1. Clone ou baixe o repositório.
2. Abra o projeto em uma IDE compatível com Java (IntelliJ, Eclipse ou NetBeans).
3. Compile o projeto.
4. Execute a classe Principal (método main).
5. A interface gráfica do sistema será exibida.

Histórico de prompts de IA utilizados:

Durante o desenvolvimento do projeto, foi utilizada inteligência artificial como ferramenta de apoio para esclarecimento de dúvidas, identificação de erros e aprimoramento da implementação. \
Abaixo serão documentados os prompts utilizados e os refinamentos realizados durante o desenvolvimento.

1. Revisão inicial da classe Veiculo

Foi solicitada a análise da primeira versão da classe Veiculo, questionando se havia erros ou algo faltando. A IA apontou que a estrutura básica estava correta, mas que, de acordo com as diretrizes do professor, faltavam os métodos toString(), equals() e hashCode(). \
A classe foi então refinada com a inclusão desses métodos, utilizando a placa como critério de igualdade.

2. Revisão da classe TicketEstacionamento

Foi enviada a primeira versão da classe TicketEstacionamento para verificação. A IA identificou que os métodos compareTo() e calcularPrecoEstadia() estavam incompletos (retornavam valores fixos). Também foi esclarecida a forma correta de calcular a diferença entre dois LocalDateTime utilizando a classe Duration. \
Após o retorno, a lógica de cálculo de preço e a ordenação pelo horário de entrada foram implementadas.

3. Desenvolvimento da classe Estacionamento

Diversas versões do método registrarEntrada foram enviadas para análise. A IA apontou erros de lógica (condição invertida na busca de vaga livre), uso incorreto de add em lista de tamanho fixo e ausência do lançamento da exceção VagaNaoDisponivelException. \
Com base nas orientações, o método foi reescrito corretamente, incluindo também o controle de ticket sequencial e a verificação de veículo já estacionado. Posteriormente foram implementados os métodos registrarSaida, listarVeiculosEstacionados, consultarHistorico (com Collections.sort) e pesquisarTicket.

4. Revisão da interface gráfica (TelaEstacionamento)

A classe da interface gráfica foi enviada para verificação de conformidade com o enunciado. A IA destacou a ausência inicial de WindowListener, do tratamento de NumberFormatException e do uso efetivo de Collections.sort(). \
Foram então adicionados: o WindowAdapter para confirmação de fechamento da janela, o painel de pesquisa de ticket (com captura de NumberFormatException) e o botão de ordenação, que passa a utilizar a lista já ordenada retornada pelo método consultarHistorico.

5. Revisão geral do projeto

Ao final, foi solicitada uma verificação completa de todas as classes em relação às diretrizes do professor. A IA confirmou que os requisitos de Collections, Comparable, interface polimórfica, exceção checada, ordenação e componentes Swing estavam atendidos.

Os retornos da inteligência artificial foram utilizados como apoio para correção de erros e alinhamento com o enunciado. Toda a lógica de negócio, decisões de arquitetura e validações finais foram de responsabilidade dos alunos.

Referências de desenvolvimento: Também foram utilizados como referência os arquivos e exemplos desenvolvidos em sala de aula durante a disciplina de Programação Orientada a Objetos.