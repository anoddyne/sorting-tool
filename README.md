[EN](README.md) | [RU](README.ru.md)
# Sorting Tool

This is a console-based sorting application that allows sorting data from different sources (console input or file) and different types (numbers, words, lines). The program supports sorting by natural order and by count.

## Features

- Sorts data by **natural order** or **by count**.
- Supports sorting of different data types: **numbers**, **words**, and **lines**.
- Reads input from console or file.
- Outputs results to console or file.

## Usage

### Command Line Arguments

The application accepts the following arguments:

- `-dataType [long|line|word]` — Specifies the type of data to be sorted.
- `-sortingType [natural|byCount]` — Specifies the sorting method.
- `-inputFile <filename>` — Specifies the input file (optional).
- `-outputFile <filename>` — Specifies the output file (optional).

### Example Commands

#### Sort numbers from an input file and save the result to an output file:

```bash
java Main -dataType long -sortingType natural -inputFile input.txt -outputFile output.txt
```
