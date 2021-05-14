# cowin-notifier
Get email notification for available slots of vaccines in a state for your age group


This is a Spring Webflux based scheduler application which can be used to get email notifications to a user as soon as a slot opens up in their locality for their age group.

The email username/password used to send the notification, exchange server details, notification interval are configurable from the properties files and environment variables.


To run the app
- Clone the repository.
- Open the folder and add the notifier email id and password credentials as environment vars or directly in the property file.
- Current config in app.props are configured for Gmail exchange server, feel free to play around with any other server you might want to use.
- Update the duration in which you want to recieve the notifications.
- Run the App.
