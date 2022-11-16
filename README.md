# Cake Manager Micro Service

## How to run
To run test locally: ./gradlew test
To build locally: ./gradlew build
To run locally: ./gradlew bootRun

## Additional notes
1. This has comprehensive unit test suite, TDD approach
2. When you goto http://localhost:8282/ you will get human-readable cakes in the system.
   For this I used Thymeleaf templating, please refer to the code for more information.
3. Also, this does payload validation via, javax.validation. If it goes wrong HTTP 400 bad-request
   will be thrown.
4. This has Dockerfile integrated (Containerisation) see `Dockerfile` in the project root.
5. This uses gitlab CI/CD see `.gitlab-ci.yml` in the project root.

## TODO
1. Authentication via OAuth2 - This is pending simply because this requires few things to set up.
   Due to limited time frame could not do this, but we can talk through this.
