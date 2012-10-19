cordova.define("phongap/dateutil", function(require, exports, module) {
	
	
	var showDate = function(successCallbck, errorCallbck){cordova.exec(successCallbck,errorCallbck, 'DateUtil', 'date',[]);},
		showTime = function(successCallbck, errorCallbck){cordova.exec(successCallbck,errorCallbck,'DateUtil', 'time',[]);};
	
	module.exports={
		
		showDate: showDate,
		showTime: showTime
	}
});


DateUtil = require("phongap/dateutil");

//  inovke DateUtil.show() to show DatePicker Dialog
//  invoke DateUtil.time() to show TimePicker Dialog