# CLIENT REQUIREMENTS

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

# USING THE APPLICATION

### Instructions

**0. Download OpenJDK:**

The Java Development Kit (JDK) is a software development environment used for creating Java applications and applets. It's a comprehensive toolkit that includes everything developers need to write, compile, debug, and run Java programs.

The JDK contains several key components:

- Java Compiler (javac) - Converts Java source code (.java files) into bytecode (.class files) that can run on the Java Virtual Machine.

- Java Runtime Environment (JRE) - Includes the Java Virtual Machine (JVM) and core libraries needed to run compiled Java programs.
Development Tools - Various utilities like the debugger (jdb), documentation generator (javadoc), JAR file creator (jar), and other command-line tools for development tasks.

- Standard Libraries and APIs - Extensive collection of pre-written classes and interfaces for common programming tasks like file I/O, networking, GUI development, and data structures.

- Documentation and Examples - API documentation and sample code to help developers understand how to use the various components.

The JDK is different from the JRE (Java Runtime Environment) in that the JRE only contains what's needed to run Java applications, while the JDK includes both the runtime environment and the development tools. If you just want to run Java programs, you need the JRE. If you want to develop Java programs, you need the full JDK.

Oracle provides the official JDK, but there are also **open-source alternatives like OpenJDK**, which forms the basis for many other Java distributions from companies like Amazon (Corretto), Eclipse (Adoptium), and others.

**1. Compile the program:**

  - Execute the following command from the project root directory:
  `javac -d classes src/Main.java src/utils/*.java`

**2. Run the program:**

  - Execute the following command from the project root directory
  `java -cp classes src.Main <path/to/file1> <path/to/file2> ...`,
  with at least one filepath as argument
  - `<path/to/file>` is the relative or absolute path of a text file to be analyzed
  (e.g. `data/data1.txt` or `/home/user/sales-analytics-application/data/data1.txt`)
