# Stage 1/5 Analyze the words

## Description
In this task, you will practice parsing an encrypted message by dividing it into individual characters. This skill will be essential for future stages where you will be tasked with decrypting messages.

## Objectives
Your program should perform the following steps:

1. **Read Input**

    - Prompt the user with the message: `Input string:`
    - Read a single line of text from the console.
    - Assume that the input string will contain alphabetic characters, space characters, and punctuation marks.
    - There is no limit on the length of the input string.
2. **Process and Output Characters:**

    - Output all characters in the string, each separated by a single space.
    - This includes space characters, which should be represented as they are, without removal or substitution.
## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

**Example 1:**
```text
Input string:
> Greetings!

G r e e t i n g s !
```

**Example 2:**
```text
Input string:
> Who am I?

W h o   a m   I ? 
```