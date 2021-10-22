#!/bin/sh

# Bash script that runs PlayGame.java and then changes the working
# directory back to the "game" folder.
# The script must be located and run in the "game" folder for this to work.

# To run this script, type "./test.sh" (without quotation marks) into
# the command line from inside the "game" folder.
# if you try to run the script and get a "permission denied" error,
# type "chmod u+x" (without quotation marks) into the command line
# and then try running the script again.

javac *.java && cd .. && java game.PlayGame && cd game
