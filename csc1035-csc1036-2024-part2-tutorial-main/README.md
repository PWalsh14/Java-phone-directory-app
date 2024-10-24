# CSC1035/CSC1036 Part 2 Tutorial

This is a tutorial where we learn how to perform version control via Git and GitHub,
and develop an interactive command line application in Java.

# Tutorial Project Specification

Create an interactive Phone Directory application.
A phone directory is a collection of records consisting of contact
information of individuals. Each record should contain a full name
and a phone number. The following functionality is required:
- Adding new records
- Removing contact information by phone number and by full name
- Listing the entire directory
- Saving the directory to the file
- Loading the directory from the file

All of the above actions should be enabled interactively.
In other words, the application must not terminate after every single
action is performed: a user must be able to execute different actions
by providing input from console.

# Further requirements

- Firstname and lastname must be strings of up to 32 characters
- Each character must be a case-insensitive letter of the English alphabet (i.e., in the [a-z][A-Z] range)
- Phone number must be a 9 character long sequence of digits
- All phone numbers in the phone directory must be unique
- Use appropriate system streams to distinguish between normal output and error messages

# Further further requirements

- In addition to domestic phone numbers (see above), add support for storing international phone numbers.
  - International phone numbers should start with "+" followed by a country code (extension) and followed by the phone number.
  - The country and the phone number must be separated by a whitespace/s.
  - There are no length restrictions on the extension and the phone number components.
- Allow tracking the history of phone calls to the individuals from the phone directory
  - List the entire history for all phone numbers. For each record in the history output: 
    - phone number, 
    - person's name, 
    - phone number type (i.e., domestic or international), 
    - data/time of the phone call,
    - the phone call duration
  - Selecting statistic by the phone number. Include the following:
    - Phone calls summary: person's name, number of phone calls to the person, average phone call length
    - List of all phone call made to this number
- Allow storing/loading the history in/from the "database" (i.e., file or files)
