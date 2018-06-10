//var urlForServer='http://192.168.1.65:8080/mnApi/mn1.0/';//test server
var urlForServer='http://localhost:8080/api/';//local server

//To Get Browser details
var is_chrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
var is_safari = navigator.userAgent.toLowerCase().indexOf('safari') > -1;
var is_Ie = navigator.userAgent.toLowerCase().indexOf('msie') > -1;

var isIphone = navigator.userAgent.toLowerCase().indexOf('iphone') > -1;
var isAndroid = navigator.userAgent.toLowerCase().indexOf('android') > -1;
var isIpad = navigator.userAgent.toLowerCase().indexOf('ipad') > -1;
var isIpod = navigator.userAgent.toLowerCase().indexOf('ipod') > -1;

var mobileOS;    // will either be iOS, Android or unknown
var mobileOSver; // this is a string, use Number(mobileOSver) to convert
var autofetchNavigation;

function getOS()
{
  var ua = navigator.userAgent;
  var uaindex;
  // determine OS
  if ( ua.match(/iPad/i) || ua.match(/iPhone/i) || ua.match(/ipod/i))
  {
    mobileOS = 'iOS';
    uaindex  = ua.indexOf( 'OS ' );
  }
  else if ( ua.match(/Android/i) )
  {
    mobileOS = 'Android';
    uaindex  = ua.indexOf( 'Android ' );
  }
  else
  {
    mobileOS = 'unknown';
  }

  // determine version
  if ( mobileOS === 'iOS'  &&  uaindex > -1 )
  {
    mobileOSver = ua.substr( uaindex + 3, 3 ).replace( '_', '.' );
  }
  else if ( mobileOS === 'Android'  &&  uaindex > -1 )
  {
    mobileOSver = ua.substr( uaindex + 8, 3 );
  }
  else
  {
    mobileOSver = 'unknown';
  }
  return mobileOSver;
}

var userId='';
var pass='';
var accessToken ='';
var tokenExpires='';
var showMap = false;
var map = '';
var imei ='';

function checkLogin() {
	
	 userId = $("#userid").val();
	 pass = $("#pass").val();
	if(userId == "" )
     {
        alert( "Please provide your ID!" );
        return false;
     }
     
     if( pass == "" )
     {
        alert( "Please provide your password!" );
      
        return false;
     } 
     getAccessToken(); 
}

function getAccessToken()
{
	if(userId !='' && pass !='')
		{
	$.ajax({
 		type: "GET",
 		url: urlForServer+"testing/login?id="+userId+"&authString="+pass,
 		success: function(response){
 		
 		if(response !=null && response != '')
 		{
 			
 			if(response.ret != 0)
 				{
 				alert("Invalid user pleasr try with different user");
 				}
 			else
 				{
				
 				accessToken = response.access_token;
 				tokenExpires = response.expires_in;
				
				localStorage.setItem("userId", userId);
				localStorage.setItem("token", accessToken);
				
 				$("#form-second").attr("style","display:block;");
 		 		$("#form-con").attr("style","display:none");
 		 		showMap = true;
				loadDefaultMap();
 				}
 		
 		
 		}
 		else
 		{
 		alert( "Please provide Valid details" );
 		}
 			  },
 			  error: function(){
 				$("#form-second").attr("style","display:block;");
  		 		$("#form-con").attr("style","display:none");
  		 		showMap = true;
 			  } ,
 			  dataType: "json",
 			  contentType: "application/json"
 		  });
		}
		
		
}
    




