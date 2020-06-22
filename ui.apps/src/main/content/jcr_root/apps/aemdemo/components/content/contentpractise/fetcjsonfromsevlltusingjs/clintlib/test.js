
alert("heyy");
$(document).ready(function(){

	    alert("Hello00 Jquery");


	     $("#getJsonData").click(function(){	   
	
	      $.ajax({
	           type: 'GET',
	           url: '/bin/test',
	          contentType:'application/json',
	          dataType: 'json',	         
	          success: function(result){
	        	  var json =JSON.stringify(result) 
                  var result = jQuery.parseJSON(json);
                  alert(result.EmployeeId + ""+result.Name +""+result.isPermanent +""+result.Nickname);
	        	  //result.EmployeeId;

               var EmployeeId= result.EmployeeId;
               var Name = result.Name;
               var Salary = result.Salary;
                var isPermanent = result.isPermanent;
               var Nickname = result.Nickname;   

                  $('#id').html(EmployeeId);
                    $('#name').html(Name);
                    $('#salary').html(Salary);
                    $('#isperm').html(isPermanent);
                    $('#nickname').html(Nickname);


	        },
	        error: function(err) {
	             alert("Unable to retrive data "+err);
	        }
	    });
	         });

	});


