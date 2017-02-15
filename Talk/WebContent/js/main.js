var webSocket = new WebSocket('ws://localhost:8080/Talk/talk');
var regist = 0;

function load() {
	setWebSocket();
	setBtnSend();
	setBtnTarget();
}

// 设置websocket
function setWebSocket() {
	webSocket.onerror = function(event) {

	};

	webSocket.onopen = function(event) {
		var message = "regist(" + $("#accountName").text() + ")";
		sendMessage(message);
	};

	webSocket.onmessage = function(event) {
		var targetName = $("#targetAccountName").text();
		var temp = $("#talks").text();
		var message = event.data;
		if (message.indexOf(targetName + ">") > 0) {
			$("#talks").text(temp + "\n" + message);
		} else {
			$("#message").text(message);
		}
	}
}

// 向服务器websocket发送数据
function sendMessage(message) {
	webSocket.send(encodeURI(message));
}

function setBtnSend() {
	$(document).ready(
			function() {
				$("#send").click(
						function() {
							var srcName = $("#accountName").text();
							var targetName = $("#targetAccountName").text();
							var talk = $("#talk").val();
							var message = "from(" + srcName + ")to("
									+ targetName + ")talk(" + talk + ")";
							sendMessage(message);
							var temp = $("#talks").text();
							$("#talks")
									.text(temp + "\n" + srcName + ">" + talk);
						});
			});
	
}

function setBtnTarget() {
	$(document).ready(function() {
		
	});
}

function processRecieveMessage(message) {
	

}
