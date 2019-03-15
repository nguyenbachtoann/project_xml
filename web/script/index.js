/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var xmlHttp;
var xmlResponse;
var xslResponse;
//function checkLocalStorage(controllerURL) {
//
//    let xmlDOM = null;
//    if (!localStorage.getItem("LAPTOPLIST")) {
//        let url = controllerURL;
//        xmlDOM = loadXMLDoc(url);
//    } else {
//        xmlDOM = localStorage.getItem("LAPTOPLIST");
//    }
//    return xmlDOM;
//}


function GetXmlHttpObject() {
    xmlHttp = null;
    try {//Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    } catch (e) {//Internet Explorer
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            xmlHttp = new XMLHttpRequest();
        }
    }
    return xmlHttp;
}



function loadXSLDom(xslUrl)
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
                applyXSLT(xmlResponse, xslResponse);
            }

        }
    };



}

function loadXMLDom(servletUrl, xslUrl) {
    xmlHttp = GetXmlHttpObject();
    if (!xmlHttp) {
        alert("Your browser does not support AJAX!");
        return;
    }

    xmlHttp.open("GET", servletUrl, true);
    xmlHttp.send(null);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            xmlResponse = xmlHttp.responseXML;
            if (xmlResponse != null) {
                loadXSLDom(xslUrl);
            }
        }
    };

}


function applyXSLTForList(servletUrl, xslUrl) {
    loadXMLDom(servletUrl, xslUrl);
}



function applyXSLT(xmlResponse, xslResponse) {

    if (window.ActiveXObject || xmlHttp.responseType == "msxml-document")
    {
        ex = xmlResponse.transformNode(xslResponse);
        document.getElementById("laptop_list_content").innerHTML = ex;
    }
// code for Chrome, Firefox, Opera, etc.
    else if (document.implementation && document.implementation.createDocument)
    {
        xsltProcessor = new XSLTProcessor();
        xsltProcessor.importStylesheet(xslResponse);
        resultDocument = xsltProcessor.transformToFragment(xmlResponse, document);
        document.getElementById("laptop_list_content").appendChild(resultDocument);
        changeImageSlash();
    }

}




function changeImageSlash() {
    var images = document.getElementsByTagName('img');
    debugger;
    for (var i = 0; i < images.length; i++)
    {
        var img = images[i];

        if (img.src.length == 0 && (img.src !== 'ICON/magnifying_glass.png' ))
        {
            img.src = img.src.split('%5C').join('/');
           
        }
    }
}


