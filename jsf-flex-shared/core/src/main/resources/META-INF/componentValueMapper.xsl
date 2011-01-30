<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 	xmlns:cvm="http://jsf-flex.googlecode.com" 
								xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:template match="cvm:classLists">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="jsf-flex-tld.css"/>
			</head>
			
			<body>
				<h1>Component Value Mapper</h1>
				<xsl:apply-templates select="cvm:classInfo" />
				
				<script type="text/javascript" src="xslContentAssister.js" />
			</body>
				
		</html>
	</xsl:template>
	
	<xsl:template name="attributeHeader">
		<div class="attributeHeader">
			<div>Field</div>
			<div>Appended</div>
		</div>
	</xsl:template>
	
	<xsl:template match="cvm:classInfo">
		<fieldset>
			<legend><xsl:value-of select="cvm:className" />&#x2009;<span onclick="toggleContentDisplay(event);">+</span></legend>
			<span></span>
			<div>
			<xsl:call-template name="attributeHeader" />
				<xsl:apply-templates select="//cvm:attributeList/cvm:attribute/cvm:name[.='value']" />
				<div>
					<div>
						&#160;
					</div>
					<div style="border-left: 0px; border-right: 0px;">
						&#160;
					</div>
				</div>
			</div>
		</fieldset>
	</xsl:template>
	
	<xsl:template match="//cvm:attributeList/cvm:attribute/cvm:name[.='value']">
		<div>
			<div>
				<xsl:value-of select="following-sibling::cvm:value" />
			</div>
			<div>
				<xsl:value-of select="parent::cvm:attribute/following-sibling::cvm:attribute/child::cvm:value/attribute::append" />
			</div>
		</div>
	</xsl:template>
	
</xsl:stylesheet>
