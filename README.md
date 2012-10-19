phonegap-datepicker
===================

PhoneGap DatePicker plugin for Android

Use the phonegap-datepicker plugin as follows:

- configure config.xml file, add following code

	```xml
	<plugin name="DateUtil" value="com.neusoft.DateUtil" />
	```
- use `phonegap-datepicker.js` in your html

- invoke the plugin javascript API like this

	```js

		
		var success = function(data){
			
			//	console.log(data.year);
			//	console.log(data.month);
			//	console.log(data.day);
		}

		//	show DatePicker Dialog
		DateUtil.show(success,null);

	```


	```js

		
		var success = function(data){
			
			//	console.log(data.hour);
			//	console.log(data.minute);
		}

		//	show TimePicker Dialog
		DateUtil.time(success,null);

	```