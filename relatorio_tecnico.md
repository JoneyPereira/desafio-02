# :page_facing_up: Relatório Técnico

Relatório de tecnologias e dificuldades encontradas na conclusão dos principais requisitos referentes ao segundo TechChallenge da Pós: Arquitetura e Desenvolvimento em Java / FIAP.
#

### Tecnologias e Ferramentas Utilizadas
* Java
* SpringBoot
* PostMan
* JPA
* Docker
* Flyway
* Swagger
* Bibliotecas: Validation, Validator, Lombok

### Desafios e Soluções

* Desenvolvimento realizado com a técnica de pair programming.
* Dificuldade ao tentar rodar cada API em um único endereço @RequestMapping("/eletrodomesticos"). Não sendo possível, foi necessário cria um endereço para cada @RequestMapping().
* Dificuldades na implatanção da validações, devido não compreensão do método privado **validar()**. Após pesquisas e implantação o Validator passou a funcionar como nas aulas.
* Dificuldade ao tentar rodar um container para o banco de dados mysql, apos algumas consultas foi possivel criar e rodar a imagem adequadamente.
* Dificuldade em implementar Flyway como gerenciador de migrações, apos algums pesquisa conseguimos implementar esta solução adequadamente.
* Dificuldade em implemtar o Swagger como documentação dos endpoint, foi preciso criar algumas classes de configuração, assim a implementação deu certo.

