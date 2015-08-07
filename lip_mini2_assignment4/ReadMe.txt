Assignment 4
Name : Li Pei
Andrew ID : lip

This is the read-me for Assignment4 get geo location and send it by SMS. 

To run this project, please load the SMSLocation project in your android studio and run it on a actual device! The default design is for Nexus 7. If you use virtual device or shut down the location service on actual device, a toast display no location service will always on when you press then button.

You could see the result snap shot in ResultSnapShot.pdf, or see the class diagram in Diagram.jpg.

-----------------------------------------------------------------------------------
Below is the package and class description for this project

(package)exception

	-- (class)ExceptionHandler : This is used for log your exception and handle the exception


(package)ui
	-- (class)MainActivity : This is the main and only one activity for this project. It is an one button activity, with real time
refresh display of your position. You could carry your device and walk, it will show your position on screen in real time, and if you press the button, you could send a message of your current position to a hard coded telephone number (It is my phone number, maybe you could try on a android phone to send me your position).


