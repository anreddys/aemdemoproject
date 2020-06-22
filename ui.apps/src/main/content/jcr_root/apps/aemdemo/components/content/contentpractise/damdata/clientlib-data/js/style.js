$(document).ready(function(){
   alert("Dam Data Jquery ");
   $("#submit").click(function(){
      // alert("Called");

 //Use JQuery AJAX request to post data to a Sling Servlet
    $.ajax({
          type: 'GET',
          url: '/content/dam/test/json/sample',
          success: function(msg){
              alert(msg);
              console.log(msg);
       //  alert("result from servlet"+msg);
       // var json = jQuery.parseJSON(msg); 

           // $('#json').val("Filed by " + firstName + " " + lastName); 
      },
        error: function(err) {
             alert("Unable to retrive data "+err);
        }
    });
         });
});







