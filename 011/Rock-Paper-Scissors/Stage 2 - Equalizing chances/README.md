# Stage 2/5: Equalizing chances

## Description
Well, now let's do something more tangible. Nobody wants to play the game where you always lose. We can use the power of the `Random` class to make the game a bit more challenging.

Write a program that reads input from users, chooses a random option, and then says who won: a user or the computer.
There are a few examples below to provide the output for any outcome (`<option>` is the option chosen by your program):

- Loss: `Sorry, but the computer chose <option>`;
- Draw: `There is a draw (<option>)`;
- Win: `Well done. The computer chose <option> and failed`;
## Objectives
Your program should:

- Read the user input that includes an option;
- Choose a random option;
- Compare the options and determine the winner;
- Output one of the lines above depending on the result of the game.
## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

**Example 1**:
```text
> rock
Well done. The computer chose scissors and failed
```

**Example 2**:
```text
> scissors
There is a draw (scissors)
```

**Example 3**:
```text
> paper
Sorry, but the computer chose scissors
```