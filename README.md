##Line Counter

This application is designed to simply count lines in files.

####Running
You should have installed Maven on your machine.<br>
In app root directory run: <br> 
**mvn clean package**<br>
java -jar target/LineCounter-1.0-SNAPSHOT.jar


####Command list:
__count [filename]__ - prints number of lines in given file. <br>
__count [directory]__ - prints number of lines in every file in given directory tree.<br>
__quit__ - quitting the program.<br>
