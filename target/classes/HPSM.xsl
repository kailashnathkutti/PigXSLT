<?xml version="1.0" encoding="utf-8"?>
<!-- Author: Kailashnath Kutti kkutti@pivotal.io This XSL is to transform 
	HPSM logs into CSV -->


<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:ns="http://www.w3.org/1999/XSL/Transform/Coo">
	<xsl:output method="text" omit-xml-declaration="yes" indent="no" />
	<xsl:template match="/ns:instance">
		<xsl:for-each select="//ns:alert/ns:alert">
			<xsl:value-of
				select="concat(ns:description,',',ns:impact,',',ns:resourceName,',',ns:resourceType,',',ns:eventType,',',ns:eventFamily,',',ns:siaFlag,',',ns:severity,',',ns:eventTime,',',ns:eventId,',',ns:flappingCount,',','&#xA;')" />
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
