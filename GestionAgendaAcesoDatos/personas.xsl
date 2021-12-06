<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
<html>
<xsl:apply-templates/>
<iframe id="adbe-cookies-checker" src="chrome-extension://efaidnbmnnnibpcajpcglclefindmkaj/data/js/extn-utils.html"/>
</html>
</xsl:template>
<xsl:template match="listaPersonas">
<head>
<title>LISTADO DE PERSONAS</title>
</head>
<body>
<h1>LISTA DE PERSONAS</h1>
<table border="1">
<tr>
<th>Nombre</th>
<th>Apellidos</th>
<th>Telefono</th>
<th>Direccion</th>
</tr>
<xsl:apply-templates select="Persona"/>
</table>
</body>
</xsl:template>
<xsl:template match="Persona">
<tr>
<xsl:apply-templates/>
</tr>
</xsl:template>
<xsl:template match="nombre|apellidos|telefono|direccion">
<td>
<xsl:apply-templates/>
</td>
</xsl:template>
</xsl:stylesheet>