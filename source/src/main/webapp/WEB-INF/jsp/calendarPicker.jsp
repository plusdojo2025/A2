<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Datepickerカレンダー</title>
<script src="<c:url value='https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js' /> "></script>
<link rel="stylesheet" href="<c:url value='https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css' />">
<script src="<c:url value='https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js' /> "></script>

	<style>
		/* 予定のある日の枠の色 */
		.highlight {
		  background-color: red;
		}

		/* 表示箇所を真ん中にするCSS */
		.ui-datepicker {
			margin-left:30%;
		}
	</style>
</head>
<body>
    <div id="app">
    <div id="datepicker"></div>

</div>

 <script>
     //日付を擬似的に作成する（テストの為）
     var dates = [];
     dates.push(new Date("2023-06-10"));
     dates.push(new Date("2023-06-10"));
     dates.push(new Date("2023-06-15"));
     dates.push(new Date("2023-06-24"));
	//もしデータがある場合は、Servletからデータを取得して、javaScript配列のlistに入れる


	//読み込まれた際に実行する
     $(document).ready(function() {
    	 //idがdatepickerのところに表示する
    	  $("#datepicker").datepicker({



    		//カレンダーに赤枠を表示するメソッド----------------------
    	    beforeShowDay: function(date) {
    	    //ループで配列に格納された日付のところを赤色にする
    	      for (var i = 0; i < dates.length; i++) {
    	    	//ひとつ取り出す
    	        var targetDate = dates[i];
				//もし合致するデータがあったら
    	        if (
    	          date.getFullYear() === targetDate.getFullYear() &&
    	          date.getMonth() === targetDate.getMonth() &&
    	          date.getDate() === targetDate.getDate()
    	        ) {
    	          //色を変える
    	          return [true, "highlight", "Custom text"];
    	        }
    	      }
			  //なんやろかよくわからん
    	      return [true, "", ""];
    	    },

	        //各dayをクリックした際に処理されるメソッド--------------------
	        onSelect: function(dateText, inst) {
	        	//クリックするたび、表示されたボックスを削除する
	        	 // .aaaクラスの要素を取得
	        	  var aaaElements = document.getElementsByClassName("aaa");
	        	  // 要素が存在する場合にのみ削除処理を実行
	        	  if (aaaElements.length > 0) {
	        		  //作業途中のボックスのための確認ダイアログ
	        		  if (confirm("開いているボックスがあります。閉じてもよろしいですか？")) {
	        			  //消す
	        			  while (aaaElements.length > 0) {
	    	        	      aaaElements[0].remove();
	    	        	  }
	        		  }else {
	        			 	return false;
	        		  }
	        	  }
	            // 押した日付を年、月、日で取得する
	            var ans = dateText.split("/");
	            var year = ans[2];
	            var month = ans[0];
	            var day = ans[1];

	            //合致しないフラグ
	            var flg=0;

	            //HTMLの作成
	            for (var i = 0; i < dates.length; i++) {
	            	//今日の日付と合致する予定があったかどうか
				  	if (
	          	          year == dates[i].getFullYear() &&
	          	          month == dates[i].getMonth()+1 &&
	          	          day == dates[i].getDate()
	          	        ) {
	            		 // 要素の作成（合致するものがあったので、データを入れる）
	    	            const divElement = document.createElement('div');
	    	            divElement.classList.add('aaa');

	    	            const formElement = document.createElement('form');
	    	            formElement.action = '飛び先';
	    	            formElement.method = 'post';

	    	            const inputElement = document.createElement('input');
	    	            inputElement.type = 'text';
	    	            inputElement.name = 'date';
	    	            //今回データを入れている場所
	    	            inputElement.value =dates[i];

	    	            const submitElement = document.createElement('input');
	    	            submitElement.type = 'submit';
	    	            submitElement.value = 'クリック';

	    	            formElement.appendChild(inputElement);
	    	            formElement.appendChild(submitElement);

	    	            divElement.appendChild(formElement);

	    	            const bodyElement = document.querySelector('body');
	    	            bodyElement.appendChild(divElement);

	    	            flg++;
	          		 }
				 }
	            //HTMLの作成：合致するものが無かったら空の要素を作成する
	         	if(flg==0){
			  		// 要素の作成
	   	            const divElement = document.createElement('div');
	   	            divElement.classList.add('aaa');

	   	            const formElement = document.createElement('form');
	   	            formElement.action = '飛び先';
	   	            formElement.method = 'post';

	   	            const inputElement = document.createElement('input');
	   	            inputElement.type = 'text';
	   	            inputElement.name = 'date';
	   	         	//合致しなかったのでデータを入れなかった場所
	   	            inputElement.value ='';

	   	            const submitElement = document.createElement('input');
	   	            submitElement.type = 'submit';
	   	            submitElement.value = 'クリック';

	   	            formElement.appendChild(inputElement);
	   	            formElement.appendChild(submitElement);

	   	            divElement.appendChild(formElement);

	   	            const bodyElement = document.querySelector('body');
	   	            bodyElement.appendChild(divElement);
			  	}
	         }
        });
    });
  </script>


</body>
</html>