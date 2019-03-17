<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : laptopDetailXSL.xsl
    Created on : March 17, 2019, 4:46 PM
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
       
        <div class="compared_list">
            <table>
                <xsl:for-each select="l:laptop">
               
                    <div class="compared_list_item" id="compared_item_{l:laptopId}">
                    
                        <tr>
                            <td>
                                <div class="compared_laptop_image" >
 
                                    <img src="{l:laptopImageURL}" />

                                </div>
                                
                            </td>
                            <td>
                                <div class="compared_laptop_content">
                                    <div class="compared_laptop_brand">
                            
                                        <xsl:value-of select="l:laptopBrand" />
                        
                                    </div>
                                    <div class="compared_laptop_name_info">
                                        <xsl:value-of select="l:laptopNameInfo" />
                                    </div>
                                    <div class="compared_laptop_price">
                                        <xsl:value-of select="l:laptopPrice" />
                                    </div>               
                                    <div class="compared_laptop_description">
                                        <xsl:value-of select="l:laptopDescription" />
                                    </div>
                        
                                    <div class="compared_laptop_domain">
                                        <a href="{l:laptopDomain}" target="_blank">
                                            <xsl:value-of select="l:laptopDomain" />
                                        </a>
                                    </div>
                                    
                                </div>
                            </td>
                            <td class="td_detail">
                                <div class="div_button_in_detail">
                                    <form action="DispathController" method="POST">
                                        <button class="button_in_detail" name="action" value="Detail">Chi Tiết</button>
                                        <input type="hidden" name="laptopId" value="{l:laptopId}"/>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    
                    
                    
                   
                    </div>
                
                </xsl:for-each>
            </table>
        </div>
       
    </xsl:template>
</xsl:stylesheet>
