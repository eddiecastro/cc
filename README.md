# Run demo-sl-app

For authentication, add the following line with the API key in ~/.bash_profile
```
export SL_DEMO_API_KEY="keygoeshere"
```

To build and run and backend (Requires Java 11):
```
./mvnw spring-boot:run
```

To build and run react client:
```
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
