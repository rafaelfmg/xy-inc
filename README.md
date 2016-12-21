
## Resumo
Sistema desenvolvido para criar um novo “Modelo” e gerar um recurso REST para gerenciamento desse modelo.

## Execução
1- Tenha Java 8 instalado e devidamente configurado(variável JAVA_HOME criada e apontada para o Java 8).

2- Tenha o servidor do MongoDB instalado e rodando, para instalar você pode seguir o passo a passo no seguinte [link](http://docs.mongodb.org/manual/installation/)

3- Edite a classe  AppConfig.java e coloque as informações do servidor MongoDB, o arquivo já está com as configurações padrões.

4 - Vá até o diretório raíz da aplicação e digite "mvn spring-boot:run".

5 - Após isso a aplicação foi compilada e está rodando no endereço https://localhost:8080, as URLs dos serviços são essas:

  Para criar um novo modelo use o arquivo modelo.json que se encontra na raiz do projeto na pasta json, use a URL para criar o modelo: 
  
  POST [http://localhost:8080/newmodel] (http://localhost:8080/newmodel)
  
  Para listar todas as instancias de um determinado modelo, usar a url:
  
  GET [http://localhost:8080/{modelo}] (http://localhost:8080/{modelo})
 
  Para listar uma determinada instancia do modelo, usar a url:
  
  GET [http://localhost:8080/{modelo}/{id}] (http://localhost:8080/{modelo}/{id})
  
  Para criar uma determinada instancia do modelo, usar a url:
  
  POST [http://localhost:8080/{modelo}] (http://localhost:8080/{modelo})
  
  Para editar uma determinada instancia do modelo, usar a url:
  
  PUT [http://localhost:8080/{modelo}/{id}] (http://localhost:8080/{modelo}/{id})
    
  Para deletar uma determinada instancia do modelo, usar a url:
  
  DELETE [http://localhost:8080/{modelo}/{id}] (http://localhost:8080/{modelo}/{id})

  Na raiz do projeto se encontra o documento Arquitetura.pdf com uma breve descrição da arquitetura proposta!
