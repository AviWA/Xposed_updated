Java.perform(function(){
	var voip = Java.use('com.whatsapp.voipcalling.Voip');
	voip.endCall.implementation = function(param1){
		XposedBridge.log("Param endCall = " + param1);
	}

	voip.muteCall.implementation = function(param1){
		param1 = false;
		XposedBridge.log("Param muteCall = " + param1);
	}

})