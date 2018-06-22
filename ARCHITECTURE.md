## Architectural choices

* My goal was to keep the solution simple, so the solution chosen here is to use the circuit breaker pattern for failover
* To implement the circuit breaker pattern Netflix [Hystrix](https://github.com/Netflix/Hystrix) library is used

## Constraints of the solution provided

* Data loss possibility in case of both mail service providers are down as the logic only allows failover from primary to secondary but not vice-versa
* Mail service provider errors messages could be unified to provide a consistent error message to the user

## Resiliency to data loss

* The first plan I have, is to save all email request from the user to a DB and set the status to complete once I receive 2xx message from the provider
* In the first plan either primary or secondary provider can try to send all incomplete mails at once, or there could be a compensating batch job to re-attempt the failed deliveries
* The second plan could be to use a message queue to queue and process email requests from there

