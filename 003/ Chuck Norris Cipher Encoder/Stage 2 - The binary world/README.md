# Stage 2: The binary world

## Description
In this stage, you'll learn how to convert each character of a string into a binary form. Any ASCII character has a size of 7 bits; in binary, the form is `0` and `1`. For example, the character `A` has a decimal value of `65`. The binary representation is `1000001`. `b` is `98` in decimal or `1100010` in binary. The space character is `32` in decimal value or `0100000` in binary.

In this stage, you will learn to represent characters in binary form.

## Objectives
In this stage, your program should:

1. Read a string from a console. The input contains a single line.
2. Print `The result:` line, followed by each character of input on a separate line, formatted as `<char> = <binary value>`.

Note that the binary representation must be 7-bit, even if the first digits are zeros. The `Integer.toBinaryString()` and `String.format()` methods can help you with that.

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

**Example 1:**
```text
Input string:
> One

The result:
O = 1001111
n = 1101110
e = 1100101
```

**Example 2:**
```text
Input string:
> 123

The result:
1 = 0110001
2 = 0110010
3 = 0110011
```
**Example 3:**
```text
Input string:
> Who am I?

The result:
W = 1010111
h = 1101000
o = 1101111
  = 0100000
a = 1100001
m = 1101101
  = 0100000
I = 1001001
? = 0111111
```