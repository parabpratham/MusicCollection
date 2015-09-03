
function getXMLHttpRequestObject() {
	var XMLHttpRequestObject = false;
	if (window.XMLHttpRequest) {
		XMLHttpRequestObject = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return XMLHttpRequestObject;
}
/*
moveMove events ==> when the user drags an item, and mouseUp events
mouseUp  events ==> which occur when the user drops a dragged item.
addListener ==> to connect the mouseMove event to  a function named handleMove, and the mouseUp event to a function named handleUp:
*/
/*The addListener function connects events to functions you want called
when those events occur, and how you do that depends on which browser
the user has. Heres what this function looks like:
*/
function addListener(type, callback) {
	if (document.addEventListener) {
		document.addEventListener(type, callback, false);
	} else {
		if (document.attachEvent) {
			document.attachEvent("on" + type, callback, false);
		}
	}
}
var offsetX, offsetY;
var divX = 0, divY = 0;
var sourceElement;
/*
code that creates the MouseEvent object that the rest of the
application can use without having to worry about what browser is involved.
Note the use of the keyword this here, which is how you refer to the current
object in JavaScript:
*/
function MouseEvent(e) {
	if (e) {
		this.e = e;
	} else {
		this.e = window.event;
	}
	if (e.pageX) {
		this.x = e.pageX;
	} else {
		this.x = e.clientX;
	}
	if (e.pageY) {
		this.y = e.pageY;
	} else {
		this.y = e.clientY;
	}
	if (e.target) {
		this.target = e.target;
	} else {
		this.target = e.srcElement;
	}
}
/*
When the user moves the mouse, you have to move the HTML element
theyre dragging. To do that, you should record the location at which the
mouse was pressed inside that element. The reason for doing so is that when
the user moves an element, you want to make the element's new location
match the new mouse location. To move an element by using styles, you can
position its top-left corner to match the new mouse location, but if the user
pressed the mouse somewhere inside the element, you have to keep in mind
that the upper-left corner doesnt necessarily correspond to the mouse location
in the element. To account for that, you can store the X and Y offset of
the mouse with respect to the upper-left corner of the dragged element, like
this:
*/
function handleDown(e) {
	sourceElement = e.target;
	var e = new MouseEvent(e);
	addListener("mousemove", handleMove);
	addListener("mouseup", handleUp);
	if (divX == 0) {
		divX = parseInt(sourceElement.style.left);
	}
	if (divY == 0) {
		divY = parseInt(sourceElement.style.top);
	}
	offsetX = e.x - parseInt(e.target.style.left);
	offsetY = e.y - parseInt(e.target.style.top);
	document.getElementById("targetDiv5").innerHTML = "";
}
/*When the user drags the mouse, your handleMove function will be called. In
that function, you should move the television <div> to match the new
location of the mouse (after taking into account the offset of the mouse inside
the <div>). The handleMove function starts by creating a new MouseEvent
object so it can decode where the mouse is:
*/
function handleMove(e) {
	var e = new MouseEvent(e);
	var x = e.x - offsetX;
	e.target.style.left = x + "px";
	var y = e.y - offsetY;
	e.target.style.top = y + "px";
	var target = document.getElementById("target");
	target.style.left = 300 + "px";
	target.style.top = 700 + "px";
}
/*
Handling mouse up events
When the user drops the item hes dragging, the handleUp function will be
called, and the first order of business is to create a MouseEvent object to get
the location at which the user dropped the dragged HTML element. Heres how:
*/
function handleUp(e) {
	var e = new MouseEvent(e);
	removeListener("mousemove", handleMove);
	removeListener("mouseup", handleUp);
	var target = document.getElementById("target");
	var x = parseInt(target.style.left);
	var y = parseInt(target.style.top);
	var width = parseInt(target.style.width);
	var height = parseInt(target.style.height);
	
	//check if user dropped in cart
	if (e.x > x && e.x < x + width && e.y > y && e.y < y + height) {
		//XMLHttpRequestObject = getXMLHttpRequestObject();
		//XMLHttpRequestObject.open("GET", "/StrutsTut/ch01/pizzas.txt");
		//XMLHttpRequestObject.onreadystatechange = function () {
			//if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
			//	document.getElementById("targetDiv5").innerHTML = XMLHttpRequestObject.responseText;
			//	delete XMLHttpRequestObject;
			//	XMLHttpRequestObject = null;
		//	}
		//};
	//	XMLHttpRequestObject.send(null);
		document.getElementById("targetDiv5").innerHTML = "" + sourceElement.id;
	}
	sourceElement.style.left = divX + "px";
	sourceElement.style.top = divY + "px";
	divX = 0;
	divY = 0;
	sourceElement = "";
}
/*
Now that the user has released the mouse button, any dragging operation
that was going on is over, so you can stop responding to mouse events until
the next mouse down event. To stop responding to mouseMove and mouseUp
events, you can remove the listener functions you connected to those events
earlier by using a new function, removeListener, like so:
*/
function removeListener(type, callback) {
	if (document.removeEventListener) {
		document.removeEventListener(type, callback, false);
	} else {
		if (document.detachEvent) {
			document.detachEvent("on" + type, callback, false);
		}
	}
}

