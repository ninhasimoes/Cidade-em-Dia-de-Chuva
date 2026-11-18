Cidade em Dia de Chuva

Global Solution de POO com Java e Web (FIAP, prof. Selmini): aplicação Spring Boot com Oracle para cadastrar bairros e trechos, registrar leituras de sensores de nível de água e consultar o painel de cada bairro.

Integrantes
Nome	RM
Ana Luiza Simões	561460
Rayssa de Moraes	565965

RM da equipe: 1127425 (561460 + 565965)

Projeto Maven: ana561460

Versão escolhida

Versão web (@Controller com páginas Thymeleaf).

Forma dos DTOs

Os DTOs são classes comuns (Lombok @Getter, @Setter e @NoArgsConstructor), e não record. A escolha se deve ao formulário do Thymeleaf (th:object e th:field), que precisa de getters e setters para ligar os campos ao objeto.

Parâmetros da equipe (RM 1127425)
Parâmetro	Cálculo	Resultado
P1	1127425 % 1000 = 425	prefixo das tabelas GS425
P2	10 + 2 * (1127425 % 4) = 10 + 2 * 1	limiar de alerta L = 12 cm (atenção a partir de 6 cm)
P3	(1127425 / 10) % 3 = 112742 % 3 = 2	atributo extra de Bairro: possuiPiscinao (Boolean, caixa de seleção)
P4	(1127425 / 100) % 3 = 11274 % 3 = 0	lista de trechos ordenada por logradouro (A a Z)
P5	(1127425 / 1000) % 2 = 1127 % 2 = 1	excluir trecho que tem leituras: cascata (apaga as leituras e depois o trecho)
Tabelas no Oracle

GS425_BAIRRO, GS425_TRECHO e GS425_LEITURA.

A senha do banco não está no repositório: ela é lida das variáveis de ambiente.

Tecnologias

Java, Maven, Spring Boot, Spring Web, Spring Data JPA, Bean Validation, Thymeleaf, Lombok, Spring Boot DevTools e Oracle (FIAP).