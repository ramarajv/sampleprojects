$(document).ready( function() {
	
	userId = localStorage.getItem("userId");
	accessToken = localStorage.getItem("token");
	imei = localStorage.getItem("imei");
	
	if(imei != null && imei !='' && imei != undefined)
	{
	 $("#form-second").attr("style","display:block;");
  	 $("#form-con").attr("style","display:none");
		window.clearInterval(autofetchNavigation);
		autofetchNavigation = 0;
		if(autofetchNavigation==null || autofetchNavigation==0){
		fetchTracking(imei);
		autofetchNavigation=window.setInterval(function(){fetchTracking(imei);},30000);
		}
	}
	else if(userId !=null && userId !='' && accessToken !=null && accessToken !='')
	{
	 $("#form-second").attr("style","display:block;");
  	 $("#form-con").attr("style","display:none");
	 loadDefaultMap();
	}
	
	////////////////////////////////////////
	$('#locateMap').click( function() {
		var imeis = $("#imeis").val();
		imei = $("#imeis").val();
		
		localStorage.setItem("imei",imei);
		window.clearInterval(autofetchNavigation);
		autofetchNavigation = 0;
		if(imeis !='' && imeis !=undefined)
		{
		if(autofetchNavigation==null || autofetchNavigation==0){
		fetchTracking(imeis);
		autofetchNavigation=window.setInterval(function(){fetchTracking(imeis);},30000);
		//fetchTracking(imeis);
		}
		
		}
		});
		
		$('#showHistory').click( function() {
		 $("#form-history").attr("style","display:block;");
		
		});
		
		$('#bringHistory').click( function() {
		
		var sdate = $("#start_datetimepicker").val();
		var edate = $("#end_datetimepicker").val();
		
		if(sdate != '' && sdate != undefined && edate != '' && edate != undefined)
		{
		fetchHistory(sdate,edate);
		}
		else
		{
		alert("Please select Start Date and End Date");
		}
		});
		
		
	///////////////////////////////////////
	
	
});

function fetchHistory(sdate,edate)
{
	var sd = new Date(sdate);
	var milliseconds = sd.getTime();
	var begs = milliseconds / 1000;
	
	var ed = new Date(edate);
	var milliseconds = ed.getTime();
	var ends = milliseconds / 1000;
	var imeiPass = '';
	var imeiHistroy = $("#imeis").val();
	if(imeiHistroy.indexOf(',') != -1)
	{
	imeiPass = imeiHistroy.split(',');
	imeiPass = imeiPass[0];
	}
	else
	{
	imeiPass = imeiHistroy; 
	}
$.ajax({
 		type: "GET",
 		url: urlForServer+"testing/histroy?token="+accessToken+"&id="+userId+"&imei="+imeiPass+"&btime="+begs+"&etime="+ends,
 		success: function(response){
 		
 		if(response !=null && response != '')
 		{
		if(response.ret != 0)
 		{
 		var errorMsg = errorMsgFromAPI(response.ret);
		alert(errorMsg);
 		}
 		else
		{
			generateMapForHistory(response.data);
 		}
		}
 			  },
 			  error: function(){
 			  } ,
 			  dataType: "json",
 			  contentType: "application/json"
 		  });
		
}


function fetchTracking(imeis)
{
$.ajax({
 		type: "GET",
 		url: urlForServer+"testing/tracking?token="+accessToken+"&id="+userId+"&imei="+imeis,
 		success: function(response){
 		
 		if(response !=null && response != '')
 		{
		if(response.ret != 0)
 		{
 		var errorMsg = errorMsgFromAPI(response.ret);
		alert(errorMsg);
 		}
 		else
		{
 		var markers = response.data;
		var zoomLevel = 14;
		if(markers !=null && markers != undefined && markers != '')
		{
			if(markers.length >= 3)
				zoomLevel = 5;
			else if(markers.length >= 2)
				zoomLevel = 8;
		}
		
		if(autofetchNavigation==null || autofetchNavigation==0){
		generateMap(markers,zoomLevel);
		}
		else
		{
		setMarkers(markers,zoomLevel);
		}
 		}
		}
 			  },
 			  error: function(){
 			  } ,
 			  dataType: "json",
 			  contentType: "application/json"
 		  });
		
}


function generateMapForHistory(latlongmark)
{
		window.clearInterval(autofetchNavigation);
		autofetchNavigation = 0;
		map = null;
		
		var path = new google.maps.MVCArray();
        var service = new google.maps.DirectionsService();
        var infoWindow = new google.maps.InfoWindow();
		
           var mapOptions = {
				center: new google.maps.LatLng(latlongmark[0].lat, latlongmark[0].lng),
				zoom: 13,
	            panControl: true,
	            zoomControl: true,
	            scaleControl: true,
				mapTypeId: google.maps.MapTypeId.ROADMAP
				};
				
				var map2 = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
				
           var lat_lng = new Array();
           for (i = 0; i < latlongmark.length; i++) {
               var data = latlongmark[i]
               var myLatlng = new google.maps.LatLng(data.lat, data.lng);
               lat_lng.push(myLatlng);
			 
               var marker = new google.maps.Marker({
                   position: myLatlng,
                   map: map2,
				   icon :'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png',
                   
               });
               (function (marker, data) {
                   google.maps.event.addListener(marker, "click", function (e) {
                       infoWindow.setContent(data.description);
                       infoWindow.open(map2, marker);
                   });
               })(marker, data);
			   
			    if( i == 0 || i == (latlongmark.length-1))
			   {
						var gpsTime = new Date(0); //
						gpsTime.setUTCSeconds(data.gps_time);
						gpsTime = dateTimeString(gpsTime)
						var contentString = '<div id="content" style="height:100px;font-size:15px;">'+
						  '<h6 style="height:0px;" > Lat :: '+data.lat+'</h6>'+
						  '<h6 style="height:0px;" > Lang :: '+data.lng+'</h6>'+
						  '<h6 style="height:0px;" > Speed(KM/H):: '+data.speed+'</h6>'+
						  '<h6 style="height:10px;" > Satelite Time :: '+gpsTime+'</h6>'+
						  '</div>';

					  var infowindow = new google.maps.InfoWindow({
						content: contentString,
						maxWidth: 150
					  });

					  infowindow.open(map2, marker);
			   }
           }
          
}



function generateMap(markers,zoomLevel)
{
		var mapOptions = {
				center: new google.maps.LatLng(markers[0].lat, markers[0].lng),
				zoom: zoomLevel,
	            panControl: true,
	            zoomControl: true,
	            scaleControl: true,
				mapTypeId: google.maps.MapTypeId.ROADMAP
				};
				map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
				setMarkers(markers,zoomLevel);
}

var infowindowForTrack ='';
var markerForTrack = '';
var markerForIcon = '';		
var markersArray =[];
var allInfos = [];

function setMarkers(markers,zoomLevel) {
  
  if(map != undefined && map !=null && map != '')
  {
  
  var image = {
    url: 'image/car.png',
    // This marker is 20 pixels wide by 32 pixels high.
    size: new google.maps.Size(40, 32),
    // The origin for this image is (0, 0).
    origin: new google.maps.Point(0, 0),
    // The anchor for this image is the base of the flagpole at (0, 32).
    anchor: new google.maps.Point(0, 32)
  };
  
  var shape = {
    coords: [1, 1, 1, 20, 18, 20, 18, 1],
    type: 'poly'
  };
  
 
	
	for (var i=0; i<markersArray.length; i++) {
     markersArray[i].setMap(null);
		}
	markersArray = [];
	
	for (var i = 0; i < allInfos.length; i++) {
            allInfos[i].close();
        }
	allInfos = [];
	
	map.setZoom(zoomLevel);
	for (i = 0; i < markers.length; i++) {
	var data = markers[i];
				var gpsTime = new Date(0); //
 				gpsTime.setUTCSeconds(data.gps_time);
				gpsTime = dateTimeString(gpsTime);
				
 				var sysTime = new Date(0); //
 				sysTime.setUTCSeconds(data.sys_time);
				sysTime = dateTimeString(sysTime);
				
 				var serTime = new Date(0); //
 				serTime.setUTCSeconds(data.server_time);
				var status ="Online";
				var statusColor = "color:green";
				
				if(data.device_info == 3)
					status ="Offline";
				else if(data.device_info == 1)
					status ="Not enabled";
				else if(data.device_info == 2)
					status ="Expired";
					
				if(status != 'Online')
				statusColor = "color:red";
				
	  var title = "IMEI :"+data.imei+", device_info : " +data.device_info +", date-time :"+gpsTime 
	  markerForIcon = new google.maps.Marker({
      position: {lat: data.lat, lng: data.lng },
      map: map,
      icon: image,
      shape: shape,
      title: title,
      zIndex: i
    });
	markersArray.push(markerForIcon);
	var iconURLPrefix = 'http://maps.google.com/mapfiles/ms/icons/';
	var sppedColor ='color:green';
	if(data.device_info == 3)
	{
	iconURLPrefix = iconURLPrefix + 'red-dot.png';
	}
	else if(data.speed == 0)
	{
	iconURLPrefix = iconURLPrefix + 'yellow-dot.png';
	sppedColor ='color:blue';
	}
	else
	{
	iconURLPrefix = iconURLPrefix + 'green-dot.png';
	}
	
	if(data.speed >= 50)
	sppedColor ='color:red';
	
	markerForTrack = new google.maps.Marker({
      position: {lat: data.lat, lng: data.lng },
      map: map,
	  draggable: true,
	 
	  icon: iconURLPrefix,
      animation: google.maps.Animation.BOUNCE 
    });
	markersArray.push(markerForTrack);
	// infoview window
	 var contentString = '<div id="content" style="height:100px;font-size:15px;">'+
      '<h6 style="height:0px;"> Lat  :: '+data.lat+'</h6>'+
	  '<h6 style="height:0px;"> Lang :: '+data.lng+'</h6>'+
	 
	  '<h6 style="height:0px;'+sppedColor+'"> Speed(KM/H):: '+data.speed+'</h6>'+
	 
	 
	  '<h6 style="height:0px;'+statusColor+'"> Status :: '+status+'</h6>'+
	 
	  
	  '<h6 style="height:12px;"> Satelite Time :: '+gpsTime+'</h6>'+
	  '<h6 style="height:12px;"> Server Time :: '+sysTime+'</h6>'+
      '</div>';

  infowindowForTrack = new google.maps.InfoWindow({
    content: contentString,
    maxWidth: 100,
	maxHeight: 60
  });
  infowindowForTrack.open(map, markerForTrack);
  allInfos.push(infowindowForTrack);
  }
  }else
  {
  generateMap(markers,zoomLevel);
  }
}

function loadDefaultMap()
{
if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(function (p) {
	        var LatLng = new google.maps.LatLng(p.coords.latitude, p.coords.longitude);
	        var mapOptions = {
	            center: LatLng,
	            zoom: 13,
	            panControl: true,
	            
	            zoomControl: true,
	            scaleControl: true,
	            mapTypeId: google.maps.MapTypeId.ROADMAP
	        };
	        //var map = new google.maps.Map(document.getElementById("dvMap"), mapOptions);
	        var mapDefault = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	        
	        var marker = new google.maps.Marker({
	            position: LatLng,
	            map: mapDefault,
	            title: "<div style = 'height:60px;width:200px'><b>Your location:</b><br />Latitude: " + p.coords.latitude + "<br />Longitude: " + p.coords.longitude,
	            animation: google.maps.Animation.DROP
	        });
			markersArray.push(marker);
			
	        google.maps.event.addListener(marker, "click", function (e) {
	            var infoWindow = new google.maps.InfoWindow();
	            infoWindow.setContent(marker.title);
	            infoWindow.open(mapDefault, marker);
				allInfos.push(infowindowForTrack);
	        });
	    });
	} else {
	    alert('Geo Location feature is not supported in this browser.');
	    var mapOptions = {
	            //center: LatLng,
	            zoom: 13,
	            panControl: true,
	            
	            zoomControl: true,
	            scaleControl: true,
	            mapTypeId: google.maps.MapTypeId.ROADMAP
	        };
	        //var map = new google.maps.Map(document.getElementById("dvMap"), mapOptions);
	        var mapDefault = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	}
}

function dateTimeString(date)
{
 return(date.getDate()+"/"+(date.getMonth()+1) +"/"+date.getFullYear()+" -- "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
}

function errorMsgFromAPI(statusCode)
{
	var errorMsg = '';
	
	switch (statusCode) {
    case 10001:
        errorMsg = "System Error";
        break;
    case 10002:
        errorMsg = "The API you request does not exist";
        break;
    case 10003:
        errorMsg = "Request frequence exceeds limitation";
        break;
    case 10004:
        errorMsg = "access_token not existed";
        break;
    case 10005:
        errorMsg = "access_token error";
        break;
    case 10006:
        errorMsg = "access_token has expired, a new one should be acquired";
        break;
    case  20001:
        errorMsg = "Account or Password Error";
        break;
	case 20002:
        errorMsg = "Required Parameter missing (%s)";
        break;
    case 20003:
        errorMsg = "Parameter Value Not Valid";
        break;
    case 20004:
        errorMsg = "Account does not existed";
        break;
    case 20005:
        errorMsg = "Not authorized to get the account info";
        break;
    case 20006:
        errorMsg = "The count of targets under the account (%s) exceeds limitation";
        break;
    case 20007:
        errorMsg = "IMEI (%s) is not existed";
        break;
	case 20008:
        errorMsg = "Not authorized to get the target info";
        break;
    case 20009:
        errorMsg = "Count of targets being requestd exceeds limitation";
        break;
    case 20010:
        errorMsg = "Target (%s) has expired";
        break;
    case 20011:
        errorMsg = "Map Type Error";
        break;
    case 20012:
        errorMsg = "Latitude or Longitude Not Valid";
        break;
}
return errorMsg;
}


