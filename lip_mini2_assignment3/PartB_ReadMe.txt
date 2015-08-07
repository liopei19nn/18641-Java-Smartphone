Assignment 3 Part B 
Rich Media Project : WitnessJayZ

Name : Li Pei
Andrew ID : lip

This is the read me for part B of Assignment 3. To run the project, load the project file into 
Android Studio and run on the “REAL” Nexus 7 please. 

Attention!! - Emulator cannot run the project correctly due to unknown reason. Please run it on 
a real Nexus 7 or similar tablet devices.

Below is the class description for class and package


(package)exception
	--(class)AutoException : Used to handle input exception, build alert dialog and log in System.out




(package)ui
	--(class)MailListActivity : Used to send email for subscribe. When you press button, it will arouse the system Gmail
							application to send email to me. Notice you have to input a email address with “@” in it

	
	--(class)MainActivity : Four buttons for 4 media. And in the text view there is clickable link for Facebook, Twitter and Wikipedia.
	

	--(class)MusicActivity : Three Tracks could be played here. They are independent, so you could restart each with its own position remembered. Notice that you could only use each track’s pause, you could use pause A to stop playing track B, etc.

	--(class)PictureActivity : Implemented with picture switcher, switch between 5 picture’s cycle. Tap screen to switch.

	--(class)VideoActivity : Only a video view in it. Listen to the fragment in it and play video according to the button id in fragment.




(package)util
	--(interface)OnFragmentInteractionListener : Interface for interaction between fragment and main activity.
	--(class)video_fragment : Fragment implemented here. Used to interact with the video view of VideoActivity.




