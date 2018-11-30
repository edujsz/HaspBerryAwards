# Instruções para Rodar o Projeto:

### Para compilar o projeto:
    Ir até a pasta do pom, abrir o prompt de comando e executar mvn clean install.

### Para rodar os testes
    Ir até a pasta do pom, abrir o prompt de comando e executar mvn clean test.
	
### Servidor
	O projeto foi desenvolvido utilizando SpringBoot, para subir o servidor basta executar o mvn clean install, ir até a pasta target do projeto e executar no prompt java -jar raspberry-awards-0.0.1-SNAPSHOT.jar.
	
### Swagger
	O Swagger está habilitado para o projeto e pode ser acessado através da url servidor:porta/swagger-ui.html. Ex: http://localhost:8080/swagger-ui.html.
	
### Heroku
	O diretório do git tem uma pipeline configurada com deploy automático no Heroku, o link para acesso é: https://raspberryawards.herokuapp.com/swagger-ui.html
	No primeiro acesso deve demorar algum tempo, pois por utilizar a camada free, o Heroku instancia uma nova máquina ao acessar a URL da API.