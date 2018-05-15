Alt- Protocol Description of "My Client-Server Application"
======

When computers communicate with each other, there needs to be a common set of rules and instructions that each computer follows. This specific set of communication rules is called a protocol. 
This document's purpose is to define the protocol used in comunication between the server and client, developed during laboratory work no.5.

**1. Messages Format.**
- All the sent messages from client to server should begin with "/";
- The command format should match `A-Za-z0-9_` format;
- For commands which receive parameter(s), the format should be: "/command_name list_of_parameters". Command name and list of parameters should be split by ` ` blank-space;
- If the entered command does not match any of the supported by the server command, it will return an informative message, proposing a possible command if there is found one(being alike the entered one).


**2. Supported commands.**<br/>
**- /help**<br/>
[Format]: no parameters are allowed;<br/>
[Returned value]: Text consisting of all the supported commands by the server, together with their description(format and returned value);<br/>
	To send parametric greeting /hello {text}<br/>
	To view current time in Сhişinău enter /time<br/>
	To generate a random alphanumeric-password /password<br/>
	To receive a joke enter /entertain-me<br/>
	To view supported commands /help.<br/>

**- /hello {parameter}**<br/>
[Format]: The parameters are entered after a blank-space next to the command name.<br/>
[Returned value]: A parameterized text with the word(s) given as parameters(arguments) to the command.<br/>
	We are happy to wellcome you, Corina Tilea. This is a parameterized greeting message.

**- /time - this (no parameters allowed) command** <br/>
[Format]: no parameters are allowed;<br/>
[Returned value]: the current date and time;<br/>
	Current time in Сhişinău: 15-05-2018 23:00:04

**- /password**<br/>
[Format]: no parameters are allowed;<br/>
[Returned value]: a alphanumeric random generated password;<br/>
	aTBy9cngDT1p

**- /entertain-me**<br/>
[Format]: no parameters are allowed; <br/>
[Returned value]: a randomly selected joke, from the server's jokes bank.<br/>
	 My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.

