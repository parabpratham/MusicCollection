
function getData(dataSource) {
	var XMLHttpRequestObject = false;
	if (window.XMLHttpRequest) {
		XMLHttpRequestObject = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHttp");
		}
	}
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.open("GET", dataSource);
		XMLHttpRequestObject.onreadystatechange = function () {
			if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
				document.getElementById("targetDiv1").innerHTML = XMLHttpRequestObject.responseText;
				delete XMLHttpRequestObject;
				XMLHttpRequestObject = null;
			}
		};
		XMLHttpRequestObject.send(null);
	}
}

//For GET request Text 
function getDataReturnText(dataSource, callback) {
	var XMLHttpRequestObject = false;
	if (window.XMLHttpRequest) {
		XMLHttpRequestObject = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.open("GET", dataSource);
		XMLHttpRequestObject.onreadystatechange = function () {
			if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
				try {
					callback(XMLHttpRequestObject.responseText);
					delete XMLHttpRequestObject;
					XMLHttpRequestObject = null;
				}
				catch (e) {
					alert(e);
				}
			}
		};
		XMLHttpRequestObject.send(null);
	}
}
//For GET request XML
function getDataReturnXml(url, callback) {
	var XMLHttpRequestObject = false;
	if (window.XMLHttpRequest) {
		XMLHttpRequestObject = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.open("GET", url);
		XMLHttpRequestObject.onreadystatechange = function () {
			if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
				try {
					callback(XMLHttpRequestObject.responseXML);
					delete XMLHttpRequestObject;
					XMLHttpRequestObject = null;
				}
				catch (e) {
					alert(e);
				}
			}
		};
		XMLHttpRequestObject.send(null);
	}
}

//For GET request XML to fill select
function fillFromDataReturnXml(url, element) {
	var XMLHttpRequestObject = false;
	if (window.XMLHttpRequest) {
		XMLHttpRequestObject = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.open("GET", url);
		XMLHttpRequestObject.onreadystatechange = function () {
			if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
				listOptions(XMLHttpRequestObject.responseXML, element);
				delete XMLHttpRequestObject;
				XMLHttpRequestObject = null;
			}
		};
		XMLHttpRequestObject.send(null);
	}
}
function listOptions(xmlDocument, element) {
	var loopIndex;
	try {
		var options = xmlDocument.getElementsByTagName("option");
		var selectControl = document.getElementById(element);
		for (loopIndex = 0; loopIndex < options.length; loopIndex = loopIndex + 1) {
			try {
				var a = new Option(options[loopIndex].firstChild.data);
				selectControl.options[loopIndex] = a;
			}
			catch (ex) {
				alert(ex);
			}
		}
	}
	catch (ex) {
		alert(ex);
	}
}

//For POST request Text
function postDataReturnText(url, data, callback) {
	var XMLHttpRequestObject = false;
	if (window.XMLHttpRequest) {
		XMLHttpRequestObject = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.open("POST", url);
		XMLHttpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		XMLHttpRequestObject.onreadystatechange = function () {
			if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
				callback(XMLHttpRequestObject.responseText);
				delete XMLHttpRequestObject;
				XMLHttpRequestObject = null;
			}
		};
		XMLHttpRequestObject.send(data);
	}
}
function postDataReturnXml(url, data, callback) {
	var XMLHttpRequestObject = false;
	if (window.XMLHttpRequest) {
		XMLHttpRequestObject = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.open("POST", url);
		XMLHttpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		XMLHttpRequestObject.onreadystatechange = function () {
			if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
				callback(XMLHttpRequestObject.responseXML);
				delete XMLHttpRequestObject;
				XMLHttpRequestObject = null;
			}
		};
		XMLHttpRequestObject.send(data);
	}
}
function postDataReturnXml(url, data, callback) {
	var XMLHttpRequestObject = false;
	if (window.XMLHttpRequest) {
		XMLHttpRequestObject = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.open("POST", url);
		XMLHttpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		XMLHttpRequestObject.onreadystatechange = function () {
			if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
				callback(XMLHttpRequestObject.responseXML);
				delete XMLHttpRequestObject;
				XMLHttpRequestObject = null;
			}
		};
		XMLHttpRequestObject.send(data);
	}
}
function getXMLForErrors(dataSource) {
	try {
		var options;
		var isValid = true;
		var XMLHttpRequestObject = getXMLHttpRequestObject();
		if (XMLHttpRequestObject) {
			XMLHttpRequestObject.open("GET", dataSource);
			XMLHttpRequestObject.onreadystatechange = function () {
				if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
					var xmlDocument = XMLHttpRequestObject.responseXML;
					options = xmlDocument.getElementsByTagName("pageerrors");
					isValid = showErrors(options);
				}
			};
			XMLHttpRequestObject.send(null);
		}
	}
	catch (e) {
		alert(e);
	}
	alert("3" + isValid);
	return isValid;
}
function addLiToPlyalist(xmlDocument) {
	var loopIndex;
	try {
		var songs = xmlDocument.getElementsByTagName("SONG");
		var playList = document.getElementById("cont_bo_ltb_playlist_item_ol");
		for (loopIndex = 0; loopIndex < songs.length; loopIndex = loopIndex + 1) {
			try {
				var songTitle = songs[loopIndex].getElementsByTagName("TITLE");
				var songArtist = songs[loopIndex].getElementsByTagName("ARTIST");
				var newli = document.createElement("li");
				newli.innerHTML = songTitle[0].firstChild.nodeValue + "-" + songArtist[0].firstChild.nodeValue;
				playList.appendChild(newli);
			}
			catch (ex) {
				alert(ex);
			}
		}
	}
	catch (ex) {
		alert(ex);
	}
}

