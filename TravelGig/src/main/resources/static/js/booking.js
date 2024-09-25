
$(document).ready(function(){
	$("#nameHolderButton").hide();
	var userName = $("#nameHolderButton").val();
    //var userName = "${username}";  // without quotations, it thinks evaluated ${username} is a variable and complains variable username is not found.
  
  
    $.get("findAllByUserName/" + userName, function(res) {  // call findAllByUserName of TravelGig (this project), get response.
        $("#tblBookingsCompleted tr:not(:first)").remove();  // remove all but first row which is table header
        console.log(res);
        
        $.each(res, function(idx, val) {
            var hotelName = "n/a";
            $.get("findHotelById/" + val.hotelId, function(hotel) {  // call findHotelById of TravelGig (this project), get response.
                $.get("findRoomTypeById/" + val.roomType, function(roomType) {  // call findRoomTypeById of TravelGig (this project), get response.
                    
                    if (val.status == "UPCOMING") {
                        $("#tblBookingsUpcoming").append("<tr>" + "<td>" + hotel.hotelName + "</td>" + "<td>" + val.noRooms + "</td>"
                            + "<td>" + val.checkInDate + "</td>" + "<td>" + val.checkOutDate + "</td>"
                            + "<td>" + roomType.name + "</td>"
                            + "<td>" + val.status + "</td>" + "<td>" + val.price + "</td>"
                            + "<td>" + "<button type='button' class='cancel-booking' attrBookingId='" + val.bookingId + "'>Cancel Booking</button>" + "</td>"
                        + "</tr>");
                    }
                	
                    if (val.status == "COMPLETED") {
                        $("#tblBookingsCompleted").append("<tr>" + "<td>" + hotel.hotelName + "</td>" + "<td>" + val.noRooms + "</td>"
                            + "<td>" + val.checkInDate + "</td>" + "<td>" + val.checkOutDate + "</td>"
                            + "<td>" + roomType.name + "</td>"
                            + "<td>" + val.status + "</td>" + "<td>" + val.price + "</td>"
                            + "<td>" + "<button type='button' class='write-review' attrBookingId='" + val.bookingId + "'>Write Review</button>" + "</td>"
                        + "</tr>");
                    }
                    
                    if (val.status == "CANCELED") {
                        $("#tblBookingsCanceled").append("<tr>" + "<td>" + hotel.hotelName + "</td>" + "<td>" + val.noRooms + "</td>"
                            + "<td>" + val.checkInDate + "</td>" + "<td>" + val.checkOutDate + "</td>"
                            + "<td>" + roomType.name + "</td>"
                            + "<td>" + val.status + "</td>" + "<td>" + val.price + "</td>"
                        + "</tr>");
                    }
                    
                });  // end ajax get findRoomTypeById
            });  // end ajax get findHotelById
        });  // end for each
    });  // end ajax get findAllByUserName
    

    $("#tblBookingsUpcoming").on("click", ".cancel-booking", function() {
    	var bookingId = $(this).attr("attrBookingId");
        $.ajax({
            type: "DELETE", 
            url: "http://localhost:8282/cancelBookingById/" + bookingId,  
            success: function(res) { 

            	location.reload();  
                alert("Booking canceled");
            },
            error: function(e) {
            	alert("Cannot cancel at this time. Please contact customer service.")
            }
        });  // end ajax delete
        
        // return false;  // prevent default behavior of anchor tag (redirecting)
    });  // end cancel-booking button click
    
    
    // Upon click of write-review button on completed bookings table, open modal.
    $("#tblBookingsCompleted").on("click", ".write-review", function() {
        $("#writeReviewsModal").toggle();
    	$("#review_bookingId").val($(this).attr("attrBookingId"));  // save currently selected bookingId
    });
    
    $("#writeReviewsModalClose").click(function() {
        $("#writeReviewsModal").hide();
    });
    
    $("#writeReviewsModalCloseOnX").click(function() {
        $("#writeReviewsModal").hide();
    });
    
    
    // Upon click of submit review button, save review.
    $("#id_submitReviewBtn").click(function() {    
        var reviewToPost = {
            "booking": {"bookingId": $("#review_bookingId").val()},
            "text": $("#id_reviewText").val(),
            "overallRating": parseInt($("#starValue").text())
        }
        
        $.ajax({
            type: "POST",
            contentType: "application/json",  
            url: "http://localhost:8282/saveReview",
            data: JSON.stringify(reviewToPost),  
            dataType: "json",  
            success: function(res) {  
                alert("Thank you for your review!");
                console.log($("#review_bookingId").val());
                console.log("Saved:");
                console.log(res);
            },
            error: function(e) {}
        });  // end ajax post
    });  // end submit review button click
    
})