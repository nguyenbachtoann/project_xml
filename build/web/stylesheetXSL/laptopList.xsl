<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : laptopList.xsl
    Created on : March 10, 2019, 3:10 PM
    Author     : bachtoan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="laptops">
        <div id="laptop_list_content">
            <xsl:for-each select="laptop">
                <div  class="laptop_list_item">
                  
                    <div class="laptop_image">
                        <img>
                            <xsl:attribute name="src">
                                <xsl:value-of select="laptopImageURL"/>
                            </xsl:attribute>
                        </img>
                    </div>
                    <div class="laptop_brand">
                        <xsl:value-of select="laptopBrand" />
                    </div>
                    <div class="laptop_name_info">
                        <xsl:value-of select="laptopNameInfo" />
                    </div>
                    <div class="laptop_price">
                        <xsl:value-of select="laptopPrice" />
                    </div>               
                    <div class="laptop_description">
                        <xsl:value-of select="laptopDescription" />
                    </div>
                </div>
            </xsl:for-each>
        </div>
           
    </xsl:template>
    

</xsl:stylesheet>
