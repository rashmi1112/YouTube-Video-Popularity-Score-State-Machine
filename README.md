# YouTube Video Popularity Score State Machine

**Name:** Rashmi A.Badadale

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt"
```
Note: Arguments accept the absolute path of the files.

## Description:

The program takes 2 arguments, input and output files. The program parses the input for required strings and perform respective operations based on the 
parsed input. The goal of the assignment is to implement a state pattern for a youtube channel. The channel has 4 states and based
on the current popularity score of the channel, it changes its state. 

The states are responsible for all the actions that are to be performed when in that particular state and print the results accordingly. 

Datastructures used : 

->Hashmap for storing the video parameters and storing the available states of the channel. 

->Hashmap is an implementation of Map interface. It is used here for storing the video objects because it can be accessed in uniform time of O(1), given the key value and takes O(n) amount of space, where 'n' would be the number of keys, videoName in this case. 

A String  builder is used to store the output result and then passed on to an output file through a buffered writer and also to the standard output console. 

Popularity Score Format: 

The popularity score of the channel at an instance, is provided in a floating point format. 


