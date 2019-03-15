<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : laptopListStype.xsl
    Created on : March 14, 2019, 2:11 PM
    Author     : bachtoan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:l="https://xml.netbeans.org/schema/laptops">
    <xsl:output method="html" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="l:laptops">
            <xsl:for-each select="l:laptop">
                <div  class="laptop_list_item">
                  
                    <div class="laptop_image">
                        <img>
                            <xsl:attribute name="src">
                                <xsl:value-of select="l:laptopImageURL"/>
                            </xsl:attribute>
                        </img>
                    </div>
                    <div class="laptop_brand">
                        <xsl:value-of select="l:laptopBrand" />
                        
                    </div>
                    <div class="laptop_name_info">
                        <xsl:value-of select="l:laptopNameInfo" />
                    </div>
                    <div class="laptop_price">
                        <xsl:value-of select="l:laptopPrice" />
                    </div>               
                    <div class="laptop_description">
                        <xsl:value-of select="l:laptopDescription" />
                    </div>
                </div>
            </xsl:for-each>
    </xsl:template>
    

</xsl:stylesheet>