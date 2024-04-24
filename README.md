# Big-Words
Simple Java program that shows your keyboard input in big white letters on a full black screen

## Controls
* Enter to clear the screen
* Backspace to clear last letter
* Hit "Shift + Space" to toggle between
Default and One Word mode

### Default Mode
Writes the largest font possible on one line

### One Word Mode
Writes the largest font possible for one word.
The space bar now acts as a way to separate new words.
For example, after typing "The" and hitting space, "The" will stay on screen until the next key is pressed. Typing "The Program" will first show "The" and then show "Program" once the user types the first letter of the next word.

_Note: "W" cannot be used because of strange
letter wrapping when displayed in large font.
Please let me know if there is a fix to this!
See comment in code in the keyboard listener_

## Next Step Ideas
* Create faster font size optimization agorithm
* Learn how to implement a state machine for different modes
* Change Font color with a new mode