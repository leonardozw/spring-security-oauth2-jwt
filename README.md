# Spring Security with OAuth2 and JWT

This project is a simple example of how to use Spring Security with OAuth2 and JWT.

## Technologies
- Java 17
- Spring Boot 3.1.3
- Spring Web
- Spring Security
- Spring OAuth2 Client
- Spring OAuth2 Resource Server

## Requirements
- JDK 17
- Maven

## Google Credentials

Before running the app you must have your Google Credentials ready,
you can get it from [Google Cloud Console](https://console.cloud.google.com/apis/credentials), 
you will need a Client ID and a Client Secret.

After getting your credentials you need to set them in `application.yml` file located in `src/main/resources` folder.

It should look like this:
- `client-id: paste your client id here` 
- `client-secret: paste your client secret here`

## Running the app

Just a reminder, this project will run on `localhost:8080`, so make sure that this port is not being used.

- Clone the project
- Go to the project folder and open the terminal

Using `.jar` file:
- Run `mvn clean package`
- Run `java -jar target/´project-name-here´-0.0.1-SNAPSHOT.jar`
- To check project name go to `target` folder and locate the `.jar` file

Using `maven`:
- Run `mvn clean spring-boot:run`

## Testing the app

- Open your browser and go to `http://localhost:8080/`

### Endpoints
- `/` - This endpoint is public, you can access it without any authentication
- `/cookie` - This endpoint is protected, you need to be authenticated to access it
- `/jwt` - This endpoint is protected, you need to inform the token to access it

### OAuth2 Flow
- Access `http://localhost:8080/cookie`
- You will be redirected to Google Login Page
- Login with your Google Account
- You will be redirected to `http://localhost:8080/login/oauth2/code/google`
- You will be redirected to `http://localhost:8080/cookie`
- You will see a bunch of information of your account
- With this endpoint a session is created and stored in a cookie

### JWT Flow

To test the JWT you will need any tool to make HTTP requests, in this case i'm using Httpie.

- Before testing the JWT you need to get the token, to do that you need to make a request to `/oauth2/authorization/google`
- So login with your Google Account and access the `/cookie` endpoint, you will see that among the information there is a `token` field, get that token
- Using Httpie type this command in your terminal `https -A bearer -a token http://localhost:8080/jwt` and replace `token` with the token you got from `/cookie` endpoint
- With that done your response should look like this:
```
HTTP/1.1 200
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Connection: keep-alive
Content-Length: 1879
Content-Type: text/plain;charset=UTF-8
Date: Wed, 06 Sep 2023 19:59:06 GMT
Expires: 0
Keep-Alive: timeout=60
Pragma: no-cache
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 0

<h1>Hello JWT World!</h1>
<h3>Logged in as: information about your account should be here</h3>
<h3>Email: email you logged in should be here</h3>
<h3>Token: token should be here</h3>
```
- With this endpoint a session is not created, you just need to inform the token in the header to access it

## Logout

- Couldn't be more simple, just access `http://localhost:8080/logout` and click the button, you will be logged out.
