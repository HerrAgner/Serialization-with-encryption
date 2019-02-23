Start ServerMain
Start ClientMain

Program - create arraylist with objects
Sender - sendToServer
SerializeObject - serializeObjectToNetwork, which encrypts before sending object to server

Server - readMessage receives stream with encrypted object
DeserializeObject - deserializeObjectFromNetwork
Checks if object is instance of SealedObject, if so, decrypt it
returns object, then serializes object to file "networkObject.txt"

server - writeMessage deserializes from file
serializeObject encrypts message and sends it back to client

client - Receiver receives stream with encrypted object
DeserializeObject - deserializeObjectFromNetwork
Checks if object is instance of SealedObject, if so, decrypt it
Client then checks if the decrypted object is instance of Collection
if so, it loops through the list and checks instanceof Person, Pet, Square and prints their variables, instance doesn't
match, just print objects tostring.
if the object isn't a Collection, it first checks if the object is a Person and prints it's name,
otherwise it just prints the objects tostring