let email="";
function getEmail(val)
{
	console.log("here");
	 email=val;
	    let pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{3}$/; 

	    if(email.match(pattern))
	    {
	    	document.getElementById("emailVal").innerHTML="";
	    }
	    else
	    	{
	    	document.getElementById("emailVal").innerHTML="Please Enter a vlid Email";
	    	}
	   

}
function getPass(val)
{
	if(val.length<8)
		document.getElementById("passVal").innerHTML="The password must be atleast 8 letters";	
	else
		document.getElementById("passVal").innerHTML="";
}