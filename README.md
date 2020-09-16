# Run demo-sl-app

For authentication, add the following line with the API key in ~/.bash_profile
```
export SL_DEMO_API_KEY="keygoeshere"
```
There are other properties that can be configured in src/main/resources/application.properties, but the defaults should work unless you can't run the app on port 3000


To build and run and backend (Requires JAVA_HOME to be pointed at Java 11. See brew install jenv and the setup for that if you need multiple versions):
```
./mvnw spring-boot:run
```

To build and run react client (I used npm version 6.4.1 and node version 10.14.2):
```
cd client/demo-sl-client-app
npm install
npm start
```


# Testing
For the Spring Boot app:
```
./mvnw test
```

For react:
```
npm test
```
Then press 'a' to run all tests
