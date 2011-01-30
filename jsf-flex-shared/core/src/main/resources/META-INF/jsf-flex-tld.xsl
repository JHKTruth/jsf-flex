<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 	xmlns:jee="http://java.sun.com/xml/ns/javaee" 
								xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
								xmlns:css="http://www.w3.org/TR/XSL-for-CSS">
	
	<xsl:template match="jee:taglib">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="jsf-flex-tld.css"/>
			</head>
			
			<body>
				<h1><xsl:value-of select="jee:display-name" /></h1>
				<css:chunk background-color="#DDFFDD">
				<fieldset>
					<legend>Following are attributes which are common for each component&#x2009;<span onclick="toggleContentDisplay(event);">+</span></legend>
					<br/>
					<div>
					<xsl:apply-templates select="//jee:attribute[jee:name='componentAttributes']" />
					</div>
				</fieldset>
				</css:chunk>
				<xsl:apply-templates select="jee:tag" />
				
				<script type="text/javascript" src="xslContentAssister.js" />
			</body>
				
		</html>
	</xsl:template>
	
	<xsl:template match="//jee:attribute[jee:name='componentAttributes']">
		<xsl:if test="position() = 2">
		<xsl:call-template name="attributeHeader" />
		
		<div>
			<div><xsl:value-of select="jee:name" /></div>
			<div><xsl:value-of select="jee:description" /></div>
		</div>
		<!-- Now search within the sibling, though unabbreviated version is slower than the
			 abbreviated version. Latter does not have equivalent following-sibling syntax [at least that I am aware of].
			 Since utilized following-sibling, just use unabbreviated version for value-of for convenience. -->
		<xsl:apply-templates select="following-sibling::jee:attribute[jee:name='componentAttributesJSONFormat']" />
		<xsl:apply-templates select="following-sibling::jee:attribute[jee:name='nameSpaceOverride']" />
		<div>
			<div>
				&#160;
			</div>
			<div style="border-left: 0px; border-right: 0px;">
				&#160;
			</div>
		</div>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="attributeHeader">
		<div class="attributeHeader">
			<css:chunk background-color="#F0F0F0" text-align="center" font-weight="bold" color="#000000">
			<div>Attribute</div>
			<div>Description</div>
			</css:chunk>
		</div>
	</xsl:template>
	
	<xsl:template match="jee:attribute[jee:name='componentAttributesJSONFormat']">
		<div>
			<div><xsl:value-of select="child::jee:name" /></div><div><xsl:value-of select="child::jee:description" /></div>
		</div>
	</xsl:template>
	
	<xsl:template match="jee:attribute[jee:name='nameSpaceOverride']">
		<div>
			<div><xsl:value-of select="child::jee:name" /></div><div><xsl:value-of select="child::jee:description" /></div>
		</div>
	</xsl:template>
	
	<xsl:template match="jee:tag">
		<fieldset>
			<legend><xsl:value-of select="jee:name" />&#x2009;<span onclick="toggleContentDisplay(event);">+</span></legend>
			<h4> <xsl:value-of select="jee:description" /> </h4>
			<div>
			<xsl:call-template name="attributeHeader" />
			<xsl:for-each select="jee:attribute[jee:name != 'componentAttributes' and jee:name != 'componentAttributesJSONFormat' and jee:name != 'nameSpaceOverride']">
				<xsl:sort select="jee:name" />
				<div>
					<div>
						<xsl:value-of select="jee:name" />
						
						<xsl:if test="jee:required">
							<css:chunk font-weight="bold" float="right"><span style="font-weight: bold; float:right;">REQUIRED</span></css:chunk>
						</xsl:if>
						
					</div>
					<div><xsl:value-of select="jee:description" /></div>
				</div>
			</xsl:for-each>
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
	
</xsl:stylesheet>
