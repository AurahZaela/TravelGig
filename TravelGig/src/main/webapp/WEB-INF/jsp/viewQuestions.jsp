<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Questions and Answers</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="./js/viewQAs.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 

<style>
	.answer{
		display: none;
	}
	
	.question{
		cursor: pointer;
		color: blue;
	}
</style>
</head>
<body >
<div class="container" style="margin-left:100px">
<h1>Frequently Asked Questions</h1>
<%
Object username = request.getAttribute("username");  
%>
<span>Welcome <%=username%>!  &nbsp; <a href='home'>Home</a> &nbsp; <a href='login?logout'>Logout</a></span>
</div>

<button value="${username}" id="nameHolderButton">${username}</button>
<br/><br/>

<div class="faq" style="margin-left:50px;">
	<div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'></div>   
	
	<h3 class="question">How Do I Book a Hotel?</h3>
		<div class="answer">
			You can book a hotel by searching for a location and even filter by specifications such as rating and/or price.
			Afterwards specify your check-in and check-out date and then register the name(s) of the people who will be staying there.
			When done, you will receive a confirmation email containing your booking details. 		
		</div>
	
	<h3 class="question">Can I Cancel a Booking?</h3>
		<div class="answer">
			You can check on your bookings and cancel them as long as the check-in date hasn't gone past the current date. 
			If a previous booking of yours has been completed, then please leave a review!
		</div>
		
		
		
		
</div>
<br>

<div class="container border rounded" style="margin:auto;padding:50px;margin-top:50px;margin-bottom:50px">
	<h3>Got a Question? Ask it here!</h3>
	<div class="form-row">
	<div class="col-3">
		<input style='' class="form-control" type="text" id="qToAsk" name="Inquiry"/>
	</div>
	<input class="btn-sm btn-primary" type="button" id="saveQuestion" value="SEND"/>
</div>
</div>



<div class="row">
<security:authorize access="hasAuthority('ADMIN')">
<div class="col-11 border rounded" style="margin-left:50px;">
    <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'>All Questions From Users</div>   
    
    <div id="listQuestions">
        <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'></div>   
        <table class="table table-dark table-hover table-primary" id="tblAllQuestions" border="1">
            <tr> <th>Question</th> <th>Answer</th> <th>Status</th> <th>From</th> <th>Response</th></tr>
        </table>
    	</div>
    </div>
</security:authorize>
</div>

<div class="row">
<security:authorize access="hasAuthority('USER')">
<div class="col-11 border rounded" style="margin-left:50px;">
    <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'>All My Questions</div>   
    
    <div id="listQuestions2">
        <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'></div>   
        <table class="table table-dark table-hover table-primary" id="tblUserQuestions" border="1">
            <tr> <th>Question</th> <th>Answer</th> <th>Status</th> <th>From</th></tr>
        </table>
    	</div>
    </div>
</security:authorize>
</div>



<div class="modal" id="addAnswerModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Question To Be Answered</h4>
        <button type="button" class="close" data-dismiss="modal" id="answerRoomModalCloseOnX">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" id="answerModalBody">        
        	<div class="col">
        		<div><input class="form-control" type="hidden" id="qId"/></div>
	        	<div>Question<input readonly="true" class="form-control" type="text" id="question"/></div>
	        	<div>Answer <input class="form-control" type="text" id="answer"/></div>
       			<div>Status <input readonly="true" class="form-control" type="text" id="status"/></div>
       			<div>From <input readonly="true" class="form-control" type="text" id="from"/></div>
		
       			<div style='margin-top:20px'>
       				<button class='btn-confirm-booking btn btn-primary' id="answerBtn">Send Answer</button>
       			</div>
        	</div>          
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal" id="answerRoomModalClose">Close</button>
      </div>

    </div>
  </div>
</div>



</body>
</html>