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
    <xsl:param name="searchValue" />
    <!--    <xsl:variable name="pageRecCount" select="5" />
    <xsl:variable name="totalRecord" select="count(laptops/laptop)" />
    <xsl:variable name="pageCount" select="floor($totalRecord div 5) + 1" />
    -->
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    
   
    
    
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="l:laptops">
            
            <xsl:for-each select="l:laptop[(contains(translate(l:laptopBrand, $searchValue, $searchValue), $searchValue) or contains(l:laptopDescription, $searchValue))]">
                   <div  class="laptop_list_item" id="{l:laptopId}">
                  
                        <div class="laptop_image" >
 
                                <img src="{l:laptopImageURL}" />

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
                        
                        <div class="div_button_detail">
                            <form action="DispathController" method="POST">
                                <button class="button_detail" name="action" value="Detail">Chi Tiết</button>
                                <input type="hidden" name="laptopId" value="{l:laptopId}"/>
                            </form>
                        </div>
                    </div>
              
            </xsl:for-each>
    </xsl:template>
    

    
    

</xsl:stylesheet>


    




<!--
<xsl:param name="searchValue" />
    <xsl:variable name="pageRecCount" select="5" />
    <xsl:variable name="totalRecord" select="count(laptops/laptop)" />
    <xsl:variable name="pageCount" select="floor($totalRecord div 5) + 1" />
    
     TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template name="listTemplate">
        <xsl:param name="i"/>
        <xsl:param name="pageRecCount"/>
        <xsl:param name="pageCount"/>
        
        
        <div id="page{$i}">
            <xsl:attribute name = "class">
                <xsl:if test="$i = 1">
                    visible
                </xsl:if>
                <xsl:if test="$i != 1">
                    hidden
                </xsl:if>
            </xsl:attribute>
            
            
            <xsl:for-each select="l:laptops/laptop[(contains(translate(l:laptopBrand, $searchValue, $searchValue), $searchValue) or contains(l:laptopDescription, $searchValue))]">
                <xsl:if test="position()&gt;= ((($i - 1)*$pageRecCount)+1) and position() &lt;=((($i-1)*$pageRecCount)+$pageRecCount) ">
                    <div  class="laptop_list_item" id="{l:laptopId}">
                  
                        <div class="laptop_image" onclick="getDetailWithCompare('{l:laptopId}')">
                            <img src="{l:laptopImageURL}"/>
                            
                      
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
                </xsl:if>
                
                
            </xsl:for-each>
            
        </div>
        
        <xsl:if test="$i &lt; $pageCount">
            <xsl:call-template name="listTemplate">
                <xsl:with-param name="i" select="$i + 1"/>
                <xsl:with-param name="pageRecCount" select="$pageRecCount"/>
                <xsl:with-param name="pageCount" select="$pageCount"/>
            </xsl:call-template>
        </xsl:if>
        
    </xsl:template>
    
    
    <xsl:template name="paging">
        <xsl:param name="i"/>
        <xsl:param name="currentPage"/>
        <xsl:param name="pageCount"/>
        <span onclick="swapPage('page{i}',{$pageCount});">
            <xsl:attribute name="id">
                <xsl:value-of select="$i"/>
            </xsl:attribute>
            <xsl:attribute name="class">
                <xsl:if test="$i = 1">
                    selected
                </xsl:if>
                <xsl:if test="$i != 1">
                    notSelected
                </xsl:if>
            </xsl:attribute>
            <xsl:value-of select="$i"/>
        </span>
        
        <xsl:if test="$i &lt; $pageCount">
            <xsl:call-template name="paging">
                <xsl:with-param name="i" select="$i + 1"/>
                <xsl:with-param name="currentPage" select="$currentPage"/>
                <xsl:with-param name="pageCount" select="$pageCount"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
    
    
<xsl:call-template name="paging">
    <xsl:with-param name="i" select="1"/>
    <xsl:with-param name="currentPage" select="1"/>
    <xsl:with-param name="pageCount" select="$pageCount"/>
</xsl:call-template>-->