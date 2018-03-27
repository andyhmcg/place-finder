# place-finder

This project is a SpringBoot application provides a rest end point to find
a venue by name and return a list of other venues which have either been recommended 
or a popular.

The application uses the foursquare api. 
https://developer.foursquare.com/docs/api/getting-started

In particular the following foursquare endpoints:-

https://api.foursquare.com/v2/venues/search
https://api.foursquare.com/v2/venues/explore
https://api.foursquare.com/v2/venues/trending

The search algorithm used is as follows

Use the /search endpoint to return a list of Venues which match the supplied name.
For each Venue returned use the /explore endpoint to return a list of recommended venues
and the /trending endpoint to return a list of popular venues.


The application can be run using 
./gradlew bootRun.

# SWAGGER
Once running the API documentation can be found at

http://localhost:8080/swagger-ui.html

# APPROACH
When developing the application I took the approach of starting with the WEB layer and working through to the 
service layer and foursquare client layer.

I started with the web layer as this allows defining and testing of the rest interface. This is useful if we have some UI 
development at the same time as it allows the UI dev early access to the endpoint.

I used MockMvc to test the WebLayer mocking out the service layer dependencies.


# EXAMPLES
SAMPLE USAGE
curl -X GET "http://localhost:8080/places?name=Costa&lng=51.4826&lat=0.0077" -H  "accept: application/json;charset=UTF-8"

SAMPLE RESULT 
{
  "venues": [
    {
      "id": "515dc7a5f0e4ca28ef9c08e7",
      "name": "Holiday Inn Express",
      "location": {
        "lng": 51.492295,
        "lat": 0.01051
      }
  ]
}

#TODO
The security needs looking at currently I am using a hard coded OAUTH token

Add integration tests. I have been using POSTman to test as I go but need automated integration tests.









