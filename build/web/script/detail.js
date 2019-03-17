/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getDetailWithCompare(servletUrl, xslUrl, compareString, price) {

    let newServletUrl = servletUrl + "?compareString=" + compareString + "&priceString=" + price;
    loadXMLDom(newServletUrl, xslUrl, compareString);
}



function loadXSLDom(xslUrl, searchValue)
{

    xmlHttp = GetXmlHttpObject();
    if (!xmlHttp) {
        alert("Your browser does not support AJAX!");
        return;
    }

    xmlHttp.open("GET", xslUrl, true);
    xmlHttp.send(null);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

            xslResponse = xmlHttp.responseXML;
            if (xslResponse != null) {
                applyXSLTCompared(xmlResponse, xslResponse, searchValue);
            }

        }
    };



}


function applyXSLTCompared(xmlResponse, xslResponse, searchValue) {

    if (window.ActiveXObject || xmlHttp.responseType == "msxml-document")
    {
        ex = xmlResponse.transformNode(xslResponse);
        document.getElementById("detail_page_compare").innerHTML = ex;
    }
// code for Chrome, Firefox, Opera, etc.
    else if (document.implementation && document.implementation.createDocument)
    {
        xsltProcessor = new XSLTProcessor();
        xsltProcessor.importStylesheet(xslResponse);
        document.getElementById("detail_page_compare").innerHTML = "";
        resultDocument = xsltProcessor.transformToFragment(xmlResponse, document);
        document.getElementById("detail_page_compare").appendChild(resultDocument);
        changeImageSlash();
    }

}
