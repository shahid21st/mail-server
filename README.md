## Local deployment with jar

* clone the repo
* Go to the repo directory in shell
* Run `mvn clean install`
* Use the separately supplied `mailProps.env` file and run `source mailProps.env`
* Run `ava -jar -Dspring.profiles.active=local target/mail-service-0.0.20-SNAPSHOT.jar`
* Run `mvn clean install docker:build docker:push -Ddocker.username=docker_username -Ddocker.password=docker_password` to push docker image to docker hub

## Local deployment with docker

* [Install Docker](https://docs.docker.com/engine/installation/)
* Run docker
* Use the separately supplied `mailPropsDocker.env` file
* Run `docker run -p 9080:9080 -e "SPRING_PROFILES_ACTIVE=local" --env-file "mailPropsDocker.env" shahid21st/mail-service:0.0.18-SNAPSHOT`

## AWS deployment

* Launch an ec2 instance
* Download the key file to ssh to the instance
* Open up port 80 for outside traffic to the instance
* ssh to the instance `ssh -i ec2-test.pem ec2-user@ec2-54-88-89-80.compute-1.amazonaws.com`
* Copy or create a file similar to `mailPropsDocker.env` file
* Run `docker run -p 80:9080 -e "SPRING_PROFILES_ACTIVE=cloud" --env-file "mailPropsDocker.env" shahid21st/mail-service:0.0.20-SNAPSHOT`

## How to use
* Determine the app url 
* For local it would be like - http://localhost:9080/email/send
* For AWS it would be like (depends on ec2 instance public DNS) - http://ec2-54-88-89-80.compute-1.amazonaws.com/email/send
* This is the email contract with the provided API:
```json
{  
   "to":[  
      {  
         "email":"emal@gmail.com",
         "name":"Sample Name"
      }
   ],
   "cc":[  
      {  
         "email":"john.doe@example.com",
         "name":"John Doe"
      }
   ],
   "bcc":[  
      {  
         "email":"john.doe1@example.com",
         "name":"John Doe1"
      }
   ],
   "subject":"Hello, World!",
   "from":{  
      "email":"sam.smith@example.com",
      "name":"Sam Smith"
   },
   "body":"Sample email body"
}
```
* By default, the app will try to send email using SendGrid, if SendGrid is unavailable or returns non 200 HTTP status code, it will try to use MailGun
* Sample successful response
```json
{
    "success": true,
    "httpStatus": null,
    "message": null
}
```
* Sample error response
```json
{
    "timestamp": 1529635390783,
    "status": 500,
    "error": "Internal Server Error",
    "exception": "org.springframework.web.client.HttpClientErrorException",
    "message": "400 BAD REQUEST",
    "path": "/email/send"
}
```

## Known issues
* In the provided API contract/user input, only `To` field is validated 
* Error reporting in case of non 200 status code is not extensive, but I think adequate in terms of error received from the mail service providers
* Data loss possibility in case of both mail service providers are down as the logic only allows failover from primary to secondary but not vice-versa
* Only MockMVC controller tests are done, integration tests could be written to test the circuit breaker functionality which is not trivial
* MailGun only allows to send email from authorised domain and authorised recipients in sandbox settings, so the from address need to be from a sandbox domain and currently the only authorised recipient is my gmail address