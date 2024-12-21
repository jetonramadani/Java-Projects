# Stage 6/7: New level

## Description
Some players need a challenge, so let's make the secret code in the game harder to guess. You should add support for more than 10 symbols by including letters too. Now, the secret code can contain the 10 numbers `0-9` and the 26 lowercase Latin characters `a-z`. So, the new maximum length for the secret code is 36.

You should also implement a system where users can select the number of possible symbols that can be used to create the secret code. So you should request input twice: once for the secret code length and once for the number of possible symbols in it. Note that the length of the secret code may not match the number of possible symbols in it. Therefore, it is important to verify that the secret code length is not greater than the number of possible symbols as the integers in secret code have to be unique.

Also, since the secret code is not a number anymore, allow the symbol `0` as the first character in a secret code.

## Objectives
In this step, your program should:

1. Ask for the length of the secret code.
2. Ask for the range of possible symbols in the secret code.
3. Generate a secret code using numbers and characters. This time, you should also print the secret code using * characters and print which symbols were used to generate the secret code.
4. Function as a fully playable game.

## Example
The greater-than symbol followed by a space `> ` represents the user input. Note that it's not part of the input.

**Example 1:**
```text
Input the length of the secret code:
> 4
Input the number of possible symbols in the code:
> 16
The secret is prepared: **** (0-9, a-f).
Okay, let's start a game!
Turn 1:
> 1a34
Grade: 1 bull and 1 cow
Turn 2:
> b354
Grade: 2 bulls and 1 cow
Turn 3:
> 93b4
Grade: 4 bulls
Congratulations! You guessed the secret code.
```
**Example 2:**
```text
Input the length of the secret code:
> 6
Input the number of possible symbols in the code:
> 27
The secret is prepared: ****** (0-9, a-q).
Okay, let's start a game!
Turn 1:
> gx567e
Grade: 2 bulls
Turn 2:
> gx089f
Grade: 1 bull and 1 cow
Turn 3:
> g530r9
Grade: 2 bulls
Turn 4:
> gyt0n7
Grade: 2 bulls and 2 cows
Turn 5:
> g0n7ed
Grade: 1 bull and 3 cows
Turn 6:
> g7n0op
Grade: 2 bulls and 4 cows
Turn 7:
> gpo07n
Grade: 6 bulls
Congratulations! You guessed the secret code.
```