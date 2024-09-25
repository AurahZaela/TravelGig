$(document).ready(function(){
	$("#nameHolderButton").hide();
	
	var username = $("#nameHolderButton").val();
	$("#searchBtn").click(function(){
		var searchLocation = $("#searchLocation").val();
		$("#hotelTbl tr").not(".header").remove();
            $.get("/findHotel/" + searchLocation, function(response, status){
                $.each(response, function(key,value){
                    $("#hotelTbl").append("<tr><td>" + value.hotelName + "</td><td><img height='200' width='200' src='" 
                    + value.imageURL+"'/></td><td>"+value.starRating + "</td><td>" + value.averagePrice + "</td>" +
                    "<td><button type='button' class='open-my-Modal btn btn-primary' data-toggle='modal' data-target='#myModal' attrHotelId='" + value.hotelId + "'>Hotel Detail</button></td>" 
                    + "<td><button type='button' class='hotelReviewsLink btn btn-primary' data-toggle='modal' data-target='#hotelReviewsModal' attrHotelId='" + value.hotelId + "'>Hotel's Reviews</button></td>" + "</tr>")
                })
            })
        })
        
        
      $("#filterBtn").click(function(){
        
        $("#hotelTbl tr").not(":first").hide();  
        
        var sliderPrice = parseInt($("#priceValue").text());  
        var oneStar = $("#1_star_rating").is(":checked"); 
        var twoStar = $("#2_star_rating").is(":checked");
        var threeStar = $("#3_star_rating").is(":checked");
        var fourStar = $("#4_star_rating").is(":checked");
        var fiveStar = $("#5_star_rating").is(":checked");
        
        $("#hotelTbl tr").not(":first").each(function(idx, val) { 
            var price = $(this).children("td").eq("3").text(); 
            var star = $(this).children("td").eq("2").text();  

            if (price <= sliderPrice) {
                if (!(oneStar || twoStar || threeStar || fourStar || fiveStar)) {  // if no star is filtered
                    $(this).show() 
                }
                else if (star == 1 && oneStar) {
                    $(this).show() 
                }
                else if (star == 2 && twoStar) {
                    $(this).show()
                }
                else if (star == 3 && threeStar) {
                    $(this).show()
                }
                else if (star == 4 && fourStar) {
                    $(this).show()
                }
                else if (star == 5 && fiveStar) {
                    $(this).show()
                }
            }
        })  
    })    
	
	$("#hotelTbl").on("click", ".open-my-Modal", function() {
        var hotelName = $(this).parent().parent().children("td").eq(0).text();  
        $("#modal_hotelName").val(hotelName);  
        $("#modal_noRooms").val($("#noRooms").val());
        $("#modal_noGuests").val($("#noGuests").val());
        $("#modal_checkInDate").val($("#checkInDate").val());
        $("#modal_checkOutDate").val($("#checkOutDate").val());
        
        $("#select_roomTypes").empty()  
        var hotelId = $(this).attr("attrHotelId");  // grab hotelId to call getRoomTypesOfHotel/hotelId
        $.get("getRoomTypesOfHotel/" + hotelId, function(res) {  // call getRoomTypesOfHotel of TravelGig (this project), get response.
            $.each(res, function(idx, item) {  
                $('#select_roomTypes').append($("<option>", {  
                    value: item.typeId,
                    text: item.name
                }))
            })
        })
        $("#modal_hotelId").val(hotelId);
    })
    
     $("#searchHotelRoomsBtn").click(function() {
        $("#searchHotelRoomsModal").hide();
        $("#bookingHotelRoomModal").toggle();
        
        $("#booking_hotelName").val($("#modal_hotelName").val()); 
        $("#booking_noRooms").val($("#modal_noRooms").val());
        $("#booking_noGuests").val($("#modal_noGuests").val());
        $("#booking_checkInDate").val($("#modal_checkInDate").val());
        $("#booking_checkOutDate").val($("#modal_checkOutDate").val());
        var selectedRoomType_Text = $('#select_roomTypes').find(":selected").text(); 
        var roomTypeId = $('#select_roomTypes').find(":selected").val();
        $("#booking_roomType").val(selectedRoomType_Text);
       
        
        currentlySelectedRoomTypeId = roomTypeId;  
        
        var hotelId = $("#modal_hotelId").val();
        $.get("getRoomPriceAndDiscount/" + hotelId + "/" + roomTypeId, function(resRoomPriceAndDiscount) {  
            $.each(resRoomPriceAndDiscount, function(idx, item) {  
                var noRooms = 1;
                var priHolder=0;
                if (idx == 0) {  
                    if ($("#booking_noRooms").val()) { 
                        noRooms = $("#booking_noRooms").val();
                    }
                    else {
                    	$("#booking_noRooms").val(1);
                    }              
                    
                    $("#booking_price").text(item)      
                    
                    priHolder= $("#booking_price").text(item) ;        
                     
                }
                else if (idx == 1) {   
                    var start = $("#modal_checkInDate").val();
                    var end = $("#modal_checkOutDate").val();
                	var diffInDays = new Date(Date.parse(end) - Date.parse(start)) / 86400000;
                	if (isNaN(diffInDays)) {
                		$("#booking_discount").text("Please choose check in/out dates.");
                		$("#booking_price_total").text("Please choose check in/out dates.");
                	}
                	else {
                        $("#booking_discount").text(item);  
                	}
                }
                else {  // idx 2 is room id
                    currentlySelectedRoomId = Math.floor(item);  
                }
            })  
        }) 
    })
    
    
    
    
       $("#bookingHotelRoomModalClose").click(function() {
        $("#bookingHotelRoomModal").hide();
    });
    
        $("#bookingHotelRoomModalCloseOnX").click(function() {
        $("#bookingHotelRoomModal").hide();
    });
    
    
     $("#confirmBookingBtn").click(function() {
        var start = $("#modal_checkInDate").val();
        var end = $("#modal_checkOutDate").val();
        var diffInDays = new Date(Date.parse(end) - Date.parse(start)) / 86400000;
        
        var userName = "${username}";  
        console.log("userName: " + userName);
        if (!userName) {  
            alert("Please log in to book.");
        }
        else if (isNaN(diffInDays)) {
            alert("Please choose check in/out dates.");
        }
        else if (diffInDays < 1) {
        	alert("Please choose valid check in/out dates (in before out).");
        }
        else if ($("#booking_noRooms").val() < 1) {
            alert("Please choose valid number of rooms >= 1");
        }
        else {
            $("#bookingHotelRoomModal").hide();
            $("#completeBookingGuestInfoModal").toggle();
            //alert($("#booking_noGuests").val());
            
            $("#id_guestFirstName").val("");  // discard previous values
            $("#id_guestLastName").val("");
            $("#id_guestAge").val("");
            $("#id_guestGender").val("");
        }
    });  // end click of confirmBookingBtn
    
     var guestList = [];
    $("#id_addGuestBtn").click(function() {
        var guestFirstName = $("#id_guestFirstName").val();
        var guestLastName = $("#id_guestLastName").val();
        var guestAge = parseInt($("#id_guestAge").val());
        var guestGender = $("#id_guestGender").val();
        
        var guestToAdd = {"firstName": guestFirstName, "lastName": guestLastName, "gender": guestGender, "age": guestAge};
        guestList.push(guestToAdd);
        
        $("#tblGuest").append("<tr>" + "<td>" + guestFirstName + "</td>" + "<td>" + guestLastName + "</td>"
    	                             + "<td>" + guestAge + "</td>" + "<td>" + guestGender + "</td>"
                                     + "<td>" + "<button type='button' class='removeGuestFromTable btn btn-primary'>Remove</button>" + "</td>" 
                                     +"</tr>");
        

    });
    
        $("#completeBookingGuestInfoBtn").click(function() {
		var username = $("#nameHolderButton").text();
    	var bookingToPost = {
    	    "hotelId": $("#modal_hotelId").val(),
    	    "hotelRoomId": currentlySelectedRoomId,
    	    "noRooms": $("#booking_noRooms").val(),
    	    "guests": guestList,
    	    "checkInDate": $("#booking_checkInDate").val(),
    	    "checkOutDate": $("#booking_checkOutDate").val(),
    	    "status": "UPCOMING",
    	    "price": $("#booking_price").text(),
    	    "discount": $("#booking_discount").text(),
    	    "customerMobile": $("#booking_customerMobile").val(),
    	    "roomType": currentlySelectedRoomTypeId,
    	    "userName": username,  
    	    "userEmail": "josephjavasession77@gmail.com"
    	}
    	
       
        $.ajax({
            type: "POST",
            contentType: "application/json",  
            url: "http://localhost:8282/saveBooking",
            data: JSON.stringify(bookingToPost),  
            dataType: "application/json",  
            success: function(res) {  
            	alert("Thank you for booking!");
            	
            },
            error: function(e) {}
        });  
    	
    	guestList = [];  
    	$("#tblGuest tr:not(:first)").remove();  
        $("#id_guestFirstName").val();  
        $("#id_guestLastName").val();
        $("#id_guestAge").val();
        $("#id_guestGender").val();
    });
    
    
    $("#completeBookingGuestInfoModalClose").click(function() {
        $("#completeBookingGuestInfoModal").hide();
        guestList = [];  // empty guestList, so it won't keep adding duplicate guests on next booking.
        $("#tblGuest tr:not(:first)").remove();  // remove all but first row which is table header
    });
    
    
        // Opens modal upon clicking hotel rating.
    $("#hotelTbl").on("click", ".hotelReviewsLink", function() {
        $("#hotelReviewsModal").toggle();
        var sumOfUserRating = 0;
        var numReviews = 0;
        
        var hotelId = $(this).attr("attrHotelId");
        $.get("findAllReviewsByHotelId/" + hotelId, function(res) {  // call findAllReviewsByHotelId of TravelGig
        	$("#tblReviews tr:not(:first)").remove();  // remove all but first row which is table header
            $.each(res, function(idx, val) {
                $("#tblReviews").append("<tr>" + "<td>" + val.booking.userName + "</td>" + "<td>" + val.overallRating + "</td>"
                    + "<td>" + val.text + "</td>"
                    + "</tr>");
                sumOfUserRating += val.overallRating;
                numReviews++;
            });
        	
            var avgUserRating = sumOfUserRating / numReviews;
            $("#id_avgUserRating").text("User Rating: " + avgUserRating);
        });
        
        return false;
    });
    
    $("#hotelReviewsModalClose").click(function() {
        $("#hotelReviewsModal").hide();
        $("#tblReviews tr:not(:first)").remove();  // remove all but first row which is table header
    });
    
    $("#hotelReviewsModalCloseOnX").click(function() {
        $("#hotelReviewsModal").hide();
        $("#tblReviews tr:not(:first)").remove();  
    });
    
    
    
    
	
})