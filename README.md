# twitter-elasticsearch-sample
Simple application which catches tweets and index them at elasticsearch.

### Configuration

* Run `docker-compose up -d` to start the elasticsearch container
* Configure the `application.properties`

### Running the application
The application requires argument for running. This argument is the text which will be watched.

##### If you don't have maven:

`./mvnw spring-boot:run -Drun.arguments="twitter"`

##### If you already have maven:

`mvn spring-boot:run -Drun.arguments="twitter"`
