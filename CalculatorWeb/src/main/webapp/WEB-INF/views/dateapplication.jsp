<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>  
<meta charset="UTF-8">
<title>Date Application</title>
</head>
<body>
<h1 style ='text-align:center;'>Date Application</h1>
<br>
<h2 style ='text-align:center;'>Choose an operation from below </h2>
<br>

<div align = 'center'>
<form id= 'mainForm'>


 <input type="radio" id="addition" name="operation" value = '1'>

  <label for="addition">Add two dates</label><br><br>


  <input type="radio" id="subtraction" name="operation" value = '2'>
<label for="subtraction">Absolute difference of two dates</label><br><br>


  <input type="radio" id="addNUnits" name="operation" value = '3'>
<label for="addNUnits">Adding N units </label><br><br>


  <input type="radio" id="subtractNUnits" name="operation" value = '4'>
<label for="subtractNUnits">Subtracting N units</label><br><br>


  
  <input type="radio" id="findDay" name="operation" value ='5'>
  <label for="findDay">Day Of the Week</label><br><br>
 

   <input type="radio" id="findWeek" name="operation" value = '6'>
  <label for="findWeek">Week No For the Date</label><br><br>


<button type = 'button' onclick = 'getOptionValue()'>Proceed</button>
<br><br>
</form>


<form id = '1' action = '/spring-mvc/dispatch' style = 'display:none;'>
	Enter first date - <input type = 'text' name = 'firstDate'><br><br>
	Enter second date -<input type = 'text' name = 'secondDate'><br><br>
	<input type = 'hidden' name = 'operation' value = '1'>
	<input type = 'submit' name='adding2' value = 'Submit'>
</form>

<form id = '2' action = '/spring-mvc/dispatch' style = 'display:none;'>
	Enter first date - <input type = 'text' name = 'firstDate'><br><br>
	Enter second date -<input type = 'text' name = 'secondDate'><br><br>
	<input type = 'hidden' name = 'operation' value = '2'>
	<input type = 'submit' name='subtracting2' value = 'Submit'>
</form>

<form id = '3' action = '/spring-mvc/dispatch' style = 'display:none;'>
	Enter date - <input type = 'text' name = 'firstDate'><br><br>
	Enter N units for addition -<input type = 'text' name = 'nUnits'><br><br>
	<input type = 'hidden' name = 'operation' value = '3'>
	<input type = 'submit' name='addingN' value = 'Submit'>
</form>

<form id = '4' action = '/spring-mvc/dispatch' style = 'display:none;'>
	Enter date - <input type = 'text' name = 'firstDate'><br><br>
	Enter N units for subtraction-<input type = 'text' name = 'nUnits'><br><br>
	<input type = 'hidden' name = 'operation' value = '4'>
	<input type = 'submit' name='subtractingN' value = 'Submit'>
</form>


<form id = '5' action = '/spring-mvc/dispatch' style = 'display:none;'>
	Enter date to find day of the week- <input type = 'text' name = 'firstDate'><br><br>
	<input type = 'hidden' name = 'operation' value = '5'>
	<input type = 'submit' name='findDay' value = 'Submit'>
</form>

<form id = '6' action = '/spring-mvc/dispatch' style = 'display:none;'>
	Enter date to find Week no - <input type = 'text' name = 'firstDate'><br><br>
	<input type = 'hidden' name = 'operation' value = '6'>
	<input type = 'submit' name='findWeek' value = 'Submit'>
</form>
</div>
<script>
function getOptionValue(){
    var val;
    // get list of radio buttons with specified name
    var radios = document.getElementById('mainForm').elements['operation'];
    
    // loop through list of radio buttons
    for (var i=0, len=radios.length; i<len; i++) {
        if ( radios[i].checked ) { // radio checked?
            val = radios[i].value; // if so, hold its value in val
            break;
        }
    }
    
    	var x = document.getElementById(val);
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
   
}
</script>

</body>

</html>