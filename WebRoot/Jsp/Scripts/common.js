
function showErrors(options) {
	var loopIndex;
	if (options.length = 1) {
		var str = options[0].firstChild.data;
		if (str == "" || str == " ") {
			alert("1-true");
			return true;
		}
	}
	for (loopIndex = 0; loopIndex < options.length; loopIndex = loopIndex + 1) {
		try {
			var str = options[loopIndex].firstChild.data;
			showSplit(str);
		}
		catch (ex) {
			alert(ex);
		}
	}
	return false;
}
function showSplit(str) {
	var splStr = str.split("--");
	var loopIndex = 0;
	while (splStr[loopIndex] != undefined) {
		try {
			var message = splStr[loopIndex];
			if (message != "" && message != " ") {
				var splMsg = splStr[loopIndex].split("-");
				var ele = document.getElementById(splMsg[0] + "error");
				ele.innerHTML = "*" + splMsg[1];
				ele.style.display = "inline";
				ele.style.fontSize = 12 + "px";
				ele.style.color = "red";
			}
		}
		catch (ex) {
		}
		loopIndex = loopIndex + 1;
	}
}
function checkNull(element) {
	var value = element.value;
	if (value == "") {
		alert("Please provide a valid" + element.label);
		element.focus;
		return false;
	}
	return true;
}
function checkEmail(emailField) {
	if (!checkNull(emailField)) {
		return false;
	}
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var emailString = emailField.value;
	if (!filter.test(emailString)) {
		alert("Please provide a valid email address");
		emailField.focus;
		return false;
	}
	return true;
}
function getHeadData(dataSource, divID) {
	var XMLHttpRequestObject = getXMLHttpRequestObject();
	if (XMLHttpRequestObject) {
		try {
			var obj = document.getElementById(divID);
			XMLHttpRequestObject.open("HEAD", dataSource);
			XMLHttpRequestObject.onreadystatechange = function () {
				if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
					obj.innerHTML = XMLHttpRequestObject.getAllResponseHeaders();
					//var date = new Date(XMLHttpRequestObject.getResponseHeader("Last-Modified"));
				} else {
					if (XMLHttpRequestObject.status == 404) {
						alert("URL doesnot exists");
					}
				}
			};
		}
		catch (ex) {
			alert(ex);
		}
	}
	XMLHttpRequestObject.send(null);
}
function dateFunction(date) {
	alert("Day (1-31): " + date.getDate());
	alert("Weekday (0-6, 0 = Sunday): " + date.getDay());
	alert("Month (0-11): " + date.getMonth());
	alert("Year (0-99-31): " + date.getYear());
	alert("Full year (four digits): " + date.getFullYear());
	alert("Day (1-31): " + date.getDate());
	alert("Day (1-31): " + date.getDate());
	alert("Hour (0-23): " + date.getHours());
	alert("Minutes (0-59): " + date.getMinutes());
	alert("Seconds (0-59): " + date.getSeconds());
}
function appendSessionId() {
	var sessionId = document.getElementById("org.apache.struts.taglib.html.TOKEN").value;
	return "org.apache.struts.taglib.html.TOKEN=" + sessionId;
}
