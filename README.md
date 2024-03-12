## Projeto 🔨

Uma aplicação VUTTR (Very Useful Tools to Remember). A aplicação é um simples repositório para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags.

Construída em Java com Spring Boot para a API e MongoDB como banco de dados com base no descritivo do desafio [Bossabox](https://bossabox.notion.site/Back-end-0b2c45f1a00e4a849eefe3b1d57f23c6) do repositório [Backend Challenges](https://github.com/CollabCodeTech/backend-challenges?tab=readme-ov-file).

Criei este projeto pessoal para estudar documentação, testes, docker e autenticação com JWT e para testar meus conhecimentos prévios também.

## Ambiente 🌎

Este projeto é containerizado e, para testa-lo, use Docker, basta seguir estes dois passos:

- Gere uma imagem da aplicação com o comando `docker build -t spring_app:1.0 .` (posteriormente farei o push da imagem para o Docker Hub e este passo não sera mais necessário).
- Logo após o processo ser finalizado, use o comando `docker-compose up` para subir tanto o container do MongoDB, quanto o container da aplicação spring e pronto!

A aplicação escuta a porta 3000 e tem como entrada <http://localhost:3000/api/tools>.

## Documentação 📖

Para a documentação deste projeto, estou usando o **OpenAPI 3.0** com o **Swagger UI** para vizualização.

Documentação disponível em <http://localhost:3000/swagger-ui/index.html>
