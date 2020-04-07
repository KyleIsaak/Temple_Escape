# Temple Escape

A game in which players attempt to collect all of the keys in a level
before reaching the exit to progress towards more difficult levels with a focus
on a high score system.

## Getting Started

These instructions will get you a copy of the project up and running on your local
machine for development and testing purposes.

### Prerequisites

Latest Java JDK: [Download here](https://www.oracle.com/java/technologies/javase-downloads.html).

Apache Maven: [Download here](https://maven.apache.org/download.cgi).

IntelliJ IDEA: [Download here](https://www.jetbrains.com/idea/).

### Installing

Install java JDK and update path variable.

```
1. Run the java installer.
2. Navigate to system environment variables.
3. Add / update JAVA_HOME variable.
4. Add %JAVA_HOME% to path variable.
```

[Download](https://maven.apache.org/download.cgi) and [install](https://maven.apache.org/install.html) Maven

```
1. Extract Maven distrubution in any directory
2. Add the bin directory of the created directory to the PATH environment variable
3. Confirm that Maven was installed properly by typing mvn -v in terminal / cmd / powershell
```

[Download](https://www.jetbrains.com/idea/) and install IntelliJ IDEA
```
Follow promts in the installer to set up your IDE
```

Open the pom.xml file as a project using intelliJ to edit the project.

## Running the tests

All of the tests can be run using the command

```
mvn test
```


### gameLogic Tests

All of these classes test the functionality of baic game objects like the player, rewards, and traps.

Example from ScoreTest:
```
    @Test
    void getScore() {
        int testAmount = 513;
        Score scoreTest = new Score(testAmount);

        assertEquals(testAmount, scoreTest.getScore());
    }
```

## Built With

* [IntelliJ](https://www.jetbrains.com/idea/download/) - IDE used
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Kyle Isaak** - [kisaak@sfu.ca](mailto:kisaak@sfu.ca?subject=[Temple%20Runner])
* **Jocelyn Gau** - [jgau@sfu.ca](mailto:jgau@sfu.ca?subject=[Temple%20Runner])

## License

This project is intended for educational purposes only. Please contact the authors listed above for permission to redistribute this project.

## Acknowledgments

* Thanks to Billie Thompson for the
[README template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2#file-readme-template-md).


