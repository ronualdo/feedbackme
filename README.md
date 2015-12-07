#Feedbackme-Services

This repository keeps the services used by the application:
https://f33dbackmeweb.herokuapp.com/

#pre-requisites
To run any of the tasks below, create the following environment variable:
export FEEDBACKME_DATASOURCE_URL=jdbc:postgresql://{host}/{databasename}?user={user}&password={password}

##To run the tests
> ./gradlew

##To build locally
> ./gradlew build

##To run the services
> ./gradlew bootRun

##Build Status
> https://snap-ci.com/ronualdo/feedbackme/branch/master

##Production environment

The latest version of the services is available at:
https://f33dbackme.herokuapp.com/

To retrieve the feedbacks of a specific user:
```
GET https://f33dbackme.herokuapp.com/{username}/feedbacks
``
To provide new feedback to a specific user:
```
POST https://f33dbackme.herokuapp.com/{username}/feedbacks
{
  feedbackText: 'the feedback itself',
  author: 'who you are'
}
``