# Stage 2/8: Duck numbers

## Description
Your next task is to determine whether a number is a **Duck number**. A Duck number is a positive number that contains zeroes. For example, 321**0**, 8**0**5**0**896, 7**0**7**0**9 are Duck numbers.

In this stage, we need to simplify the way we display the information. We already have several properties that we need to show; we are going to add new features to our program in each stage. From now on, the card should follow this notation:

```text
Properties of {number}
{property}: true/false
{property}: true/false
...
{property}: true/false
```
In this stage, the properties are `even`, `odd`, `buzz` and `duck`. For `14`, the program output should look like this:
```text
Properties of 14
        even: true
         odd: false
        buzz: true
        duck: false
```
The property order, indentation, and spaces are not checked by the tests. The tests are also case-insensitive.

## Objectives
Your program should print the properties of a natural number. In this stage, your program should:

1. Ask a user to enter a natural number;
2. If the number is not natural, the program should print an error message;
3. If the number is natural, the program should indicate the properties of the number;
4. Finish the program after printing the message.

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

**Example 1:**
```text
Enter a natural number:
> -7
This number is not natural!
```
**Example 2:**
```text
Enter a natural number:
> 15
Properties of 15
        even: false
         odd: true
        buzz: false
        duck: false
```
**Example 3:**
```text
Enter a natural number:
> 14
Properties of 14
        even: true
         odd: false
        buzz: true
        duck: false
```
**Example 4:**
```text
Enter a natural number:
> 102
Properties of 102
        even: true
         odd: false
        buzz: false
        duck: true
```

