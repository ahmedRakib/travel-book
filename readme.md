This project root folder is the `web`
# Dev Environment Setup
## Tools required
### [NodeJS](https://nodejs.org/)
Install the latest LTS version.
 
### [Yarn](https://yarnpkg.com/getting-started/install)
(make sure to install 1.x version)

### [Docker](https://docs.docker.com/get-docker/)
For Linux, make sure to complete post installation process and  allow Docker as non-root user. Read this link: https://docs.docker.com/engine/install/linux-postinstall/

# How to run the project?
## How to run the server?
1. From IntelliJ Idea
  * Run `Docker compose` that we configured above.
  * Create Spring Boot config for class `AppApplication` and run it.

2. From terminal
  * `docker-compose up`
  * `./gradlew bootRun`

## How to populate preexisting data?
1. Run the sql files from the `sql` folder.

2. From terminal
  * `docker-compose up`
  * `./gradlew bootRun`

## How to run the frontend?
1. From IntelliJ Idea
  * Run `yarn start` that we configured above.

2. From terminal
  * `cd src/main/webapp`
  * `yarn`
  * `yarn start`