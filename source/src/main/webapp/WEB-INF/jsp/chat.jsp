<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat Application</title>
<link rel="stylesheet" href="<c:url value='/etcProject/css/chat.css'/>">

</head>
<body onload="connect()">
    <div id="chat-container">
        <div id="messages" class="messages"></div>
        <div class="input-area">
            <input type="text" id="message" placeholder="メッセージを入力してください" onkeydown="if(event.key === 'Enter') sendMessage()">
            <button onclick="sendMessage()">送信</button>
        </div>
    </div>
    
    
    <script>
	    var socket;
	    var user_id_speaker = "one"; // 送信者のユーザーIDを文字列にする
	    var user_id_listener = "two"; // 受信者のユーザーIDを文字列にする
	
	    function connect() {
	        // WebSocketを初期化するで
	        socket = new WebSocket("ws://" + window.location.host + "/etcProject/chat");
	
	        // 接続が開いたときの処理やで
	        socket.onopen = function() {
	            console.log("WebSocket connection opened");
	            document.getElementById("messages").innerHTML += "<div class='system-message'>チャットサーバーに接続しました。</div>";
	            // ユーザーIDをサーバーに送信するで
	            var initMessage = JSON.stringify({ type: 'init', user_id_speaker: user_id_speaker, user_id_listener: user_id_listener });
	            socket.send(initMessage);
	        };
	
	        // メッセージを受信したときの処理やで
	        socket.onmessage = function(event) {
	            console.log("Received message: ", event.data);
	            var data = event.data.split(" ");
	            var createdAt = data.slice(0, 2).join(" ");
	            var speaker = data[2];
	            var listener = data[3];
	            var message = data.slice(4).join(" ");
	            var messageClass = (speaker === user_id_speaker) ? "sent-message" : "received-message";
	            var messageElement = "<div class='" + messageClass + "'><div class='message-time'>" + createdAt + "</div><div class='message-content'>" + message + "</div></div>";
	            document.getElementById("messages").innerHTML += messageElement;
	            document.getElementById("messages").scrollTop = document.getElementById("messages").scrollHeight;
	        };
	
	        // 接続が閉じたときの処理やで
	        socket.onclose = function() {
	            console.log("WebSocket connection closed");
	            document.getElementById("messages").innerHTML += "<div class='system-message'>チャットサーバーから切断されました。</div>";
	        };
	
	        // エラーが発生したときの処理やで
	        socket.onerror = function(event) {
	            console.error("WebSocket error: ", event);
	            document.getElementById("messages").innerHTML += "<div class='system-message'>エラー: " + event.data + "</div>";
	        };
	    }
	
	    // メッセージを送信する関数やで
	    function sendMessage() {
	        var message = document.getElementById("message").value;
	        var now = new Date();
	        var formattedTime = now.getFullYear() + "-" +
	                            ('0' + (now.getMonth() + 1)).slice(-2) + "-" +
	                            ('0' + now.getDate()).slice(-2) + " " +
	                            ('0' + now.getHours()).slice(-2) + ":" +
	                            ('0' + now.getMinutes()).slice(-2) + ":" +
	                            ('0' + now.getSeconds()).slice(-2);
	        // メッセージを送信するで
	        var messageToSend = formattedTime + " " + user_id_speaker + " " + user_id_listener + " " + message;
	        socket.send(messageToSend);
	        document.getElementById("message").value = "";
	    }
	</script>
</body>
</html>
