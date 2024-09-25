
$(document).ready(function(){
	


	$('#qToAsk').on('input', function () {
            this.style.height = 'auto';
 
            this.style.height =
                (this.scrollHeight) + 'px';
        });
	
	
$("#nameHolderButton").hide();
var userName = $("#nameHolderButton").text()



    $('.faq h3').on('click', function(){
		$(this)
			.next()
			.slideToggle();
	})
	
	$("#saveQuestion").click(function(){
		var qaToPost = {
			"question": $("#qToAsk").val(),
			"personAsked": $("#nameHolderButton").val()
		}
		$.ajax({
            type: "POST",
            contentType: "application/json",  
            url: "http://localhost:8282/saveQA",
            data: JSON.stringify(qaToPost),  
            dataType: "json",  
            success: function(res) {  
                alert("Thank you for your Question!");
                console.log($("#qToAsk").val());
                console.log("Saved:");
                console.log(res);
            },
            error: function(e) {}
        });  // end ajax post
		
		
	})//end of SaveQuestion
	
	    $.get("findAllQs", function(res) {  // call findAllByUserName of TravelGig (this project), get response.
        $("#tblAllQuestions tr:not(:first)").remove();  // remove all but first row which is table header
        console.log(res);
        $.each(res, function(idx, val) {
			
                        $("#tblAllQuestions").append("<tr>" + "<td class='qVal' >" + val.question + "</td>" + "<td class='aVal'>" + val.answer + "</td>"
                            + "<td class='statusVal'>" + val.status + "</td>" + "<td class='askVal'>" + val.personAsked + "</td>"
                            + "<td>" + "<button type='button' class='add-answer btn btn-primary' data-toggle='modal' data-target='#addAnswerModal' attrId='" + val.id + "'>Answer</button>" + "</td>"
                        + "</tr>");
                    
                	
                 
        });  // end for each
    });  // end ajax get findAll
    
    $.get("findAllQsByUsername/"+ userName, function(res) {  // call findAllByUserName of TravelGig (this project), get response.
        $("#tblUserQuestions tr:not(:first)").remove();  // remove all but first row which is table header
        console.log(res);
        $.each(res, function(idx, val) {
			
                        $("#tblUserQuestions").append("<tr>" + "<td class='qVal' >" + val.question + "</td>" + "<td class='aVal'>" + val.answer + "</td>"
                            + "<td class='statusVal'>" + val.status + "</td>" + "<td class='askVal'>" + val.personAsked + "</td>"
                            + "</tr>");
                    
                	
                 
        });  // end for each
    });  // end ajax get findByUsername
    
    
	$("#tblAllQuestions").on("click", ".add-answer", function() {
    	var idV=$(this).attr("attrId");
    	var questionV = $(this).closest("tr").find(".qVal").text();
    	var answerV = $(this).closest("tr").find(".aVal").text();
    	var statusV = $(this).closest("tr").find(".statusVal").text();
    	var askedV = $(this).closest("tr").find(".askVal").text();
    	
    	
    	
    	$("#question").val(questionV)
    	$("#answer").val(answerV)
    	$("#status").val(statusV)
    	$("#from").val(askedV)
    	
    	$("#qId").val(idV)
	
		//alert($("#qId").val())
		
    });  
    
    
    $("#answerBtn").click(function(){
		var questionToAnswer = {
			"id": $("#qId").val(),
			"question": $("#question").val(),
			"answer": $("#answer").val(),
			"personAsked": $("#from").val()
		}
		
		 $.ajax({
            type: "POST",
            contentType: "application/json",  
            url: "http://localhost:8282/updateQ",
            data: JSON.stringify(questionToAnswer),  
            dataType: "json",  
            success: function(res) {  
            	alert("Answer Sent");
            	
            },
            error: function(e) {}
        });  
	})
    


})//End of $(document).ready