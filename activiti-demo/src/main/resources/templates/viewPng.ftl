<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title>流程图预览</title>

	</head>
	<body>
		<#if result == 'success'>
			<img src="data:image/png;base64,${base64Result}">
		<#else >
			流程未部署！！！
		</#if>
	</body>
</html>