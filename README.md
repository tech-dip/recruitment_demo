# recruitment_demo
This is a  demo of backend service that handles a recruiting process. The process
requires two types of objects: job offers and applications from candidates. minimum
required fields for the objects are:

### Offer:

jobTitle (unique)
startDate
numberOfApplications


### Application:

related offer
candidate email (unique per Offer)
resume text
applicationStatus (APPLIED, INVITED, REJECTED, HIRED)

### Use cases
#### ->user has to be able to create a job offer and read a single and list all offers.
#### ->candidate has to be able to apply for an offer and can see status or job applied by him.
#### ->user has to be able to read one and list all applications per offer.


### SPRINGBOOT 2.3.2
### MySQL 8.0
### MAVEN
### JAVA 8




