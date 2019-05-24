<?xml version="1.0" encoding="utf-8"?>
<?xml-stylesheet type="text/xsl" href="file:///C:/Users/Mirjeta%20Bytyqi/Desktop/Projekti-MDHGJP/Faza2/XSLT/Kandidatet_xsl.xsl"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Kandidatet</h2>
				<table border="1">
					<tr>
						<th>Id</th>
						<th>Emri</th>
						<th>Mbiemri</th>
						<th>Data e regjistrimit</th>
						<th>Nr. Personal</th>
						<th>Mosha</th>
					</tr>
					<xsl:for-each select="kandidatet/kandidati">
						<xsl:sort select="emriK" order="ascending"/>
						<tr>
							<td>
								<xsl:value-of select="@kId"/>
							</td>
							<td>
								<xsl:value-of select="emriK"/>
							</td>
							<td>
								<xsl:value-of select="mbiemriK"/>
							</td>
							<xsl:choose>
								<xsl:when test="@dataRegjistrimit != '02-Jan-2018'">
									<td bgcolor="green">
										<xsl:value-of select="@dataRegjistrimit"/>
									</td>
								</xsl:when>
								<xsl:otherwise>
									<td>
										<xsl:value-of select="@dataRegjistrimit"/>
									</td>
								</xsl:otherwise>
							</xsl:choose>
							<td>
								<xsl:value-of select="nrPersonalK"/>
							</td>
							<td>
								<xsl:value-of select="moshaK"/>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
