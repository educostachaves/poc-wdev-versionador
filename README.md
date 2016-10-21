## Projeto WDEV-Versionador

### Definição
Vamos iniciar a sua POC e para isso eu elaborei o caso abaixo. Todas as definições de negócio chegam para a equipe de CanaisWeb na forma de GDs e em contrapartida, cada GD possui uma Especificação de Negócios (EN).

Existe a necessidade de versionarmos cada GD, armazenando seu nome, data de confecção, versão e o nome do arquivo. Além de armazenar os metadados precisaremos armazenar o documento em formato binário (este deverá ser armazenado no banco de dados). Os documentos, em formato PDF, serão copiados para um diretório (file server) no mesmo host onde o sistema estará executando. Cada documento PDF possuirá em seu conteúdo metadados (exemplo abaixo) que poderão ser lidos pela aplicação.


```xml
<rdf:Description rdf:about=""
  xmlns:wdev="http://www.wdev.com.br/namespace/1.0/">
  <wdev:gd>2014001436</wdev:gd>
  <wdev:descricao>Meios Remotos</wdev:descricao>
  <wdev:dataCriacao>2016-04-03</wdev:dataCriacao>
  <wdev:versao>1.0</wdev:versao>
</rdf:Description>
```

A aplicação deverá, periodicamente, varrer este diretório afim de encontrar arquivos novos (essa periodicidade deverá acontecer de 5 em 5 minutos). Uma vez o PDF descoberto no diretório, a aplicação deverá validar os metadados a fim de localizar possíveis inconsistências. Esse processo inicial deverá ser assíncrono visando evitar gargalos de processamento. Em seguida os arquivos deverão ser armazenados no banco de dados juntamente com os seus metadados. Esses dados serão consultados tanto por uma interface web (browser) como por um aplicativo mobile.

### Requisitos não funcionais:

- O sistema deverá ser confeccionado em Java e disponibilizado na forma de um aplicativo web.
- Deverá ser usado o Spring Framework para a Inversão de Controle e a Injeção de Dependências.
- Deverá ser usado o Quartz para o scheduler (monitorar diretorio de 5 em 5 min)
- Deverá ser usado o ActiveMQ (JMS) para o processamento assíncrono.
- Deverá haver duas filas JMS. Nos casos em que a validação foi um sucesso, será usada a file de processamento. Nos casos de erro de validação será usada a fila de erro.
- Deverá existir uma tela que consulte a fila de erro e liste os arquivos que não foram validados. Essa tela deve permitir excluir os arquivos (após analisados pelo usuário).
- Para o MVC será usado o Spring MVC.
- Deverá ser usado o MySQL como SGBD da aplicação. O modelo ficará à cargo do desenvolvedor, desde que atenda ao negócio.
- Para a interface como Mobile deverá ser usado serviços do tipo Rest. Para a criação desse serviço deverá ser usado o Apache CXF.
- O código será versionado no Subversion em um branch que será fornecido. O código será analisado diariamente, por esse motivo, não esqueça de realizar o commit ao menos uma vez ao dia.
- Para as interfaces web (front) não há exigências. Ficará à cargo do desenvolvedor escolher as tecnologias que serão usadas, desde que não comprometa a usabilidade.
- Estou anexando um desenho da arquitetura do sistema e alguma massa de dados para auxiliar nos testes.

### Observações

OBS: Esta POC visa não só avaliar seu conhecimento como auxiliar no seu desenvolvimento profissional. Não há regras em relação à tirar dúvidas com outros integrantes da equipe, desde que use o bom senso. Procure pesquisar antes, tente solucionar suas dúvidas sozinho. Isso conta no dia-a-dia.
