# PROGRAMMING EXERCISE

A client in the retail sector provides several files tracking all their sales. Each file contains up to a million lines of the following format:

`31/03/2021##245.39`

Each line consists of the date of sale, a delimiter (##), and the sale amount. Note that multiple sales can exist for the same date. The format of the date and the amount shall be configurable by the user.

Every now and then, the client needs to generate some statistics on their data. Supported statistics:

- Average of earnings for a range of years.
- Standard deviation of earnings within a specific year.
- Standard deviation of earnings for a range of years.

Using Java, C#, or C++, write a console application demonstrating your design and coding skills, and help the client perform the required calculations.

Submit your solution’s source code, a screenshot of a sample run of the application and a set of brief instructions to the client on how to use your application.

---

# Instructions

### Launching the application

execute the command `java Main <path/to/file1> <path/to/file2> ...` with at least one filepath from the root directory of the application
`<path/to/file>`: relative or absolute path of a text file to be analyzed (e.g. `data/data.txt` or `/home/user/sales-analytics-application/data/data.txt`)

