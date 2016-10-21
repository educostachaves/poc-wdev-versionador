<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Listagem de Gds</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
</head>
<body>

	<h3>Listagem de Gds</h3>
	
	<a href="<c:url value='/failed' />">Fila de falhados</a>

	<table class="tg">

		<tr>
			<th width="80">Gd</th>
			<th width="120">Nome do Arquivo</th>
			<th width="120">Descrição</th>
			<th width="60">Versão</th>
			<th width="60">Criação</th>
			<th width="60">Funções</th>
		</tr>
		<c:choose>
			<c:when test="${!empty listGds}">
				<c:forEach items="${listGds}" var="gd">
					<tr>
						<td>${gd.gd}</td>
						<td>${gd.nomeArquivo}</td>
						<td>${gd.descricao}</td>
						<td>${gd.versao}</td>
						<td>${gd.dataCriacao}</td>
						<td><a href="<c:url value='/gd/download/${gd.id}' />">Download</a>
							/ <a href="<c:url value='/gd/remove/${gd.id}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">Não há gds carregados até o momento!</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>

</body>
</html>
