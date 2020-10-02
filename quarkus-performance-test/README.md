# Performance-test project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Application generation

This project was created through Maven with the following command:
```shell script
mvn io.quarkus:quarkus-maven-plugin:1.8.1.Final:create     
     -DprojectGroupId=eu.davidemartorana.performance.quarkus
     -DprojectArtifactId=quarkus-performance-test
     -Dextensions="resteasy-jackson,jdbc-postgresql,hibernate-orm,agroal,config-yaml"
```

In a later stage [Liquibase](https://www.liquibase.org/) extension for Quarkus has been added, witht he command:
```shell script
mvn quarkus:add-extension 
     -Dextensions="liquibase"
```

### Database Info
This project connects with a PostgreSQL instance, which is supposed to be running and reachable. Provisionally,
the URL for the database is set in the file `application.yml` as follows:
```yaml
quarkus:
  datasource:
    jdbc:
      url: jdbc:postgresql://localhost:5432/covid19_italy?currentSchema=public
``` 
If an instance of PostgreSQL is running locally and listening to the given port(`5432`), no change is required. Otherwise, edit the above property accordingly.

As soon as the application gets connected with PostgreSQL the beans of [Liquibase](https://www.liquibase.org/) will read the configuration in `resources\db\master.yml` and will create the table in the database. 
As expected by the normal behaviour of Liquibase, the database **IS NOT** created, and neither is the schema. Both are expected to be already present. 

### Adding Panache

```shell script
mvnw quarkus:add-extensions 
     -Dextensions="hibernate-orm-panache"
``` 

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev -DPG_USER=<database username> -DPG_PWD=<database password>
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `performance-test-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ (or _fat-jar_) as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/performance-test-1.0-SNAPSHOT-runner.jar`.

## Packaging as Über-Jar and running the application

If you like packaging the application as an _über-jar_ (or _fat-jar_) that contains all the dependencies, then you need to add 
the following property in the `application.yml` file:
```yaml
quarkus:
  package:
    type: uber-jar
```
The mvn command lines remain the same: `./mvnw package`.

## Creating a native executable

You can create a native executable using: 
```shell script
  ./mvnw package -Dquarkus.package.type=native -DPG_USER=<database username> -DPG_PWD=<database password>
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

### Running the native executable
You can then execute your native executable with:
```shell script
 ./target/quarkus-performance-test-runner -DPG_USER=<database username> -DPG_PWD=<database password>
```


### Other Resources
If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
