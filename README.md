Client will first send the credentials and system 
will authenticate them. If the authentication is successful
server will send back the JWT token as a response which 
client can attach in the authorization header of the subsequent requests and 
if the attached token is valid client will be authorized
to access resources.
