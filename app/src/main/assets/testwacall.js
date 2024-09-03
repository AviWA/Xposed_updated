Java.perform(function(){
    var voip = Java.use('com.whatsapp.voipcalling.Voip');

    // Overload for endCall with boolean and int parameters
    voip.endCall.overload('boolean', 'int').implementation = function(param1, param2){
        XposedBridge.log("endCall called with parameters: param1 = " + param1 + ", param2 = " + param2);
        return this.endCall(param1, param2);
    };

    // Overload for muteCall with boolean parameter
    voip.muteCall.overload('boolean').implementation = function(param1){
        XposedBridge.log("muteCall called with parameter: param1 = " + param1);
        return this.muteCall(param1);
    };

});