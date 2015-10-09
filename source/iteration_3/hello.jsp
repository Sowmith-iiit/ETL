<!DOCTYPE html>
<html>
<head>
	<title>Sample JSP Page</title>
	<link rel="stylesheet" href="./css/styles.css" type="text/css"/>
	<link rel="stylesheet" href="./css/new.css" type="text/css"/>
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
	function sub(){
	$.ajax({
	    type: 'get',
	    url: '',
	    dataType: 'JSON',
	    data: { 
	      loadProds: 1,
	      test: JSON.stringify(test) // look here!
	    },
	    success: function(data) {
			alert(data);
			console.log("dfhdjfhjd");
	    },
	    error: function(data) {
	        alert('fail');
	        console.log("fjghdjghfjhg");
	    }
	});
	}
	</script>
</head>
<body>
<h1>NOT A Sample JSP Page ITS going to be our Project</h1>
<%@ page import="java.util.*" %>
<h2>Time on server: <%= new Date() %></h2>
<p>
This is a simple JSP page. When first learning, make a new Dynamic Web app in Eclipse,
copy this file to the WebContent folder, deploy the app, start the server, and access 
the page with http://localhost/<i>appName</i>/hello.jsp.
</p>
<a href="#" > Just Checking </a>
<form onsubmit="sub()">
	<input name="in" type="text" >
	<input type="submit" name="sub" value="Submit" > 
</form>
</body></html>