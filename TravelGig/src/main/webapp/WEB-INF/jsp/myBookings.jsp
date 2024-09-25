<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Booking</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="./js/booking.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container" style="margin-left:100px">
<h1>My Bookings</h1>
<%
Object username = request.getAttribute("username");  // request.getAttribute("username") can be in java codes. ${username} can be in jsp codes.
%>
<span>Welcome <%=username%>! &nbsp; <a href='home'>Home</a> &nbsp; <a href='login?logout'>Logout</a> </span>
</div>
<button value="${username}" id="nameHolderButton" >${username}</button>
<br/><br/>
<div class="row">

<div class="col-11 border rounded" style="margin-left:50px;">
    <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'>List of My Bookings</div>   
    
    <div id="listBookings">
        <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'>Upcoming</div>   
        <table class="table table-dark table-hover table-primary" id="tblBookingsUpcoming" border="1">
            <tr> <th>Hotel</th> <th>Number of Rooms</th> <th>Check In</th> <th>Check Out</th> <th>Room Type</th> <th>Booking Status</th> <th>Total Price</th> <th>Cancel</th> </tr>
        </table>
        
        <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'>Completed</div>   
        <table class="table table-dark table-hover table-primary" id="tblBookingsCompleted" border="1">
            <tr> <th>Hotel</th> <th>Number of Rooms</th> <th>Check In</th> <th>Check Out</th> <th>Room Type</th> <th>Booking Status</th> <th>Total Price</th> <th>Write Review</th> </tr>
        </table>
        
        <div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'>Canceled</div>   
        <table class="table table-dark table-hover table-primary" id="tblBookingsCanceled" border="1">
            <tr> <th>Hotel</th> <th>Number of Rooms</th> <th>Check In</th> <th>Check Out</th> <th>Room Type</th> <th>Booking Status</th> <th>Total Price</th> </tr>
        </table>
    </div>
    
</div>
</div>


<div class="modal" id=writeReviewsModal>
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Write Your Review</h4>
        <button type="button" class="close" data-dismiss="modal" id="writeReviewsModalCloseOnX">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" id="writeReviews_modalBody">
            <div class="col">
                <div><input class="form-control" type="hidden" id="review_bookingId"/></div>
                
                <div class="container border rounded" style="margin:auto;padding:50px;margin-top:50px;margin-bottom:50px">
                    <div class="form">
                        <div class="slidecontainer">
                            Your Rating
                            <input type="range" min="1" max="10" value="10" class="slider" id="starRange">
                            <p>Stars: <span id="starValue"></span></p>
                        </div>
                        <div>
                            Your Review <textarea class="form-control" id="id_reviewText"></textarea>
                        </div>
                    </div>  <!-- end form -->
                    <br/>
                    <input class="btn-sm btn-primary" type="button" id="id_submitReviewBtn" value="Submit Review"/>
                </div>  <!-- end container border rounded -->
                
            </div> 
      </div> 
      

      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal" id="writeReviewsModalClose">Close</button>
      </div>
      
    </div>
  </div>
</div>


<script>
var slider = document.getElementById("starRange");
var output = document.getElementById("starValue");
output.innerHTML = slider.value;
slider.oninput = function() {
    output.innerHTML = this.value;
}
</script>
</body>
</html>