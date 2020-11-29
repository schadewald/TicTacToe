:: javac *.java
Echo Starting Server
start cmd.exe /k java TicServer
Echo Starting Client 1
start /min cmd.exe /k java TicClientGUI 
Echo Starting Client 2
start /min cmd.exe /k java TicClientGUI